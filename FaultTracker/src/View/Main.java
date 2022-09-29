package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Stage mainStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("AboutTab.fxml"));
            mainStage.setTitle("Fault");
//            mainStage.getIcons().add(new Image("file:" +  System.getProperty("user.dir") + "/resources/GroSureMini.png"));
            mainStage.setScene(new Scene(root, 335, 600));
            mainStage.show();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}