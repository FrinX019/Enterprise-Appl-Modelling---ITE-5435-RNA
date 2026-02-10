package com.fp.week4;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
public class Book {

	@Id
	private String id;
	private String title;
	private String author;
	private double price;
	private boolean available;

	// Default constructor
	public Book() {
	}

	// All fields constructor
	public Book(String id, String title, String author, double price, boolean available) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.price = price;
		this.available = available;
	}

	// Getters
	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public double getPrice() {
		return price;
	}

	public boolean isAvailable() {
		return available;
	}

	// Setters
	public void setId(String id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", price=" + price + ", available="
				+ available + "]";
	}
}
