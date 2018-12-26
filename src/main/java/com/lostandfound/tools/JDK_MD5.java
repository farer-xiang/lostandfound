package com.lostandfound.tools;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Hex;

public class JDK_MD5 {
	public static String jdkMD5(String str){
		try {
			MessageDigest mDigest=MessageDigest.getInstance("MD5");
			byte[] md5Bytes = mDigest.digest(str.getBytes());
			String string = Hex.encodeHexString(md5Bytes);
			return string;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
}
