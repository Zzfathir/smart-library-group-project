/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smartlibraryy;

/**
 *
 * @author Legion
 */
public class BookBST {
    private Book root;

    public void insert(int isbn, String title, String author) {
        root = ins(root, isbn, title, author);
    }

    private Book ins(Book r, int i, String t, String a) {
        if (r == null)
            return new Book(i, t, a);
        if (i < r.isbn) {
            r.left = ins(r.left, i, t, a);
        } else if (i > r.isbn) {
            r.right = ins(r.right, i, t, a);
        }
        return r;
    }

    public Book search(int i) {
        return sea(root, i);
    }

    private Book sea(Book r, int i) {
        if (r == null || r.isbn == i)
            return r;
        return (i < r.isbn) ? sea(r.left, i) : sea(r.right, i);
    }

    public void delete(int isbn) {
        root = del(root, isbn);
    }

    private Book del(Book r, int isbn) {
        if (r == null) return null;

        if (isbn < r.isbn) {
            r.left = del(r.left, isbn);
        } else if (isbn > r.isbn) {
            r.right = del(r.right, isbn);
        } else {
            if (r.left == null) return r.right;
            if (r.right == null) return r.left;

            Book successor = findMin(r.right);
            r.isbn   = successor.isbn;
            r.title  = successor.title;
            r.author = successor.author;
            r.right  = del(r.right, successor.isbn);
        }
        return r;
    }

    private Book findMin(Book r) {
        while (r.left != null) r = r.left;
        return r;
    }
}
