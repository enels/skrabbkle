package skrabbkle.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

public class HumanPlayer extends Player{

    //    create human's tiles rack
    TilesRack tilesRack;
    private TileBag gameTileBag;

    // word played by human
    private String wordPlayed;

    // store users entered tiles and position
    String [] tilesAndPosition;

    // player's score
    private int score;

    public HumanPlayer(TileBag tileBag) throws Exception {

        this.gameTileBag = tileBag;
        this.setTilesRack();
        this.setScore();

    }

    /**
     * set human player score
     */
    private void setScore() {

        this.score = 0;
    }

    /**
     * get human player score
     * @return the score
     */
    public int getScore() {

        return this.score;
    }
}
