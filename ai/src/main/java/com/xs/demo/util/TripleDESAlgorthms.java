package com.xs.demo.util;

import java.security.Security;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.sun.crypto.provider.SunJCE;

/**
 * 3DES算法
 * @author 小帅丶<br/>
 * @类名称  TripleDESAlgorthms
 * @remark TripleDES算法工具类
 * @date  2017-6-28
 */
public class TripleDESAlgorthms {
	/**
	 * TripleDES JAVA 名称
	 */
	public static final String TRIPLEDES_ALGORITHM = "DESede";
	
	
	/**
	 * 生成随机数字+字母字符串
	 * @param length  随机串长度
	 * @return length位带数字字母的字符串
	 */
    public static String genRandomNum(int length){  
        int  maxNum = 62;  
        int i;  
        int count = 0;  
        char[] str = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',  
          'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',  
          'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',  
          'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',  
          'x', 'y', 'z','0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };      
        StringBuffer pwd = new StringBuffer("");  
        Random r = new Random();  
        while(count < length){  
         i = Math.abs(r.nextInt(maxNum));     
         if (i >= 0 && i < str.length) {  
          pwd.append(str[i]);  
          count ++;  
         }  
        }  
        return pwd.toString();  
      } 
    /**
	 * 加密数据
	 * @param keybyte
	 * @param str
	 * @return
	 */
	public static byte[] encryptMode(byte[] keybyte,byte[] str) {
		try {
			Security.addProvider(new SunJCE());
			//生成密钥
			SecretKey key = new SecretKeySpec(keybyte, TRIPLEDES_ALGORITHM);
			Cipher cipher = Cipher.getInstance(TRIPLEDES_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			return cipher.doFinal(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 解密数据
	 * @param keybyte
	 * @param str
	 * @return
	 */
	public static byte[] decryptMode(byte[] keybyte,byte[] str) {
		try {
			Security.addProvider(new SunJCE());
			//生成密钥
			SecretKey key = new SecretKeySpec(keybyte, TRIPLEDES_ALGORITHM);
			Cipher cipher = Cipher.getInstance(TRIPLEDES_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, key);
			return cipher.doFinal(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
