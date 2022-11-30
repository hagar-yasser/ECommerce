package org.example.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Customer {

    private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean isAdmin;
    private boolean isLoggedIn;
    private boolean isActivated;

    private Set<CustomerItem>shoppingCart=new HashSet<>();
    private Set<MyOrder> myOrders =new HashSet<>();


    private int wrongPasswordTrials;

    public Customer(){}
    public Customer(String firstName, String lastName,
                    String email, String password, boolean isAdmin, boolean isLoggedIn, boolean isActivated, int wrongPasswordTrials) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isLoggedIn = isLoggedIn;
        this.isActivated = isActivated;
        this.wrongPasswordTrials = wrongPasswordTrials;
    }
    @Id
    @GeneratedValue
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }
    @OneToMany(mappedBy = "customerItemId.customer",cascade = CascadeType.ALL)
    public Set<CustomerItem> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(Set<CustomerItem> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
    @OneToMany(mappedBy = "owner",cascade = CascadeType.ALL)
    public Set<MyOrder> getMyOrders() {
        return myOrders;
    }

    public void setMyOrders(Set<MyOrder> myOrders) {
        this.myOrders = myOrders;
    }

    public int getWrongPasswordTrials() {
        return wrongPasswordTrials;
    }

    public void setWrongPasswordTrials(int wrongPasswordTrials) {
        this.wrongPasswordTrials = wrongPasswordTrials;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                ", isLoggedIn=" + isLoggedIn +
                ", isVerified=" + isActivated +
                '}';
    }
}
