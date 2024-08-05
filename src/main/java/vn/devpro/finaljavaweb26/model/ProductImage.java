package vn.devpro.finaljavaweb26.model;

import javax.persistence.*;

@Entity
@Table(name = "tbl_product_image")
public class ProductImage extends BaseModel{
    @Column(name = "title", length = 500, nullable = true)
    private String title;
    @Column(name = "path", length = 300, nullable = true)
    private String path;
//    Mapping many-to-one
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
//    -------------------------------------------------------

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
