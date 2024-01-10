package eu.telecomnancy.labfx.controller;

import eu.telecomnancy.labfx.MaterialService.MaterialController;
import eu.telecomnancy.labfx.MaterialService.Material;
import eu.telecomnancy.labfx.MaterialService.ServiceController;
import eu.telecomnancy.labfx.MaterialService.Service;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;



public class ResultatsController {

    @FXML
    private ComboBox<String> displayModeComboBox;

    @FXML
    private TilePane resultsTilePane;

    @FXML
    private Label searchLabel;

    private MaterialController materialController = MaterialController.getInstance();
    private ServiceController serviceController = ServiceController.getInstance();
    private Material material ;
    private Service service ;
    
    
    public void initializeResults(String searchTerm) {
        searchLabel.setText("Voici les résultats de la recherche pour : " + searchTerm);
        // separerMotRecherche(searchTerm);
      
        int maxServiceId = serviceController.getMaxId();
        int maxMaterialId = materialController.getMaxId();
        
 
        int[][] ListeIdScoreService = new int[maxServiceId][2];
        int[][] ListeIdScoreMateriel = new int[maxMaterialId][2];
        
      
        for (int i = 0; i < maxServiceId; i++) {
            if (serviceController.getServiceById(i + 1) != null) {
                ListeIdScoreService[i][0] = i + 1;
                ListeIdScoreService[i][1] = 1;
            }
        }
        
        for (int i = 0; i < maxMaterialId; i++) {
            if (materialController.getMaterialById(i + 1) != null) {
                ListeIdScoreMateriel[i][0] = i + 1;
                ListeIdScoreMateriel[i][1] = 1;
            }
        }
       
       trierScore(ListeIdScoreService);
        trierScore(ListeIdScoreMateriel);
        afficherResultats(ListeIdScoreService, ListeIdScoreMateriel);



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
                // Add a custom component with an image, name, and description to the TilePane
                resultsTilePane.getChildren().add(createCustomComponentService(idScoreService[i][0], "Service"));
                // Add space between components
                resultsTilePane.getChildren().add(new Region()); // Spacer
            }
        }
        for (int i = 0; i < idScoreMateriel.length; i++) {
            if (idScoreMateriel[i][1] != 0) {
                // Add a custom component with an image, name, and description to the TilePane
                resultsTilePane.getChildren().add(createCustomComponentMateriel(idScoreMateriel[i][0], "Materiel"));
                // Add space between components
                resultsTilePane.getChildren().add(new Region()); // Spacer
            }
        }
    }
    

    private VBox createCustomComponentMateriel(int itemId, String itemType) {
        material = materialController.getMaterialById(itemId);
        // Create a custom component with an image, name, and description
        ImageView imageView = new ImageView(/* Set your image here */);
        Label nameLabel = new Label(itemType + " : " + material.getName());
        Label descriptionLabel = new Label(material.getDescription());

        // Arrange the elements in a VBox
        VBox customComponent = new VBox(imageView, nameLabel, descriptionLabel);
        customComponent.setSpacing(5); // Adjust spacing as needed

        return customComponent;
    }
     private VBox createCustomComponentService(int itemId, String itemType) {
        service = serviceController.getServiceById(itemId);
        // Create a custom component with an image, name, and description
        ImageView imageView = new ImageView(/* Set your image here */);
        Label nameLabel = new Label(itemType + " : " + service.getName());
        Label descriptionLabel = new Label(service.getDescription());

        // Arrange the elements in a VBox
        VBox customComponent = new VBox(imageView, nameLabel, descriptionLabel);
        customComponent.setSpacing(5); // Adjust spacing as needed

        return customComponent;
    }

    
    public int max(int a, int b) {
        return a > b ? a : b;
    }
    
    
}
