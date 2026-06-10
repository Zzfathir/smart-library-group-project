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
// SmartLibrary.java
import java.util.Scanner;

// SmartLibrary.java
import java.io.*;
import java.util.*;

class SmartLibrary implements LibraryADT {
    private BookBST catalogue = new BookBST();
    private BorrowStack history = new BorrowStack();
    
    private HashSet<Integer> currentlyBorrowed = new HashSet<>();
    private HashMap<Integer, Queue<String>> waitlists = new HashMap<>();
    
    private final String FILE_NAME = "./src/smartlibraryy/books.csv";

    public SmartLibrary() {
        loadFromCSV();
    }

    @Override
    public void addBook(int isbn, String title, String author) {
        if (catalogue.search(isbn) == null) {
            catalogue.insert(new Book(isbn, title, author));
            saveToCSV(isbn, title, author);
            System.out.println("Book added & saved to database.");
        } else {
            System.out.println("Book with this ISBN already exists.");
        }
    }

    @Override
    public void searchBook(int isbn) {
        Book b = catalogue.search(isbn);
        if (b != null) {
            System.out.println("Found: " + b.title() + " by " + b.author());
            catalogue.recommendByAuthor(b.author(), b.isbn());
        } else {
            System.out.println("Not Found.");
        }
    }

    @Override
    public void borrowBook(int isbn, String studentName) {
        Book b = catalogue.search(isbn);
        if (b == null) {
            System.out.println("Book not in catalogue.");
            return;
        }

        if (currentlyBorrowed.contains(isbn)) {
            waitlists.putIfAbsent(isbn, new LinkedList<>());
            waitlists.get(isbn).add(studentName);
            System.out.println("Book is currently checked out. " + studentName + " added to waitlist. Queue position: " + waitlists.get(isbn).size());
        } else {
            currentlyBorrowed.add(isbn);
            history.push(b);
            System.out.println("Borrowed successfully by " + studentName + ".");
            catalogue.recommendByAuthor(b.author(), b.isbn()); // Trigger Recommendation
        }
    }

    @Override
    public void returnBook(int isbn) {
        if (currentlyBorrowed.remove(isbn)) {
            System.out.println("Book returned.");
            Queue<String> queue = waitlists.get(isbn);
            if (queue != null && !queue.isEmpty()) {
                String nextStudent = queue.poll();
                currentlyBorrowed.add(isbn);
                System.out.println("🔔 ALERT: Book automatically assigned to " + nextStudent + " from the waitlist!");
                history.push(catalogue.search(isbn)); // Add to history for the new borrower
            }
        } else {
            System.out.println("This book was not checked out.");
        }
    }

    @Override
    public void viewLatestHistory() {
        history.show();
    }

    private void loadFromCSV() {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) file.createNewFile();
            Scanner fileScanner = new Scanner(file);
            int count = 0;
            while (fileScanner.hasNextLine()) {
                String[] data = fileScanner.nextLine().split(",");
                if (data.length == 3) {
                    catalogue.insert(new Book(Integer.parseInt(data[0]), data[1], data[2]));
                    count++;
                }
            }
            System.out.println("System Boot: Loaded " + count + " books from database.");
            fileScanner.close();
        } catch (IOException e) {
            System.out.println("Error loading database.");
        }
    }

    private void saveToCSV(int isbn, String title, String author) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(isbn + "," + title + "," + author);
        } catch (IOException e) {
            System.out.println("Error saving to database.");
        }
    }

    public void runMenu() {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
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
            int choice = getValidInt(sc, "Choice: ");

            switch (choice) {
                case 1 -> {
                    int isbn = getValidInt(sc, "Enter ISBN: ");
                    System.out.print("Enter Title: "); String t = sc.nextLine();
                    System.out.print("Enter Author: "); String a = sc.nextLine();
                    addBook(isbn, t, a);
                }
                case 2 -> searchBook(getValidInt(sc, "Enter ISBN to search: "));
                case 3 -> {
                    int isbn = getValidInt(sc, "Enter ISBN to borrow: ");
                    System.out.print("Enter Student Name: "); String name = sc.nextLine();
                    borrowBook(isbn, name);
                }
                case 4 -> returnBook(getValidInt(sc, "Enter ISBN to return: "));
                case 5 -> viewLatestHistory();
                case 6 -> running = false;
                default -> System.out.println("Invalid option.");
            }
        }
        sc.close();
    }

    private int getValidInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try { return Integer.parseInt(sc.nextLine().trim()); } 
            catch (NumberFormatException e) { System.out.println("Invalid input."); }
        }
    }
}