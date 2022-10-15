package Controller;

import Model.Converter;
import Model.RBTree;
import Model.Report;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

public class CaseViewController {

    @FXML
    private Button backBtn;

    @FXML
    private Label contactLbl;

    @FXML
    private Button deleteBtn;

    @FXML
    private Label descLbl;

    @FXML
    private AnchorPane header;

    @FXML
    private Label locationLbl;

    @FXML
    private Label severityLbl;

    @FXML
    private Label titleLbl;

    @FXML
    private Label typeLbl;

    @FXML
    private Label urgencyLbl;

    private Report selected;
    private HashMap<String, RBTree<Report>> treeMap;

    public void initData(HashMap<String, RBTree<Report>> treeMap, Report selected){
        this.treeMap = treeMap;
        this.selected = selected;
        titleLbl.setText(selected.getTitle());
        descLbl.setText(selected.getDescription());
        locationLbl.setText(selected.getLocation());
        contactLbl.setText(selected.getContact());
        urgencyLbl.setText(String.valueOf(selected.getUrgency()));
        severityLbl.setText(String.valueOf(selected.getSeverity()));
        typeLbl.setText(selected.getType());
    }
    @FXML
    void backToAdmin(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) backBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Admin.fxml"));
        Parent newRoot = loader.load();
        AdminController ac = loader.getController();
        ac.initData(treeMap);
        primaryStage.getScene().setRoot(newRoot);
    }

    @FXML
    void deleteReport(ActionEvent event) throws  IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initOwner((Stage) backBtn.getScene().getWindow());
        alert.initModality(Modality.WINDOW_MODAL);
        alert.setTitle("Delete Case?");
        alert.setHeaderText("Delete Case of " + titleLbl.getText() + "?");
        alert.setContentText("Are you sure you want to delete this? This cannot be undone");

        Optional<ButtonType> result = alert.showAndWait();
        if(!result.isPresent() || result.get() != ButtonType.OK) {
        }
        else {
            RBTree<Report> tree = treeMap.get(selected.getType());
            tree.delete(selected);
            treeMap.put(selected.getType(), tree);
            String s = selected.getType();
            File writeTo = new File("FaultTracker/Data/" + s.toLowerCase() + "_reports.csv");
            FileWriter fw = new FileWriter(writeTo);
            fw.write(Converter.export_report_tree(tree));
            fw.close();
            Stage primaryStage = (Stage) backBtn.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Admin.fxml"));
            Parent newRoot = loader.load();
            AdminController ac = loader.getController();
            ac.initData(treeMap);
            primaryStage.getScene().setRoot(newRoot);
        }
    }

}
