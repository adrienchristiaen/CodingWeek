package eu.telecomnancy.labfx;

import eu.telecomnancy.labfx.user.User;
import eu.telecomnancy.labfx.user.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Redirection {

    public static void acceuil(User user, Button actionButton){
        try {
            FXMLLoader loader = new FXMLLoader(Redirection.class.getResource("/eu/telecomnancy/labfx/views/accueil.fxml"));
            AnchorPane page = loader.load();

            // Récupérez le contrôleur associé à l'onglet d'accueil
            AccueilController accueilController = loader.getController();

            // Passez l'utilisateur connecté au contrôleur d'accueil
            accueilController.initData(user);

            Scene scene = new Scene(page);
            Stage primaryStage = (Stage) actionButton.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Erreur de redirection", "Une erreur s'est produite lors de la redirection vers l'onglet d'accueil.");
        }
    }

    public static void inscription(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(Redirection.class.getResource("/eu/telecomnancy/labfx/views/inscription.fxml"));
            AnchorPane page = loader.load();

            Scene scene = new Scene(page);
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Erreur de redirection", "Une erreur s'est produite lors de la redirection vers l'onglet d'inscription.");
        }
    }

    public static void connexion(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(Redirection.class.getResource("/eu/telecomnancy/labfx/views/connexion.fxml"));
            AnchorPane page = loader.load();

            Scene scene = new Scene(page);
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Erreur de redirection", "Une erreur s'est produite lors de la redirection vers l'onglet de connexion.");
        }
    }


    private static void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
