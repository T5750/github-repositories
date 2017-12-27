/**
 * 
 */
package org.ibase4j.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.model.SysUser;
import org.ibase4j.provider.ISysProvider;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import top.ibase4j.core.base.AbstractController;
import top.ibase4j.core.base.Parameter;
import top.ibase4j.core.support.Assert;
import top.ibase4j.core.support.HttpCode;
import top.ibase4j.core.util.SecurityUtil;
import top.ibase4j.core.util.UploadUtil;

/**
 * 用户管理控制器
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:12:12
 */
@RestController
@Api(value = "用户管理", description = "用户管理")
@RequestMapping(value = "/user")
public class SysUserController extends AbstractController<ISysProvider> {
    public String getService() {
        return "sysUserService";
    }

    @PostMapping
    @ApiOperation(value = "修改用户信息")
    @RequiresPermissions("sys.base.user.update")
    public Object update(ModelMap modelMap, @RequestBody SysUser param) {
        Assert.isNotBlank(param.getAccount(), "ACCOUNT");
        Assert.length(param.getAccount(), 3, 15, "ACCOUNT");
        if (param.getId() != null) {
            if (param.getEnable() == null) {
                param.setEnable(0);
            }
            Parameter parameter = new Parameter(getService(), "queryById", param.getId());
            SysUser user = (SysUser)provider.execute(parameter).getResult();
            Assert.notNull(user, "USER", param.getId());
            if (StringUtils.isNotBlank(param.getPassword())) {
                if (!param.getPassword().equals(user.getPassword())) {
                    param.setPassword(SecurityUtil.encryptPassword(param.getPassword()));
                }
            }
        } else if (StringUtils.isNotBlank(param.getPassword())) {
            param.setPassword(SecurityUtil.encryptPassword(param.getPassword()));
        }
        return super.update(modelMap, param);
    }

    // 查询用户
    @ApiOperation(value = "查询用户")
    @RequiresPermissions("sys.base.user.read")
    @PutMapping(value = "/read/list")
    public Object query(ModelMap modelMap, @RequestBody Map<String, Object> param) {
        return super.query(modelMap, param);
    }

    // 用户详细信息
    @ApiOperation(value = "用户详细信息")
    @RequiresPermissions("sys.base.user.read")
    @PutMapping(value = "/read/detail")
    public Object get(ModelMap modelMap, @RequestBody SysUser param) {
        return super.get(modelMap, param);
    }

    // 用户详细信息
    @ApiOperation(value = "删除用户")
    @RequiresPermissions("sys.base.user.delete")
    @DeleteMapping
    public Object delete(ModelMap modelMap, @RequestBody SysUser param) {
        return super.delete(modelMap, param);
    }

    // 当前用户
    @ApiOperation(value = "当前用户信息")
    @GetMapping(value = "/read/promission")
    public Object promission(ModelMap modelMap) {
        Long id = getCurrUser();
        Parameter parameter = new Parameter(getService(), "queryById", id);
        SysUser sysUser = (SysUser)provider.execute(parameter).getResult();
        modelMap.put("user", sysUser);
        parameter = new Parameter("sysAuthorizeService", "queryAuthorizeByUserId", id);
        logger.info("{} execute queryAuthorizeByUserId start...", parameter.getNo());
        List<?> menus = provider.execute(parameter).getResultList();
        logger.info("{} execute queryAuthorizeByUserId end.", parameter.getNo());
        modelMap.put("menus", menus);
        return setSuccessModelMap(modelMap);
    }

    // 当前用户
    @ApiOperation(value = "当前用户信息")
    @GetMapping(value = "/read/current")
    public Object current(ModelMap modelMap) {
        SysUser param = new SysUser();
        param.setId(getCurrUser());
        return super.get(modelMap, param);
    }

    @ApiOperation(value = "修改个人信息")
    @PostMapping(value = "/update/person")
    public Object updatePerson(ModelMap modelMap, @RequestBody SysUser param) {
        param.setId(getCurrUser());
        param.setPassword(null);
        Assert.isNotBlank(param.getAccount(), "ACCOUNT");
        Assert.length(param.getAccount(), 3, 15, "ACCOUNT");
        return super.update(modelMap, param);
    }

    @ApiOperation(value = "修改用户头像")
    @PostMapping(value = "/upload/avatar")
    public Object updateAvatar(HttpServletRequest request, ModelMap modelMap) {
        List<String> fileNames = UploadUtil.uploadImageData(request);
        if (fileNames.size() > 0) {
            SysUser param = new SysUser();
            param.setId(getCurrUser());
            for (int i = 0; i < fileNames.size(); i++) {
                String filePath = UploadUtil.getUploadDir(request) + fileNames.get(i);
                String avatar = UploadUtil.remove2DFS("sysUser", "U" + param.getId(), filePath).getRemotePath();
                param.setAvatar(avatar);
            }
            modelMap.put("data", param);
            return super.update(modelMap, param);
        } else {
            setModelMap(modelMap, HttpCode.BAD_REQUEST);
            modelMap.put("msg", "请选择要上传的文件！");
            return modelMap;
        }
    }

    // 修改密码
    @ApiOperation(value = "修改密码")
    @PostMapping(value = "/update/password")
    public Object updatePassword(ModelMap modelMap, @RequestBody SysUser param) {
        Assert.isNotBlank(param.getOldPassword(), "OLDPASSWORD");
        Assert.isNotBlank(param.getPassword(), "PASSWORD");
        Long userId = getCurrUser();
        String encryptPassword = SecurityUtil.encryptPassword(param.getOldPassword());
        Parameter parameter = new Parameter(getService(), "queryById", userId);
        logger.info("{} execute queryById start...", parameter.getNo());
        SysUser sysUser = (SysUser)provider.execute(parameter).getResult();
        logger.info("{} execute queryById end.", parameter.getNo());
        Assert.notNull(sysUser, "USER", param.getId());
        if (!sysUser.getPassword().equals(encryptPassword)) {
            throw new UnauthorizedException("原密码错误.");
        }
        sysUser.setPassword(SecurityUtil.encryptPassword(param.getPassword()));
        return super.update(modelMap, sysUser);
    }
}
