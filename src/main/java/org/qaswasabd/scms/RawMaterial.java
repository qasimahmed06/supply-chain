package org.qaswasabd.scms;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONArray;
import org.json.JSONObject;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("rawMaterial")
public class RawMaterial extends Product {
    @JsonProperty("MaterialType")
    private String materialType;
    private static final String FILE_PATH = "src/main/resources/org/qaswasabd/scms/JSON/`products.json";

    @JsonCreator
    public RawMaterial(@JsonProperty("ProductID") String productID,
                       @JsonProperty("ProductName") String productName,
                       @JsonProperty("Description") String description,
                       @JsonProperty("UnitPrice") double unitPrice,
                       @JsonProperty("QuantityInStock") int quantityInStock,
                       @JsonProperty("SupplierID") String supplierID,
                       @JsonProperty("MaterialType") String materialType) {
        super(productID, productName, description, unitPrice, quantityInStock, supplierID);
        this.materialType = materialType;
    }

    @JsonProperty("MaterialType")
    public String getMaterialType() {
        return materialType;
    }
    public void setMaterialType(String materialType){
        this.materialType = materialType;
    }

    public int addProduct() throws IOException {
        // Create a JSONObject to represent the product
        JSONObject productJson = new JSONObject();

        // Add attributes of the product to the JSON object
        productJson.put("type", "rawMaterial");
        productJson.put("ProductID", this.getProductID());
        productJson.put("ProductName", this.getProductName());
        productJson.put("Description", this.getDescription());
        productJson.put("UnitPrice", this.getUnitPrice());
        productJson.put("QuantityInStock", this.getQuantity());
        productJson.put("SupplierID", this.getSupplierID());
        productJson.put("MaterialType", this.materialType);

        // Read the current products from the file
        JSONArray products = readProductsFromFile();

        // Check if the product already exists
        if (getProductIndex(this.getProductID(), products) != -1) {
            return -1; // Return -1 to indicate no rows inserted
        }

        // Add the new product to the JSON array
        products.put(productJson);

        // Write the updated products back to the file
        writeProductsToFile(products);

        // Return 1 to indicate one row inserted
        return 1;
    }

    public int updateProduct() throws IOException {
        // Create a JSONObject to represent the updated product
        JSONObject updatedProductJson = new JSONObject();

        // Add attributes of the updated product to the JSON object
        updatedProductJson.put("type", "rawMaterial");
        updatedProductJson.put("ProductID", this.getProductID());
        updatedProductJson.put("ProductName", this.getProductName());
        updatedProductJson.put("Description", this.getDescription());
        updatedProductJson.put("UnitPrice", this.getUnitPrice());
        updatedProductJson.put("QuantityInStock", this.getQuantity());
        updatedProductJson.put("SupplierID", this.getSupplierID());
        updatedProductJson.put("MaterialType", this.materialType);

        // Read the current products from the file
        JSONArray products = readProductsFromFile();

        // Find the index of the existing product
        int index = getProductIndex(this.getProductID(), products);
        if (index == -1) {
            System.out.println("Product does not exist in the database.");
            return 0; // Return 0 to indicate no rows updated
        }

        // Update the product in the JSON array
        products.put(index, updatedProductJson);

        // Write the updated products back to the file
        writeProductsToFile(products);

        // Return 1 to indicate one row updated
        return 1;
    }

    public int deleteProduct() throws IOException {
        // Read the current products from the file
        JSONArray products = readProductsFromFile();

        // Find the index of the existing product
        int index = getProductIndex(this.getProductID(), products);
        if (index == -1) {
            System.out.println("Product does not exist in the database.");
            return 0; // Return 0 to indicate no rows deleted
        }

        // Remove the product from the JSON array
        products.remove(index);

        // Write the updated products back to the file
        writeProductsToFile(products);

        // Return 1 to indicate one row deleted
        return 1;
    }

    private int getProductIndex(String productID, JSONArray products) {
        for (int i = 0; i < products.length(); i++) {
            JSONObject product = products.getJSONObject(i);
            if (product.getString("ProductID").equals(productID)) {
                return i;
            }
        }
        return -1; // Product not found
    }

    private JSONArray readProductsFromFile() throws IOException {
        // Read the content of the file
        String content = new String(Files.readAllBytes(Paths.get(FILE_PATH)));

        // If the content is empty, return an empty JSON array
        if (content.isEmpty()) {
            return new JSONArray();
        }

        // Parse the content into a JSON array
        return new JSONArray(content);
    }

    private void writeProductsToFile(JSONArray products) throws IOException {
        Files.write(Paths.get(FILE_PATH), products.toString(4).getBytes());
    }
}
