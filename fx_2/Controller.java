package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class Controller implements Initializable{


    public static FileReader fr;
    public static BufferedReader br;
    public static FileWriter fw;
    public static BufferedWriter bw;
    public static final String file_name = "C:\\Users\\DELL\\IdeaProjects\\test\\src\\sample\\E_V.txt";
    public Map<String, Word> data = new TreeMap<String, Word>();
    public ObservableList<String> list = FXCollections.observableArrayList();


    @FXML
    public ListView<String> listView;
    @FXML
    public WebView definitionView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.listView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    Word selectedWord = data.get(newValue.trim());
                    String definition = selectedWord.getWord_explain();
                    definitionView.getEngine().loadContent(definition, "text/html");
                }
        );
        try {
            readData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.list.addAll(this.data.keySet());
        this.listView.setItems(this.list);
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

    public void closeApp(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    public void changeToAddScene(ActionEvent event) throws Exception {
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("AddScene.fxml"));
//        Parent addScene = loader.load();
//        Scene scene = new Scene(addScene);
//        stage.setScene(scene);
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("AddScene.fxml"));

        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = new Stage();
        window.setScene(tableViewScene);

        window.show();
    }



    public void actDelete(ActionEvent event) {
        String selected = listView.getSelectionModel().getSelectedItem();
        this.list.remove(selected);
        this.data.remove(selected);
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

}
