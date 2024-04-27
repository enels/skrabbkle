package skrabbkle.main;

/**
 * Player class to help both the human and computer player put tiles on the
 * board
 */
public class Player extends Board{

    public Player() throws Exception {
        super();
    }

    /**
     *
     * @param tilesPosition stores the tiles and its position to put it on the board
     * @return true (play successful) false (play unsuccessful)
     */
    public Boolean play(String [] tilesPosition) {
        // tiles and position
        // [0] - tiles
        // [1] -position

        // checks if it's a word in the wordlist
        if (super.placeTilesOnPosition(tilesPosition[0], tilesPosition[1]) == 1) {
            this.printMove(tilesPosition[0], tilesPosition[1]);
        }

        return true;
    }
}
