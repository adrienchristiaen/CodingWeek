package eu.telecomnancy.labfx;

import eu.telecomnancy.labfx.MaterialService.Material;
import eu.telecomnancy.labfx.MaterialService.MaterialController;
import eu.telecomnancy.labfx.MaterialService.Service;
import eu.telecomnancy.labfx.MaterialService.ServiceController;
import eu.telecomnancy.labfx.Profil.InfoPersoController;
import eu.telecomnancy.labfx.controller.AccueilController;
import eu.telecomnancy.labfx.controller.NavBarController;
import eu.telecomnancy.labfx.controller.PreviewItemController;
import eu.telecomnancy.labfx.user.User;
import eu.telecomnancy.labfx.user.UserController;
import eu.telecomnancy.labfx.utils.DirectoryHandler;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

public class Redirection {

    public static void accueil(User user, Button actionButton){
        try {
            //On crée un contenair root qui sera une vbox
            VBox root = new VBox();
            root.setAlignment(javafx.geometry.Pos.TOP_CENTER);

            //On load la navbar et on la met en haut
            FXMLLoader top = new FXMLLoader(Redirection.class.getResource("/eu/telecomnancy/labfx/views/navbar.fxml"));
            top.setControllerFactory(cl -> {
                try {
                    Constructor<?> cons = cl.getConstructor(User.class);
                    if (cons != null) {
                        return cons.newInstance(user);
                    } else {
                        return cl.newInstance();
                    }
                } catch (Exception exc) {
                    throw new RuntimeException(exc);
                }
            });

            root.getChildren().add(top.load());

            //On load le centre et on le met dans une anchorpane
            FXMLLoader center = new FXMLLoader(Redirection.class.getResource("/eu/telecomnancy/labfx/views/accueil.fxml"));
            center.setControllerFactory(cl -> {
                try {
                    Constructor<?> cons = cl.getConstructor(User.class);
                    if (cons != null) {
                        return cons.newInstance(user);
                    } else {
                        return cl.newInstance();
                    }
                } catch (Exception exc) {
                    throw new RuntimeException(exc);
                }
            });
            root.getChildren().add(center.load());

            //Creation d'une gridpane pour contenir les previewItems
            GridPane previews = new GridPane();
            previews.setAlignment(javafx.geometry.Pos.TOP_CENTER);
            //On recupere les 3 derniers services et les 3 derniers materials
            System.out.println("Size of materials: " + MaterialController.getInstance().getMaterials().size());
            ArrayList<Material> materials = new ArrayList(MaterialController.getInstance().sortByUpdateAt().subList(0, 2));
            ArrayList<Service> services = new ArrayList(ServiceController.getInstance().sortByUpdateAt().subList(0, 2));
            System.out.println(services.size() + " " + materials.size());
            //On ajoute les previewsItem dans la gridpane

            for (int i = 0; i < materials.size(); i++) {
               FXMLLoader preview = new FXMLLoader(Redirection.class.getResource("/eu/telecomnancy/labfx/views/previewItem.fxml"));
               Parent previewItem = preview.load();
               PreviewItemController previewItemController = preview.getController();
               previewItemController.setItem(materials.get(i), user);
               previews.add(previewItem, 0, i );
            }
            for (int i = 0; i < services.size(); i++) {
                FXMLLoader preview = new FXMLLoader(Redirection.class.getResource("/eu/telecomnancy/labfx/views/previewItem.fxml"));
                Parent previewItem = preview.load();
                PreviewItemController previewItemController = preview.getController();
                previewItemController.setItem(services.get(i), user);
                previews.add(previewItem, 1, i );
            }
            root.getChildren().add(previews);



            Scene scene = new Scene(root, 1920, 1080);
            Stage primaryStage = (Stage) actionButton.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Erreur de redirection", "Une erreur s'est produite lors de la redirection vers l'onglet d'accueil.");
        }
    }

    public static void goProfil(User user, Button actionButton) {
        try {
            FXMLLoader loader = new FXMLLoader(Redirection.class.getResource("/eu/telecomnancy/labfx/views/Profil/profil.fxml"));
            loader.setControllerFactory(cl -> {
                try {
                    Constructor<?> cons = cl.getConstructor(User.class);
                    if (cons != null) {
                        return cons.newInstance(user);
                    } else {
                        return cl.newInstance();
                    } 
                } catch (Exception exc) {
                    throw new RuntimeException(exc);
                }
            });
            Parent profilRoot = loader.load();
            Scene profilScene = new Scene(profilRoot,1920,1080);
            Stage currentStage = (Stage) actionButton.getScene().getWindow();
            currentStage.setScene(profilScene);
            currentStage.setTitle("Profil");

        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Erreur de redirection", "Une erreur s'est produite lors de la redirection vers l'onglet Profil.");
        }
    }


    public static void inscription(ActionEvent event) {
        try {
            BorderPane root = new BorderPane();
            FXMLLoader loader = new FXMLLoader(Redirection.class.getResource("/eu/telecomnancy/labfx/views/inscription.fxml"));
            root.setCenter(loader.load());
            Scene scene = new Scene(root, 1920, 1080);
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
            Scene scene = new Scene(root, 1920, 1080);
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Erreur de redirection", "Une erreur s'est produite lors de la redirection vers l'onglet de connexion.");
        }
    }

    public static void goAddItem (User user, Button actionButton) {
        try {
            VBox addItemRoot = new VBox();
            addItemRoot.setAlignment(javafx.geometry.Pos.TOP_CENTER);

            //On load la navbar et on la met en haut
            FXMLLoader top = new FXMLLoader(Redirection.class.getResource("/eu/telecomnancy/labfx/views/navbar.fxml"));
            top.setControllerFactory(cl -> {
                try {
                    Constructor<?> cons = cl.getConstructor(User.class);
                    if (cons != null) {
                        return cons.newInstance(user);
                    } else {
                        return cl.newInstance();
                    }
                } catch (Exception exc) {
                    throw new RuntimeException(exc);
                }
            });
            addItemRoot.getChildren().add(top.load());

            //On load le formulaire
            FXMLLoader form = new FXMLLoader(Redirection.class.getResource("/eu/telecomnancy/labfx/views/AddItem.fxml"));
            form.setControllerFactory(cl -> {
                try {
                    Constructor<?> cons = cl.getConstructor(User.class);
                    if (cons != null) {
                        return cons.newInstance(user);
                    } else {
                        return cl.newInstance();
                    }
                } catch (Exception exc) {
                    throw new RuntimeException(exc);
                }
            });
            addItemRoot.getChildren().add(form.load());

            Scene scene = new Scene(addItemRoot, 1920, 1080);
            Stage primaryStage = (Stage) actionButton.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            showErrorDialog("Erreur de redirection", "Une erreur s'est produite lors de la redirection vers l'onglet d'ajout d'item.");
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
