package com.copernicus.stats.model;

import javax.persistence.*;
import java.util.Objects;

import static com.copernicus.stats.utils.Colors.*;

@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String phoneNumber;
    private String email;
    private String companyName;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Contact() {
    }

    public Contact(String name, String phoneNumber, String email, String companyName) {
        setName(name);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setCompanyName(companyName);
    }

//    public Contact(String name, String phoneNumber, String email, String companyName, Account account) {
//        setName(name);
//        setPhoneNumber(phoneNumber);
//        setEmail(email);
//        setCompanyName(companyName);
//        setAccount(account);
//    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return ANSI_CYAN + ANSI_BOLD +
                "Contact: " + id +
                ANSI_RESET + ANSI_BLUE +
                "\n  name = " + name +
                ", \n  phoneNumber = " + phoneNumber +
                ", \n  email = " + email  +
                ", \n  companyName = " + companyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(name, contact.name) &&
                Objects.equals(phoneNumber, contact.phoneNumber) &&
                Objects.equals(email, contact.email) &&
                Objects.equals(companyName, contact.companyName);
    }

}
