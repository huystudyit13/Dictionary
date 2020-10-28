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

    public Word(String target, String explain) {
        this.word_explain = explain;
        this.word_target = target;
    }

    @Override
    public String toString() {
        return word_target + '\t' + word_explain ;
    }
}

