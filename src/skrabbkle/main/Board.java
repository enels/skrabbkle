package skrabbkle.main;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Defines the Board Object class with all the
 */
public class Board {

    // stores the default board without tiles on it
    private String[][] defaultBoard = new String[16][16];

    // wordlist array
    private ArrayList<String> wordList = new ArrayList<String>();

    // direction of tiles - 0: vertical, 1: horizontal
    private int directionOfTiles;

    // board type
    private int boardType;

    // store any score multiplier found on board
    int scoreMultiplier = 1;

    // new word formed
    private String newWord = "";

    // column alphabets
    private Map<Character, Integer> columnAlphabets = new HashMap<Character, Integer>();

    // board row and column position of start of word
    private int row = 0, col = 0;

    // board path
    String board_path = "resources/defaultBoard.txt";

    public Board() throws Exception {

        // load the default board when game starts
        this.loadBoard();

        //store words in word list in an array list
        this.storeWordListInArray();

        // initialize the column alphabet
        columnAlphabets.put('a', 0);
        columnAlphabets.put('b', 1);
        columnAlphabets.put('c', 2);
        columnAlphabets.put('d', 3);
        columnAlphabets.put('e', 4);
        columnAlphabets.put('f', 5);
        columnAlphabets.put('g', 6);
        columnAlphabets.put('h', 7);
        columnAlphabets.put('i', 8);
        columnAlphabets.put('j', 9);
        columnAlphabets.put('k', 10);
        columnAlphabets.put('l', 11);
        columnAlphabets.put('m', 12);
        columnAlphabets.put('n', 13);
        columnAlphabets.put('o', 14);
        columnAlphabets.put('p', 15);
    }

    /**
     * Set the board type choosed by human player
     * @param btype - an int to indicate the board type
     */
    public void setBoardType(int btype) {
        // get the human choses board type
        this.boardType = btype;
    }

    /**
     * get board typed that was chosed
     * @return the type of board: 0 - default board
     */
    private int getBoardType() {
        // return board type
        return this.boardType;
    }

    /**
     * get the new word formed
     * @return newly formed word
     */
    public String getNewWordFormed() {
        // return new word formed
        return this.newWord;
    }
}
