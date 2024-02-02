package oopcoursework;

import java.util.ArrayList;
import java.util.Scanner;

public interface ShoppingManager {
    
    // Method to add products to the product list
    public void addProducts(Scanner scanner, ArrayList<Product> productList);

    // Method to add an Electronics product to the product list
    Electronics AddElectronicsProduct(Scanner scan);

    // Method to add a Clothing product to the product list
    Clothing AddClothingProduct(Scanner scan);

    // Method to print the details of products in the product list
    public void printProducts(ArrayList<Product> productList);

    // Method to delete products from the product list
    public void deleteProducts(Scanner scanner, ArrayList<Product> productList);

    // Method to save product data to a file
    public void savetofile();

    // Method to load product data from a file
    public void loadFromFile();
}
