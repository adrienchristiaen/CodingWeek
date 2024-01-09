package eu.telecomnancy.labfx;


import eu.telecomnancy.labfx.user.User;
import eu.telecomnancy.labfx.user.UserController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("JavaFx Demo");
        UserController userController = UserController.getInstance();
        ArrayList<User> users = userController.getUsers();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/eu/telecomnancy/labfx/views/connexion.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 1920, 1080);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
