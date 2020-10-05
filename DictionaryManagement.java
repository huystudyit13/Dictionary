
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class DictionaryManagement {
    static int tempNumber = 0;
    public DictionaryManagement() {
    }
    public static void insertFromFile(Dictionary dictionary) throws IOException {
        Scanner input = new Scanner(Paths.get("C:\\Users\\DELL\\IdeaProjects\\Dictionary\\src\\Dic.txt"),"UTF-8");
        int number = input.nextInt();
        //dictionary.newDictionary(number);
        input.nextLine();
        tempNumber += number;
        for (int i = 0; i < number; i++) {
            String word = input.next();
            String mean = input.nextLine();
            dictionary.addWord(word,mean);
        }
        input.close();
    }
    public void insertFromCommandline(Dictionary dictionary) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ban muon them bao nhieu tu ?");
        int number = sc.nextInt();
        number += tempNumber;
        //dictionary.newDictionary(number);
        sc.nextLine();
        for(int i = tempNumber;i < number ;i++) {
            String word = sc.nextLine();
            String mean = sc.nextLine();
            dictionary.addWord(word,mean);
        }
    }
    public void deleteWord(Dictionary dictionary) {
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


}
