package Controller;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.io.File;

public class CaseTypeCardController {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ImageView typeImg;
    @FXML
    private Label typeLbl;

    private String type;

    private String colour;

    private boolean state = false ;

    public void setData(String type){
        this.type = type;
        typeLbl.setText(type);
        switch (type){
            case "Compliments": {
                File s = new File("FaultTracker/resources/images/type_compliments.png");
                typeImg.setImage(new Image(s.toURI().toString()));;
                anchorPane.setStyle("-fx-background-color: \"CECECE\"; -fx-background-radius: 20;");
                colour = "81C2D7";
                break;
            }
            case "Littering": {
                File s = new File("FaultTracker/resources/images/type_littering.png");
                typeImg.setImage(new Image(s.toURI().toString()));
                anchorPane.setStyle("-fx-background-color: \"CECECE\"; -fx-background-radius: 20;");
                colour = "81D089";
                break;
            }
            case "Mosquitoes": {
                File s = new File("FaultTracker/resources/images/type_mosquito.png");
                typeImg.setImage(new Image(s.toURI().toString()));
                anchorPane.setStyle("-fx-background-color: \"CECECE\"; -fx-background-radius: 20;");
                colour = "CDAF99";
                break;
            }
            case "Others": {
                File s = new File("FaultTracker/resources/images/type_others.png");
                typeImg.setImage(new Image(s.toURI().toString()));
                anchorPane.setStyle("-fx-background-color: \"CECECE\"; -fx-background-radius: 20;");
                colour = "E08686";
                break;
            }
            case "Rodents": {
                File s = new File("FaultTracker/resources/images/type_rodents.png");
                typeImg.setImage(new Image(s.toURI().toString()));
                colour = "D8A2D9";
                anchorPane.setStyle("-fx-background-color: \"CECECE\"; -fx-background-radius: 20;");
                break;
            }
            case "Smoking": {
                File s = new File("FaultTracker/resources/images/type_smoking.png");
                typeImg.setImage(new Image(s.toURI().toString()));
                colour = "676767";
                anchorPane.setStyle("-fx-background-color: \"CECECE\"; -fx-background-radius: 20;");
                break;
            }
        }
    }

}
