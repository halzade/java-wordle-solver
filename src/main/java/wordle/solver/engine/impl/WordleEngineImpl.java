package wordle.solver.engine.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import wordle.solver.engine.WordleEngineInt;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.String.valueOf;
import static wordle.solver.lists.WordLists.WORD_LIST_FILE_NAME;

public class WordleEngineImpl implements WordleEngineInt {

    private final List<Character> greenLetters_1 = new ArrayList<>();
    private final List<Character> greenLetters_2 = new ArrayList<>();
    private final List<Character> greenLetters_3 = new ArrayList<>();
    private final List<Character> greenLetters_4 = new ArrayList<>();
    private final List<Character> greenLetters_5 = new ArrayList<>();
    private final List<Character> yellowLetters_1 = new ArrayList<>();
    private final List<Character> yellowLetters_2 = new ArrayList<>();
    private final List<Character> yellowLetters_3 = new ArrayList<>();
    private final List<Character> yellowLetters_4 = new ArrayList<>();
    private final List<Character> yellowLetters_5 = new ArrayList<>();
    private final List<Character> grayLetters = new ArrayList<>();

    private static final Logger log = LogManager.getLogger(WordleEngineImpl.class);

    public static final WordleEngineInt Wordle;

    private WordleEngineImpl() {
    }

    static {
        Wordle = new WordleEngineImpl();
    }

    public void green1(char c) {
        greenLetters_1.add(c);
    }

    public void green2(char c) {
        greenLetters_2.add(c);
    }

    public void green3(char c) {
        greenLetters_3.add(c);
    }

    public void green4(char c) {
        greenLetters_4.add(c);
    }

    public void green5(char c) {
        greenLetters_5.add(c);
    }

    public void yellow1(char c) {
        yellowLetters_1.add(c);
    }

    public void yellow2(char c) {
        yellowLetters_2.add(c);
    }

    public void yellow3(char c) {
        yellowLetters_3.add(c);
    }

    public void yellow4(char c) {
        yellowLetters_4.add(c);
    }

    public void yellow5(char c) {
        yellowLetters_5.add(c);
    }

    public void gray(char c) {
        grayLetters.add(c);
    }

    public List<String> solve() {
        log.debug("solve()");

        final ArrayList<String> words = read();
        log.info("process " + words.size() + " words");

        final List<List<Character>> greenLetters = pack(greenLetters_1, greenLetters_2, greenLetters_3, greenLetters_4, greenLetters_5);
        final List<List<Character>> yellowLetters = pack(yellowLetters_1, yellowLetters_2, yellowLetters_3, yellowLetters_4, yellowLetters_5);

        final List<String> ret = new ArrayList<>();

        int found = 0;
        for (String w : words) {
            boolean skip = false;

            /*
             * GREEN LETTERS
             */
            int positionGreen = -1;
            for (List<Character> lettersGreenAtPosition : greenLetters) {
                positionGreen++;
                for (final Character c : lettersGreenAtPosition) {
                    if (!c.equals(w.charAt(positionGreen))) {
                        skip = true;
                        break;
                    }
                }
            }
            if (skip) {
                continue;
            }

            /*
             * YELLOW LETTERS
             */
            int positionYellow = -1;
            for (List<Character> lettersYellowAtPosition : yellowLetters) {
                positionYellow++;

                for (final Character c : lettersYellowAtPosition) {
                    if (c.equals(w.charAt(positionYellow))
                            || !w.contains(valueOf(c))) {
                        skip = true;
                        break;
                    }
                }
            }
            if (skip) {
                continue;
            }

            /*
             * GRAY LETTERS
             */
            for (Character c : grayLetters) {
                if (w.contains(valueOf(c))) {
                    skip = true;
                    break;
                }
            }

            if (skip) {
                continue;
            }

            found++;
            log.info(w);
            ret.add(w);
        }
        log.info("found: " + found);
        return ret;
    }

    private List<List<Character>> pack(List<Character> list1, List<Character> list2, List<Character> list3, List<Character> list4, List<Character> list5) {
        log.debug("pack()");

        final List<List<Character>> ret = new ArrayList<>(5);
        ret.add(list1);
        ret.add(list2);
        ret.add(list3);
        ret.add(list4);
        ret.add(list5);
        return ret;
    }

    /**
     * Read list of all words
     */
    private ArrayList<String> read() {
        log.debug("read()");

        final ArrayList<String> ret = new ArrayList<>();
        try (Scanner scanner = new Scanner(new URL(WORD_LIST_FILE_NAME).openStream())) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (5 == line.length()) {
                    ret.add(line.toLowerCase());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ret;
    }
}
