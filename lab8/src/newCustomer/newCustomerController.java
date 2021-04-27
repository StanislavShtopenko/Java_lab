package newCustomer;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import myclasses.Customer;
import sample.Controller;

public class newCustomerController {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TextField lastName;
    @FXML
    private TextField firstName;
    @FXML
    private TextField patronymic;
    @FXML
    private TextField adres;
    @FXML
    private TextField numberCard;
    @FXML
    private TextField balance;
    @FXML
    private Button addBtn;
    @FXML
    private TextField yearField;
    @FXML
    void onClickAddBtn(MouseEvent event) {
        long numberCard = Long.parseLong(this.numberCard.getText());
        double balance = Double.parseDouble(this.balance.getText());
        Controller.newCustomer(
                new Customer(lastName.getText(), firstName.getText(), patronymic.getText(), Integer.parseInt(yearField.getText()), adres.getText(), numberCard, balance)
        );
    }
}
