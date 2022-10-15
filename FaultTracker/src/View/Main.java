package View;

import Controller.StartController;
import Model.Converter;
import Model.RBTree;
import Model.Report;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class Main extends Application {
    public HashMap<String, RBTree<Report>> treeMap;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage){
        try {
            Stage mainStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("Start.fxml")));

            Parent root = fxmlLoader.load();

            mainStage.setTitle("Fault");
//            mainStage.getIcons().add(new Image("file:" +  System.getProperty("user.dir") + "/resources/GroSureMini.png"));
            mainStage.setScene(new Scene(root, 1080, 680));
            mainStage.show();
            mainStage.setResizable(false);


            ArrayList<String> types = new ArrayList<>();
            types.add("Compliments");
            types.add("Smoking");
            types.add("Rodents");
            types.add("Littering");
            types.add("Mosquitoes");
            types.add("Others");

            treeMap = new HashMap<String, RBTree<Report>>();

            for (int i = 0; i < types.size(); i++){
                File f = new File("FaultTracker/Data/"+ types.get(i).toLowerCase()+"_reports.csv");
                Scanner scn = new Scanner(f);
                scn.useDelimiter("\n");
                RBTree<Report> rbTree = Converter.import_report_tree(scn);
                treeMap.put(types.get(i), rbTree);

            }
            StartController sc = fxmlLoader.getController();
            sc.initData(treeMap);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}