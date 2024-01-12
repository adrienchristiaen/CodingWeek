package eu.telecomnancy.labfx.Profil;

import eu.telecomnancy.labfx.user.User;
import eu.telecomnancy.labfx.utils.History;
import eu.telecomnancy.labfx.user.UserController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class HistoriqueRecentController {

    @FXML
    private VBox recentHistoryContainer; // La VBox pour afficher l'historique récent

    private User user;
    private UserController userController;

    public HistoriqueRecentController(User user) {
        this.user = user;
    }
    @FXML
    public void initialize() {
        // Récupérez l'historique récent de l'utilisateur
        ArrayList<History> recentHistory = userController.getInstance().getRecentHistory(user);

        // Affichez chaque élément de l'historique récent dans la VBox
        for (History historyItem : recentHistory) {
            System.out.println(1);
            String historyItemString = historyItem.getMaterialService().getName() +
                    " - : " + historyItem.getMaterialService().getType() +
                    " -  cout : " + historyItem.getPrice()+ "€"+
                    " -  date : " + historyItem.getTansactionTime().toString();
            Label historyLabel = new Label(historyItemString);
            recentHistoryContainer.getChildren().add(historyLabel);
        }
    }
}
