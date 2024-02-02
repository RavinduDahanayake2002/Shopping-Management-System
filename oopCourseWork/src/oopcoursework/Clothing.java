package oopcoursework;

public class Clothing extends Product{
    private String Size;
    private String color;
    
//    constructer and pass the data to constructer
    public Clothing(String productId, String productName, int availabelItems, double price,String Size, String color) {
        super(productId, productName, availabelItems, price);
        this.Size = Size;
        this.color = color;
    }
    
    
//    Setters and Getters
    public void setSize(String Size) {
        this.Size = Size;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return Size;
    }

    public String getColor() {
        return color;
    }
    
    @Override
    public String toString() {
        return "Clothing\n\tproductId = " + super.getProductId() + "\n\tproductName = " + super.getProductName() + "\n\tavailableItems = " + super.getAvailabelItems() + "\n\tprice = " + super.getPrice()  + "\n\tSize = " + Size + "\n\tcolor = " + color;
    }
    
    
}
