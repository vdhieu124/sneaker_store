package vn.devpro.finaljavaweb26.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_sale_order")
public class SaleOrder extends BaseModel{
    @Column(name = "code", length = 60, nullable = false)
    private String code;
    @Column(name = "total", nullable = true)
    private BigDecimal total;
    @Column(name = "customer_name", length = 300, nullable = false)
    private String customerName;
    @Column(name = "customer_mobile", length = 120, nullable = false)
    private String customerMobile;
    @Column(name = "customer_email", length = 120, nullable = true)
    private String customerEmail;
    @Column(name = "customer_address", length = 300, nullable = false)
    private String customerAddress;
//    Mapping many-to-one-----------------------------------------
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
//    ----------------------------------------------------------
//    ----------Mapping one to many---------------------------
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "saleOrder")
    private Set<SaleOrderProduct> saleOrderProducts = new HashSet<SaleOrderProduct>();
    public void addRelationalSaleOrderProduct(SaleOrderProduct saleOrderProduct){
        saleOrderProducts.add(saleOrderProduct);
        saleOrderProduct.setSaleOrder(this);
    }
    public void removeRelationalSaleOrderProduct(SaleOrderProduct saleOrderProduct){
        saleOrderProducts.remove(saleOrderProduct);
        saleOrderProduct.setSaleOrder(null);
    }
//    -------------------------------------------------------

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Set<SaleOrderProduct> getSaleOrderProducts() {
        return saleOrderProducts;
    }

    public void setSaleOrderProducts(Set<SaleOrderProduct> saleOrderProducts) {
        this.saleOrderProducts = saleOrderProducts;
    }
}
