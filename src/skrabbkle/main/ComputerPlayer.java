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

    /**
     * create new tiles rack for player
     */
    private void setTilesRack () {

        this.tilesRack = new TileRack();

    }

    /**
     * Randomly pick tile from tile rack
     * @param tileIndex - index of tile
     * @return string - the tile that is picked
     */
    private String pickTilesRandomly(int tileIndex) {

        // get tile
        return this.tilesRack.getTiles(tileIndex);
    }

    /**
     * Generate random position for computer player
     */
    public void generatePosition () {

        Random rand = new Random();

        // generate the random alphabet from a - p
        int randIndex = rand.nextInt(16);

        String randomAlphabet = this.alphabets[randIndex];

        // generate random number
        int randomNumber = rand.nextInt(16);

        // generate random row and/or column for the number and alphabet
        // e.g 8g, g2, h4

        int row = rand.nextInt(2);
        // set position
        if ( row == 0 ) {

            this.position = randomAlphabet;
            this.position += String.valueOf(randomNumber);
        }
        else {
            this.position = String.valueOf(randomNumber);
            this.position += randomAlphabet;
        }
    }

    /**
     * generate engine to generate the random tiles and position
     */
    private void generateTilesAndPosition() {

        // randomly picks tiles from computer's tiles rack
        this.generateTiles();

        // generate position
        this.generatePosition();

        System.out.println("tiles picked: " + this.tilesPicked);
        // store tiles and position

        // extract only the characters from the word
        this.extractCharactersFromTilesPicked(this.tilesPicked);
        this.tilesAndPosition[0] = this.wordPlayed;
        this.tilesAndPosition[1] = this.position;
    }

    /**
     * extract character from tile picked
     * @param tilesPicked all the tiles that make up the word
     */
    private void extractCharactersFromTilesPicked (String tilesPicked) {

        for (int i = 0; i < this.tilesPicked.length(); i++ ) {

            if ( Character.isAlphabetic(this.tilesPicked.charAt(i))) {
                this.wordPlayed += this.tilesPicked.charAt(i);
            }
        }
    }

    public String play() {

        this.generateTilesAndPosition();
        // play
        while ( !super.play(this.tilesAndPosition) ) {

            this.generateTilesAndPosition();
        }

        // remove played word tiles from tiles bag
        this.score += gameTileBag.removePlayedTilesFromTilesBag(this.tilesAndPosition[0]);

        // remove tiles from tiles rack
        tilesRack.removeTilesFromTilesRack(tilesAndPosition[0]);

        // sets the number of tiles to populate the tiles rack with
        this.tilesRack.setNumberOfTilesToReplace(tilesAndPosition[0].length());

        // popualate back the tiles rack
        tilesRack.populateTilesRack();

        // for the printing of the most recently played word
        // meant for the game class itself - Skrabbkle
        return this.tilesAndPosition[0];
    }

    /**
     * view computer player tile rack
     */
    public void viewTileRack() {

        this.tilesRack.viewTilesRack();
    }

    /**
     * total number of tiles to generate
     * @return randomly number generated that indicates number of tiles to generate
     */
    private int totalNumberOfTilesToGenerate() {

        // upper bound
        int upperBound = 8;

        // pick a number of tiles to generate
        Random rand = new Random();

        // get number of tiles to generate
        return rand.nextInt(upperBound);
    }

    /**
     * method that does actual the generation of tiles
     */
    protected void generateTiles() {
        int totalTilesToGenerate = this.totalNumberOfTilesToGenerate();

        for ( int tileIndex = 0; tileIndex < totalTilesToGenerate; tileIndex++ ) {

            this.tilesPicked += this.pickTilesRandomly(tileIndex);

        }
    }
}
