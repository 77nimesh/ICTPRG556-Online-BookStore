package model;

import java.text.DecimalFormat;
import java.io.*;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The {@code Book} class represents a book with an ISBN, title, author, and
 * price. It implements the {@code Serializable} interface to allow its
 * instances to be serialized.
 */
@Entity
@Table(name = "TBOOKS", schema = "USER1")
@NamedQueries({
    @NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b")
})
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "ISBN")
    private String isbn;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "PRICE")
    private double price;

    /**
     * Default constructor required by JPA.
     */
    public Book() {
    }

    /*
     private String isbn = "";
     private String title = "";
     private String author = "";
     private double price = 0.00;
     */
    /**
     * Constructs a new {@code Book} with the specified ISBN, title, author, and
     * price.
     *
     * @param isbn the ISBN of the book
     * @param title the title of the book
     * @param author the author of the book
     * @param price the price of the book
     */
    public Book(String isbn, String title, String author, double price) {
        this.isbn = isbn;
        this.author = author;
        this.title = title;
        this.price = price;
    }

    /**
     * Returns the ISBN of the book.
     *
     * @return the ISBN of the book
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Returns the title of the book.
     *
     * @return the title of the book
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the author of the book.
     *
     * @return the author of the book
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Returns the price of the book.
     *
     * @return the price of the book
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the ISBN of the book.
     *
     * @param isbn the ISBN of the book
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Sets the title of the book.
     *
     * @param title the title of the book
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the author of the book.
     *
     * @param author the author of the book
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Sets the price of the book.
     *
     * @param price the price of the book
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns the ISBN using the legacy uppercase method name.
     *
     * @return the ISBN of the book
     */
    public String getISBN() {
        return isbn;
    }

    /**
     * Sets the ISBN using the legacy uppercase method name.
     *
     * @param isbn the ISBN of the book
     */
    public void setISBN(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Returns a string representation of the book, which includes the title.
     *
     * @return a string representation of the book
     */
    @Override
    public String toString() {
        return "Title: " + title + "  ";
    }

    /**
     * Compares this book with another object using the ISBN value.
     *
     * @param obj the object to compare
     * @return true if the objects represent the same book; otherwise false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        if ((this.isbn == null) ? (other.isbn != null) : !this.isbn.equals(other.isbn)) {
            return false;
        }
        return true;
    }

    /**
     * Returns the price of the book formatted as a dollar amount.
     *
     * @return the price of the book formatted as a dollar amount
     */
    public String getDollarPrice() {
        DecimalFormat priceFormat = new DecimalFormat("0.00");
        return "$" + priceFormat.format(this.price);
    }
}
