package cn.pzhu.javaweb.filesys.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Conver2MD5 {

	/**
	 * 这个方法是获得某一个字符串的MD5信息摘要
	 * @param str 传入的字符串
	 * @return 返回str这个字符串的MD5
	 * @author LanQuanxiang
	 * @date 2018年3月3日10:24:04
	 */
	public static String getMD5(String str) {
        String reStr = null;
        try {
        	MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte ss[] = md.digest();
            reStr = bytes2String(ss);
        } catch (NoSuchAlgorithmException e) {
        	e.printStackTrace();
        }
        return reStr;
    }
	
	public static String getSHA(String str) {
        String reStr = null;
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            sha.update(str.getBytes());
            byte ss[] = sha.digest();
            reStr = bytes2String(ss);
        } catch (NoSuchAlgorithmException e) {

        }
        return reStr;

    }
    private static String bytes2String(byte[] aa) {
        String hash = "";
        for (int i = 0; i < aa.length; i++) {
            int temp;
            System.out.print(aa[i] + "  ");
            if (aa[i] < 0)		
                temp = 256 + aa[i];
            else
                temp = aa[i];
            if (temp < 16)
                hash += "0";
            System.out.print(temp + "  ");            
            hash += Integer.toString(temp, 16);
        }
        hash = hash.toUpperCase();
        return hash;
    }
}
