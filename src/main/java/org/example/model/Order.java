package org.example.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Order {
    @Id
    @GeneratedValue
    private int orderId;
    private LocalDate orderDate;
    @ManyToOne
    @JoinColumn(name="owner_id",nullable = false)
    private Customer owner;
    @ManyToMany
    @JoinTable(name = "order_item",
            joinColumns = { @JoinColumn(name = "fk_order") },
            inverseJoinColumns = { @JoinColumn(name = "fk_item") })
    private Set<Item>orderedItems=new HashSet<>();
    public Order(){}

    public Order(LocalDate orderDate, Customer owner, Set<Item> orderedItems) {
        this.orderDate = orderDate;
        this.owner = owner;
        this.orderedItems = orderedItems;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public Set<Item> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(Set<Item> orderedItems) {
        this.orderedItems = orderedItems;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", owner=" + owner +
                ", orderedItems=" + orderedItems +
                '}';
    }
}
