package com.xs.demo.util;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.X509CRL;
import java.util.Date;

import javax.security.auth.x500.X500Principal;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.x509.X509V2CRLGenerator;

import com.xiaoleilu.hutool.lang.Base64;
/**
 * X509V2CRL证书吊销类
 * @author 小帅丶
 * @类名称  CRL
 * @remark 
 * @date  2017-6-22
 * test
 */
public class CRL {
	/**
	 * 组装X509V2CRL证书吊销对象
	 * @param bdate 生效日期
	 * @param edate 下一次更新日期
	 * @param info 主题信息
	 * @param priKey CA证书私钥
	 * @return X509CRL
	 * @throws Exception
	 */
	private static X509CRL generateV2CRLCertificate(Date bdate, Date edate,
			X500Principal info, PrivateKey priKey) throws Exception{
		Security.addProvider(new BouncyCastleProvider());
		X509V2CRLGenerator certGen = new X509V2CRLGenerator();
		certGen.setIssuerDN(info);
		certGen.setThisUpdate(bdate);
		certGen.setNextUpdate(edate);
		certGen.setSignatureAlgorithm("SHA1withRSA");
		return certGen.generate(priKey, "BC");
	}
	/**
	 * 生成X509V2CRL证书吊销文件
	 * @param address 保存的目录
	 * @param bdate 生效日期
	 * @param edate 下一次更新日期
	 * @param info 主题信息
	 * @param priKey CA证书私钥
	 * @throws Exception
	 */
	public  static void createCRLCert(String address, Date bdate, Date edate,
			X500Principal info, PrivateKey priKey) throws Exception {
		String certaddress = address + "/" + new Date().getTime() / 1000
				+ ".crl";
		X509CRL cert = generateV2CRLCertificate(bdate,edate,info,priKey);
		FileOutputStream outputStream = new FileOutputStream(certaddress);
		Writer writer = new OutputStreamWriter(outputStream,
				Charset.forName("UTF-8"));
		writer.write("-----BEGIN CERTIFICATE-----\n");
		writer.write(Base64.encode(cert.getEncoded()));
		writer.write("\n-----END CERTIFICATE-----\n");
		writer.flush();
		writer.close();
		System.out.println("证书保存目录:" + certaddress);
	}
	public static void  verify(String serialNumber) {
		
	}
}
