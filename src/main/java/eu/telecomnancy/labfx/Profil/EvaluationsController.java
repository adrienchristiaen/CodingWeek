package eu.telecomnancy.labfx.Profil;

import eu.telecomnancy.labfx.user.User;
import eu.telecomnancy.labfx.utils.Evaluation;
import eu.telecomnancy.labfx.utils.EvaluationController;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class EvaluationsController {

    @FXML
    private Label moyenneEvaluationsLabel;

    @FXML
    private ListView<Evaluation> evaluationsListView;

    private User user;
    private ArrayList<Evaluation> receivedEvaluations;

    public EvaluationsController(User user) {
        this.user = user;
        this.receivedEvaluations = new ArrayList<>();
    }

    public void initialize() {
        // Récupérez les évaluations récentes de l'utilisateur
        ArrayList<Evaluation> recentEvaluations = EvaluationController.getInstance().getEvaluations();

        // Affichez chaque évaluation récente dans la ListView
        evaluationsListView.getItems().setAll(recentEvaluations);

        // Vous pouvez personnaliser la façon dont les évaluations sont affichées en utilisant une cell factory, si nécessaire
        evaluationsListView.setCellFactory(param -> new javafx.scene.control.ListCell<Evaluation>() {
            @Override
            protected void updateItem(Evaluation item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText("Note: " + item.getRating() + ", Commentaire: " + item.getComment());
                }
            }
        });
    }
}
