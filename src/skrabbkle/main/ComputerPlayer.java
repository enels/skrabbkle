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
}
