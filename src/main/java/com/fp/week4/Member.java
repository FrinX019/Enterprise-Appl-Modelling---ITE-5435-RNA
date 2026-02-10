package com.fp.week4;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document(collection = "members")
public class Member {

    @Id
    private String id;
    private String name;
    private String address;
    private String membershipType;
    private LocalDate membershipDate;
    private LocalDate expiryDate;

    // Default constructor
    public Member() {
    }

    // All fields constructor
    public Member(String id, String name, String address, String membershipType, LocalDate membershipDate,
            LocalDate expiryDate) {
        super();
        this.id = id;
        this.name = name;
        this.address = address;
        this.membershipType = membershipType;
        this.membershipDate = membershipDate;
        this.expiryDate = expiryDate;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public LocalDate getMembershipDate() {
        return membershipDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public void setMembershipDate(LocalDate membershipDate) {
        this.membershipDate = membershipDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return "Member [id=" + id + ", name=" + name + ", address=" + address + ", membershipType=" + membershipType
                + ", membershipDate=" + membershipDate + ", expiryDate=" + expiryDate + "]";
    }
}
