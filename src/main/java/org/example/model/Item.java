package org.example.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Item {

    private int itemId;
    private int quantity;
    private String name;
    private String category;
    private double price;
    private int rating;

    private byte[] image;
    private String imageUrlForJSP;
    private Set<CustomerItem>customerItems=new HashSet<>();
    private Set<MyOrderItem> myOrderItems =new HashSet<>();
    public Item(){}

    public Item(int quantity, String name,
                String category, double price, int rating) {
        this.quantity = quantity;
        this.name = name;
        this.category = category;
        this.price = price;
        this.rating = rating;
    }
    @Id
    @GeneratedValue
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @NotNull
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    @Min(value = 0, message = "Rating should not be less than 0")
    @Max(value = 5, message = "Rating should not be greater than 5")
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
    @Lob
    @Column(columnDefinition="BLOB")
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    @OneToMany(mappedBy = "customerItemId.item",cascade = CascadeType.ALL)
    public Set<CustomerItem> getCustomerItems() {
        return customerItems;
    }

    public void setCustomerItems(Set<CustomerItem> customerItems) {
        this.customerItems = customerItems;
    }
    @OneToMany(mappedBy = "myOrderItemId.item",cascade = CascadeType.ALL)
    public Set<MyOrderItem> getMyOrderItems() {
        return myOrderItems;
    }

    public void setMyOrderItems(Set<MyOrderItem> myOrderItems) {
        this.myOrderItems = myOrderItems;
    }
    @Transient
    public String getImageUrlForJSP(){
        return "data:image/png;base64," + Base64.getEncoder().encodeToString(getImage());

    }

    public void setImageUrlForJSP(String imageUrlForJSP) {
        this.imageUrlForJSP = imageUrlForJSP;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", quantity=" + quantity +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                '}';
    }
}
