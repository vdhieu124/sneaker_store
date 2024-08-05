package vn.devpro.finaljavaweb26.controller.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.devpro.finaljavaweb26.controller.BaseController;
import vn.devpro.finaljavaweb26.model.SaleOrder;
import vn.devpro.finaljavaweb26.model.SaleOrderProduct;
import vn.devpro.finaljavaweb26.service.SaleOrderDetailService;
import vn.devpro.finaljavaweb26.service.SaleOrderService;

import java.io.IOException;
import java.util.List;

@Controller
public class AdminOrderController extends BaseController {
    @Autowired
    private SaleOrderService saleOrderService;
    @Autowired
    private SaleOrderDetailService saleOrderDetailService;

    @RequestMapping(value = "/admin/order-list", method = RequestMethod.GET)
    public String orderList(final Model model) throws IOException {
        List<SaleOrder> saleOrders = saleOrderService.findAll();
        model.addAttribute("saleOrders", saleOrders);
        return "backend/order-list";
    }
    @GetMapping("admin/order-details/{orderId}")
    public String getOrderDetails(@PathVariable("orderId") int orderId, Model model) {
        // Retrieve the Order by its ID
        SaleOrder order = saleOrderService.getById(orderId);

        // Retrieve the list of OrderDetails for the Order
        List<SaleOrderProduct> orderDetails = saleOrderDetailService.getOrderDetailsByOrderId(orderId);

        model.addAttribute("order", order);
        model.addAttribute("orderDetails", orderDetails);

        return "backend/order-details"; // Return the JSP page to display the Order details
    }
}
