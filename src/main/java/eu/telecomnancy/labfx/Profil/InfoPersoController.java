package eu.telecomnancy.labfx.Profil;

import eu.telecomnancy.labfx.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class InfoPersoController {

    @FXML
    private TextField identifiantField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField lastnameField;

    @FXML
    private TextField firstnameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField cityField;

    private User user;


    public InfoPersoController(User user) {
        this.user = user;
        //afficherInfoUtilisateur();
    }

    public void initialize() {
       // Afficher les informations de l'utilisateur dans les champs appropriés
        identifiantField.setText(user.getIdentifiant());
        passwordField.setText(user.getPassword());
        lastnameField.setText(user.getLastName());
        firstnameField.setText(user.getFirstName());
        emailField.setText(user.getEmail());
        cityField.setText(user.getCity());
    }

    @FXML
    void sauvegarderInfoPerso(ActionEvent event) {
        // Récupérez les valeurs des champs et sauvegardez-les où vous en avez besoin
        String identifiant = identifiantField.getText();
        String password = passwordField.getText();
        String lastname = lastnameField.getText();
        String firstname = firstnameField.getText();
        String email = emailField.getText();
        String city = cityField.getText();

        // Ajoutez le code nécessaire pour sauvegarder ces informations
        System.out.println("Informations personnelles sauvegardées : " + identifiant + ", " + password + ", " + lastname + ", " + firstname + ", " + email + ", " + city);
    }
}
