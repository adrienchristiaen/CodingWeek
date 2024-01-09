package eu.telecomnancy.labfx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ResultatsController {

    @FXML
    private ListView<String> resultsListView;

    @FXML
    private Label searchLabel;

    public void initializeResults(String searchTerm) {
        searchLabel.setText("Voici les résultats de la recherche pour : " + searchTerm);

        // Ajoutez ici la logique pour récupérer les résultats en fonction du terme de recherche (searchTerm)
        // Par exemple, vous pourriez avoir une liste d'objets (biens ou services) que vous affichez dans la ListView.

        // Pour l'exemple, ajoutons quelques résultats fictifs
        resultsListView.getItems().addAll("Résultat 1", "Résultat 2", "Résultat 3");
    }
}
