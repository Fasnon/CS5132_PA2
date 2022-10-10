package Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class ConfirmationController {

    @FXML
    private Button homeBtn;

    @FXML
    private Button quitBtn;

    @FXML
    private Label dateLbl;


    @FXML
    void backToHome(ActionEvent event)  throws Exception {
        Stage primaryStage = (Stage) homeBtn.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/Start.fxml")));
        primaryStage.getScene().setRoot(newRoot);
    }

    @FXML
    void quitApp(ActionEvent event)  throws Exception {
        Platform.exit();
    }

    void initData(Date d){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        dateLbl.setText("at " + sdf.format(d));
    }

}
