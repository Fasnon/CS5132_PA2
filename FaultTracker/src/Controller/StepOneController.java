package Controller;

import View.StepOneListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class StepOneController implements Initializable {


    @FXML
    private GridPane grid;

    @FXML
    private Button backBtn;

    @FXML
    private Button proceedBtn;

    private String selectedType = "nil";

    private StepOneListener stepOneListener;
    private ArrayList<CaseTypeCardController> cards;

    @FXML
    void formBack(ActionEvent event) throws Exception {
        Stage primaryStage = (Stage) backBtn.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/Start.fxml")));
        primaryStage.getScene().setRoot(newRoot);
    }

    @FXML
    void formProceed(ActionEvent event) throws Exception{
        if (selectedType != "nil") {
            Stage primaryStage = (Stage) backBtn.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/StepTwo.fxml"));
//            Parent newRoot = FXMLLoader.load(Objects.requireNonNull());
            primaryStage.getScene().setRoot(loader.load());
            StepTwoController controller = loader.getController();
            controller.initData(selectedType);
//            primaryStage.setUserData(selectedType);
        }
        else{
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Select a report type");
            a.setContentText("Please select a report type by clicking on it");
            a.showAndWait();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ArrayList<String> types = new ArrayList<>();
            cards = new ArrayList<CaseTypeCardController>();
            types.add("Compliments");
            types.add("Smoking");
            types.add("Rodents");
            types.add("Littering");
            types.add("Mosquitoes");
            types.add("Others");
            stepOneListener = new StepOneListener() {
                @Override
                public void onClickListener(String type) {
                    selectedType = type;
                    for (int i = 0; i < cards.size(); i++){
                        if (!cards.get(i).getType().equals(selectedType)){
                            cards.get(i).deselect();
                        }
                    }
                    System.out.println(type);
                }
            };

            int column = 0;
            int row = 0;

            for (int i = 0; i < types.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/View/CaseTypeCard.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                CaseTypeCardController caseTypeCardController = fxmlLoader.getController();
                caseTypeCardController.setData(types.get(i), stepOneListener);
                cards.add(caseTypeCardController);

                if(column == 3){
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

}
