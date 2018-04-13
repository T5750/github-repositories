package com.xs.demo.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.security.auth.x500.X500Principal;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.x509.X509V3CertificateGenerator;

import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.lang.Base64;

/**
 * 通过Java调用security 生成公私钥对。并保存在指定文件夹中<br>
 * 如果需要公私钥对和数字证书 则调用createCert方法(该方法会在创建公私钥对后，再生成cer数字证书)<br>
 * 如果只需要公私钥对调用getKeyPair该方法即可。默认的名称为(暂时不支持自定义)publicKey & privateKey 后缀名都是keystore<br>
 * @author 小帅丶
 * @类名称 KeyGen
 * @remark 生成密钥对
 * @date 2017-6-16
 */
public class CertUtil {
	/**
	 * 加密算法RSA
	 */
	public static final String KEY_ALGORITHM = "RSA";
	/**
	 * 签名算法 sha1withrsa
	 */
	public static final String SIGN_ALGORITHMS = "SHA1withRSA";

	/**
	 * 制作证书
	 * 
	 * @param pair
	 *            公私钥对
	 * @param bdate
	 *            生效日期
	 * @param edate
	 *            失效日期
	 * @param info
	 *            证书信息
	 * @return
	 * @throws Exception
	 */
	public X509Certificate generateV3Certificate(KeyPair pair, Date bdate,
			Date edate, X500Principal info) throws Exception {
		Security.addProvider(new BouncyCastleProvider());
		X509V3CertificateGenerator certGen = new X509V3CertificateGenerator();
		certGen.setSerialNumber(BigInteger.valueOf(System.currentTimeMillis()));
		certGen.setIssuerDN(info);
		certGen.setNotBefore(bdate);
		certGen.setNotAfter(edate);
		certGen.setSubjectDN(info);
		certGen.setPublicKey(pair.getPublic());
		certGen.setSignatureAlgorithm("SHA1withRSA");
		return certGen.generateX509Certificate(pair.getPrivate(), "BC");
	}

	/**
	 * 
	 * @param address
	 *            文件保存路径
	 * @param bdate
	 *            生效日期
	 * @param edate
	 *            失效日期
	 * @param info
	 *            证书信息
	 * @param algorithm
	 *            算法名称
	 * @param keysize
	 *            密钥长度
	 * @param random
	 *            随机源
	 * @throws Exception
	 */
	public void createCert(String address, Date bdate, Date edate,
			X500Principal info, String algorithm, int keysize,
			SecureRandom random) throws Exception {
		String certaddress = address + "/" + new Date().getTime() / 1000
				+ ".cer";
		KeyPair kp = getKeyPair(algorithm, keysize, address);
		X509Certificate cert = generateV3Certificate(kp, bdate, edate, info);
		cert.checkValidity(new Date());
		cert.verify(cert.getPublicKey());
		FileOutputStream outputStream = new FileOutputStream(certaddress);
		Writer writer = new OutputStreamWriter(outputStream,
				Charset.forName("UTF-8"));
		writer.write("-----BEGIN CERTIFICATE-----\n");
		writer.write(new sun.misc.BASE64Encoder().encode(cert.getEncoded()));
		writer.write("\n-----END CERTIFICATE-----\n");
		writer.flush();
		System.out.println("证书保存目录:" + certaddress);
	}

	/**
	 * 获取证书对象
	 * 
	 * @param address
	 *            证书文件路径
	 * @return X509Certificate
	 * @throws Exception
	 */
	public static X509Certificate GetCert(String address) throws Exception {
		X509Certificate cert = null;
		FileInputStream fis = new FileInputStream(address);
		BufferedInputStream bis = new BufferedInputStream(fis);
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		while (bis.available() > 0) {
			cert = (X509Certificate) cf.generateCertificate(fis);
		}
		return cert;
	}

	/**
	 * 
	 * @param algorithm
	 *            算法名称
	 * @param keysize
	 *            密钥长度
	 * @param random
	 *            随机源
	 * @return KeyPair
	 */
	@Deprecated
	public static KeyPair getKeyPair(String algorithm, int keysize,
			SecureRandom random) {
		KeyPairGenerator keyGen = null;
		KeyPair keyPair = null;
		try {
			if (algorithm != null && !algorithm.equals("")) {
				keyGen = KeyPairGenerator.getInstance(algorithm);
			} else {
				keyGen = KeyPairGenerator.getInstance("RSA");
			}
			if (keysize <= 0) {
				keyGen.initialize(keysize, random);
			} else {
				keyGen.initialize(1024, random);
			}
			keyPair = keyGen.generateKeyPair();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return keyPair;
	}

	/**
	 * 生成公私钥对并保存
	 * 
	 * @param algorithm
	 *            算法名称
	 * @param keysize
	 *            密钥长度
	 * @param address
	 *            存放盘符
	 * @return KeyPair
	 */
	public static KeyPair getKeyPair(String algorithm, int keysize,
			String address) {
		KeyPairGenerator keyGen = null;
		KeyPair keyPair = null;
		try {
			if (algorithm != null && !algorithm.equals("")) {
				keyGen = KeyPairGenerator.getInstance(algorithm);
			} else {
				keyGen = KeyPairGenerator.getInstance("RSA");
			}
			if (keysize <= 0) {
				keyGen.initialize(keysize);
			} else {
				keyGen.initialize(1024);
			}
			keyPair = keyGen.generateKeyPair();
			// 公钥
			PublicKey pubKey = keyPair.getPublic();
			// 私钥
			PrivateKey priKey = keyPair.getPrivate();
			String privateKeyString = Base64.encode(priKey.getEncoded());
			// 得到公钥字符串
			String publicKeyString = Base64.encode(pubKey.getEncoded());
			// 将密钥对写入到文件
			FileWriter pubfw = new FileWriter(address + "/publicKey.keystore");
			FileWriter prifw = new FileWriter(address + "/privateKey.keystore");
			BufferedWriter pubbw = new BufferedWriter(pubfw);
			BufferedWriter pribw = new BufferedWriter(prifw);
			pubbw.write(publicKeyString);
			pribw.write(privateKeyString);
			pubbw.flush();
			pubbw.close();
			pubfw.close();
			pribw.flush();
			pribw.close();
			prifw.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return keyPair;
	}

	/**
	 * 获取公钥
	 * 
	 * @param keyPair
	 *            密钥对
	 * @return
	 */
	public PublicKey getPublicKey(KeyPair keyPair) {
		return (PublicKey) keyPair.getPublic();
	}

	/**
	 * 获取私钥
	 * 
	 * @param keyPair
	 *            密钥对
	 * @return
	 */
	public PrivateKey getPrivateKey(KeyPair keyPair) {
		return (PrivateKey) keyPair.getPrivate();
	}

	/**
	 * 从文件中输入流加载公钥
	 * 
	 * @param path
	 *            公钥的地址
	 * @return String
	 * @throws Exception
	 */
	public static String loadPublicKeyByFile(String path) throws Exception {
		try {
			BufferedReader br = new BufferedReader(new FileReader(path
					+ "/publicKey.keystore"));
			String readLine = null;
			StringBuffer sb = new StringBuffer();
			while ((readLine = br.readLine()) != null) {
				sb.append(readLine);
			}
			br.close();
			return sb.toString();
		} catch (FileNotFoundException e) {
			throw new Exception("公钥文件未找到或不存在");
		} catch (IOException e) {
			throw new Exception("公钥输入流为空");
		}
	}

	/**
	 * 从字符串中读取公钥
	 * 
	 * @param publicKeyStr
	 * @return RSAPublicKey
	 * @throws Exception
	 */
	public static RSAPublicKey loadPublicKeyByStr(String publicKeyStr)
			throws Exception {
		byte[] buffer = Base64.decode(publicKeyStr);
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
			return (RSAPublicKey) keyFactory.generatePublic(keySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("算法不存在");
		} catch (InvalidKeySpecException e) {
			throw new Exception("公钥非法");
		}
	}

	/**
	 * 从文件中输入流加载私钥
	 * 
	 * @param path
	 *            私钥的地址
	 * @return String
	 * @throws Exception
	 */
	public static String loadPrivateKeyByFile(String path) throws Exception {
		try {
			BufferedReader br = new BufferedReader(new FileReader(path
					+ "/privateKey.keystore"));
			String readLine = null;
			StringBuffer sb = new StringBuffer();
			while ((readLine = br.readLine()) != null) {
				sb.append(readLine);
			}
			br.close();
			return sb.toString();
		} catch (FileNotFoundException e) {
			throw new Exception("私钥文件未找到或不存在");
		} catch (IOException e) {
			throw new Exception("私钥输入流为空");
		}
	}

	/**
	 * 从字符串中读取私钥
	 * 
	 * @param privateKeyStr
	 * @return RSAPrivateKey
	 * @throws Exception
	 */
	public static RSAPrivateKey loadPrivateKeyByStr(String privateKeyStr)
			throws Exception {
		byte[] buffer = Base64.decode(privateKeyStr);
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
			return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("算法不存在");
		} catch (InvalidKeySpecException e) {
			throw new Exception("私钥非法");
		}
	}

	/**
	 * 公钥加密过程
	 * 
	 * @param publicKey
	 *            公钥
	 * @param plainTextData
	 *            明文数据
	 * @return 明文
	 * @throws Exception
	 *             加密过程中的异常信息
	 */
	public static byte[] encrypt(RSAPublicKey publicKey, byte[] plainTextData)
			throws Exception {
		if (publicKey == null) {
			throw new Exception("加载公钥为空，请修改");
		}
		try {
			Cipher cipher = null;
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] output = cipher.doFinal(plainTextData);
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此加密算法");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("加密公钥非法,请检查");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("明文长度非法");
		} catch (BadPaddingException e) {
			throw new Exception("明文数据已损坏");
		}
	}

	/**
	 * 私钥加密过程
	 * 
	 * @param privateKey
	 *            私钥
	 * @param plainTextData
	 *            明文数据
	 * @return 明文
	 * @throws Exception
	 *             加密过程中的异常信息
	 */
	public static byte[] encrypt(RSAPrivateKey privateKey, byte[] plainTextData)
			throws Exception {
		if (privateKey == null) {
			throw new Exception("加密私钥为空, 请设置");
		}
		Cipher cipher = null;
		try {
			// 使用默认RSA
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);
			byte[] output = cipher.doFinal(plainTextData);
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此加密算法");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("加密私钥非法,请检查");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("明文长度非法");
		} catch (BadPaddingException e) {
			throw new Exception("明文数据已损坏");
		}
	}

	/**
	 * 私钥解密过程
	 * 
	 * @param privateKey
	 *            私钥
	 * @param cipherData
	 *            密文数据
	 * @return 明文
	 * @throws Exception
	 *             解密过程中的异常信息
	 */
	public static byte[] decrypt(RSAPrivateKey privateKey, byte[] cipherData)
			throws Exception {
		if (privateKey == null) {
			throw new Exception("解密私钥为空, 请设置");
		}
		Cipher cipher = null;
		try {
			// 使用默认RSA
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] output = cipher.doFinal(cipherData);
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此解密算法");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("解密私钥非法,请检查");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("密文长度非法");
		} catch (BadPaddingException e) {
			throw new Exception("密文数据已损坏");
		}
	}

	/**
	 * 公钥解密过程
	 * 
	 * @param publicKey
	 *            公钥
	 * @param cipherData
	 *            密文数据
	 * @return 明文
	 * @throws Exception
	 *             解密过程中的异常信息
	 */
	public static byte[] decrypt(RSAPublicKey publicKey, byte[] cipherData)
			throws Exception {
		if (publicKey == null) {
			throw new Exception("解密公钥为空, 请设置");
		}
		Cipher cipher = null;
		try {
			// 使用默认RSA
			cipher = Cipher.getInstance("RSA");
			// cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());
			cipher.init(Cipher.DECRYPT_MODE, publicKey);
			byte[] output = cipher.doFinal(cipherData);
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此解密算法");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("解密公钥非法,请检查");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("密文长度非法");
		} catch (BadPaddingException e) {
			throw new Exception("密文数据已损坏");
		}
	}

	/**
	 * <p>
	 * 用私钥对信息生成数字签名
	 * </p>
	 * 
	 * @param data
	 *            已加密数据
	 * @param privateKey
	 *            私钥(BASE64编码)
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String sign(byte[] data, String privateKey) throws Exception {
		byte[] keyBytes = Base64.decode(privateKey);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
		signature.initSign(privateK);
		signature.update(data);
		return Base64.encode(signature.sign());
	}

	/**
	 * RSA签名 默认编码
	 * 
	 * @param content
	 *            待签名数据
	 * @param privateKey
	 *            私钥
	 * @return
	 */
	public static String sign(String content, String privateKey) {
		try {
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(
					Base64.decode(privateKey));
			KeyFactory keyf = KeyFactory.getInstance("RSA");
			PrivateKey priKey = keyf.generatePrivate(priPKCS8);
			Signature signature = Signature
					.getInstance(SIGN_ALGORITHMS);
			signature.initSign(priKey);
			signature.update(content.getBytes());
			byte[] signed = signature.sign();
			return Base64.encode(signed);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <p>
	 * 获取私钥
	 * </p>
	 * 
	 * @param keyMap
	 *            密钥对
	 * @return
	 * @throws Exception
	 */
	public static String getPrivateKey(Map<String, Object> keyMap)
			throws Exception {
		Key key = (Key) keyMap.get("RSAPrivateKey");
		return Base64.encode(key.getEncoded());
	}

	/**
	 * <p>
	 * 校验数字签名
	 * </p>
	 * 
	 * @param data
	 *            已加密数据
	 * @param publicKey
	 *            公钥(BASE64编码)
	 * @param sign
	 *            数字签名
	 * @return
	 * @throws Exception
	 * 
	 */
	public static boolean verify(byte[] data, String publicKey, String sign)
			throws Exception {
		byte[] keyBytes = Base64.decode(publicKey);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PublicKey publicK = keyFactory.generatePublic(keySpec);
		Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
		signature.initVerify(publicK);
		signature.update(data);
		return signature.verify(Base64.decode(sign));
	}

	/**
	 * <p>
	 * 校验数字签名
	 * </p>
	 * 
	 * @param data
	 *            已加密数据
	 * @param publicKey
	 *            公钥(BASE64编码)
	 * @param sign
	 *            数字签名
	 * @return 验证的结果 true false
	 * @throws Exception
	 * 
	 */
	@Deprecated
	public static boolean verify(byte[] data, PublicKey publicKey, String sign)
			throws Exception {
		Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
		signature.initVerify(publicKey);
		signature.update(data);
		return signature.verify(Base64.decode(sign));
	}

	/**
	 * 检查证书是否过期
	 * 
	 * @param dateStr
	 *            时间 格式为:yyyy-MM-dd HH:mm:ss
	 * @param certificate
	 *            证书对象
	 * @return
	 */
	public static boolean verifyCertificate(String dateStr,
			Certificate certificate) {
		boolean isValid = true;
		try {
			X509Certificate x509Certificate = (X509Certificate) certificate;
			x509Certificate.checkValidity(DateUtil.parse(dateStr,
					"yyyy-MM-dd HH:mm:ss"));
		} catch (Exception e) {
			isValid = false;
			e.printStackTrace();
		}
		return isValid;
	}

}
