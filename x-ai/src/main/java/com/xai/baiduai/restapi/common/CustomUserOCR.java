package com.xai.baiduai.restapi.common;

import com.baidu.aip.client.BaseClient;
import com.baidu.aip.http.AipRequest;
import com.baidu.aip.util.Base64Util;
import com.xai.common.utils.FileUtil;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

/**
 * 百度文字识别
 *
 * @author 小帅丶
 * @create 2018-03-26 13:35
 **/
public class CustomUserOCR extends BaseClient{
    /**
     * 手写识别
     */
    public static final String HANDWRITING_DETECT = "https://aip.baidubce.com/rest/2.0/ocr/v1/handwriting";

    public CustomUserOCR(String appId, String apiKey, String secretKey) {
        super(appId, apiKey, secretKey);
    }
    /**
     * 手写识别接口
     * 该请求用于食材识别。即对于输入的一张图片（可正常解码，且长宽比适宜），输出图片的食材名称，置信度。
     * @param imagePath - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     * options - options列表:
     * @return JSONObject
     */
    public JSONObject handwritingDetect(String imagePath, HashMap<String, String> options) throws IOException {
        AipRequest request = new AipRequest();
        preOperation(request);
        byte[] image = FileUtil.readFileByBytes(imagePath);
        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(HANDWRITING_DETECT);
        postOperation(request);
        return requestServer(request);
    }
}
