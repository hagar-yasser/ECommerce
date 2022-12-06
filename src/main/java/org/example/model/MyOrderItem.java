package org.example.model;

import javax.persistence.*;

@Entity
@AssociationOverrides({
        @AssociationOverride(name = "myOrderItemId.myOrder",
                joinColumns = @JoinColumn(name = "myOrder_id")),
        @AssociationOverride(name = "myOrderItemId.item",
                joinColumns = @JoinColumn(name = "item_id")) })
public class MyOrderItem {

    private MyOrderItemId myOrderItemId=new MyOrderItemId();
    private int quantity;
    @EmbeddedId
    public MyOrderItemId getMyOrderItemId() {
        return myOrderItemId;
    }

    public void setMyOrderItemId(MyOrderItemId myOrderItemId) {
        this.myOrderItemId = myOrderItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    @Transient
    public MyOrder getMyOrder(){
        return getMyOrderItemId().getMyOrder();
    }
    public void setMyOrder(MyOrder myOrder){
        getMyOrderItemId().setMyOrder(myOrder);
    }
    @Transient
    public Item getItem(){
        return getMyOrderItemId().getItem();
    }
    public void setItem(Item item){
        getMyOrderItemId().setItem(item);
    }
}
