package eu.telecomnancy.labfx.Profil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;

public class ModeEditionController {

    @FXML
    private PasswordField nouveauMotDePasseField;

    @FXML
    private PasswordField confirmerMotDePasseField;

    @FXML
    private ImageView nouvellePhotoProfil;

    // Méthode associée à l'action "Changer Mot de Passe"
    @FXML
    private void changerMotDePasse(ActionEvent event) {
        // Logique pour changer le mot de passe
    }

    // Méthode associée à l'action "Charger Nouvelle Photo"
    @FXML
    private void chargerNouvellePhoto(ActionEvent event) {
        // Logique pour charger une nouvelle photo
    }
}