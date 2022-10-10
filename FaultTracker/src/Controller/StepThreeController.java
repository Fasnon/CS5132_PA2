package Controller;

import Model.Report;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.Date;
import java.util.Objects;

public class StepThreeController {
    @FXML
    private Button backBtn;

    @FXML
    private Button proceedBtn;

    @FXML
    private Label contactLbl;

    @FXML
    private Label descLbl;

    @FXML
    private Label locationLbl;

    @FXML
    private Label severityLbl;

    @FXML
    private Label titleLbl;

    @FXML
    private Label typeLbl;

    @FXML
    private Label urgancyLbl;

    private Report report;

    @FXML
    void formBack(ActionEvent event) throws Exception {
        Stage primaryStage = (Stage) backBtn.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/StepTwo.fxml")));
        primaryStage.getScene().setRoot(newRoot);
    }

    @FXML
    void formProceed(ActionEvent event) throws Exception {
        Stage primaryStage = (Stage) backBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Confirmation.fxml"));

        primaryStage.getScene().setRoot(loader.load());
        ConfirmationController c = loader.getController();
        c.initData(new Date());
    }

    public void initData(Report report){
        this.report = report;
        titleLbl.setText(report.getTitle());
        descLbl.setText(report.getDescription());
        locationLbl.setText(report.getLocation());
        contactLbl.setText(report.getContact());
        urgancyLbl.setText(String.valueOf(report.getUrgency()));
        severityLbl.setText(String.valueOf(report.getSeverity()));
        typeLbl.setText(report.getType());
    }
}
