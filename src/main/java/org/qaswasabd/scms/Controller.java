package org.qaswasabd.scms;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Controller {
    //Arraylists for storing all the Classes Objects
    ArrayList<Product> products = new ArrayList<Product>();
    ArrayList<Inventory> inventories = new ArrayList<Inventory>();
    ArrayList<Warehouse> warehouses = new ArrayList<Warehouse>();
    ArrayList<Payment> payments = new ArrayList<Payment>();
    ArrayList<Customer> customers = new ArrayList<Customer>();
    ArrayList<PrimarySupplier> suppliers = new ArrayList<PrimarySupplier>();
    ArrayList<DeliveryTracking> deliverytrackers = new ArrayList<DeliveryTracking>();
    ArrayList<Order> orders = new ArrayList<Order>();

    //Paths of all JSON files that store all Classes Objects
    private static String inventorypath = "src/main/resources/org/qaswasabd/scms/JSON/inventory.json";
    private static String orderpath = "src/main/resources/org/qaswasabd/scms/JSON/orders.json";
    private static String deliverypath = "src/main/resources/org/qaswasabd/scms/JSON/delivery_tracking.json";
    private static String customerpath = "src/main/resources/org/qaswasabd/scms/JSON/customers.json";
    private static String productpath = "src/main/resources/org/qaswasabd/scms/JSON/products.json";
    private static String paymentpath = "src/main/resources/org/qaswasabd/scms/JSON/payments.json";
    private static String warehousepath = "src/main/resources/org/qaswasabd/scms/JSON/warehouses.json";
    private static String supplierpath = "src/main/resources/org/qaswasabd/scms/JSON/suppliers.json";
    private static String passwordpath = "src/main/resources/org/qaswasabd/scms/JSON/details.json";

    //Objects of GUI for all the FXML files
    @FXML
    private Slider unitPriceSlider;
    @FXML
    private Slider quantitySlider;
    @FXML
    private Slider unitPriceSlider2;
    @FXML
    private Slider quantitySlider2;
    @FXML
    private Label sliderlabel1;
    @FXML
    private Label sliderlabel2;
    @FXML
    private Label sliderlabel3;
    @FXML
    private Label sliderlabel4;
    private String key = "1000-000-23141";
    private Stage stage; // Holds the stage
    private Main mainApp; // Holds a reference to the main application
    @FXML
    private PasswordField oldPasswordField;
    @FXML
    private Label vs;
    @FXML
    private PasswordField newPasswordField;
    @FXML
    private TextField keyField;
    @FXML
    private Label errorLabel;
    @FXML
    private Label errorLabel4;
    @FXML
    private Label pwdcheck;
    @FXML
    private Label errorLabel2;
    @FXML
    private PasswordField passwordField1;
    @FXML
    private TextField passwordField1_shown;
    @FXML
    private TextField inventoryIDField3;
    @FXML
    private Label errorLabel8;
    @FXML
    private TextField usernameField;
    @FXML
    private ImageView eyeIcon1;
    @FXML
    private ImageView eyeIcon2;
    @FXML
    private ImageView eyeIcon3;
    @FXML
    private TextField oldPasswordField_shown;
    @FXML
    private TextField newPasswordField_shown;
    @FXML
    private TextField supplierIDField3;
    @FXML
    private TextField supplierIDField4;
    @FXML
    private TextField supplierNameField;
    @FXML
    private TextField supplierNameField2;
    @FXML
    private TextField contactField;
    @FXML
    private TextField contactField2;
    @FXML
    private ListView<Product> selectProducts3;
    @FXML
    private ListView<Product> selectProducts4;
    @FXML
    private Label errorLabel12;
    @FXML
    private Label errorLabel13;
    @FXML
    private Label errorLabel3;
    @FXML
    private Label vo;
    @FXML
    private TextField productIDField;
    @FXML
    private TextField supplierIDField;
    @FXML
    private TextField DescriptionField;
    @FXML
    private TextField productNameField;
    @FXML
    private TextField inventoryIDField;
    @FXML
    private TextField warehouseIDField;
    @FXML
    private TextField quantityField;
    @FXML
    private TextField inventoryIDField2;
    @FXML
    private TextField warehouseIDField2;
    @FXML
    private TextField quantityField2;
    @FXML
    private TextField supplierIDField2;
    @FXML
    private TextField DescriptionField2;
    @FXML
    private TextField productNameField2;
    @FXML
    private Label errorLabel5;
    @FXML
    private ToggleGroup toggleGroup;
    @FXML
    private Label errorLabel14;
    @FXML
    private TextField supplierIDField5;
    @FXML
    private RadioButton PGood;
    @FXML
    private RadioButton RMaterial;
    @FXML
    private TextField productTypeTextField;
    @FXML
    private Label productLabel;
    @FXML
    private DatePicker productionDatePicker;
    @FXML
    private ToggleGroup toggleGroup2;
    @FXML
    private RadioButton PGood2;
    @FXML
    private RadioButton RMaterial2;
    @FXML
    private TextField productTypeTextField2;
    @FXML
    private Label productLabel2;
    @FXML
    private DatePicker productionDatePicker2;
    @FXML
    private Label vp;
    @FXML
    private Label errorLabel9;
    @FXML
    private TextField warehouseIDField3;
    @FXML
    private TextField warehouseNameField;
    @FXML
    private TextField locationField;
    @FXML
    private TextField capacityField;
    @FXML
    private ListView<Inventory> inventoriesList;
    @FXML
    private Label errorLabel10;
    @FXML
    private Label errorLabel11;
    @FXML
    private TextField warehouseIDField4;
    @FXML
    private TextField warehouseIDField5;
    @FXML
    private TextField warehouseNameField2;
    @FXML
    private TextField locationField2;
    @FXML
    private TextField capacityField2;
    @FXML
    private ListView<Inventory> inventoriesList2;
    @FXML
    private Button helloContinueButton;
    @FXML
    private TextField searchField;
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, String> productIDColumn;
    @FXML
    private TableColumn<Product, String> productNameColumn;
    @FXML
    private TableColumn<Product, String> descriptionColumn;
    @FXML
    private TableColumn<Product, String> unitPriceColumn;
    @FXML
    private TableColumn<Product, String> quantityInStockColumn;
    @FXML
    private TableColumn<Product, String> supplierIDColumn;
    @FXML
    private TableColumn<Product, String> materialTypeColumn;
    @FXML
    private TableColumn<Product, LocalDate> prodDateColumn;
    @FXML
    private Label errorLabel6;
    @FXML
    private ListView<Product> selectProducts;
    @FXML
    private Label errorLabel7;
    @FXML
    private ListView<Product> selectProducts2;
    @FXML
    private TextField searchField2;
    @FXML
    private Label vi;
    @FXML
    private TableView<Inventory> inventoryTable;
    @FXML
    private TableColumn<Inventory, String> inventoryIDColumn;
    @FXML
    private TableColumn<Inventory, String> warehouseIDColumn;
    @FXML
    private TableColumn<Inventory, String> quantityColumn;
    @FXML
    private TableColumn<Inventory, String> productsColumn;
    @FXML
    private TextField searchField6;
    @FXML
    private Label vc;
    @FXML
    private TextField searchField7;
    @FXML
    private Label vpy;
    @FXML
    private TableView<Payment> paymentTable;
    @FXML
    private TableColumn<Payment, String> paymentIDColumn;
    @FXML
    private TableColumn<Payment, String> orderIDColumn2;
    @FXML
    private TableColumn<Payment, String> methodColumn;
    @FXML
    private TableColumn<Payment, String> amountColumn;
    @FXML
    private TableColumn<Payment, String> paymentDateColumn;
    @FXML
    private TextField searchField8;
    @FXML
    private Label td;
    @FXML
    private TableView<DeliveryTracking> deliveryTable;
    @FXML
    private TableColumn<DeliveryTracking, String> deliveryIDColumn;
    @FXML
    private TableColumn<DeliveryTracking, String> orderIDColumn3;
    @FXML
    private TableColumn<DeliveryTracking, String> estDateColumn;
    @FXML
    private TableColumn<DeliveryTracking, String> actDateColumn;
    @FXML
    private TableColumn<DeliveryTracking, String> statusColumn;
    @FXML
    private TableColumn<DeliveryTracking, String> carrierInfoColumn;
    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableColumn<Customer, String> customerIDColumn;
    @FXML
    private TableColumn<Customer, String> customerNameColumn;
    @FXML
    private TableColumn<Customer, String> contactInfoColumn;
    @FXML
    private TableColumn<Customer, String> addressColumn;
    @FXML
    private TableView<Order> orderTable;
    @FXML
    private TableColumn<Order, String> orderIDColumn;
    @FXML
    private TableColumn<Order, String> customerInfoColumn;
    @FXML
    private TableColumn<Order, String> orderDateColumn;
    @FXML
    private TableColumn<Order, String> productsColumn3;
    @FXML
    private TableColumn<Order, String> totalPriceColumn;
    @FXML
    private TableView<PrimarySupplier> supplierTable;
    @FXML
    private TableColumn<PrimarySupplier, String> supplierIDColumn2;
    @FXML
    private TableColumn<PrimarySupplier, String> supplierNameColumn;
    @FXML
    private TableColumn<PrimarySupplier, String> contactColumn;
    @FXML
    private TableColumn<PrimarySupplier, String> productsColumn2;
    @FXML
    private TextField searchField4;
    @FXML
    private TextField searchField5;
    @FXML
    private TextField searchField3;
    @FXML
    private Label vw;
    @FXML
    private TableView<Warehouse> warehouseTable;
    @FXML
    private TableColumn<Warehouse, String> warehouseIDColumn2;
    @FXML
    private TableColumn<Warehouse, String> warehouseNameColumn;
    @FXML
    private TableColumn<Warehouse, String> locationColumn;
    @FXML
    private TableColumn<Warehouse, String> CapacityColumn;
    @FXML
    private TableColumn<Warehouse, String> inventoriesColumn;
    @FXML
    private TextField productIDField2;
    @FXML
    private Label errorLabel15;
    @FXML
    private Label errorLabel16;
    @FXML
    private TextField orderIDField;
    @FXML
    private TextField customerInfoField;
    @FXML
    private DatePicker orderDatePicker;
    @FXML
    private ListView selectProducts5;
    @FXML
    private Label errorLabel17;
    @FXML
    private TextField orderIDField2;
    @FXML
    private Label errorLabel18;
    @FXML
    private TextField orderIDField3;
    @FXML
    private TextField customerInfoField2;
    @FXML
    private DatePicker orderDatePicker2;
    @FXML
    private ListView selectProducts6;
    @FXML
    private TextField customerIDField;
    @FXML
    private TextField customerNameField;
    @FXML
    private TextField contactInfoField;
    @FXML
    private TextField addressField;
    @FXML
    private Label errorLabel19;
    @FXML
    private TextField customerIDField2;
    @FXML
    private TextField customerNameField2;
    @FXML
    private TextField contactInfoField2;
    @FXML
    private TextField addressField2;
    @FXML
    private Label errorLabel20;
    @FXML
    private TextField orderIDField4;
    @FXML
    private TextField paymentIDField;
    @FXML
    private TextField amountField;
    @FXML
    private Label errorLabel21;
    @FXML
    private DatePicker paymentDatePicker;
    @FXML
    private ChoiceBox<String> methodList;
    @FXML
    private Label errorLabel22;
    @FXML
    private DatePicker estDatePicker;
    @FXML
    private DatePicker actDatePicker;
    @FXML
    private ChoiceBox<Status> statusList;
    @FXML
    private TextField orderIDField5;
    @FXML
    private TextField carrierInfoField;

    //For Login, Change Password
    private boolean passwordVisible1 = false;
    private boolean passwordVisible2 = false;
    private boolean passwordVisible3 = false;

    //For TableView
    private ObservableList<DeliveryTracking> deliveryList = FXCollections.observableArrayList();
    private ObservableList<Customer> customerList = FXCollections.observableArrayList();
    private ObservableList<Payment> paymentlist = FXCollections.observableArrayList();
    private ObservableList<Order> orderList = FXCollections.observableArrayList();
    private ObservableList<PrimarySupplier> supplierList = FXCollections.observableArrayList();
    private ObservableList<Warehouse> warehouseList = FXCollections.observableArrayList();
    private ObservableList<Inventory> inventoryList = FXCollections.observableArrayList();
    private ObservableList<Product> productList = FXCollections.observableArrayList();


    // Method to receive the stage from Main class
    public void setStageAndMainApp(Stage stage, Main mainApp) {
        this.stage = stage;
        this.mainApp = mainApp;
    }

    //Json File Creation and Loading Methods
    public static void createDirectoryJSONIfNotExists() {
        String directoryPath = "src/main/resources/org/qaswasabd/scms/JSON/";
        Path path = Paths.get(directoryPath);

        // Check if directory exists
        if (!Files.exists(path)) {
            try {
                // Create the directory
                Files.createDirectories(path);
            } catch (IOException e) {
                return;
            }
        } else {
            return;
        }
    }
    private boolean jsonFilesExist() {
        File inventoryFile = new File(inventorypath);
        File orderFile = new File(orderpath);
        File deliveryFile = new File(deliverypath);
        File customerFile = new File(customerpath);
        File productFile = new File(productpath);
        File paymentFile = new File(paymentpath);
        File warehouseFile = new File(warehousepath);
        File supplierFile = new File(supplierpath);
        File passwordFile = new File(passwordpath);

        return inventoryFile.exists() && orderFile.exists() && deliveryFile.exists() &&
                customerFile.exists() && productFile.exists() && paymentFile.exists() &&
                warehouseFile.exists() && supplierFile.exists() && passwordFile.exists();
    }
    private boolean inventoryFileExistsAndNotEmpty() {
        return fileExistsAndNotEmpty(inventorypath);
    }
    private boolean orderFileExistsAndNotEmpty() {
        return fileExistsAndNotEmpty(orderpath);
    }
    private boolean deliveryFileExistsAndNotEmpty() {
        return fileExistsAndNotEmpty(deliverypath);
    }
    private boolean customerFileExistsAndNotEmpty() {
        return fileExistsAndNotEmpty(customerpath);
    }
    private boolean productFileExistsAndNotEmpty() {
        return fileExistsAndNotEmpty(productpath);
    }
    private boolean paymentFileExistsAndNotEmpty() {
        return fileExistsAndNotEmpty(paymentpath);
    }
    private boolean warehouseFileExistsAndNotEmpty() {
        return fileExistsAndNotEmpty(warehousepath);
    }
    private boolean supplierFileExistsAndNotEmpty() {
        return fileExistsAndNotEmpty(supplierpath);
    }
    private boolean fileExistsAndNotEmpty(String filePath) {
        File file = new File(filePath);
        return file.exists() && file.length() > 0;
    }
    private void loadInventoriesFromJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            String inventoryJson = new String(Files.readAllBytes(Paths.get(inventorypath)));
            inventories = objectMapper.readValue(inventoryJson, new TypeReference<ArrayList<Inventory>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadOrdersFromJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            String orderJson = new String(Files.readAllBytes(Paths.get(orderpath)));
            orders = objectMapper.readValue(orderJson, new TypeReference<ArrayList<Order>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadDeliveryTrackersFromJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            String deliveryJson = new String(Files.readAllBytes(Paths.get(deliverypath)));
            deliverytrackers = objectMapper.readValue(deliveryJson, new TypeReference<ArrayList<DeliveryTracking>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadCustomersFromJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String customerJson = new String(Files.readAllBytes(Paths.get(customerpath)));
            customers = objectMapper.readValue(customerJson, new TypeReference<ArrayList<Customer>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadProductsFromJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        try {
            String productJson = new String(Files.readAllBytes(Paths.get(productpath)));
            products = objectMapper.readValue(productJson, new TypeReference<ArrayList<Product>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadPaymentsFromJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            String paymentJson = new String(Files.readAllBytes(Paths.get(paymentpath)));
            payments = objectMapper.readValue(paymentJson, new TypeReference<ArrayList<Payment>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadWarehousesFromJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            String warehouseJson = new String(Files.readAllBytes(Paths.get(warehousepath)));
            warehouses = objectMapper.readValue(warehouseJson, new TypeReference<ArrayList<Warehouse>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadSuppliersFromJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            String supplierJson = new String(Files.readAllBytes(Paths.get(supplierpath)));
            suppliers = objectMapper.readValue(supplierJson, new TypeReference<ArrayList<PrimarySupplier>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void createJSONFiles() {
        try {
            // Create empty JSON files if they don't exist
            Files.createFile(Paths.get(inventorypath));
            Files.createFile(Paths.get(orderpath));
            Files.createFile(Paths.get(deliverypath));
            Files.createFile(Paths.get(customerpath));
            Files.createFile(Paths.get(productpath));
            Files.createFile(Paths.get(paymentpath));
            Files.createFile(Paths.get(warehousepath));
            Files.createFile(Paths.get(supplierpath));
            String jsonContent = "{\"username\":\"admin\",\"password\":\"admin\"}";
            Files.write(Paths.get(passwordpath),jsonContent.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Initialize the contents of FXML, add listeners, properties etc.
    public void initialize() {
        createDirectoryJSONIfNotExists();
        if (helloContinueButton != null) {
            helloContinueButton.requestFocus();
            helloContinueButton.setOnKeyPressed(this::handleKeyPress2);
        }
        if (!jsonFilesExist()) {
            // Create JSON files
            createJSONFiles();
        }
        if (inventoryFileExistsAndNotEmpty()) {
            loadInventoriesFromJSON();
        }
        if (orderFileExistsAndNotEmpty()) {
            loadOrdersFromJSON();
        }
        if (deliveryFileExistsAndNotEmpty()) {
            loadDeliveryTrackersFromJSON();
        }
        if (customerFileExistsAndNotEmpty()) {
            loadCustomersFromJSON();
        }
        if (productFileExistsAndNotEmpty()) {
            loadProductsFromJSON();
        }
        if (paymentFileExistsAndNotEmpty()) {
            loadPaymentsFromJSON();
        }
        if (warehouseFileExistsAndNotEmpty()) {
            loadWarehousesFromJSON();
        }
        if (supplierFileExistsAndNotEmpty()) {
            loadSuppliersFromJSON();
        }

        //Add properties and prevent null logic
        if (errorLabel != null) {
            errorLabel.setVisible(false);

            // Add listeners to the text properties of all text fields
            oldPasswordField.textProperty().addListener((observable, oldValue, newValue) -> handleTextFieldChange(newValue, errorLabel));
            newPasswordField.textProperty().addListener((observable, oldValue, newValue) -> handleTextFieldChange(newValue, errorLabel));
        }
        if (errorLabel3 != null) {
            errorLabel3.setVisible(false);
            unitPriceSlider.valueProperty().addListener((observable, oldValue, newValue) ->
                    sliderlabel2.setText("Value: " + String.format("%.1f", newValue)));
            quantitySlider.valueProperty().addListener((observable, oldValue, newValue) ->
                    sliderlabel1.setText("Value: " + String.format("%.1f", newValue)));
            // Add listeners to the text properties of all text fields
            toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue == PGood) {
                    productLabel.setText("Material Type");
                    productTypeTextField.setVisible(true);
                    productionDatePicker.setVisible(false);
                } else if (newValue == RMaterial) {
                    productLabel.setText("Production Date");
                    productionDatePicker.setValue(LocalDate.now());
                    productionDatePicker.getEditor().setDisable(true);

                    productionDatePicker.setVisible(true);
                }
            });
            productionDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> handleTextFieldChange(newValue.toString(), errorLabel3));
            productTypeTextField.textProperty().addListener((observable, oldValue, newValue) -> handleTextFieldChange(newValue, errorLabel3));
            productIDField.textProperty().addListener((observable, oldValue, newValue) -> handleTextFieldChange(newValue, errorLabel3));
            productNameField.textProperty().addListener((observable, oldValue, newValue) -> handleTextFieldChange(newValue, errorLabel3));
            DescriptionField.textProperty().addListener((observable, oldValue, newValue) -> handleTextFieldChange(newValue, errorLabel3));
            supplierIDField.textProperty().addListener((observable, oldValue, newValue) -> handleTextFieldChange(newValue, errorLabel3));
        }
        if (errorLabel21 != null) {
            errorLabel21.setVisible(false);
            // Add listeners to the text properties of all text fields
            paymentDatePicker.getEditor().setDisable(true);
            paymentDatePicker.setValue(LocalDate.now());
            paymentDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> handleTextFieldChange(newValue.toString(), errorLabel21));
            orderIDField4.textProperty().addListener((observable, oldValue, newValue) -> handleTextFieldChange(newValue, errorLabel21));
            paymentIDField.textProperty().addListener((observable, oldValue, newValue) -> handleTextFieldChange(newValue, errorLabel21));
            amountField.textProperty().addListener((observable, oldValue, newValue) -> handleTextFieldChange(newValue, errorLabel21));
            methodList.setItems(FXCollections.observableArrayList(
                    "Card",
                    "Cash",
                    "Voucher"
            ));

            methodList.setValue("Cash");
        }
        if (errorLabel22 != null) {
            errorLabel22.setVisible(false);
            estDatePicker.getEditor().setDisable(true);
            actDatePicker.getEditor().setDisable(true);
            // Add listeners to the text properties of all text fields
            orderIDField5.textProperty().addListener((observable, oldValue, newValue) -> handleTextFieldChange(newValue, errorLabel22));
              statusList.setItems(FXCollections.observableArrayList(
                      Status.PROCESSING,
                    Status.PAID,
                      Status.DELIVERED
            ));
        }
        if (errorLabel4 != null) {
            errorLabel4.setVisible(false);
            unitPriceSlider2.valueProperty().addListener((observable, oldValue, newValue) ->
                    sliderlabel3.setText("Value: " + String.format("%.1f", newValue)));
            quantitySlider2.valueProperty().addListener((observable, oldValue, newValue) ->
                    sliderlabel4.setText("Value: " + String.format("%.1f", newValue)));
            productIDField.textProperty().addListener((observable, oldValue, newValue) -> handleTextFieldChange(newValue, errorLabel4));
            toggleGroup2.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue == PGood2) {
                    productLabel2.setText("Material Type");
                    productTypeTextField2.setVisible(true);
                    productionDatePicker2.setVisible(false);
                } else if (newValue == RMaterial2) {
                    productLabel2.setText("Production Date");
                    productTypeTextField2.setVisible(false);
                    productionDatePicker2.setVisible(true);
                    productionDatePicker2.setValue(LocalDate.now());
                    productionDatePicker2.getEditor().setDisable(true);
                }
            });
        }
        if (errorLabel19 != null) {
            errorLabel19.setVisible(false);
            customerIDField.textProperty().addListener((observable, oldValue, newValue) -> handleTextFieldChange(newValue, errorLabel19));
            contactInfoField.textProperty().addListener((observable, oldValue, newValue) -> handleTextFieldChange(newValue, errorLabel19));
            customerNameField.textProperty().addListener((observable, oldValue, newValue) -> handleTextFieldChange(newValue, errorLabel19));
            addressField.textProperty().addListener((observable, oldValue, newValue) -> handleTextFieldChange(newValue, errorLabel19));
        }
        if (errorLabel20 != null) {
            errorLabel20.setVisible(false);
            customerIDField2.textProperty().addListener((observable, oldValue, newValue) -> handleTextFieldChange(newValue, errorLabel20));
        }
        if (errorLabel11 != null) {
            warehouseIDField5.textProperty().addListener((observable, oldValue, newValue) -> handleTextFieldChange(newValue, errorLabel11));

        }
        if (errorLabel5 != null) {
            productIDField.textProperty().addListener((observable, oldValue, newValue) -> handleTextFieldChange(newValue, errorLabel5));
        }
        if (errorLabel14 != null) {
            supplierIDField5.textProperty().addListener((observable, oldValue, newValue) -> handleTextFieldChange(newValue, errorLabel14));

        }
        if (errorLabel2 != null) {
            errorLabel2.setVisible(false);
            // Add listeners to the text properties of all text fields
            passwordField1.textProperty().addListener((observable, oldValue, newValue) -> handleTextFieldChange(newValue, errorLabel2));
            usernameField.textProperty().addListener((observable, oldValue, newValue) -> handleTextFieldChange(newValue, errorLabel2));
        }
        if (errorLabel15 != null) {
            errorLabel15.setVisible(false);
            // Add listeners to the text properties of all text fields
            productIDField2.textProperty().addListener((observable, oldValue, newValue) -> handleTextFieldChange(newValue, errorLabel15));
        }
        if (errorLabel6 != null) {

                selectProducts.getItems().addAll(products); // Add your product names here
                selectProducts.setCellFactory(param -> new ListCell<Product>() {
                    @Override
                    protected void updateItem(Product product, boolean empty) {
                        super.updateItem(product, empty);

                        if (empty || product == null) {
                            setText(null);
                        } else {
                            setText(product.getProductName()); // Set the product name as the text of the cell
                        }
                    }
                });
            selectProducts.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            selectProducts.getSelectionModel().getSelectedItems().addListener((ListChangeListener<Product>) change -> {
                // Check if selection becomes empty
                if (selectProducts.getSelectionModel().getSelectedItems().isEmpty()) {
                    // Select the first item if selection becomes empty
                    selectProducts.getSelectionModel().selectFirst();
                }
            });
            inventoryIDField.textProperty().addListener((observable, oldValue, newValue) -> handleTextFieldChange(newValue, errorLabel6));
            warehouseIDField.textProperty().addListener((observable, oldValue, newValue) -> handleTextFieldChange(newValue, errorLabel6));
            quantityField.textProperty().addListener((observable, oldValue, newValue) -> handleTextFieldChange(newValue, errorLabel6));

        }
        if (errorLabel16 != null) {
                selectProducts5.getItems().addAll(products); // Add your product names here
                selectProducts5.setCellFactory(param -> new ListCell<Product>() {
                    @Override
                    protected void updateItem(Product product, boolean empty) {
                        super.updateItem(product, empty);

                        if (empty || product == null) {
                            setText(null);
                        } else {
                            setText(product.getProductName()); // Set the product name as the text of the cell
                        }
                    }
                });
            selectProducts5.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            selectProducts5.getSelectionModel().getSelectedItems().addListener((ListChangeListener<Product>) change -> {
                // Check if selection becomes empty
                if (selectProducts5.getSelectionModel().getSelectedItems().isEmpty()) {
                    // Select the first item if selection becomes empty
                    selectProducts5.getSelectionModel().selectFirst();
                }
            });
            orderIDField.textProperty().addListener((observable, oldValue, newValue) -> handleTextFieldChange(newValue, errorLabel16));
            customerInfoField.textProperty().addListener((observable, oldValue, newValue) -> handleTextFieldChange(newValue, errorLabel16));
            orderDatePicker.setValue(LocalDate.now());
            orderDatePicker.getEditor().setDisable(true);
            orderDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> handleTextFieldChange(newValue.toString(), errorLabel16));
        }
        if (errorLabel17 != null) {
                selectProducts6.getItems().addAll(products); // Add your product names here
                selectProducts6.setCellFactory(param -> new ListCell<Product>() {
                    @Override
                    protected void updateItem(Product product, boolean empty) {
                        super.updateItem(product, empty);

                        if (empty || product == null) {
                            setText(null);
                        } else {
                            setText(product.getProductName()); // Set the product name as the text of the cell
                        }
                    }
                });

            selectProducts6.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            orderIDField2.textProperty().addListener((observable, oldValue, newValue) -> handleTextFieldChange(newValue, errorLabel17));
        }
        if (errorLabel9 != null) {
                inventoriesList.getItems().addAll(inventories); // Add your product names here
                inventoriesList.setCellFactory(param -> new ListCell<Inventory>() {
                    @Override
                    protected void updateItem(Inventory inventory, boolean empty) {
                        super.updateItem(inventory, empty);

                        if (empty || inventory == null) {
                            setText(null);
                        } else {
                            setText(inventory.getInventoryID()); // Set the inventory ID as the text of the cell
                        }
                    }
                });

            inventoriesList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            inventoriesList.getSelectionModel().getSelectedItems().addListener((ListChangeListener<Inventory>) change -> {
                // Check if selection becomes empty
                if (inventoriesList.getSelectionModel().getSelectedItems().isEmpty()) {
                    // Select the first item if selection becomes empty
                    inventoriesList.getSelectionModel().selectFirst();
                }
            });
            warehouseIDField3.textProperty().addListener((observable, oldValue, newValue) -> handleTextFieldChange(newValue, errorLabel9));
            warehouseNameField.textProperty().addListener((observable, oldValue, newValue) -> handleTextFieldChange(newValue, errorLabel9));
            capacityField.textProperty().addListener((observable, oldValue, newValue) -> handleTextFieldChange(newValue, errorLabel9));
            locationField.textProperty().addListener((observable, oldValue, newValue) -> handleTextFieldChange(newValue, errorLabel9));

        }
        if (errorLabel12 != null) {

                selectProducts3.getItems().addAll(products); // Add your product names here
                selectProducts3.setCellFactory(param -> new ListCell<Product>() {
                    @Override
                    protected void updateItem(Product product, boolean empty) {
                        super.updateItem(product, empty);

                        if (empty || product == null) {
                            setText(null);
                        } else {
                            setText(product.getProductName()); // Set the product name as the text of the cell
                        }
                    }
                });

            selectProducts3.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            selectProducts3.getSelectionModel().getSelectedItems().addListener((ListChangeListener<Product>) change -> {
                // Check if selection becomes empty
                if (selectProducts3.getSelectionModel().getSelectedItems().isEmpty()) {
                    // Select the first item if selection becomes empty
                    selectProducts3.getSelectionModel().selectFirst();
                }
            });
            supplierIDField3.textProperty().addListener((observable, oldValue, newValue) -> handleTextFieldChange(newValue, errorLabel12));
            supplierNameField.textProperty().addListener((observable, oldValue, newValue) -> handleTextFieldChange(newValue, errorLabel12));
            contactField.textProperty().addListener((observable, oldValue, newValue) -> handleTextFieldChange(newValue, errorLabel12));
        }
        if (errorLabel13 != null) {

                selectProducts4.getItems().addAll(products); // Add your product names here
                selectProducts4.setCellFactory(param -> new ListCell<Product>() {
                    @Override
                    protected void updateItem(Product product, boolean empty) {
                        super.updateItem(product, empty);

                        if (empty || product == null) {
                            setText(null);
                        } else {
                            setText(product.getProductName()); // Set the product name as the text of the cell
                        }
                    }
                });

            selectProducts4.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            selectProducts4.getSelectionModel().getSelectedItems().addListener((ListChangeListener<Product>) change -> {
            });
            supplierIDField4.textProperty().addListener((observable, oldValue, newValue) -> handleTextFieldChange(newValue, errorLabel13));
        }
        if (errorLabel10 != null) {
                inventoriesList2.getItems().addAll(inventories); // Add your product names here
                inventoriesList2.setCellFactory(param -> new ListCell<Inventory>() {
                    @Override
                    protected void updateItem(Inventory inventory, boolean empty) {
                        super.updateItem(inventory, empty);

                        if (empty || inventory == null) {
                            setText(null);
                        } else {
                            setText(inventory.getInventoryID()); // Set the inventory ID as the text of the cell
                        }
                    }
                });

            inventoriesList2.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            inventoriesList2.getSelectionModel().getSelectedItems().addListener((ListChangeListener<Inventory>) change -> {

            });
            warehouseIDField4.textProperty().addListener((observable, oldValue, newValue) -> handleTextFieldChange(newValue, errorLabel10));
        }
        if (errorLabel7 != null) {

                selectProducts2.getItems().addAll(products); // Add your product names here
                selectProducts2.setCellFactory(param -> new ListCell<Product>() {
                    @Override
                    protected void updateItem(Product product, boolean empty) {
                        super.updateItem(product, empty);

                        if (empty || product == null) {
                            setText(null);
                        } else {
                            setText(product.getProductName()); // Set the product name as the text of the cell
                        }
                    }
                });

            selectProducts2.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            inventoryIDField2.textProperty().addListener((observable, oldValue, newValue) -> handleTextFieldChange(newValue, errorLabel7));
        }
        if (errorLabel18 != null) {
            orderIDField3.textProperty().addListener((observable, oldValue, newValue) -> handleTextFieldChange(newValue, errorLabel18));
        }

        //Error handling, event listeners
        if (oldPasswordField != null) {
            // Add event filters to prevent spaces in text fields
            oldPasswordField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                if (event.getCharacter().trim().isEmpty()) {
                    event.consume();
                }
            });
            oldPasswordField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    oldPasswordField.setText(oldValue);
                }
            });
        }
        if (newPasswordField != null) {
            newPasswordField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                if (event.getCharacter().trim().isEmpty()) {
                    event.consume();
                }
            });
            newPasswordField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    newPasswordField.setText(oldValue);
                }
            });
        }
        if (oldPasswordField_shown != null) {
            oldPasswordField_shown.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                if (event.getCharacter().trim().isEmpty()) {
                    event.consume();
                }
            });
            oldPasswordField_shown.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    oldPasswordField_shown.setText(oldValue);
                }
            });
        }
        if (productTypeTextField != null) {
            productTypeTextField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                if (event.getCharacter().trim().isEmpty()) {
                    event.consume();
                }
            });
            productTypeTextField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);
                if (!Character.isLetter(inputChar)) {
                    event.consume();
                }
            });
            productTypeTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    productTypeTextField.setText(oldValue);
                }
            });
        }
        if (newPasswordField_shown != null) {
            newPasswordField_shown.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                if (event.getCharacter().trim().isEmpty()) {
                    event.consume();
                }
            });
            newPasswordField_shown.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    newPasswordField_shown.setText(oldValue);
                }
            });
        }
        if (passwordField1 != null) {
            // Add event filters to prevent spaces in text fields
            passwordField1.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                if (event.getCharacter().trim().isEmpty()) {
                    event.consume();
                }
            });
            passwordField1.setOnKeyPressed(this::handleKeyPress);
            passwordField1.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    passwordField1.setText(oldValue);
                }
            });
        }
        if (usernameField != null) {
            usernameField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                if (event.getCharacter().trim().isEmpty()) {
                    event.consume();
                }
            });
            usernameField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);
                if (!Character.isLetter(inputChar)) {
                    event.consume();
                }
            });
            usernameField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    usernameField.setText(oldValue);
                }
            });
            usernameField.requestFocus();
        }
        if (passwordField1_shown != null) {
            passwordField1_shown.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                if (event.getCharacter().trim().isEmpty()) {
                    event.consume();
                }
            });
            passwordField1_shown.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    passwordField1_shown.setText(oldValue);
                }
            });
        }
        if (pwdcheck != null) {
            pwdcheck.setVisible(false);

            // Add listeners to the text properties of all text fields
            keyField.textProperty().addListener((observable, oldValue, newValue) -> handleTextFieldChange(newValue, pwdcheck));
            keyField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                if (event.getCharacter().trim().isEmpty()) {
                    event.consume();
                }
            });
            keyField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);
                if (!Character.isLetter(inputChar)) {
                    event.consume();
                }
            });
            keyField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    keyField.setText(oldValue);
                }
            });
        }
        if (productIDField != null) {
            productIDField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                if (event.getCharacter().trim().isEmpty()) {
                    event.consume();
                }
            });
            productIDField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);
                // Allow only alphanumeric characters and underscores
                if (!Character.isLetterOrDigit(inputChar)) {
                    event.consume();
                }
            });
            productIDField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    productIDField.setText(oldValue);
                }
            });
        }
        if (orderIDField4 != null) {
            orderIDField4.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                if (event.getCharacter().trim().isEmpty()) {
                    event.consume();
                }
            });
            orderIDField4.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);
                // Allow only alphanumeric characters and underscores
                if (!Character.isLetterOrDigit(inputChar)) {
                    event.consume();
                }
            });
            orderIDField4.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    orderIDField4.setText(oldValue);
                }
            });
        }
        if (paymentIDField != null) {
            paymentIDField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                if (event.getCharacter().trim().isEmpty()) {
                    event.consume();
                }
            });
            paymentIDField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);
                // Allow only alphanumeric characters and underscores
                if (!Character.isLetterOrDigit(inputChar)) {
                    event.consume();
                }
            });
            paymentIDField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    paymentIDField.setText(oldValue);
                }
            });
        }
        if (customerIDField != null) {
            customerIDField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                if (event.getCharacter().trim().isEmpty()) {
                    event.consume();
                }
            });
            customerIDField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);
                // Allow only alphanumeric characters and underscores
                if (!Character.isLetterOrDigit(inputChar)) {
                    event.consume();
                }
            });
            customerIDField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    customerIDField.setText(oldValue);
                }
            });
        }
        if (customerIDField2 != null) {
            customerIDField2.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                if (event.getCharacter().trim().isEmpty()) {
                    event.consume();
                }
            });
            customerIDField2.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);
                // Allow only alphanumeric characters and underscores
                if (!Character.isLetterOrDigit(inputChar)) {
                    event.consume();
                }
            });
            customerIDField2.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    customerIDField2.setText(oldValue);
                }
            });
        }
        if (orderIDField != null) {
            orderIDField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                if (event.getCharacter().trim().isEmpty()) {
                    event.consume();
                }
            });
            orderIDField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);
                // Allow only alphanumeric characters and underscores
                if (!Character.isLetterOrDigit(inputChar)) {
                    event.consume();
                }
            });
            orderIDField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    orderIDField.setText(oldValue);
                }
            });
        }
        if (orderIDField2 != null) {
            orderIDField2.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                if (event.getCharacter().trim().isEmpty()) {
                    event.consume();
                }
            });
            orderIDField2.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);
                // Allow only alphanumeric characters and underscores
                if (!Character.isLetterOrDigit(inputChar)) {
                    event.consume();
                }
            });
            orderIDField2.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    orderIDField2.setText(oldValue);
                }
            });
        }
        if (orderIDField3 != null) {
            orderIDField3.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                if (event.getCharacter().trim().isEmpty()) {
                    event.consume();
                }
            });
            orderIDField3.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);
                // Allow only alphanumeric characters and underscores
                if (!Character.isLetterOrDigit(inputChar)) {
                    event.consume();
                }
            });
            orderIDField3.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    orderIDField3.setText(oldValue);
                }
            });
        }
        if (orderIDField5 != null) {
            orderIDField5.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                if (event.getCharacter().trim().isEmpty()) {
                    event.consume();
                }
            });
            orderIDField5.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);
                // Allow only alphanumeric characters and underscores
                if (!Character.isLetterOrDigit(inputChar)) {
                    event.consume();
                }
            });
            orderIDField5.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    orderIDField5.setText(oldValue);
                }
            });
        }
        if (productNameField != null) {
            productNameField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    productNameField.setText(oldValue);
                }
            });
            productNameField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);
                String currentText = productNameField.getText();

                // Allow only alphanumeric characters and underscores
                if (!Character.isLetterOrDigit(inputChar) && inputChar != ' ') {
                    event.consume();
                }
                if (currentText.isEmpty() && inputChar == ' ') {
                    event.consume();
                }
            });
        }
        if (carrierInfoField != null) {
            carrierInfoField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    carrierInfoField.setText(oldValue);
                }
            });
            carrierInfoField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);
                String currentText = carrierInfoField.getText();

                // Allow only alphanumeric characters and underscores
                if (!Character.isLetterOrDigit(inputChar) && inputChar != ' ') {
                    event.consume();
                }
                if (currentText.isEmpty() && inputChar == ' ') {
                    event.consume();
                }
            });
        }
        if (customerNameField != null) {
            customerNameField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    customerNameField.setText(oldValue);
                }
            });
            customerNameField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);
                String currentText = customerNameField.getText();

                // Allow only alphanumeric characters and underscores
                if (!Character.isLetterOrDigit(inputChar) && inputChar != ' ') {
                    event.consume();
                }
                if (currentText.isEmpty() && inputChar == ' ') {
                    event.consume();
                }
            });
        }
        if (customerNameField2 != null) {
            customerNameField2.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    customerNameField2.setText(oldValue);
                }
            });
            customerNameField2.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);
                String currentText = customerNameField2.getText();

                // Allow only alphanumeric characters and underscores
                if (!Character.isLetterOrDigit(inputChar) && inputChar != ' ') {
                    event.consume();
                }
                if (currentText.isEmpty() && inputChar == ' ') {
                    event.consume();
                }
            });
        }
        if (DescriptionField != null) {

            DescriptionField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 255) {
                    DescriptionField.setText(oldValue);
                }
            });
            DescriptionField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);
                String currentText = DescriptionField.getText();
                if (currentText.isEmpty() && inputChar == ' ') {
                    event.consume();
                }
                // Allow only alphanumeric characters and underscores
                if (!Character.isLetterOrDigit(inputChar) && inputChar != ' ') {
                    event.consume();
                }
            });
        }
        if (supplierIDField != null) {
            supplierIDField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                if (event.getCharacter().trim().isEmpty()) {
                    event.consume();
                }
            });
            supplierIDField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);

                // Allow only alphanumeric characters and underscores
                if (!Character.isLetterOrDigit(inputChar)) {
                    event.consume();
                }

            });
            supplierIDField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    supplierIDField.setText(oldValue);
                }
            });
        }
        if (supplierIDField3 != null) {
            supplierIDField3.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                if (event.getCharacter().trim().isEmpty()) {
                    event.consume();
                }
            });
            supplierIDField3.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    supplierIDField3.setText(oldValue);
                }
            });
            supplierIDField3.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);
                String currentText = supplierIDField3.getText();

                // Allow only alphanumeric characters and underscores
                if (!Character.isLetterOrDigit(inputChar)) {
                    event.consume();
                }

                // Disallow dash as the first character
                if (currentText.isEmpty() && inputChar == '-') {
                    event.consume();
                }
            });
        }
        if (supplierIDField4 != null) {
            supplierIDField4.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                if (event.getCharacter().trim().isEmpty()) {
                    event.consume();
                }
            });
            supplierIDField4.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);

                // Allow only alphanumeric characters and underscores
                if (!Character.isLetterOrDigit(inputChar)) {
                    event.consume();
                }
            });
            supplierIDField4.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    supplierIDField4.setText(oldValue);
                }
            });
        }
        if (supplierNameField != null) {
            supplierNameField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    supplierNameField.setText(oldValue);
                }
            });
            supplierNameField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);

                // Allow only alphanumeric characters and underscores
                if (!Character.isLetterOrDigit(inputChar) && inputChar != ' ') {
                    event.consume();
                }
                String currentText = supplierNameField.getText();
                // Disallow space as the first character
                if (currentText.isEmpty() && inputChar == ' ') {
                    event.consume();
                }

            });
        }
        if (supplierNameField2 != null) {
            supplierNameField2.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    supplierNameField2.setText(oldValue);
                }
            });
            supplierNameField2.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);
                String currentText = supplierNameField2.getText();
                // Disallow space as the first character

                // Allow only alphanumeric characters and spaces
                if (!Character.isLetterOrDigit(inputChar) && inputChar != ' ') {
                    event.consume();
                }

                // Disallow space as the first character
                if (currentText.isEmpty() && inputChar == ' ') {
                    event.consume();
                }
            });
        }
        if (contactField != null) {
            contactField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    contactField.setText(oldValue);
                }
            });
            contactField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);

                // Allow only alphanumeric characters and underscores
                if (!Character.isLetterOrDigit(inputChar) && inputChar != ' ' && inputChar != '#' && inputChar != ',') {
                    event.consume();
                }
                String currentText = contactField.getText();

                // Disallow space as the first character
                if (currentText.isEmpty() && (inputChar == ' ' || inputChar == '#' || inputChar == ',')) {
                    event.consume();
                }
            });
        }
        if (contactInfoField != null) {
            contactInfoField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    contactInfoField.setText(oldValue);
                }
            });
            contactInfoField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);

                // Allow only alphanumeric characters and underscores
                if (!Character.isLetterOrDigit(inputChar) && inputChar != ' ' && inputChar != '#' && inputChar != ',') {
                    event.consume();
                }
                String currentText = contactInfoField.getText();

                // Disallow space as the first character
                if (currentText.isEmpty() && (inputChar == ' ' || inputChar == '#' || inputChar == ',')) {
                    event.consume();
                }
            });
        }
        if (addressField != null) {
            addressField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    addressField.setText(oldValue);
                }
            });
            addressField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);

                // Allow only alphanumeric characters and underscores
                if (!Character.isLetterOrDigit(inputChar) && inputChar != ' ' && inputChar != '#' && inputChar != ',') {
                    event.consume();
                }
                String currentText = addressField.getText();

                // Disallow space as the first character
                if (currentText.isEmpty() && (inputChar == ' ' || inputChar == '#' || inputChar == ',')) {
                    event.consume();
                }
            });
        }
        if (contactInfoField2 != null) {
            contactInfoField2.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    contactInfoField2.setText(oldValue);
                }
            });
            contactInfoField2.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);

                // Allow only alphanumeric characters and underscores
                if (!Character.isLetterOrDigit(inputChar) && inputChar != ' ' && inputChar != '#' && inputChar != ',') {
                    event.consume();
                }
                String currentText = contactInfoField2.getText();

                // Disallow space as the first character
                if (currentText.isEmpty() && (inputChar == ' ' || inputChar == '#' || inputChar == ',')) {
                    event.consume();
                }
            });
        }
        if (addressField2 != null) {
            addressField2.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    addressField2.setText(oldValue);
                }
            });
            addressField2.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);

                // Allow only alphanumeric characters and underscores
                if (!Character.isLetterOrDigit(inputChar) && inputChar != ' ' && inputChar != '#' && inputChar != ',') {
                    event.consume();
                }
                String currentText = addressField2.getText();

                // Disallow space as the first character
                if (currentText.isEmpty() && (inputChar == ' ' || inputChar == '#' || inputChar == ',')) {
                    event.consume();
                }
            });
        }
        if (contactField2 != null) {
            contactField2.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    contactField2.setText(oldValue);
                }
            });
            contactField2.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);

                // Allow only alphanumeric characters and underscores
                if (!Character.isLetterOrDigit(inputChar) && inputChar != ' ' && inputChar != '#' && inputChar != ',') {
                    event.consume();
                }
                String currentText = contactField2.getText();

                // Disallow space as the first character
                if (currentText.isEmpty() && (inputChar == ' ' || inputChar == '#' || inputChar == ',')) {
                    event.consume();
                }
            });
        }
        if (warehouseIDField3 != null) {
            warehouseIDField3.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                if (event.getCharacter().trim().isEmpty()) {
                    event.consume();
                }
            });
            warehouseIDField3.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);

                // Allow only alphanumeric characters and underscores
                if (!Character.isLetterOrDigit(inputChar)) {
                    event.consume();
                }
            });
            warehouseIDField3.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    warehouseIDField3.setText(oldValue);
                }
            });
        }
        if (productIDField2 != null) {
            productIDField2.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                if (event.getCharacter().trim().isEmpty()) {
                    event.consume();
                }
            });
            productIDField2.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);

                // Allow only alphanumeric characters and underscores
                if (!Character.isLetterOrDigit(inputChar)) {
                    event.consume();
                }
            });
            productIDField2.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    productIDField2.setText(oldValue);
                }
            });
        }
        if (warehouseIDField5 != null) {
            warehouseIDField5.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                if (event.getCharacter().trim().isEmpty()) {
                    event.consume();
                }
            });
            warehouseIDField5.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);

                // Allow only alphanumeric characters and underscores
                if (!Character.isLetterOrDigit(inputChar)) {
                    event.consume();
                }
            });
            warehouseIDField5.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    warehouseIDField5.setText(oldValue);
                }
            });
        }
        if (warehouseNameField != null) {
            warehouseNameField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    warehouseNameField.setText(oldValue);
                }
            });
            warehouseNameField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);
                String currentText = warehouseNameField.getText();
                if (currentText.isEmpty() && inputChar == ' ') {
                    event.consume();
                }
                // Allow only alphanumeric characters and underscores
                if (!Character.isLetterOrDigit(inputChar) && inputChar != ' ') {
                    event.consume();
                }

            });
        }
        if (capacityField != null) {
            capacityField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                if (event.getCharacter().trim().isEmpty()) {
                    event.consume();
                }
            });
            capacityField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);

                // Allow only alphanumeric characters and underscores
                if (!Character.isDigit(inputChar)) {
                    event.consume();
                }
            });
            capacityField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    capacityField.setText(oldValue);
                }
            });
        }
        if (amountField != null) {
            amountField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                if (event.getCharacter().trim().isEmpty()) {
                    event.consume();
                }
            });
            amountField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);

                // Allow only alphanumeric characters and underscores
                if (!Character.isDigit(inputChar)) {
                    event.consume();
                }
            });
            amountField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    amountField.setText(oldValue);
                }
            });
        }
        if (locationField != null) {

            locationField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    locationField.setText(oldValue);
                }
            });
            locationField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);
                String currentText = locationField.getText();
                if (currentText.isEmpty() && (inputChar == ' ' || inputChar == '#' || inputChar == ',')) {
                    event.consume();
                }
                // Allow only alphanumeric characters and underscores
                if (!Character.isLetterOrDigit(inputChar) && inputChar != ' ' && inputChar != '#' && inputChar != ',') {
                    event.consume();
                }
            });
        }
        if (customerInfoField != null) {

            customerInfoField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    customerInfoField.setText(oldValue);
                }
            });
            customerInfoField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);
                String currentText = customerInfoField.getText();
                if (currentText.isEmpty() && (inputChar == ' ' || inputChar == '#' || inputChar == ',')) {
                    event.consume();
                }
                // Allow only alphanumeric characters and underscores
                if (!Character.isLetterOrDigit(inputChar) && inputChar != ' ' && inputChar != '#' && inputChar != ',') {
                    event.consume();
                }
            });
        }
        if (customerInfoField2 != null) {

            customerInfoField2.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    customerInfoField2.setText(oldValue);
                }
            });
            customerInfoField2.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);
                String currentText = customerInfoField2.getText();
                if (currentText.isEmpty() && (inputChar == ' ' || inputChar == '#' || inputChar == ',')) {
                    event.consume();
                }
                // Allow only alphanumeric characters and underscores
                if (!Character.isLetterOrDigit(inputChar) && inputChar != ' ' && inputChar != '#' && inputChar != ',') {
                    event.consume();
                }
            });
        }
        if (warehouseIDField4 != null) {
            warehouseIDField4.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                if (event.getCharacter().trim().isEmpty()) {
                    event.consume();
                }
            });
            warehouseIDField4.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    warehouseIDField4.setText(oldValue);
                }
            });
            warehouseIDField4.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);

                // Allow only alphanumeric characters and underscores
                if (!Character.isLetterOrDigit(inputChar)) {
                    event.consume();
                }
            });
        }
        if (warehouseNameField2 != null) {
            warehouseNameField2.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    warehouseNameField2.setText(oldValue);
                }
            });
            warehouseNameField2.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);
                String currentText = warehouseNameField2.getText();
                if (currentText.isEmpty() && inputChar == ' ') {
                    event.consume();
                }
                // Allow only alphanumeric characters and underscores
                if (!Character.isLetterOrDigit(inputChar) && inputChar != ' ') {
                    event.consume();
                }
            });
        }
        if (capacityField2 != null) {
            capacityField2.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                if (event.getCharacter().trim().isEmpty()) {
                    event.consume();
                }
            });
            capacityField2.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);

                // Allow only alphanumeric characters and underscores
                if (!Character.isDigit(inputChar)) {
                    event.consume();
                }
            });
            capacityField2.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    capacityField2.setText(oldValue);
                }
            });
        }
        if (locationField2 != null) {

            locationField2.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    locationField2.setText(oldValue);
                }
            });
            locationField2.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);
                String currentText = locationField2.getText();
                // Disallow space as the first character
                if (currentText.isEmpty() && (inputChar == ' ' || inputChar == '#' || inputChar == ',')) {
                    event.consume();
                }

                // Allow only alphanumeric characters and underscores
                if (!Character.isLetterOrDigit(inputChar) && inputChar != ' ' && inputChar != '#' && inputChar != ',') {
                    event.consume();
                }
            });
        }
        if (productNameField2 != null) {
            productNameField2.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    productNameField2.setText(oldValue);
                }
            });
            productNameField2.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);
                String currentText = productNameField2.getText();
                // Disallow space as the first character
                if (currentText.isEmpty() && inputChar == ' ') {
                    event.consume();
                }

                // Allow only alphanumeric characters and underscores
                if (!Character.isLetterOrDigit(inputChar) && inputChar != ' ') {
                    event.consume();
                }
            });
        }
        if (inventoryIDField != null) {
            inventoryIDField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                if (event.getCharacter().trim().isEmpty()) {
                    event.consume();
                }
            });
            inventoryIDField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    inventoryIDField.setText(oldValue);
                }
            });
            inventoryIDField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);

                // Allow only alphanumeric characters and underscores
                if (!Character.isLetterOrDigit(inputChar)) {
                    event.consume();
                }
            });
        }
        if (inventoryIDField2 != null) {
            inventoryIDField2.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                if (event.getCharacter().trim().isEmpty()) {
                    event.consume();
                }
            });
            inventoryIDField2.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    inventoryIDField2.setText(oldValue);
                }
            });
            inventoryIDField2.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);

                // Allow only alphanumeric characters and underscores
                if (!Character.isLetterOrDigit(inputChar)) {
                    event.consume();
                }
            });
        }
        if (warehouseIDField != null) {
            warehouseIDField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                if (event.getCharacter().trim().isEmpty()) {
                    event.consume();
                }
            });
            warehouseIDField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    warehouseIDField.setText(oldValue);
                }
            });
            warehouseIDField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);

                // Allow only alphanumeric characters and underscores
                if (!Character.isLetterOrDigit(inputChar)) {
                    event.consume();
                }
            });
        }
        if (warehouseIDField2 != null) {
            warehouseIDField2.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                if (event.getCharacter().trim().isEmpty()) {
                    event.consume();
                }
            });
            warehouseIDField2.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    warehouseIDField2.setText(oldValue);
                }
            });
            warehouseIDField2.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);

                // Allow only alphanumeric characters and underscores
                if (!Character.isLetterOrDigit(inputChar)) {
                    event.consume();
                }
            });
        }
        if (quantityField2 != null) {
            quantityField2.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                if (event.getCharacter().trim().isEmpty()) {
                    event.consume();
                }
            });
            quantityField2.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    quantityField2.setText(oldValue);
                }
            });
            quantityField2.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);

                // Allow only alphanumeric characters and underscores
                if (!Character.isDigit(inputChar)) {
                    event.consume();
                }
            });
        }
        if (quantityField != null) {
            quantityField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                if (event.getCharacter().trim().isEmpty()) {
                    event.consume();
                }
            });
            quantityField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    quantityField.setText(oldValue);
                }
            });
            quantityField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);

                // Allow only alphanumeric characters and underscores
                if (!Character.isDigit(inputChar)) {
                    event.consume();
                }
            });
        }
        if (DescriptionField2 != null) {
            DescriptionField2.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 255) {
                    DescriptionField2.setText(oldValue);
                }
            });
            DescriptionField2.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);
                String currentText = DescriptionField2.getText();
                // Disallow space as the first character
                if (currentText.isEmpty() && (inputChar == ' ' || inputChar == '#' || inputChar == ',')) {
                    event.consume();
                }

                // Allow only alphanumeric characters and underscores
                if (!Character.isLetterOrDigit(inputChar) && inputChar != ' ' && inputChar != '#' && inputChar != ',') {
                    event.consume();
                }
            });
        }
        if (supplierIDField2 != null) {
            supplierIDField2.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                if (event.getCharacter().trim().isEmpty()) {
                    event.consume();
                }
            });
            supplierIDField2.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    supplierIDField2.setText(oldValue);
                }
            });
            supplierIDField2.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);

                // Allow only alphanumeric characters and underscores
                if (!Character.isLetterOrDigit(inputChar)) {
                    event.consume();
                }
            });
        }
        if (supplierIDField5 != null) {
            supplierIDField5.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                if (event.getCharacter().trim().isEmpty()) {
                    event.consume();
                }
            });
            supplierIDField5.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() > 50) {
                    supplierIDField5.setText(oldValue);
                }
            });
            supplierIDField5.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                char inputChar = event.getCharacter().charAt(0);

                // Allow only alphanumeric characters and underscores
                if (!Character.isLetterOrDigit(inputChar)) {
                    event.consume();
                }
            });
        }

        //TableView Initialize, Event Listeners, Population
        if (vp != null) {
            productIDColumn.setCellValueFactory(new PropertyValueFactory<>("productID"));
            productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
            unitPriceColumn.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
            quantityInStockColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            supplierIDColumn.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
            materialTypeColumn.setCellValueFactory(cellData -> {
                if (cellData.getValue() instanceof RawMaterial) {
                    return new SimpleStringProperty(((RawMaterial) cellData.getValue()).getMaterialType());
                } else {
                    return new SimpleStringProperty("");
                }
            });

            prodDateColumn.setCellValueFactory(cellData -> {
                if (cellData.getValue() instanceof FinishedGoods) {
                    return new SimpleObjectProperty<>(((FinishedGoods) cellData.getValue()).getProductionDate());
                } else {
                    return new SimpleObjectProperty<>(null);
                }
            });

            // Populate the table view with data
            productList.addAll(products); // Assuming vp is your ArrayList<Product>
            populateTable();
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                search(newValue);
            });
        }
        if (vi != null) {
            inventoryIDColumn.setCellValueFactory(new PropertyValueFactory<>("inventoryID"));
            warehouseIDColumn.setCellValueFactory(new PropertyValueFactory<>("warehouseID"));
            quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            // Assuming products is a list of products inside each inventory
            productsColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                    cellData.getValue().getProducts().stream()
                            .map(Product::getProductID)
                            .collect(Collectors.joining(", "))));

            // Populate the table view with data
            inventoryList.addAll(inventories); // Assuming inventories is your list of Inventory objects
            populateTable2();
            searchField2.textProperty().addListener((observable, oldValue, newValue) -> {
                search2(newValue);
            });
        }
        if (vo != null) {
            orderIDColumn.setCellValueFactory(new PropertyValueFactory<>("orderID"));
            customerInfoColumn.setCellValueFactory(new PropertyValueFactory<>("customerInfo"));
            orderDateColumn.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
            // Assuming products is a list of products inside each inventory
            productsColumn3.setCellValueFactory(cellData -> new SimpleStringProperty(
                    cellData.getValue().getProducts().stream()
                            .map(Product::getProductID)
                            .collect(Collectors.joining(", "))));

            totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
            // Populate the table view with data
            orderList.addAll(orders); // Assuming inventories is your list of Inventory objects
            populateTable5();
            searchField5.textProperty().addListener((observable, oldValue, newValue) -> {
                search5(newValue);
            });
        }
        if (vs != null) {
            supplierIDColumn2.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
            supplierNameColumn.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
            contactColumn.setCellValueFactory(new PropertyValueFactory<>("contactInfo"));

            // Assuming products is a list of products inside each inventory
            productsColumn2.setCellValueFactory(cellData -> new SimpleStringProperty(
                    cellData.getValue().getProducts().stream()
                            .map(Product::getProductID)
                            .collect(Collectors.joining(", "))));

            // Populate the table view with data
            supplierList.addAll(suppliers); // Assuming inventories is your list of Inventory objects
            populateTable4();
            searchField4.textProperty().addListener((observable, oldValue, newValue) -> {
                search4(newValue);
            });
        }
        if (vw != null) {
            warehouseIDColumn2.setCellValueFactory(new PropertyValueFactory<>("warehouseID"));
            warehouseNameColumn.setCellValueFactory(new PropertyValueFactory<>("warehouseName"));
            locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
            CapacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));

            // Assuming products is a list of products inside each inventory
            inventoriesColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                    cellData.getValue().getInventories().stream()
                            .map(Inventory::getInventoryID)
                            .collect(Collectors.joining(", "))));

            // Populate the table view with data
            warehouseList.addAll(warehouses); // Assuming inventories is your list of Inventory objects
            populateTable3();
            searchField3.textProperty().addListener((observable, oldValue, newValue) -> {
                search3(newValue);
            });
        }
        if (vc != null) {
            customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
            customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
            contactInfoColumn.setCellValueFactory(new PropertyValueFactory<>("contactInfo"));
            addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

            // Populate the table view with data
            customerList.addAll(customers); // Assuming inventories is your list of Inventory objects
            populateTable6();
            searchField6.textProperty().addListener((observable, oldValue, newValue) -> {
                search6(newValue);
            });
        }
        if (vpy != null) {
            paymentIDColumn.setCellValueFactory(new PropertyValueFactory<>("paymentID"));
            orderIDColumn2.setCellValueFactory(new PropertyValueFactory<>("orderID"));
            amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
            methodColumn.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
            paymentDateColumn.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));

            // Populate the table view with data
            paymentlist.addAll(payments); // Assuming inventories is your list of Inventory objects
            populateTable7();
            searchField7.textProperty().addListener((observable, oldValue, newValue) -> {
                search7(newValue);
            });
        }
        if (td != null) {
            deliveryIDColumn.setCellValueFactory(new PropertyValueFactory<>("deliveryID"));
            orderIDColumn3.setCellValueFactory(new PropertyValueFactory<>("orderID"));
            estDateColumn.setCellValueFactory(new PropertyValueFactory<>("estimatedDeliveryDate"));
            actDateColumn.setCellValueFactory(new PropertyValueFactory<>("actualDeliveryDate"));
            carrierInfoColumn.setCellValueFactory(new PropertyValueFactory<>("carrierInfo"));
            statusColumn.setCellValueFactory(new PropertyValueFactory<>("deliveryStatus"));

            // Populate the table view with data
            deliveryList.addAll(deliverytrackers); // Assuming inventories is your list of Inventory objects
            populateTable8();
            searchField8.textProperty().addListener((observable, oldValue, newValue) -> {
                search8(newValue);
            });
        }
    }

    //Populate TableView
    private void populateTable8() {
        deliveryTable.setItems(deliveryList);
    }
    private void populateTable7() {
        paymentTable.setItems(paymentlist);
    }
    private void populateTable6() {
        customerTable.setItems(customerList);
    }
    private void populateTable5() {
        orderTable.setItems(orderList);
    }
    private void populateTable4() {
        supplierTable.setItems(supplierList);
    }
    private void populateTable3() {
        warehouseTable.setItems(warehouseList);
    }
    private void populateTable2() {
        inventoryTable.setItems(inventoryList);
    }
    private void populateTable() {
        productTable.setItems(productList);
    }

    // Implement search functionality of TableView
    public void search(String keyword) {
        ObservableList<Product> filteredList = FXCollections.observableArrayList();
        for (Product product : productList) {
            if (product.getProductName().toLowerCase().contains(keyword.toLowerCase())) {
                filteredList.add(product);
            }
        }
        productTable.setItems(filteredList);
    }
    public void search2(String keyword) {
        ObservableList<Inventory> filteredList = FXCollections.observableArrayList();
        for (Inventory inventory : inventoryList) {
            if (inventory.getInventoryID().toLowerCase().contains(keyword.toLowerCase())) {
                filteredList.add(inventory);
            }
        }
        inventoryTable.setItems(filteredList);
    }
    public void search3(String keyword) {
        ObservableList<Warehouse> filteredList = FXCollections.observableArrayList();
        for (Warehouse warehouse : warehouseList) {
            if (warehouse.getWarehouseID().toLowerCase().contains(keyword.toLowerCase())) {
                filteredList.add(warehouse);
            }
        }
        warehouseTable.setItems(filteredList);
    }
    public void search4(String keyword) {
        ObservableList<PrimarySupplier> filteredList = FXCollections.observableArrayList();
        for (PrimarySupplier supplier : supplierList) {
            if (supplier.getSupplierID().toLowerCase().contains(keyword.toLowerCase())) {
                filteredList.add(supplier);
            }
        }
        supplierTable.setItems(filteredList);
    }
    public void search5(String keyword) {
        ObservableList<Order> filteredList = FXCollections.observableArrayList();
        for (Order order : orderList) {
            if (order.getOrderID().toLowerCase().contains(keyword.toLowerCase())) {
                filteredList.add(order);
            }
        }
        orderTable.setItems(filteredList);
    }
    public void search6(String keyword) {
        ObservableList<Customer> filteredList = FXCollections.observableArrayList();
        for (Customer customer : customerList) {
            if (customer.getCustomerName().toLowerCase().contains(keyword.toLowerCase())) {
                filteredList.add(customer);
            }
        }
        customerTable.setItems(filteredList);
    }
    public void search7(String keyword) {
        ObservableList<Payment> filteredList = FXCollections.observableArrayList();
        for (Payment payment : paymentlist) {
            if (payment.getPaymentID().toLowerCase().contains(keyword.toLowerCase())) {
                filteredList.add(payment);
            }
        }
        paymentTable.setItems(filteredList);
    }
    public void search8(String keyword) {
        ObservableList<DeliveryTracking> filteredList = FXCollections.observableArrayList();
        for (DeliveryTracking dt : deliveryList) {
            if (dt.getOrderID().toLowerCase().contains(keyword.toLowerCase())) {
                filteredList.add(dt);
            }
        }
        deliveryTable.setItems(filteredList);
    }

    //Method to show error when field is empty
    private void handleTextFieldChange(String newValue, Label label) {
        // Check if the new value is empty
        if (newValue.trim().isEmpty()) {
            // Set error message and show the error label
            label.setText("Field cannot be empty!");
            label.setVisible(true);
        } else {
            // Hide error label if field is not empty
            label.setVisible(false);
        }
    }

    //Method to add product
    public int addProduct() throws IOException {
        int success = 0;
        String productID = productIDField.getText().trim();
        String productName = productNameField.getText().trim();
        String description = DescriptionField.getText().trim();
        double unitPrice = unitPriceSlider.getValue();
        int quantity = (int) quantitySlider.getValue();
        String supplierID = supplierIDField.getText().trim();
        if (productID.isEmpty() || productName.isEmpty() || description.isEmpty() || unitPrice == 0.0 || quantity == 0 || supplierID.isEmpty()) {
            return -1; // Return -1 if any field is empty or zero
        }
        if (productTypeTextField.getText().trim().equals("")) {
            if (productionDatePicker.getValue() == null) {

                return -1;
            }
            FinishedGoods finishedgood = new FinishedGoods(productID, productName, description, unitPrice, quantity, supplierID, productionDatePicker.getValue());

            products.add(finishedgood);
            return finishedgood.addProduct();
        } else {
            if (productTypeTextField.getText().trim().equals("")) {

                return -1;
            }
            RawMaterial rawmaterial = new RawMaterial(productID, productName, description, unitPrice, quantity, supplierID, productTypeTextField.getText().trim());
            products.add(rawmaterial);
            return rawmaterial.addProduct();
        }
    }

    //Methods for closing and minimizing window
    @FXML
    private void handleClose() {
        stage.close();
    }
    @FXML
    private void handleMinimize() {
        stage.setIconified(true);
    }

    //Methods to implement functionality of FXML files
    @FXML
    private void handleLoginButtonClick() throws IOException {
        mainApp.loadLoginView(); // Call the loadLoginView() method in the main application
    }
    @FXML
    private void handlechangePasswordButtonClick() throws IOException {
        mainApp.loadchangePasswordView(); // Call the loadLoginView() method in the main application
    }
    @FXML
    private void handleforgotPasswordButtonClick() throws IOException {
        mainApp.loadforgotPasswordView(); // Call the loadLoginView() method in the main application
    }
    @FXML
    private void handleChangePassword() {
        String oldPassword = oldPasswordField.getText();
        String newPassword = newPasswordField.getText();

        if (oldPassword.isEmpty() || newPassword.isEmpty()) {
            // Both text fields are empty, set error message and make errorLabel visible
            errorLabel.setText("Field cannot be empty!");
            errorLabel.setVisible(true);
        } else {
            try {
                // Load credentials from JSON file
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(new File(passwordpath));
                String storedPassword = rootNode.get("password").asText();

                // Verify the old password
                if (oldPassword.equals(storedPassword)) {
                    // Update the password in the JSON structure
                    ((ObjectNode) rootNode).put("password", newPassword);

                    // Write the updated JSON back to the file
                    objectMapper.writeValue(new File(passwordpath), rootNode);

                    // Inform the user that the password was changed
                    errorLabel.setText("Password Changed!");
                    errorLabel.setTextFill(Color.BLACK);
                    errorLabel.setVisible(true);
                } else {
                    // Inform the user that the old password is incorrect
                    errorLabel.setText("Old Password is incorrect!");
                    errorLabel.setVisible(true);
                }
            } catch (IOException e) {
                e.printStackTrace();
                errorLabel.setText("An error occurred while changing the password!");
                errorLabel.setVisible(true);
            }
        }
    }
    @FXML
    private void handleShowPassword() {
        String userkey = keyField.getText();

        if (userkey.isEmpty()) {
            // Key field is empty, set error message and make pwdcheck visible
            pwdcheck.setText("Field cannot be empty!");
            pwdcheck.setVisible(true);
        } else {
            if (userkey.equals(key)) {
                try {
                    // Load password from JSON file
                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode rootNode = objectMapper.readTree(new File(passwordpath));
                    String password = rootNode.get("password").asText();

                    // Display the password
                    pwdcheck.setText(password);
                    pwdcheck.setTextFill(Color.BLACK);
                    pwdcheck.setVisible(true);
                } catch (IOException e) {
                    e.printStackTrace();
                    pwdcheck.setText("An error occurred while loading the password!");
                    pwdcheck.setVisible(true);
                }
            } else {
                // Key is incorrect, set error message and make pwdcheck visible
                pwdcheck.setText("Key is incorrect!");
                pwdcheck.setVisible(true);
            }
        }
    }
    @FXML
    private void handleShowPasswordButtonClick(ActionEvent event) {
        Button button = (Button) event.getSource();
        PasswordField passwordField = null;
        TextField textField = null;
        ImageView eyeIcon = null;
        boolean passwordVisible = false;

        if (button.getId().equals("showPasswordButton1")) {
            passwordField = oldPasswordField;
            textField = oldPasswordField_shown;
            eyeIcon = eyeIcon1;
            passwordVisible = passwordVisible1;
        } else if (button.getId().equals("showPasswordButton2")) {
            passwordField = newPasswordField;
            textField = newPasswordField_shown;
            eyeIcon = eyeIcon2;
            passwordVisible = passwordVisible2;
        } else if (button.getId().equals("showPasswordButton3")) {
            passwordField = passwordField1;
            textField = passwordField1_shown;
            eyeIcon = eyeIcon3;
            passwordVisible = passwordVisible3;
        }

        if (passwordField != null && textField != null && eyeIcon != null) {
            // Toggle the visibility of the password field and text field
            passwordField.setVisible(passwordVisible);
            passwordField.setManaged(passwordVisible);
            textField.setVisible(!passwordVisible);
            textField.setManaged(!passwordVisible);

            // Update the eye icon based on the current state
            if (passwordVisible) {
                eyeIcon.setImage(new Image(getClass().getResourceAsStream("hidden.png")));
            } else {
                eyeIcon.setImage(new Image(getClass().getResourceAsStream("show.png")));
            }

            // Update the text field with the content of the password field
            if (!passwordVisible) {
                textField.setText(passwordField.getText());
            } else {
                passwordField.setText(textField.getText());
                textField.setText("");
            }

            // Update the password visibility flag
            if (button.getId().equals("showPasswordButton1")) {
                passwordVisible1 = !passwordVisible1;
            } else if (button.getId().equals("showPasswordButton2")) {
                passwordVisible2 = !passwordVisible2;
            } else if (button.getId().equals("showPasswordButton3")) {
                passwordVisible3 = !passwordVisible3;
            }
        }
    }
    @FXML
    private void handleLogin() throws IOException {
        // Load credentials from JSON file
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(new File("src/main/resources/org/qaswasabd/scms/JSON/details.json"));
        String storedUsername = rootNode.get("username").asText();
        String storedPassword = rootNode.get("password").asText();

        String user_password = passwordField1.getText();
        String user_username = usernameField.getText();

        if (user_username.isEmpty() || user_password.isEmpty()) {
            // Both text fields are empty, set error message and make errorLabel visible
            errorLabel2.setText("Field cannot be empty!");
            errorLabel2.setVisible(true);
        } else {
            if (user_username.equals(storedUsername)) {
                if (user_password.equals(storedPassword)) {
                    // Assuming mainApp has a method loadHomeView to load the home view
                    mainApp.loadHomeView();
                } else {
                    errorLabel2.setText("Password is incorrect!");
                    errorLabel2.setVisible(true);
                }
            } else {
                errorLabel2.setText("Username is incorrect!");
                errorLabel2.setVisible(true);
            }
        }
    }
    @FXML
    private void handleInventoryMgmt() throws IOException {
        mainApp.loadInventoryMgmtView();
    }
    @FXML
    private void handleProductMgmt() throws IOException {
        mainApp.loadProductMgmtView();
    }
    @FXML
    private void handleaddProductMgmt() throws IOException {
        mainApp.loadaddProductView();
    }
    @FXML
    private void handleWarehouseMgmt() throws IOException {
        mainApp.loadWarehouseMgmtView();
    }
    @FXML
    private void handleupdateProductMgmt() throws IOException {
        mainApp.loadupdateProductView();
    }
    @FXML
    private void handledeleteProductMgmt() throws IOException {
        mainApp.loaddeleteProductView();
    }
    @FXML
    private void handleHomeView() throws IOException {
        mainApp.loadHomeView();
    }
    @FXML
    private void handleAddProductClick() throws IOException {
        if (addProduct() != -1) {
            errorLabel3.setText("Added product succesfully!");
            errorLabel3.setTextFill(Color.BLACK);
            errorLabel3.setVisible(true);
        } else {
            errorLabel3.setText("Product could not be added!");
            errorLabel3.setVisible(true);
        }
    }
    @FXML
    private void handleUpdateProductClick() throws IOException {
        String productID = productIDField.getText().trim();
        String productName = productNameField2.getText().trim();
        String description = DescriptionField2.getText().trim();
        double unitPrice = unitPriceSlider2.getValue();
        int quantity = (int) quantitySlider2.getValue();
        String supplierID = supplierIDField2.getText().trim();
        String type = productTypeTextField2.getText().trim();
        LocalDate date = productionDatePicker2.getValue();
        for (Product product : products) {
            if (product.getProductID().equals(productID)) {
                if (!productName.equals("")) {
                    product.setProductName(productName);
                }
                if (!description.equals("")) {
                    product.setDescription(description);
                }
                if (unitPrice != 0) {
                    product.setUnitPrice(unitPrice);
                }
                if (quantity != 0) {
                    product.setQuantity(quantity);
                }
                if (!supplierID.equals("")) {
                    product.setSupplierID(supplierID);
                }
                if (!type.equals("")) {
                    RawMaterial rawMaterial = (RawMaterial) product;
                    rawMaterial.setMaterialType(type);
                }
                if (date != null) {
                    FinishedGoods fgoods = (FinishedGoods) product;
                    fgoods.setProductionDate(date);
                }
                product.updateProduct();
                errorLabel4.setText("Updated product succesfully!");
                errorLabel4.setTextFill(Color.BLACK);
                errorLabel4.setVisible(true);
                return;
            }
        }
        errorLabel4.setText("Product could not be updated!");
        errorLabel4.setVisible(true);
    }
    @FXML
    private void handleDeleteProductClick() throws IOException {
        String id = productIDField.getText().trim();
        Product productToDelete = null;

        // Find the product to delete
        for (Product product : products) {
            if (product.getProductID().equals(id)) {
                productToDelete = product;
                break;
            }
        }

        if (productToDelete != null) {
            // Create a confirmation dialog
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Delete Product");
            alert.setContentText("Are you sure you want to delete this product?");

            // Optional: Change the button types (default is OK and Cancel)
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

            // Show the dialog and wait for user response
            ButtonType result = alert.showAndWait().orElse(ButtonType.NO);

            if (result == ButtonType.YES) {
                // Remove the product from all inventories
                for (Inventory inventory : inventories) {
                    ArrayList<Product> productz = new ArrayList<>(inventory.getProducts());
                    productz.removeIf(p -> p.getProductID().equals(id));
                    inventory.setProducts(productz);
                    inventory.updateInventoryItem();
                }

                // Update all warehouses
                for (Warehouse warehouse : warehouses) {
                    ArrayList<Inventory> invent = new ArrayList<>(warehouse.getInventories());
                    for (Inventory invente : invent) {
                        if (invente.getProducts().removeIf(p -> p.getProductID().equals(id))) {
                            invente.updateInventoryItem();
                        }
                    }
                    warehouse.setInventories(invent);
                    warehouse.updateWarehouse();
                }

                // Remove the product from all suppliers
                for (PrimarySupplier supplier : suppliers) {
                    ArrayList<Product> supplierProducts = new ArrayList<>(supplier.getProducts());
                    supplierProducts.removeIf(p -> p.getProductID().equals(id));
                    supplier.setProducts(supplierProducts);
                    supplier.updateSupplier();
                }

                // Delete the product and remove it from the product list
                productToDelete.deleteProduct();
                products.remove(productToDelete);

                errorLabel5.setText("Removed product successfully!");
                errorLabel5.setTextFill(Color.BLACK);
                errorLabel5.setVisible(true);
            } else {
                // User clicked No or closed the dialog
                errorLabel5.setText("Deletion canceled!");
                errorLabel5.setVisible(true);
            }
        } else {
            errorLabel5.setText("Product could not be found!");
            errorLabel5.setVisible(true);
        }
    }
    @FXML
    private void handleviewProduct() throws IOException {
        mainApp.loadviewProductView();
    }
    @FXML
    private void handleInvMgmt() throws IOException {
        mainApp.loadinvManagementView();
    }
    @FXML
    private void handleAddInventory() throws IOException {
        mainApp.loadaddInventoryView();
    }
    @FXML
    private void handleUpdateInventory() throws IOException {
        mainApp.loadupdateInventoryView();
    }
    @FXML
    private void handleDeleteInventory() throws IOException {
        mainApp.loaddeleteInventoryView();
    }
    @FXML
    private void handleAddInventoryClick() throws IOException {
        String inventoryid = inventoryIDField.getText().trim();
        String warehouseid = warehouseIDField.getText().trim();
        ArrayList<Product> userProducts = new ArrayList<>(selectProducts.getSelectionModel().getSelectedItems());
        if (inventoryid.equals("") || warehouseid.equals("") || quantityField.getText().trim().equals("") || userProducts.isEmpty()) {
            errorLabel6.setText("Failed to add!");
            errorLabel6.setVisible(true);
            return;
        }
        int quantity = Integer.parseInt(quantityField.getText().trim());
        Inventory inventory = new Inventory(inventoryid, warehouseid, quantity, userProducts);
        inventories.add(inventory);
        if (inventory.addInventoryItem() != -1) {
            errorLabel6.setText("Added Succesfully!");
            errorLabel6.setTextFill(Color.BLACK);
            errorLabel6.setVisible(true);
        } else {
            errorLabel6.setText("Failed to add!");
            errorLabel6.setVisible(true);
        }
    }
    @FXML
    private void handleUpdateInventoryClick() throws IOException {
        String inventoryid = inventoryIDField2.getText().trim();
        String warehouseid = warehouseIDField2.getText().trim();
        int quantity = 0;
        if(!quantityField2.getText().trim().equals("")) {
            quantity = Integer.parseInt(quantityField2.getText().trim());
        }
        ArrayList<Product> userProducts = new ArrayList<>(selectProducts2.getSelectionModel().getSelectedItems());
        for (Inventory inventory : inventories) {
            if (inventory.getInventoryID().equals(inventoryid)) {
                if (!warehouseid.equals("")) {
                    inventory.setWarehouseID(warehouseid);
                }
                if (quantity != 0) {
                    inventory.setQuantity(quantity);
                }
                if (!userProducts.isEmpty()) {
                    inventory.setProducts(userProducts);
                }
                inventory.updateInventoryItem();
                errorLabel7.setText("Updated Succesfully!");
                errorLabel7.setTextFill(Color.BLACK);
                errorLabel7.setVisible(true);
                return;
            }
        }
        errorLabel7.setText("Update Failed!");
        errorLabel7.setTextFill(Color.BLACK);
        errorLabel7.setVisible(true);
    }
    @FXML
    private void handleDeleteInventoryClick() throws IOException {
        String id = inventoryIDField3.getText().trim();
        Inventory inventoryToDelete = null;

        // Find the inventory to delete
        for (Inventory inventory : inventories) {
            if (inventory.getInventoryID().equals(id)) {
                inventoryToDelete = inventory;
                break;
            }
        }

        if (inventoryToDelete != null) {
            // Create a confirmation dialog
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Delete Inventory");
            alert.setContentText("Are you sure you want to delete this inventory?");

            // Optional: Change the button types (default is OK and Cancel)
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

            // Show the dialog and wait for user response
            ButtonType result = alert.showAndWait().orElse(ButtonType.NO);

            if (result == ButtonType.YES) {
                // Remove the inventory from all warehouses that store it
                for (Warehouse warehouse : warehouses) {
                    ArrayList<Inventory> inventoriesToRemove = new ArrayList<>();
                    for (Inventory warehouseInventory : warehouse.getInventories()) {
                        if (warehouseInventory.getInventoryID().equals(id)) {
                            inventoriesToRemove.add(warehouseInventory);
                        }
                    }
                    warehouse.getInventories().removeAll(inventoriesToRemove);
                    warehouse.updateWarehouse();
                }

                // Delete the inventory and remove it from the inventory list
                inventoryToDelete.deleteInventoryItem();
                inventories.remove(inventoryToDelete);

                errorLabel8.setText("Removed inventory successfully!");
                errorLabel8.setTextFill(Color.BLACK);
                errorLabel8.setVisible(true);
            } else {
                // User clicked No or closed the dialog
                errorLabel8.setText("Deletion canceled!");
                errorLabel8.setVisible(true);
            }
        } else {
            errorLabel8.setText("Inventory could not be found!");
            errorLabel8.setVisible(true);
        }
    }
    @FXML
    private void handleViewInventory() throws IOException {
        mainApp.loadviewInventoryView();
    }
    @FXML
    private void handleAddWarehouse() throws IOException {
        mainApp.loadaddWarehouseView();
    }
    @FXML
    private void handleUpdateWarehouse() throws IOException {
        mainApp.loadupdateWarehouseView();
    }
    @FXML
    private void handleDeleteWarehouse() throws IOException {
        mainApp.loaddeleteWarehouseView();
    }
    @FXML
    private void handleViewWarehouse() throws IOException {
        mainApp.loadviewWarehouseView();
    }
    @FXML
    private void handleAddWarehouseClick() throws IOException {
        String warehouseid = warehouseIDField3.getText().trim();
        String warehousename = warehouseNameField.getText().trim();
        String location = locationField.getText().trim();
        if (capacityField.getText().trim().equals("")) {
            errorLabel9.setText("Failed to Add!");
            errorLabel9.setVisible(true);
            return;
        }
        int capacity = Integer.parseInt(capacityField.getText().trim());
        if (warehouseid.equals("") || warehousename.equals("") || location.equals("")) {
            errorLabel9.setText("Failed to add!");
            errorLabel9.setTextFill(Color.BLACK);
            errorLabel9.setVisible(true);
            return;
        }
        ArrayList<Inventory> userInventories = new ArrayList<>(inventoriesList.getSelectionModel().getSelectedItems());
        Warehouse warehouse = new Warehouse(warehouseid, warehousename, location, capacity, userInventories);
        if (warehouse.addWarehouse() != -1) {
            warehouses.add(warehouse);
            errorLabel9.setText("Added Succesfully!");
            errorLabel9.setTextFill(Color.BLACK);
            errorLabel9.setVisible(true);
        } else {
            errorLabel9.setText("Failed to add!");
            errorLabel9.setVisible(true);
        }
    }
    @FXML
    private void handleUpdateWarehouseClick() throws IOException {
        String warehouseid = warehouseIDField4.getText().trim();
        String warehousename = warehouseNameField2.getText().trim();
        String location = locationField2.getText().trim();
        int capacity = 0;
        if (!capacityField2.getText().trim().equals("")) {
            capacity = Integer.parseInt(capacityField2.getText().trim());
        }
        ArrayList<Inventory> userInventories = new ArrayList<>(inventoriesList2.getSelectionModel().getSelectedItems());
        for (Warehouse warehouse : warehouses) {
            if (warehouse.getWarehouseID().equals(warehouseid)) {
                if (!warehousename.equals("")) {
                    warehouse.setWarehouseName(warehousename);
                }
                if (!location.equals("")) {
                    warehouse.setLocation(location);
                }
                if (capacity != 0) {
                    warehouse.setCapacity(capacity);
                }
                if (!userInventories.isEmpty()) {
                    warehouse.setInventories(userInventories);
                }
                warehouse.updateWarehouse();
                errorLabel10.setText("Updated Succesfully!");
                errorLabel10.setTextFill(Color.BLACK);
                errorLabel10.setVisible(true);
                return;
            }
        }
        errorLabel10.setText("Update Failed!");
        errorLabel10.setVisible(true);
    }
    @FXML
    private void handleDeleteWarehouseClick() throws IOException {
        String warehouseId = warehouseIDField5.getText().trim();
        Warehouse warehouseToDelete = null;

        // Find the warehouse to delete
        for (Warehouse warehouse : warehouses) {
            if (warehouse.getWarehouseID().equals(warehouseId)) {
                warehouseToDelete = warehouse;
                break;
            }
        }

        if (warehouseToDelete != null) {
            // Create a confirmation dialog
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Delete Warehouse");
            alert.setContentText("Are you sure you want to delete this warehouse?");

            // Optional: Change the button types (default is OK and Cancel)
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

            // Show the dialog and wait for user response
            ButtonType result = alert.showAndWait().orElse(ButtonType.NO);

            if (result == ButtonType.YES) {
                // Remove the warehouse ID from all inventories inside the warehouse
                for (Inventory inventory : warehouseToDelete.getInventories()) {
                        inventory.setWarehouseID(""); // Set warehouse ID to an empty string or handle it according to your logic
                        inventory.updateInventoryItem();

                }

                // Delete the warehouse and remove it from the warehouses list
                warehouseToDelete.deleteWarehouse();
                warehouses.remove(warehouseToDelete);

                errorLabel11.setVisible(true);
                errorLabel11.setText("Removed successfully!");
                errorLabel11.setTextFill(Color.BLACK);
            } else {
                // User clicked No or closed the dialog
                errorLabel11.setVisible(true);
                errorLabel11.setText("Deletion canceled!");
            }
        } else {
            errorLabel11.setVisible(true);
            errorLabel11.setText("Warehouse not found!");
        }
    }
    @FXML
    private void handleSRM() throws IOException {
        mainApp.loadSRMView();
    }
    @FXML
    private void handleAddSupplier() throws IOException {
        mainApp.loadaddSupplierView();
    }
    @FXML
    private void handleUpdateSupplier() throws IOException {
        mainApp.loadupdateSupplierView();
    }
    @FXML
    private void handleaddSupplierClick() throws IOException {
        String supplierID = supplierIDField3.getText().trim();
        String supplierName = supplierNameField.getText().trim();
        String contact = contactField.getText().trim();
        ArrayList<Product> userProducts = new ArrayList<>(selectProducts3.getSelectionModel().getSelectedItems());
        if (supplierID.equals("") || supplierName.equals("") || contact.equals("") || userProducts.isEmpty()) {
            errorLabel12.setVisible(true);
            errorLabel12.setText("Failed to add");
            return;
        }
        PrimarySupplier supplier = new PrimarySupplier(supplierID, supplierName, contact, userProducts);
        suppliers.add(supplier);
        if (supplier.addSupplier() != -1){
            errorLabel12.setVisible(true);
        errorLabel12.setText("Added succesfully");
        errorLabel12.setTextFill(Color.BLACK);
    }else

    {
        errorLabel12.setVisible(true);
        errorLabel12.setText("Failed to add");
    }
}
    @FXML
    private void handleupdateSupplierClick() throws IOException{
        String supplierID = supplierIDField4.getText().trim();
        String supplierName = supplierNameField2.getText().trim();
        String contact = contactField2.getText().trim();
        ArrayList<Product> userProducts = new ArrayList<>(selectProducts4.getSelectionModel().getSelectedItems());
        for(PrimarySupplier supplier: suppliers){
            if(supplier.getSupplierID().equals(supplierID)){
                if(!supplierName.equals("")){
                    supplier.setSupplierName(supplierName);
                }
                if(!contact.equals("")){
                    supplier.setContactInfo(contact);
                }
                if(!userProducts.isEmpty()){
                    supplier.setProducts(userProducts);
                }
                supplier.updateSupplier();
                errorLabel13.setVisible(true);
                errorLabel13.setText("Update Successfull");
                errorLabel13.setTextFill(Color.BLACK);
                return ;
            }
        }
        errorLabel13.setVisible(true);
        errorLabel13.setText("Update Failed");
    }
    @FXML
    private void handleDeleteSupplier() throws IOException{
        mainApp.loaddeleteSupplierView();
    }
    @FXML
    private void handleDeleteSupplierClick() throws IOException {
        String supplierid = supplierIDField5.getText().trim();
        if (supplierid.equals("")) {
            errorLabel14.setVisible(true);
            errorLabel14.setText("Supplier ID cannot be empty!");
            return;
        }

        // Create a confirmation dialog
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Delete Supplier");
        alert.setContentText("Are you sure you want to delete this supplier?");

        // Optional: Change the button types (default is OK and Cancel)
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        // Show the dialog and wait for user response
        ButtonType result = alert.showAndWait().orElse(ButtonType.NO);

        if (result == ButtonType.YES) {
            // User clicked Yes, proceed with deletion
            boolean found = false;
            for (PrimarySupplier supplier : suppliers) {
                if (supplier.getSupplierID().equals(supplierid)) {
                    supplier.deleteSupplier();
                    suppliers.remove(supplier);
                    errorLabel14.setVisible(true);
                    errorLabel14.setText("Removed successfully!");
                    errorLabel14.setTextFill(Color.BLACK);
                    found = true;
                    break;
                }
            }

            if (!found) {
                errorLabel14.setVisible(true);
                errorLabel14.setText("Supplier ID not found!");
            }
        } else {
            // User clicked No or closed the dialog
            errorLabel14.setVisible(true);
            errorLabel14.setText("Deletion canceled!");
        }
    }
    @FXML
    private void handleViewSuppliers() throws IOException{
        mainApp.loadviewSupplierView();
    }
    private void handleKeyPress(KeyEvent keyEvent){
        if (keyEvent.getCode() == KeyCode.ENTER) {
            // Call another function when Enter is pressed
            try {
                handleLogin();
            }catch(IOException e){
                errorLabel2.setText("Failed to login");
                errorLabel2.setVisible(true);
            }
        }
    }
    private void handleKeyPress2(KeyEvent keyEvent){
        if (keyEvent.getCode() == KeyCode.ENTER) {
            // Call another function when Enter is pressed
            try {
                handleLoginButtonClick();
            }catch(IOException e){
                System.exit(-1);
            }
        }
    }
    @FXML
    private void handleOrderManagement() throws IOException {
        mainApp.loadorderMgmtView();
    }
    @FXML
    private void handleDemandForecast() throws IOException {
        mainApp.loadDemandForecastView();
    }
    @FXML
    private void handleDemandForecastClick() throws IOException{
        String productID = productIDField2.getText().trim();
        if(productID.equals("")){
            errorLabel15.setText("Field cannot be empty!");
            errorLabel15.setVisible(true);
            return;
        }
        boolean exists = false;
        for(Product product: products){
            if(product.getProductID().equals(productID)){
                exists = true;
            }
        }
        if(!exists){
            errorLabel15.setText("Product does not exist");
            errorLabel15.setVisible(true);
            return;
        }
            DemandForecasting temp = new DemandForecasting(productID);
            String demand = Integer.toString(temp.generateDemandForecast());
            errorLabel15.setText(demand + " units");
            errorLabel15.setTextFill(Color.BLACK);
            errorLabel15.setVisible(true);
    }
    @FXML
    private void handleOrderMgmt() throws IOException {
        mainApp.loadOrderManagementView();
    }
    @FXML
    private void handleaddOrder() throws IOException {
        mainApp.loadaddOrderView();
    }
    @FXML
    private void handledeleteorder() throws IOException {
        mainApp.loaddeleteorderView();
    }
    @FXML
    private void handleupdateOrder() throws IOException {
        mainApp.loadupdateorderview();
    }
    @FXML
    private void handleviewOrder() throws IOException {
        mainApp.loadviewOrdersView();
    }
    @FXML
    private void handleCustomerManagement() throws IOException {
        mainApp.loadCustomerManagementView();
    }
    @FXML
    private void handleaddCustomer() throws IOException {
        mainApp.loadaddCustomerView();
    }
    @FXML
    private void handleupdateCustomer() throws IOException {
        mainApp.loadupdateCustomerView();
    }
    @FXML
    private void handleViewCustomers() throws IOException {
        mainApp.loadviewCustomersView();
    }
    @FXML
    private void handlePayment() throws IOException {
        mainApp.loadPaymentManagementView();
    }
    @FXML
    private void handleMakePayment() throws IOException {
        mainApp.loadmakePaymentView();
    }
    @FXML
    private void handleviewPayment() throws IOException {
        mainApp.loadviewPaymentsView();
    }
    @FXML
    private void handleDeliveryTrackingMgmt() throws IOException {
        mainApp.loadDeliveryTrackingMgmtView();
    }
    @FXML
    private void handleUpdateDeliveryTracking() throws IOException {
        mainApp.loadupdateDeliveryTracking();
    }
    @FXML
    private void handleViewDeliveries() throws IOException {
        mainApp.loadviewDeliveries();
    }
    @FXML
    private void handleaddOrderClick() throws IOException{
        String orderID = orderIDField.getText().trim();
        String customerInfo = customerInfoField.getText().trim();
        LocalDate date = orderDatePicker.getValue();
        ArrayList<Product> userProducts = new ArrayList<>(selectProducts5.getSelectionModel().getSelectedItems());
        if(orderID.equals("") || customerInfo.equals("")||date == null||userProducts.isEmpty()){
            errorLabel16.setVisible(true);
            errorLabel16.setText("Field cannot be empty!");
            return;
        }
        Order order = new Order(orderID,date,userProducts,customerInfo);
        orders.add(order);
        if(order.addOrder()!=-1){
            DeliveryTracking dt = new DeliveryTracking(orderID+"_DT",orderID,"RabbitCourierX",date.plusWeeks(1));
            deliverytrackers.add(dt);
            if(dt.addDeliveryTracking()!=-1) {
                errorLabel16.setVisible(true);
                errorLabel16.setText("Order added!");
                errorLabel16.setTextFill(Color.BLACK);
            }else{
                errorLabel16.setVisible(true);
                errorLabel16.setText("Failed to add order");
            }
        }else{
            errorLabel16.setVisible(true);
            errorLabel16.setText("Failed to add order");
        }
    }
    @FXML
    private void handleupdateOrderClick() throws IOException{
        String orderID = orderIDField2.getText().trim();
        String customerInfo = customerInfoField2.getText().trim();
        orderDatePicker2.getEditor().setDisable(true);
        LocalDate date = orderDatePicker2.getValue();
        ArrayList<Product> userProducts = new ArrayList<>(selectProducts6.getSelectionModel().getSelectedItems());
        if(orderID.equals("")){
            errorLabel17.setVisible(true);
            errorLabel17.setText("Order ID cannot be empty!");
            return;
        }
        for(Order order :orders){
            if(order.getOrderID().equals(orderID)){
                if(!customerInfo.equals("")){
                    order.setCustomerInfo(customerInfo);
                }
                if(date!=null){
                    order.setOrderDate(date);
                }
                if(!userProducts.isEmpty()){
                    order.setProducts(userProducts);
                }
                if(order.updateOrder()!=-1){
                    errorLabel17.setVisible(true);
                    errorLabel17.setText("Order updated!");
                    errorLabel17.setTextFill(Color.BLACK);
                }else{
                    errorLabel17.setVisible(true);
                    errorLabel17.setText("Failed to update!");
                    return;
                }
            }
        }
        errorLabel17.setVisible(true);
        errorLabel17.setText("Failed to update!");
        return;
    }
    @FXML
    private void handleDeleteOrderClick() throws IOException {
        String orderid = orderIDField3.getText().trim();
        if (orderid.equals("")) {
            errorLabel18.setVisible(true);
            errorLabel18.setText("Order ID cannot be empty!");
            return;
        }

        // Find the order to delete
        Order orderToDelete = null;
        for (Order order : orders) {
            if (order.getOrderID().equals(orderid)) {
                orderToDelete = order;
                break;
            }
        }

        if (orderToDelete != null) {
            // Create a confirmation dialog
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Delete Order");
            alert.setContentText("Are you sure you want to delete this order?");

            // Optional: Change the button types (default is OK and Cancel)
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

            // Show the dialog and wait for user response
            ButtonType result = alert.showAndWait().orElse(ButtonType.NO);

            if (result == ButtonType.YES) {
                // Attempt to remove the order
                if (orderToDelete.removeOrder() != -1) {
                    orders.remove(orderToDelete);
                    errorLabel18.setVisible(true);
                    errorLabel18.setText("Order removed successfully!");
                    errorLabel18.setTextFill(Color.BLACK);
                } else {
                    errorLabel18.setVisible(true);
                    errorLabel18.setText("Failed to remove order!");
                }
            } else {
                // User clicked No or closed the dialog
                errorLabel18.setVisible(true);
                errorLabel18.setText("Deletion canceled!");
            }
        } else {
            errorLabel18.setVisible(true);
            errorLabel18.setText("Order not found!");
        }
    }
    @FXML
    private void handleAddCustomerClick() throws IOException{
        String customerid = customerIDField.getText().trim();
        String customername = customerNameField.getText().trim();
        String contactinfo = contactInfoField.getText().trim();
        String address = addressField.getText().trim();
        if(customerid.equals("")||customername.equals("")||contactinfo.equals("")||address.equals("")){
            errorLabel19.setVisible(true);
            errorLabel19.setText("Field cannot be empty!");
            return ;
        }
        Customer customer = new Customer(customerid,customername,contactinfo,address);
        customers.add(customer);
        if(customer.addCustomer()!=-1){
            errorLabel19.setVisible(true);
            errorLabel19.setText("Customer added!");
            errorLabel19.setTextFill(Color.BLACK);
            return;
        }else{
            errorLabel19.setVisible(true);
            errorLabel19.setText("Customer could not be added!");
        }
    }
    @FXML
    private void handleUpdateCustomerClick() throws IOException{
        String customerID = customerIDField2.getText().trim();
        String customername = customerNameField2.getText().trim();
        String address = addressField2.getText().trim();
        String contactinfo = contactInfoField2.getText().trim();
        if(customerID.equals("")){
            errorLabel20.setVisible(true);
            errorLabel20.setText("Field cannot be empty!");
            return;
        }
        for(Customer customer: customers){
            if(customer.getCustomerID().equals(customerID)){
                if(!customername.equals("")){
                    customer.setCustomerName(customername);
                }
                if(!address.equals("")){
                    customer.setAddress(address);
                }
                if(!contactinfo.equals("")){
                    customer.setAddress(address);
                }
                if(customer.updateCustomer()!=-1){
                    errorLabel20.setVisible(true);
                    errorLabel20.setText("Customer updated!");
                    errorLabel20.setTextFill(Color.BLACK);
                    return;
                }else{
                    errorLabel20.setVisible(true);
                    errorLabel20.setText("Customer could not be updated!");
                    return;
                }
            }
        }
        errorLabel20.setVisible(true);
        errorLabel20.setText("Failed to update!");
        return;
    }
    @FXML
    private void handlemakePaymentClick() throws IOException{
        String paymentid = paymentIDField.getText().trim();
        String orderid = orderIDField4.getText().trim();
        if(amountField.getText().trim().equals("")){
            errorLabel21.setVisible(true);
            errorLabel21.setText("Field cannot be empty!");
            return;
        }
        double amount = Double.parseDouble(amountField.getText().trim());
        LocalDate date = paymentDatePicker.getValue();
        String method = methodList.getValue();
        if(paymentid.equals("")||orderid.equals("")||date==null||method==null){
            errorLabel21.setVisible(true);
            errorLabel21.setText("Field cannot be empty!");
            return;
        }
        Payment payment = new Payment(paymentid,orderid,method,amount,date);
        payments.add(payment);
        if(payment.processPayment()!=-1){
            for(DeliveryTracking dt : deliverytrackers){
                if(dt.getOrderID().equals(orderid)){
                    dt.setDeliveryStatus(Status.PAID);
                    dt.updateDeliveryStatus();
                }
            }
            errorLabel21.setVisible(true);
            errorLabel21.setText("Payment made sucessfully!");
            errorLabel21.setTextFill(Color.BLACK);
            return;
        }
        errorLabel21.setVisible(true);
        errorLabel21.setText("Payment failed!");
        return;
    }
    @FXML
    private void handleUpdateDeliveryClick() throws IOException{
        String orderid = orderIDField5.getText().trim();
        if(orderid.equals("")){
            errorLabel22.setVisible(true);
            errorLabel22.setText("Order ID cannot be empty!");
            return;
        }
        LocalDate estDate = estDatePicker.getValue();
        LocalDate actDate = actDatePicker.getValue();
        Status status = statusList.getValue();
        String carrierinfo = carrierInfoField.getText().trim();
        for(DeliveryTracking dt : deliverytrackers){
            if(dt.getOrderID().equals(orderid)){
                if(estDate!=null){
                    dt.setEstimatedDeliveryDate(estDate);
                }
                if(actDate!=null){
                    dt.setActualDeliveryDate(actDate);
                }
                if(status!=null){
                    dt.setDeliveryStatus(status);
                }
                if(!carrierinfo.equals("")){
                    dt.setCarrierInfo(carrierinfo);
                }
                if(dt.updateDeliveryStatus()!=-1){
                    errorLabel22.setVisible(true);
                    errorLabel22.setText("Update Delivery Tracker!");
                    errorLabel22.setTextFill(Color.BLACK);
                    return;
                }
            }
        }
        errorLabel22.setVisible(true);
        errorLabel22.setText("Failed to Update!");
        return;
    }
}