package vn.devpro.finaljavaweb26.service;

import org.springframework.stereotype.Service;
import vn.devpro.finaljavaweb26.dto.Jw26Constants;
import vn.devpro.finaljavaweb26.model.SaleOrderProduct;

import java.util.List;

@Service
public class SaleOrderDetailService extends BaseService<SaleOrderProduct> implements Jw26Constants {
    @Override
    public Class<SaleOrderProduct> clazz() {
        return SaleOrderProduct.class;
    }
    public List<SaleOrderProduct> getOrderDetailsByOrderId(int orderId){
        String sql = "SELECT * FROM tbl_sale_order_product WHERE sale_order_id = " + orderId;
        return super.executeNativeSql(sql);
    }
}
