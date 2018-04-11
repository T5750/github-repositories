package com.xai.baiduai.manager.ocr;

import com.xai.baiduai.manager.domain.OCRBdDO;
import com.xai.baiduai.manager.service.OCRBDService;
import com.xai.common.annotation.Log;
import com.xai.common.config.Constant;
import com.xai.common.controller.BaseController;
import com.xai.common.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 图像识别管理模块
 *
 * @author 小帅丶
 * @create 2018-02-07 15:06
 **/
@RequestMapping(value = "/manage/ocrbd")
@Controller
public class OCRBDManagerController extends BaseController{
    String prefix = "baiduai/ocr";
    @Autowired
    OCRBDService ocrbdService;
    @RequiresPermissions("baiduai:ocr:ocr")
    @GetMapping()
    String face(){
        return prefix+"/ocr";
    }
    @RequiresPermissions("baiduai:ocr:ocr")
    @GetMapping("/list")
    @ResponseBody()
    List<OCRBdDO> list(){
        List<OCRBdDO> ocrBdDOList = ocrbdService.list();
        return ocrBdDOList;
    }
    @RequiresPermissions("baiduai:ocr:batchRemove")
    @Log("批量删除用户")
    @PostMapping("/batchRemove")
    @ResponseBody
    R batchRemove(@RequestParam("ids[]") Long[] icrIds) {
        if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
            return R.error(1, "演示系统测试账户不允许修改,完整体验请部署程序");
        }
        int r = ocrbdService.batchremove(icrIds);
        if (r > 0) {
            return R.ok();
        }
        return R.error();
    }
    /**
     * 删除内容
     * @param id
     * @return
     */
    @RequestMapping(value = "/remove")
    @ResponseBody
    R reomve(Long id){
        if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
            return R.error(1, "演示系统测试账户不允许修改,完整体验请部署程序");
        }
        if (ocrbdService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }
}
