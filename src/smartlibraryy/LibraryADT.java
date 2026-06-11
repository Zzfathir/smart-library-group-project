/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package smartlibraryy;

/**
 *
 * @author
 * @author Taqaufa Sadiid Priya Prasetyo
 */
public interface LibraryADT {
    void addBook(int isbn, String title, String author);

    void borrowBook(int isbn, String studentName);

    void returnBook(int isbn);

    void viewLatestHistory();

    void searchBook(int isbn);

    void listAllBooks();

    void findByTitle(String title);
 
    void findByAuthor(String author);
}
