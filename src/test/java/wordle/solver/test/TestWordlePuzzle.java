package wordle.solver.test;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static wordle.solver.engine.impl.WordleEngineImpl.Wordle;

public class TestWordlePuzzle {

    @Test
    public void test() {

        Wordle.green2('o');
        Wordle.yellow1('r');
        Wordle.yellow1('s');
        Wordle.yellow3('u');
        Wordle.yellow3('s');
        Wordle.gray('h');
        Wordle.gray('m');
        Wordle.gray('p');

        final List<String> list = Wordle.solve();

        assertEquals(1, list.size());
        assertEquals("torus", list.get(0));
    }
}
