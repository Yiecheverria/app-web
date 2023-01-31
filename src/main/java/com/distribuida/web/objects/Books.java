package com.distribuida.web.objects;

import java.math.BigDecimal;

public class Books {

    private Integer id;
    private String isbn;
    private String title;
    private BigDecimal price;
    private Integer author_id;

    public Books() {
    }

    public Books(String isbn, String title, BigDecimal price, Integer author_id) {
        this.isbn = isbn;
        this.title = title;
        this.price = price;
        this.author_id = author_id;
    }

    public Books(Integer id, String isbn, String title, BigDecimal price, Integer author_id) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.price = price;
        this.author_id = author_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Integer author_id) {
        this.author_id = author_id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", author_id=" + author_id +
                '}';
    }
}
