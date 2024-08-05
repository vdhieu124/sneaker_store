package vn.devpro.finaljavaweb26.service;

import org.springframework.stereotype.Service;
import vn.devpro.finaljavaweb26.model.Category;

@Service
public class CategoryService extends BaseService<Category> {
    @Override
    public Class<Category> clazz() {
        return Category.class;
    }
}
