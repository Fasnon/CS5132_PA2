package Controller;

import Model.RBTree;
import Model.Report;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

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
    private TextField titleTF;


    private String type;

    private HashMap<String, RBTree<Report>> treeMap;

    @FXML
    private ChoiceBox<Integer> severityCB;

    @FXML
    private ChoiceBox<Integer> urgencyCB;


    public void initData(String type, HashMap<String, RBTree<Report>> treeMap){
        this.type = type;
        this.treeMap = treeMap;

        severityCB.setItems(FXCollections.observableArrayList(1,2,3));
        severityCB.setValue(1);
        urgencyCB.setItems(FXCollections.observableArrayList(1,2,3));
        urgencyCB.setValue(1);
    }

    public void initDataFromThree(Report report, HashMap<String, RBTree<Report>> treeMap){
        this.treeMap = treeMap;

        titleTF.setText(report.getTitle());
        descTA.setText(report.getDescription());
        locTF.setText(report.getLocation());
        contactTF.setText(report.getContact());
        severityCB.setItems(FXCollections.observableArrayList(1,2,3));
        severityCB.setValue(report.getSeverity());
        urgencyCB.setItems(FXCollections.observableArrayList(1,2,3));
        urgencyCB.setValue(report.getUrgency());
    }

    @FXML
    void formBack(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) backBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/StepOne.fxml"));
        Parent newRoot = loader.load();
        StepOneController soc = loader.getController();
        soc.initData(treeMap);
        primaryStage.setUserData(null);
        primaryStage.getScene().setRoot(newRoot);


    }

    @FXML
    void formProceed(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) backBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/StepThree.fxml"));
        primaryStage.getScene().setRoot(loader.load());
        Report userReport = new Report(type, titleTF.getText(), descTA.getText(), contactTF.getText(), locTF.getText(), urgencyCB.getSelectionModel().getSelectedItem(), severityCB.getSelectionModel().getSelectedItem(), new Date());
        StepThreeController controller = loader.getController();
        controller.initData(userReport, treeMap);
        System.out.println(userReport);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        Stage primaryStage = (Stage) backBtn.getScene().getWindow();
    }
}
