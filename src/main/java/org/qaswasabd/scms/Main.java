package org.qaswasabd.scms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;


public class Main extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        Image icon = new Image(getClass().getResourceAsStream("icon.png"));

        // Set the loaded image as the taskbar icon
        primaryStage.getIcons().add(icon);
        loadHelloView();
    }

    public static void main(String[] args) {
        launch();
    }

    // Method to load hello-view.fxml
    private void loadHelloView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("Welcome");
        primaryStage.setScene(scene);
        primaryStage.show();


        // Get the controller instance and pass the stage to it
        Controller controller = fxmlLoader.getController();
        controller.setStageAndMainApp(primaryStage, this); // Pass the stage and main application instance
    }

    // Method to load login-view.fxml
    public void loadLoginView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Get the controller instance and pass the stage to it
        Controller controller = fxmlLoader.getController();
        controller.setStageAndMainApp(primaryStage, this); // Pass the stage and main application instance
    }

    // Method to load changepass-view.fxml
    public void loadchangePasswordView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("changepass-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("Change Password");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Get the controller instance and pass the stage to it
        Controller controller = fxmlLoader.getController();
        controller.setStageAndMainApp(primaryStage, this); // Pass the stage and main application instance
    }

    // Method to load forgotpass-view.fxml
    public void loadforgotPasswordView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("forgotpass-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("Forgot Password");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Get the controller instance and pass the stage to it
        Controller controller = fxmlLoader.getController();
        controller.setStageAndMainApp(primaryStage, this); // Pass the stage and main application instance
    }

    // Method to load home-view.fxml
    public void loadHomeView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("Home");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Get the controller instance and pass the stage to it
        Controller controller = fxmlLoader.getController();
        controller.setStageAndMainApp(primaryStage, this); // Pass the stage and main application instance
    }

    // Method to load productmgmt-view.fxml
    public void loadProductMgmtView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("productmgmt-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("Product Management");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Get the controller instance and pass the stage to it
        Controller controller = fxmlLoader.getController();
        controller.setStageAndMainApp(primaryStage, this); // Pass the stage and main application instance
    }

    // Method to load inventorymgmt-view.fxml
    public void loadInventoryMgmtView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("inventorymgmt-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("Inventory Management");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Get the controller instance and pass the stage to it
        Controller controller = fxmlLoader.getController();
        controller.setStageAndMainApp(primaryStage, this); // Pass the stage and main application instance
    }

    // Method to load warehousemgmt-view.fxml
    public void loadWarehouseMgmtView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("warehousemgmt-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("Warehouse Management");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Get the controller instance and pass the stage to it
        Controller controller = fxmlLoader.getController();
        controller.setStageAndMainApp(primaryStage, this); // Pass the stage and main application instance
    }

    // Method to load addproduct-view.fxml
    public void loadaddProductView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("addproduct-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("Product Management");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Get the controller instance and pass the stage to it
        Controller controller = fxmlLoader.getController();
        controller.setStageAndMainApp(primaryStage, this); // Pass the stage and main application instance
    }

    // Method to load updateproduct-view.fxml
    public void loadupdateProductView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("updateproduct-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("Product Management");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Get the controller instance and pass the stage to it
        Controller controller = fxmlLoader.getController();
        controller.setStageAndMainApp(primaryStage, this); // Pass the stage and main application instance
    }

    // Method to load deleteproduct-view.fxml
    public void loaddeleteProductView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("deleteproduct-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("Product Management");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Get the controller instance and pass the stage to it
        Controller controller = fxmlLoader.getController();
        controller.setStageAndMainApp(primaryStage, this); // Pass the stage and main application instance
    }

    // Method to load viewproducts-view.fxml
    public void loadviewProductView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("viewproducts-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("Product Management");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Get the controller instance and pass the stage to it
        Controller controller = fxmlLoader.getController();
        controller.setStageAndMainApp(primaryStage, this); // Pass the stage and main application instance
    }

    // Method to load invmgmt-view.fxml
    public void loadinvManagementView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("invmgmt-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("Inventory Management");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Get the controller instance and pass the stage to it
        Controller controller = fxmlLoader.getController();
        controller.setStageAndMainApp(primaryStage, this); // Pass the stage and main application instance
    }

    // Method to load addinventory-view.fxml
    public void loadaddInventoryView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("addinventory-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("Inventory Management");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Get the controller instance and pass the stage to it
        Controller controller = fxmlLoader.getController();
        controller.setStageAndMainApp(primaryStage, this); // Pass the stage and main application instance
    }

    // Method to load updateinventory-view.fxml
    public void loadupdateInventoryView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("updateinventory-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("Inventory Management");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Get the controller instance and pass the stage to it
        Controller controller = fxmlLoader.getController();
        controller.setStageAndMainApp(primaryStage, this); // Pass the stage and main application instance
    }

    // Method to load deleteinventory-view.fxml
    public void loaddeleteInventoryView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("deleteinventory-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("Inventory Management");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Get the controller instance and pass the stage to it
        Controller controller = fxmlLoader.getController();
        controller.setStageAndMainApp(primaryStage, this); // Pass the stage and main application instance
    }

    // Method to load viewinventories-view.fxml
    public void loadviewInventoryView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("viewinventories-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("Inventory Management");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Get the controller instance and pass the stage to it
        Controller controller = fxmlLoader.getController();
        controller.setStageAndMainApp(primaryStage, this); // Pass the stage and main application instance
    }

    // Method to load addwarehouse-view.fxml
    public void loadaddWarehouseView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("addwarehouse-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("Warehouse Management");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Get the controller instance and pass the stage to it
        Controller controller = fxmlLoader.getController();
        controller.setStageAndMainApp(primaryStage, this); // Pass the stage and main application instance
    }

    // Method to load updatewarehouse-view.fxml
    public void loadupdateWarehouseView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("updatewarehouse-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("Warehouse Management");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Get the controller instance and pass the stage to it
        Controller controller = fxmlLoader.getController();
        controller.setStageAndMainApp(primaryStage, this); // Pass the stage and main application instance
    }

    // Method to load deletewarehouse-view.fxml
    public void loaddeleteWarehouseView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("deleteWarehouse-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("Warehouse Management");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Get the controller instance and pass the stage to it
        Controller controller = fxmlLoader.getController();
        controller.setStageAndMainApp(primaryStage, this); // Pass the stage and main application instance
    }

    // Method to load viewwarehouses-view.fxml
    public void loadviewWarehouseView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("viewwarehouses-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("Warehouse Management");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Get the controller instance and pass the stage to it
        Controller controller = fxmlLoader.getController();
        controller.setStageAndMainApp(primaryStage, this); // Pass the stage and main application instance
    }

    // Method to load suppliermgmt-view.fxml
    public void loadSRMView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("suppliermgmt-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("SRM");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Get the controller instance and pass the stage to it
        Controller controller = fxmlLoader.getController();
        controller.setStageAndMainApp(primaryStage, this); // Pass the stage and main application instance
    }

    // Method to load addsupplier-view.fxml
    public void loadaddSupplierView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("addsupplier-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("SRM");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Get the controller instance and pass the stage to it
        Controller controller = fxmlLoader.getController();
        controller.setStageAndMainApp(primaryStage, this); // Pass the stage and main application instance
    }

    // Method to load updatesupplier-view.fxml
    public void loadupdateSupplierView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("updatesupplier-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("SRM");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Get the controller instance and pass the stage to it
        Controller controller = fxmlLoader.getController();
        controller.setStageAndMainApp(primaryStage, this); // Pass the stage and main application instance
    }

    // Method to load deletesupplier-view.fxml
    public void loaddeleteSupplierView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("deletesupplier-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("SRM");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Get the controller instance and pass the stage to it
        Controller controller = fxmlLoader.getController();
        controller.setStageAndMainApp(primaryStage, this); // Pass the stage and main application instance
    }

    // Method to load viewsuppliers-view.fxml
    public void loadviewSupplierView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("viewsuppliers-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("SRM");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Get the controller instance and pass the stage to it
        Controller controller = fxmlLoader.getController();
        controller.setStageAndMainApp(primaryStage, this); // Pass the stage and main application instance
    }

    // Method to load ordermanagement-view.fxml
    public void loadorderMgmtView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ordermanagement-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("Order Management");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Get the controller instance and pass the stage to it
        Controller controller = fxmlLoader.getController();
        controller.setStageAndMainApp(primaryStage, this); // Pass the stage and main application instance
    }

    // Method to load demandforecast-view.fxml
    public void loadDemandForecastView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("demandforecast-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("Order Management");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Get the controller instance and pass the stage to it
        Controller controller = fxmlLoader.getController();
        controller.setStageAndMainApp(primaryStage, this); // Pass the stage and main application instance
    }

    // Method to load ordermgmt-view.fxml
    public void loadOrderManagementView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ordermgmt-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("Order Management");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Get the controller instance and pass the stage to it
        Controller controller = fxmlLoader.getController();
        controller.setStageAndMainApp(primaryStage, this); // Pass the stage and main application instance
    }

    // Method to load addorder-view.fxml
    public void loadaddOrderView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("addorder-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("Order Management");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Get the controller instance and pass the stage to it
        Controller controller = fxmlLoader.getController();
        controller.setStageAndMainApp(primaryStage, this); // Pass the stage and main application instance
    }

    // Method to load updateorder-view.fxml
    public void loadupdateorderview() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("updateorder-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("Order Management");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Get the controller instance and pass the stage to it
        Controller controller = fxmlLoader.getController();
        controller.setStageAndMainApp(primaryStage, this); // Pass the stage and main application instance
    }

    // Method to load deleteorder-view.fxml
    public void loaddeleteorderView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("deleteorder-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("Order Management");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Get the controller instance and pass the stage to it
        Controller controller = fxmlLoader.getController();
        controller.setStageAndMainApp(primaryStage, this); // Pass the stage and main application instance
    }

    // Method to load vieworders-view.fxml
    public void loadviewOrdersView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("vieworders.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("Order Management");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Get the controller instance and pass the stage to it
        Controller controller = fxmlLoader.getController();
        controller.setStageAndMainApp(primaryStage, this); // Pass the stage and main application instance
    }

    // Method to load payment-view.fxml
    public void loadPaymentManagementView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("payment-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("Order Management");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Get the controller instance and pass the stage to it
        Controller controller = fxmlLoader.getController();
        controller.setStageAndMainApp(primaryStage, this); // Pass the stage and main application instance
    }

    // Method to load viewpayments-view.fxml
    public void loadviewPaymentsView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("viewpayments-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("Order Management");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Get the controller instance and pass the stage to it
        Controller controller = fxmlLoader.getController();
        controller.setStageAndMainApp(primaryStage, this); // Pass the stage and main application instance
    }

    // Method to load makepayment-view.fxml
    public void loadmakePaymentView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("makepayment-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("Order Management");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Get the controller instance and pass the stage to it
        Controller controller = fxmlLoader.getController();
        controller.setStageAndMainApp(primaryStage, this); // Pass the stage and main application instance
    }

    // Method to load customermgmt-view.fxml
    public void loadCustomerManagementView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("customermgmt-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("Order Management");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Get the controller instance and pass the stage to it
        Controller controller = fxmlLoader.getController();
        controller.setStageAndMainApp(primaryStage, this); // Pass the stage and main application instance
    }

    // Method to load addcustomer-view.fxml
    public void loadaddCustomerView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("addcustomer-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("Order Management");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Get the controller instance and pass the stage to it
        Controller controller = fxmlLoader.getController();
        controller.setStageAndMainApp(primaryStage, this); // Pass the stage and main application instance
    }

    // Method to load updatecustomer-view.fxml
    public void loadupdateCustomerView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("updatecustomer-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("Order Management");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Get the controller instance and pass the stage to it
        Controller controller = fxmlLoader.getController();
        controller.setStageAndMainApp(primaryStage, this); // Pass the stage and main application instance
    }

    // Method to load viewcustomers-view.fxml
    public void loadviewCustomersView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("viewcustomers-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("Order Management");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Get the controller instance and pass the stage to it
        Controller controller = fxmlLoader.getController();
        controller.setStageAndMainApp(primaryStage, this); // Pass the stage and main application instance
    }

    // Method to load deliverytrackingmgmt-view.fxml
    public void loadDeliveryTrackingMgmtView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("deliverytrackingmgmt-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("Order Management");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Get the controller instance and pass the stage to it
        Controller controller = fxmlLoader.getController();
        controller.setStageAndMainApp(primaryStage, this); // Pass the stage and main application instance
    }

    // Method to load updatedelivery-view.fxml
    public void loadupdateDeliveryTracking() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("updatedelivery-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("Order Management");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Get the controller instance and pass the stage to it
        Controller controller = fxmlLoader.getController();
        controller.setStageAndMainApp(primaryStage, this); // Pass the stage and main application instance
    }

    // Method to load viewdeliveries-view.fxml
    public void loadviewDeliveries() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("viewdeliveries-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("Order Management");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Get the controller instance and pass the stage to it
        Controller controller = fxmlLoader.getController();
        controller.setStageAndMainApp(primaryStage, this); // Pass the stage and main application instance
    }
}

