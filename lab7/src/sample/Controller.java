package sample;
import java.io.*;
import java.net.URL;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import myclasses.Customer;

public class Controller {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TableView<Customer> table;
    @FXML
    private TableColumn<Customer, Integer> id;
    @FXML
    private TableColumn<Customer, String> surname;
    @FXML
    private TableColumn<Customer, String> name;
    @FXML
    private TableColumn<Customer, String> patronymic;
    @FXML
    private TableColumn<Customer, String> adres;
    @FXML
    private TableColumn<Customer, Integer> numberCard;
    @FXML
    private TableColumn<Customer, Double> balance;
    @FXML
    private TableColumn<Customer, Integer> birthYear;
    @FXML
    private Button newCustomer;
    @FXML
    private Button saveFile;
    @FXML
    private Button openFile;
    @FXML
    private MenuItem menuA;
    @FXML
    private MenuItem menuB;
    @FXML
    private MenuItem menuC;
    @FXML
    private MenuItem menuD;
    @FXML
    private MenuItem menuE;
    @FXML
    private MenuItem menuF;
    @FXML
    private Button btnReset;

    private static ObservableList<Customer> customersObservableList = FXCollections.observableArrayList();
    private String searchQuerry;
    String path = "Customers.dat";

    static public void newCustomer(Customer cstmr){ customersObservableList.add(cstmr); }

    private void removeCustomer(){
        customersObservableList.remove(table.getFocusModel().getFocusedItem());
        table.setItems(customersObservableList);
    }

    private void searchWindow(int a){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Search");
        switch (a){
            case 1:
                dialog.setHeaderText("Enter name");
                break;
            case 2:
                dialog.setHeaderText("Enter range, splitting by \"-\"");
                break;
        }

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            searchQuerry = result.get();
        }
    }

    @FXML
    void onTableKeyClick(KeyEvent event) { if (event.getCode() == KeyCode.DELETE) removeCustomer(); }
    @FXML
    void onDelClicked(MouseEvent event) { removeCustomer(); }
    @FXML
    void resetClicked(MouseEvent event) { table.setItems(customersObservableList); }
    @FXML
    void taskAbtn(ActionEvent event) {
        searchWindow(1);
        if (searchQuerry == null)
            return;
        ObservableList<Customer> sortedByName = FXCollections.observableArrayList(CustomerLogic.taskA(customersObservableList, searchQuerry));
        if(sortedByName.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error!");
            alert.setHeaderText(null);
            alert.setContentText("There is no customers \"" + searchQuerry + "\"");
            alert.showAndWait();
        }
        else
            table.setItems(sortedByName);
    }
    @FXML
    void taskBbtn(ActionEvent event) {
        searchWindow(2);
        if (searchQuerry == null)
            return;
        ObservableList<Customer> sorted = FXCollections.observableArrayList(CustomerLogic.taskB(customersObservableList, searchQuerry));
        if(sorted.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error!");
            alert.setHeaderText(null);
            alert.setContentText("There is no customers in this range");
            alert.showAndWait();
        }
        else
            table.setItems(sorted);

    }
    @FXML
    void taskCbtn(ActionEvent event) {
        ObservableList<Customer> sortedObservable = FXCollections.observableArrayList(CustomerLogic.taskC(customersObservableList));
        if(sortedObservable.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error!");
            alert.setHeaderText(null);
            alert.setContentText("Debtors was not found");
            alert.showAndWait();
        }
        else
            table.setItems(sortedObservable);
    }
    @FXML
    void taskDbtn(ActionEvent event) {
        ObservableList<Customer> sortedObservable = FXCollections.observableArrayList(CustomerLogic.taskD(customersObservableList));
        table.setItems(sortedObservable);
    }
    @FXML
    void taskEbtn(ActionEvent event) {
        Set sorted = CustomerLogic.taskE(customersObservableList);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        if (sorted.toString() == "[]")
            alert.setContentText("List is empty!");
        else
            alert.setContentText(sorted.toString());
        alert.showAndWait();
    }
    @FXML
    void taskFbtn(ActionEvent event) {
        Map<Integer, Customer> sortedMap = CustomerLogic.taskF(customersObservableList);
        ObservableList<Customer> sorted = FXCollections.observableArrayList();
        for (Customer value : sortedMap.values()) {
            sorted.add(value);
        }
        table.setItems(sorted);
    }
    @FXML
    void newCustomerClicked(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../newCustomer/newCustomer.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("New customer");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error!");
            alert.setHeaderText(null);
            alert.setContentText("Error of loading FXML window!\n" + e.toString());
            alert.showAndWait();
        }
    }
    @FXML
    void saveFileClicked(MouseEvent event) {
        try{
            CustomerLogic.saveCustomers(path, customersObservableList);
        } catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Ошибка при сохранении\n" + e.toString());
            alert.showAndWait();
        }
    }
    @FXML
    void loadFileClicked(MouseEvent event) {
        if (customersObservableList.size() > 0)
                customersObservableList.remove(0, customersObservableList.size());
        try{
           CustomerLogic.loadCustomers(path, customersObservableList);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Ошибка при чтении файла\n" + e.toString());
            alert.showAndWait();
        }
    }
    @FXML
    void initialize() {
        id.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));
        surname.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));
        name.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
        patronymic.setCellValueFactory(new PropertyValueFactory<Customer, String>("patronymic"));
        adres.setCellValueFactory(new PropertyValueFactory<Customer, String>("adres"));
        numberCard.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("numberCard"));
        balance.setCellValueFactory(new PropertyValueFactory<Customer, Double>("balance"));
        birthYear.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("birthYear"));
        table.setItems(customersObservableList);
    }
}
