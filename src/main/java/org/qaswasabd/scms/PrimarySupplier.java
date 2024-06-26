package org.qaswasabd.scms;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class PrimarySupplier implements InterfaceSupplier {
    @JsonProperty("SupplierID")
    private String supplierID;
    @JsonProperty("SupplierName")
    private String supplierName;
    @JsonProperty("ContactInfo")
    private String contactInfo;
    @JsonProperty("Products")
    private ArrayList<Product> products;
    private static final String FILE_PATH = "src/main/resources/org/qaswasabd/scms/JSON/suppliers.json";

    @JsonCreator
    public PrimarySupplier(@JsonProperty("SupplierID") String supplierID,
                           @JsonProperty("SupplierName") String supplierName,
                           @JsonProperty("ContactInfo") String contactInfo,
                           @JsonProperty("Products") ArrayList<Product> products) {
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.contactInfo = contactInfo;
        this.products = products;
    }
    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    public int addSupplier() throws IOException {
        // Create a JSONObject to represent the supplier
        JSONObject supplierJson = new JSONObject();
        supplierJson.put("SupplierID", this.supplierID);
        supplierJson.put("SupplierName", this.supplierName);
        supplierJson.put("ContactInfo", this.contactInfo);
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

            // Add more properties as needed
            productsJson.put(productJson);
        }
        supplierJson.put("Products", productsJson);


        // Read the current suppliers from the file
        JSONArray suppliers = readSuppliersFromFile();

        // Check if the supplier already exists
        if (getSupplierIndex(this.supplierID, suppliers) != -1) {
            return -1; // Return -1 to indicate no rows inserted
        }

        // Add the new supplier to the JSON array
        suppliers.put(supplierJson);

        // Write the updated suppliers back to the file
        writeSuppliersToFile(suppliers);

        // Return 1 to indicate one row inserted
        return 1;
    }

    @Override
    public int updateSupplier() throws IOException {
        // Create a JSONObject to represent the updated supplier
        JSONObject updatedSupplierJson = new JSONObject();
        updatedSupplierJson.put("SupplierID", this.supplierID);
        updatedSupplierJson.put("SupplierName", this.supplierName);
        updatedSupplierJson.put("ContactInfo", this.contactInfo);
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

            // Add more properties as needed
            productsJson.put(productJson);
        }
        updatedSupplierJson.put("Products", productsJson);

        // Read the current suppliers from the file
        JSONArray suppliers = readSuppliersFromFile();

        // Find the index of the existing supplier
        int index = getSupplierIndex(this.supplierID, suppliers);
        if (index == -1) {
            System.out.println("Supplier does not exist.");
            return 0; // Return 0 to indicate no rows updated
        }

        // Update the supplier in the JSON array
        suppliers.put(index, updatedSupplierJson);

        // Write the updated suppliers back to the file
        writeSuppliersToFile(suppliers);

        // Return 1 to indicate one row updated
        return 1;
    }

    @Override
    public void deleteSupplier() throws IOException {
        // Read the current suppliers from the file
        JSONArray suppliers = readSuppliersFromFile();

        // Find the index of the existing supplier
        int index = getSupplierIndex(this.supplierID, suppliers);
        if (index == -1) {
            System.out.println("Supplier does not exist.");
            return; // Supplier not found
        }

        // Remove the supplier from the JSON array
        suppliers.remove(index);

        // Write the updated suppliers back to the file
        writeSuppliersToFile(suppliers);
    }

    private int getSupplierIndex(String supplierID, JSONArray suppliers) {
        for (int i = 0; i < suppliers.length(); i++) {
            JSONObject supplier = suppliers.getJSONObject(i);
            if (supplier.getString("SupplierID").equals(supplierID)) {
                return i;
            }
        }
        return -1; // Supplier not found
    }

    private JSONArray readSuppliersFromFile() throws IOException {
        // Read the content of the file
        String content = new String(Files.readAllBytes(Paths.get(FILE_PATH)));

        // If the content is empty, return an empty JSON array
        if (content.isEmpty()) {
            return new JSONArray();
        }

        // Parse the content into a JSON array
        return new JSONArray(content);
    }

    private void writeSuppliersToFile(JSONArray suppliers) throws IOException {
        Files.write(Paths.get(FILE_PATH), suppliers.toString(4).getBytes());
    }
}
