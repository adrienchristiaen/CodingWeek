package eu.telecomnancy.labfx;

import eu.telecomnancy.labfx.controller.NavBarController;
import eu.telecomnancy.labfx.user.User;
import eu.telecomnancy.labfx.user.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Redirection {

    public static void acceuil(User user, Button actionButton){
        try {
            //On crée un contenair root qui sera une vbox
            VBox root = new VBox();
            root.setAlignment(javafx.geometry.Pos.TOP_CENTER);

            //On load la navbar et on la met en haut
            FXMLLoader top = new FXMLLoader(Redirection.class.getResource("/eu/telecomnancy/labfx/views/navbar.fxml"));
            Parent navBar = top.load();
            NavBarController navBarController = top.getController();
            navBarController.setUser(user);
            navBarController.setFlorains();

            root.getChildren().add(navBar);

            //On load le centre et on le met dans une anchorpane
            FXMLLoader center = new FXMLLoader(Redirection.class.getResource("/eu/telecomnancy/labfx/views/accueil.fxml"));
            root.getChildren().add(center.load());

            Scene scene = new Scene(root);
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
            BorderPane root = new BorderPane();
            FXMLLoader loader = new FXMLLoader(Redirection.class.getResource("/eu/telecomnancy/labfx/views/connexion.fxml"));
            root.setCenter(loader.load());
            Scene scene = new Scene(root);
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
