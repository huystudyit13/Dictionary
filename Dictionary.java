public class Dictionary {
    int number = 0;
    Word [] list = new Word[100];
    public Dictionary(){
    }
    public void addWord(String n, String m){
        list[number] = new Word(n,m);
        number++;
    }
}
