package org.qaswasabd.scms;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Customer {
    private String customerID;
    private String customerName;
    private String contactInfo;
    private String address;
    private static final String FILE_PATH = "src/main/resources/org/qaswasabd/scms/JSON/customers.json";

    @JsonCreator
    public Customer(@JsonProperty("CustomerID") String customerID,
                    @JsonProperty("CustomerName") String customerName,
                    @JsonProperty("ContactInfo") String contactInfo,
                    @JsonProperty("Address") String address) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.contactInfo = contactInfo;
        this.address = address;
    }

    @JsonProperty("CustomerID")
    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    @JsonProperty("CustomerName")
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @JsonProperty("ContactInfo")
    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    @JsonProperty("Address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int addCustomer() throws IOException {
        // Read existing customers from the file
        JSONArray customers = readCustomersFromFile();

        // Check if the customer already exists
        if (getCustomerIndex(customerID, customers) != -1) {
            return -1; // Return -1 to indicate no rows inserted
        }

        // Create a JSONObject for the new customer
        JSONObject customerJson = new JSONObject();
        customerJson.put("CustomerID", customerID);
        customerJson.put("CustomerName", customerName);
        customerJson.put("ContactInfo", contactInfo);
        customerJson.put("Address", address);

        // Add the new customer to the JSONArray
        customers.put(customerJson);

        // Write the updated JSON array back to the file
        writeCustomersToFile(customers);

        // Return 1 to indicate one row inserted
        return 1;
    }

    public int updateCustomer() throws IOException {
        // Read existing customers from the file
        JSONArray customers = readCustomersFromFile();

        // Find the index of the customer to update
        int index = getCustomerIndex(customerID, customers);
        if (index == -1) {
            return -1; // Customer not found
        }

        // Update the customer details
        JSONObject customerJson = customers.getJSONObject(index);
        customerJson.put("CustomerName", customerName);
        customerJson.put("ContactInfo", contactInfo);
        customerJson.put("Address", address);

        // Write the updated JSON array back to the file
        writeCustomersToFile(customers);

        // Return 1 to indicate one row updated
        return 1;
    }

    private int getCustomerIndex(String customerID, JSONArray customers) {
        for (int i = 0; i < customers.length(); i++) {
            JSONObject customer = customers.getJSONObject(i);
            if (customer.getString("CustomerID").equals(customerID)) {
                return i;
            }
        }
        return -1; // Customer not found
    }

    private JSONArray readCustomersFromFile() throws IOException {
        // Read the content of the file
        String content = new String(Files.readAllBytes(Paths.get(FILE_PATH)));

        // If the content is empty, return an empty JSON array
        if (content.isEmpty()) {
            return new JSONArray();
        }

        // Parse the content into a JSON array
        return new JSONArray(content);
    }

    private void writeCustomersToFile(JSONArray customers) throws IOException {
        Files.write(Paths.get(FILE_PATH), customers.toString(4).getBytes());
    }
}
