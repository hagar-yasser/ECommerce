package org.example.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class MyOrder {

    private int myOrderId;
    private LocalDate MyOrderDate;
    private Customer owner;

    private Set<MyOrderItem> myOrderedItems =new HashSet<>();
    public MyOrder(){}

    public MyOrder(LocalDate MyOrderDate, Customer owner, Set<MyOrderItem> myOrderedItems) {
        this.MyOrderDate = MyOrderDate;
        this.owner = owner;
        this.myOrderedItems = myOrderedItems;
    }
    @Id
    @GeneratedValue
    public int getMyOrderId() {
        return myOrderId;
    }

    public void setMyOrderId(int myOrderId) {
        this.myOrderId = myOrderId;
    }

    public LocalDate getMyOrderDate() {
        return MyOrderDate;
    }

    public void setMyOrderDate(LocalDate myOrderDate) {
        this.MyOrderDate = myOrderDate;
    }
    @ManyToOne
    @JoinColumn(name="owner_id",nullable = false)
    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }
    @OneToMany(mappedBy = "myOrderItemId.myOrder",cascade = CascadeType.ALL)
    public Set<MyOrderItem> getMyOrderedItems() {
        return myOrderedItems;
    }

    public void setMyOrderedItems(Set<MyOrderItem> myOrderedItems) {
        this.myOrderedItems = myOrderedItems;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + myOrderId +
                ", orderDate=" + MyOrderDate +
                ", owner=" + owner +
                ", orderedItems=" + myOrderedItems +
                '}';
    }
}
