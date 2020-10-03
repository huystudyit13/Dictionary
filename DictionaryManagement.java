
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class DictionaryManagement {
    public DictionaryManagement() {
    }
    public static void insertFromCommandline(Dictionary dictionary) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        sc.nextLine();
        for(int i = 0;i < number ;i++) {
            String word = sc.nextLine();
            String mean = sc.nextLine();
            dictionary.addWord(word,mean);
        }
        sc.close();
    }
    public static void insertFromFile(Dictionary dictionary) throws IOException {
        Scanner input = new Scanner(Paths.get("D:\\Code big project\\Dictionary\\Dic.txt"),"UTF-8");
        int number = input.nextInt();
        input.nextLine();
        for (int i = 0; i < number; i++) {
            String word = input.nextLine();
            String mean = input.nextLine();
            dictionary.addWord(word,mean);
        }
    }
}
