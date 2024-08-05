package vn.devpro.finaljavaweb26.controller.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.devpro.finaljavaweb26.controller.BaseController;
import vn.devpro.finaljavaweb26.model.Role;
import vn.devpro.finaljavaweb26.model.User;
import vn.devpro.finaljavaweb26.service.RoleService;
import vn.devpro.finaljavaweb26.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class AdminUserController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @RequestMapping(value = "/admin/user-list", method = RequestMethod.GET)
    public String userList(final Model model) throws IOException {
        List<User> users = userService.findAll();
        model.addAttribute("users",users);
        return "backend/user-list";
    }

    @RequestMapping(value = "/admin/user-add", method = RequestMethod.GET)
    public String userAdd(final Model model) throws IOException{
        List<User> users = userService.findAll();
        model.addAttribute("users",users);

        List<Role> roles = roleService.findAll();
        model.addAttribute("roles",roles);

        User user = new User();
        user.setCreateDate(new Date());
        model.addAttribute("user",user);
        return "backend/user-add";
    }

    @RequestMapping(value = "/admin/user-add-save", method = RequestMethod.POST)
    public String userAddSave(final Model model,
                              final HttpServletRequest request,
                              @ModelAttribute("user") User user ) throws IOException{
        if (!StringUtils.isEmpty(request.getParameter("role"))){
            int roleId = Integer.parseInt(request.getParameter("role"));
            //lấy role từ db
            Role role = roleService.getById(roleId);
            //lưu userId và roleId
            user.addRelationalUserRole(role);
            //lưu user vào db
            userService.saveOrUpdate(user);
        }
        //khong chon role thì ko lưu user
        return "redirect:/admin/user-list";
    }

    @RequestMapping(value = "/admin/user-edit/{userId}", method = RequestMethod.GET)
    public String userEdit(final Model model,
                           @PathVariable("userId") Integer userId) throws IOException{

        User user = userService.getById(userId);
        model.addAttribute("user",user);


        return "backend/user-edit";
    }

    @RequestMapping(value = "/admin/user-edit-save", method = RequestMethod.POST)
    public String userEditSave(final Model model,
                               final HttpServletRequest request,
                               @ModelAttribute("user") User user ) throws IOException{

        userService.saveOrUpdate(user);

        //khong chon role thì ko lưu user
        return "redirect:/admin/user-list";
    }
}
