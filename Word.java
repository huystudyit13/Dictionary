public class Word {
    private String word_target;
    private String word_explain;

    // getter/setter
    public String getWord_target() {
        return word_target;
    }
    public void setWord_target(String n) {
        this.word_target = n;
    }
    public String getWord_explain() {
        return word_explain;
    }
    public void setWord_explain(String n) {
        this.word_explain = n;
    }

    public Word(String n, String m) {
        this.word_explain = m;
        this.word_target = n;
    }


}
