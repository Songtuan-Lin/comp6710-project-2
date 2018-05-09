package comp1110.ass2.gui;

import comp1110.ass2.Card;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Random;

public class Game extends Application {
    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    Card p = new Card();

    GridPane gridPane = new GridPane();
    private static final int VIEWER_WIDTH = 933;
    private static final int VIEWER_HEIGHT = 700;

    private static final String URI_BASE = "assets/";

    private final Group root = new Group();
    private final Group controls = new Group();
    TextField textField;
    static final String[] PLACEMENTS = {
            "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"
    };

    // FIXME Task 9: Implement a basic playable Warring States game in JavaFX

    // FIXME Task 11: Allow players of your Warring States game to play against your simple agent

    // FIXME Task 12: Integrate a more advanced opponent into your game

    void makePlacement(String placement) {



        String sub[] = new String[placement.length() / 3];
        int rown = 0, coln = 0,col=5;
        Random r = new Random();
        int selector = r.nextInt(20);
        Image[][] grid = new Image[6][6];

        Image[][] grid2 =
                {
                        {p.a0, p.a1, p.a2, p.a3, p.a4, p.a5, p.a6, p.a7},
                        {p.b0, p.b1, p.b2, p.b3, p.b4, p.b5, p.b6},
                        {p.c0, p.c1, p.c2, p.c3, p.c4, p.c5},
                        {p.d0, p.d1, p.d2, p.d3, p.d4},
                        {p.e0, p.e1, p.e2, p.e3},
                        {p.f0, p.f1, p.f2},
                        {p.g0, p.g1},
                        {p.z0}
                };

        sub = substr(PLACEMENTS[selector]);

        for (int y = 0; y < sub.length; y++) {
            if (sub[y].charAt(0) == 'z') {
                rown = 7;
                coln = 0;
            } else {
                rown = sub[y].charAt(0) - 'a';
                coln = sub[y].charAt(1) - '0';
            }
            if (sub[y].charAt(2) >= 65 && sub[y].charAt(2) <= 70) {
                int row = sub[y].charAt(2) % 13;
                grid[row][col] = grid2[rown][coln];
            }
            if (sub[y].charAt(2) >= 71 && sub[y].charAt(2) <= 76) {
                int row = (sub[y].charAt(2) % 71) ;
                grid[row][col-1] = grid2[rown][coln];
            }
            if (sub[y].charAt(2) >= 77 && sub[y].charAt(2) <= 82) {
                int row = (sub[y].charAt(2) % 77) ;
                grid[row][col-2] = grid2[rown][coln];
            }
            if (sub[y].charAt(2) >= 83 && sub[y].charAt(2) <= 88) {
                int row = (sub[y].charAt(2) % 83) ;
                grid[row][col-3] = grid2[rown][coln];
            }
            if (sub[y].charAt(2) >= 89 && sub[y].charAt(2) <= 90) {
                int row = (sub[y].charAt(2) % 89);
                grid[row][col-4] = grid2[rown][coln];
            }
            if (sub[y].charAt(2) >= 48 && sub[y].charAt(2) <= 51) {
                int row = (sub[y].charAt(2) % 23);
                grid[row][col-4] = grid2[rown][coln];
            }
            if (sub[y].charAt(2) >= 52 && sub[y].charAt(2) <= 57) {
                int row = (sub[y].charAt(2) % 52);
                grid[row][col-5] = grid2[rown][coln];
            }


        }
        gridPane.setHgap(2);
        gridPane.setVgap(2);
        gridPane.setStyle("-fx-background-color: White; -fx-border-color: Black ");
        gridPane.setPadding(new Insets(1,1,1,1));
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                ImageView imageView = new ImageView(grid[x][y]);
                imageView.setFitWidth(100);
                imageView.setFitHeight(100);
                gridPane.add(imageView, y, x);
            }
        }

    }

    private String[] substr(String placement)
    {
        int count =0;
        String sub[] = new String[placement.length()/3];
        for (int x = 0; x < placement.length(); x += 3) {
            sub[count] = placement.substring(x, x + 3);
            count++;
        }
        return sub;
    }
    private void makeControls() {
        Label label1 = new Label("Player Move:");
        textField = new TextField();
        textField.setPrefWidth(150);
        Button button = new Button("Play");
        Button newGame = new Button("New Game");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
  //              gridPane.getChildren().clear();
  //              makePlacement(textField.getText());
                textField.clear();
            }
        });

        newGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gridPane.getChildren().clear();
                makePlacement(textField.getText());
                textField.clear();
            }
        });
        HBox hb = new HBox();
        HBox hb1 = new HBox();
        hb.getChildren().addAll(label1, textField, button);
        hb1.getChildren().add(newGame);
        hb1.setSpacing(10);
        hb1.setLayoutX(800);
        hb1.setLayoutY(50);
        hb.setSpacing(10);
        hb.setLayoutX(130);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);
        controls.getChildren().add(hb1);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Warring States Viewer");

        gridPane.setLayoutX(100);
        gridPane.setLayoutY(10);

        root.getChildren().add(gridPane);

        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);
        root.getChildren().add(controls);


        makeControls();
        primaryStage.setScene(scene);
        primaryStage.show();

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