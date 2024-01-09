package eu.telecomnancy.labfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class InscriptionController {

    @FXML
    private TextField emailField;

    @FXML
    private TextField identifiantField;

    @FXML
    private PasswordField motDePasseField;

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField villeField;

    @FXML
    void inscriptionButtonHandler(ActionEvent event) {
        // On utilise les valeurs des champs pour effectuer une vérification
        String email = emailField.getText();
        String identifiant = identifiantField.getText();
        String motDePasse = motDePasseField.getText();
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String ville = villeField.getText();

        // Vérification basique de connexion
        if (email.isEmpty() || identifiant.isEmpty() || motDePasse.isEmpty() || nom.isEmpty() || prenom.isEmpty() || ville.isEmpty()) {
            // Connexion échouée
            System.out.println("Veuillez remplir tous les champs");
        } else {
            // Connexion réussie
            System.out.println("Inscription réussie");
        }

    }

}
