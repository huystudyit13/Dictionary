import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.collections.transformation.FilteredList;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;

public class Dictionary extends Application {

    private static final String DATA_FILE = "D:\\Code big project\\DicitonaryWithFX\\src\\E_V.txt";
    private static final String FXML_FILE = "D:\\Code big project\\DicitonaryWithFX\\src\\Dictionary_Main1.fxml";
    Map<String, Word> data = new TreeMap<String, Word>();
    ObservableMap oMap = FXCollections.observableMap(data);
    public Dictionary() { }
    @FXML
    private ListView<String> listView;
    @FXML
    private WebView definitionView;
    @FXML
    private TextField text;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        FileInputStream fis = new FileInputStream(FXML_FILE);
        AnchorPane root = fxmlLoader.load(fis);
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
        //loadTextField();
    }

    public void initComponents(Scene scene) {
        this.definitionView = (WebView) scene.lookup("#definitionView");
        this.listView = (ListView<String>) scene.lookup("#listView");
        Dictionary context = this;
        this.listView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    Word selectedWord = data.get(newValue.trim());
                    String definition = selectedWord.getWord_explain();
                    definitionView.getEngine().loadContent(definition, "text/html");
                }
        );
    }

    public void loadWordList() {
        this.listView.getItems().addAll(data.keySet());
    }

    /*public void loadTextField() {
        //FilteredList<Dictionary> filteredData = new FilteredList<Dictionary>(data, b -> true);
        Set<String> keys = data.keySet();

            for(String key : keys){
                if(key.toLowerCase().contains("hi")) {
                    this.listView.getItems().addAll(key);
                }
            }

    }*/

    public void readData() throws IOException {
        FileReader fr = new FileReader(DATA_FILE);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        while ((line = br.readLine()) != null) {
            String[] word = line.split("<html>");
            Word addword = new Word(word[0],word[1]);
            data.put(word[0], addword);
        }
        br.close();
    }

    public void changeToMainScene(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Dictionary_Main.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        // init components
        initComponents(tableViewScene);

        // read word list from E_V.txt
        readData();

        // load word list to the ListView
        loadWordList();
        window.setScene(tableViewScene);
        window.show();

    }

    public void addNewWord(ActionEvent event) {

    }

}
