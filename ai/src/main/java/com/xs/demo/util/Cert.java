package com.xs.demo.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Calendar;
import java.util.Date;

import javax.security.auth.x500.X500Principal;

import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.SubjectKeyIdentifier;
import org.bouncycastle.asn1.x509.X509Extensions;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.x509.X509V3CertificateGenerator;
import org.bouncycastle.x509.extension.AuthorityKeyIdentifierStructure;
import org.bouncycastle.x509.extension.SubjectKeyIdentifierStructure;

import com.xiaoleilu.hutool.lang.Base64;
/**
 * 使用X509 V3版本生成数字证书
 * @author 小帅丶
 * @date 2017年6月21日   
 */
public class Cert {
	/**
	 * 根证书路径
	 */
	private static final String CA_PATH="G:/cav3/root/root.cer";
	/**
	 * 私钥keystore路径
	 */
	private static final String CA_PRIKEY_PATH="G:/cav3/root/privateKey.keystore";
	/**
	 * 默认签名算法
	 */
	private static final String SIGNATURE_ALGORITHM="SHA1withRSA";
	/**
	 * 申请数字证书
	 * @param subjectDN 证书使用者等相关信息
	 * @param publicKey 需要签名的公钥 base64编码
	 * @return cert的base64编码
	 * @throws Exception
	 */
	public static String AppCertReq(String subjectDN,String publicKey){
		//接收证书的字符串
		StringBuffer certStr = new StringBuffer();
		try {
		//生成X509 V3版本证书的类
		X509V3CertificateGenerator certGen = null;
		//接收证书的类
		X509Certificate subCert = null;
		//ROOT证书
		X509Certificate rootCA = rootCA(CA_PATH);
		//ROOT的私钥
		PrivateKey privateKey = getpriKey(CA_PRIKEY_PATH); 
		//默认使用BC做机构
		Security.addProvider(new BouncyCastleProvider());
		//证书的序列号
		BigInteger serialNumber = BigInteger.valueOf(System.currentTimeMillis());
		//颁发者信息通过CA证书获取
		X500Principal issuerDn = rootCA.getIssuerX500Principal();
		//使用者信息
		X500Principal subjectDn = new X500Principal(subjectDN);
		//生效日期 默认立即生效
		Date notBefore = new Date();
		//截止日期 默认5年
		Date notAfter  = getYearLater(5);
		//需要签名的公钥
		PublicKey pubKey = loadPublicKeyByStr(publicKey);
		certGen = new X509V3CertificateGenerator();
		certGen.setPublicKey(pubKey);
		certGen.setSerialNumber(serialNumber);
		certGen.setIssuerDN(issuerDn);
		certGen.setNotBefore(notBefore);
		certGen.setNotAfter(notAfter);
		certGen.setSubjectDN(subjectDn);
		certGen.setSignatureAlgorithm(SIGNATURE_ALGORITHM);
		//添加主体密钥(公钥)
		certGen.addExtension(X509Extensions.SubjectKeyIdentifier, false,
					new SubjectKeyIdentifierStructure(pubKey));
		//因为版本的原因。如果不使用JDK16ON 则使用下面的扩展信息方法
//		certGen.addExtension(Extension.subjectKeyIdentifier, false,
//				new SubjectKeyIdentifier(pubKey.getEncoded()));
//	   //添加颁发者密钥(公钥)
		certGen.addExtension(X509Extensions.AuthorityKeyIdentifier, false,
				new AuthorityKeyIdentifierStructure(rootCA.getPublicKey()));
		//因为版本的原因。如果不使用JDK16ON 则使用下面的扩展信息方法
//		certGen.addExtension(Extension.authorityKeyIdentifier, false,
//				new AuthorityKeyIdentifierStructure(rootCA.getPublicKey()));
		//生成X509证书
		subCert = certGen.generate(privateKey,"BC");
		//拼接成BASE64编码
		certStr.append("-----BEGIN CERTIFICATE-----\n");
		certStr.append(Base64.encode(subCert.getEncoded()));
		certStr.append("\n-----END CERTIFICATE-----\n");
		//保存证书
		saveCert(subCert);
//		catch (CertificateParsingException e) {
//			logger.info("证书解析异常"+e.getMessage());
//		} catch (InvalidKeyException e) {
//			logger.info("无效的Key 请检查"+e.getMessage());
//		} catch (CertificateEncodingException e) {
//			logger.info("证书编码异常"+e.getMessage());
//		} catch (IllegalStateException e) {
//			logger.info("非正常状态"+e.getMessage());
//		} catch (NoSuchProviderException e) {
//			logger.info(" 供应商不存在 "+e.getMessage());
//		} catch (NoSuchAlgorithmException e) {
//			logger.info("算法不存在"+e.getMessage());
//		}catch (SignatureException e) {
//			logger.info("签名错误"+e.getMessage());
//		} catch (FileNotFoundException e) {
//			logger.info("文件找不到"+e.getMessage());
//		}
		}  catch (Exception e) {
			certStr.setLength(0);
			certStr.append("系统错误,请稍后再试");
		} 
		return certStr.toString();
	}
	/**
	 * 数字验签
	 * @param data 待签名的数据
	 * @param strSignData 签名数据和用户证书 格式 sign:base64(cert) 英文冒号
	 * @return boolean
	 */
	public static boolean ESverify(String data,String strSignData){
		boolean bverfiy = false;
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			String signData[] = strSignData.split(":");
			String sign = signData[0];
			String cert = signData[1];
			String certx509 = "-----BEGIN CERTIFICATE-----\n"+cert+"\n-----END CERTIFICATE-----\n";
			CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(certx509.getBytes());
			X509Certificate certificate = (X509Certificate) certificateFactory.generateCertificate(byteArrayInputStream);
			PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(certificate.getPublicKey().getEncoded()));
			Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
			signature.initVerify(pubKey);
			signature.update(data.getBytes());
			bverfiy = signature.verify(Base64.decode(sign));
			return bverfiy;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bverfiy;
	} 
	/**
	 * 数据加密 <br/>采用数字信封方式<br/>
	 * @param strInCert 指定加密的证书base64编码<br/>
	 * @param strInData 待加密的数据 <br/>
	 * @return String
	 * @throws Exception 
	 */
	public static String ESEnvelopeEnc(String strInCert,String strInData) throws Exception{
		try {
			//转换为x509证书对象。读取公钥
			String certx509 = "-----BEGIN CERTIFICATE-----\n"+strInCert+"\n-----END CERTIFICATE-----\n";
			CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(certx509.getBytes());
			X509Certificate certificate = (X509Certificate) certificateFactory.generateCertificate(byteArrayInputStream);
			RSAPublicKey rsaPublicKey = (RSAPublicKey) certificate.getPublicKey();
			//3des密钥
			String des3pwd = DES3Util.genRandomNum(24);
			//先使用3des进行数据加密
			byte [] encstr = DES3Util.encrypt3DES(des3pwd.getBytes(), strInData.getBytes());
			//使用公钥对3des加密后的数据加密
			byte [] certenc = CertUtil.encrypt(rsaPublicKey, encstr);
			//拼接格式
			String result = Base64.encode(certenc)+":"+Base64.encode(des3pwd.getBytes());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	/**
	 * 保存申请的证书到指定盘符
	 * @param cert
	 * @throws Exception
	 */
	public static void saveCert(X509Certificate cert) throws Exception{
		String address = "G:/CERT/"+new Date().getTime()/1000+".cer";
		FileOutputStream outputStream = new FileOutputStream(address);
		Writer writer = new OutputStreamWriter(outputStream,Charset.forName("UTF-8"));
		writer.write("-----BEGIN CERTIFICATE-----\n");
		writer.write(new sun.misc.BASE64Encoder().encode(cert.getEncoded()));
		writer.write("\n-----END CERTIFICATE-----\n");
		writer.flush();
	}
	/**
	 * 获取根证书相关信息
	 * @param rootPath
	 * @return cert
	 * @throws Exception
	 */
	public static X509Certificate rootCA(String rootPath) throws Exception{
		X509Certificate cert = null;
		FileInputStream fis = new FileInputStream(rootPath);
		BufferedInputStream bis = new BufferedInputStream(fis);
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		while (bis.available() > 0) {
			cert = (X509Certificate) cf.generateCertificate(fis);
		}
		return cert;
	}
	/**
	 * 计算当前时间的N年后
	 * @param later 正整数
	 * @return
	 */
	public static Date getYearLater(int later) {
		Date date = new Date();
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.YEAR,later);
			date = calendar.getTime();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return date;
	}
	/**
	 * 从字符串中读取公钥
	 * @param publicKeyStr 字符串
	 * @return RSAPublicKey ras公钥对象
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
	 * 根据路径获取公钥对象
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static PublicKey getpubKey(String path) throws Exception{
		StringBuffer str = new StringBuffer("");
		File file = new File(path);
		X509EncodedKeySpec keySpec = null;
		KeyFactory keyFactory = null;
		try {
			FileReader fileReader = new FileReader(file);
			int ch = 0;
			while ((ch=fileReader.read())!=-1) {
				str.append((char)ch);
			}
			fileReader.close();
			byte[] buffer = Base64.decode(str.toString());
			keyFactory = KeyFactory.getInstance("RSA");
			keySpec = new X509EncodedKeySpec(buffer);
			return keyFactory.generatePublic(keySpec);
		} catch (Exception e) {
			throw new Exception("公钥数据为空");
		}
	}
	/**
	 * 根据路径获取公钥字符串
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static String getpubKeystr(String path) throws Exception{
		StringBuffer str = new StringBuffer("");
		File file = new File(path);
		X509EncodedKeySpec keySpec = null;
		KeyFactory keyFactory = null;
		try {
			FileReader fileReader = new FileReader(file);
			int ch = 0;
			while ((ch=fileReader.read())!=-1) {
				str.append((char)ch);
			}
			fileReader.close();
		} catch (Exception e) {
			throw new Exception("公钥数据为空");
		}
		return str.toString();
	}
	/**
	 * 根据路径获取私钥对象
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static PrivateKey getpriKey(String path) throws Exception{
		StringBuffer str = new StringBuffer("");
		File file = new File(path);
		PKCS8EncodedKeySpec keySpec = null;
		KeyFactory keyFactory = null;
		try {
			FileReader fileReader = new FileReader(file);
			int ch = 0;
			while ((ch=fileReader.read())!=-1) {
				str.append((char)ch);
			}
			fileReader.close();
			byte[] buffer = Base64.decode(str.toString());
			keyFactory = KeyFactory.getInstance("RSA");
			keySpec = new PKCS8EncodedKeySpec(buffer);
			return keyFactory.generatePrivate(keySpec);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new Exception("私钥数据为空");
		}
	}
}
