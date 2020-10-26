package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import javafx.util.Pair;

import java.io.*;
import java.net.URL;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class Controller implements Initializable{


    public static FileReader fr;
    public static BufferedReader br;
    public static FileWriter fw;
    public static BufferedWriter bw;
    public static final String fileEV_name = "C:\\Users\\DELL\\IdeaProjects\\test\\src\\sample\\E_V.txt";
    public static final String fileVE_name = "C:\\Users\\DELL\\IdeaProjects\\test\\src\\sample\\V_E.txt";
    public Map<String, Word> data = new TreeMap<String, Word>();
    public ObservableList<String> list = FXCollections.observableArrayList();
    public boolean check = true;


    @FXML
    public ListView<String> listView;
    @FXML
    public WebView definitionView;
    @FXML
    public TextField textField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.listView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    Word selectedWord = data.get(newValue.trim());
                    String definition = selectedWord.getWord_explain();
                    definitionView.getEngine().loadContent(definition, "text/html");
                });
        try {
            readData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.list.addAll(this.data.keySet());
        this.listView.setItems(this.list);
    }

    public void readData() throws IOException {
        fr = new FileReader(fileEV_name);
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

    public void actAdd(ActionEvent event){
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Add new word");
        dialog.setHeaderText(null);
        ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20,150,10,10));

        TextField newWord = new TextField();
        newWord.setPromptText("New word");
        TextField newDef = new TextField();
        newDef.setPromptText("Definition");

        grid.add(new Label("New word:"),0,0);
        grid.add(newWord,1,0);
        grid.add(new Label("Definition:"),0,1);
        grid.add(newDef,1,1);

        Node add = dialog.getDialogPane().lookupButton(addButton);
        add.setDisable(true);

        newWord.textProperty().addListener((observable, oldValue, newValue) -> {
            add.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButton) {
                return new Pair<>(newWord.getText(), newDef.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();
        result.ifPresent(word -> {
            if(this.data.containsKey(word.getKey())){
                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Error ");
                alert.setHeaderText("Can not add word");
                alert.setContentText("This word has already existed!");
                alert.showAndWait();
            } else {
                String s = "<i>" + word.getKey() + "</i><br/><ul><li><font color='#cc0001'><b> " + word.getValue() + "</b></font></li></ul></html>";
                Word obj = new Word(word.getKey(), s);
                this.data.put(word.getKey(), obj);
                this.list.clear();
                this.list.addAll(this.data.keySet());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Done!");
                alert.showAndWait();
            }
        });
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
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Fix word");
        dialog.setHeaderText(null);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        HTMLEditor htmlEditor = new HTMLEditor();
        htmlEditor.setPrefHeight(245);
        htmlEditor.setMinHeight(220);
        String def = this.listView.getSelectionModel().getSelectedItem();
        String INITIAL_TEXT = this.data.get(def).getWord_explain();
        htmlEditor.setHtmlText(INITIAL_TEXT);

        Button showHTMLButton = new Button("Show in WebView");
        WebView webView = new WebView();
        webView.setPrefHeight(245);
        webView.setMinHeight(220);

        showHTMLButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                webView.getEngine().loadContent(htmlEditor.getHtmlText(),"text/html");
            }
        });

        VBox root = new VBox();
        root.setPadding(new Insets(10,10,10,10));
        root.setSpacing(5);
        root.getChildren().addAll(htmlEditor, showHTMLButton, webView);

        dialog.getDialogPane().setContent(root);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return htmlEditor.getHtmlText();
            }
            return null;
        });
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(newDef ->{
            this.data.get(def).setWord_explain(newDef);
            this.definitionView.getEngine().loadContent(newDef,"text/html");
        });


    }

    public void actUpdate(ActionEvent event) throws IOException {
        if (check) {
            fw = new FileWriter("C:\\Users\\DELL\\IdeaProjects\\test\\src\\sample\\E_V.txt");
            bw = new BufferedWriter(fw);
        } else {
            fw = new FileWriter("C:\\Users\\DELL\\IdeaProjects\\test\\src\\sample\\V_E.txt");
            bw = new BufferedWriter(fw);
        }
        //add tat ca cac tu vao file
        for (Map.Entry<String,Word> entry : data.entrySet()) {
            bw.write(entry.getKey() + "<html>" + entry.getValue().getWord_explain());
            bw.newLine();
        }
        bw.flush();
        bw.close();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Done!");
        alert.showAndWait();
    }

    public void changeVE(ActionEvent event) throws IOException {
        check = false;
        this.definitionView.getEngine().loadContent("");
        this.data.clear();
        this.list.clear();
        fr = new FileReader(fileVE_name);
        br = new BufferedReader(fr);
        String line = "";
        while ((line = br.readLine()) != null) {
            String[] word = line.split("<html>");
            Word addword = new Word(word[0],word[1]);
            this.data.put(word[0], addword);
        }
        br.close();
        this.list.addAll(this.data.keySet());
        this.listView.setItems(this.list);
    }

    public void changeEV(ActionEvent event) throws IOException {
        check = true;
        this.definitionView.getEngine().loadContent("");
        this.data.clear();
        this.list.clear();
        readData();
        this.list.addAll(this.data.keySet());
        this.listView.setItems(this.list);
    }

}
