import java.util.Map;
import java.io.*;
import java.util.Scanner;

public class DictionaryManagement {
    public static Dictionary dictionary = new Dictionary();
    public static Scanner sc = new Scanner(System.in);


    public DictionaryManagement() {
    }

    public static void insertFromFile() throws IOException {
        FileReader fr = new FileReader("C:\\Users\\DELL\\IdeaProjects\\Dictionary\\src\\Dic.txt");
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        while ((line = br.readLine()) != null){
            String[] word = line.split("\t");
            Word addword = new Word(word[0],word[1]);
            dictionary.list.put(word[0], addword);
        }
        br.close();
    }

    public static void insertFromCommandline() throws IOException {
        FileWriter fw = new FileWriter("C:\\Users\\DELL\\IdeaProjects\\Dictionary\\src\\Dic.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        System.out.println("Ban muon them bao nhieu tu ?");
        int number = sc.nextInt();
        sc.nextLine();
        // them tu vao list
        for(int i = 0 ;i < number ;i++) {
            String word = sc.nextLine();
            String mean = sc.nextLine();
            Word newword = new Word(word,mean);
            dictionary.list.put(word, newword);

        }
        //add tat ca cac tu vao file
        for (Map.Entry<String, Word> entry : dictionary.list.entrySet()) {
            bw.write(entry.getKey() + "\t" + entry.getValue().getWord_explain());
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    public void deleteWord() {
        System.out.println("Nhap tu Tieng Anh ban muon xoa:");
        sc.nextLine();
        String word = sc.nextLine();
        if ( dictionary.list.containsKey(word) ) {
            dictionary.list.remove(word);
        }
        else {
            System.out.println("Not found!");
        }

    }

    public void dictionaryLookup() {
        System.out.println("Nhap tu Tieng Anh ban muon tim:");
        sc.nextLine();
        String word = sc.nextLine();
        if ( dictionary.list.containsKey(word) ) {
            System.out.println(dictionary.list.get(word).getWord_explain());
        }
        else {
            System.out.println("Not found!");
        }
    }

    

}
