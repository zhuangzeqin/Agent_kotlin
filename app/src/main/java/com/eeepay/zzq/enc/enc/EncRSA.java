package com.eeepay.zzq.enc.enc;


import android.util.Base64;



public class EncRSA {
    //公钥: MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCJ9s1qlOyv9qpuaTqauW6fUftzE50rVk3yVPZwv1aO1Ch/XSEz76xCwkyvqpaqceRXrPpdBmO5+ruJ+I8osOHo7L5GWEOcMOO+8izp9hXKBBrmRMD4Egpn00k9DhVIEKp/vyddZPS/doxB8onhN6poTJDLdFLFVEicMf52caN9GQIDAQAB
//    public static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCJ9s1qlOyv9qpuaTqauW6fUftzE50rVk3yVPZwv1aO1Ch/XSEz76xCwkyvqpaqceRXrPpdBmO5+ruJ+I8osOHo7L5GWEOcMOO+8izp9hXKBBrmRMD4Egpn00k9DhVIEKp/vyddZPS/doxB8onhN6poTJDLdFLFVEicMf52caN9GQIDAQAB";
    public static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCp5vsHxCqnsEZ4Fm3bqf3RuKQwcl55WQjGSuo3JojhpFKZFzWryvaGqpGCsQlLbSOILilyTrUann3owVpjeogfl3+B0gte4T8+E0aTgcKaobIbx41t7k/lMR5eYcXbM+NM5oVqhrvjVT/NJliiL1YHC1m02td7RAhs9nI8M/jf/QIDAQAB";
    public static String EncPass(String source) throws Exception {

//        Logger.d("公钥加密——私钥解密");
//        Logger.d("\r加密前文字：\r\n" + source);
        // publicKey = Session.getSession().getEnv().get("loginPubkeys");
//        Logger.d("\r加密公钥：\r\n" + publicKey);
        byte[] data = source.getBytes();
        byte[] encodedData = RSAUtils.encryptByPublicKey(data, publicKey);

//		LogUtils.d("加密后文字：\r\n" + Base64Utils.encode(encodedData));
//		Logger.d("加密后文字：\r\n" + RSAUtils.hexString(encodedData));
//        Logger.d("加密后文字：\r\n" + Base64Utils.encode(encodedData));
        // byte[] decodedData = RSAUtils.decryptByPrivateKey(encodedData,
        // privateKey);
        // String target = new String(decodedData);
        // LogUtils.d("解密后文字: \r\n" + target);
//		return RSAUtils.hexString(encodedData);
        return Base64Utils.encode(encodedData);

//		return Base64Utils.encode(encodedData);
    }

    public static String EncPassByte(byte[] source) throws Exception {
//        Logger.d("公钥加密——私钥解密");
//        Logger.d("\r加密前文字：\r\n" + source);
//        // publicKey = Session.getSession().getEnv().get("loginPubkeys");
//        Logger.d("\r加密公钥：\r\n" + publicKey);
        byte[] encodedData = RSAUtils.encryptByPublicKey(source, publicKey);

//        Logger.d("加密后文字：\r\n" + RSAUtils.hexString(encodedData));
        // byte[] decodedData = RSAUtils.decryptByPrivateKey(encodedData,
        // privateKey);
        // String target = new String(decodedData);
        // LogUtils.d("解密后文字: \r\n" + target);
        return RSAUtils.hexString(encodedData);
    }

    /**
     * @param source 加密字符
     * @param pubkey 加密公钥
     * @return
     * @throws Exception
     */
    public static String EncPass(String source, String pubkey) throws Exception {
//        Logger.d("公钥加密——私钥解密");
//        Logger.d("\r加密前文字：\r\n" + source);
//        Logger.d("\r加密公钥：\r\n" + pubkey);
        byte[] data = source.getBytes();
        byte[] encodedData = RSAUtils.encryptByPublicKey(data, pubkey);

//        Logger.d("加密后文字：\r\n" + RSAUtils.hexString(encodedData));
        // byte[] decodedData = RSAUtils.decryptByPrivateKey(encodedData,
        // privateKey);
        // String target = new String(decodedData);
        // LogUtils.d("解密后文字: \r\n" + target);
        return RSAUtils.hexString(encodedData);
    }

    public static String EncBase64Pass(String source) throws Exception {
//        LogUtils.d("公钥加密——私钥解密");
//        LogUtils.d("\r加密前文字：\r\n" + source);
//        // publicKey = Session.getSession().getEnv().get("loginPubkeys");
//        LogUtils.d("\r加密公钥：\r\n" + publicKey);
        byte[] data = source.getBytes();
        byte[] encodedData = RSAUtils.encryptByPublicKey(data, publicKey);

//        LogUtils.d("加密后文字：\r\n" + RSAUtils.hexString(encodedData));
        // byte[] decodedData = RSAUtils.decryptByPrivateKey(encodedData,
        // privateKey);
        // String target = new String(decodedData);
        // LogUtils.d("解密后文字: \r\n" + target);
        String base64 = android.util.Base64.encodeToString(encodedData, Base64.DEFAULT);
        return base64;
    }

}
