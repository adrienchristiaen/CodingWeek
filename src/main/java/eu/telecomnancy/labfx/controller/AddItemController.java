package eu.telecomnancy.labfx.controller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddItemController {
    ObservableList<String> itemType = FXCollections.observableArrayList("Service", "Material");

    @FXML
    private TextArea description;
    @FXML
    private DatePicker endDate;
    @FXML
    private TextField endHour;
    @FXML
    private TextField endMinute;
    @FXML
    private TextField price;
    @FXML
    private ChoiceBox<String> selectType;
    @FXML
    private DatePicker startDate;
    @FXML
    private TextField startHour;
    @FXML
    private TextField startMinute;
    @FXML
    private TextField title;
    @FXML
    private Button validate;

    @FXML
    public void initialize() {
        selectType.setValue("Choisir un type");
    	selectType.setItems(itemType);
    }

    @FXML
    void validateOffer(ActionEvent event) {

    }

}
