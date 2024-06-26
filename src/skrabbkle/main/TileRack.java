package skrabbkle.main;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * The Tiles Rack Object Class
 */
public class TileRack extends TileBag {

    // create the tiles rack container
    private List<String> tilesRack = new ArrayList<String>();

    // maximum number of tiles in the tiles rack
    private int lengthOfTilesRack = 7;

    // number of tiles to collect
    private int numberOfTilesToReplace;

    /**
     * Randomly populates the tiles rack at the start of the game
     */
    public TileRack () {
        // randomly populates the tiles rack
        this.numberOfTilesToReplace = 7;
        this.populateTilesRack();
    }

    /**
     * Determines number of tiles to replace
     * @param nTiles holds number of tiles to replace
     */
    protected void setNumberOfTilesToReplace(int nTiles) {
        this.numberOfTilesToReplace = nTiles;
    }

    /**
     * remove all the tiles from the tiles rack containing the word played
     * @param wordPlayed word played by either human or computer player
     */
    protected void removeTilesFromTilesRack(String wordPlayed) {

        for (int i = 0; i < wordPlayed.length(); i++) {

            if (Character.isAlphabetic(wordPlayed.charAt(i))) {
                int index = 0;
                int newTilesRackSize = this.tilesRack.size();
                int tilesRackSize = newTilesRackSize;
                while (index < tilesRackSize && tilesRackSize == newTilesRackSize) {

                    //System.out.print(" " + this.tilesRack.get(index).charAt(1));
                    if (Character.compare(wordPlayed.charAt(i), this.tilesRack.get(index).charAt(1)) > 0) {
                        this.tilesRack.remove(index);
                        // get the latest size after removal
                        newTilesRackSize = this.tilesRack.size();
                    }

                    index++;
                }

            }

        }
    }

    /**
     * Does the actual popolation of the tiles rack
     */
    protected void populateTilesRack() {

        // set upper bound
        int upperBound = 16;

        // number of tiles to collect
        int numberOfTilesToCollect = this.numberOfTilesToReplace;

        // collect the tiles
        for (int i = 0; i < numberOfTilesToCollect; i++) {

            // randomly get tiles index to pick from tile bag
            int tileIndex = this.generateTileIndexNumber(upperBound);

            // calls method to pick tiles from the tiles bag
            String tile = this.pickTileFromTilesBag(tileIndex);

            // add to tiles Rack
            this.addToTilesRack(tile);
        }
    }

    /**
     * collect the tile from the tiles rack
     * @param tileIndex the index of the tile in the tiles rack
     * @return the index number of the tiles location
     */
    protected String getTiles(int tileIndex) {

        return tilesRack.get(tileIndex);
    }

    /**
     * add tiles (possibly from tiles bag) to tiles rack
     * @param tile the tile to add
     */
    protected void addToTilesRack(String tile) {
        this.tilesRack.add(tile);
    }

    /**
     * Generate random index number
     * @param upperBound the upper bound of the
     * @return
     */
    protected int generateTileIndexNumber (int upperBound) {

        // generates a random number between 0 and 16
        Random rand = new Random();

        // generate 7 random numbers to help get the 7 tiles
        return rand.nextInt(upperBound);
    }

    /**
     * Displays the tiles rack on stdout
     */
    protected void viewTilesRack() {

        // display current tiles rack to human
        // meant for the game class itself - Skrabbkle
        for (int i = 0; i < this.tilesRack.size(); i++ ) {
            if ( this.tilesRack.get(i) == null ) {
                ;
            }
            else {
                if ( i == this.tilesRack.size() - 1 ) {
                    System.out.print(this.tilesRack.get(i));
                }
                else {
                    System.out.print(this.tilesRack.get(i) + ", ");
                }

            }

        }
        System.out.println();
    }
}
