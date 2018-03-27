package comp1110.ass2.gui;

import comp1110.ass2.WarringStatesGame;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.stage.Stage;

/**
 * A very simple viewer for card layouts in the Warring States game.
 * <p>
 * NOTE: This class is separate from your main game class.  This
 * class does not play a game, it just illustrates various card placements.
 */
public class Viewer extends Application {

    GridPane gridPane = new GridPane();
    Image a0 = new Image("/resource/a0_lady_mi.png");
    Image a1 = new Image("/resource/a1_king_zhaoxiang.png");
    Image a2 = new Image("/resource/a2_duke_xiao.png");
    Image a3 = new Image("/resource/a3_lady_zhao.png");
    Image a4 = new Image("/resource/a4_king__hui.png");
    Image a5 = new Image("/resource/a5_king_zheng.png");
    Image a6 = new Image("/resource/a6_bai_qi.png");
    Image a7 = new Image("/resource/a7_shang_yang.png");
    Image b0 = new Image("/resource/b0_sun_bin.png");
    Image b1 = new Image("/resource/b1_zhong_wuyan.png");
    Image b2 = new Image("/resource/b2_lord_menchang.png");
    Image b3 = new Image("/resource/b3_king_jian.png");
    Image b4 = new Image("/resource/b4_queen_junwang.png");
    Image b5 = new Image("/resource/b5_king_xiang.png");
    Image b6 = new Image("/resource/b6_king_xuan.png");
    Image c0 = new Image("/resource/c0_wu_qi.png");
    Image c1 = new Image("/resource/c1_lord_chunshen.png");
    Image c2 = new Image("/resource/c2_fuchu.png");
    Image c3 = new Image("/resource/c3_king_ai.png");
    Image c4 = new Image("/resource/c4_king_you.png");
    Image c5 = new Image("/resource/c5_king_kaolie.png");
    Image d0 = new Image("/resource/d0_lian_po.png");
    Image d1 = new Image("/resource/d1_lord_pingyuan.png");
    Image d2 = new Image("/resource/d2_king_wuling.png");
    Image d3 = new Image("/resource/d3_king_xiaocheng.png");
    Image d4 = new Image("/resource/d4_li_mu.png");
    Image e0 = new Image("/resource/e0_marquess_ai.png");
    Image e1 = new Image("/resource/e1_han_fei.png");
    Image e2 = new Image("/resource/e2_king_an.png");
    Image e3 = new Image("/resource/e3_king_huanhui.png");
    Image f0 = new Image("/resource/f0_king_anxi.png");
    Image f1 = new Image("/resource/f1_lord_xinling.png");
    Image f2 = new Image("/resource/f2_marquess_wen.png");
    Image g0 = new Image("/resource/g0_king_xi.png");
    Image g1 = new Image("/resource/g1_prince_dan.png");
    Image z0 = new Image("/resource/z0_zhang_yi.png");

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
        // FIXME Task 4: implement the simple placement viewer
      //  boolean a = WarringStatesGame.isCardPlacementWellFormed(placement);

            String sub[] = new String[placement.length() / 3];
            int rown = 0, coln = 0,col=5;
            Image[][] grid = new Image[6][6];
            Image[][] grid2 =
                    {
                            {a0, a1, a2, a3, a4, a5, a6, a7},
                            {b0, b1, b2, b3, b4, b5, b6},
                            {c0, c1, c2, c3, c4, c5},
                            {d0, d1, d2, d3, d4},
                            {e0, e1, e2, e3},
                            {f0, f1, f2},
                            {g0, g1},
                            {z0}
                    };
            int count = 0;
            for (int x = 0; x < placement.length(); x += 3) {
                sub[count] = placement.substring(x, x + 3);
                System.out.println(sub[count]);
                count++;
            }
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


