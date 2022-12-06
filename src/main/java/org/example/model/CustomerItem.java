package org.example.model;

import javax.persistence.*;

@Entity
@AssociationOverrides({
        @AssociationOverride(name = "customerItemId.customer",
                joinColumns = @JoinColumn(name = "customer_id")),
        @AssociationOverride(name = "customerItemId.item",
                joinColumns = @JoinColumn(name = "item_id")) })
public class CustomerItem {

    private CustomerItemId customerItemId=new CustomerItemId();
    private int quantity;
    @EmbeddedId
    public CustomerItemId getCustomerItemId() {
        return customerItemId;
    }

    public void setCustomerItemId(CustomerItemId customerItemId) {
        this.customerItemId = customerItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    @Transient
    public Customer getCustomer(){
        return getCustomerItemId().getCustomer();
    }
    public void setCustomer(Customer customer){
        getCustomerItemId().setCustomer(customer);
    }
    @Transient
    public Item getItem(){
        return getCustomerItemId().getItem();
    }
    public void setItem(Item item){
        getCustomerItemId().setItem(item);
    }
}
