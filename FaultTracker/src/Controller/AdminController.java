package Controller;

import Model.Converter;
import Model.RBTree;
import Model.Report;
import View.ToggleListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class AdminController implements Initializable {

    @FXML
    private GridPane grid;
    @FXML
    private Button backBtn;

    @FXML
    private TextArea dirTF;

    @FXML
    private Button importBtn;

    @FXML
    private Button confirmBtn;
    @FXML
    private ChoiceBox<String> typeCB;

    @FXML
    private Button exportBtn;

    @FXML
    private Button caseBtn;
    private ArrayList<ToggleTypeCardController> cards;
    private ArrayList<String> selectedTypes;
    private ToggleListener tl;
    @FXML
    private TableColumn<Report, Integer> severityTableColumn;

    @FXML
    private TableColumn<Report, Date> timeTableColumn;

    @FXML
    private TableColumn<Report, String> titleTableColumn;


    @FXML
    private TableColumn<Report, String> typeTableColumn;

    @FXML
    private TableColumn<Report, Integer> urgencyTableColumn;

    private boolean exportS = false;
    private boolean importS = false;
    private File file;
    @FXML
    private TableView<Report> reportTable;


    private HashMap<String, RBTree<Report>> treeMap;



    @FXML
    public void formProceed(ActionEvent e) throws Exception{
        Stage primaryStage = (Stage) backBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/CaseView.fxml"));
        Parent newRoot = loader.load();
        CaseViewController cvc = loader.getController();
        cvc.initData(treeMap, reportTable.getSelectionModel().getSelectedItem());
        primaryStage.getScene().setRoot(newRoot);
    }
    @FXML
    public void formBack(ActionEvent e) throws Exception{
        Stage primaryStage = (Stage) backBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Start.fxml"));
        Parent newRoot = loader.load();
        StartController sc = loader.getController();
        sc.initData(treeMap);
        primaryStage.getScene().setRoot(newRoot);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ArrayList<String> types = new ArrayList<>();
            cards = new ArrayList<ToggleTypeCardController>();
            selectedTypes = new ArrayList<String>();
            types.add("Compliments");
            types.add("Smoking");
            types.add("Rodents");
            types.add("Littering");
            types.add("Mosquitoes");
            types.add("Others");
            tl = new ToggleListener() {
                @Override
                public void onClickListener(String type) {

                    if (selectedTypes.contains(type)){
                        selectedTypes.remove(type);

                    }
                    else{
                        selectedTypes.add(type);
                    }

                    RBTree<Report> collated = new RBTree<Report>();

                    for (int t = 0; t < selectedTypes.size() ; t++){
                        collated = collated.merge(treeMap.get(selectedTypes.get(t)));
                    }
                    ArrayList<Report> sorted = collated.inOrder();


                    ObservableList<Report> s = FXCollections.observableArrayList();
                    Collections.reverse(sorted);
                    s.addAll(sorted);


                    reportTable.setItems(null);
                    reportTable.setItems(s);


                    typeTableColumn.setCellValueFactory(
                        new PropertyValueFactory<Report, String>("type")
                    );
                    titleTableColumn.setCellValueFactory(
                            new PropertyValueFactory<Report, String>("title")
                    );
                    urgencyTableColumn.setCellValueFactory(
                            new PropertyValueFactory<Report, Integer>("urgency")
                    );
                    severityTableColumn.setCellValueFactory(
                            new PropertyValueFactory<Report, Integer>("severity")
                    );
                    timeTableColumn.setCellValueFactory(
                            new PropertyValueFactory<Report, Date>("date")
                    );
                    System.out.println(selectedTypes);
                }
            };

            int column = 0;
            int row = 0;

            for (int i = 0; i < types.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/View/ToggleTypeCard.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                ToggleTypeCardController toggleTypeCardController = fxmlLoader.getController();
                toggleTypeCardController.setData(types.get(i), tl);
                cards.add(toggleTypeCardController);

                if(column == 2){
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row);
//                cards.add(anchorPane);

            }
            grid.setSnapToPixel(false);

        }
        catch (IOException ioe){

        }
    }
    public void initData(HashMap<String, RBTree<Report>> treeM){
//        this.type = type;
        typeCB.setItems(FXCollections.observableArrayList("Compliments", "Littering", "Mosquitoes", "Others", "Rodents", "Smoking"));
        File s = new File("FaultTracker/resources/images/import.png");
        ImageView v = new ImageView(new Image(s.toURI().toString()));
        v.setFitHeight(20);
        v.setFitWidth(20);
        importBtn.setGraphic(v);
        File t = new File("FaultTracker/resources/images/export.png");
        ImageView w = new ImageView(new Image(t.toURI().toString()));
        w.setFitHeight(20);
        w.setFitWidth(20);
        exportBtn.setGraphic(w);
        t = new File("FaultTracker/resources/images/confirm.png");
        ImageView o = new ImageView(new Image(t.toURI().toString()));
        o.setFitHeight(20);
        o.setFitWidth(20);
        confirmBtn.setGraphic(o);
        confirmBtn.setVisible(false);
        typeCB.setVisible(false);
        dirTF.setVisible(false);
        DirectoryChooser dc = new DirectoryChooser();
        treeMap = treeM;
    }


    @FXML
    void importAction(ActionEvent event) {
        confirmBtn.setVisible(true);
        typeCB.setVisible(true);
        dirTF.setVisible(true);
        dirTF.setText("");
        importS = true;
        exportS = false;
        file = null;

    }
    @FXML
    void exportAction(ActionEvent event) {
        confirmBtn.setVisible(true);
        typeCB.setVisible(true);
        dirTF.setVisible(true);
        dirTF.setText("");
        importS = false;
        exportS = true;
        file = null;

    }

    @FXML
    void directoryAction(MouseEvent event) {
        if (importS){
            FileChooser fc = new FileChooser();
            file = fc.showOpenDialog((Stage) dirTF.getScene().getWindow());
            dirTF.setText(file.toString());
        }
        if (exportS){
            DirectoryChooser dc = new DirectoryChooser();
            file = dc.showDialog((Stage) dirTF.getScene().getWindow());
            dirTF.setText(file.toString());
        }

    }

    @FXML
    void confirmAction(ActionEvent e){
        if (file == null || typeCB.getValue() == null){

            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Select a report type");
            a.setContentText("Select an area for the file and the its type");
            a.showAndWait();
        }

        else {
            try {
                if (importS) {
                    String s = typeCB.getValue();
                    Scanner scn = new Scanner(file);
                    scn.useDelimiter("\n");
                    RBTree<Report> r = Converter.import_report_tree(scn);
                    RBTree<Report> ro = treeMap.get(s);
                    ro = ro.merge(r);
                    File f = new File("FaultTracker/Data/" + s.toLowerCase() + "_reports.csv");
                    FileWriter fw = new FileWriter(f);
                    fw.write(Converter.export_report_tree(ro));
                    fw.close();
                    treeMap.put(s, ro);
                    typeCB.setValue(null);
                    dirTF.setText("");
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setHeaderText("File successfully merged");
                    a.showAndWait();
                }
                if (exportS) {
                    String s = typeCB.getValue();
                    File writeTo = new File(file.toString() + "/" + s.toLowerCase() + "_reports.csv");
                    FileWriter fw = new FileWriter(writeTo);
                    fw.write(Converter.export_report_tree(treeMap.get(s)));
                    fw.close();
                    typeCB.setValue(null);
                    dirTF.setText("");
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setHeaderText("File successfully exported");
                    a.showAndWait();
                }
            }
            catch (IOException ee){
                ee.printStackTrace();
            }

            reportTable.refresh();
        }
    }


}
