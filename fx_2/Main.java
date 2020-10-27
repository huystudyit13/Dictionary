import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        FileInputStream fis = new FileInputStream("C:\\Users\\DELL\\IdeaProjects\\test\\src\\sample.fxml");
        AnchorPane root = fxmlLoader.load(fis);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dictionary");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
