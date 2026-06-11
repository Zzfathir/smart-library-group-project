package smartlibraryy;

import java.util.List;

/**
 *
 * @author Taqaufa Sadiid Priya Prasetyo
 */

public class RecordFinder {
 
    private BookBST catalogue;
 
    public RecordFinder(BookBST catalogue) {
        this.catalogue = catalogue;
    }
 
    public void findByIsbn(int isbn) {
        System.out.println("\n--- Record Finder: Search by ISBN ---");
        Book b = catalogue.search(isbn);
        showRecord(b);
    }
 
    public void findByTitle(String title) {
        System.out.println("\n--- Record Finder: Search by Title ---");
        List<Book> results = catalogue.searchByTitle(title);
        if (results.isEmpty()) {
            System.out.println("   No records found for: " + title);
        } else {
            System.out.println("   Found " + results.size() + " result(s):");
            for (Book b : results) showRecord(b);
        }
    }
 
    public void findByAuthor(String author) {
        System.out.println("\n--- Record Finder: Search by Author ---");
        List<Book> results = catalogue.searchByAuthor(author);
        if (results.isEmpty()) {
            System.out.println("   No records found for: " + author);
        } else {
            System.out.println("   Found " + results.size() + " result(s):");
            for (Book b : results) showRecord(b);
        }
    }
 
    private void showRecord(Book b) {
        if (b == null) {
            System.out.println("   Record not found.");
            return;
        }
        System.out.println("   ISBN   : " + b.getIsbn());
        System.out.println("   Title  : " + b.getTitle());
        System.out.println("   Author : " + b.getAuthor());
        System.out.println("   Status : " + (b.isBorrowed() ? "Checked Out" : "Available"));
        if (b.isBorrowed() && !b.getWaitlist().isEmpty()) {
            System.out.println("   Queue  : " + b.getWaitlist().size() + " student(s) waiting");
        }
    }
}