package library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Singleton Pattern: Database Connection
class DatabaseConnection {
    private static DatabaseConnection instance;
    
    private DatabaseConnection() {
        // Normally here we would initialize a connection to the database
        System.out.println("Database connection established.");
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }
}

// Factory Pattern: Abstract Book
abstract class Book {
    abstract void displayDetails();
}

class SoftwareEngineeringBook extends Book {
    @Override
    public void displayDetails() {
        String details = "Title: Software Engineering\n" +
                         "Author: Robert C. Martin\n" +
                         "Publisher: Prentice Hall\n" +
                         "Release Date: 2023\n" +
                         "Price: $59.99";
        JOptionPane.showMessageDialog(null, details);
    }
}

class ManagementBook extends Book {
    @Override
    public void displayDetails() {
        String details = "Title: Management Essentials\n" +
                         "Author: Peter Drucker\n" +
                         "Publisher: HarperCollins\n" +
                         "Release Date: 2022\n" +
                         "Price: $49.99";
        JOptionPane.showMessageDialog(null, details);
    }
}

class AIBook extends Book {
    @Override
    public void displayDetails() {
        String details = "Title: Artificial Intelligence Basics\n" +
                         "Author: Rajendra Akerkar\n" +
                         "Publisher: Wiley\n" +
                         "Release Date: 2024\n" +
                         "Price: $69.99";
        JOptionPane.showMessageDialog(null, details);
    }
}

// Factory Pattern: Book Factory to create different types of books
class BookFactory {
    public static Book createBook(String type) {
        switch (type.toLowerCase()) {
            case "software engineering":
                return new SoftwareEngineeringBook();
            case "management":
                return new ManagementBook();
            case "ai":
                return new AIBook();
            default:
                throw new IllegalArgumentException("Unknown book type: " + type);
        }
    }
}

// Prototype Pattern: Book Prototype to clone books
abstract class BookPrototype implements Cloneable {
    public abstract BookPrototype clone();
    public abstract void displayDetails();
}

class PrototypeSoftwareEngineeringBook extends BookPrototype {
    @Override
    public BookPrototype clone() {
        return new PrototypeSoftwareEngineeringBook();
    }

    @Override
    public void displayDetails() {
        String details = "This is a prototype Software Engineering book.";
        JOptionPane.showMessageDialog(null, details);
    }
}

class PrototypeManagementBook extends BookPrototype {
    @Override
    public BookPrototype clone() {
        return new PrototypeManagementBook();
    }

    @Override
    public void displayDetails() {
        String details = "This is a prototype Management book.";
        JOptionPane.showMessageDialog(null, details);
    }
}

// Builder Pattern: Custom Book Builder
class BookBuilder {
    private String title;
    private String author;
    private String publisher;
    private String releaseDate;
    private double price;

    public BookBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public BookBuilder setAuthor(String author) {
        this.author = author;
        return this;
    }

    public BookBuilder setPublisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    public BookBuilder setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public BookBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    public Book build() {
        return new CustomBook(title, author, publisher, releaseDate, price);
    }
}

class CustomBook extends Book {
    private String title;
    private String author;
    private String publisher;
    private String releaseDate;
    private double price;

    public CustomBook(String title, String author, String publisher, String releaseDate, double price) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.releaseDate = releaseDate;
        this.price = price;
    }

    @Override
    public void displayDetails() {
        String details = "Title: " + title + "\n" +
                         "Author: " + author + "\n" +
                         "Publisher: " + publisher + "\n" +
                         "Release Date: " + releaseDate + "\n" +
                         "Price: $" + price;
        JOptionPane.showMessageDialog(null, details);
    }
}

// Adapter Pattern: To adapt Book details to a new format
class BookDetailsAdapter {
    private Book book;

    public BookDetailsAdapter(Book book) {
        this.book = book;
    }

    public void showBookDetails() {
        String adaptedDetails = "Displaying Book Details:\n";
        book.displayDetails();
    }
}

// Book Manager to handle book creation and displaying details
class BookManager {
    public void displayBookDetails(Book book) {
        book.displayDetails();
    }

    public Book createBook(String type) {
        return BookFactory.createBook(type);
    }

    public BookPrototype cloneBook(BookPrototype book) {
        return book.clone();
    }
}

// Main GUI class
public class Library {

    public static void main(String[] args) {
        // Singleton Pattern: Database connection instance
        DatabaseConnection dbConnection = DatabaseConnection.getInstance();

        // Setting up the GUI window
        JFrame frame = new JFrame("Library Management System");
        frame.setSize(500, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // Set background color of the window
        frame.getContentPane().setBackground(new Color(239, 240, 245)); // Soft background color

        // Set font for the buttons
        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        
        // Initialize Book Manager
        BookManager bookManager = new BookManager();

        // Create buttons for selecting different books
        JButton softwareButton = new JButton("Software Engineering Book");
        JButton managementButton = new JButton("Management Book");
        JButton aiButton = new JButton("AI Book");

        // Button for creating custom book
        JButton customBookButton = new JButton("Custom Book");

        // Set colors for the buttons
        Color buttonColor = new Color(58, 100, 175); // Soft blue color
        Color hoverColor = new Color(88, 130, 200); // Lighter blue for hover effect

        // Apply styles to the buttons
        softwareButton.setBackground(buttonColor);
        softwareButton.setFont(buttonFont);
        softwareButton.setForeground(Color.WHITE);
        softwareButton.setFocusPainted(false);

        managementButton.setBackground(buttonColor);
        managementButton.setFont(buttonFont);
        managementButton.setForeground(Color.WHITE);
        managementButton.setFocusPainted(false);

        aiButton.setBackground(buttonColor);
        aiButton.setFont(buttonFont);
        aiButton.setForeground(Color.WHITE);
        aiButton.setFocusPainted(false);

        customBookButton.setBackground(new Color(97, 171, 79)); // Soft green for custom book button
        customBookButton.setFont(buttonFont);
        customBookButton.setForeground(Color.WHITE);
        customBookButton.setFocusPainted(false);

        // Adding action listeners for the buttons
        softwareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Book softwareEngineeringBook = bookManager.createBook("software engineering");
                bookManager.displayBookDetails(softwareEngineeringBook);
            }
        });

        managementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Book managementBook = bookManager.createBook("management");
                bookManager.displayBookDetails(managementBook);
            }
        });

        aiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Book aiBook = bookManager.createBook("ai");
                bookManager.displayBookDetails(aiBook);
            }
        });

        customBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Book customBook = new BookBuilder()
                    .setTitle("Custom Java Book")
                    .setAuthor("John Doe")
                    .setPublisher("Custom Publisher")
                    .setReleaseDate("2024")
                    .setPrice(39.99)
                    .build();
                bookManager.displayBookDetails(customBook);
            }
        });

        // Adding buttons to the frame
        frame.add(softwareButton);
        frame.add(managementButton);
        frame.add(aiButton);
        frame.add(customBookButton);

        // Make the window visible
        frame.setVisible(true);
    }
}
