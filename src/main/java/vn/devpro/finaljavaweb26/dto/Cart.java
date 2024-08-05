package vn.devpro.finaljavaweb26.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartProduct> cartProducts = new ArrayList<CartProduct>();

    public Cart() {
    }

    public Cart(List<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public List<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
    }

    //tim san pham
    public int findProductById(int id){
        for (int i = 0; i < this.cartProducts.size(); i++){
            if (this.cartProducts.get(i).getProductId() == id){
                return i;
            }
        }
        return -1;
    }
    //tinh tong so sp
    public Integer totalCartProducts(){
        Integer total = 0;
        for (CartProduct cartProduct : cartProducts){
            total += cartProduct.getQuantity();
        }
        return total;
    }
    //tinh tong tien hang
    public BigDecimal totalCartPrice(){
        BigDecimal total = BigDecimal.ZERO;
        for (CartProduct cartProduct : cartProducts){
            total = total.add(cartProduct.totalPrice());
        }
        return total;
    }
}
