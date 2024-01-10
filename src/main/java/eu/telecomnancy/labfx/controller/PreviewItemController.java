package eu.telecomnancy.labfx.controller;

import eu.telecomnancy.labfx.MaterialService.MaterialService;
import eu.telecomnancy.labfx.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PreviewItemController {

    private MaterialService item;
    private User user;

    @FXML
    private Label cost;
    @FXML
    private Label description;
    @FXML
    private ImageView imgItem;
    @FXML
    private ToggleButton like;
    @FXML
    private Label name;
    @FXML
    private Label note;
    @FXML
    private Label title;
    @FXML
    private Label ville;
    @FXML
    void addFavori(ActionEvent event) {

    }

    public void setItem(MaterialService item, User user){
        this.item = item;
        this.name.setText(item.getName());
        this.description.setText(item.getDescription());
        this.cost.setText(String.valueOf(item.getCost()));
        //this.note.setText(item.getNote());
        this.ville.setText(user.getCity());
        try {
            String filePath = PreviewItemController.class.getResource("/eu/telecomnancy/labfx/images/".concat(item.getImage())).getFile();
            Image image = new Image("../images/".concat(user.getImage()));
            this.imgItem.setImage(image);
        }catch (Exception e){
            System.out.println("Image not found");
        }
    }
}
