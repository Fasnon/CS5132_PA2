package Controller;

import Model.Converter;
import Model.RBTree;
import Model.Report;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
    private HashMap<String, RBTree<Report>> treeMap;

    @FXML
    void formBack(ActionEvent event) throws Exception {
        Stage primaryStage = (Stage) backBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/StepTwo.fxml"));
        Parent newRoot = loader.load();
        StepTwoController stc = loader.getController();
        stc.initDataFromThree(report, treeMap);
        primaryStage.getScene().setRoot(newRoot);
    }

    @FXML
    void formProceed(ActionEvent event) throws Exception {
        Stage primaryStage = (Stage) backBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Confirmation.fxml"));
        primaryStage.getScene().setRoot(loader.load());
        ConfirmationController c = loader.getController();
        c.initData(new Date(), treeMap);
        RBTree<Report> tree = treeMap.get(report.getType());
        tree.insert(report);
        treeMap.put(report.getType(), tree);
        System.out.println("TRee: " + tree);
        System.out.println(treeMap);


        ArrayList<String> types = new ArrayList<>();
        types.add("Compliments");
        types.add("Smoking");
        types.add("Rodents");
        types.add("Littering");
        types.add("Mosquitoes");
        types.add("Others");

        try{
            for (int a = 0; a< types.size(); a++){
                if (treeMap.get(types.get(a)) == null ) continue;
                if (treeMap.get(types.get(a)).getRoot() == null ) continue;
                FileWriter fw = new FileWriter("FaultTracker/Data/"+ types.get(a).toLowerCase() + "_reports.csv", false);
                fw.write(Converter.export_report_tree(treeMap.get(types.get(a))));
//                System.out.println
                fw.close();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void initData(Report report, HashMap<String, RBTree<Report>> treeMap){
        this.report = report;
        this.treeMap = treeMap;
        titleLbl.setText(report.getTitle());
        descLbl.setText(report.getDescription());
        locationLbl.setText(report.getLocation());
        contactLbl.setText(report.getContact());
        urgancyLbl.setText(String.valueOf(report.getUrgency()));
        severityLbl.setText(String.valueOf(report.getSeverity()));
        typeLbl.setText(report.getType());
    }
}
