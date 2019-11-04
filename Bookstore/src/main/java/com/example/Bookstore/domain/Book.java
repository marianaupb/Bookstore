package com.example.Bookstore.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity // it represents a table in a database
public class Book {

	@Id // creating id column of the table
	@GeneratedValue(strategy = GenerationType.IDENTITY) // automatically generates PK for every new entity
	private long id; // long is better than integer. it's also accepts numbers longer than integers

	@ManyToOne
	@JsonIgnore // are considered to be ignored in JSON serialization and deserialization
	@JoinColumn(name = "categoryId", nullable = true)
	private Category category;

	public Book() {
	}

	public Book(String author, String title, String isbn, int year, Category category) {
		super();
		this.author = author;
		this.title = title;
		this.isbn = isbn;
		this.year = year;
		this.category = category;
	}

	private String author, title, isbn;
	private int year;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		if (this.category != null)
			return "Book [id=" + id + ", author=" + author + ", title=" + title + ", isbn=" + isbn + " category ="
					+ this.getCategory() + "]";
		else
			return "Book [id=" + id + ", author=" + author + ", title=" + title + ", isbn=" + isbn + "]";
	}

}