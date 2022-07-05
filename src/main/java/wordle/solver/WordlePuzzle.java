package wordle.solver;

import static wordle.solver.engine.impl.WordleEngineImpl.Wordle;

public class WordlePuzzle {

    public static void main(String[] args) {

        /* highlighted green letter on position 2 */
        Wordle.green2('o');

        /* highlighted yellow letters on positions 1 and 3 */
        Wordle.yellow1('r');
        Wordle.yellow3('u');
        Wordle.yellow3('s');

        /* highlighted gray letters on any position */
        Wordle.gray('c');
        Wordle.gray('z');

        Wordle.solve();
    }
}
