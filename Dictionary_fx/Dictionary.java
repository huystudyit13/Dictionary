import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class Dictionary extends Application {

    public static FileReader fr;
    public static BufferedReader br;
    public static FileWriter fw;
    public static BufferedWriter bw;
    private static final String file_name = "D:\\Code big project\\DicitonaryWithFX\\src\\E_V.txt";
    private Map<String, Word> data = new TreeMap<String, Word>();
    private ObservableList<String> list = FXCollections.observableArrayList();
    private static final String FXML_FILE = "D:\\Code big project\\DicitonaryWithFX\\src\\Dictionary_Main1.fxml";

    @FXML
    private ListView<String> listView;

    @FXML
    private WebView definitionView;

    @FXML
    private TextField add_NewWord;

    @FXML
    private TextField add_NewDefi;


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
        readData();
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
                    definitionView.getEngine().loadContent(definition, "text/html");
                }
        );
    }

    public void loadWordList() {
        this.list.addAll(this.data.keySet());
        this.listView.setItems(this.list);
        //this.listView.getItems().addAll(this.data.keySet());
    }

    public void readData() throws IOException {
        fr = new FileReader(file_name);
        br = new BufferedReader(fr);
        String line = "";
        while ((line = br.readLine()) != null) {
            String[] word = line.split("<html>");
            Word addword = new Word(word[0],word[1]);
            this.data.put(word[0], addword);
        }
        br.close();
    }


    public void changeToMainScene(ActionEvent event) throws IOException {

    }

    public void changeToAddScene(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Add_Scene.fxml"));

        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = new Stage();
        window.setScene(tableViewScene);

        window.show();
    }


    public void closeApp(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    public void actAdd(ActionEvent event) throws IOException {
        String addNewWord = add_NewWord.getText();
        String addNewDefi = add_NewDefi.getText();
        Word newword = new Word(addNewWord,addNewDefi);
        data.put(addNewWord,newword);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Done!");
        alert.show();
        changeToMainScene(event);
    }
    public void actDelete(ActionEvent event) {
        String selected = listView.getSelectionModel().getSelectedItem();
//        this.list.remove(selected);
//        this.data.remove(selected);
        System.out.println(selected);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Done!");
        alert.showAndWait();
    }
    public void actFix(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Done!");
        alert.showAndWait();
    }
    public void actUpdate(ActionEvent event) throws IOException {
//        fw = new FileWriter("E:\\testDic\\src\\1.txt");
//        bw = new BufferedWriter(fw);
//        //add tat ca cac tu vao file
//        for (Map.Entry<String,Word> entry : data.entrySet()) {
//            bw.write(entry.getKey() + "<html>" + entry.getValue().getWord_explain());
//            bw.newLine();
//        }
//        bw.flush();
//        bw.close();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Done!");
        alert.showAndWait();
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





    public void addNewWord(ActionEvent event) {

    }

}
