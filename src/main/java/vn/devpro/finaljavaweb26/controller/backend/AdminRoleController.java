package vn.devpro.finaljavaweb26.controller.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.devpro.finaljavaweb26.controller.BaseController;
import vn.devpro.finaljavaweb26.model.Role;
import vn.devpro.finaljavaweb26.service.RoleService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class AdminRoleController extends BaseController {
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/admin/role-list", method = RequestMethod.GET)
    public String roleList(final Model model) throws IOException {
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        return "backend/role-list";
    }

    @RequestMapping(value = "/admin/role-add", method = RequestMethod.GET)
    public String roleAdd(final Model model) throws IOException{

        Role role = new Role();
        role.setCreateDate(new Date());
        model.addAttribute("role", role);
        return "backend/role-add";
    }
    @RequestMapping(value = "/admin/role-add-save", method = RequestMethod.POST)
    public String roleAddSave(final Model model,
                                 final HttpServletRequest request,
                                 @ModelAttribute("role") Role role) throws IOException{

        roleService.saveOrUpdate(role);

        return "redirect:/admin/role-list";
    }

    @RequestMapping(value = "admin/role-edit/{roleId}", method = RequestMethod.GET)
    public String roleEdit(final Model model,
                              @PathVariable("roleId") Integer roleId) throws IOException{

        Role role = roleService.getById(roleId);
        model.addAttribute("role", role);

        return "backend/role-edit";
    }
    @RequestMapping(value = "/admin/role-edit-save", method = RequestMethod.POST)
    public String roleEditSave(final Model model,
                                  final HttpServletRequest request,
                                  @ModelAttribute("role") Role role) throws IOException{
        roleService.saveOrUpdate(role);
        return "redirect:/admin/role-list";
    }
}
