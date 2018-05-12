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

/**
 * A very simple viewer for card layouts in the Warring States game.
 * <p>
 * NOTE: This class is separate from your main game class.  This
 * class does not play a game, it just illustrates various card placements.
 */
public class Viewer extends Application {
    Card p = new Card();                                                //creating an object of the card class to display the cards.

    GridPane gridPane = new GridPane();
    private static final int VIEWER_WIDTH = 933;
    private static final int VIEWER_HEIGHT = 700;

    private static final String URI_BASE = "assets/";

    private final Group root = new Group();
    private final Group controls = new Group();
    TextField textField;

    /**
     * Draw a placement in the window, removing any previously drawn one
     *
     * @param placement A valid placement string
     */
    void makePlacement(String placement) {

        //example String for testing : "a2Aa3Gc4Hb5Ed0Sg04g19e02a7Uz0V"
        // FIXME Task 4: implement the simple placement viewer
      //  boolean a = WarringStatesGame.isCardPlacementWellFormed(placement);

            String sub[] = new String[placement.length() / 3];
            int rown = 0, coln = 0,col=5;
            Image[][] grid = new Image[6][6];

    //Creating a grid of images to be used.
        Image[][] grid2 =
                {
                        {p.a0, p.a1, p.a2, p.a3, p.a4, p.a5, p.a6, p.a7},
                        {p.b0, p.b1, p.b2, p.b3, p.b4, p.b5, p.b6},
                        {p.c0, p.c1, p.c2, p.c3, p.c4, p.c5},
                        {p.d0, p.d1, p.d2, p.d3, p.d4},
                        {p.e0, p.e1, p.e2, p.e3},
                        {p.f0, p.f1, p.f2},
                        {p.g0, p.g1},
                        {p.z9}
                };

            sub = substr(placement);

    // Checking the position based on the placement string and assigning the images to the board.
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

    //Setting the gridpane structure with the veritical and horizontal distances.
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

    /**
     * @param placement
     * @return array of strings containing each character and it's position
     */
    public String[] substr(String placement)
        {
            int count =0;
            String sub[] = new String[placement.length()/3];
            for (int x = 0; x < placement.length(); x += 3) {
                sub[count] = placement.substring(x, x + 3);
                count++;
            }
            return sub;
        }



    /**
     * Create a basic text field for input and a refresh button.
     */
    private void makeControls() {
        Label label1 = new Label("Placement:");
        textField = new TextField();
        textField.setPrefWidth(300);
        Button button = new Button("Refresh");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                gridPane.getChildren().clear();
                makePlacement(textField.getText());
                textField.clear();
            }
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField, button);
        hb.setSpacing(10);
        hb.setLayoutX(130);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);
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
}


