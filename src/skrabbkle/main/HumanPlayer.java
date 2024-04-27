package skrabbkle.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

public class HumanPlayer extends Player{

    //    create human's tiles rack
    TileRack tilesRack;
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

    /**
     * create human player tile rack
     */
    private void setTilesRack() {
        this.tilesRack = new TileRack();
    }

    /**
     * collect tiles and position keyed in by human player
     */
    private void collectTilesAndPosition () {

        // ask human player to key in his/her tiles and the position on the board to put it
        String tilesPosition = this.promptForTilesAndPosition();

        // seperate tiles and position
        this.tilesAndPosition = tilesPosition.split(",", 2);
    }

    /**
     * prompt human player for tiles and position
     * @return the keyed in tiles and position
     */
    private String promptForTilesAndPosition() {

        String tilesPosition = this.collectInput();

        return tilesPosition;
    }

    /**
     * helps in collecting input
     * @return
     */
    private String collectInput()  {

        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            String humanInput = br.readLine();

            return humanInput;
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
