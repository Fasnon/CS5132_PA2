package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.Objects;


public class AdminController {

    @FXML
    private Button backBtn;

    @FXML
    private Button caseBtn;


    @FXML
    public void formProceed(ActionEvent e) throws Exception{
        Stage primaryStage = (Stage) backBtn.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/CaseView.fxml")));
        primaryStage.getScene().setRoot(newRoot);
    }
    @FXML
    public void formBack(ActionEvent e) throws Exception{
        Stage primaryStage = (Stage) backBtn.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/Start.fxml")));
        primaryStage.getScene().setRoot(newRoot);
    }
}
