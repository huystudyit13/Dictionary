import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.*;
import java.util.TreeMap;
import java.util.Map;

public class Dictionary extends Application {

  private static final String DATA_FILE = "C:\\Users\\DELL\\IdeaProjects\\testDic\\src\\E_V.txt";
  private static final String FXML_FILE =
      "C:\\Users\\DELL\\IdeaProjects\\testDic\\src\\Dictionary_Main.fxml";
    private static final String SPLITTING_CHARACTERS = "<html>";
    private Map<String, Word> data = new TreeMap<>();

    @FXML
    private ListView<String> listView;
    @FXML
    private WebView definitionView;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        FileInputStream fis = new FileInputStream(FXML_FILE);
        BorderPane root = fxmlLoader.load(fis);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dictionary");
        primaryStage.show();

        // init components
        initComponents(scene);

        // read word list from E_V.txt
        readData();

        // load word list to the ListView
        loadWordList();
    }

    public void initComponents(Scene scene) {
        this.definitionView = (WebView) scene.lookup("#definitionView");
        this.listView = (ListView<String>) scene.lookup("#listView");
        Dictionary context = this;
        this.listView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    Word selectedWord = data.get(newValue.trim());
                    String definition = selectedWord.getWord_explain();
                    context.definitionView.getEngine().loadContent(definition, "text/html");
                }
        );
    }

    public void loadWordList() {
        this.listView.getItems().addAll(data.keySet());
    }

    public void readData() throws IOException {
        FileReader fr = new FileReader(DATA_FILE);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        while ((line = br.readLine()) != null){
            String[] word = line.split(SPLITTING_CHARACTERS);
            Word addword = new Word(word[0],word[1]);
            data.put(word[0], addword);
        }
        br.close();
    }
}
