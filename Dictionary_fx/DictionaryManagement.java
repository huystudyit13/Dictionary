import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.*;

import java.util.Map;

import java.util.TreeMap;
import java.io.IOException;

public class DictionaryManagement {


    //ObservableMap oMap = FXCollections.observableMap(data);

    private TextField text;


    public DictionaryManagement() {}


    public void searchWord() {

    }

//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        try {
//            readData();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        loadWordList();
//    }
    /*
    public static void insertFromCommandline() throws IOException {
        System.out.println("How many words do you want to add ?");
        int number = sc.nextInt();
        sc.nextLine();
        // them tu vao list
        for(int i = 0 ;i < number ;i++) {
        System.out.println("\n" + "Enter the word you want to add:");
            String word = sc.nextLine();
            String mean = sc.nextLine();
            if( dictionary.data.containsKey(word)) {
                System.out.println("Dictionary has already contains this word!");
            } else {
                Word newword = new Word(word, mean);
                dictionary.data.put(word, newword);
                System.out.println("Done!");
            }
        }
        //add tat ca cac tu vao file
        dictionaryExportToFile();
    }


    public static void dictionaryLookup() {
        System.out.println("Enter the English word you want to find:");
        sc.nextLine();
        String word = sc.nextLine();
        if ( dictionary.data.containsKey(word) ) {
            System.out.println(dictionary.data.get(word).toString());
        } else {
            System.out.println("Not found!");
        }
    }

    public static void fixWord() throws IOException {
        System.out.println("Enter the English word you want to change");
        sc.nextLine();
        String oldWord = sc.nextLine();
        if ( dictionary.data.containsKey(oldWord) ) {
            dictionary.data.remove(oldWord);
            System.out.println("Enter the new English word");
            String newEword = sc.nextLine();
            System.out.println("Enter the new meaning");
            String newmeaning = sc.nextLine();
            Word newword = new Word(newEword, newmeaning);
            dictionary.data.put(newEword, newword);
            System.out.println("Done!");
            dictionaryExportToFile();
        } else {
            System.out.println("Not found!");
        }
    }

    public void searchWords() {
        System.out.println("Enter the English word you want to find:");
        sc.nextLine();
        String s = sc.nextLine();
        int n = s.length() ;
        boolean check = false;
        for (Map.Entry<String, Word> entry : dictionary.data.entrySet()) {
            int m = entry.getKey().length();
            if (m >= n) {
                String ss = entry.getKey().substring(0, n);
                if (s.contentEquals(ss)) {
                    System.out.println(entry.getValue().toString());
                    check = true;
                }
            }
        }
        if( !check) {
            System.out.println("Not found!");
        }
    }
*/
}
