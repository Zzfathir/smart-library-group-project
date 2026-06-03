/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package smartlibraryy;

/**
 *
 * @author fathir
 */
public interface LibraryADT {
    void addBook(int isbn, String title, String author);
    void borrowBook(int isbn);
    void viewLatestHistory();
    void searchBook(int isbn);
}
