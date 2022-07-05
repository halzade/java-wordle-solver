package wordle.solver.engine;

import java.util.List;

@SuppressWarnings(value = "unused")
public interface WordleEngineInt {

    void green1(char c);

    void green2(char c);

    void green3(char c);

    void green4(char c);

    void green5(char c);

    void yellow1(char c);

    void yellow2(char c);

    void yellow3(char c);

    void yellow4(char c);

    void yellow5(char c);

    void gray(char c);

    List<String> solve();
}
