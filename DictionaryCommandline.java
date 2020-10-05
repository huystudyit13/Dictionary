import java.io.IOException;
import java.util.Scanner;

public class DictionaryCommandline {

    public static void showAllWords(Dictionary dictionary) {
        System.out.println("No" + '\t'  + "| English" +'\t'+'\t'+"| Vietnamese");
        for(int i = 0;i < dictionary.getNumber() ;i++) {
            System.out.print(i+1);
            System.out.println('\t'+"| "+dictionary.list[i].getWord_target()+'\t'+'\t'+"| "+dictionary.list[i].getWord_explain());
            //System.out.println(dictionary.list[i].getWord_explain());
        }
    }
    public static void menu() {
        System.out.println("-------------------");
        System.out.println("1. Them tu");
        System.out.println("2. Xoa tu");
        System.out.println("3. In tu");
        System.out.println("4. Ket thuc");
        System.out.println("-------------------");
    }
    public static void dictionaryBasic(Dictionary dictionary) throws IOException {
        Scanner input = new Scanner(System.in);
        DictionaryManagement dm = new DictionaryManagement();
        dm.insertFromFile(dictionary);
        //chay menu

        int choice = 1;

        while ( choice != 0 ) {
            menu();
            choice = input.nextInt();
            if (choice == 1) {
                dm.insertFromCommandline(dictionary);
            } else if (choice == 2) {
                //System.out.println("Xoa tu");
                dm.deleteWord(dictionary);
            } else if (choice == 3) {
                showAllWords(dictionary);
            } else {
                break;
            }
            System.out.println("\n \nBan muon lam gi tiep ?");
            //choice = input.nextInt();
        }
    }

    public static void main(String[] args) throws IOException {
        Dictionary dictionary = new Dictionary();
        dictionaryBasic(dictionary);
    }
}
