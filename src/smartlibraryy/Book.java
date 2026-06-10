/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smartlibraryy;

/**
 *
 * @author Legion
 */

import java.util.LinkedList;
import java.util.Queue;

public class Book {
    private int isbn;
    private String title;
    private String author;
    
    private boolean isBorrowed;
    private Queue<String> waitlist;

    public Book(int isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;

        this.isBorrowed = false;
        this.waitlist = new LinkedList<>(); 
    }

    public int getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    
    public boolean isBorrowed() { return isBorrowed; }
    public void setBorrowed(boolean status) { this.isBorrowed = status; }
    public Queue<String> getWaitlist() { return waitlist; }
}
