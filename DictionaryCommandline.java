import java.io.IOException;

public class DictionaryCommandline extends DictionaryManagement {

    public static void showAllWords() {
        System.out.println("No" + '\t'  + "| English" +'\t'+'\t'+"| Vietnamese");
        for(int i = 0; i < dictionary.list.size(); i++ ) {
            System.out.print(i+1);
            System.out.println('\t'+"| "+ dictionary.list.get(i).getWord_target()+'\t'+'\t'+"| "+ dictionary.list.get(i).getWord_explain());
            //System.out.println(dictionary.list[i].getWord_explain());
        }
    }

    public static void menu() {
        System.out.println("-------------------");
        System.out.println("1. Dịch từ");
        System.out.println("2. Thêm từ");
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
                System.out.println("Dich tu");
            } else if (choice == 2) {
                dm.insertFromCommandline();
            } else if (choice == 3) {
                System.out.println("Xoa tu");
                //dm.deleteWord(dictionary);
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
