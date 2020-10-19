import java.io.IOException;
import java.util.Map;

public class DictionaryCommandline extends DictionaryManagement {

    public static void showAllWords() {
        System.out.println("No" + '\t'  + "| English" +'\t'+'\t'+"| Vietnamese");
        int n = 1;
        for (Map.Entry<String, Word> entry : dictionary.list.entrySet()) {
            System.out.print(n);
            System.out.println('\t' + "| " + entry.getKey() + '\t' + '\t' + "| " + entry.getValue().getWord_explain());
            n++;
        }
    }

    public static void menu() {
        System.out.println("-------------------");
        System.out.println("1. Translate word");
        System.out.println("2. Add word");
        System.out.println("3. Delete word");
        System.out.println("4. Show all word");
        System.out.println("5. Fix word");
        System.out.println("6. Search words");
        System.out.println("7. End");
        System.out.println("-------------------");
    }

    public static void dictionaryBasic() throws IOException {
        DictionaryManagement dm = new DictionaryManagement();
        dm.insertFromFile();

        //chay menu

        int choice = 1;

        while ( choice != 0 ) {
            menu();
            choice = sc.nextInt();
            if (choice == 1) {
                dm.dictionaryLookup();
            } else if (choice == 2) {
                dm.insertFromCommandline();
            } else if (choice == 3) {
                dm.deleteWord();
            } else if (choice == 4) {
                showAllWords();
            } else if (choice == 5) {
                dm.fixWord();
            } else if (choice == 6) {
                dm.searchWords();
            } else {
                break;
            }
            System.out.println("\n \nWhat do you want to do next ?");
            //choice = input.nextInt();
        }
    }

    public static void main(String[] args) throws IOException {
        dictionaryBasic();
    }
}
