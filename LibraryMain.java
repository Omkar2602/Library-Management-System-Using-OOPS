import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


abstract class LibraryItem {
    private String title;
    private String author;
    private int id;

    public LibraryItem(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }

    public abstract void displayDetails();
}

class Book extends LibraryItem {
    private int pages;

    public Book(int id, String title, String author, int pages) {
        super(id, title, author);
        this.pages = pages;
    }

    @Override
    public void displayDetails() { // Polymorphism
        System.out.println("Book ID: " + getId());
        System.out.println("Title: " + getTitle());
        System.out.println("Author: " + getAuthor());
        System.out.println("Pages: " + pages);
        System.out.println("--------------------------");
    }
}

class EBook extends LibraryItem {
    private double fileSizeMB;

    public EBook(int id, String title, String author, double fileSizeMB) {
        super(id, title, author);
        this.fileSizeMB = fileSizeMB;
    }

    @Override
    public void displayDetails() {
        System.out.println("E-Book ID: " + getId());
        System.out.println("Title: " + getTitle());
        System.out.println("Author: " + getAuthor());
        System.out.println("File Size: " + fileSizeMB + " MB");
        System.out.println("--------------------------");
    }
}

class Library {
    private List<LibraryItem> items = new ArrayList<>();

    public void addItem(LibraryItem item) {
        items.add(item);
        System.out.println(item.getTitle() + " added successfully!");
    }

    public void showAllItems() {
        if (items.isEmpty()) {
            System.out.println("No items in the library yet.");
            return;
        }
        for (LibraryItem item : items) {
            item.displayDetails();
        }
    }

    public void searchItemByTitle(String title) {
        for (LibraryItem item : items) {
            if (item.getTitle().equalsIgnoreCase(title)) {
                System.out.println("Found:");
                item.displayDetails();
                return;
            }
        }
        System.out.println("Item not found!");
    }
}

public class LibraryMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        while (true) {
            System.out.println("\n===== LIBRARY MENU =====");
            System.out.println("1. Add Book");
            System.out.println("2. Add E-Book");
            System.out.println("3. Show All Items");
            System.out.println("4. Search by Title");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();
                    System.out.print("Enter Number of Pages: ");
                    int pages = sc.nextInt();
                    sc.nextLine();
                    library.addItem(new Book(id, title, author, pages));
                    break;

                case 2:
                    System.out.print("Enter ID: ");
                    int eid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Title: ");
                    String etitle = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String eauthor = sc.nextLine();
                    System.out.print("Enter File Size (MB): ");
                    double size = sc.nextDouble();
                    sc.nextLine();
                    library.addItem(new EBook(eid, etitle, eauthor, size));
                    break;

                case 3:
                    library.showAllItems();
                    break;

                case 4:
                    System.out.print("Enter Title to Search: ");
                    String searchTitle = sc.nextLine();
                    library.searchItemByTitle(searchTitle);
                    break;

                case 5:
                    System.out.println("Exiting system...");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
