package vn.devpro.finaljavaweb26.controller.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.devpro.finaljavaweb26.controller.BaseController;
import vn.devpro.finaljavaweb26.dto.Jw26Constants;
import vn.devpro.finaljavaweb26.dto.SearchModel;
import vn.devpro.finaljavaweb26.model.Category;
import vn.devpro.finaljavaweb26.model.Product;
import vn.devpro.finaljavaweb26.model.User;
import vn.devpro.finaljavaweb26.service.CategoryService;
import vn.devpro.finaljavaweb26.service.ProductService;
import vn.devpro.finaljavaweb26.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class AdminProductController extends BaseController implements Jw26Constants {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @RequestMapping(value = "/admin/product-list", method = RequestMethod.GET)
    public String productList(final Model model,
                              final HttpServletRequest request) throws IOException{
        //List<Product> products = productService.findAll();
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);

        SearchModel productSearch = new SearchModel();

        //tim voi tieu chi status
        productSearch.setStatus(2);
        if(!StringUtils.isEmpty(request.getParameter("status"))){
            productSearch.setStatus(Integer.parseInt(request.getParameter("status")));
        }
        //tim voi tieu chi category
        productSearch.setCategoryId(0);
        if(!StringUtils.isEmpty(request.getParameter("categoryId"))){
            productSearch.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
        }
        //tim voi tieu chi keyword
        productSearch.setKeyword(request.getParameter("keyword"));
        //tim voi tieu chi fromdate to date
        productSearch.setBeginDate(request.getParameter("beginDate"));
        productSearch.setEndDate(request.getParameter("endDate"));

        //int totalPages = productSearch.getTotalItems() / productSearch.getSizeOfPage();
        if(!StringUtils.isEmpty(request.getParameter("page"))){
            productSearch.setCurrentPage(Integer.parseInt(request.getParameter("page")));
        } else {
            productSearch.setCurrentPage(1);
        }

        List<Product> allProducts = productService.searchProduct(productSearch);


        List <Product> products = new ArrayList<Product>();

        int totalPages = allProducts.size() / SIZE_OF_PAGE;
        if (allProducts.size() % SIZE_OF_PAGE > 0){
            totalPages++;
        }
        if (totalPages < productSearch.getCurrentPage()){
            productSearch.setCurrentPage(1);
        }

        int firstIndex = (productSearch.getCurrentPage() - 1)* SIZE_OF_PAGE;
        int index = firstIndex, count = 0;
        while (index < allProducts.size() && count < SIZE_OF_PAGE){
            products.add(allProducts.get(index));
            index++;
            count++;
        }

        //phân trang

        productSearch.setSizeOfPage(SIZE_OF_PAGE);
        productSearch.setTotalItems(allProducts.size());

        model.addAttribute("products", products);
        model.addAttribute("productSearch", productSearch);
        return "backend/product-list";
    }

    @RequestMapping(value = "/admin/product-add", method = RequestMethod.GET)
    public String productAdd(final Model model) throws IOException{
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);

        Product product = new Product();
        product.setCreateDate(new Date());
        model.addAttribute("product", product);
        return "backend/product-add";
    }

    @RequestMapping(value = "/admin/product-add-save", method = RequestMethod.POST)
    public String productAddSave(final Model model,
                                 final HttpServletRequest request,
                                 @ModelAttribute("product") Product product,
                                 @RequestParam("avatarFile") MultipartFile avatarFile,
                                 @RequestParam("imageFiles") MultipartFile[] imageFiles) throws IOException{

        productService.saveAddProduct(product,avatarFile,imageFiles);

        return "redirect:/admin/product-list";
    }

    @RequestMapping(value = "admin/product-edit/{productId}", method = RequestMethod.GET)
    public String productEdit(final Model model,
                               @PathVariable("productId") Integer productId) throws IOException{
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        Product product = productService.getById(productId);
        model.addAttribute("product", product);

        return "backend/product-edit";
    }
    @RequestMapping(value = "/admin/product-edit-save", method = RequestMethod.POST)
    public String productEditSave(final Model model,
                                  final HttpServletRequest request,
                                  @ModelAttribute("product") Product product,
                                  @RequestParam("avatarFile") MultipartFile avatarFile,
                                  @RequestParam("imageFiles") MultipartFile[] imageFiles) throws IOException{
        productService.saveEditProduct(product, avatarFile, imageFiles);
        return "redirect:/admin/product-list";
    }

    //    //    delete
//    @RequestMapping(value = "/admin/product-delete/{productId}", method = RequestMethod.GET)
//    public String productDelete (final Model model,
//                              @PathVariable("productId") int productId) throws IOException
//    {
//
//        productService.deleteProductById(productId);
//        return "redirect:/admin/product-list";
//    }
    //    unactive
    @RequestMapping(value = "/admin/product-delete/{productId}", method = RequestMethod.GET)
    public String productDelete (final Model model,
                                 @PathVariable("productId") int productId) throws IOException{
        Product product = productService.getById(productId);
        product.setStatus(Boolean.FALSE);
        productService.saveOrUpdate(product);
        return "redirect:/admin/product-list";
    }
}
