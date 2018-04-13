package com.xs.demo.util;

import java.security.Security;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.sun.crypto.provider.SunJCE;
/**
 * 3DES算法工具类
 * @author Administrator
 *
 */
public class DES3Util {
	/**
	 * 3DES算法
	 */
	public static final String Algorithm = "DESede";
	/**
	 * 默认数据加密算法
	 */
	private static final String DATAENCRY_ALGORITHM="DESede";
	/**
	 * 生成随机数字+字母字符串<br/>
	 * 3des算法密钥要求是24位 所以length传24<br/>
	 * des 算法密钥要求是8位   所以length传8<br/>
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
	 * 3DES加密数据
	 * @param keybyte 密钥
	 * @param str 待加密数据
	 * @return
	 */
	public static byte[] encrypt3DES(byte[] keybyte,byte[] str) {
		try {
			Security.addProvider(new SunJCE());
			//生成密钥
			SecretKey key = new SecretKeySpec(keybyte, DATAENCRY_ALGORITHM);
			Cipher cipher = Cipher.getInstance(DATAENCRY_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			return cipher.doFinal(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 3DES解密数据
	 * @param keybyte 密钥
	 * @param str 待解密数据
	 * @return
	 */
	public static byte[] decrypt3DES(byte[] keybyte,byte[] str) {
		try {
			Security.addProvider(new SunJCE());
			//生成密钥
			SecretKey key = new SecretKeySpec(keybyte, DATAENCRY_ALGORITHM);
			Cipher cipher = Cipher.getInstance(DATAENCRY_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, key);
			return cipher.doFinal(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
