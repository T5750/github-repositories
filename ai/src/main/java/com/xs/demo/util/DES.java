package com.xs.demo.util;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
/**
 * DES对称算法加解密方法类
 * @author 小帅丶
 * @类名称  DES
 * @remark DES 密钥长度必须是8
 * @date  2017-6-21
 */
public class DES {
	/**
	 * DES算法加密
	 * @param datasource 需要加密的数据
	 * @param password des密钥
	 * @return byte
	 * @author 小帅丶
	 */
	public static byte[] encrypt(byte[] datasource,String password) {
		try {
			//DES算法要求有一个可信任的随机源
			SecureRandom random = new SecureRandom();
			//创建一个DesKeySpec对象
			DESKeySpec desKeySpec = new  DESKeySpec(password.getBytes());
			//创建一个密钥工厂
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			//将DesKeySpec对象转换成SecrectKey对象
			SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
			// Cipher对象实例化DES算法进行解密操作
			Cipher cipher = Cipher.getInstance("DES");
		    //用密钥初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, secretKey,random);
			//开始加密返回byte字节数据
			return cipher.doFinal(datasource);
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}
	/**
	 * DES算法解密
	 * @param str 需要解密的数据 
	 * @param password des密钥
	 * @return byte
	 * @author 小帅丶
	 */
	public static byte[] decrypt(byte [] str,String password){
		try {
			//DES算法要求有一个可信任的随机源
			SecureRandom random = new SecureRandom();
			//创建一个DesKeySpec对象
			DESKeySpec desKeySpec = new  DESKeySpec(password.getBytes());
			//创建一个密钥工厂
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			//将DesKeySpec对象转换成SecrectKey对象
			SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
			// Cipher对象实例化DES算法进行解密操作
			Cipher cipher = Cipher.getInstance("DES");
			//用密钥初始化Cipher对象
			cipher.init(Cipher.DECRYPT_MODE, secretKey,random);
			//开始解密返回byte字节数据
			return cipher.doFinal(str);
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}
}
