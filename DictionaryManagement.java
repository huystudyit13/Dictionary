
import java.util.Scanner;

public class DictionaryManagement {
    public DictionaryManagement(){
    }
    public static void insertFromCommandline(Dictionary dictionary){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String word = sc.nextLine();
        for(int i = 0;i < n ;i++){
            String word1 = sc.nextLine();
            String word2 = sc.nextLine();
            dictionary.addWord(word1,word2);
        }
    }
}
