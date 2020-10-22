import sun.awt.geom.AreaOp;

import java.util.Map;
import java.io.*;
import java.util.Scanner;

public class DictionaryManagement {
    public static Dictionary dictionary = new Dictionary();
    public static Scanner sc = new Scanner(System.in);
    public static FileReader fr;
    public static BufferedReader br;
    public static FileWriter fw;
    public static BufferedWriter bw;
    private static final String file_name = "C:\\Users\\DELL\\IdeaProjects\\Dictionary\\src\\Dic.txt";

    public DictionaryManagement() {
    }
    public static void dictionaryExportToFile() throws IOException {
        fw = new FileWriter(file_name);
        bw = new BufferedWriter(fw);
        //add tat ca cac tu vao file
        for (Map.Entry<String, Word> entry : dictionary.list.entrySet()) {
            bw.write(entry.getKey() + "\t" + entry.getValue().getWord_explain());
            bw.newLine();
        }
        bw.flush();
        bw.close();

    }
    public static void insertFromFile() throws IOException {
        fr = new FileReader(file_name);
        br = new BufferedReader(fr);
        String line = "";
        while ((line = br.readLine()) != null){
            String[] word = line.split("\t");
            Word addword = new Word(word[0],word[1]);
            dictionary.list.put(word[0], addword);
        }
        br.close();
    }

    public static void insertFromCommandline() throws IOException {
        System.out.println("How many words do you want to add ?");
        int number = sc.nextInt();
        sc.nextLine();
        // them tu vao list
        for(int i = 0 ;i < number ;i++) {
        System.out.println("\n" + "Enter the word you want to add:");
            String word = sc.nextLine();
            String mean = sc.nextLine();
            if( dictionary.list.containsKey(word)) {
                System.out.println("Dictionary has already contains this word!");
            } else {
                Word newword = new Word(word, mean);
                dictionary.list.put(word, newword);
                System.out.println("Done!");
            }
        }
        //add tat ca cac tu vao file
        dictionaryExportToFile();
    }

    public static void deleteWord() throws IOException {
        System.out.println("Enter the English word you want to delete:");
        sc.nextLine();
        String word = sc.nextLine();
        if ( dictionary.list.containsKey(word) ) {
            dictionary.list.remove(word);
            System.out.println("Done!");
        } else {
            System.out.println("Not found!");
        }
        //add tat ca cac tu vao file
        dictionaryExportToFile();
    }

    public static void dictionaryLookup() {
        System.out.println("Enter the English word you want to find:");
        sc.nextLine();
        String word = sc.nextLine();
        if ( dictionary.list.containsKey(word) ) {
            System.out.println(dictionary.list.get(word).toString());
        } else {
            System.out.println("Not found!");
        }
    }

    public static void fixWord() throws IOException {
        System.out.println("Enter the English word you want to change");
        sc.nextLine();
        String oldWord = sc.nextLine();
        if ( dictionary.list.containsKey(oldWord) ) {
            dictionary.list.remove(oldWord);
            System.out.println("Enter the new English word");
            String newEword = sc.nextLine();
            System.out.println("Enter the new meaning");
            String newmeaning = sc.nextLine();
            Word newword = new Word(newEword, newmeaning);
            dictionary.list.put(newEword, newword);
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
        for (Map.Entry<String, Word> entry : dictionary.list.entrySet()) {
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

}
