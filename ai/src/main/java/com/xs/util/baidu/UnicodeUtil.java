package com.xs.util.baidu;

import java.io.UnsupportedEncodingException;
import java.lang.Character.UnicodeBlock;

/**
 * 转给需要的人。UNICODE转中文工具类
 * @author 小帅帅丶
 * @blog https://my.oschina.net/xshuai
 * @date 2017-5-5下午01:11:42
 */
public class UnicodeUtil {
	/***
	 * 将Unicode编码转为中文
	 * @Title decodeUnicode
	 * @param theString   包含unicode的字符串
	 * @return 转换后的字符串
	 * @author 小帅帅丶
	 * @date 2017-5-5
	 */
	public static String decodeUnicode(String theString) {
		char aChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			aChar = theString.charAt(x++);
			if (aChar == '\\') {
				aChar = theString.charAt(x++);
				if (aChar == 'u') {
					// Read the xxxx
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = theString.charAt(x++);
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException(
									"Malformed   \\uxxxx   encoding.");
						}

					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't')
						aChar = '\t';
					else if (aChar == 'r')
						aChar = '\r';
					else if (aChar == 'n')
						aChar = '\n';
					else if (aChar == 'f')
						aChar = '\f';
					outBuffer.append(aChar);
				}
			} else
				outBuffer.append(aChar);
		}
		return outBuffer.toString();

	}
	/**
	 * UTF-8转GBK
	 * @param str
	 * @return String
	 * @throws Exception
	 */
	public static String getGbk(String str) throws Exception{
		String t = str;  
		String utf8 = new String(t.getBytes( "UTF-8"));  
		System.out.println(utf8+"utf8");
		String unicode = new String(utf8.getBytes(),"UTF-8");   
		System.out.println(unicode+"unicode");
		String gbk = new String(unicode.getBytes("GBK"));  
		System.out.println(gbk+"gbk");
		return gbk;
	}
	/**
	 * Unicode转GBK
	 * @param dataStr
	 * @return String
	 */
	public static String Unicode2GBK(String dataStr) {  
        int index = 0;  
        StringBuffer buffer = new StringBuffer();  
  
        int li_len = dataStr.length();  
        while (index < li_len) {  
            if (index >= li_len - 1  
                    || !"\\u".equals(dataStr.substring(index, index + 2))) {  
                buffer.append(dataStr.charAt(index));  
                index++;  
                continue;  
            }  
            String charStr = "";  
            charStr = dataStr.substring(index + 2, index + 6);  
            char letter = (char) Integer.parseInt(charStr, 16);  
            buffer.append(letter);  
            index += 6;  
        }  
        return buffer.toString();  
    }  
	/**
	 * 获取字符串的编码格式
	 * @param str
	 * @return
	 */
    public static String getEncoding(String str) {        
    	String encode[] = new String[]{  
                "UTF-8",  
                "ISO-8859-1",  
                "GB2312",  
                "GBK",  
                "GB18030",  
                "Big5",  
                "Unicode",  
                "ASCII"  
        };  
        for (int i = 0; i < encode.length; i++){  
            try {  
                if (str.equals(new String(str.getBytes(encode[i]), encode[i]))) {  
                    return encode[i];  
                }  
            } catch (Exception ex) {  
            }  
        }  
        return "";     
    }    
	 public static String utf82gbk(String utf) {  
	        String l_temp = utf8ToUnicode(utf);  
	        l_temp = Unicode2GBK(l_temp);  
	        return l_temp;  
	    }  
	/** 
     * utf-8 转unicode 
     *  
     * @param inStr 
     * @return String 
     */  
    public static String utf8ToUnicode(String inStr) {  
        char[] myBuffer = inStr.toCharArray();  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < inStr.length(); i++) {  
            UnicodeBlock ub = UnicodeBlock.of(myBuffer[i]);  
            if (ub == UnicodeBlock.BASIC_LATIN) {  
                sb.append(myBuffer[i]);  
            } else if (ub == UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {  
                int j = (int) myBuffer[i] - 65248;  
                sb.append((char) j);  
            } else {  
                short s = (short) myBuffer[i];  
                String hexS = Integer.toHexString(s);  
                String unicode = "\\u" + hexS;  
                sb.append(unicode.toLowerCase());  
            }  
        }  
        return sb.toString();  
    }  
    public static void main(String[] args) throws Exception {
    	String  a = getGbk("你好");
    	System.out.println(getEncoding(a));
//    	System.out.println(getEncoding(new String("你好".getBytes(),"GBK")));
	}
}
