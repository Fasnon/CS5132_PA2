package Controller;

import Model.Report;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class StepTwoController implements Initializable {
    @FXML
    private Button backBtn;

    @FXML
    private TextField contactTF;

    @FXML
    private TextArea descTA;

    @FXML
    private TextField locTF;

    @FXML
    private Button proceedBtn;

    @FXML
    private TextField severityTF;

    @FXML
    private TextField titleTF;

    @FXML
    private TextField urgencyTF;

    private String type;

    public void initData(String type){
        this.type = type;
    }

    @FXML
    void formBack(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) backBtn.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/StepOne.fxml")));
        primaryStage.setUserData(null);
        primaryStage.getScene().setRoot(newRoot);


    }

    @FXML
    void formProceed(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) backBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/StepThree.fxml"));
        primaryStage.getScene().setRoot(loader.load());
//        String type = (String) primaryStage.getUserData();
        Report userReport = new Report(type, titleTF.getText(), descTA.getText(), contactTF.getText(), locTF.getText(), Integer.parseInt(urgencyTF.getText()), Integer.parseInt(severityTF.getText()), new Date());
//        primaryStage.setUserData(userReport);
        StepThreeController controller = loader.getController();
        controller.initData(userReport);
        System.out.println(userReport);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        Stage primaryStage = (Stage) backBtn.getScene().getWindow();
    }
}
