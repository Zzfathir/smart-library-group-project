/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smartlibraryy;

import java.util.Stack;
/**
 *
 * @author raya
 */

public class BorrowStack {
    private Stack<Book> stack = new Stack<>();

    public void push(Book b) {
        stack.push(b);
    }

    public void show() {
        if (stack.isEmpty()) {
            System.out.println("History is empty.");
        } else {
            System.out.println("--- Borrowing History (Most Recent First) ---");
            for (int i = stack.size() - 1; i >= 0; i--) {
                Book b = stack.get(i);
                System.out.println("[ISBN: " + b.getIsbn() + "] " + b.getTitle());
            }
        }
    }

    public Book remove(int isbn) {
        for (int i = stack.size() - 1; i >= 0; i--) {
            if (stack.get(i).getIsbn() == isbn) {
                return stack.remove(i);
            }
        }
        return null;
    }
}
