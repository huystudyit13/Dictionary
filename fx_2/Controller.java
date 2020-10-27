import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
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

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import java.io.*;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.function.Predicate;

public class Controller implements Initializable{

    public static FileReader fr;
    public static BufferedReader br;
    public static FileWriter fw;
    public static BufferedWriter bw;
    public static final String fileEV_name = "C:\\Users\\DELL\\IdeaProjects\\test\\src\\E_V.txt";
    public static final String fileVE_name = "C:\\Users\\DELL\\IdeaProjects\\test\\src\\V_E.txt";
    public Map<String, Word> data = new TreeMap<>();
    public ObservableList<String> list = FXCollections.observableArrayList();
    public FilteredList<String> filteredList;
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

        filteredList = new FilteredList<>(list,e ->true);
        textField.setOnKeyPressed(e -> textField.textProperty().addListener((observable, oldValue, newValue) -> filteredList.setPredicate((Predicate<? super String>) list->{
            if(newValue == null || newValue.isEmpty()) {
                return true;
            }
            String lowerFilter = newValue.toLowerCase();
            int n = lowerFilter.length();
            int m = list.length();
            if (m >= n) {
                String s = list.substring(0, n).toLowerCase();
                if (lowerFilter.contentEquals(s)) {
                    return true;
                }
            }
            return false;
        })));

        this.listView.setItems(filteredList);

    }

    public void readData() throws IOException {
        fr = new FileReader(fileEV_name);
        br = new BufferedReader(fr);
        String line ;
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

        newWord.textProperty().addListener((observable, oldValue, newValue) -> add.setDisable(newValue.trim().isEmpty()));

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

        showHTMLButton.setOnAction(event1 -> webView.getEngine().loadContent(htmlEditor.getHtmlText(),"text/html"));

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
            fw = new FileWriter(fileEV_name);
        } else {
            fw = new FileWriter(fileVE_name);
        }
        bw = new BufferedWriter(fw);
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
    public void searchWithAPI(ActionEvent event) {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Search with google API");
        dialog.setHeaderText("Search with google API");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20,50,10,10));

        ToggleGroup typeDictionary = new ToggleGroup();
        RadioButton E_VButton = new RadioButton("English to VietNamese");
        E_VButton.setToggleGroup(typeDictionary);
        RadioButton V_EButton = new RadioButton("Vietnamese to English");
        V_EButton.setToggleGroup(typeDictionary);
        TextField newWord = new TextField();
        newWord.setPromptText("New word");
        WebView webDefinition = new WebView();
        webDefinition.setMaxSize(300,200);
        Button searchButton = new Button();
        searchButton.setText("Search");

        grid.add(new Label("Choose type of Translate:"),0,0);
        grid.add(E_VButton,1,0);
        grid.add(V_EButton,1,1);
        grid.add(new Label("New word:"),0,2);
        grid.add(newWord,1,2);
        grid.add(new Label("Definition:"),0,3);
        grid.add(webDefinition,1,3);
        grid.add(searchButton,2,2);

        searchButton.setOnAction(event1 -> {
            String text = newWord.getText();
            if (E_VButton.isSelected()) {
                try {
                    webDefinition.getEngine().loadContent(translate("en", "vi", text));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    webDefinition.getEngine().loadContent(translate("vi", "en", text));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });

        dialog.getDialogPane().setContent(grid);
        dialog.showAndWait();
    }

    private static String translate(String langFrom, String langTo, String text) throws IOException {
        // INSERT YOU URL HERE
        String urlStr = "https://script.google.com/macros/s/AKfycbyKCVFomoxRlGw8scWXMFIQrnBcA8BpNeSDIoJCp-la_UuxLKw2/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
    public void changeVE(ActionEvent event) throws IOException {
        this.data.clear();
        this.list.clear();
        //this.filteredList.clear();
        check = false;
        this.definitionView.getEngine().loadContent("");
        fr = new FileReader(fileVE_name);
        br = new BufferedReader(fr);
        String line ;
        while ((line = br.readLine()) != null) {
            String[] word = line.split("<html>");
            Word addword = new Word(word[0],word[1]);
            this.data.put(word[0], addword);
        }
        br.close();
        this.list.addAll(this.data.keySet());
        //this.filteredList = new FilteredList<>(list,e ->true);
        //this.listView.setItems(this.list);
    }

    public void changeEV(ActionEvent event) throws IOException {
        this.listView.getSelectionModel().clearSelection();
        this.definitionView.getEngine().loadContent("");
        check = true;
        this.data.clear();
        this.list.clear();
        //this.filteredList.clear();
        readData();
        this.list.addAll(this.data.keySet());
        //this.listView.setItems(this.list);
        //this.filteredList = new FilteredList<>(list,e ->true);
    }

}
