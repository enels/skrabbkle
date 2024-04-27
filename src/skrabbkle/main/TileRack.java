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
}
