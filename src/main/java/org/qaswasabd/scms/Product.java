package org.qaswasabd.scms;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.io.IOException;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = FinishedGoods.class, name = "finishedGoods"),
        @JsonSubTypes.Type(value = RawMaterial.class, name = "rawMaterial")
})
public abstract class Product {
    private String productID;
    private String productName;
    private String description;
    private double unitPrice;
    private int quantity;
    private String supplierID;

    public Product(String productID, String productName, String description, double unitPrice, int quantityInStock, String supplierID) {
        this.productID = productID;
        this.productName = productName;
        this.description = description;
        this.unitPrice = unitPrice;
        this.quantity = quantityInStock;
        this.supplierID = supplierID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) throws IOException {
        this.productID = productID;
        updateProduct();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) throws IOException {
        this.productName = productName;
        updateProduct();

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws IOException {
        this.description = description;
        updateProduct();

    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) throws IOException {
        this.unitPrice = unitPrice;
        updateProduct();

    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) throws IOException {
        this.quantity = quantity;
        updateProduct();

    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) throws IOException {
        this.supplierID = supplierID;
        updateProduct();
    }

    public abstract int addProduct() throws IOException;
    public abstract int updateProduct() throws IOException;
    public abstract int deleteProduct() throws IOException;
}
