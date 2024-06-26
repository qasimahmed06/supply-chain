package org.qaswasabd.scms;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

public class Payment {
    @JsonProperty("PaymentID")
    private String paymentID;
    @JsonProperty("OrderID")
    private String orderID;
    @JsonProperty("PaymentMethod")
    private String paymentMethod;
    @JsonProperty("Amount")
    private double amount;
    @JsonProperty("PaymentDate")
    private LocalDate paymentDate;
    private static final String FILE_PATH = "src/main/resources/org/qaswasabd/scms/JSON/payments.json";

    @JsonCreator
    public Payment(@JsonProperty("PaymentID") String paymentID,
                   @JsonProperty("OrderID") String orderID,
                   @JsonProperty("PaymentMethod") String paymentMethod,
                   @JsonProperty("Amount") double amount,
                   @JsonProperty("PaymentDate") LocalDate paymentDate) {
        this.paymentID = paymentID;
        this.orderID = orderID;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    @JsonProperty("PaymentID")
    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    @JsonProperty("OrderID")
    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    @JsonProperty("PaymentMethod")
    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @JsonProperty("Amount")
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @JsonProperty("PaymentDate")
    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public int processPayment() throws IOException {
        // Check if the payment already exists in the file
        if (paymentExistsInFile()) {
            System.out.println("Payment already exists in the file.");
            return 0; // Return 0 to indicate no rows inserted
        }

        // Create a JSON object to represent the payment
        JSONObject paymentJson = new JSONObject();
        paymentJson.put("PaymentID", paymentID);
        paymentJson.put("OrderID", orderID);
        paymentJson.put("PaymentMethod", paymentMethod);
        paymentJson.put("Amount", amount);
        paymentJson.put("PaymentDate", paymentDate.toString()); // Convert Date to String

        // Read existing payments from file
        JSONArray payments = readPaymentsFromFile();

        // Add the new payment to the JSON array
        payments.put(paymentJson);

        // Write the updated payments back to the file
        writePaymentsToFile(payments);

        // Return the number of rows inserted (should always be 1)
        return 1;
    }

    private boolean paymentExistsInFile() throws IOException {
        JSONArray payments = readPaymentsFromFile();
        for (Object obj : payments) {
            JSONObject paymentJson = (JSONObject) obj;
            if (paymentJson.getString("PaymentID").equals(paymentID)) {
                return true;
            }
        }
        return false;
    }

    private JSONArray readPaymentsFromFile() throws IOException {
        // Read the content of the file
        String content = new String(Files.readAllBytes(Paths.get(FILE_PATH)));

        // If the content is empty, return an empty JSON array
        if (content.isEmpty()) {
            return new JSONArray();
        }

        // Parse the content into a JSON array
        return new JSONArray(content);
    }

    private void writePaymentsToFile(JSONArray payments) throws IOException {
        Files.write(Paths.get(FILE_PATH), payments.toString(4).getBytes());
    }
}