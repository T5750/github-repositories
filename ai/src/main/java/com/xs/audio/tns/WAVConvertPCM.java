package com.xs.audio.tns;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;
/**
 * WAV转PCM(截掉前44个字节数据)
 * @author Administrator
 *
 */
public class WAVConvertPCM {
	/**
	 * WAV转PCM文件
	 * @param wavfilepath wav文件路径
	 * @param pcmfilepath pcm要保存的文件路径及文件名
	 * @return
	 */
	public static String convertAudioFiles(String wavfilepath,String pcmfilepath){
		FileInputStream fileInputStream;
		FileOutputStream fileOutputStream;
		try {
			fileInputStream = new FileInputStream(wavfilepath);
			fileOutputStream = new FileOutputStream(pcmfilepath);
			byte[] wavbyte = InputStreamToByte(fileInputStream);
			byte[] pcmbyte = Arrays.copyOfRange(wavbyte, 44, wavbyte.length);
			fileOutputStream.write(pcmbyte);
			IOUtils.closeQuietly(fileInputStream);
			IOUtils.closeQuietly(fileOutputStream);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return pcmfilepath;
	}
	/**
	 * 输入流转byte二进制数据
	 * @param fis
	 * @return
	 * @throws IOException
	 */
	private static byte[] InputStreamToByte(FileInputStream fis) throws IOException {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		long size = fis.getChannel().size();
		byte[] buffer = null;
		if (size <= Integer.MAX_VALUE) {
			buffer = new byte[(int) size];
		} else {
			buffer = new byte[8];
			for (int ix = 0; ix < 8; ++ix) {
				int offset = 64 - (ix + 1) * 8;
				buffer[ix] = (byte) ((size >> offset) & 0xff);
			}
		}
		int len;
		while ((len = fis.read(buffer)) != -1) {
			byteStream.write(buffer, 0, len);
		}
		byte[] data = byteStream.toByteArray();
		IOUtils.closeQuietly(byteStream);
		return data;
	}
}
