package pl.edu.wszib.springwithtests.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table
public class ShoppingBasketItem {

    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne(optional = false)
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;
    @Column(nullable = false)
    private Integer amount;
    @OneToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ShoppingBasket shoppingBasket;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public ShoppingBasket getShoppingBasket() {
        return shoppingBasket;
    }

    public void setShoppingBasket(ShoppingBasket shoppingBasket) {
        this.shoppingBasket = shoppingBasket;
    }
}
