# Wordle solver

## Java code to easily solve Wordle puzzles

### Application use

Use in IDE to easily edit guessed letters in `WordlePuzzle`

For example

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

With pre-defined word list, `WordleEngine` find following words 

    15:51:00 INFO WordleEngineImpl:90 - process 15920 words
    15:51:00 INFO WordleEngineImpl:152 - morus
    15:51:00 INFO WordleEngineImpl:152 - porus
    15:51:00 INFO WordleEngineImpl:152 - sorus
    15:51:00 INFO WordleEngineImpl:152 - torus
    15:51:00 INFO WordleEngineImpl:155 - found: 4

