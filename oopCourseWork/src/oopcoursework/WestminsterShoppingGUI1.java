package oopcoursework;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class WestminsterShoppingGUI1 extends JFrame {

    private static final ArrayList<Product> cartLIST = new ArrayList<>();
    private JTable table;
    private JLabel totalPriceLabel;
    private JLabel discount;
    private JLabel Purdiscount;
    private JLabel Finaltot;
    private Map<Product, Integer> productCountMap;
    private boolean newUser;

    WestminsterShoppingGUI1(boolean newUser) {
        this.newUser = newUser;
        JPanel detailsy = new JPanel();
        detailsy.setBounds(-5, 300, 1000, 300);
        detailsy.setLayout(null);
        add(detailsy);

        JLabel pdLabel = new JLabel("Total: ");
        pdLabel.setBounds(380, 40, 200, 20);
        pdLabel.setFont(new Font("arial", Font.PLAIN, 12));
        pdLabel.setHorizontalAlignment(SwingConstants.LEFT);
        detailsy.add(pdLabel);

        JLabel catLabel = new JLabel("First Purchase Discount(10%): ");
        catLabel.setBounds(240, 80, 200, 20);
        catLabel.setFont(new Font("arial", Font.PLAIN, 12));
        catLabel.setHorizontalAlignment(SwingConstants.LEFT);
        detailsy.add(catLabel);

        JLabel naLabel = new JLabel("Three items in the same category Discount(20%): ");
        naLabel.setBounds(140, 120, 280, 20);
        naLabel.setFont(new Font("arial", Font.PLAIN, 12));
        detailsy.add(naLabel);

        JLabel siLabel = new JLabel("Final Total: ");
        siLabel.setBounds(345, 160, 200, 20);
        siLabel.setFont(new Font("arial", Font.BOLD, 12));
        detailsy.add(siLabel);

        totalPriceLabel = new JLabel("");
        totalPriceLabel.setBounds(430, 40, 200, 20);
        totalPriceLabel.setFont(new Font("arial", Font.PLAIN, 12));
        detailsy.add(totalPriceLabel);

        discount = new JLabel("");
        discount.setBounds(430, 120, 200, 20);
        discount.setFont(new Font("arial", Font.PLAIN, 12));
        detailsy.add(discount);
        
        Purdiscount = new JLabel("");
        Purdiscount.setBounds(430, 80, 200, 20);
        Purdiscount.setFont(new Font("arial", Font.PLAIN, 12));
        detailsy.add(Purdiscount);
        
        Finaltot = new JLabel("");
        Finaltot.setBounds(430, 160, 200, 20);
        Finaltot.setFont(new Font("arial", Font.PLAIN, 12));
        detailsy.add(Finaltot);

        JPanel detailsx = new JPanel();
        detailsx.setLayout(null);
        add(detailsx);

        String data[][] = {};

        String col[] = {"Product", "Quantity", "Price"};
        table = new JTable(data, col);

        JTableHeader tableHeader = table.getTableHeader();
        Dimension headerSize = tableHeader.getPreferredSize();
        headerSize.height = 40;
        tableHeader.setPreferredSize(headerSize);

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(50, 40, 700, 150);
        detailsx.add(pane);
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowHeight(55);
        pane.setVerticalScrollBarPolicy(pane.VERTICAL_SCROLLBAR_NEVER);

        setVisible(false);
        setTitle("Shopping Cart");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        call the updatetable method
        updateTable();
//        System.out.println(newUser);
    }

    private double calculateTotalPrice() {
        double totalPrice = 0;

        for (Map.Entry<Product, Integer> entry : productCountMap.entrySet()) {
            Product p = entry.getKey();
            int quantity = entry.getValue();
            totalPrice += p.getPrice() * quantity;
        }

        return totalPrice;
    }

    public void add(Product product) {
        cartLIST.add(product);
        updateTable();
    }

    public void updateTable() {
//        Column names for the shopping cart table
        String col[] = {"Product", "Quantity", "Price"};

//        Map to store the count of each product in the shopping cart
        productCountMap = new HashMap<>();

        for (Product product : cartLIST) {
            productCountMap.put(product, productCountMap.getOrDefault(product, 0) + 1);
        }

//         Variables to store total price, unique rows, and table data
        double totalPrice = 0;
        int uniqueRows = productCountMap.size();
        Object[][] data = new Object[uniqueRows][3];
        int i = 0;

        
        for (Map.Entry<Product, Integer> entry : productCountMap.entrySet()) {
            Product p = entry.getKey();
            int quantity = entry.getValue();
            data[i][0] = p.getProductName();
            data[i][1] = quantity;
            data[i][2] = p.getPrice() * quantity;
            totalPrice += p.getPrice() * quantity;
            i++;
        }

        DefaultTableModel model = new DefaultTableModel(data, col);
        table.setModel(model);

        double categoryDiscount = calculateCategoryDiscount();
        double twentyDiscount = totalPrice * categoryDiscount;
        double finalTotal = totalPrice - twentyDiscount - newUserDiscount();

        
//        Calculate category discount, 20% discount, and the final total
        totalPriceLabel.setText(String.valueOf(totalPrice));
        discount.setText(String.valueOf(twentyDiscount));
        Purdiscount.setText(String.valueOf(newUserDiscount()));
        Finaltot.setText(String.valueOf(finalTotal));

    }

    private double calculateCategoryDiscount() {
        Map<String, Integer> categoryCountMap = new HashMap<>();

        for (Product product : cartLIST) {
            String category = (product instanceof Electronics) ? "Electronics" : "Clothing";
            categoryCountMap.put(category, categoryCountMap.getOrDefault(category, 0) + 1);
        }

        double discount = 0;

        for (int count : categoryCountMap.values()) {
            if (count >= 3) {
                // Apply 20% discount for each category with three or more items
                discount += 0.20;
            }
        }

        return discount;
    }

    private double newUserDiscount() {
//        Check if the user is a new user
        if (newUser) {
//            Apply a 10% discount to the total price for new users
            return calculateTotalPrice() * 0.10;
        }
       return 0;
    }
}