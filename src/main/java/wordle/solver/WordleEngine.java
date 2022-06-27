package wordle.solver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;

public class WordleEngine {

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

    private final String wordListFileName;

    private static final Logger log = LogManager.getLogger(WordleEngine.class);

    public WordleEngine(String wordListFileName) {
        this.wordListFileName = wordListFileName;
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

        final ArrayList<String> words = read();
        log.info("Process " + words.size() + " words");

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
        log.info("Found:" + found);
        return ret;
    }

    private List<List<Character>> pack(List<Character> list1, List<Character> list2, List<Character> list3, List<Character> list4, List<Character> list5) {
        final List<List<Character>> ret = new ArrayList<>(5);
        ret.add(list1);
        ret.add(list2);
        ret.add(list3);
        ret.add(list4);
        ret.add(list5);
        return ret;
    }

    private ArrayList<String> read() {
        final ArrayList<String> ret = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(wordListFileName))) {
            String line = br.readLine();
            while (line != null) {
                if (5 == line.length()) {
                    ret.add(line.toLowerCase());
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
