/**
 * 
 */
package top.ibase4j.core.exception;

import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.ModelMap;

import top.ibase4j.core.support.HttpCode;

/**
 * 
 * @author ShenHuaJie
 * @version 2016年6月7日 下午8:43:02
 */
@SuppressWarnings("serial")
public abstract class BaseException extends RuntimeException {
	public BaseException() {
	}

	public BaseException(Throwable ex) {
		super(ex);
	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(String message, Throwable ex) {
		super(message, ex);
	}

	public void handler(ModelMap modelMap) {
		modelMap.put("code", getCode().value());
		if (StringUtils.isNotBlank(getMessage())) {
			modelMap.put("msg", getMessage());
		} else {
			modelMap.put("msg", getCode().msg());
		}
		modelMap.put("timestamp", System.currentTimeMillis());
	}

	protected abstract HttpCode getCode();
}
