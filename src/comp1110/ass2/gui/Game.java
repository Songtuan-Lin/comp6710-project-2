package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class Game extends Application {
    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;

    // FIXME Task 9: Implement a basic playable Warring States game in JavaFX

    // FIXME Task 11: Allow players of your Warring States game to play against your simple agent

    // FIXME Task 12: Integrate a more advanced opponent into your game

    @Override
    public void start(Stage primaryStage) throws Exception {

    }


    public static void winner() {
        //count the number of flags and cards of each player, decide who is the winner
    }

    public void setup() {
        //check if the placement is valid and  make it visible
    }

    public void nextStep() {
        //decide who's turn to play
        //decide if the move is legal
        //decide cards and flags collected for each player
        //decide if the game has ended and who wins
    }
}