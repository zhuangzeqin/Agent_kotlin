package com.eeepay.zzq.utils;


import android.util.Base64;

public class EncRSA {
	public static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQD1mecWBLMB1snW" +
			"3J089PGK/yICyWRzXnheUuIHD756S9g9XT0QqeR2l8k8L946VnTWLm3QmtpkS32c2ejfarvVnzkuJ" +
			"rYZyGZivN2hswz+PRxwresR8n/8NQOJ9hu9XVURL24owRKICQg5pD3lqRVL0MFxW+BJB/BZn+uSUFQMIw" +
			"IDAQAB";
	public static String EncPass(String source) throws Exception
	{

//		LogUtils.d("公钥加密——私钥解密");
//		LogUtils.d("\r加密前文字：\r\n" + source);
//		// publicKey = Session.getSession().getEnv().get("loginPubkeys");
//		LogUtils.d("\r加密公钥：\r\n" + MyApplication.publicKey);
		byte[] data = source.getBytes();
		byte[] encodedData = RSAUtils.encryptByPublicKey(data, publicKey);

//		LogUtils.d("加密后文字：\r\n" + RSAUtils.hexString(encodedData));
		// byte[] decodedData = RSAUtils.decryptByPrivateKey(encodedData,
		// privateKey);
		// String target = new String(decodedData);
		// LogUtils.d("解密后文字: \r\n" + target);
		return RSAUtils.hexString(encodedData);
	}
	public static String EncBase64Pass(String source) throws Exception
	{

//		LogUtils.d("公钥加密——私钥解密");
//		LogUtils.d("\r加密前文字：\r\n" + source);
//		// publicKey = Session.getSession().getEnv().get("loginPubkeys");
//		LogUtils.d("\r加密公钥：\r\n" + MyApplication.publicKey);
		byte[] data = source.getBytes();
		byte[] encodedData = RSAUtils.encryptByPublicKey(data, publicKey);

//		LogUtils.d("加密后文字：\r\n" + RSAUtils.hexString(encodedData));
		// byte[] decodedData = RSAUtils.decryptByPrivateKey(encodedData,
		// privateKey);
		// String target = new String(decodedData);
		// LogUtils.d("解密后文字: \r\n" + target);
        String base64 = android.util.Base64.encodeToString(encodedData, Base64.DEFAULT);
		return base64;
	}

	public static String EncPassByte(byte[] source) throws Exception
	{
//		LogUtils.d("公钥加密——私钥解密");
//		LogUtils.d("\r加密前文字：\r\n" + source);
//		// publicKey = Session.getSession().getEnv().get("loginPubkeys");
//		LogUtils.d("\r加密公钥：\r\n" + MyApplication.publicKey);
		byte[] encodedData = RSAUtils.encryptByPublicKey(source, publicKey);

//		LogUtils.d("加密后文字：\r\n" + RSAUtils.hexString(encodedData));
		// byte[] decodedData = RSAUtils.decryptByPrivateKey(encodedData,
		// privateKey);
		// String target = new String(decodedData);
		// LogUtils.d("解密后文字: \r\n" + target);
		return RSAUtils.hexString(encodedData);
	}

	/**
	 * 
	 * @param source
	 *            加密字符
	 * @param pubkey
	 *            加密公钥
	 * @return
	 * @throws Exception
	 */
	public static String EncPass(String source, String pubkey) throws Exception

	{
//		LogUtils.d("公钥加密——私钥解密");
//		LogUtils.d("\r加密前文字：\r\n" + source);
//		LogUtils.d("\r加密公钥：\r\n" + pubkey);
		byte[] data = source.getBytes();
		byte[] encodedData = RSAUtils.encryptByPublicKey(data, pubkey);

//		LogUtils.d("加密后文字：\r\n" + RSAUtils.hexString(encodedData));
		// byte[] decodedData = RSAUtils.decryptByPrivateKey(encodedData,
		// privateKey);
		// String target = new String(decodedData);
		// LogUtils.d("解密后文字: \r\n" + target);
		return RSAUtils.hexString(encodedData);
	}

}
