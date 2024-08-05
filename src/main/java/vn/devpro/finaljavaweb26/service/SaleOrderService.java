package vn.devpro.finaljavaweb26.service;

import org.springframework.stereotype.Service;
import vn.devpro.finaljavaweb26.dto.Jw26Constants;
import vn.devpro.finaljavaweb26.model.SaleOrder;

import javax.transaction.Transactional;

@Service
public class SaleOrderService extends BaseService<SaleOrder> implements Jw26Constants {
    @Override
    public Class<SaleOrder> clazz() {
        return SaleOrder.class;
    }

    @Transactional
    public SaleOrder saveOrder(SaleOrder saleOrder) {
        return super.saveOrUpdate(saleOrder);
    }
}
