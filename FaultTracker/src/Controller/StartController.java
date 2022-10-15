package Controller;

import Model.RBTree;
import Model.Report;
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

import java.util.HashMap;
import java.util.Objects;

public class StartController {
    @FXML
    private AnchorPane header;
    @FXML
    private Button formBtn;
    @FXML
    private Button signinBtn;
    DropShadow dropShadow = new DropShadow(5, Color.BLACK);
    private HashMap<String, RBTree<Report>> treeMap;


    public void initialize(){
        header.setEffect(dropShadow);
    }
    public void initData(HashMap<String, RBTree<Report>> treeMap){
        this.treeMap = treeMap;
    }


    @FXML
    public void adminLogIn(ActionEvent e) throws Exception{
        Stage primaryStage = (Stage) signinBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Admin.fxml"));
        primaryStage.getScene().setRoot(loader.load());
        AdminController controller = loader.getController();
        controller.initData(treeMap);
    }
    @FXML
    public void formProceed(ActionEvent e) throws Exception{
        Stage primaryStage = (Stage) signinBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/StepOne.fxml"));
        Parent newRoot = loader.load();
        StepOneController soc = loader.getController();
        soc.initData(treeMap);
        primaryStage.getScene().setRoot(newRoot);
    }

}
