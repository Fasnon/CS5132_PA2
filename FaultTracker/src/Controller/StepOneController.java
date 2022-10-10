package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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

    @FXML
    void formBack(ActionEvent event) throws Exception {
        Stage primaryStage = (Stage) backBtn.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/Start.fxml")));
        primaryStage.getScene().setRoot(newRoot);
    }

    @FXML
    void formProceed(ActionEvent event) throws Exception{
        Stage primaryStage = (Stage) backBtn.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/StepTwo.fxml")));
        primaryStage.getScene().setRoot(newRoot);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ArrayList<String> types = new ArrayList<>();
            types.add("Compliments");
            types.add("Smoking");
            types.add("Rodents");
            types.add("Littering");
            types.add("Mosquitoes");
            types.add("Others");

            int column = 0;
            int row = 0;

            for (int i = 0; i < types.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/View/CaseTypeCard.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                CaseTypeCardController caseTypeCardController = fxmlLoader.getController();
                caseTypeCardController.setData(types.get(i));

                if(column == 3){
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row);

            }
            grid.setSnapToPixel(false);

        }
        catch (IOException ioe){

        }
    }

}
