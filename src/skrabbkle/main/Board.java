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

    /**
     * Load the current state of the board with tiles on it
     * @throws Exception - for entering wrong board type integer
     */
    public void loadBoard() throws Exception {

        int boardType = this.getBoardType();

        if (boardType == 0) {
            this.loadDefaultBoard();
        } else {
            System.out.println("Chose which Board to load: ");
            System.out.println("Board 1\nBoard 2\nBoard 3");
        }

        // catch the other board types
        if (boardType != 0) {
            System.out.println("Other boards are currently not available");
        }
    }

    /**
     * Store words from wordlist.txt into an array
     * @throws Exception for file not found, or damaged file, or
     * any errors that arises from inability to read file
     */
    private void storeWordListInArray() throws Exception {
        String wordListPath = "resources/wordlist.txt";

        FileReader wordListFile = new FileReader(wordListPath);
        BufferedReader br = new BufferedReader(wordListFile);

        String word;

        while ((word = br.readLine()) != null) {

            for (String wordListWord : wordList) {

                wordList.add(word);
            }
        }

    }

    /**
     * checks if current position choosen by computer or human player is free
     * @param position - position choosen
     * @return true if free, false otherwise
     */
    private Boolean checkIfPositionIsFree(String position) {

        // checks the first character for either alphabet or fullstop
        // collect the characters entered by human
        char firstCharacter = position.charAt(0);
        char secondCharacter = position.charAt(1);

        // check if that position contains the beginning of any word on the board
        // test for first character
        if (Character.isAlphabetic(firstCharacter)) {
            this.col = this.columnAlphabets.get(firstCharacter);

            // set direction of tiles
            this.directionOfTiles = 0; // vertical
        } else if (firstCharacter >= '0' && firstCharacter <= '9') {
            this.row = Integer.parseInt(String.valueOf(firstCharacter));

            // set direction of tiles
            this.directionOfTiles = 1; // horizontal
        }

        // test for second character
        if (Character.isAlphabetic(secondCharacter)) {
            this.col = this.columnAlphabets.get(secondCharacter);
        } else if (secondCharacter >= '0' && secondCharacter <= '9') {
            this.row = Integer.parseInt(String.valueOf(secondCharacter));
        }

        // check if position choosen by human or computer is free
        if (Character.isAlphabetic(defaultBoard[row][col].charAt(0))) {

            // start position is free
            return false;
        }

        return true;
    }

    /**
     * places the tile on the position located on the board
     * @param word - the word that is formed from the tiles
     * @param boardPosition - the position choosen
     * @return 1 if successful, 0 otherwise
     */
    protected int placeTilesOnPosition(String word, String boardPosition) {

        // check position to make sure it is empty
        Boolean positionIsFree = this.checkIfPositionIsFree(boardPosition);

        // if position is free
        if (positionIsFree) {
            int wordStatus = this.movement(word, this.directionOfTiles);

            // if all conditions are met and word found in dictionary
            if ( wordStatus == 1 ) {
                Boolean wordInWordList = checkWordInWordList();
                // if word is in word list and movement direction is vertical
                if ( wordInWordList ) {
                    // new word formed
                    // move vertically
                    // get the position to end on the board
                    int end = this.row + word.length();

                    int charIndex = 0; // index of each character in word
                    String strPositionValue; // string position on board
                    for (int row = this.row; row < end; ++row ) {
                        // string on position
                        strPositionValue = defaultBoard[row][this.col];

                        // check first character of current string in position
                        if ( strPositionValue.charAt(0) == '{' || strPositionValue.charAt(0) == '(' ) { // score digit found
                            // collect the character integer
                            this.scoreMultiplier += Integer.parseInt(String.valueOf(strPositionValue.charAt(1)));
                        }

                        // put tile on board
                        defaultBoard[row][this.col] = String.valueOf(word.charAt(charIndex));
                        charIndex += 1;
                    }

                    // put tile on board
                    defaultBoard[row][this.col] = String.valueOf(word.charAt(charIndex));
                    charIndex += 1;
                }
                // finish putting all tiles in word on board
                return 1;
            }

        }

        return 0;
    }

    /** Checks if word exists in word list
     *  @param word: the word played by human or computer
     * @param direction: the direction for next word formation
     *  return:        -1 - new word encrouching into other words
     *                  1 - all is good - possibility of a new word
     * */
    private int movement(String word, int direction) {

        Boolean wordToJoin = true;
        int nextRow = 0, nextCol = 0;

        // vertical movement
        if (direction == 0) {
            // checks for beginning/end of row
            if (this.row == 0 || this.row == 15) {
                nextRow = this.row;
            }
            else {
                // checks if there's a character on the previous row
                wordToJoin = Character.isAlphabetic(defaultBoard[this.row - 1][this.col].charAt(0));
                nextRow = this.row + 1;
            }

        }
        else {
            // checks for beginning/end of column
            if (this.col == 0 || this.col == 15) {
                nextCol = this.col;
            }
            else {
                // checks if there's a character on the previous column
                wordToJoin = Character.isAlphabetic(defaultBoard[this.row][this.col - 1].charAt(0));
                nextCol = this.col + 1;
            }

        }

        // get length of word to put on board
        int wordLength = word.length();

        if (!wordToJoin) {

            while ((wordLength != 0 || !wordToJoin) && newWord.length() <= 1 && nextRow < 16) {

                if (direction == 0) {
                    // if not at end of row
                    if ( nextRow != 15 && this.col != 15) {
                        wordToJoin = Character.isAlphabetic(defaultBoard[nextRow+1][this.col].charAt(0));
                        wordLength--;
                    }
                    else {
                        wordLength--;
                    }
                    // move to next row along the same column
                    nextRow++;
                } else {
                    // if not at end of column
                    if ( nextCol != 15 && this.row != 15 ) {
                        wordToJoin = Character.isAlphabetic(defaultBoard[this.row][nextCol+1].charAt(0));
                        wordLength--;
                    }
                    else {
                        wordLength--;
                    }
                    // move to next row along the same row
                    nextCol++;
                }

                // combine word with any possible alphabetic character seen on the way
                if (wordToJoin && direction == 0) {

                    this.newWord = word + defaultBoard[nextRow + 1][this.col];
                } else if (wordToJoin && direction == 1) {
                    this.newWord = word + defaultBoard[this.row][nextCol + 1];
                }
            }
            // checks if it encrouches into another word
            if (newWord.length() > 1) {
                return -1;
            }
        }
        // possibility of a new word to be formed
        return 1;
    }

    /**
     * check if word exists in word list
     * @return true if it exists, false otherwise
     */
    private Boolean checkWordInWordList () {

        // if new word formed, check if it exists in dictionary (word list)
        // iterate through the dictionary(word list) to see if the word exists
        for (String w : wordList) {
            // word found
            if (w.equals(this.newWord)) {
                return true;
            }
        }

        return false;
    }

    private void loadDefaultBoard() throws Exception {

        // display default board from file
        FileReader board_file = new FileReader(board_path);
        BufferedReader br = new BufferedReader(board_file);

        // display board
        int i, col_num = -1, row_num = 0;
        String s, coord_s = "";

        // loop through the loaded board
        while ((i = br.read()) != -1) {

            s = String.valueOf((char) i);
            // encounters new line
            if (s.equals("\n")) {

                // shift the character to the right if it's a "."
                if (coord_s.length() == 1) {
                    // shift it by 1 to the right
                    coord_s = " " + coord_s;
                }

                defaultBoard[row_num][col_num + 1] = coord_s;
                col_num = -1;
                row_num += 1;
                coord_s = "";
            }
            // still within a row(line)
            else {
                // maximum length of string on each coordinate reached
                if (coord_s.length() == 3 && (s.equals("}") || s.equals(")"))) {
                    ;
                } else {
                    coord_s += s;
                }
            }
            // still within a row(line)
            // but about to enter a new column position
            if (s.equals(" ")) {
                // enter next column
                col_num += 1;

                // checks the legnth of string at the current coordinate to see if it's
                // just one
                if (coord_s.length() == 2) {
                    // shift it by 1 to the right
                    coord_s = " " + coord_s;
                }

                // store the value in the row_coln index of the default board
                defaultBoard[row_num][col_num] = coord_s;

                // starts new string
                coord_s = "";
            }
        }

        this.printBoard();

        br.close();
        board_file.close();
    }

    public void printBoard() {

        // store the right row number
        int right_row_num;

        // print line space before the board
        System.out.println();

        System.out.println("   a  b   c  d  e   f  g  h  i   j  k  l  m   n  o  p");
        System.out.println();


        for (int row = 0; row < 16; row++) {

            // print the column row number on the left side
            System.out.print(row + 1 + " ");

            // print the Board tile value
            for (int col = 0; col < 16; col++) {
                System.out.print(defaultBoard[row][col]);
            }

            right_row_num = row + 1;
            // print the column row number on the left side
            System.out.print("\t\t" + right_row_num);

            System.out.println();

        }
        System.out.println();
        System.out.println("    a  b   c  d  e   f  g  h  i   j  k  l  m   n  o  p");
    }
}
