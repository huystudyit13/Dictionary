import java.io.IOException;
import java.util.Map;

public class DictionaryCommandline extends DictionaryManagement {

    public static void showAllWords() {
        System.out.println("No" + '\t'  + "| English" +'\t'+'\t'+"| Vietnamese");
        int n = 1;
        for (Map.Entry<String, Word> entry : dictionary.list.entrySet()) {
            System.out.print(n);
            System.out.println('\t'+"| "+ entry.getKey()+'\t'+'\t'+"| "+ entry.getValue().getWord_explain());
            n++;
        }
    }

    public static void menu() {
        System.out.println("-------------------");
        System.out.println("1. Dich tu");
        System.out.println("2. Them tu");
        System.out.println("3. Xoa tu");
        System.out.println("4. In tu");
        System.out.println("5. Ket thuc");
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
                //System.out.println("Dich tu");
                dm.dictionaryLookup();
            } else if (choice == 2) {
                dm.insertFromCommandline();
            } else if (choice == 3) {
                //System.out.println("Xoa tu");
                dm.deleteWord();
            } else if (choice == 4) {
                showAllWords();
            } else {
                break;
            }
            System.out.println("\n \nBan muon lam gi tiep ?");
            //choice = input.nextInt();
        }
    }

    public static void main(String[] args) throws IOException {
        dictionaryBasic();
    }
}
