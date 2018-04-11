package com.xai.baiduai.restapi.ocr;

import com.alibaba.fastjson.JSON;
import com.baidu.aip.ocr.AipOcr;
import com.xai.baiduai.jsonbean.BdResponse;
import com.xai.baiduai.jsonbean.OcrBDJSON;
import com.xai.baiduai.jsonbean.OcrBDResponse;
import com.xai.baiduai.manager.domain.OCRBdDO;
import com.xai.baiduai.manager.service.OCRBDService;
import com.xai.baiduai.restapi.common.BDContants;
import com.xai.baiduai.restapi.common.CustomUserOCR;
import com.xai.baiduai.util.BDConstant;
import com.xai.common.controller.BaseController;
import com.xai.common.utils.FileUtil;
import com.xai.common.utils.PrintUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * 百度文字识别
 *
 * @author 小帅丶
 * @create 2018-03-26 13:26
 **/
@Controller
@RequestMapping(value = "/bd/ocrBD")
@Scope("prototype")
public class OCRBDController extends BaseController{
    AipOcr aipOcr = new AipOcr(BDContants.ICR_APPID,BDContants.ICR_APIKEY,BDContants.ICR_SECRETKEY);
    CustomUserOCR customUserOCR =  new CustomUserOCR(BDContants.ICR_APPID,BDContants.ICR_APIKEY,BDContants.ICR_SECRETKEY);
    @Autowired
    private OCRBDService ocrbdService;
    /**
     * 图像检测网页上传
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = "/ocrdetect")
    public String getDetectFace(HttpServletRequest request, HttpServletResponse response){
        String prefix = "baiduai/ocr/ocrfile";
        return prefix;
    }
    @RequestMapping(value = "/ocrbd",method = RequestMethod.POST)
    public  String uploadOCR(@RequestParam(value = "file")MultipartFile file,@RequestParam(value="apiType")String apiType,HttpServletRequest request, HttpServletResponse response){
        DecimalFormat df = new DecimalFormat("######0.00");
        String resultData = "";
        String bdPath = "/OCRbd/";
        String typeName = apiType+new Date().getTime()/1000+"OCRBD";
        System.out.println("======="+file+"======接口类型"+apiType);
        String fileName = typeName+file.getOriginalFilename();
        String filePath = request.getSession().getServletContext().getRealPath(bdPath);
        try{
            FileUtil.uploadFile(file.getBytes(),filePath,fileName);
            //图片的本地路径
            String imagePath = filePath+fileName;
            JSONObject jsonObject = getOCRData(imagePath,apiType);
            System.out.println("========接口返回的内容"+jsonObject.toString(2));
            if(jsonObject.has("error_code")){
                System.out.println("文字识别百度接口出错了"+jsonObject.get("error_code")+"="+jsonObject.get("error_msg").toString());
                BdResponse bdResponse = new BdResponse();
                bdResponse.setCode(BDConstant.BD_ERROR.getCode().toString());
                bdResponse.setMsg(jsonObject.get("error_msg").toString());
                resultData = JSON.toJSONString(bdResponse);
                System.out.println(resultData);
                PrintUtil.printJson(response,resultData);
            }else{
                OcrBDJSON ocrBDJSON = com.alibaba.fastjson.JSONObject.parseObject(jsonObject.toString(),OcrBDJSON.class);
                if(apiType.equals("bankcard")){
                    if(null==ocrBDJSON.getResult()){
                        BdResponse bdResponse = new BdResponse();
                        bdResponse.setCode(BDConstant.BD_ERROR.getCode().toString());
                        bdResponse.setMsg("未能识别出文字内容");
                        resultData = JSON.toJSONString(bdResponse);
                        System.out.println(resultData);
                        PrintUtil.printJson(response,resultData);
                    }else{
                        bdPath += fileName;
                        OCRBdDO ocrBdDO = getOcrDO(ocrBDJSON,apiType,imagePath);
                        OcrBDResponse ocrBDResponse = getOcrResponse(ocrBDJSON,apiType);
                        resultData = JSON.toJSONString(ocrBDResponse);
                        System.out.println(resultData);
                        PrintUtil.printJson(response,resultData);
                        int saveResult = ocrbdService.save(ocrBdDO);
                    }
                }else{
                    if(ocrBDJSON.getWords_result_num()==0){
                        BdResponse bdResponse = new BdResponse();
                        bdResponse.setCode(BDConstant.BD_ERROR.getCode().toString());
                        bdResponse.setMsg("未能识别出文字内容");
                        resultData = JSON.toJSONString(bdResponse);
                        System.out.println(resultData);
                        PrintUtil.printJson(response,resultData);
                    }else{
                        bdPath += fileName;
                        OCRBdDO ocrBdDO = getOcrDO(ocrBDJSON,apiType,imagePath);
                        OcrBDResponse ocrBDResponse = getOcrResponse(ocrBDJSON,apiType);
                        resultData = JSON.toJSONString(ocrBDResponse);
                        System.out.println(resultData);
                        PrintUtil.printJson(response,resultData);
                        int saveResult = ocrbdService.save(ocrBdDO);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            BdResponse bdResponse = new BdResponse();
            bdResponse.setCode(BDConstant.BD_ERROR.getCode().toString());
            bdResponse.setMsg("系统错误。请联系管理员");
            resultData = JSON.toJSONString(bdResponse);
            System.out.println(resultData);
            PrintUtil.printJson(response,resultData);
        }
        return  null;
    }

    private OCRBdDO getOcrDO(OcrBDJSON ocrjson, String apiType, String imagePath) {
        OCRBdDO ocrBdDO = new OCRBdDO();
        ocrBdDO.setLogId(ocrjson.getLog_id().toString());
        ocrBdDO.setApiType(apiType);
        ocrBdDO.setImagePath(imagePath);
        //身份证识别反面
        if(apiType.equals("idcardb")){
        }
        //身份证识别正面
        if(apiType.equals("idcardf")){
            ocrBdDO.setWordsResultNum(ocrjson.getWords_result_num());
            ocrBdDO.setName(ocrjson.getWords_result().get(0).getName().getWords());
            ocrBdDO.setIdCardNum(ocrjson.getWords_result().get(0).getIdCardNum().getWords());
            ocrBdDO.setAddress(ocrjson.getWords_result().get(0).getAddress().getWords());
            ocrBdDO.setSex(ocrjson.getWords_result().get(0).getSex().getWords());
            ocrBdDO.setBirth(ocrjson.getWords_result().get(0).getBirth().getWords());
            ocrBdDO.setNation(ocrjson.getWords_result().get(0).getNation().getWords());
        }
        //银行卡识别
        if(apiType.equals("bankcard")){
            ocrBdDO.setBankName(ocrjson.getResult().getBank_name());
            ocrBdDO.setBankCardNumber(ocrjson.getResult().getBank_card_number());
            ocrBdDO.setBankCardType(ocrjson.getResult().getBank_card_type().toString());
        }
        //通用文字识别含位置
        if(apiType.equals("general")){
            String words = "";
            for(int i=0;i<ocrjson.getWords_result_num();i++){
                words += ocrjson.getWords_result().get(i).getWords()+",";
            }
            ocrBdDO.setWords(words.substring(0,words.length()-1));
        }
        //通用文字识别
        if(apiType.equals("general_basic")){
        }
        //网络图片文字识别
        if(apiType.equals("webimage")){
        }
        //行驶证识别
        if(apiType.equals("vehicle_license")){
        }
        //驾驶证识别
        if(apiType.equals("driving_license")){
        }
        //营业执照识别
        if(apiType.equals("business_license")){
        }
        //车牌识别
        if(apiType.equals("license_plate")){
        }
        //通用票据识别
        if(apiType.equals("receipt")){
        }
        //手写文字识别
        if(apiType.equals("handwriting")){
        }
        return  ocrBdDO;
    }

    //组装页面所需对象
    private OcrBDResponse getOcrResponse(OcrBDJSON ocrjson, String apiType) {
        OcrBDResponse ocr = new OcrBDResponse();
        DecimalFormat df = new DecimalFormat("######0.00");
        ocr.setCode(BDConstant.BD_SUCCESS.getCode().toString());
        ocr.setMsg(BDConstant.BD_SUCCESS.getMsg());
        ocr.setType(apiType);
        //身份证识别反面
        if(apiType.equals("idcardb")){
        }
        //身份证识别正面
        if(apiType.equals("idcardf")){
            ocr.setName(ocrjson.getWords_result().get(0).getName().getWords());
            ocr.setIdCardNum(ocrjson.getWords_result().get(0).getIdCardNum().getWords());
            ocr.setAddress(ocrjson.getWords_result().get(0).getAddress().getWords());
            ocr.setSex(ocrjson.getWords_result().get(0).getSex().getWords());
            ocr.setBirth(ocrjson.getWords_result().get(0).getBirth().getWords());
            ocr.setNation(ocrjson.getWords_result().get(0).getNation().getWords());
        }
        //银行卡识别
        if(apiType.equals("bankcard")){
            ocr.setBankName(ocrjson.getResult().getBank_name());
            ocr.setBankCardNumber(ocrjson.getResult().getBank_card_number());
            ocr.setBankCardType(ocrjson.getResult().getBank_card_type());
        }
        //通用文字识别含位置
        if(apiType.equals("general")){
            String words = "";
            for(int i=0;i<ocrjson.getWords_result_num();i++){
                words += ocrjson.getWords_result().get(i).getWords()+",";
            }
            ocr.setWords(words.substring(0,words.length()-1));
        }
        //通用文字识别
        if(apiType.equals("general_basic")){
        }
        //网络图片文字识别
        if(apiType.equals("webimage")){
        }
        //行驶证识别
        if(apiType.equals("vehicle_license")){
        }
        //驾驶证识别
        if(apiType.equals("driving_license")){
        }
        //营业执照识别
        if(apiType.equals("business_license")){
        }
        //车牌识别
        if(apiType.equals("license_plate")){
        }
        //通用票据识别
        if(apiType.equals("receipt")){
        }
        //手写文字识别
        if(apiType.equals("handwriting")){
        }
        return  ocr;
    }

    //文字识别处理
    private JSONObject getOCRData(String imagePath, String apiType) throws Exception {
        HashMap<String, String> option = new HashMap<String, String>();
        option.put("detect_direction", "true");
        JSONObject jsonObject = null;
        //身份证识别反面
        if(apiType.equals("idcardb")){
            jsonObject = aipOcr.idcard(imagePath,"back",option);
        }
        //身份证识别正面
        if(apiType.equals("idcardf")){
            jsonObject = aipOcr.idcard(imagePath,"front",option);
        }
        //银行卡识别
        if(apiType.equals("bankcard")){
            jsonObject = aipOcr.bankcard(imagePath,option);
        }
        //通用文字识别含位置
        if(apiType.equals("general")){
            jsonObject = aipOcr.general(imagePath,option);
        }
        //通用文字识别
        if(apiType.equals("general_basic")){
            jsonObject = null;
        }
        //网络图片文字识别
        if(apiType.equals("webimage")){
            jsonObject = aipOcr.webImage(imagePath,option);
        }
        //行驶证识别
        if(apiType.equals("vehicle_license")){
            jsonObject = aipOcr.vehicleLicense(imagePath,option);
        }
        //驾驶证识别
        if(apiType.equals("driving_license")){
            jsonObject = aipOcr.drivingLicense(imagePath,option);
        }
        //营业执照识别
        if(apiType.equals("business_license")){
            jsonObject = aipOcr.businessLicense(imagePath,option);
        }
        //车牌识别
        if(apiType.equals("license_plate")){
            jsonObject = aipOcr.plateLicense(imagePath,option);
        }
        //通用票据识别
        if(apiType.equals("receipt")){
            jsonObject = aipOcr.receipt(imagePath,option);
        }
        //手写文字识别
        if(apiType.equals("handwriting")){
            jsonObject = customUserOCR.handwritingDetect(imagePath,option);
        }
        return  jsonObject;
        }


}
