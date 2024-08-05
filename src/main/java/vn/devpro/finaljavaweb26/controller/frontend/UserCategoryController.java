package vn.devpro.finaljavaweb26.controller.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.devpro.finaljavaweb26.controller.BaseController;
import vn.devpro.finaljavaweb26.dto.Jw26Constants;
import vn.devpro.finaljavaweb26.model.Product;
import vn.devpro.finaljavaweb26.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class UserCategoryController extends BaseController implements Jw26Constants {
    @Autowired
    private ProductService productService;
    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public String index(final Model model,
                        final HttpServletRequest request,
                        final HttpServletResponse response) throws IOException {

        List<Product> products = productService.findAllActive();
        model.addAttribute("products", products);
        model.addAttribute("totalProducts", products.size());
        return "frontend/category/category";
    }
}
