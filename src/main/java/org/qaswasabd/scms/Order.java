package org.qaswasabd.scms;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {
    @JsonProperty("OrderID")
    private String orderID;
    @JsonProperty("OrderDate")
    private LocalDate orderDate;
    @JsonProperty("Products")
    private ArrayList<Product> products;
    private double totalPrice;
    @JsonProperty("CustomerInfo")
    private String customerInfo;

    private static final String FILE_PATH = "src/main/resources/org/qaswasabd/scms/JSON/orders.json";

    @JsonCreator
    public Order(@JsonProperty("OrderID") String orderID,
                 @JsonProperty("OrderDate") LocalDate orderDate,
                 @JsonProperty("Products") ArrayList<Product> products,
                 @JsonProperty("CustomerInfo") String customerInfo) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.products = products;
        this.customerInfo = customerInfo;
        this.totalPrice = calculateTotalPrice(products);
    }

    @JsonProperty("OrderID")
    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    @JsonProperty("OrderDate")
    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    @JsonProperty("Products")
    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    @JsonProperty("TotalPrice")
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @JsonProperty("CustomerInfo")
    public String getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(String customerInfo) {
        this.customerInfo = customerInfo;
    }
    public int addOrder() throws IOException {
        // Read existing orders from the file
        JSONArray orders = readOrdersFromFile();

        // Check if the order already exists
        if (getOrderIndex(orderID, orders) != -1) {
            return -1; // Return -1 to indicate no rows inserted
        }

        // Create a JSONObject for the new order
        JSONObject orderJson = new JSONObject();
        orderJson.put("OrderID", orderID);
        orderJson.put("CustomerInfo", customerInfo);
        orderJson.put("OrderDate", orderDate.toString()); // Convert LocalDate to String

        // Add products to the order JSON
        JSONArray productsJson = new JSONArray();
        for (Product product : products) {
            JSONObject productJson = new JSONObject();
            productJson.put("ProductID", product.getProductID());
            productJson.put("ProductName", product.getProductName());
            productJson.put("Description", product.getDescription());
            productJson.put("UnitPrice", product.getUnitPrice());
            productJson.put("QuantityInStock", product.getQuantity());
            productJson.put("SupplierID", product.getSupplierID());

            if (product instanceof RawMaterial) {
                productJson.put("type", "rawMaterial");
                productJson.put("MaterialType", ((RawMaterial) product).getMaterialType());
            } else if (product instanceof FinishedGoods) {
                productJson.put("type", "finishedGoods");
                productJson.put("ProductionDate", ((FinishedGoods) product).getProductionDate().toString()); // Convert LocalDate to String
            }

            productsJson.put(productJson);
        }
        orderJson.put("Products", productsJson);

        // Add the new order to the JSONArray
        orders.put(orderJson);

        // Write the updated JSON array back to the file
        writeOrdersToFile(orders);

        // Return 1 to indicate one row inserted
        return 1;
    }

    public int updateOrder() throws IOException {
        // Read existing orders from the file
        JSONArray orders = readOrdersFromFile();

        // Find the index of the order to update
        int index = getOrderIndex(orderID, orders);
        if (index == -1) {
            return -1; // Return -1 to indicate order not found
        }

        // Create a JSONObject for the updated order
        JSONObject orderJson = new JSONObject();
        orderJson.put("OrderID", orderID);
        orderJson.put("CustomerInfo", customerInfo);
        orderJson.put("OrderDate", orderDate.toString()); // Convert LocalDate to String

        // Add products to the order JSON
        JSONArray productsJson = new JSONArray();
        for (Product product : products) {
            JSONObject productJson = new JSONObject();
            productJson.put("ProductID", product.getProductID());
            productJson.put("ProductName", product.getProductName());
            productJson.put("Description", product.getDescription());
            productJson.put("UnitPrice", product.getUnitPrice());
            productJson.put("QuantityInStock", product.getQuantity());
            productJson.put("SupplierID", product.getSupplierID());

            if (product instanceof RawMaterial) {
                productJson.put("type", "rawMaterial");
                productJson.put("MaterialType", ((RawMaterial) product).getMaterialType());
            } else if (product instanceof FinishedGoods) {
                productJson.put("type", "finishedGoods");
                productJson.put("ProductionDate", ((FinishedGoods) product).getProductionDate().toString()); // Convert LocalDate to String
            }

            productsJson.put(productJson);
        }
        orderJson.put("Products", productsJson);

        // Update the order in the JSONArray
        orders.put(index, orderJson);

        // Write the updated JSON array back to the file
        writeOrdersToFile(orders);

        // Return 1 to indicate one row updated
        return 1;
    }

    public int removeOrder() throws IOException {
        // Read existing orders from the file
        JSONArray orders = readOrdersFromFile();

        // Find the index of the order to remove
        int index = getOrderIndex(orderID, orders);
        if (index == -1) {
            return -1; // Return -1 to indicate order not found
        }

        // Remove the order from the JSONArray
        orders.remove(index);

        // Write the updated JSON array back to the file
        writeOrdersToFile(orders);

        // Return 1 to indicate one row removed
        return 1;
    }

    private double calculateTotalPrice(List<Product> products) {
        return products.stream().mapToDouble(Product::getUnitPrice).sum();
    }

    private JSONArray readOrdersFromFile() throws IOException {
        // Read existing orders from the file
        String content = new String(Files.readAllBytes(Paths.get(FILE_PATH)));

        // If the content is empty, return an empty JSON array
        if (content.isEmpty()) {
            return new JSONArray();
        }

        // Parse the content into a JSON array
        return new JSONArray(content);
    }
    private static JSONArray readOrdersFromFile2() throws IOException {
        // Read existing orders from the file
        String content = new String(Files.readAllBytes(Paths.get(FILE_PATH)));

        // If the content is empty, return an empty JSON array
        if (content.isEmpty()) {
            return new JSONArray();
        }

        // Parse the content into a JSON array
        return new JSONArray(content);
    }

    private void writeOrdersToFile(JSONArray orders) throws IOException {
        // Write the updated JSON array back to the file with formatted indentation
        Files.write(Paths.get(FILE_PATH), orders.toString(4).getBytes());
    }

    private int getOrderIndex(String orderID, JSONArray orders) {
        // Find the index of the order in the JSONArray based on orderID
        for (int i = 0; i < orders.length(); i++) {
            JSONObject order = orders.getJSONObject(i);
            if (order.getString("OrderID").equals(orderID)) {
                return i;
            }
        }
        return -1;
    }

    public static double[] getHistoricalSalesData(String productID) throws IOException {
        List<Double> salesData = new ArrayList<>();
        JSONArray orders = readOrdersFromFile2();

        // Iterate over each order
        for (int i = 0; i < orders.length(); i++) {
            JSONObject order = orders.getJSONObject(i);
            JSONArray productsArray = order.getJSONArray("Products");

            // Iterate over each product in the order
            for (int j = 0; j < productsArray.length(); j++) {
                JSONObject product = productsArray.getJSONObject(j);
                if (product.getString("ProductID").equals(productID)) {
                    salesData.add(product.getDouble("UnitPrice"));
                }
            }
        }

        // Convert List<Double> to double[]
        return salesData.stream().mapToDouble(Double::doubleValue).toArray();
    }
}