package org.nextprot.commons.algo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Algo {

	public static String computeMD5(String payload) {

		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(payload.getBytes());
			byte byteData[] = md.digest();

			// convert the byte to hex format method 1
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}

			// convert the byte to hex format method 2
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				String hex = Integer.toHexString(0xff & byteData[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}

			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {

			throw new RuntimeException("Not possible to compute MD5");
		}

	}

}
