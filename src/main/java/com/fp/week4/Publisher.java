package com.fp.week4;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "publishers")
public class Publisher {

    @Id
    private String id;
    private String name;
    private String address;

    // Default constructor
    public Publisher() {
    }

    // All fields constructor
    public Publisher(String id, String name, String address) {
        super();
        this.id = id;
        this.name = name;
        this.address = address;
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

    @Override
    public String toString() {
        return "Publisher [id=" + id + ", name=" + name + ", address=" + address + "]";
    }
}
