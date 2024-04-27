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

}
