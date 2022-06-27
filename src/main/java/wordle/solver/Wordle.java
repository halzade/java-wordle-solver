package wordle.solver;

public class Wordle {

    public static final String WORD_LIST_FILE_NAME = "https://raw.githubusercontent.com/dwyl/english-words/master/words_alpha.txt";

    public static void main(String[] args) {
        final WordleEngine w = new WordleEngine(WORD_LIST_FILE_NAME);

        /* highlighted green letter on position 2 */
        w.green2('o');
        /* highlighted yellow letters on positions 1 and 3 */
        w.yellow1('r');
        w.yellow3('u');
        w.yellow3('s');
        /* highlighted gray letters on any position */
        w.gray('c');
        w.gray('z');

        w.solve();
    }
}
