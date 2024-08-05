package vn.devpro.finaljavaweb26.service;

import org.springframework.stereotype.Service;
import vn.devpro.finaljavaweb26.dto.Jw26Constants;
import vn.devpro.finaljavaweb26.model.ProductImage;

@Service
public class ProductImageService extends BaseService<ProductImage> implements Jw26Constants {
    @Override
    public Class<ProductImage> clazz() {
        return ProductImage.class;
    }
}
