package vn.devpro.finaljavaweb26.controller.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.devpro.finaljavaweb26.controller.BaseController;
import vn.devpro.finaljavaweb26.dto.Cart;
import vn.devpro.finaljavaweb26.dto.CartProduct;
import vn.devpro.finaljavaweb26.dto.Customer;
import vn.devpro.finaljavaweb26.dto.Jw26Constants;
import vn.devpro.finaljavaweb26.model.Product;
import vn.devpro.finaljavaweb26.model.SaleOrder;
import vn.devpro.finaljavaweb26.model.SaleOrderProduct;
import vn.devpro.finaljavaweb26.model.User;
import vn.devpro.finaljavaweb26.service.ProductService;
import vn.devpro.finaljavaweb26.service.SaleOrderService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CartController extends BaseController implements Jw26Constants {
    @Autowired
    private ProductService productService;
    @Autowired
    private SaleOrderService saleOrderService;

    @Autowired
    private JavaMailSender mailSender;

    @RequestMapping(value = "/add-to-cart", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> addToCart(final Model model,
                                                         final HttpServletRequest request,
                                                         final HttpServletResponse response,
                                                         @RequestBody CartProduct addedProduct) throws IOException{
        //lay session
        HttpSession session = request.getSession();

        Cart cart = null;
        //kiem tra xem co gio hang chua
        if (session.getAttribute("cart") != null) {
            cart = (Cart) session.getAttribute("cart");

        } else {
            cart = new Cart();
            session.setAttribute("cart",cart);
        }

        //thêm hàng vô giỏ
        //lấy product trong db
        Product dbProduct = productService.getById(addedProduct.getProductId());
        //tạo mới 1 cart product
        int index = cart.findProductById(dbProduct.getId());
        if (index != -1) {
            cart.getCartProducts().get(index).setQuantity(
                    cart.getCartProducts().get(index).getQuantity() +
                            addedProduct.getQuantity());
        } else {
            addedProduct.setAvatar(dbProduct.getAvatar());
            addedProduct.setProductName(dbProduct.getName());
            addedProduct.setPrice(dbProduct.getSalePrice());

            cart.getCartProducts().add(addedProduct);
        }

        //thiet lap bien session cho cart
        session.setAttribute("cart",cart);
        //tra ve tong so san pham
        model.addAttribute("totalCartProducts", cart.totalCartProducts());

        Map<String, Object> jsonResult = new HashMap<String, Object>();
        jsonResult.put("code", 404);
        jsonResult.put("message","Đã thêm sản phẩm '" + dbProduct.getName() + "' vào giỏ hàng!");
        jsonResult.put("totalCartProducts", cart.totalCartProducts());
        return ResponseEntity.ok(jsonResult);
    }

    //    @RequestMapping(value = "/cart-view", method = RequestMethod.GET)
//    public String cartView(final Model model,
//                           final HttpServletRequest request,
//                           final HttpServletResponse response) throws IOException{
//        //lấy giỏ hàng
//        HttpSession session = request.getSession();
//        Cart cart = null;
//        String message = null;
//
//        if (session.getAttribute("cart") != null){
//            cart = (Cart) session.getAttribute("cart");
//            message = "Có " + cart.totalCartProducts() + " trong giỏ hàng";
//            model.addAttribute("totalCartPrice", cart.totalCartPrice());
//        } else {
//            message = "Không có sản phẩm nào trong giỏ hàng!";
//        }
//        model.addAttribute("cart", cart);
//        model.addAttribute("message", message);
//        return "frontend/cart-view";
//    }
    //--------------------DELETE product
    @RequestMapping(value = "/product-cart-delete/{productId}", method = RequestMethod.GET)
    public String productCartDelete(@PathVariable("productId") int productId,
                                    final Model model,
                                    final HttpServletRequest request) throws IOException{
        //lấy giỏ hàng
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        int index = cart.findProductById(productId);
        if (index != -1){
            cart.getCartProducts().remove(index);
        }
        if (cart.totalCartProducts() == 0){
            cart = null;
        }
        session.setAttribute("cart", cart);
        return "redirect:/cart-view";
    }
    //-------------------------------CART Display
    @RequestMapping(value = "/cart-view", method = RequestMethod.GET)
    public String cartView(         final Model model,
                                    final HttpServletRequest request) throws IOException{
        //lấy giỏ hàng
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        String message = null;
        String errorMessage = null;
        if (cart != null) {
            model.addAttribute("totalCartProducts", cart.totalCartProducts());
            model.addAttribute("totalCartPrice", cart.totalCartPrice());
            message = "Có tổng " + cart.totalCartProducts() + " sản phẩm trong giỏ hàng";
            model.addAttribute("totalCartProducts", cart.totalCartProducts());
        } else {
            errorMessage = "Không có sản phẩm nào trong giỏ hàng!";
        }
        model.addAttribute("message", message);
        model.addAttribute("errorMessage", errorMessage);

        if (isLogined()){
            model.addAttribute("user", getLoginedUser());
        } else {
            model.addAttribute("user", new User());
        }
        return "frontend/cart-view";
    }
    //-----------------change quantity of item
    @RequestMapping(value = "/update-product-quantity", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> updateProductQuantity(final Model model,
                                                                     final HttpServletRequest request,
                                                                     final HttpServletResponse response,
                                                                     @RequestBody CartProduct updateProduct) throws IOException{
        //lấy giỏ hàng
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        int index = cart.findProductById(updateProduct.getProductId());
        Integer newQuantity = cart.getCartProducts().get(index).getQuantity() + updateProduct.getQuantity();
        if (newQuantity.intValue() < 1){
            newQuantity = 1;
        }
        cart.getCartProducts().get(index).setQuantity(newQuantity);

        Map<String, Object> jsonResult = new HashMap<String, Object>();
        jsonResult.put("code", 200);
        jsonResult.put("status","Success");
        jsonResult.put("newQuantity", newQuantity);

        return ResponseEntity.ok(jsonResult);
    }

    //----------------------------PLACE ORDER
    @RequestMapping(value = "/place-order", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> placeOrder(final Model model,
                                                          final HttpServletRequest request,
                                                          final HttpServletResponse response,
                                                          @RequestBody Customer customer) throws IOException, MessagingException {
        Map<String, Object> jsonResult = new HashMap<String, Object>();

        //kiem tra thong tin bat buoc
        if (customer.getTxtName().isEmpty()){
            jsonResult.put("code", 100);
            jsonResult.put("message", "Ban chua nhap ho ten");
        } else if (customer.getTxtMobile().isEmpty()) {
            jsonResult.put("code", 101);
            jsonResult.put("message", "Ban chua nhap so dien thoai");
        } else if (customer.getTxtEmail().isEmpty()) {
            jsonResult.put("code", 101);
            jsonResult.put("message", "Ban chua nhap email");
        } else {
            HttpSession session = request.getSession();
            if(session.getAttribute("cart") != null){
                Cart cart = (Cart) session.getAttribute("cart");
                if (cart.totalCartProducts().intValue() > 0){
                    //tao don hang
                    SaleOrder saleOrder = new SaleOrder();
                    saleOrder.setCustomerName(customer.getTxtName());
                    saleOrder.setCustomerMobile(customer.getTxtMobile());
                    saleOrder.setCustomerAddress(customer.getTxtAddress());
                    saleOrder.setCustomerEmail(customer.getTxtEmail());
                    saleOrder.setTotal(cart.totalCartPrice());
                    saleOrder.setCode(customer.getTxtMobile());
                    saleOrder.setCreateDate(new Date());
                    saleOrder.setStatus(false);

                    //set moi quan he voi user
                    User user = new User();
                    user.setId(1);
                    saleOrder.setUser(user);
                    //Duyet danh sach sp trong gio hang va luu tbl_sale_order-product
                    for (CartProduct cartProduct : cart.getCartProducts()){
                        SaleOrderProduct saleOrderProduct = new SaleOrderProduct();
                        saleOrderProduct.setQuantity(cartProduct.getQuantity().intValue());
                        Product product = productService.getById(cartProduct.getProductId());
                        saleOrderProduct.setProduct(product);
                        saleOrder.addRelationalSaleOrderProduct(saleOrderProduct);
                    }
                    //luu don hang vao tbl_sale_order
                    saleOrderService.saveOrder(saleOrder);
                    //send mail
                    sendConfirmationEmail(customer.getTxtEmail(), customer.getTxtName(), customer.getTxtMobile());
                    //xoa gio hang sau khi luu thanh cong
                    cart = new Cart();
                    session.setAttribute("cart", null);
                    jsonResult.put("code", 200);
                    jsonResult.put("message", "Ban da dat hang thanh cong!");

                    session.setAttribute("checkout", true);
                }
                else {
                    jsonResult.put("code", 103);
                    jsonResult.put("message", "Co loi duong truyen Internet");
                }
            }
            else {
                jsonResult.put("code", 104);
                jsonResult.put("message", "Co loi duong truyen Internet");
            }
        }
        return ResponseEntity.ok(jsonResult);
    }

    public void sendConfirmationEmail(String recipientEmail, String customerName, String orderNumber) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
        helper.setTo(recipientEmail);
        helper.setSubject("Xác nhận đơn hàng #" + orderNumber);
        helper.setText(getConfirmationEmailContent(customerName, orderNumber), true);
        mailSender.send(message);
        System.out.println("Email sent successfully!");
    }
    private String getConfirmationEmailContent(String customerName, String orderNumber) {
        String content = "<html><body style=\"font-family: Arial, sans-serif;\">"
                + "<h2>Xác nhận đơn hàng</h2>"
                + "<p>Kính gửi anh/chị <b>" + customerName + "</b>,</p>"
                + "<p>Cảm ơn anh/chị đã đặt hàng thành công. Đơn hàng của anh/chị có số <b>#" + orderNumber + "</b> đã được tiếp nhận.</p>"
                + "<p>Chúng tôi sẽ tiến hành xử lý đơn hàng và sẽ thông báo cho anh/chị khi đơn hàng được gửi đi.</p>"
                + "<p>Cảm ơn anh/chị đã tin tưởng và mua hàng từ chúng tôi.</p>"
                + "<p>Trân trọng,<br>Đội ngũ <b style=\"color: red; font-size: 14px; \">HIEU Sneaker</b>.</p>"
                + "<img src=\"https://scontent.fhan20-1.fna.fbcdn.net/v/t39.30808-6/405411880_1464590641118556_7534509131641889411_n.jpg?_nc_cat=101&ccb=1-7&_nc_sid=3635dc&_nc_ohc=Ie2X5bvLVQ4AX_PsS3C&_nc_ht=scontent.fhan20-1.fna&oh=00_AfBZv5u5yAELT1s6p32_57qNeKh50hA-tzY44eVBSmzMvQ&oe=6570C39D\">"
                + "</body></html>";

        return content;
    }
}
