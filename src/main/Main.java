package main;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
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
        //Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        window = primaryStage;
        window.setTitle("table view and menu");

        //menu file:
        Menu fileMenu = new Menu("_File");
            //menu item
        MenuItem miNew = new MenuItem("new...");
        miNew.setOnAction(e->System.out.println("new clicked"));

        MenuItem miOpen = new MenuItem("open...");
        MenuItem miSave = new MenuItem("save...");
        MenuItem miSet = new MenuItem("set...");
        MenuItem miExit = new MenuItem("exit...");

        fileMenu.getItems().addAll(miNew,miOpen,miSave,new SeparatorMenuItem(),miSet,miExit);


        //menu edit:
        Menu editMenu = new Menu("_Edit");
            //menu item
        MenuItem miredo = new MenuItem("redo");
        miredo.setOnAction(e->System.out.println("redo clicked"));

        MenuItem miundo = new MenuItem("undo");
        miundo.setOnAction(e->System.out.println("undo clicked"));

        MenuItem micopy = new MenuItem("copy");
        miredo.setOnAction(e->System.out.println("copy clicked"));

        MenuItem mipaste = new MenuItem("paste");
        miundo.setOnAction(e->System.out.println("paste clicked"));
        mipaste.setDisable(true);

        editMenu.getItems().addAll(miundo,miredo,new SeparatorMenuItem(),micopy,mipaste);


        //menu check:
        Menu checkMenu = new Menu("_Check");
        //menu item
        CheckMenuItem micheck1 = new CheckMenuItem("check1");
        micheck1.setOnAction(e->{
            if(micheck1.isSelected()){
                System.out.println("1 check");
            } else {
                System.out.println("1 uncheck");
            }
        });
        micheck1.setSelected(true);

        CheckMenuItem micheck2 = new CheckMenuItem("check2");
        micheck2.setOnAction(e->{
            if(micheck2.isSelected()){
                System.out.println("2 check");
            } else {
                System.out.println("2 uncheck");
            }
        });

        checkMenu.getItems().addAll(micheck1,micheck2);


        //menu radio:
        Menu radioMenu = new Menu("_Radio");
        ToggleGroup radioToggle = new ToggleGroup();
        //menu item
        RadioMenuItem miradio1 = new RadioMenuItem("radio1");
        miradio1.setOnAction(e->System.out.println("radio 1 selected"));

        RadioMenuItem miradio2 = new RadioMenuItem("radio2");
        miradio2.setOnAction(e->System.out.println("radio 2 selected"));

        RadioMenuItem miradio3 = new RadioMenuItem("radio3");
        miradio3.setOnAction(e->System.out.println("radio 3 selected"));

        radioToggle.getToggles().addAll(miradio1,miradio2,miradio3);

        radioMenu.getItems().addAll(miradio1,miradio2,miradio3);

        //menubar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu,editMenu,checkMenu,radioMenu);

        //table:
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


        //style part
        VBox styleVBox = new VBox(10);
        Label slabel1 = new Label("style 1 label");
        slabel1.setStyle("-fx-text-fill: blue"); // inline style

        Button blueButton = new Button("blueButton");
        blueButton.getStyleClass().add("button-blue");

        Label slabel2 = new Label("style 2 label");
        slabel2.setId("bold-label");

        styleVBox.getChildren().addAll(slabel1, blueButton, slabel2);


        //binding example on input
        VBox bindBox = new VBox(10);
        Label bindttile = new Label("show info responsively while typing");

        TextField typeField = new TextField();
        typeField.setMaxWidth(200);
        typeField.setPromptText("type here...");
        Label typemonitor = new Label("(type monitor)");
        typemonitor.textProperty().bind(typeField.textProperty());

        bindBox.getChildren().addAll(bindttile,typeField,typemonitor);

        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(menuBar,productTable,buttonBox,new Separator() ,
                styleVBox, new Separator(),bindBox);



        Scene mainScene = new Scene(vBox, 800, 600);
        mainScene.getStylesheets().add(getClass().getResource("style.css").toString());

        window.setScene(mainScene);
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

        //pure binding example
        IntegerProperty x = new SimpleIntegerProperty(3);
        IntegerProperty y = new SimpleIntegerProperty();

        y.bind(x.multiply(10));
        System.out.println("x: "+ x.getValue());
        System.out.println("y: "+ y.getValue() + "\n");

        x.setValue(6);
        System.out.println("x: "+ x.getValue());
        System.out.println("y: "+ y.getValue() + "\n");

        launch(args);
    }
}
