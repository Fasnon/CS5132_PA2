package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class StepTwoController {
    @FXML
    private Button backBtn;

    @FXML
    private Button proceedBtn;

    @FXML
    void formBack(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) backBtn.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/StepOne.fxml")));
        primaryStage.getScene().setRoot(newRoot);

    }

    @FXML
    void formProceed(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) backBtn.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/StepThree.fxml")));
        primaryStage.getScene().setRoot(newRoot);

    }

}
