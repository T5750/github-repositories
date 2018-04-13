package com.xs.pojo.ocr.basic;

import java.util.List;
/**
 * @类名称 OcrBasic
 * @author 小帅丶
 * @remark OCR通用文字识别基础对象
 *
 */
public class OcrBasic{
	/**
	 * 唯一的log id，用于问题定位
	 */
	public Integer log_id;
	/**
	 * 识别结果数，表示words_result的元素个数
	 */
	public Integer words_result_num;
	/**
	 * 识别结果数组
	 */
	public List<WordsResult> words_result;
	
	public Integer getLog_id() {
		return log_id;
	}
	public void setLog_id(Integer log_id) {
		this.log_id = log_id;
	}
	public Integer getWords_result_num() {
		return words_result_num;
	}
	public void setWords_result_num(Integer words_result_num) {
		this.words_result_num = words_result_num;
	}
	public List<WordsResult> getWords_result() {
		return words_result;
	}
	public void setWords_result(List<WordsResult> words_result) {
		this.words_result = words_result;
	}
	
}
