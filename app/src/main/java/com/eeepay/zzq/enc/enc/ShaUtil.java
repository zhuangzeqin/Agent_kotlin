package com.eeepay.zzq.enc.enc;

import java.security.MessageDigest;

/**
 *@author  :xqf
 *@date    :2019/5/23 13:58
 *@desc    :
 *@update  :
 */
public class ShaUtil {

    /**
     * SHA256算法
     * @param str
     * @return
     */
    public static String SHA256(String str) {
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodeStr = byte2Hex(messageDigest.digest());
        }catch (Exception e){
            e.printStackTrace();
        }
            return encodeStr;
    }



    private static String byte2Hex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        String temp;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                builder.append("0");
            }
            builder.append(temp);
        }
        return builder.toString();
    }

}
