import java.util.ArrayList;
import java.util.Scanner;

public class Hangman {
    private int correctLetters;
    ArrayList<Character> guessedLetters;

    public Hangman() {
        guessedLetters = new ArrayList<>();
        correctLetters = 0;
        int hangmanIndex = 0;
        int lives = 6;

        System.out.println("WELCOME TO HANGMAN");
        String[] hangmanAscii = buildHangmanAscii();
        System.out.println(hangmanAscii[hangmanIndex]);

        String word = getWordFromPlayerOne();
        int numberOfLetters = countLetters(word);

        char[] wordBoard = createWordBoard(word);
        System.out.println(wordBoard);

        startGameLoop(word, wordBoard, numberOfLetters, hangmanIndex, lives, hangmanAscii);

    }
    public static String getWordFromPlayerOne() {
        Scanner wordInputScanner = new Scanner(System.in);
        String wordInput;
        while (true){
            System.out.print("Player 1 give me a word: ");
            wordInput = wordInputScanner.nextLine();
            if (wordInput.isEmpty()) {
                System.out.println("You have to enter a word");
                continue;
            }
            boolean valid = true;
            for(int i = 0; i < wordInput.length(); i++) {
                if (!Character.isLetter(wordInput.charAt(i))) {
                    valid = false;
                    System.out.println("Please Enter only valid letters");
                    break;
                }
            }
            if (!valid) {
                continue;
            }
            break;
        }
        return wordInput.toLowerCase();
    }
    public static char[] createWordBoard(String word) {
        char[] wordBoard = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == ' ') {
                wordBoard[i] = ' ';
            } else {
                wordBoard[i] = '_';
            }
        }
        return wordBoard;
    }
    private static int countLetters(String word) {
        int count = 0;
        for(int i =0; i < word.length(); i++) {
            if(word.charAt(i) != ' ') {
                count++;
            }
        }
        return count;
    }
    private void startGameLoop(String word, char[] wordBoard, int numberOfLetters, int hangmanIndex, int lives, String[] hangmanAscii) {
        Scanner guessInputScanner = new Scanner(System.in);
        String guessInput;
        while (true) {
            if (numberOfLetters == correctLetters) {
                playerTwoWins();
                break;
            }
            System.out.print("Player 2 Provide me with a letter: ");
            guessInput = guessInputScanner.next().toLowerCase();
            if (guessInput.length() > 1) {
                System.out.println("Invalid Input, Please enter ONE letter");
                continue;
            }
            char letter = guessInput.charAt(0);
            if (!Character.isLetter(letter)) {
                System.out.println("Invalid Input, Please enter a valid letter");
                continue;
            } else if(guessedLetters.contains(letter)) {
                System.out.println("Invalid Input, Already Guessed letter");
                continue;
            }
            guessedLetters.add(letter);
            if(word.indexOf(letter) == -1) {
                System.out.println("The letter: " + letter + " was not in the word");
                lives -= 1;
                hangmanIndex++;
                System.out.println(hangmanAscii[hangmanIndex]);
                if(lives <= 0 ) {
                    playerOneWins();
                    break;
                }
                continue;
            }
            for(int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == letter) {
                    correctLetters += 1;
                    wordBoard[i] = letter;
                }
            }
            System.out.println(wordBoard);
        }
    }
    private static void playerOneWins() {
        System.out.println("Congratulations you beat player 2 YAYYYYY");
    }
    private static void playerTwoWins() {
        System.out.println("Congratulations you beat player 1 YAYYYYY");
    }
    private static String[] buildHangmanAscii() {
        return new String[] {
                """
 ____ 
|/   |
|
|
|
|
|
|_____
""",
                """
 ____ 
|/   |
|   (_)
|
|
|
|
|_____
""",
                """
 ____ 
|/   |
|   (_)
|    |
|    |
|
|
|_____
""",
                """
 ____ 
|/   |
|   (_)
|   \\|
|    |
|
|
|_____
""",
                """
 ____ 
|/   |
|   (_)
|   \\|/
|    |
|
|
|_____
""",
                """
 ____ 
|/   |
|   (_)
|   \\|/
|    |
|   /
|
|_____
""",
                """
 ____ 
|/   |
|   (_)
|   \\|/
|    |
|   / \\
|
|_____
""",
                """
 ____ 
|/   |
|   (_)
|   /|\\
|    |
|   | |
|
|_____
"""
        };
    }
}
