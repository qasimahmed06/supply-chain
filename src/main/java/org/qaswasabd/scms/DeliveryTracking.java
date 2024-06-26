package org.qaswasabd.scms;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

public class DeliveryTracking {
    @JsonProperty("DeliveryID")
    private String deliveryID;
    @JsonProperty("OrderID")
    private String orderID;
    @JsonProperty("CarrierInfo")
    private String carrierInfo;
    @JsonProperty("EstimatedDeliveryDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate estimatedDeliveryDate;
    @JsonProperty("ActualDeliveryDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate actualDeliveryDate = null;
    @JsonProperty("DeliveryStatus")
    private Status deliveryStatus;
    private static final String FILE_PATH = "src/main/resources/org/qaswasabd/scms/JSON/delivery_tracking.json";

    // Constructor with Jackson annotations
    public DeliveryTracking(@JsonProperty("DeliveryID") String deliveryID,
                            @JsonProperty("OrderID") String orderID,
                            @JsonProperty("CarrierInfo") String carrierInfo,
                            @JsonProperty("EstimatedDeliveryDate") LocalDate estimatedDeliveryDate) {
        this.deliveryID = deliveryID;
        this.orderID = orderID;
        this.carrierInfo = carrierInfo;
        this.estimatedDeliveryDate = estimatedDeliveryDate;
        this.deliveryStatus = Status.PROCESSING;
    }

    @JsonProperty("DeliveryID")
    public String getDeliveryID() {
        return deliveryID;
    }

    public void setDeliveryID(String deliveryID) {
        this.deliveryID = deliveryID;
    }

    @JsonProperty("OrderID")
    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    @JsonProperty("CarrierInfo")
    public String getCarrierInfo() {
        return carrierInfo;
    }

    public void setCarrierInfo(String carrierInfo) {
        this.carrierInfo = carrierInfo;
    }

    @JsonProperty("EstimatedDeliveryDate")
    public LocalDate getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public void setEstimatedDeliveryDate(LocalDate estimatedDeliveryDate) {
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    @JsonProperty("ActualDeliveryDate")
    public LocalDate getActualDeliveryDate() {
        return actualDeliveryDate;
    }

    public void setActualDeliveryDate(LocalDate actualDeliveryDate) {
        this.actualDeliveryDate = actualDeliveryDate;
    }

    @JsonProperty("DeliveryStatus")
    public Status getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(Status deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    // Method to update delivery status
    public int updateDeliveryStatus() throws IOException {
        // Read existing delivery tracking records from the file
        JSONArray deliveryTrackingRecords = readDeliveryTrackingRecordsFromFile();

        // Find the index of the delivery tracking record to update
        int index = getDeliveryTrackingRecordIndex(deliveryID, deliveryTrackingRecords);
        if (index == -1) {
            return -1; // Record not found
        }

        // Update the delivery tracking record
        JSONObject deliveryTrackingJson = deliveryTrackingRecords.getJSONObject(index);
        deliveryTrackingJson.put("DeliveryStatus", deliveryStatus.toString()); // Update delivery status as a string
        deliveryTrackingJson.put("ActualDeliveryDate", actualDeliveryDate != null ? actualDeliveryDate.toString() : null); // Update actual delivery date as a string or null
        deliveryTrackingJson.put("CarrierInfo", carrierInfo); // Update carrier info
        deliveryTrackingJson.put("EstimatedDeliveryDate", estimatedDeliveryDate.toString()); // Update estimated delivery date as a string

        // Write the updated JSON array back to the file
        writeDeliveryTrackingRecordsToFile(deliveryTrackingRecords);

        // Return 1 to indicate one record updated
        return 1;
    }

    // Method to add delivery tracking
    public int addDeliveryTracking() throws IOException {
        // Read existing delivery tracking records from the file
        JSONArray deliveryTrackingRecords = readDeliveryTrackingRecordsFromFile();

        // Check if the delivery tracking record already exists
        if (getDeliveryTrackingRecordIndex(deliveryID, deliveryTrackingRecords) != -1) {
            return -1; // Record already exists
        }

        // Create a new JSON object for the new delivery tracking record
        JSONObject newRecord = new JSONObject();
        newRecord.put("DeliveryID", deliveryID);
        newRecord.put("OrderID", orderID);
        newRecord.put("CarrierInfo", carrierInfo);
        newRecord.put("EstimatedDeliveryDate", estimatedDeliveryDate.toString()); // Convert LocalDate to string
        newRecord.put("ActualDeliveryDate", actualDeliveryDate != null ? actualDeliveryDate.toString() : null); // Convert LocalDate to string or null
        newRecord.put("DeliveryStatus", deliveryStatus.toString()); // Convert Status enum to string

        // Add the new record to the JSON array
        deliveryTrackingRecords.put(newRecord);

        // Write the updated JSON array back to the file
        writeDeliveryTrackingRecordsToFile(deliveryTrackingRecords);

        // Return 1 to indicate one record added
        return 1;
    }

    // Method to read delivery tracking records from file
    private JSONArray readDeliveryTrackingRecordsFromFile() throws IOException {
        // Read the content of the file
        String content = new String(Files.readAllBytes(Paths.get(FILE_PATH)));

        // If the content is empty, return an empty JSON array
        if (content.isEmpty()) {
            return new JSONArray();
        }

        // Parse the content into a JSON array
        return new JSONArray(content);
    }

    // Method to write delivery tracking records to file
    private void writeDeliveryTrackingRecordsToFile(JSONArray deliveryTrackingRecords) throws IOException {
        Files.write(Paths.get(FILE_PATH), deliveryTrackingRecords.toString(4).getBytes());
    }

    // Method to get delivery tracking record index
    private int getDeliveryTrackingRecordIndex(String deliveryID, JSONArray deliveryTrackingRecords) {
        for (int i = 0; i < deliveryTrackingRecords.length(); i++) {
            JSONObject record = deliveryTrackingRecords.getJSONObject(i);
            if (record.getString("DeliveryID").equals(deliveryID)) {
                return i;
            }
        }
        return -1; // Record not found
    }
}
