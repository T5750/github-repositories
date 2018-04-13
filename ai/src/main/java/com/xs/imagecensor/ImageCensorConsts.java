package com.xs.imagecensor;

public class ImageCensorConsts {
	/**
	 * 图像审核接口
	 */
	static final String ANTI_USER_DEFINED_URL = "https://aip.baidubce.com/rest/2.0/solution/v1/img_censor/user_defined";
	/**
	 * 
	 */
	static final String ANTI_PORN_URL = "https://aip.baidubce.com/rest/2.0/antiporn/v1/detect";
	/**
	 * GIF色情图像识别
	 */
    static final String ANTI_PORN_GIF_URL ="https://aip.baidubce.com/rest/2.0/antiporn/v1/detect_gif";
	/**
	 * 
	 */
    static final String ANTI_TERROR_URL = "https://aip.baidubce.com/rest/2.0/antiterror/v1/detect";
	/**
	 * 图像审核组合服务接口
	 */
    static final String IMAGE_CENSOR_COMB_URL = "https://aip.baidubce.com/api/v1/solution/direct/img_censor";
	/**
	 * 用户头像审核
	 */
    static final String FACE_AUDIT_URL = "https://aip.baidubce.com/rest/2.0/solution/v1/face_audit";
}
