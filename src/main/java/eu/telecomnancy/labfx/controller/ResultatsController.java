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
                service=serviceController.getServiceById(i + 1);
                ListeIdScoreService[i][0] = service.getId();
                ListeIdScoreService[i][1] = 0;
            }
        }
        
        for (int i = 0; i < maxMaterialId; i++) {
            if (materialController.getMaterialById(i + 1) != null) {
                material=materialController.getMaterialById(i + 1);
                ListeIdScoreMateriel[i][0] = material.getId();
                ListeIdScoreMateriel[i][1] = 0;
            }
        }
        motEstDansClasseMateriel(searchTerm, ListeIdScoreMateriel);
        motEstDansClasseService(searchTerm, ListeIdScoreService);
      
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
    // mot est une chaine de caractere
    public void motEstDansClasseService(String mot, int[][] idScoreService) {
        String[] mots1 = mot.split(" ");
        for (int i = 0; i < idScoreService.length; i++) {
            int itemId = idScoreService[i][0];
            service = serviceController.getServiceById(itemId);
    
            if (service != null && service.getName() != null) {
                for (int j = 0; j < mots1.length; j++) {
                    if (estDedans(mots1[j], service.getName()) == 1) {
                        idScoreService[i][1] += 1;
                    }
                }
            }
            if (service != null && service.getDescription() != null) {
                for (int j = 0; j < mots1.length; j++) {
                    if (estDedans(mots1[j], service.getDescription()) == 1) {
                        idScoreService[i][1] += 1;
                    }
                }
            }
            if (service != null && service.getType()!=null){
                for (int j = 0; j < mots1.length; j++) {
                    if (estDedans(mots1[j], service.getType()) == 1) {
                        idScoreService[i][1] += 1;
                    }
                }
            }
        }
    }
    
    public void motEstDansClasseMateriel(String mot, int[][] idScoreMateriel) {
        String[] mots1 = mot.split(" ");
        for (int i = 0; i < idScoreMateriel.length; i++) {
            int itemId = idScoreMateriel[i][0];
            material = materialController.getMaterialById(itemId);
    
            if (material != null && material.getName() != null) {
                for (int j = 0; j < mots1.length; j++) {
                    if (estDedans(mots1[j], material.getName()) == 1) {
                        idScoreMateriel[i][1] += 1;
                    }
                }
            }
            if (material != null && material.getDescription() != null) {
                for (int j = 0; j < mots1.length; j++) {
                    if (estDedans(mots1[j], material.getDescription()) == 1) {
                        idScoreMateriel[i][1] += 1;
                    }
                }
            }
            if (material != null && material.getType()!=null){
                for (int j = 0; j < mots1.length; j++) {
                    if (estDedans(mots1[j], material.getType()) == 1) {
                        idScoreMateriel[i][1] += 1;
                    }
                }
            }
        }
    }
    // return 0 le mot n'est pas dans chainesDeCaracteres sinon return 1
    public int estDedans (String mot, String chainesDeCaracteres)  {
        String[] mots = chainesDeCaracteres.split(" ");
        for (int i = 0; i < mots.length; i++) {
            if (mots[i].equals(mot)) {
                return 1;
            }
        }
        return 0;
    }
}
