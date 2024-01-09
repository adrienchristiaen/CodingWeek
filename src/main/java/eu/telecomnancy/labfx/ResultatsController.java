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
        // separerMotRecherche(searchTerm);
        int[][] idScoreService = {
            {1, 75},
            {2, 92},
            {3, 80},
            {4, 65},
            {5, 88}
        };
        int[][] idScoreMateriel = {
            {1, 75},
            {2, 92},
            {3, 80},
            {4, 65},
            {5, 88}
        };
        trierScore(idScoreMateriel);
        trierScore(idScoreService);
        afficherResultats(idScoreService, idScoreMateriel);


    }

    public void separerMotRecherche(String searchTerm) {
        String[] mots = searchTerm.split(" ");
    
        for (int i = 0; i < mots.length; i++) {
            mots[i] = mots[i].toLowerCase();
        }
    }

    // creer une fonction qui prends en entré un tableau de couple (id, score) et un mot de recherche et qui renvoie un tableau de couple (id, score). 
    // le score augmente de 1 si le mot de recherche est dans la classe du service ou materiel

    public void calculScore(String[] mots, int[][] idScoreService, int[][] idScoreMateriel) {
        for (int i = 0; i < mots.length; i++) {
            for (int j = 0; j < idScoreService.length; j++) {
                if (compareMotIdService(mots[i], idScoreService[j][0]) == 1) {
                    idScoreService[j][1] += 1;
                }
            }
            for (int j = 0; j < idScoreMateriel.length; j++) {
                if (compareMotIdBien(mots[i], idScoreMateriel[j][0]) == 1) {
                    idScoreMateriel[j][1] += 1;
                }
            }
        }
    }

    // à faire quand j'aurais Killian
    public int compareMotIdService(String mot, int id) {
        return 0;
    }
    // à faire quand j'aurais Killian
    public int compareMotIdBien(String mot, int id) {
        return 0;
    }

    public void trierScore(int[][] idScore) {
        int[][] idScoreTrie = new int[idScore.length][2];
        int max = 0;
        int indice = 0;
        for (int i = 0; i < idScore.length; i++) {
            for (int j = 0; j < idScore.length; j++) {
                if (idScore[j][1] > max) {
                    max = idScore[j][1];
                    indice = j;
                }
            }
            idScoreTrie[i][0] = idScore[indice][0];
            idScoreTrie[i][1] = idScore[indice][1];
            idScore[indice][1] = 0;
            max = 0;
        }
        for (int i = 0; i < idScore.length; i++) {
            idScore[i][0] = idScoreTrie[i][0];
            idScore[i][1] = idScoreTrie[i][1];
        }
    }

    // creer une fonction qui prends en entré un tableau de couple (id, score) et qui affiche par ordre décroissant les id des services et materiels qui ont le plus de score et on retir ceux qui ont 0 de score
    public void afficherResultats(int[][] idScoreService, int[][] idScoreMateriel) {
        for (int i = 0; i < idScoreService.length; i++) {
            if (idScoreService[i][1] != 0) {
                resultsListView.getItems().add("Service : " + idScoreService[i][0]);
            }
        }
        for (int i = 0; i < idScoreMateriel.length; i++) {
            if (idScoreMateriel[i][1] != 0) {
                resultsListView.getItems().add("Materiel : " + idScoreMateriel[i][0]);
            }
        }
    }

    
}
