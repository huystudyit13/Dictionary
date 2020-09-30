
public class DictionaryCommandline {

    public static void showAllWords(Dictionary dictionary) {
        System.out.println("No" + '\t'  + "| English" +'\t'+'\t'+"| Vietnamese");
        for(int i = 0;i < dictionary.number ;i++) {
            System.out.print(i+1);
            System.out.println('\t'+"| "+dictionary.list[i].getWord_target()+'\t'+'\t'+"| "+dictionary.list[i].getWord_explain());
        }
    }
    public static void dictionaryBasic(Dictionary dictionary){
        DictionaryManagement dm = new DictionaryManagement();
        dm.insertFromCommandline(dictionary);
        showAllWords(dictionary);
    }

    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();
        dictionaryBasic(dictionary);
    }
}
