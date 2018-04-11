package com.xai.baiduai.manager.face;

import com.xai.baiduai.manager.domain.FaceBdDO;
import com.xai.baiduai.manager.service.FaceBDService;
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
 * 百度人脸检测管理层Controller
 *
 * @author 小帅丶
 * @create 2018-01-09 16:17
 **/
@Controller
@RequestMapping(value = "/manage/facebd")
public class FaceBDManagerController extends BaseController{
    String prefix = "baiduai/face";
    @Autowired
    FaceBDService faceBDService;
    @RequiresPermissions("baiduai:face:face")
    @GetMapping()
    String face(){
        return prefix+"/face";
    }

    @RequiresPermissions("baiduai:face:face")
    @GetMapping("/list")
    @ResponseBody()
    List<FaceBdDO> list(){
        List<FaceBdDO> faceBdDOList = faceBDService.list();
        FaceBdDO faceBdDO = faceBdDOList.get(0);
        System.out.print(faceBdDO.getImageBase64());
        return faceBdDOList;
    }
    @RequiresPermissions("baiduai:face:batchRemove")
    @Log("批量删除用户")
    @PostMapping("/batchRemove")
    @ResponseBody
    R batchRemove(@RequestParam("ids[]") Long[] faceIds) {
        if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
            return R.error(1, "演示系统测试账户不允许修改,完整体验请部署程序");
        }
        int r = faceBDService.batchremove(faceIds);
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
        if (faceBDService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }
}
