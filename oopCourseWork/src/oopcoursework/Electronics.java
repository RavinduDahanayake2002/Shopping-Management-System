package oopcoursework;


public class Electronics extends Product{
    private String brand;
    private String warrentyPeriod;

//    constructer and pass the data to constructer
    public Electronics(String productId, String productName, int availabelItems, double price,String brand, String warrentyPeriod) {
        super(productId, productName, availabelItems, price);
        this.brand = brand;
        this.warrentyPeriod = warrentyPeriod;
    }

//    Setters and Getters
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setWarrentyPeriod(String warrentyPeriod) {
        this.warrentyPeriod = warrentyPeriod;
    }

    public String getBrand() {
        return brand;
    }

    public String getWarrentyPeriod() {
        return warrentyPeriod;
    }  

    @Override
    public String toString() {
        return "Electronic\n\tproductId = " + super.getProductId() + "\n\tproductName = " + super.getProductName() + "\n\tavailableItems = " + super.getAvailabelItems() + "\n\tprice = " + super.getPrice()  + "\n\tBrand = " + brand + "\n\tWarrenty Period = " + warrentyPeriod;
    }

    
}
