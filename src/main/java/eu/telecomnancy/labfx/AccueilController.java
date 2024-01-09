package eu.telecomnancy.labfx;

import eu.telecomnancy.labfx.user.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AccueilController {

    @FXML
    private Label welcomeLabel;

    public void initData(User user) {
        // Initialise le Label avec le nom de l'utilisateur
        welcomeLabel.setText("Bienvenue " + user.getFirstName());
    }
}
