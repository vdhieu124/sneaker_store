package vn.devpro.finaljavaweb26.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import vn.devpro.finaljavaweb26.dto.Jw26Constants;
import vn.devpro.finaljavaweb26.dto.SearchModel;
import vn.devpro.finaljavaweb26.model.Product;
import vn.devpro.finaljavaweb26.model.ProductImage;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class ProductService extends BaseService<Product> implements Jw26Constants {
    @Override
    public Class<Product> clazz() {
        return Product.class;
    }
    public boolean isUploadFile(MultipartFile file){
        if(file == null || file.getOriginalFilename().isEmpty()){
            return false;

        }
        return true;
    }
    public boolean isUploadFiles (MultipartFile[] files){
        if(files == null || files.length ==0){
            return false;
        }
        return true;
    }
    @Transactional
    public Product saveAddProduct(Product product, MultipartFile avatarFile, MultipartFile[] imageFiles) throws IOException {
        if (isUploadFile(avatarFile)){
            String path = FOLDER_UPLOAD + "Product/Avatar/" + avatarFile.getOriginalFilename();
            File file = new File(path);
            avatarFile.transferTo(file);
            product.setAvatar("Product/Avatar/" + avatarFile.getOriginalFilename());
        }
        if (isUploadFiles(imageFiles)){
            for (MultipartFile imageFile: imageFiles){
                if (isUploadFile(imageFile)){
                    String path = FOLDER_UPLOAD + "Product/Image/" + imageFile.getOriginalFilename();
                    File file = new File(path);
                    imageFile.transferTo(file);
                    //lưu
                    ProductImage productImage = new ProductImage();
                    productImage.setTitle(imageFile.getOriginalFilename());
                    productImage.setPath("Product/Image/" + imageFile.getOriginalFilename());
                    productImage.setStatus(Boolean.TRUE);
                    productImage.setCreateDate(new Date());
                    product.addRelationalProductImage(productImage);
                }
            }
        }
        return super.saveOrUpdate(product);
    }

    //    -----------------------------EDIT------------------------------------------------------
    @Transactional
    public Product saveEditProduct(Product product, MultipartFile avatarFile, MultipartFile[] imageFiles) throws IOException {
        //Lay product trong db
        Product dbProduct = super.getById(product.getId());
        //Luu avatar moi neu nguoi dung co upload
        if (isUploadFile(avatarFile)){
            //xoa avatar cu
            String path = FOLDER_UPLOAD + dbProduct.getAvatar();
            File file = new File(path);
            file.delete();
            //luu file avt moi
            path = FOLDER_UPLOAD + "Product/Avatar/" + avatarFile.getOriginalFilename();
            file = new File(path);
            avatarFile.transferTo(file);
            product.setAvatar("Product/Avatar/" + avatarFile.getOriginalFilename());
        }
        else {
            //ko upload anh -> giu nguyen avt cu
            product.setAvatar(dbProduct.getAvatar());
        }
        if (isUploadFiles(imageFiles)){
            for (MultipartFile imageFile: imageFiles){
                if (isUploadFile(imageFile)){
                    String path = FOLDER_UPLOAD + "Product/Image/" + imageFile.getOriginalFilename();
                    File file = new File(path);
                    imageFile.transferTo(file);
                    //lưu
                    ProductImage productImage = new ProductImage();
                    productImage.setTitle(imageFile.getOriginalFilename());
                    productImage.setPath("Product/Image/" + imageFile.getOriginalFilename());
                    productImage.setStatus(Boolean.TRUE);
                    productImage.setCreateDate(new Date());

                    //lưu đối tượng productImage
                    product.addRelationalProductImage(productImage);
                }
            }
        }
        return super.saveOrUpdate(product);
    }

    //Delete product
    @Autowired
    private ProductImageService productImageService;
    @Transactional
    public void deleteProductById(int productId){

        Product product = super.getById(productId);
        //xóa lần lượt ảnh của product trong thư mục lưu trữ và đường dẫn ảnh trong tbl_product-image
        String sql = "select * from tbl_product_image where product_id=" + productId;
        List<ProductImage> productImages = productImageService.executeNativeSql(sql);

        for (ProductImage productImage:productImages){
            String path = FOLDER_UPLOAD + productImage.getPath();
            File file = new File(path);
            file.delete();
            //product.removeRelationalProductImage(productImage);
        }

        String path = FOLDER_UPLOAD + product.getAvatar();
        File file = new File(path);
        file.delete();

        //xoa product
        super.delete(product);
    }

    public List<Product> findAllActive(){
        return super.executeNativeSql("SELECT * from tbl_product WHERE status=1;");
    }
    public List<Product> findAllHot(){
        return super.executeNativeSql("SELECT * from tbl_product WHERE is_hot=1;");
    }

    public List<Product> searchProduct(SearchModel productSearch) {
        String sql = "SELECT * FROM tbl_product p WHERE 1=1";

        if(productSearch.getStatus() != 2){
            sql += " AND p.status = " + productSearch.getStatus();
        }

        if(productSearch.getCategoryId() != 0){
            sql += " AND p.category_id = " + productSearch.getCategoryId();
        }

        if(!StringUtils.isEmpty(productSearch.getKeyword())){
            String keyword = productSearch.getKeyword().toLowerCase();

            sql += " AND (LOWER(p.name) like '%" + keyword + "%'" +
                    " OR LOWER(p.short_description) like '%" + keyword + "%'" +
                    " OR LOWER(p.seo) like '%" + keyword + "%')";
        }

        if(!StringUtils.isEmpty(productSearch.getBeginDate()) && !StringUtils.isEmpty(productSearch.getEndDate())){
            String beginDate = productSearch.getBeginDate();
            String endDate = productSearch.getEndDate();

            sql += " AND p.create_date BETWEEN '" + beginDate + "' AND '" + endDate + "'";
        }
        return super.executeNativeSql(sql, productSearch.getCurrentPage(), productSearch.getSizeOfPage());
    }
}
