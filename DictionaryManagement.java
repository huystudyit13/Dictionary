
import java.io.*;
import java.util.Scanner;

public class DictionaryManagement {
    static int tempNumber = 0;
    public DictionaryManagement() {
    }
    public static void insertFromFile(Dictionary dictionary) throws IOException {
        FileReader fr = new FileReader("D:\\Code big project\\Dictionary\\Dic.txt");
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        while ((line = br.readLine()) != null){
            String[] word = line.split("\t");
            dictionary.addWord(word[0],word[1]);
        }
        br.close();
    }

    public void insertFromCommandline(Dictionary dictionary) throws IOException {
        Scanner sc = new Scanner(System.in);
        FileWriter fw = new FileWriter("D:\\Code big project\\Dictionary\\Dic.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        System.out.println("Ban muon them bao nhieu tu ?");
        int number = sc.nextInt();
        sc.nextLine();
        // them tu vao list
        for(int i = 0 ;i < number ;i++) {
            String word = sc.nextLine();
            String mean = sc.nextLine();
            dictionary.addWord(word,mean);
        }
        //add tat ca cac tu vao file
        for(int i = 0;i < dictionary.getNumber() ;i++) {
            bw.write(dictionary.list[i].getWord_target() + "\t" + dictionary.list[i].getWord_explain());
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
    /*public void deleteWord(Dictionary dictionary) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap tu ban muon xoa :");
        String word = sc.nextLine();
        int count = -1;
        for(int i = 0;i < dictionary.getNumber() ;i++) {
            if( word.equals(dictionary.list[i].getWord_explain()) ) {
                count = i;
                break;
            }
            else if( word.equals(dictionary.list[i].getWord_target()) ) {
                count = i;
                break;
            }
        }
        if (count == -1) {
            System.out.println("Word not found!");
        }
        else {
            dictionary.deleteWord(count);
        }
    }
    public void deleteWord(Dictionary dictionary) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap tu ban muon xoa :");
        String word = sc.nextLine();
    }*/


}
