package eu.telecomnancy.pcd2k17;

import eu.telecomnancy.pcd2k17.model.ContextAPI;
import eu.telecomnancy.pcd2k17.model.DataBaseInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiClient;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Permissions;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.User;

import java.sql.Connection;
import java.util.List;

public class ConnectionController {

    final static Logger log = LogManager.getLogger(ConnectionController.class);

    @FXML
    private Button connectionButton;
    @FXML
    private TextField tokenField;
    @FXML
    private TextField urlField;
    @FXML
    private Text errorMessage;

    private GitLabApi gitLabApi;

    public void initialize(){
        tokenField.focusedProperty().addListener((ov, oldV, newV)-> {
            if (newV){ // si on entre une nouvelle valeur dans les champs, on efface le message d'erreur etc
                tokenField.setStyle("");
                urlField.setStyle("");
                errorMessage.setText("");
            }
        });
    }


    @FXML
    public void connectionButtonHandler(ActionEvent evt){
        if(evt.getSource().equals(connectionButton)){
            errorMessage.setText("Tentative de connexion");
            errorMessage.setStyle("-fx-fill:green");
            if (!("".equals(urlField.getText()))&&!("".equals(tokenField.getText()))) {
                this.gitLabApi = new GitLabApi(urlField.getText(), tokenField.getText());
                ContextAPI.setGitLabApi(this.gitLabApi);
                log.debug("Tentative : url : " + urlField.getText() + " ; " + "Token : " + tokenField.getText());
                try {
                    gitLabApi.getUserApi().getCurrentUser();
                    ContextAPI.getDbInterface().retrieveDataFromGitlab(gitLabApi);
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("projectsList.fxml"));
                    Parent root = loader.load();
                    Stage st = new Stage();
                    st.setScene(new Scene(root, 912, 643));
                    st.show();
                    Stage scene = (Stage) connectionButton.getScene().getWindow();
                    scene.close();
                }
                catch (GitLabApiException e)
                {
                    tokenField.setStyle("-fx-border-color: red;");
                    urlField.setStyle("-fx-border-color: red;");
                    errorMessage.setStyle("-fx-fill:red");
                    errorMessage.setText("Erreur de connexion, verifier l'url et le token de connexion");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            } else {
                if ("".equals(urlField.getText())){
                    urlField.setStyle("-fx-border-color: red;");
                }
                if ("".equals(tokenField.getText())){
                    tokenField.setStyle("-fx-border-color: red;");
                }
                errorMessage.setText("Merci d'indiquer un URL et un token de connexion");
            }
        }
    }

    public void testConnection(String url, String token){
        this.gitLabApi = new GitLabApi(url, token);
        ContextAPI.setGitLabApi(this.gitLabApi);
        try {
            System.out.println(gitLabApi.getUserApi().getCurrentUser().getName());
            System.out.println(gitLabApi.getProjectApi());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public GitLabApi getGitLabApi() {
        return this.gitLabApi;
    }

    public TextField getTokenField() {
        return this.tokenField;
    }

    public TextField getUrlField() {
        return this.urlField;
    }

    public Text getErrorMessage() {
        return this.errorMessage;
    }

    public void setGitLabApi(GitLabApi gitLabApi) {
        this.gitLabApi = gitLabApi;
    }

    public void setTokenField(TextField token) {
        this.tokenField = token;
    }

    public void setUrlField(TextField url) {
        this.urlField = url;
    }

}
