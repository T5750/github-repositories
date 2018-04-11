package com.xai.baiduai.restapi.face;

import com.alibaba.fastjson.JSON;
import com.baidu.aip.face.AipFace;
import com.baidu.aip.util.Base64Util;
import com.xai.baiduai.jsonbean.BdFaceResponse;
import com.xai.baiduai.jsonbean.FaceBDJSON;
import com.xai.baiduai.manager.domain.FaceBdDO;
import com.xai.baiduai.manager.service.FaceBDService;
import com.xai.baiduai.restapi.common.BDContants;
import com.xai.baiduai.util.BDConstant;
import com.xai.common.config.Constant;
import com.xai.common.controller.BaseController;
import com.xai.common.utils.FileUtil;
import com.xai.common.utils.PrintUtil;

import com.xai.common.utils.R;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * 百度AI人脸识别模块
 */
@RequestMapping("/bd/faceBD")
@Controller
@Scope("prototype")
public class FaceBDController extends BaseController{
    AipFace aipFace = new AipFace(BDContants.FACE_APPID,BDContants.FACE_APIKEY,BDContants.FACE_SECRETKEY);
    @Autowired
    private FaceBDService faceBDService;
    /**
     * 人脸检测网页上传
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = "/detectface")
    public String getDetectFace(HttpServletRequest request, HttpServletResponse response){
        String prefix = "baiduai/face/facefile";
        return prefix;
    }
    /**
     * 人脸检测上传百度
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/Facedb",method = {RequestMethod.POST})
    public String uploadDetectFace(@RequestParam(value = "file")MultipartFile file, HttpServletRequest request, HttpServletResponse response){
        DecimalFormat df = new DecimalFormat("######0.00");
        String resultData = "";
        String perfix = "Facedb/";
        String dbPath ="/"+perfix;
        String typeName = new Date().getTime()/1000+"faceBD";
        System.out.print("======="+file);
        String fileName = typeName+file.getOriginalFilename();
        String filePath = request.getSession().getServletContext().getRealPath(perfix);
        try {
            FileUtil.uploadFile(file.getBytes(),filePath,fileName);
            //图片的本地路径
            String imagePath = filePath+fileName;
            HashMap<String, String> option = new HashMap<String, String>();
            option.put("face_fields","age,beauty,expression,faceshape,gender,glasses,race,qualities");
            option.put("max_face_num", "1");
            JSONObject jsonObject = aipFace.detect(imagePath,option);
            FaceBDJSON faceBDJSON = com.alibaba.fastjson.JSONObject.parseObject(jsonObject.toString(),FaceBDJSON.class);
            System.out.print("======="+jsonObject.toString(2));
            if(faceBDJSON.getResult_num()>=1){
                FaceBdDO faceBdDO = new FaceBdDO();
                dbPath += fileName;
                faceBdDO = getFaceBdDO(faceBDJSON,dbPath);
                int result =faceBDService.save(faceBdDO);
                System.out.println("保存成功了 "+result);
                BdFaceResponse bdFaceResponse = new BdFaceResponse();
                bdFaceResponse.setCode(BDConstant.BD_SUCCESS.getCode().toString());
                bdFaceResponse.setMsg(BDConstant.BD_SUCCESS.getMsg());
                bdFaceResponse.setAge(df.format(faceBDJSON.getResult().get(0).getAge()));
                bdFaceResponse.setBeauty(df.format(faceBDJSON.getResult().get(0).getBeauty()));
                bdFaceResponse.setCartoon(df.format(faceBDJSON.getResult().get(0).getQualities().getType().getCartoon()));
                //表情，0，不笑；1，微笑；2，大笑
                if(faceBDJSON.getResult().get(0).getExpression()==0){
                	bdFaceResponse.setExpression("不笑");
               }else if (faceBDJSON.getResult().get(0).getExpression()==1) {
            	   bdFaceResponse.setExpression("微笑");
				}else {
					bdFaceResponse.setExpression("大笑");
				}
                if(faceBDJSON.getResult().get(0).getGender().equals("male")){
                	bdFaceResponse.setGender("男");
                }else{
                	bdFaceResponse.setGender("女");
                }
                //0-无眼镜，1-普通眼镜，2-墨镜
                if(faceBDJSON.getResult().get(0).getGlasses()==0){
                	 bdFaceResponse.setGlasses("无眼镜");
                }else if (faceBDJSON.getResult().get(0).getGlasses()==1) {
                	 bdFaceResponse.setGlasses("普通眼镜");
				}else {
					 bdFaceResponse.setGlasses("墨镜");
				}
                resultData = JSON.toJSONString(bdFaceResponse);
                System.out.println(resultData);
                PrintUtil.printJson(response,resultData);
            }else{
                System.out.print("人脸检测百度接口识别的人脸数目为:"+faceBDJSON.getResult_num());
                BdFaceResponse bdFaceResponse = new BdFaceResponse();
                bdFaceResponse.setCode(BDConstant.BD_NOFACE.getCode().toString());
                bdFaceResponse.setMsg(BDConstant.BD_NOFACE.getMsg());
                resultData = JSON.toJSONString(bdFaceResponse);
                System.out.println(resultData);
                PrintUtil.printJson(response,resultData);
            }
        }catch (Exception e){
            System.out.println("人脸检测百度接口出错了"+e.getMessage());
            BdFaceResponse bdFaceResponse = new BdFaceResponse();
            bdFaceResponse.setCode(BDConstant.BD_ERROR.getCode().toString());
            bdFaceResponse.setMsg(BDConstant.BD_ERROR.getMsg());
            resultData = JSON.toJSONString(bdFaceResponse);
            System.out.println(resultData);
            PrintUtil.printJson(response,resultData);
        }
        return null;
    }
    //组装数据
    private FaceBdDO getFaceBdDO(FaceBDJSON faceBDJSON,String imageBase64) {
        DecimalFormat df = new DecimalFormat("######0.00");
        FaceBdDO bdDO = new FaceBdDO();
        bdDO.setLogId(faceBDJSON.getLog_id());
        bdDO.setResultNum(faceBDJSON.getResult_num());
        bdDO.setAge(df.format(faceBDJSON.getResult().get(0).getAge()));
        bdDO.setBeauty(df.format(faceBDJSON.getResult().get(0).getBeauty()));
        bdDO.setLeft(""+faceBDJSON.getResult().get(0).getLocation().getLeft());
        bdDO.setTop(""+faceBDJSON.getResult().get(0).getLocation().getTop());
        bdDO.setWidth(""+faceBDJSON.getResult().get(0).getLocation().getWidth());
        bdDO.setHeight(""+faceBDJSON.getResult().get(0).getLocation().getWidth());
        bdDO.setFaceProbability(df.format(faceBDJSON.getResult().get(0).getFace_probability()));
        bdDO.setRotationAngle(""+faceBDJSON.getResult().get(0).getRotation_angle());
        bdDO.setYaw(df.format(faceBDJSON.getResult().get(0).getYaw()));
        bdDO.setPitch(df.format(faceBDJSON.getResult().get(0).getPitch()));
        bdDO.setRoll(df.format(faceBDJSON.getResult().get(0).getRoll()));
        bdDO.setExpression(""+faceBDJSON.getResult().get(0).getExpression());
        bdDO.setExpressionProbability(df.format(faceBDJSON.getResult().get(0).getExpression_probability()));
        bdDO.setType(faceBDJSON.getResult().get(0).getFaceshape().get(0).getType());
        bdDO.setProbability(df.format(faceBDJSON.getResult().get(0).getFaceshape().get(0).getProbability()));
        bdDO.setGender(faceBDJSON.getResult().get(0).getGender());
        bdDO.setGenderProbability(df.format(faceBDJSON.getResult().get(0).getGender_probability()));
        bdDO.setGlasses(""+faceBDJSON.getResult().get(0).getGlasses());
        bdDO.setGlassesProbability(df.format(faceBDJSON.getResult().get(0).getGlasses_probability()));
        bdDO.setRace(faceBDJSON.getResult().get(0).getRace());
        bdDO.setRaceProbability(df.format(faceBDJSON.getResult().get(0).getRace_probability()));
        bdDO.setLeftEye(df.format(faceBDJSON.getResult().get(0).getQualities().getOcclusion().getLeft_eye()));
        bdDO.setRightEye(df.format(faceBDJSON.getResult().get(0).getQualities().getOcclusion().getRight_eye()));
        bdDO.setNose(df.format(faceBDJSON.getResult().get(0).getQualities().getOcclusion().getNose()));
        bdDO.setMouth(df.format(faceBDJSON.getResult().get(0).getQualities().getOcclusion().getMouth()));
        bdDO.setLeftCheek(df.format(faceBDJSON.getResult().get(0).getQualities().getOcclusion().getLeft_cheek()));
        bdDO.setRightCheek(df.format(faceBDJSON.getResult().get(0).getQualities().getOcclusion().getRight_cheek()));
        bdDO.setChin(df.format(faceBDJSON.getResult().get(0).getQualities().getOcclusion().getChin()));
        bdDO.setBlur(df.format(faceBDJSON.getResult().get(0).getQualities().getBlur()));
        bdDO.setIllumination(""+faceBDJSON.getResult().get(0).getQualities().getIllumination());
        bdDO.setCompleteness(""+faceBDJSON.getResult().get(0).getQualities().getCompleteness());
        bdDO.setHuman(df.format(faceBDJSON.getResult().get(0).getQualities().getType().getHuman()));
        bdDO.setCartoon(df.format(faceBDJSON.getResult().get(0).getQualities().getType().getCartoon()));
        bdDO.setImageBase64(imageBase64);
        return bdDO;
    }

}
