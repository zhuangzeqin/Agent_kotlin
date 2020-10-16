package com.eeepay.zzq.enc.enc;

/**
 * <p>
 * Title:
 * </p>
 *
 * <p>
 * Description: openEAP WEB Core
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2005
 * </p>
 *
 * <p>
 * Company: 新太科技
 * </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class Hex {

	public static String getByteToHex(byte[] buffer) {
		String dump = "";
		try {
			int dataLen = buffer.length;
			for (int i = 0; i < dataLen; i++) {
				dump += Character.forDigit((buffer[i] >> 4) & 0x0f, 16);
				dump += Character.forDigit(buffer[i] & 0x0f, 16);
			}
		} catch (Throwable t) {
		}
		return dump;
	}

	public static byte[] getKey(String keystr) {
		int l = keystr.length() / 2;
		byte[] w = new byte[l];
		for (int i = 0; i < l; i++) {
			w[i] = (byte) (Integer.parseInt(keystr.substring(i * 2, i * 2 + 2), 16) & 0xff);
		}
		return (w);
	}
}
