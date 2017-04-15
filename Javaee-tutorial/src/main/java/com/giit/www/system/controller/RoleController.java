package com.giit.www.system.controller;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.giit.www.entity.Role;
import com.giit.www.system.service.ResourceBiz;
import com.giit.www.system.service.RoleBiz;

/**
 * Created by c0de8ug on 16-2-21.
 */
@Controller
@RequestMapping("/role.do")
public class RoleController {
	@Resource(name = "roleBizImpl")
	private RoleBiz roleBiz;
	@Resource(name = "resourceBizImpl")
	private ResourceBiz resourceBiz;

	@RequiresRoles("admin")
	@RequestMapping("role.view")
	public String roleView(Model model) {
		model.addAttribute("roleList", roleBiz.findAll());
		return "/admin/system/role/role";
	}

	@RequiresRoles("admin")
	@RequestMapping("role_add.view")
	public String roleAddView(Model model) {
		model.addAttribute("roleList", roleBiz.findAll());
		return "/admin/system/role/role_add";
	}

	@RequiresRoles("admin")
	@RequestMapping("add")
	public String add(Role role, RedirectAttributes redirectAttributes) {
		roleBiz.createRole(role);
		return "redirect:/role.do/role.view";
	}

	@RequiresRoles("admin")
	@RequestMapping("delete")
	public String delete(Long id, RedirectAttributes redirectAttributes) {
		roleBiz.deleteRole(id);
		return "redirect:/role.do/role.view";
	}
	//
	// @RequiresPermissions("role:create")
	// @RequestMapping(value = "/create", method = RequestMethod.GET)
	// public String showCreateForm(Model model) {
	// setCommonData(model);
	// model.addAttribute("role", new Role());
	// model.addAttribute("op", "新增");
	// return "role/edit";
	// }
	//
	// @RequiresPermissions("role:update")
	// @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
	// public String showUpdateForm(@PathVariable("id") Long id, Model model) {
	// setCommonData(model);
	// model.addAttribute("role", roleBiz.findOne(id));
	// model.addAttribute("op", "修改");
	// return "role/edit";
	// }
	//
	// @RequiresPermissions("role:update")
	// @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
	// public String update(Role role, RedirectAttributes redirectAttributes) {
	// roleBiz.updateRole(role);
	// redirectAttributes.addFlashAttribute("msg", "修改成功");
	// return "redirect:/role";
	// }
	//
	// @RequiresPermissions("role:delete")
	// @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	// public String showDeleteForm(@PathVariable("id") Long id, Model model) {
	// setCommonData(model);
	// model.addAttribute("role", roleBiz.findOne(id));
	// model.addAttribute("op", "删除");
	// return "role/edit";
	// }
	// private void setCommonData(Model model) {
	// model.addAttribute("resourceList", roleBiz.findAll());
	// }
}
