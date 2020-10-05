public class Dictionary {
    private int number = 0;
    Word[] list = new Word[9999];
    
    public Dictionary() {
    }
    /*
    public void newDictionary(int capacity) {
        list = new Word[capacity];
    }*/
    public void addWord(String n, String m) {
        list[number] = new Word(n,m);
        number++;
    }
    public int getNumber() {
        return this.number;
    }
    public void deleteWord(int n) {
        for (int i = n; i < (this.number - 1); i++) {
            list[i] = list[i + 1];
        }
        this.number--;
    }
}
