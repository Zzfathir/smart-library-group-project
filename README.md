# Smart Library

This project is a console-based Smart Library Management application built in Java. It demonstrates the practical application of Object-Oriented Programming (OOP) principles and advanced data structures, including Binary Search Trees (BST), Stacks, and Queues.

## 📂 Project File Structure

Ensure all of the following files are saved in the exact same directory (folder) before running the program:

1. `LibraryADT.java` - The abstract interface ensuring information hiding.
2. `Book.java` - The core entity class managing book details, borrowing status, and waitlists.
3. `BookBST.java` - The Binary Search Tree managing the catalog and recommendation engine.
4. `BorrowStack.java` - The Stack structure handling the Last-In-First-Out (LIFO) borrowing history.
5. `SmartLibrary.java` - The central logic system integrating the data structures and console menu.
6. `Main.java` - The executable entry point for the application.

*Note: The system will automatically generate a `books.csv` file in this folder upon first launch to handle data persistence.*

---

## ⚙️ Compilation and Execution Guide

### Prerequisites
You must have the Java Development Kit (JDK) installed on your system. You can verify this by opening your terminal or command prompt and typing:
`java -version`

### Step 1: Compile the Code
Open your terminal or command prompt, navigate to the folder containing your Java files, and compile them all together using the wildcard command:

```bash
javac *.java
