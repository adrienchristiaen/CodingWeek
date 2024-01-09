package eu.telecomnancy.labfx.Profil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class InfoPersoController {

    @FXML
    private TextField identifiantField;

    @FXML
    private PasswordField motDePasseField;

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField villeField;

    @FXML
    void sauvegarderInfoPerso(ActionEvent event) {
        // Récupérez les valeurs des champs et sauvegardez-les où vous en avez besoin
        String identifiant = identifiantField.getText();
        String motDePasse = motDePasseField.getText();
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String email = emailField.getText();
        String ville = villeField.getText();

        // Ajoutez le code nécessaire pour sauvegarder ces informations
        System.out.println("Informations personnelles sauvegardées : " + identifiant + ", " + motDePasse + ", " + nom + ", " + prenom + ", " + email + ", " + ville);
    }
}
