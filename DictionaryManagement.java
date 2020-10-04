
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class DictionaryManagement {
    static int tempNumber = 0;
    public DictionaryManagement() {
    }
    public static void insertFromFile(Dictionary dictionary) throws IOException {
        Scanner input = new Scanner(Paths.get("D:\\Code big project\\Dictionary\\Dic.txt"),"UTF-8");
        int number = input.nextInt();
        input.nextLine();
        tempNumber += number;
        for (int i = 0; i < number; i++) {
            String word = input.next();
            String mean = input.nextLine();
            dictionary.addWord(word,mean);
        }
        input.close();
    }
    public static void insertFromCommandline(Dictionary dictionary) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ban muon them bao nhieu tu ?");
        int number = sc.nextInt();
        sc.nextLine();
        for(int i = tempNumber;i < number ;i++) {
            String word = sc.nextLine();
            String mean = sc.nextLine();
            dictionary.addWord(word,mean);
        }
        sc.close();
    }



}
