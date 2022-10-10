package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage){
        try {
            Stage mainStage = new Stage();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Start.fxml")));

            mainStage.setTitle("Fault");
//            mainStage.getIcons().add(new Image("file:" +  System.getProperty("user.dir") + "/resources/GroSureMini.png"));
            mainStage.setScene(new Scene(root, 1080, 680));
            mainStage.show();
            mainStage.setResizable(false);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}