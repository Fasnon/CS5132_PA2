package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Objects;

public class StartController {
    @FXML
    private AnchorPane header;
    @FXML
    private Button formBtn;
    @FXML
    private Button signinBtn;
    DropShadow dropShadow = new DropShadow(5, Color.BLACK);


    public void initialize(){
        header.setEffect(dropShadow);

    }
    @FXML
    public void adminLogIn(ActionEvent e) throws Exception{
        Stage primaryStage = (Stage) signinBtn.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("StepOne.fxml"));
        primaryStage.getScene().setRoot(newRoot);
    }
    @FXML
    public void formProceed(ActionEvent e) throws Exception{
        Stage primaryStage = (Stage) signinBtn.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/StepOne.fxml")));
        primaryStage.getScene().setRoot(newRoot);
    }

}
