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
    public TilesRack () {
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

}
