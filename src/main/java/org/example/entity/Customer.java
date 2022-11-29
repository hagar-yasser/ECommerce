package org.example.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Customer {
    @Id
    @GeneratedValue
    private int customerId;
    private String email;
    private String password;
    private boolean isAdmin;
    private boolean isLoggedIn;
    private boolean isVerified;
    @JoinTable(name = "customer_item",
            joinColumns = { @JoinColumn(name = "fk_customer") },
            inverseJoinColumns = { @JoinColumn(name = "fk_item") })
    private Set<Item>shoppingCart=new HashSet<>();


    private int wrongPasswordTrials;

    public Customer(){}
    public Customer(String email, String password, boolean isAdmin,
                    boolean isLoggedIn, boolean isVerified, int wrongPasswordTrials) {
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isLoggedIn = isLoggedIn;
        this.isVerified = isVerified;
        this.wrongPasswordTrials = wrongPasswordTrials;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
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
                ", isVerified=" + isVerified +
                '}';
    }
}
