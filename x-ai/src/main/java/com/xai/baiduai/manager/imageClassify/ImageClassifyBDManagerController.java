package com.xai.baiduai.manager.imageClassify;

import com.xai.baiduai.manager.domain.ImageClassifyBdDO;
import com.xai.baiduai.manager.service.ImageClassifyBDService;
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
@RequestMapping(value = "/manage/icrbd")
@Controller
public class ImageClassifyBDManagerController extends BaseController{
    String prefix = "baiduai/imageclassify";
    @Autowired
    ImageClassifyBDService imageClassifyBDService;
    @RequiresPermissions("baiduai:imageclassify:imageclassify")
    @GetMapping()
    String face(){
        return prefix+"/imageclassify";
    }
    @RequiresPermissions("baiduai:imageclassify:imageclassify")
    @GetMapping("/list")
    @ResponseBody()
    List<ImageClassifyBdDO> list(){
        List<ImageClassifyBdDO> imageClassifyBdDOList = imageClassifyBDService.list();
        return imageClassifyBdDOList;
    }
    @RequiresPermissions("baiduai:imageclassify:batchRemove")
    @Log("批量删除用户")
    @PostMapping("/batchRemove")
    @ResponseBody
    R batchRemove(@RequestParam("ids[]") Long[] icrIds) {
        if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
            return R.error(1, "演示系统测试账户不允许修改,完整体验请部署程序");
        }
        int r = imageClassifyBDService.batchremove(icrIds);
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
        if (imageClassifyBDService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }
}
