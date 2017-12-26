/**
 * 
 */
package top.ibase4j.core.support.context;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import top.ibase4j.core.Constants;
import top.ibase4j.core.util.PropertiesUtil;
import top.ibase4j.core.util.SecurityUtil;

/**
 * 
 * @author ShenHuaJie
 * @version 2017年12月1日 上午10:48:48
 */
public class PropertyPlaceholder extends PropertyPlaceholderConfigurer {
    private List<String> decryptProperties;

    @Override
    protected void loadProperties(Properties props) throws IOException {
        super.loadProperties(props);
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            if (decryptProperties != null && decryptProperties.contains(keyStr)) {
                value = SecurityUtil.decryptDes(value, props.getProperty("db.key", Constants.DB_KEY).getBytes());
                props.setProperty(keyStr, value);
            }
            PropertiesUtil.getProperties().put(keyStr, value);
        }
    }

    /**
     * @param decryptPropertiesMap
     *            the decryptPropertiesMap to set
     */
    public void setDecryptProperties(List<String> decryptProperties) {
        this.decryptProperties = decryptProperties;
    }

    public static void main(String[] args) {
        String encrypt = SecurityUtil.encryptDes("buzhidao", Constants.DB_KEY.getBytes());
        System.out.println(encrypt);
        System.out.println(SecurityUtil.decryptDes(encrypt, Constants.DB_KEY.getBytes()));
    }
}
