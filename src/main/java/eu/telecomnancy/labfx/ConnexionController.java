package eu.telecomnancy.labfx;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class ConnexionController {

    @FXML
    private TextField IDField;

    @FXML
    private TextField MdpField;

    @FXML
    private Button connexionButton;

    @FXML
    private Text errorMessage;

    @FXML
    void connexionButtonHandler(ActionEvent event) {
        // On utilise les valeurs des champs IDField et MdpField pour effectuer une vérification
        String identifiant = IDField.getText();
        String motDePasse = MdpField.getText();

        // Vérification basique de connexion
        if ("votreIdentifiantValide".equals(identifiant) && "votreMotDePasseValide".equals(motDePasse)) {
            // Connexion réussie
            errorMessage.setText("Connexion réussie");
            errorMessage.setStyle("-fx-fill: green;"); // Couleur verte pour le succès
        } else {
            // Connexion échouée
            errorMessage.setText("Identifiant ou mot de passe incorrect");
            errorMessage.setStyle("-fx-fill: red;"); // Couleur rouge pour l'échec
        }
    }

    @FXML
    void redirectionInscription(ActionEvent event) {
        try {
            // Chargez l'onglet d'inscription depuis le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("inscription.fxml"));
            AnchorPane page = loader.load();

            // Récupérez le contrôleur associé à l'onglet d'inscription
            InscriptionController InscriptionController = loader.getController();

            // Créez une nouvelle scène avec l'onglet d'inscription
            Scene scene = new Scene(page);

            // Obtenez la fenêtre principale à partir de n'importe quel contrôleur
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Définissez la nouvelle scène dans la fenêtre principale
            primaryStage.setScene(scene);

            // Affichez la nouvelle scène
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Gérez l'exception (par exemple, en affichant un message d'erreur à l'utilisateur)
            showErrorDialog("Erreur de redirection", "Une erreur s'est produite lors de la redirection vers l'onglet d'inscription.");
        }
    }


private void showErrorDialog(String title, String message) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}

}