package com.eeepay.zzq.enc.enc;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.Base64;

import java.security.Provider;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 描述：AES加解密（兼容Android7.0）
 * 作者：zhuangzeqin
 * 时间: 2018/5/18-9:14
 * 邮箱：zzq@eeepay.cn
 */
public final class AESUtils {
    private final static String HEX = "0123456789ABCDEF";
    private static final String CBC_PKCS5_PADDING = "AES/CBC/PKCS5Padding";//AES是加密方式 CBC是工作模式 PKCS5Padding是填充模式
    private static final String ECB_PKCS5_PADDING = "AES/ECB/PKCS5Padding";//
    private static final String AES = "AES";//AES 加密
    private static final String SHA1PRNG = "SHA1PRNG";// SHA1PRNG 强随机种子算法, 要区别4.2以上版本的调用方法

    /**
     * 生成一个随机Key
     * 生成随机数，可以当做动态的密钥 加密和解密的密钥必须一致，不然将不能解密
     */
    public static String generateKey() {
        try {
            SecureRandom localSecureRandom = SecureRandom.getInstance(SHA1PRNG);
            byte[] bytes_key = new byte[20];
            localSecureRandom.nextBytes(bytes_key);
            String str_key = toHex(bytes_key);
            return str_key;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 对密钥进行处理
    @SuppressLint("DeletedProvider")
    private static byte[] getRawKey(byte[] seed) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance(AES);
        //for android
        SecureRandom sr = null;
        // 在4.2以上版本中，SecureRandom获取方式发生了改变
        int sdk_version = android.os.Build.VERSION.SDK_INT;
        if (sdk_version > 23) {  // Android  6.0 以上
            sr = SecureRandom.getInstance(SHA1PRNG, new CryptoProvider());
        } else if (android.os.Build.VERSION.SDK_INT >= 17) {   //4.2及以上
            sr = SecureRandom.getInstance(SHA1PRNG, "Crypto");
        } else {
            sr = SecureRandom.getInstance(SHA1PRNG);
        }
        // for Java
        // secureRandom = SecureRandom.getInstance(SHA1PRNG);
        sr.setSeed(seed);
        kgen.init(128, sr); //256 bits or 128 bits,192bits
        //AES中128位密钥版本有10个加密循环，192比特密钥版本有12个加密循环，256比特密钥版本则有14个加密循环。
        SecretKey skey = kgen.generateKey();
        byte[] raw = skey.getEncoded();
        return raw;
    }

    /**
     * 适配9.0以上手机系统
     * aes9.0系统加密返回null问题，对密钥进行处理
     */
    public static byte[] getRawKey9(byte[] seed) throws Exception {
        byte[] rawKey = InsecureSHA1PRNGKeyDerivator.deriveInsecureKey(seed, 32);
        return rawKey;
    }

    /*
     * 加密
     */
    public static String encrypt(String key, String cleartext) {
        if (TextUtils.isEmpty(cleartext)) {
            return cleartext;
        }
        try {
            byte[] result = encrypt(key, cleartext.getBytes());
//            return Base64Encoder.encode(result);
            return new String(Base64.encode(result, Base64.DEFAULT));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加密
     */
    private static byte[] encrypt(String key, byte[] clear) throws Exception {
        //byte[] raw = getRawKey(key.getBytes());
        //兼容9.0系统自动登录密码问题
        //2019年1月17日 18:58:14  LiangAn
        byte[] raw = null;
        int sdk_version = android.os.Build.VERSION.SDK_INT;
        if (sdk_version >= 28) {//兼容9.0
            raw = getRawKey9(key.getBytes());
        } else {
            raw = getRawKey(key.getBytes());
        }
        SecretKeySpec skeySpec = new SecretKeySpec(raw, AES);
        Cipher cipher = Cipher.getInstance(CBC_PKCS5_PADDING);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(new byte[cipher.getBlockSize()]));
        byte[] encrypted = cipher.doFinal(clear);
        return encrypted;
    }

    /**
     * 解密
     */
    public static String decrypt(String key, String encrypted) {
        if (TextUtils.isEmpty(encrypted)) {
            return encrypted;
        }
        try {
//            byte[] enc = Base64Decoder.decodeToBytes(encrypted);
            byte[] enc = Base64.decode(encrypted, Base64.DEFAULT);
            byte[] result = decrypt(key, enc);
            return new String(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     */
    private static byte[] decrypt(String key, byte[] encrypted) throws Exception {
        //byte[] raw = getRawKey(key.getBytes());
        //兼容9.0系统自动登录密码问题
        //2019年1月17日 18:58:14  LiangAn
        byte[] raw = null;
        int sdk_version = android.os.Build.VERSION.SDK_INT;
        if (sdk_version >= 28) {
            raw = getRawKey9(key.getBytes());
        } else {
            raw = getRawKey(key.getBytes());
        }
        SecretKeySpec skeySpec = new SecretKeySpec(raw, AES);
        Cipher cipher = Cipher.getInstance(CBC_PKCS5_PADDING);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(new byte[cipher.getBlockSize()]));
        byte[] decrypted = cipher.doFinal(encrypted);
        return decrypted;
    }

    //二进制转字符
    public static String toHex(byte[] buf) {
        if (buf == null)
            return "";
        StringBuffer result = new StringBuffer(2 * buf.length);
        for (int i = 0; i < buf.length; i++) {
            appendHex(result, buf[i]);
        }
        return result.toString();
    }

    private static void appendHex(StringBuffer sb, byte b) {
        sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
    }

    // 增加  CryptoProvider  类
    public static class CryptoProvider extends Provider {
        /**
         * Creates a Provider and puts parameters
         */
        public CryptoProvider() {
            super("Crypto", 1.0, "HARMONY (SHA1 digest; SecureRandom; SHA1withDSA signature)");
            put("SecureRandom.SHA1PRNG",
                    "org.apache.harmony.security.provider.crypto.SHA1PRNG_SecureRandomImpl");
            put("SecureRandom.SHA1PRNG ImplementedIn", "Software");
        }
    }
        //ECB模式加密
    //    public static String encryptECB(String input, byte[] key) {
//        byte[] crypted = (byte[]) null;
//        try {
//            SecretKeySpec skey = new SecretKeySpec(key, AES);
//            Cipher cipher = Cipher.getInstance(AES);
//            cipher.init(Cipher.ENCRYPT_MODE, skey);
//            crypted = cipher.doFinal(input.getBytes());
//        } catch (Exception e) {
//            return null;
//        }
//        return new String(Hex.getByteToHex(crypted));
//    }
    public static String encryptAES(String input, byte[] key) {
        byte[] crypted = (byte[]) null;
        try {
            SecretKeySpec skey = new SecretKeySpec(key, AES);
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.ENCRYPT_MODE, skey);
            crypted = cipher.doFinal(input.getBytes());
            return Base64Utils.encode(crypted);
        } catch (Exception e) {
            return null;
        }

    }
          //ECB模式解密
    //    public static String decryptECB(String hexStr, byte[] key) {
//        byte[] output = (byte[]) null;
//        try {
//            byte[] decByte = Hex.getKey(hexStr);
//            SecretKeySpec skey = new SecretKeySpec(key, AES);
//            Cipher cipher = Cipher.getInstance(AES);
//            cipher.init(Cipher.DECRYPT_MODE, skey);
//            output = cipher.doFinal(decByte);
//        } catch (Exception e) {
//            return null;
//        }
//        return new String(output);
//    }
    public static String decryptAES(byte[] hexStr, byte[] key) {
        byte[] output = (byte[]) null;
        try {
            SecretKeySpec skey = new SecretKeySpec(key, AES);
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.DECRYPT_MODE, skey);
            output = cipher.doFinal(hexStr);
        } catch (Exception e) {
            return null;
        }
        return new String(output);
    }

    //获取16位密钥
    public static byte[] getAesKey() {
        // 每次请求生成一个AES对称加密密钥
        //实例化
        KeyGenerator kgen = null;
        try {
            kgen = KeyGenerator.getInstance("AES");
            //设置密钥长度
            kgen.init(128);
            //生成密钥
            SecretKey skey = kgen.generateKey();
            //返回密钥的二进制编码
//            String baseSecretKey = Base64.encodeToString(skey.getEncoded(),Base64.DEFAULT);
//            Logger.d("baseSecretKey2="+baseSecretKey);
//            Logger.d("skey.getEncoded()="+skey.getEncoded().length);
            return skey.getEncoded();//16位
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
