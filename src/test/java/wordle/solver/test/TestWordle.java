package wordle.solver.test;

import org.junit.jupiter.api.Test;
import wordle.solver.WordleEngine;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static wordle.solver.Wordle.WORD_LIST_FILE_NAME;

public class TestWordle {

    @Test
    public void test() {
        final WordleEngine w = new WordleEngine(WORD_LIST_FILE_NAME);
        w.green2('o');
        w.yellow1('r');
        w.yellow1('s');
        w.yellow3('u');
        w.yellow3('s');
        w.gray('h');
        w.gray('m');
        w.gray('p');

        final List<String> list = w.solve();

        assertEquals(1, list.size());
        assertEquals("torus", list.get(0));
    }
}
