/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smartlibraryy;
import java.util.Scanner;
/**
 *
 * @author Legion
 */
public class SmartLibrary implements LibraryADT {
    private BookBST catalogue = new BookBST();
    private BorrowStack history = new BorrowStack();

   
    @Override
    public void addBook(int isbn, String title, String author) {
        catalogue.insert(isbn, title, author);
        System.out.println("  Book added successfully.");
    }

    @Override
    public void searchBook(int isbn) {
        Book b = catalogue.search(isbn);
        if (b != null) {
            System.out.println("  Found: [ISBN: " + b.getIsbn() + "] " + b.getTitle() + " by " + b.getAuthor());
        } else {
            System.out.println("  Book not found in catalogue.");
        }
    }

    @Override
    public void viewLatestHistory() {
        System.out.println("  --- Borrowing History (Most Recent First) ---");
        history.show();
    }

    @Override
    public void borrowBook(int isbn) {
        Book b = catalogue.search(isbn);   

        if (b != null) {
            history.push(b);                
            catalogue.delete(isbn);         
            System.out.println("  Successfully borrowed: \"" + b.getTitle() + "\"");
        } else {
            System.out.println("  Book not found. It may already be borrowed or doesn't exist.");
        }
    }

    @Override
    public void returnBook(int isbn) {
        Book b = history.remove(isbn);      

        if (b != null) {
            catalogue.insert(b.getIsbn(), b.getTitle(), b.getAuthor()); 
            System.out.println("  Successfully returned: \"" + b.getTitle() + "\"");
        } else {
            System.out.println("  This book is not in your borrowing history.");
        }
    }

   
    public void runMenu() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            printMenu();
            System.out.print("Choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("  Invalid input. Please enter a number between 1 and 6.");
                sc.next();
                continue;
            }

            int choice = sc.nextInt();

            if (choice == 6) {
                System.out.println("  Goodbye!");
                break;
            }

            handleChoice(choice, sc);
        }

        sc.close();
    }

    private void printMenu() {
        System.out.println("\n=============================");
        System.out.println("      Smart Library Menu     ");
        System.out.println("=============================");
        System.out.println("  1. Add Book");
        System.out.println("  2. Search Book (BST)");
        System.out.println("  3. Borrow Book");
        System.out.println("  4. Return Book");
        System.out.println("  5. View Borrowing History");
        System.out.println("  6. Exit");
        System.out.println("=============================");
    }

    private void handleChoice(int choice, Scanner sc) {
        switch (choice) {
            case 1:
                System.out.print("  Enter ISBN (numbers only): ");
                if (!sc.hasNextInt()) { System.out.println("  Invalid ISBN."); sc.next(); break; }
                int addIsbn = sc.nextInt();
                System.out.print("  Enter Title : ");
                sc.nextLine();
                String title = sc.nextLine().trim();
                System.out.print("  Enter Author: ");
                String author = sc.nextLine().trim();
                addBook(addIsbn, title, author);
                break;

            case 2:
                System.out.print("  Enter ISBN to search: ");
                if (!sc.hasNextInt()) { System.out.println("  Invalid ISBN."); sc.next(); break; }
                searchBook(sc.nextInt());
                break;

            case 3: // YOUR PART
                System.out.print("  Enter ISBN to borrow: ");
                if (!sc.hasNextInt()) { System.out.println("  Invalid ISBN."); sc.next(); break; }
                borrowBook(sc.nextInt());
                break;

            case 4: // YOUR PART
                System.out.print("  Enter ISBN to return: ");
                if (!sc.hasNextInt()) { System.out.println("  Invalid ISBN."); sc.next(); break; }
                returnBook(sc.nextInt());
                break;

            case 5:
                viewLatestHistory();
                break;

            default:
                System.out.println("  Invalid option. Please choose between 1 and 6.");
        }
    }
}
