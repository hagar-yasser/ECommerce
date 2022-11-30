package org.example.model;

import javax.persistence.*;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

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
