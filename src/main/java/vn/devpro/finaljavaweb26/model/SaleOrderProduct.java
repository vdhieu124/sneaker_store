package vn.devpro.finaljavaweb26.model;

import javax.persistence.*;

@Entity
@Table(name = "tbl_sale_order_product")
public class SaleOrderProduct extends BaseModel{
    @Column(name = "quantity", nullable = true)
    private Integer quantity;
    @Column(name = "description", length = 500, nullable = true)
    private String description;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sale_order_id")
    private SaleOrder saleOrder;
//    -----------------------------------------------

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public SaleOrder getSaleOrder() {
        return saleOrder;
    }

    public void setSaleOrder(SaleOrder saleOrder) {
        this.saleOrder = saleOrder;
    }
//    ----------------------------------------------------

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
