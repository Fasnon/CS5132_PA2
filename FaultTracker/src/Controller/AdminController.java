package Controller;

import View.StepOneListener;
import View.ToggleListener;
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
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;


public class AdminController implements Initializable {

    @FXML
    private GridPane grid;
    @FXML
    private Button backBtn;

    @FXML
    private Button caseBtn;
    private ArrayList<ToggleTypeCardController> cards;
    private ArrayList<String> selectedTypes;
    private ToggleListener tl;


    @FXML
    public void formProceed(ActionEvent e) throws Exception{
        Stage primaryStage = (Stage) backBtn.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/CaseView.fxml")));
        primaryStage.getScene().setRoot(newRoot);
    }
    @FXML
    public void formBack(ActionEvent e) throws Exception{
        Stage primaryStage = (Stage) backBtn.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/Start.fxml")));
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
}
