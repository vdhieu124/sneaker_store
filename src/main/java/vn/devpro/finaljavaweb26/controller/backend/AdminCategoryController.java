package vn.devpro.finaljavaweb26.controller.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.devpro.finaljavaweb26.controller.BaseController;
import vn.devpro.finaljavaweb26.model.Category;
import vn.devpro.finaljavaweb26.model.User;
import vn.devpro.finaljavaweb26.service.CategoryService;
import vn.devpro.finaljavaweb26.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class AdminCategoryController extends BaseController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/admin/category-list", method = RequestMethod.GET)
    public String categoryList(final Model model) throws IOException{
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "backend/category-list";
    }
    @RequestMapping(value = "/admin/category-add", method = RequestMethod.GET)
    public String categoryAdd(final Model model) throws IOException{
        List<User> users = userService.findAll();
        model.addAttribute("users", users);

        Category category = new Category();
        category.setCreateDate(new Date());
        model.addAttribute("category", category);
        return "backend/category-add";
    }
    @RequestMapping(value = "/admin/category-add-save", method = RequestMethod.POST)
    public String categoryAddSave(final Model model,
                                  final HttpServletRequest request,
                                  @ModelAttribute("category") Category category) throws IOException{

        categoryService.saveOrUpdate(category);

        return "redirect:/admin/category-list";
    }

    @RequestMapping(value = "admin/category-edit/{categoryId}", method = RequestMethod.GET)
    public String categoryEdit(final Model model,
                               @PathVariable("categoryId") Integer categoryId) throws IOException{
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        Category category = categoryService.getById(categoryId);
        model.addAttribute("category", category);

        return "backend/category-edit";
    }
    @RequestMapping(value = "/admin/category-edit-save", method = RequestMethod.POST)
    public String categoryEditSave(final Model model,
                                   final HttpServletRequest request,
                                   @ModelAttribute("category") Category category) throws IOException{
        categoryService.saveOrUpdate(category);
        return "redirect:/admin/category-list";
    }
}
