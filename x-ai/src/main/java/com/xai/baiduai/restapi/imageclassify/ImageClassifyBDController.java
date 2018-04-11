package com.xai.baiduai.restapi.imageclassify;

import com.alibaba.fastjson.JSON;
import com.baidu.aip.face.AipFace;
import com.baidu.aip.imageclassify.AipImageClassify;
import com.xai.baiduai.jsonbean.*;
import com.xai.baiduai.manager.domain.FaceBdDO;
import com.xai.baiduai.manager.domain.ImageClassifyBdDO;
import com.xai.baiduai.manager.service.FaceBDService;
import com.xai.baiduai.manager.service.ImageClassifyBDService;
import com.xai.baiduai.restapi.common.BDContants;
import com.xai.baiduai.restapi.common.CustomUserICR;
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
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * 百度AI图像识别模块
 */
@RequestMapping("/bd/icrBD")
@Controller
@Scope("prototype")
public class ImageClassifyBDController extends BaseController{
    AipImageClassify aipImageClassify = new AipImageClassify(BDContants.ICR_APPID,BDContants.ICR_APIKEY,BDContants.ICR_SECRETKEY);
    CustomUserICR customUserICR = new CustomUserICR(BDContants.ICR_APPID,BDContants.ICR_APIKEY,BDContants.ICR_SECRETKEY);
    @Autowired
    private ImageClassifyBDService imageClassifyBDService;
    /**
     * 图像检测网页上传
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = "/icrdetect")
    public String getDetectFace(HttpServletRequest request, HttpServletResponse response){
        String prefix = "baiduai/imageclassify/imageclassifyfile";
        return prefix;
    }
    /**
     * 图像识别上传百度
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/ImageClassifydb",method = {RequestMethod.POST})
    public void uploadICR(@RequestParam(value = "file")MultipartFile file,@RequestParam(value = "apiType")String apiType,HttpServletRequest request, HttpServletResponse response){
        DecimalFormat df = new DecimalFormat("######0.00");
        String resultData = "";
        String perfix = "ICRdb/";
        String dbPath ="/"+perfix;
        String typeName = apiType+new Date().getTime()/1000+"ICRBD";
        System.out.println("======="+file+"======接口类型"+apiType);
        String fileName = typeName+file.getOriginalFilename();
        String filePath = request.getSession().getServletContext().getRealPath(perfix);
        try {
            FileUtil.uploadFile(file.getBytes(),filePath,fileName);
            //图片的本地路径
            String imagePath = filePath+fileName;
            JSONObject jsonObject = getImageClassify(imagePath,apiType);
            System.out.println("========接口返回的内容"+jsonObject.toString());
            if(jsonObject.has("error_code")){
                System.out.println("图像识别百度接口出错了");
                BdResponse bdResponse = new BdResponse();
                bdResponse.setCode(BDConstant.BD_ERROR.getCode().toString());
                bdResponse.setMsg(jsonObject.get("error_msg").toString());
                resultData = JSON.toJSONString(bdResponse);
                System.out.println(resultData);
                PrintUtil.printJson(response,resultData);
            }else{
            ImageClassifyBDJSON bdjson = com.alibaba.fastjson.JSONObject.parseObject(jsonObject.toString(),ImageClassifyBDJSON.class);
                if(bdjson.getResult().get(0).getName().equals("非动物")
                        ||bdjson.getResult().get(0).getName().equals("非植物")
                        ||bdjson.getResult().get(0).getName().equals("非菜")
                        ||bdjson.getResult().get(0).getName().equals("非车类")
                        ||bdjson.getResult().get(0).getName().equals("非果蔬食材")){
                    BdResponse bdResponse = new BdResponse();
                    bdResponse.setCode(BDConstant.BD_ERROR.getCode().toString());
                    bdResponse.setMsg(bdjson.getResult().get(0).getName());
                    resultData = JSON.toJSONString(bdResponse);
                    System.out.println(resultData);
                    PrintUtil.printJson(response,resultData);
                }else{
                    dbPath += fileName;
                    ImageClassifyBdDO bdDO = getImageClassifyDO(bdjson,apiType,dbPath);
                    int saveResult = imageClassifyBDService.save(bdDO);
                    BDImageClassifyResponse classifyResponse = getImageClassifyRespone(bdjson,apiType);
                    resultData = JSON.toJSONString(classifyResponse);
                    System.out.println(resultData);
                    PrintUtil.printJson(response,resultData);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            BdResponse bdResponse = new BdResponse();
            bdResponse.setCode(BDConstant.BD_ERROR.getCode().toString());
            bdResponse.setMsg("系统错误。请联系管理员");
            resultData = JSON.toJSONString(bdResponse);
            System.out.println(resultData);
            PrintUtil.printJson(response,resultData);
        }
    }
    //组装页面所需对象
    private BDImageClassifyResponse getImageClassifyRespone(ImageClassifyBDJSON apjson, String apiType) {
        BDImageClassifyResponse classifyResponse = new BDImageClassifyResponse();
        DecimalFormat df = new DecimalFormat("######0.00");
        classifyResponse.setCode(BDConstant.BD_SUCCESS.getCode().toString());
        classifyResponse.setMsg(BDConstant.BD_SUCCESS.getMsg());
        classifyResponse.setName(apjson.getResult().get(0).getName());
        classifyResponse.setType(apiType);
        if(apiType.equals("car")){
            classifyResponse.setColorResult(apjson.getColor_result());
            classifyResponse.setYear(apjson.getResult().get(0).getYear());
        }
        if(apiType.equals("dish")){
            classifyResponse.setCalorie(df.format(apjson.getResult().get(0).getCalorie()));
        }
        if(apiType.equals("dish")||apiType.equals("logo")){
            String probability = Double.toString(apjson.getResult().get(0).getProbability()*100).substring(0,5);
            classifyResponse.setProbability(probability+"%");
        }
        if(!apiType.equals("dish")&&!apiType.equals("logo")){
            classifyResponse.setScore(df.format(apjson.getResult().get(0).getScore()));
        }
        return classifyResponse;
    }

    //组装数据库对象
    private ImageClassifyBdDO getImageClassifyDO(ImageClassifyBDJSON apjson,String apiType,String imagePath) {
        ImageClassifyBdDO bdDO = new ImageClassifyBdDO();
        DecimalFormat df = new DecimalFormat("######0.00");
        bdDO.setLogId(apjson.getLog_id());
        bdDO.setApiType(apiType);
        if(!apiType.equals("car")){
            bdDO.setResultNum(apjson.getResult_num());
        }
        if(apiType.equals("car")){
            bdDO.setColorResult(apjson.getColor_result());
            bdDO.setYear(apjson.getResult().get(0).getYear());
        }
        bdDO.setName(apjson.getResult().get(0).getName());
        bdDO.setImagePath(imagePath);
        if(apiType.equals("dish")){
            bdDO.setCalorie(df.format(apjson.getResult().get(0).getCalorie()));
        }
        if(apiType.equals("dish")||apiType.equals("logo")){
            String probability = Double.toString(apjson.getResult().get(0).getProbability()*100).substring(0,5);
            bdDO.setProbability(probability+"%");
        }
        if(!apiType.equals("dish")||!apiType.equals("logo")){
            bdDO.setScore(df.format(apjson.getResult().get(0).getScore()));
        }
        if(apiType.equals("logo")){
            bdDO.setLeft(Integer.toString(apjson.getResult().get(0).getLocation().getLeft()));
            bdDO.setTop(Integer.toString(apjson.getResult().get(0).getLocation().getTop()));
            bdDO.setWidth(Integer.toString(apjson.getResult().get(0).getLocation().getWidth()));
            bdDO.setHeight(Integer.toString(apjson.getResult().get(0).getLocation().getHeight()));
            bdDO.setType(Integer.toString(apjson.getResult().get(0).getType()));
        }
        return bdDO;
    }

    //图像识别处理
    private JSONObject getImageClassify(String imagePath, String apiType) throws Exception{
        HashMap<String, String> option = new HashMap<String, String>();
        option.put("top_num", "1");
        JSONObject jsonObject = null;
        if(apiType.equals("plant")){
            jsonObject = aipImageClassify.plantDetect(imagePath,option);
        }
        if(apiType.equals("dish")){
            jsonObject = aipImageClassify.dishDetect(imagePath,option);
        }
        if(apiType.equals("car")){
            jsonObject = aipImageClassify.carDetect(imagePath,option);
        }
        if(apiType.equals("logo")){
            jsonObject = aipImageClassify.logoSearch(imagePath,option);
        }
        if(apiType.equals("animal")){
            jsonObject = aipImageClassify.animalDetect(imagePath,option);
        }
        if(apiType.equals("ingredient")){
            jsonObject = customUserICR.ingredientDetect(imagePath,option);
        }
        return  jsonObject;
    }

}
