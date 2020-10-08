
import java.io.*;
import java.util.Scanner;

public class DictionaryManagement {
    public static Dictionary dictionary = new Dictionary();
    public static Scanner sc = new Scanner(System.in);;

    public DictionaryManagement() {
    }

    public static void insertFromFile() throws IOException {
        FileReader fr = new FileReader("C:\\Users\\DELL\\IdeaProjects\\Dictionary\\src\\Dic.txt");
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        while ((line = br.readLine()) != null){
            String[] word = line.split("\t");
            Word addword = new Word(word[0],word[1]);
            dictionary.list.add(addword);
        }
        br.close();
    }

    public void insertFromCommandline() throws IOException {
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
            dictionary.list.add(newword);
        }
        //add tat ca cac tu vao file
        for(int i = 0;i < dictionary.list.size() ;i++) {
            bw.write(dictionary.list.get(i).getWord_target() + "\t" + dictionary.list.get(i).getWord_explain());
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    /*public void deleteWord() {

    }*/

}
