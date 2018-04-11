package com.xai.activiti.controller;

import com.xai.activiti.domain.SalaryDO;
import com.xai.activiti.service.SalaryService;
import com.xai.activiti.utils.ActivitiUtils;
import com.xai.common.utils.PageUtils;
import com.xai.common.utils.Query;
import com.xai.common.utils.R;
import com.xai.common.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 审批流程测试表
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-11-25 13:33:16
 */

@Controller
@RequestMapping("/act/salary")
public class SalaryController {
    @Autowired
    private SalaryService salaryService;
    @Autowired
    ActivitiUtils activitiUtils;

    @GetMapping()
        //@RequiresPermissions("activiti:salary:salary")
    String Salary() {
        return "activiti/salary/salary";
    }

    @ResponseBody
    @GetMapping("/list")
    //@RequiresPermissions("activiti:salary:salary")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<SalaryDO> salaryList = salaryService.list(query);
        int total = salaryService.count(query);
        PageUtils pageUtils = new PageUtils(salaryList, total);
        return pageUtils;
    }

    @GetMapping("/form")
        //@RequiresPermissions("activiti:salary:add")
    String add() {

        return "act/salary/add";
    }

    @GetMapping("/form/{taskId}")
        //@RequiresPermissions("activiti:salary:edit")
    String edit(@PathVariable("taskId") String taskId, Model model) {
        SalaryDO salary = salaryService.get(activitiUtils.getBusinessKeyByTaskId(taskId));
        salary.setTaskId(taskId);
        model.addAttribute("salary", salary);
        return "act/salary/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    //@RequiresPermissions("activiti:salary:add")
    public R saveOrUpdate(SalaryDO salary) {
        salary.setCreateDate(new Date());
        salary.setUpdateDate(new Date());
        salary.setCreateBy(ShiroUtils.getUserId().toString());
        salary.setUpdateBy(ShiroUtils.getUserId().toString());
        salary.setDelFlag("1");
        if (salaryService.save(salary) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    //@RequiresPermissions("activiti:salary:edit")
    public R update(SalaryDO salary) {
        String taskKey = activitiUtils.getTaskByTaskId(salary.getTaskId()).getTaskDefinitionKey();
        if ("audit2".equals(taskKey)) {
            salary.setHrText(salary.getTaskComment());
        } else if ("audit3".equals(taskKey)) {
            salary.setLeadText(salary.getTaskComment());
        } else if ("audit4".equals(taskKey)) {
            salary.setMainLeadText(salary.getTaskComment());
        } else if("apply_end".equals(salary.getTaskComment())){
            //流程完成，兑现
        }
        salaryService.update(salary);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    //@RequiresPermissions("activiti:salary:remove")
    public R remove(String id) {
        if (salaryService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    //@RequiresPermissions("activiti:salary:batchRemove")
    public R remove(@RequestParam("ids[]") String[] ids) {
        salaryService.batchRemove(ids);
        return R.ok();
    }

}
