package skrabbkle.main;

import java.util.Random;
import java.util.ArrayList;

public class ComputerPlayer extends Player {

    //    create computer's tiles rack
    TileRack tilesRack;

    // tiles picked to be played
    String tilesPicked = "";
    String wordPlayed = "";

    // position played
    String position;

    // tile and position played
    String[] tilesAndPosition;

    // store the score
    private int score;

    private TileBag gameTileBag;

    // possible alphabets to choose from
    String [] alphabets;

    /**
     * Constructs the tiles bag and tile and position of the tile
     * @param tileBag
     * @throws Exception
     */
    public ComputerPlayer(TileBag tileBag) throws Exception {

//       store the game's tile bag
        this.gameTileBag = tileBag;
        this.tilesAndPosition = new String[8];
        this.setTilesRack();

        // all possible alphabets
        this.alphabets = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p"};

        this.setScore();
    }

    /**
     * set computer player score
     */
    private void setScore() {
        this.score = 0;
    }

    /**
     * get computer player score
     * @return the score
     */
    public int getScore() {
        return this.score;
    }
}
