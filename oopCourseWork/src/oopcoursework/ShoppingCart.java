package oopcoursework;

public class ShoppingCart {
    
    private String productList;

    // Constructor to initialize the shopping cart with a product list
    public ShoppingCart(String productList) {
        this.productList = productList;
    }

    // Setter method to update the product list
    public void setProductList(String productList) {
        this.productList = productList;
    }

    // Getter method to retrieve the product list
    public String getProductList() {
        return productList;
    }
    
    // Method to add a product to the shopping cart
    public void addProduct(){
    }
    
    // Method to remove a product from the shopping cart
    public void removeProduct(){
    }
    
    // Method to calculate the total cost of the products in the shopping cart
    public void calculateTotalCost(){
    }
}
