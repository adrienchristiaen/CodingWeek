package eu.telecomnancy.labfx.controller;

import eu.telecomnancy.labfx.MaterialService.MaterialService;
import eu.telecomnancy.labfx.user.User;
import eu.telecomnancy.labfx.user.UserController;
import eu.telecomnancy.labfx.utils.DirectoryHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.time.format.DateTimeFormatter;

public class PageAnnonceController {

    private User user;
    private MaterialService item;

    @FXML
    private Label city;
    @FXML
    private Label date;
    @FXML
    private Text description;
    @FXML
    private ImageView imageAnnonce;
    @FXML
    private Label name;
    @FXML
    private Label note;
    @FXML
    private Label price;
    @FXML
    private Button reserve;
    @FXML
    private Label titre;

    public void setItem(User user, MaterialService item){
        this.user = this.user;
        this.item = item;
        User owner = UserController.getInstance().getUserById(item.getOwner());
        titre.setText(item.getName());
        this.name.setText(owner.getFirstName().concat(" ").concat(owner.getLastName()));
        city.setText(owner.getCity());
        description.setText(item.getDescription());
        note.setText(String.valueOf(owner.getAverageNote()));
        price.setText(String.valueOf(item.getCost()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.date.setText(item.getStartTime().format(formatter)+" à "+item.getEndTime().format(formatter));
        try {
            String filePath = DirectoryHandler.getPathResources("/images/" + item.getImage());
            Image image = new Image(filePath);
            this.imageAnnonce.setImage(image);
        }catch (Exception e){
            System.out.println("Image not found");
        }
    }

    @FXML
    void initialize() {

    }

    @FXML
    void reserve(ActionEvent event) {
        //Redirection.popUpReserve(user, item);
    }

}
