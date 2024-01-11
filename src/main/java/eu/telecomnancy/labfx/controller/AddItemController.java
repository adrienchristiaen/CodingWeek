package eu.telecomnancy.labfx.controller;

import eu.telecomnancy.labfx.user.User;
import java.io.File;
import java.time.LocalDate;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AddItemController {
    ObservableList<String> itemType = FXCollections.observableArrayList("Service", "Material");
    private User user;
    private String imagePath;
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
    @FXML
    private Label errorText ;

    public AddItemController(User user) {
        this.user = user;
    }
    @FXML
    public void initialize() {
        selectType.setValue("Choisir un type");
    	selectType.setItems(itemType);
    }

    @FXML
    void validateOffer(ActionEvent event) {
        String type = selectType.getValue();
        String title = this.title.getText();
        int price = Integer.parseInt(this.price.getText());
        String description = this.description.getText();
        String startDate = toLocalDateTime(this.startDate.getValue(), this.startHour.getText(), this.startMinute.getText());
        String endDate = toLocalDateTime(this.endDate.getValue(), this.endHour.getText(), this.endMinute.getText());
        String imagePath = this.imagePath;

        if(type == null || title == null || price == 0 ||startDate == null || endDate == null){
            errorText.setText("Veuillez remplir tous les champs");
        }
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
        this.imagePath = file.getAbsolutePath();

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

    private String toLocalDateTime(LocalDate date, String hour, String minute) {
        if(date == null){
            errorText.setText("La date ne peut pas être nulle");
            return null;
        }
        if(date.isBefore(LocalDate.now())){
            errorText.setText("La date ne peut pas être antérieure à la date actuelle");
            return null;
        }
        if(Integer.parseInt(hour)>23 || Integer.parseInt(hour)<0){
            errorText.setText("L'heure doit être comprise entre 0 et 23");
            return null;
        }
        if(Integer.parseInt(minute)>60 || Integer.parseInt(minute)<0){
            errorText.setText("Les minutes doivent être comprises entre 0 et 60");
            return null;
        }
        if(hour.length() == 1){
            hour = "0" + hour;
        }
        if(minute.length() == 1){
            minute = "0" + minute;
        }
        return date.getYear() + "-" + date.getMonthValue() + "-" + date.getDayOfMonth() + "T" + hour + ":" + minute + ":00";
    }

}
