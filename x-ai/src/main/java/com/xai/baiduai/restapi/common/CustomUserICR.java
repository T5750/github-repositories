package com.xai.baiduai.restapi.common;

import com.baidu.aip.client.BaseClient;
import com.baidu.aip.http.AipRequest;
import com.baidu.aip.util.Base64Util;
import com.xai.common.utils.FileUtil;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

/**
 * 食材接口调用基于SDK封装
 *
 * @author 小帅丶
 * @create 2018-03-20 14:13
 **/
public class CustomUserICR extends BaseClient{
    /**
     * 食材识别
     */
    public static final String IngredientICR_DETECT = "https://aip.baidubce.com/rest/2.0/image-classify/v1/classify/ingredient";
    /**
     * 定制化图像识别
     */
    public static final String CUSTOM_DETECT = "";
    public CustomUserICR(String appId, String apiKey, String secretKey) {
        super(appId, apiKey, secretKey);
    }
    /**
     * 食材识别接口
     * 该请求用于食材识别。即对于输入的一张图片（可正常解码，且长宽比适宜），输出图片的食材名称，置信度。
     * @param imagePath - 二进制图像数据
     * @param options - 可选参数对象，key: value都为string类型
     * options - options列表:
     *   top_num 返回预测得分top结果数，默认为5
     * @return JSONObject
     */
    public JSONObject ingredientDetect(String imagePath, HashMap<String, String> options) throws IOException {
        AipRequest request = new AipRequest();
        preOperation(request);
        byte[] image = FileUtil.readFileByBytes(imagePath);
        String base64Content = Base64Util.encode(image);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri(IngredientICR_DETECT);
        postOperation(request);
        return requestServer(request);
    }

}
