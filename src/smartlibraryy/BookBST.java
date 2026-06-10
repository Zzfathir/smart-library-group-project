/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smartlibraryy;

/**
 *
 * @author Legion
 */
// BookBST.java
class BookBST {

    private class TreeNode {
        Book book;
        TreeNode left, right;
        TreeNode(Book book) { this.book = book; }
    }

    private TreeNode root;

    public void insert(Book newBook) {
        root = ins(root, newBook);
    }

    private TreeNode ins(TreeNode r, Book b) {
        if (r == null) return new TreeNode(b);
        if (b.isbn() < r.book.isbn()) r.left = ins(r.left, b);
        else if (b.isbn() > r.book.isbn()) r.right = ins(r.right, b);
        return r;
    }

    public Book search(int isbn) {
        TreeNode result = sea(root, isbn);
        return (result == null) ? null : result.book;
    }

    private TreeNode sea(TreeNode r, int isbn) {
        if (r == null || r.book.isbn() == isbn) return r;
        return (isbn < r.book.isbn()) ? sea(r.left, isbn) : sea(r.right, isbn);
    }


    public void recommendByAuthor(String author, int excludeIsbn) {
        System.out.println("\n💡 SMART RECOMMENDATION: Other books by " + author + ":");
        boolean[] found = {false};
        recSearch(root, author, excludeIsbn, found);
        if (!found[0]) System.out.println("   No other books found by this author.");
    }

    private void recSearch(TreeNode r, String author, int excludeIsbn, boolean[] found) {
        if (r != null) {
            recSearch(r.left, author, excludeIsbn, found);
            if (r.book.author().equalsIgnoreCase(author) && r.book.isbn() != excludeIsbn) {
                System.out.println("   -> " + r.book.title() + " (ISBN: " + r.book.isbn() + ")");
                found[0] = true;
            }
            recSearch(r.right, author, excludeIsbn, found);
        }
    }
}