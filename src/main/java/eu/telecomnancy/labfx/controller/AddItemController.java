package eu.telecomnancy.labfx.controller;

import eu.telecomnancy.labfx.user.User;
import java.io.File;
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
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AddItemController {
    ObservableList<String> itemType = FXCollections.observableArrayList("Service", "Material");
    private User user;
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
    private Button addImage;
    @FXML
    private ImageView currentImage;
    @FXML
    private Button validate;

    public AddItemController(User user) {
        this.user = user;
    }
    @FXML
    public void initialize() {
        selectType.setValue("Choisir un type");
    	selectType.setItems(itemType);
    }

    @FXML
    void addImage(ActionEvent event) {
        Stage stage = (Stage) addImage.getScene().getWindow();
        final FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            currentImage.setImage(new javafx.scene.image.Image(file.toURI().toString()));
        }
    }

    private static void configureFileChooser(final FileChooser fileChooser) {
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.jpg", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

    }
    @FXML
    void validateOffer(ActionEvent event) {

    }

}
