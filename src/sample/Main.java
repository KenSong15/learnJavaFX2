package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;
    TableView<Product> productTable;

    TextField nameInput, priceInput, quantityInput;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        window = primaryStage;
        window.setTitle("table view and menu");

        //name column
        TableColumn<Product, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        //price column
        TableColumn<Product, String> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(70);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        //quantity column
        TableColumn<Product, String> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(50);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        productTable = new TableView<>();
        productTable.setItems(getProduct());
        productTable.getColumns().addAll(nameColumn,priceColumn,quantityColumn);
        productTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        //name input
        nameInput = new TextField();
        nameInput.setPromptText("product name");
        nameInput.setMinWidth(100);

        //price input
        priceInput = new TextField();
        priceInput.setPromptText("price");
        priceInput.setMaxWidth(70);

        //quantity input
        quantityInput = new TextField();
        quantityInput.setPromptText("quantity");
        quantityInput.setMaxWidth(70);

        //button
        Button addButton = new Button("add");
        addButton.setOnAction(e -> addButtonClicked());
        Button deleteButton = new Button("delete");
        deleteButton.setOnAction(e -> deleteButtonClicked());
        HBox buttonBox = new HBox(10);
        buttonBox.setPadding(new Insets(10,10,10,10));
        buttonBox.getChildren().addAll(nameInput,priceInput,quantityInput,addButton,deleteButton);


        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(productTable,buttonBox);

        window.setScene(new Scene(vBox, 600, 275));
        window.show();
    }



    //get all of products
    public ObservableList<Product> getProduct(){
        ObservableList<Product> products = FXCollections.observableArrayList();
        products.add(new Product("mac", 859.00, 20));
        products.add(new Product("iphone", 1099.00, 5));
        products.add(new Product("ipad", 499.00, 10));
        products.add(new Product("ipod", 299.00, 25));
        products.add(new Product("iwatch", 439.00, 15));
        return products;
    }


    private void addButtonClicked() {
        Product np = new Product();
        np.setName(nameInput.getText());
        np.setPrice(Double.parseDouble(priceInput.getText()));
        np.setPrice(Integer.parseInt(quantityInput.getText()));

        productTable.getItems().add(np);
        nameInput.clear();
        priceInput.clear();
        quantityInput.clear();
    }

    private void deleteButtonClicked() {
        ObservableList<Product> productSelected, allProducts;
        allProducts = productTable.getItems();
        productSelected = productTable.getSelectionModel().getSelectedItems();

        productSelected.forEach(allProducts::remove);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
