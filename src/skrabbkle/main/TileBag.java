package skrabbkle.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Collection;

public class TileBag {

    // declare the tile bag
    private Map<String, Integer> tileBag = new HashMap<String, Integer>();
    private ArrayList<String> tiles;
    private ArrayList<Integer> tileCount;

    public TileBag() {

        tileBag.put("[A1]", 8);
        tileBag.put("[B3]", 2);
        tileBag.put("[C3]", 2);
        tileBag.put("[D2]", 4);
        tileBag.put("[E1]", 10);
        tileBag.put("[F4]", 3);
        tileBag.put("[G2]", 4);
        tileBag.put("[H4]", 3);
        tileBag.put("[I1]", 8);
        tileBag.put("[J9]", 1);
        tileBag.put("[K6]", 1);
        tileBag.put("[L1]", 4);
        tileBag.put("[M3]", 2);
        tileBag.put("[N1]", 7);
        tileBag.put("[O1]", 7);
        tileBag.put("[P3]", 2);
        tileBag.put("[Q12]", 1);
        tileBag.put("[R1]", 6);
        tileBag.put("[S1]", 4);
        tileBag.put("[T1]", 6);
        tileBag.put("[U1]", 5);
        tileBag.put("[V4]", 2);
        tileBag.put("[W4]", 1);
        tileBag.put("[X9]", 1);
        tileBag.put("[Y5]", 2);
        tileBag.put("[Z11]", 1);

        // create a set of keys from the tiles
        Set<String> keySet = tileBag.keySet();

        // creating an arrayList of keys
        tiles = new ArrayList<String>(keySet);

        // Getting collections of values from the tile bag Tiles Count
        Collection<Integer> values = tileBag.values();

        // creating an array list of values
        tileCount = new ArrayList<Integer>(values);
    }

    /**
     * get the tiles bag
     */
    protected Map<String, Integer> getTilesBag () {

        return this.tileBag;
    }

    /**
     * Picks tile from tile bag
     * @param tileIndexNumber
     * @return the tile that was picked
     */
    protected String pickTileFromTilesBag (int tileIndexNumber) {

        // get the actual tile
        String tileChosen = tiles.get(tileIndexNumber);

        // checks if the required tile is still available
        Boolean isAvailable = checkAvailability(tileChosen);

        // if tile is still available
        if ( isAvailable ) {

            // reduce the tileCount by one
            this.tileCount.set(tileIndexNumber, tileBag.get(tileChosen) - 1);

            // reduce the tile in the tileBag itself
            reduceTilesByOne(tileChosen);

            return tiles.get(tileIndexNumber);
        }
        else {
            // delete tile from tile bag
            tileBag.remove(tiles.get(tileIndexNumber));
        }

        return "";
    }

    /**
     * Check if the tile to play is available on the tile bag
     * @param tile the choosen tile
     * @return true if available, false otherwise
     */
    private Boolean checkAvailability (String tile) {

        // get number of tile available
        int remainingTiles = tileBag.get(tile);

        if ( remainingTiles != 0 ) {*
            // reduce number of tiles
            return true;
        }

        return false;
    }

    /**
     * count number of tiles reamining in tiles bag
     * @return
     */
    public int countTilesInTilesBag(){
        return tiles.size();
    }

    /**
     * Remove the played tile from tiles bag
     * @param wordPlayed the word that was played
     * @return the score of the current player
     */
    protected int removePlayedTilesFromTilesBag(String wordPlayed) {

        int score = 0;

        for (Map.Entry<String, Integer> entry : tileBag.entrySet()) {

            // iterate through each character of the played word
            for ( int i = 0; i < wordPlayed.length(); i++ ) {

                // remove the charcter tiles by one
                if ( entry.getKey().charAt(1) == wordPlayed.charAt(i) ) {

                    // calculate the score
                    // get tile score
                    score += entry.getKey().charAt(2);
                    //this.reduceTilesByOne(entry.getKey());
                }
            }

        }

        return score;
    }
}
