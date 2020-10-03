import java.io.IOException;

public class DictionaryCommandline {

    public static void showAllWords(Dictionary dictionary) {
        System.out.println("No" + '\t'  + "| English" +'\t'+'\t'+"| Vietnamese");
        for(int i = 0;i < dictionary.number ;i++) {
            System.out.print(i+1);
            System.out.println('\t'+"| "+dictionary.list[i].getWord_target()+'\t'+'\t'+"| "+dictionary.list[i].getWord_explain());
        }
    }
    public static void dictionaryBasic(Dictionary dictionary) throws IOException {
        DictionaryManagement dm = new DictionaryManagement();
        //dm.insertFromCommandline(dictionary);
        dm.insertFromFile(dictionary);
        showAllWords(dictionary);
    }

    public static void main(String[] args) throws IOException {
        Dictionary dictionary = new Dictionary();
        dictionaryBasic(dictionary);
    }
}
