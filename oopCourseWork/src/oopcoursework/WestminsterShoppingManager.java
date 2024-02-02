package oopcoursework;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager {

    private static final Scanner scan = new Scanner(System.in);

//    ArrayList to store products
    private final ArrayList<Product> productList = new ArrayList<>();

    public void WestminsterShoppingManager() {
        System.out.println("***Welcome to Westminster Shopping Menu***");
        System.out.println("\tPlease Select your option");
        System.out.println(" ");
        System.out.println("\t01: Manage Users");
        System.out.println("\t02: Manage Products");
        System.out.println("\t03: Exit the Program");
        System.out.println();

        System.out.print("Enter Your Option :");
        String number = scan.nextLine();

//      Switch statement to handle user's choice
        switch (number) {
            case "01", "1":
                User customer = new User();
                break;
            case "02", "2":
                managerPart();
                break;
            case "03","3":
                System.exit(0);
            default:
                System.out.println("Invalid number..");
        }
    }

//    Method to handle product management options
    public void managerPart() {

        loadFromFile();

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add a Product");
            System.out.println("2. Print Products");
            System.out.println("3. Delete Products");
            System.out.println("4. Save to file");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            String choice = scan.nextLine();

            switch (choice) {
                case "1":
                    addProducts(scan, productList);
                    break;
                case "2":
                    printProducts(productList);
                    break;
                case "3":
                    deleteProducts(scan, productList);
                    break;
                case "4":
                    savetofile();
                    break;
                case "5":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

//    Method to add products to the productList
    public void addProducts(Scanner scanner, ArrayList<Product> productList) {
        if (productList.size() >= 50) {
            System.out.println("Cannot add more products. Maximum limit 50 reached.");
        } else {
            Scanner scan = new Scanner(System.in);
            System.out.println("\nWhat would you like to add (Press '1' for Electronic Product and Press '2' for Clothing Product)");
            System.out.println("\n1. Electronics");
            System.out.println("2. Cloths");
            System.out.print("\nEnter your choice: ");
            int categoryChoice = scanner.nextInt();
            scanner.nextLine();
            switch (categoryChoice) {
                case 1:
                    Electronics electronics = this.AddElectronicsProduct(scan);
                    if (electronics != null) {
                        this.productList.add(electronics);
                        System.out.println("\nElectronics product added successfully.");
                    }
                    break;
                case 2:
                    Clothing clothing = this.AddClothingProduct(scanner);
                    if (clothing != null) {
                        this.productList.add(clothing);
                        System.out.println("\nClothing product added successfully.");
                    }
                    break;
                default:
                    System.out.println("\nInvalid category choice.");
                    break;
            }
        }
    }

//    Method to add an Electronics product
    public Electronics AddElectronicsProduct(Scanner scan) {
//        Input details for Electronics product
        System.out.print("\nEnter product ID: ");
        String productId = scan.nextLine();
        System.out.print("Enter product name: ");
        String productName = scan.nextLine();
        System.out.print("Enter number of items: ");
        int numItems = scan.nextInt();
        System.out.print("Enter price: ");
        double price = scan.nextDouble();
        scan.nextLine();
        System.out.print("Enter brand: ");
        String brand = scan.nextLine();
        System.out.print("Enter warranty period: ");
        String warranty = scan.nextLine();
        return new Electronics(productId, productName, numItems, price, brand, warranty);
    }

//    Method to add a Clothing product
    public Clothing AddClothingProduct(Scanner scan) {
//        Input details for Clothing product
        System.out.print("\nEnter product ID: ");
        String productId = scan.nextLine();
        System.out.print("Enter product name: ");
        String productName = scan.nextLine();
        System.out.print("Enter number of items: ");
        int numItems = scan.nextInt();
        System.out.print("Enter price: ");
        double price = scan.nextDouble();
        scan.nextLine();
        System.out.print("Enter size: ");
        String size = scan.nextLine();
        System.out.print("Enter color: ");
        String color = scan.nextLine();
        return new Clothing(productId, productName, numItems, price, size, color);
    }

//    Method to print products in sorted order
    public void printProducts(ArrayList<Product> productList) {
        productList.sort(Comparator.comparing(Product::getProductId));

        if (productList.isEmpty()) {
            System.out.println("No products available.");
        } else {
            for (Product product : productList) {
                if (product instanceof Electronics) {
                    Electronics electronics = (Electronics) product;
                    System.out.println("\n"+electronics.toString());
                    System.out.println("");
                } else if (product instanceof Clothing) {
                    Clothing clothing = (Clothing) product;
                    System.out.println("\n"+clothing.toString());
                    System.out.println("");
                }
            }
        }
    }

    
//    Method to delete a product by product ID
    public void deleteProducts(Scanner scanner, ArrayList<Product> productList) {
        System.out.print("Enter the Product ID to delete: ");
        String productIdToDelete = scanner.nextLine();

        int indexToDelete = -1;

        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProductId() == null ? productIdToDelete == null : productList.get(i).getProductId().equals(productIdToDelete)) {
                indexToDelete = i;
                break;
            }
        }
        if (indexToDelete != -1) {
            productList.remove(indexToDelete);
            System.out.println("Product deleted successfully!");
        } else {
            System.out.println("Product with ID " + productIdToDelete + " not found.");
        }
    }

    
//    Method to save products to a file
    public void savetofile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("productsData.txt"))) {
            for (Product product : productList) {
                if (product instanceof Electronics) {
                    Electronics electronics = (Electronics) product;
                    writer.println(electronics.getProductId() + ","
                            + electronics.getProductName() + ","
                            + electronics.getAvailabelItems() + ","
                            + electronics.getPrice() + ","
                            + electronics.getBrand() + ","
                            + electronics.getWarrentyPeriod());
                } else if (product instanceof Clothing) {
                    Clothing clothing = (Clothing) product;
                    writer.println(clothing.getProductId() + ","
                            + clothing.getProductName() + ","
                            + clothing.getAvailabelItems() + ","
                            + clothing.getPrice() + ","
                            + clothing.getSize() + ","
                            + clothing.getColor());
                }
            }
            System.out.println("Product data saved to file successfully!");
        } catch (IOException e) {
            System.err.println("Error saving product data to file: " + e.getMessage());
        }
    }

    
//    Method to load products 
    public void loadFromFile() {
        try (Scanner fileScanner = new Scanner(new File("productsData.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] productData = line.split(",");

                if (productData.length >= 5) {
                    String productId = productData[0];
                    String productName = productData[1];
                    int numItems = Integer.parseInt(productData[2]);
                    double price = Double.parseDouble(productData[3]);

                    if (productId.startsWith("E")) {
                        
                        String brand = productData[4];
                        String warranty = productData[5];
                        Electronics electronics = new Electronics(productId, productName, numItems, price, brand, warranty);
                        productList.add(electronics);
                    } else if (productId.startsWith("C")) {
                        
                        String size = productData[4];
                        Clothing clothing = new Clothing(productId, productName, numItems, price, size, productData[5]);
                        productList.add(clothing);
                    } else {
                        System.out.println("Invalid product data in the file.");
                    }
                } else {
                    System.out.println("Invalid product data in the file.");
                }
            }
            System.out.println("");
        } catch (FileNotFoundException e) {

        } catch (Exception e) {
        }
    }
}