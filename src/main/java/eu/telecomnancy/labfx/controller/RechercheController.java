package eu.telecomnancy.labfx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RechercheController {

    @FXML
    private TextField barreRecherche;

    @FXML
protected void handleRechercherButtonClick(ActionEvent event) {
    try {
        // Charger le fichier FXML de la page Resultats.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/eu/telecomnancy/labfx/views/resultats.fxml"));
        Parent root = loader.load();

        // Récupérer le contrôleur associé à la page de résultats
        ResultatsController resultatsController = loader.getController();

        // Passer le terme de recherche au contrôleur de résultats
        resultatsController.initializeResults(barreRecherche.getText());

        // Créer la scène
        Scene scene = new Scene(root);

        // Obtenir le stage à partir de l'événement
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Mettre la nouvelle scène dans le stage
        stage.setScene(scene);

    } catch (IOException e) {
        e.printStackTrace();  // Gérer l'exception selon vos besoins
    }
}
}
