package org.qaswasabd.scms;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Inventory {
    @JsonProperty("InventoryID")
    private String inventoryID;
    @JsonProperty("Products")
    private ArrayList<Product> products;
    @JsonProperty("WarehouseID")
    private String warehouseID;
    @JsonProperty("Quantity")
    private int quantity;
    private static final String FILE_PATH = "src/main/resources/org/qaswasabd/scms/JSON/inventory.json";

    @JsonCreator
    public Inventory(@JsonProperty("InventoryID") String inventoryID,
                     @JsonProperty("WarehouseID") String warehouseID,
                     @JsonProperty("Quantity") int quantity,
                     @JsonProperty("Products") ArrayList<Product> products) {
        this.inventoryID = inventoryID;
        this.warehouseID = warehouseID;
        this.quantity = quantity;
        this.products = products;
    }

    @JsonProperty("InventoryID")
    public String getInventoryID() {
        return inventoryID;
    }
    public void setInventoryID(String inventoryID) {
        this.inventoryID = inventoryID;
    }

    @JsonProperty("Products")
    public ArrayList<Product> getProducts() {
        return products;
    }
    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    @JsonProperty("WarehouseID")
    public String getWarehouseID() {
        return warehouseID;
    }
    public void setWarehouseID(String warehouseID) {
        this.warehouseID = warehouseID;
    }

    @JsonProperty("Quantity")
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int addInventoryItem() throws IOException {
        // Read existing inventory items from the file
        JSONArray inventoryItems = readInventoryFromFile();

        // Check if the inventory item already exists
        if (getInventoryItemIndex(inventoryID, inventoryItems) != -1) {
            return -1; // Return -1 to indicate no rows inserted
        }

        // Create a JSONObject for the new inventory item
        JSONObject inventoryJson = new JSONObject();
        inventoryJson.put("InventoryID", inventoryID);
        inventoryJson.put("WarehouseID", warehouseID);
        inventoryJson.put("Quantity", quantity);

        // Add products to the inventory JSON
        JSONArray productsJson = new JSONArray();
        for (Product product : products) {
            JSONObject productJson = new JSONObject();
            productJson.put("ProductID", product.getProductID());
            productJson.put("ProductName", product.getProductName());
            productJson.put("Description", product.getDescription());
            productJson.put("UnitPrice", product.getUnitPrice());
            productJson.put("QuantityInStock", product.getQuantity());
            productJson.put("SupplierID", product.getSupplierID());
            if(product instanceof RawMaterial) {
                productJson.put("type", "rawMaterial");
                productJson.put("MaterialType", ((RawMaterial) product).getMaterialType());
            }else{
                productJson.put("type", "finishedGoods");
                productJson.put("ProductionDate", ((FinishedGoods) product).getProductionDate());
            }
            productsJson.put(productJson);
        }
        inventoryJson.put("Products", productsJson);

        // Add the new inventory item to the JSONArray
        inventoryItems.put(inventoryJson);

        // Write the updated JSON array back to the file
        writeInventoryToFile(inventoryItems);

        // Return 1 to indicate one row inserted
        return 1;
    }

    public int updateInventoryItem() throws IOException {
        // Read existing inventory items from the file
        JSONArray inventoryItems = readInventoryFromFile();

        // Find the index of the inventory item to update
        int index = getInventoryItemIndex(inventoryID, inventoryItems);
        if (index == -1) {
            return -1; // Inventory item not found
        }

        // Update the inventory item details
        JSONObject inventoryJson = new JSONObject();
        inventoryJson.put("InventoryID", inventoryID);
        inventoryJson.put("WarehouseID", warehouseID);
        inventoryJson.put("Quantity", quantity);

        // Add products to the inventory JSON
        JSONArray productsJson = new JSONArray();
        for (Product product : products) {
            JSONObject productJson = new JSONObject();
            productJson.put("ProductID", product.getProductID());
            productJson.put("ProductName", product.getProductName());
            productJson.put("Description", product.getDescription());
            productJson.put("UnitPrice", product.getUnitPrice());
            productJson.put("QuantityInStock", product.getQuantity());
            productJson.put("SupplierID", product.getSupplierID());
            if(product instanceof RawMaterial) {
                productJson.put("type", "rawMaterial");
                productJson.put("MaterialType", ((RawMaterial) product).getMaterialType());
            } else {
                productJson.put("type", "finishedGoods");
                productJson.put("ProductionDate", ((FinishedGoods) product).getProductionDate());
            }
            productsJson.put(productJson);
        }
        inventoryJson.put("Products", productsJson);

        // Replace the existing inventory item at the found index
        inventoryItems.put(index, inventoryJson);

        // Write the updated JSON array back to the file
        writeInventoryToFile(inventoryItems);

        // Return 1 to indicate one row updated
        return 1;
    }


    public int deleteInventoryItem() throws IOException {
        // Read existing inventory items from the file
        JSONArray inventoryItems = readInventoryFromFile();

        // Find the index of the inventory item to delete
        int index = getInventoryItemIndex(inventoryID, inventoryItems);
        if (index == -1) {
            return -1; // Inventory item not found
        }

        // Remove the inventory item from the JSONArray
        inventoryItems.remove(index);

        // Write the updated JSON array back to the file
        writeInventoryToFile(inventoryItems);

        // Return 1 to indicate one row deleted
        return 1;
    }

    private int getInventoryItemIndex(String inventoryID, JSONArray inventoryItems) {
        for (int i = 0; i < inventoryItems.length(); i++) {
            JSONObject inventoryItem = inventoryItems.getJSONObject(i);
            if (inventoryItem.getString("InventoryID").equals(inventoryID)) {
                return i;
            }
        }
        return -1; // Inventory item not found
    }

    private JSONArray readInventoryFromFile() throws IOException {
        // Read the content of the file
        String content = new String(Files.readAllBytes(Paths.get(FILE_PATH)));

        // If the content is empty, return an empty JSON array
        if (content.isEmpty()) {
            return new JSONArray();
        }

        // Parse the content into a JSON array
        return new JSONArray(content);
    }

    private void writeInventoryToFile(JSONArray inventoryItems) throws IOException {
        Files.write(Paths.get(FILE_PATH), inventoryItems.toString(4).getBytes());
    }
}