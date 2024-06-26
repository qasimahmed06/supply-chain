package org.qaswasabd.scms;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("finishedGoods")
public class FinishedGoods extends Product {
    @JsonProperty("ProductionDate")
    private LocalDate productionDate;
    private static final String FILE_PATH = "src/main/resources/org/qaswasabd/scms/JSON/products.json";

    @JsonCreator
    public FinishedGoods(@JsonProperty("ProductID") String productID,
                         @JsonProperty("ProductName") String productName,
                         @JsonProperty("Description") String description,
                         @JsonProperty("UnitPrice") double unitPrice,
                         @JsonProperty("QuantityInStock") int quantityInStock,
                         @JsonProperty("SupplierID") String supplierID,
                         @JsonProperty("ProductionDate") LocalDate productionDate) {
        super(productID, productName, description, unitPrice, quantityInStock, supplierID);
        this.productionDate = productionDate;
    }

    @JsonProperty("ProductionDate")
    public LocalDate getProductionDate() {
        return productionDate;
    }
    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }
    public int addProduct() throws IOException {
        // Read the current products from the file
        JSONArray products = readProductsFromFile();

        // Check if the product already exists
        for (int i = 0; i < products.length(); i++) {
            JSONObject product = products.getJSONObject(i);
            if (product.getString("ProductID").equals(super.getProductID())) {
                return -1; // Return -1 to indicate that the product already exists
            }
        }

        // Add the new product to the JSON array
        JSONObject newProduct = getProductJSONObject();
        products.put(newProduct);

        // Write the updated products back to the file
        writeProductsToFile(products);

        // Return 1 to indicate one row inserted
        return 1;
    }

    public int updateProduct() throws IOException {
        // Read the current products from the file
        JSONArray products = readProductsFromFile();

        // Find the existing product and update its information
        for (int i = 0; i < products.length(); i++) {
            JSONObject product = products.getJSONObject(i);
            if (product.getString("ProductID").equals(super.getProductID())) {
                updateProductJSONObject(product);
                // Write the updated products back to the file
                writeProductsToFile(products);
                return 1; // Return 1 to indicate one row updated
            }
        }
        // Return 0 to indicate no rows updated
        return 0;
    }

    public int deleteProduct() throws IOException {
        // Read the current products from the file
        JSONArray products = readProductsFromFile();

        // Find and remove the existing product
        for (int i = 0; i < products.length(); i++) {
            JSONObject product = products.getJSONObject(i);
            if (product.getString("ProductID").equals(super.getProductID())) {
                products.remove(i);
                // Write the updated products back to the file
                writeProductsToFile(products);
                return 1; // Return 1 to indicate one row deleted
            }
        }
        // Return 0 to indicate no rows deleted
        return 0;
    }

    private JSONObject getProductJSONObject() {
        JSONObject product = new JSONObject();
        product.put("type", "finishedGoods");
        product.put("ProductID", super.getProductID());
        product.put("ProductName", super.getProductName());
        product.put("Description", super.getDescription());
        product.put("UnitPrice", super.getUnitPrice());
        product.put("QuantityInStock", super.getQuantity());
        product.put("SupplierID", super.getSupplierID());
        product.put("ProductionDate", this.productionDate);
        return product;
    }

    private void updateProductJSONObject(JSONObject product) {
        product.put("type", "finishedGoods");
        product.put("ProductName", super.getProductName());
        product.put("Description", super.getDescription());
        product.put("UnitPrice", super.getUnitPrice());
        product.put("QuantityInStock", super.getQuantity());
        product.put("SupplierID", super.getSupplierID());
        product.put("ProductionDate", this.productionDate);
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
