package org.example.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Customer {

    private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean isAdmin=false;
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean isLoggedIn=false;
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean isActivated=false;

    private Set<CustomerItem>shoppingCart=new HashSet<>();
    private Set<MyOrder> myOrders =new HashSet<>();


    private int wrongPasswordTrials;

    public Customer(){}

    public Customer(int customerId, String firstName, String lastName, String email, String password, boolean isAdmin, boolean isLoggedIn, boolean isActivated, int wrongPasswordTrials) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isLoggedIn = isLoggedIn;
        this.isActivated = isActivated;
        this.wrongPasswordTrials = wrongPasswordTrials;
    }

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

    public Customer(CustomerBuilder customerBuilder) {
        this.customerId = customerBuilder.customerId;
        this.firstName = customerBuilder.firstName;
        this.lastName = customerBuilder.lastName;
        this.email = customerBuilder.email;
        this.password = customerBuilder.password;
        this.wrongPasswordTrials = customerBuilder.wrongPasswordTrials;
        this.isAdmin= customerBuilder.isAdmin;
        this.isLoggedIn=customerBuilder.isLoggedIn;
        this.isActivated= customerBuilder.isActivated;
    }

    @Id
    @GeneratedValue
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    @NotNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @NotNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @NotNull
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    public void setIsLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public boolean getIsActivated() {
        return isActivated;
    }

    public void setIsActivated(boolean isActivated) {
        this.isActivated = isActivated;
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
    //Builder class
    public static class CustomerBuilder {
        private int customerId;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private boolean isAdmin = false;
        private boolean isLoggedIn = false;
        private boolean isActivated = false;
        private int wrongPasswordTrials;

        public CustomerBuilder(int customerId, String firstName, String lastName, String email, String password, int wrongPasswordTrials) {
            this.customerId = customerId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.password = password;
            this.wrongPasswordTrials = wrongPasswordTrials;
        }
        public CustomerBuilder setAdmin(boolean admin) {
            this.isAdmin = admin;
            return this;
        }

        public CustomerBuilder setLoggedIn(boolean loggedIn) {
            this.isLoggedIn = loggedIn;
            return this;
        }

        public CustomerBuilder setActivated(boolean activated) {
            this.isActivated = activated;
            return this;
        }
        public Customer build(){
            return new Customer(this);
        }
    }
}
