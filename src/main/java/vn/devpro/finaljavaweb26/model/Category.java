package vn.devpro.finaljavaweb26.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_category")
public class Category extends BaseModel {
    @Column(name = "name", length = 300, nullable = false)
    private String name;
    @Column(name = "description", length = 500, nullable = true)
    private String description;
    @Column(name = "seo", length = 1000, nullable = true)
    private String seo;
//    ------------- mapping one-to-many
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "category")
    private Set<Product> products = new HashSet<Product>();
    // Methods add and remove elements in relational product list
    public void addRelationalProduct(Product product){
        products.add(product);
        product.setCategory(this);
    }
    public void removeRelationalProduct(Product product){
        products.remove(product);
        product.setCategory(null);
    }
//    ---------------------------------------------------------------------
//    ----------------Mapping many to one
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "create_by")
    private User userCreateCategory;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "update_by")
    private User userUpdateCategory;

    public User getUserCreateCategory() {
        return userCreateCategory;
    }

    public void setUserCreateCategory(User userCreateCategory) {
        this.userCreateCategory = userCreateCategory;
    }

    public User getUserUpdateCategory() {
        return userUpdateCategory;
    }

    public void setUserUpdateCategory(User userUpdateCategory) {
        this.userUpdateCategory = userUpdateCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeo() {
        return seo;
    }

    public void setSeo(String seo) {
        this.seo = seo;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
