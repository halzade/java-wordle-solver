package wordle.solver.test;

import org.junit.jupiter.api.Test;
import wordle.solver.WordleEngine;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWordle {

    @Test
    public void test() {
        final String WORD_LIST_FILE_NAME = "/home/lukas/Library/words/wordlist-german.txt";
        final WordleEngine w = new WordleEngine(WORD_LIST_FILE_NAME);
        w.green2('o');
        w.yellow1('r');
        w.yellow3('u');
        w.yellow3('s');
        w.gray('h');
        w.gray('m');

        final List<String> list = w.solve();

        assertEquals(1, list.size());
        assertEquals("torus", list.get(0));
    }
}
