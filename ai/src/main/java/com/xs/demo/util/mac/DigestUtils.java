package com.xs.demo.util.mac;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtils {
	/**
	  * 根据给定摘要算法创建一个消息摘要实例
	  * 
	  * @param algorithm
	  *            摘要算法名
	  * @return 消息摘要实例
	  * @see MessageDigest#getInstance(String)
	  * @throws RuntimeException
	  *             当 {@link NoSuchAlgorithmException} 发生时
	  */
	 static MessageDigest getDigest(String algorithm) {
	  try {
	   return MessageDigest.getInstance(algorithm);
	  } catch (NoSuchAlgorithmException e) {
	   throw new RuntimeException(e.getMessage());
	  }
	 }

	 /**
	  * 获取 MD5 消息摘要实例
	  * 
	  * @return MD5 消息摘要实例
	  * @throws RuntimeException
	  *             当 {@link NoSuchAlgorithmException} 发生时
	  */
	 private static MessageDigest getMd5Digest() {
	  return getDigest("MD5");
	 }

	 /**
	  * 获取 SHA-1 消息摘要实例
	  * 
	  * @return SHA-1 消息摘要实例
	  * @throws RuntimeException
	  *             当 {@link NoSuchAlgorithmException} 发生时
	  */
	 private static MessageDigest getShaDigest() {
	  return getDigest("SHA");
	 }

	 /**
	  * 获取 SHA-256 消息摘要实例
	  * 
	  * @return SHA-256 消息摘要实例
	  * @throws RuntimeException
	  *             当 {@link NoSuchAlgorithmException} 发生时
	  */
	 private static MessageDigest getSha256Digest() {
	  return getDigest("SHA-256");
	 }

	 /**
	  * 获取 SHA-384 消息摘要实例
	  * 
	  * @return SHA-384 消息摘要实例
	  * @throws RuntimeException
	  *             当 {@link NoSuchAlgorithmException} 发生时
	  */
	 private static MessageDigest getSha384Digest() {
	  return getDigest("SHA-384");
	 }

	 /**
	  * 获取 SHA-512 消息摘要实例
	  * 
	  * @return SHA-512 消息摘要实例
	  * @throws RuntimeException
	  *             当 {@link NoSuchAlgorithmException} 发生时
	  */
	 private static MessageDigest getSha512Digest() {
	  return getDigest("SHA-512");
	 }

	 /**
	  * 使用MD5消息摘要算法计算消息摘要
	  * 
	  * @param data
	  *            做消息摘要的数据
	  * @return 消息摘要（长度为16的字节数组）
	  */
	 public static byte[] encodeMD5(byte[] data) {
	  return getMd5Digest().digest(data);
	 }

	 /**
	  * 使用MD5消息摘要算法计算消息摘要
	  * 
	  * @param data
	  *            做消息摘要的数据
	  * @return 消息摘要（长度为32的十六进制字符串）
	  */
	 public static String encodeMD5Hex(byte[] data) {
	  return Hex.encodeHexStr(encodeMD5(data));
	 }

	 /**
	  * 使用SHA-1消息摘要算法计算消息摘要
	  * 
	  * @param data
	  *            做消息摘要的数据
	  * @return SHA-1消息摘要（长度为20的字节数组）
	  */
	 public static byte[] encodeSHA(byte[] data) {
	  return getShaDigest().digest(data);
	 }

	 /**
	  * 使用SHA-1消息摘要算法计算消息摘要
	  * 
	  * @param data
	  *            做消息摘要的数据
	  * @return SHA-1消息摘要（长度为40的十六进制字符串）
	  */
	 public static String encodeSHAHex(byte[] data) {
	  return Hex.encodeHexStr(getShaDigest().digest(data));
	 }

	 /**
	  * 使用SHA-256消息摘要算法计算消息摘要
	  * 
	  * @param data
	  *            做消息摘要的数据
	  * @return SHA-256消息摘要（长度为32的字节数组）
	  */
	 public static byte[] encodeSHA256(byte[] data) {
	  return getSha256Digest().digest(data);
	 }

	 /**
	  * 使用SHA-256消息摘要算法计算消息摘要
	  * 
	  * @param data
	  *            做消息摘要的数据
	  * @return SHA-256消息摘要（长度为64的十六进制字符串）
	  */
	 public static String encodeSHA256Hex(byte[] data) {
	  return Hex.encodeHexStr(encodeSHA256(data));
	 }

	 /**
	  * 使用SHA-384消息摘要算法计算消息摘要
	  * 
	  * @param data
	  *            做消息摘要的数据
	  * @return SHA-384消息摘要（长度为43的字节数组）
	  */
	 public static byte[] encodeSHA384(byte[] data) {
	  return getSha384Digest().digest(data);
	 }

	 /**
	  * 使用SHA-384消息摘要算法计算消息摘要
	  * 
	  * @param data
	  *            做消息摘要的数据
	  * @return SHA-384消息摘要（长度为86的十六进制字符串）
	  */
	 public static String encodeSHA384Hex(byte[] data) {
	  return Hex.encodeHexStr(encodeSHA384(data));
	 }

	 /**
	  * 使用SHA-512消息摘要算法计算消息摘要
	  * 
	  * @param data
	  *            做消息摘要的数据
	  * @return SHA-512消息摘要（长度为64的字节数组）
	  */
	 public static byte[] encodeSHA512(byte[] data) {
	  return getSha512Digest().digest(data);
	 }

	 /**
	  * 使用SHA-512消息摘要算法计算消息摘要
	  * 
	  * @param data
	  *            做消息摘要的数据
	  * @return SHA-512消息摘要（长度为128的十六进制字符串）
	  */
	 public static String encodeSHA512Hex(byte[] data) {
	  return Hex.encodeHexStr(encodeSHA512(data));
	 }

	}
