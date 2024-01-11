package eu.telecomnancy.labfx.controller;

import eu.telecomnancy.labfx.Redirection;
import eu.telecomnancy.labfx.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AccueilController {

    @FXML
    private Button addItem;
    @FXML
    private Button favoris;
    @FXML
    private TextField rechercheAcceuil;
    @FXML
    private Button buy;
    private User user;

    public AccueilController(User user) {
        this.user = user;
    }

    public void initalize(){

    }
    @FXML
    void goAddItem(ActionEvent event) {

    }

    @FXML
    void goFavoris(ActionEvent event) {

    }

    @FXML
    void searchFor(ActionEvent event) {

    }

    public void goBuy(ActionEvent event) {
        Redirection.pageAnnonce(user ,buy);
    }
}
