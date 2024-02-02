package oopcoursework;

public abstract class Product {

    private String productId;
    private String productName;
    private int availabelItems;
    private double price;
    
//    constructer and pass the data to constructer
    public Product(String productId, String productName, int availabelItems, double price) {
        this.productId = productId;
        this.productName = productName;
        this.availabelItems = availabelItems;
        this.price = price;
    }
    
    
//    Setters and Getters

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setAvailabelItems(int availabelItems) {
        this.availabelItems = availabelItems;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getAvailabelItems() {
        return availabelItems;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return """
               Product
               \tproductId = """ + productId + "\n\tproductName = " + productName + "\n\tavailabelItems = " + availabelItems + "\n\tprice = " + price;
    }

    
    
}
