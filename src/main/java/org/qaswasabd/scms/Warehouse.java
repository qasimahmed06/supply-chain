    package org.qaswasabd.scms;

    import com.fasterxml.jackson.annotation.JsonCreator;
    import com.fasterxml.jackson.annotation.JsonProperty;
    import org.json.JSONArray;
    import org.json.JSONObject;
    import java.io.IOException;
    import java.nio.file.Files;
    import java.nio.file.Paths;
    import java.util.ArrayList;

    public class Warehouse {
        @JsonProperty("WarehouseID")
        private String warehouseID;
        @JsonProperty("WarehouseName")
        private String warehouseName;
        @JsonProperty("Location")
        private String location;
        @JsonProperty("Capacity")
        private int capacity;
        @JsonProperty("Inventories")
        private ArrayList<Inventory> inventories;
        private static final String FILE_PATH = "src/main/resources/org/qaswasabd/scms/JSON/warehouses.json";

        public Warehouse(){
        }
        @JsonCreator
        public Warehouse(@JsonProperty("WarehouseID") String warehouseID,
                         @JsonProperty("WarehouseName") String warehouseName,
                         @JsonProperty("Location") String location,
                         @JsonProperty("Capacity") int capacity,
                         @JsonProperty("Inventories") ArrayList<Inventory> inventories) {
            this.warehouseID = warehouseID;
            this.warehouseName = warehouseName;
            this.location = location;
            this.capacity = capacity;
            this.inventories = inventories;
        }

        @JsonProperty("Inventories")
        public ArrayList<Inventory> getInventories() {
            return inventories;
        }
        public void setInventories(ArrayList inventories) {
            this.inventories = inventories;
        }

        @JsonProperty("WarehouseID")
        public String getWarehouseID() {
            return warehouseID;
        }
        public void setWarehouseID(String warehouseID) {
            this.warehouseID = warehouseID;
        }

        @JsonProperty("WarehouseName")
        public String getWarehouseName() {
            return warehouseName;
        }
        public void setWarehouseName(String warehouseName) {
            this.warehouseName = warehouseName;
        }

        @JsonProperty("Location")
        public String getLocation() {
            return location;
        }
        public void setLocation(String location) {
            this.location = location;
        }

        @JsonProperty("Capacity")
        public int getCapacity() {
            return capacity;
        }
        public void setCapacity(int capacity) {
            this.capacity = capacity;
        }

        public int addWarehouse() throws IOException {
            // Read existing warehouses from the file
            JSONArray warehouses = readWarehousesFromFile();

            // Check if the warehouse already exists
            if (getWarehouseIndex(warehouseID, warehouses) != -1) {
                return -1; // Return -1 to indicate no rows inserted
            }

            // Create a JSONObject for the new warehouse
            JSONObject warehouseJson = new JSONObject();
            warehouseJson.put("WarehouseID", warehouseID);
            warehouseJson.put("WarehouseName", warehouseName);
            warehouseJson.put("Location", location);
            warehouseJson.put("Capacity", capacity);

            // Update inventories in the warehouse JSON
            JSONArray inventoriesJson = new JSONArray();
            for (Inventory inventory : inventories) {
                JSONObject inventoryJson = new JSONObject();
                inventoryJson.put("InventoryID", inventory.getInventoryID());
                inventoryJson.put("WarehouseID", inventory.getWarehouseID());
                inventoryJson.put("Quantity", inventory.getQuantity());

                // Add products to the inventory JSON
                JSONArray productsJson = new JSONArray();
                for (Product product : inventory.getProducts()) {
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
                    } else {
                        productJson.put("type", "finishedGoods");
                        productJson.put("ProductionDate", ((FinishedGoods) product).getProductionDate());
                    }
                    productsJson.put(productJson);
                }
                inventoryJson.put("Products", productsJson);
                inventoriesJson.put(inventoryJson);
            }
            warehouseJson.put("Inventories", inventoriesJson);

            // Add the new warehouse to the JSONArray
            warehouses.put(warehouseJson);

            // Write the updated JSON array back to the file
            writeWarehousesToFile(warehouses);

            // Return 1 to indicate one row inserted
            return 1;
        }


        public int updateWarehouse() throws IOException {
            // Read existing warehouses from the file
            JSONArray warehouses = readWarehousesFromFile();

            // Find the index of the warehouse to update
            int index = getWarehouseIndex(warehouseID, warehouses);
            if (index == -1) {
                return -1; // Warehouse not found
            }

            // Update the warehouse details
            JSONObject warehouseJson = warehouses.getJSONObject(index);
            warehouseJson.put("WarehouseName", warehouseName);
            warehouseJson.put("Location", location);
            warehouseJson.put("Capacity", capacity);

            JSONArray inventoriesJson = new JSONArray();
            for (Inventory inventory : inventories) {
                JSONObject inventoryJson = new JSONObject();
                inventoryJson.put("InventoryID", inventory.getInventoryID());
                inventoryJson.put("WarehouseID", inventory.getWarehouseID());
                inventoryJson.put("Quantity", inventory.getQuantity());

                // Add products to the inventory JSON
                JSONArray productsJson = new JSONArray();
                for (Product product : inventory.getProducts()) {
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
                    } else {
                        productJson.put("type", "finishedGoods");
                        productJson.put("ProductionDate", ((FinishedGoods) product).getProductionDate());
                    }
                    productsJson.put(productJson);
                }
                inventoryJson.put("Products", productsJson);
                inventoriesJson.put(inventoryJson);
            }
            warehouseJson.put("Inventories", inventoriesJson);

            // Write the updated JSON array back to the file
            writeWarehousesToFile(warehouses);

            // Return 1 to indicate one row updated
            return 1;
        }


        public int deleteWarehouse() throws IOException {
            // Read existing warehouses from the file
            JSONArray warehouses = readWarehousesFromFile();

            // Find the index of the warehouse to delete
            int index = getWarehouseIndex(warehouseID, warehouses);
            if (index == -1) {
                return -1; // Warehouse not found
            }

            // Remove the warehouse from the JSONArray
            warehouses.remove(index);

            // Write the updated JSON array back to the file
            writeWarehousesToFile(warehouses);

            // Return 1 to indicate one row deleted
            return 1;
        }

        private int getWarehouseIndex(String warehouseID, JSONArray warehouses) {
            for (int i = 0; i < warehouses.length(); i++) {
                JSONObject warehouse = warehouses.getJSONObject(i);
                if (warehouse.getString("WarehouseID").equals(warehouseID)) {
                    return i;
                }
            }
            return -1; // Warehouse not found
        }

        private JSONArray readWarehousesFromFile() throws IOException {
            // Read the content of the file
            String content = new String(Files.readAllBytes(Paths.get(FILE_PATH)));

            // If the content is empty, return an empty JSON array
            if (content.isEmpty()) {
                return new JSONArray();
            }

            // Parse the content into a JSON array
            return new JSONArray(content);
        }

        private void writeWarehousesToFile(JSONArray warehouses) throws IOException {
            Files.write(Paths.get(FILE_PATH), warehouses.toString(4).getBytes());
        }
    }
