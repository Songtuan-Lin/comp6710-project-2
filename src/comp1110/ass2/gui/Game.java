package comp1110.ass2.gui;


import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import comp1110.ass2.Card;
import java.util.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Random;
import comp1110.ass2.WarringStatesGame;



import static comp1110.ass2.WarringStatesGame.*;
import static javafx.application.Platform.exit;

public class Game extends Application {
    static int num_players = 0;

    Card p = new Card();
    String getMove;
    String getBotMove;
    GridPane gridPane = new GridPane();
    private static final int VIEWER_WIDTH = 933;
    private static final int VIEWER_HEIGHT = 700;
    String boardMatrix[][];
    static String playerName[];
    VBox flags;
    public Button button = new Button("Play");
    private static final String URI_BASE = "assets/";
    String placement1,setup;
    private static final Pane root = new Pane();
    private final Pane introroot = new Pane();
    private final Group controls = new Group();
    static int flag[] = new int[7];
    static int botFlag[][] = new int[7][7];
    public static int cardCount[] = new int[7];
    static String roundGains[] = new String[7];
    TextField textField,player1,player2,player3,player4;
    Label qinF  = new Label(" ");
    Label qiF    = new Label(" ");
    Label chuF   = new Label(" ");
    Label zhaoF  = new Label(" ");
    Label hanF   = new Label(" ");
    Label weiF   = new Label(" ");
    Label yanF   = new Label(" ");
    int mCount = 0;
    int titanCount = 0;
    static Label winnerID = new Label(" ");;
    String moveSequence = "";
    String botMoveSequence = "";
    static int winner=0;
    public int player =-1;
    boolean check = true;
    boolean botPlay = true;
    boolean titan = false;
    Thread t;
    GridPane pane = new GridPane();
    static Text declareWinner = new Text(45,350,"");

    /*Declaring all the audio clips to be used*/
    AudioClip click1 = new AudioClip("http://www.wavlist.com/soundfx/020/clock-tick1.wav");
    AudioClip scene1player = new AudioClip(this.getClass().getResource("/resource/Immigrant.mp3").toString());
    AudioClip scene2player = new AudioClip(this.getClass().getResource("/resource/z_avengers.mp3").toString());
    AudioClip lokiIntro = new AudioClip(this.getClass().getResource("/resource/LokiIntro.wav").toString());
    AudioClip lokiIntro1 = new AudioClip(this.getClass().getResource("/resource/LokiIntroTalk.wav").toString());
    AudioClip loki1 = new AudioClip(this.getClass().getResource("/resource/Loki1.wav").toString());
    AudioClip loki2 = new AudioClip(this.getClass().getResource("/resource/Loki2.wav").toString());
    AudioClip loki3 = new AudioClip(this.getClass().getResource("/resource/Loki3.wav").toString());
    AudioClip loki4 = new AudioClip(this.getClass().getResource("/resource/Loki4.wav").toString());
    AudioClip loki5 = new AudioClip(this.getClass().getResource("/resource/Loki5.wav").toString());
    AudioClip lokiEnding = new AudioClip(this.getClass().getResource("/resource/LokiEnding.wav").toString());
    AudioClip click2 = new AudioClip(this.getClass().getResource("/resource/clicksound.mp3").toString());

    Label sp1 = new Label();
    Label sp2 = new Label();
    Label sp3 = new Label();
    Label sp4 = new Label();
    HBox scoreLabels = new HBox();
    Text scores1 = new Text();
    Text scores2 = new Text();
    Text scores3 = new Text();
    Text scores4 = new Text();
    Text cards1 = new Text();
    Text cards2 = new Text();
    Text cards3 = new Text();
    Text cards4 = new Text();
    HBox scores = new HBox();

    /*Randomize the starting placement string*/
    static final String[] PLACEMENTS = {
            "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"
    };

    /*Array of image objects*/
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

    /*Declare all the positions on the baord in an array*/
    static final char c[] = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9'};


    /**
     * Method to modify the Board after every move
     * @param placement
     * Author: Rufus Raja (u6275198)
     */
    void makePlacement(String placement) {

        button.setDisable(false);
        textField.setDisable(false);
        textField.requestFocus();
        String sub[] = new String[placement.length() / 3];
        int rown = 0, coln = 0,col=5;
        Random r = new Random();
        int selector = r.nextInt(20);
        Image[][] grid = new Image[6][6];
        sub = substr(placement,3);
        /*Get the grid for the image changes in the placement string*/
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
        /*Defining the grid properties*/
        gridPane.setHgap(2);
        gridPane.setVgap(2);
        gridPane.autosize();
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
        grid = null;
    }

    /**
     * Substring function that can break the placement string to required number of characters, and returns a String array
     * @param placement
     * @param pos
     * @return
     * Author: Rufus Raja (u6275198)
     */
    private String[] substr(String placement,int pos)
    {
        int count =0;
        String sub[] = new String[placement.length()/pos];
        for (int x = 0; x < placement.length(); x += pos) {
            sub[count] = placement.substring(x, x + pos);
            count++;
        }
        return sub;
    }

    int numOfTimes = 0;

    /**
     * Create the elements for the game movements
     * Author: Rufus Raja (u6275198)
     */
    private void makeControls() {
        Label playerMoveLabel = new Label("Player Move:");
        playerMoveLabel.setTextFill(Color.WHITE);
        textField = new TextField();
        textField.setPrefWidth(150);

        Button newGame = new Button("New Game");
        /*Check if the button was pressed*/
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if(!textField.getText().equals("") || !textField.getText().equals(" "))
                    singleMove();
            }
        });
        /*Check if the Enter key was pressed*/
        textField.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent ke)
            {
                if (ke.getCode().equals(KeyCode.ENTER) && (!textField.getText().equals("") || !textField.getText().equals(" ")))
                {
                    singleMove();
                }
            }
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(playerMoveLabel, textField, button);
        hb.setSpacing(10);
        hb.setLayoutX(175);
        hb.setLayoutY(VIEWER_HEIGHT-50);
        controls.getChildren().add(hb);
    }

    private void singleMove() {
        click1.setVolume(0.3);
        click1.play();
        mCount=mCount+1;
        numOfTimes = 0;
        /*Modulate the audio based on the moves*/
        if(mCount%5 == 0 && numOfTimes == 0) {
            loki1.play();
            numOfTimes = 1;
        }
        if(mCount%7 == 0 && numOfTimes == 0){
            loki2.play();
            numOfTimes = 1;
        }
        if(mCount%9 == 0 && numOfTimes == 0){
            loki3.play();
            numOfTimes = 1;
        }
        if(mCount%11 == 0 && numOfTimes == 0){
            loki4.play();
            numOfTimes = 1;
        }
        if(mCount%13 == 0 && numOfTimes == 0){
            loki5.play();
            numOfTimes = 1;
        }
        getMove = textField.getText();
        String validCheck  = textField.getText();
        if (getMove.length() > 1)
        {
            textField.clear();
        }
        else
        {
            nextStep();
        }
        /*Executes the AI moves*/
        if(playerName[1].equals("Omega(AI)") && check && botPlay)
        {
            button.setDisable(true);
            textField.setDisable(true);
            getMove = alpha_beta_search(placement1, boardMatrix) + "";//Call the advanced AI
            delay(2000, new Runnable(){ public void run(){ nextStep();} });//Delay execution by 2 secs
        }
        textField.clear();

    }

    /**
     * Simple agent moves
     * @return
     * Author: Rufus Raja (u6275198)
     */
    public char BotMove()
    {
        return generateMove(placement1);
    }

    /**
     * Method to create delays in the processing
     * @param delayMs
     * @param toRun
     * Author: Rufus Raja (u6275198)
     */
    public static void delay(long delayMs, Runnable toRun){
        Thread t = new Thread(new Runnable(){
            public void run(){
                try { Thread.sleep(delayMs); }catch(InterruptedException ignored){}
                Platform.runLater(toRun);
            }
        });
        t.setDaemon(true);
        t.start();
    }

    /**
     * Declaring the main components for the board and the application design
     * @param primaryStage
     * @throws Exception
     * Author: Rufus Raja (u6275198)
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("The Comic Wars");
        qinF.setTextFill(Color.WHITE.brighter());
        qiF.setTextFill(Color.WHITE.brighter());
        chuF.setTextFill(Color.WHITE.brighter());
        zhaoF.setTextFill(Color.WHITE.brighter());
        hanF.setTextFill(Color.WHITE.brighter());
        weiF.setTextFill(Color.WHITE.brighter());
        yanF.setTextFill(Color.WHITE.brighter());
        scoreLabels.setLayoutX(760);
        scoreLabels.setLayoutY(470);
        VBox p1Score = new VBox();
        VBox p2Score = new VBox();
        VBox p3Score = new VBox();
        VBox p4Score = new VBox();
        p1Score.setLayoutX(693);
        p1Score.setLayoutY(462);
        p2Score.setLayoutX(753);
        p2Score.setLayoutY(462);
        p3Score.setLayoutX(813);
        p3Score.setLayoutY(462);
        p4Score.setLayoutX(873);
        p4Score.setLayoutY(462);
        Label scoreHead = new Label("SCORES");
        scoreHead.setTextFill(Color.WHITE);
        scoreHead.setFont(Font.font("Verdana", FontWeight.BOLD, 12));;
        scoreHead.setLayoutX(854);
        scoreHead.setLayoutY(444);
        Label flagCount = new Label("Teams: ");
        Label cardCount = new Label("Cards: ");
        flagCount.setLayoutX(638);
        flagCount.setLayoutY(484);
        cardCount.setLayoutX(638);
        cardCount.setLayoutY(507);
        cardCount.setFont(Font.font("Arial Black", FontWeight.BOLD, 10));
        flagCount.setFont(Font.font("Arial Black", FontWeight.BOLD, 10));
        cardCount.setTextFill(Color.WHITE);
        flagCount.setTextFill(Color.WHITE);
        sp1.setTextFill(Color.WHITE.brighter());
        sp2.setTextFill(Color.WHITE.brighter());
        sp3.setTextFill(Color.WHITE.brighter());
        sp4.setTextFill(Color.WHITE.brighter());
        scores1.setFill(Color.WHITE.brighter());
        scores2.setFill(Color.WHITE.brighter());
        scores3.setFill(Color.WHITE.brighter());
        scores4.setFill(Color.WHITE.brighter());
        scores1.setFont(Font.font("Arial Black", FontWeight.BOLD, 14));
        scores2.setFont(Font.font("Arial Black", FontWeight.BOLD, 14));
        scores3.setFont(Font.font("Arial Black", FontWeight.BOLD, 14));
        scores4.setFont(Font.font("Arial Black", FontWeight.BOLD, 14));
        cards1.setFill(Color.WHITE.brighter());
        cards2.setFill(Color.WHITE.brighter());
        cards3.setFill(Color.WHITE.brighter());
        cards4.setFill(Color.WHITE.brighter());
        cards1.setFont(Font.font("Arial Black", FontWeight.BOLD, 14));
        cards2.setFont(Font.font("Arial Black", FontWeight.BOLD, 14));
        cards3.setFont(Font.font("Arial Black", FontWeight.BOLD, 14));
        cards4.setFont(Font.font("Arial Black", FontWeight.BOLD, 14));
        p1Score.getChildren().addAll(sp1,scores1,cards1);
        p2Score.getChildren().addAll(sp2,scores2,cards2);
        p3Score.getChildren().addAll(sp3,scores3,cards3);
        p4Score.getChildren().addAll(sp4,scores4,cards4);
        p1Score.setSpacing(3);
        p2Score.setSpacing(3);
        p3Score.setSpacing(3);
        p4Score.setSpacing(3);
        scoreLabels.setSpacing(20);
        scores.setSpacing(60);
        gridPane.setLayoutX(15);
        gridPane.setLayoutY(10);
        root.getChildren().add(gridPane);
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);
        Scene intro = new Scene(introroot, VIEWER_WIDTH,VIEWER_HEIGHT);
        /*Declaring the background images and modifying the background for the scenes*/
        intro.getStylesheets().addAll(this.getClass().getResource("/resource/style.css").toExternalForm());
        Image img = new Image("/resource/z9_background.jpg");
        Image img2 = new Image("/resource/z10_background.jpg");
        introroot.setBackground(new Background(new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true))));
        root.setBackground(new Background(new BackgroundImage(img2,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true))));
        ChoiceBox player = new ChoiceBox<>();
        HBox selection = new HBox();
        HBox playerIDs = new HBox();
        VBox kingdoms = new VBox();
        Label p1   = new Label("Player Names");
        p1.setTextFill(Color.WHITE);
        p1.setFont(Font.font("Arial Black", FontWeight.BOLD, 14));
        kingdoms.setSpacing(30);
        kingdoms.setLayoutX(750);
        kingdoms.setLayoutY(70);
        playerIDs.setSpacing(20);
        playerIDs.setLayoutX(740);
        playerIDs.setLayoutY(10);
        Button startGame = new Button("Start Game");
        Button help = new Button("Instructions");
        Button mutemusic = new Button("Music On/Off");
        HBox sounds = new HBox();
        sounds.getChildren().addAll(mutemusic);
        sounds.setSpacing(40);
        sounds.setLayoutX(15);
        sounds.setLayoutY(650);
        startGame.setLayoutX(400);
        startGame.setLayoutY(550);
        help.setLayoutX(500);
        help.setLayoutY(550);
        player.setItems(FXCollections.observableArrayList(
                "1 Player","2 Players","3 Players","4 Players"));
        player.setValue("1 Player");
        selection.setLayoutX(VIEWER_HEIGHT/2);
        selection.setLayoutY(VIEWER_WIDTH/2);
        player.setLayoutY(250);
        player.setLayoutX(445);
        VBox playerLabels = new VBox();
        Label player1name = new Label("Player 1");
        Label player2name = new Label("Player 2");
        Label player3name = new Label("Player 3");
        Label player4name = new Label("Player 4");
        player1name.setTextFill(Color.WHITE);
        player2name.setTextFill(Color.WHITE);
        player3name.setTextFill(Color.WHITE);
        player4name.setTextFill(Color.WHITE);
        VBox playernames = new VBox();
        player1 = new TextField();
        player2 = new TextField();
        player3 = new TextField();
        player4 = new TextField();
        player2.setDisable(true);
        player3.setDisable(true);
        player4.setDisable(true);
        playerLabels.setLayoutX(355);
        playerLabels.setLayoutY(300);
        playerLabels.setSpacing(30);
        playerLabels.getChildren().addAll(player1name,player2name,player3name,player4name);
        playernames.setLayoutX(415);
        playernames.setLayoutY(300);
        playernames.getChildren().addAll(player1,player2,player3,player4);
        /*Limit the name field to 9 characters*/
        player1.setOnKeyTyped(event ->{
            int maxCharacters = 8;
            if(player1.getText().length() > maxCharacters) event.consume();
        });
        player2.setOnKeyTyped(event ->{
            int maxCharacters = 8;
            if(player2.getText().length() > maxCharacters) event.consume();
        });
        player3.setOnKeyTyped(event ->{
            int maxCharacters = 8;
            if(player3.getText().length() > maxCharacters) event.consume();
        });
        player4.setOnKeyTyped(event ->{
            int maxCharacters = 8;
            if(player4.getText().length() > maxCharacters) event.consume();
        });
        playernames.setSpacing(20);
        player.setMaxSize(200,200);
        introroot.getChildren().add(playernames);
        introroot.getChildren().add(playerLabels);
        root.getChildren().add(controls);
        root.getChildren().add(playerIDs);
  //      root.getChildren().add(flags);
        root.getChildren().add(winnerID);
        root.getChildren().add(declareWinner);
        root.getChildren().add(sounds);
        root.getChildren().addAll(p1Score,p2Score,p3Score,p4Score);
        root.getChildren().add(scoreHead);
        root.getChildren().addAll(flagCount,cardCount);
        scene1player.setVolume(0.2);
        scene1player.play();
        introroot.getChildren().add(player);
        introroot.getChildren().add(startGame);
        introroot.getChildren().add(help);

        /*based on the selection of number of players enable or disable the textfields for names*/
        player.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(player.getValue() == "1 Player")
                {
                    player2.setDisable(true);
                    player3.setDisable(true);
                    player4.setDisable(true);
                }
                if(player.getValue() == "2 Players")
                {
                    player2.setDisable(false);
                    player3.setDisable(true);
                    player4.setDisable(true);
                }
                else if(player.getValue() == "3 Players")
                {
                    player2.setDisable(false);
                    player3.setDisable(false);
                    player4.setDisable(true);
                }
                else if(player.getValue() == "4 Players")
                {
                    player2.setDisable(false);
                    player3.setDisable(false);
                    player4.setDisable(false);
                }
            }
        });
        playerName = new String[4];
        playerName[0] = "Player 1";
        playerName[1] = "Player 2";
        playerName[2] = "Player 3";
        playerName[3] = "Player 4";
        sp1.setText("Player 1");
        sp2.setText("Player 2");
        sp3.setText("Player 3");
        sp4.setText("Player 4");
        /*Initialization and setting up of steps when the game starts*/
        startGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                createGrid();
                click1.play();
                scene1player.stop();// Stop scene 1 music
                lokiIntro.play(); // Play intro Loki message
                scene2player.setVolume(0.1); //Reduce the volume
                scene2player.play();//Start scene 2 music
                num_players = Integer.parseInt(player.getValue().toString().substring(0,1));
                if(!player1.getText().equals("")) {
                    playerName[0] = player1.getText();
                    sp1.setText(playerName[0]);
                }
                /*Based on the number of players adjust the position of Scores title and the names displayed*/
                if(num_players == 1)
                {
                    playerName[1] = "Omega(AI)";
                    num_players = 2;
                    sp2.setText("Omega(AI)");
                    sp3.setText(" ");
                    sp4.setText(" ");
                    scoreHead.setLayoutX(714);
                    scoreHead.setLayoutY(435);
                }
                if(num_players == 2)
                {
                    sp3.setText(" ");
                    sp4.setText(" ");
                    scoreHead.setLayoutX(714);
                    scoreHead.setLayoutY(435);
                    if(!player2.getText().equals("")) {
                        playerName[1] = player2.getText();
                        sp2.setText(playerName[1]);
                    }
                }
                if(num_players == 3)
                {
                    sp4.setText(" ");
                    scoreHead.setLayoutX(742);
                    scoreHead.setLayoutY(435);
                    if(!player2.getText().equals("") || !player3.getText().equals("")) {
                        playerName[1] = player2.getText();
                        playerName[2] = player3.getText();
                        sp2.setText(playerName[1]);
                        sp3.setText(playerName[2]);
                    }
                }
                if(num_players == 4)
                {
                    scoreHead.setLayoutX(772);
                    scoreHead.setLayoutY(435);
                    if(!player2.getText().equals("") || !player3.getText().equals("") || !player4.getText().equals("")) {
                        playerName[1] = player2.getText();
                        playerName[2] = player3.getText();
                        playerName[3] = player4.getText();
                        sp2.setText(playerName[1]);
                        sp3.setText(playerName[2]);
                        sp4.setText(playerName[3]);
                    }
                }
                /*Initialize the gains per person to start*/
                for(String s:roundGains)
                {
                    s = " ";
                }
                /*Used to toggle the music controls*/
                mutemusic.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if(scene2player.isPlaying())
                        {
                            scene2player.stop();
                        }
                        else
                            scene2player.play();
                    }
                });
                playerIDs.getChildren().addAll(p1);
                setup();
                textField.clear();
                qinF.setLayoutX(743);
                qinF.setLayoutY(60);
                qiF.setLayoutX(743);
                qiF.setLayoutY(115);
                chuF.setLayoutX(743);
                chuF.setLayoutY(165);
                zhaoF.setLayoutX(743);
                zhaoF.setLayoutY(239);
                hanF.setLayoutX(743);
                hanF.setLayoutY(295);
                weiF.setLayoutX(743);
                weiF.setLayoutY(342);
                yanF.setLayoutX(743);
                yanF.setLayoutY(383);
                root.getChildren().addAll(qinF,qiF,chuF,zhaoF,hanF,weiF,yanF);
                setup = placement1;
                boardMatrix = createMatrix(placement1);
                primaryStage.setScene(scene);
            }
        });

        /* Create the help screen with instructions to play the game*/
        help.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final Stage helpScreen = new Stage();
                helpScreen.getIcons().add(new Image("/resource/comicwars.jpg"));
                helpScreen.setResizable(false);
                Image helpS = new Image("/resource/z11_background.jpg");
                helpScreen.initModality(Modality.APPLICATION_MODAL);
                helpScreen.initOwner(primaryStage);
                Pane insts = new Pane();
                Scene dialogScene = new Scene(insts, 1300, 730);
                insts.setBackground(new Background(new BackgroundImage(helpS,
                        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true))));
                helpScreen.setScene(dialogScene);
                helpScreen.show();
            }
        });
        makeControls();
        primaryStage.setResizable(false);
        primaryStage.setScene(intro);
        primaryStage.getIcons().add(new Image("/resource/comicwars.jpg"));
        primaryStage.show();

    }

    /**
     * Create the layout grid for move reference
     * Author: Rufus Raja (u6275198)
     */
    public void createGrid()
    {
        String array[][] = {
                {"4","5","6","7","8","9"},
                {"Y","Z","0","1","2","3"},
                {"S","T","U","V","W","X"},
                {"M","N","O","P","Q","R"},
                {"G","H","I","J","K","L"},
                {"A","B","C","D","E","F"}
        };
        Label setupDisplay = new Label("Board Layout = ");
        setupDisplay.setLayoutX(638);
        setupDisplay.setLayoutY(610);
        setupDisplay.setTextFill(Color.WHITE);
        setupDisplay.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        pane.setLayoutX(750);
        pane.setLayoutY(560);
        pane.setStyle("-fx-background-color: Black; -fx-border-color: Black");
        root.getChildren().add(pane);
        root.getChildren().add(setupDisplay);
        for (int x = 0; x < array.length; x++){
            for (int y = 0; y < array[x].length; y++){
                Label label = new Label(array[x][y], new Rectangle(8, 8));
                label.setTextFill(Color.WHITE);
                label.setFont(Font.font("Verdana", FontWeight.BOLD, 14));

                pane.add(label, x, y);
            }
        }
    }

    /**
     * Count the number of flags and cards of each player, decide who is the winner
     * Author: Rufus Raja (u6275198), Jiajia Xu (6528982)
     */
    public static void winner() {
        int playerSums [] = new int[num_players];
        for(int x=0; x<playerSums.length;x++)
        {
            playerSums[x] = WinnerSum(x);
        }
        int max = playerSums[0];
        winner = 0;
        String winnerName = " ";
        /*Compare number of flags to determine the winner*/
        for(int x=0;x<playerSums.length;x++)
        {
            if(playerSums[x] > max)
            {
                max=playerSums[x];
                winner= x;

            }
            if(playerSums[x] == max)
            {
                /*If the flags are same, checks for the number of cards*/
                if(cardCount[x] > cardCount[winner]) {
                    max = playerSums[x];
                    winner = x;
                }
                /*If cards are same checks for who holds the Avengers cards (largest team)*/
                if(cardCount[x] == cardCount[winner] && flag[0] == x)
                {
                    winner = x;
                }
                else
                {
                    continue;
                }
            }
        }
        winnerName = playerName[winner];
        String win = Integer.toString(winner);
        Game end = new Game();
        declareWinner.setText("Player "+winnerName+" is the Winner!");
        end.scene2player.setVolume(0.08);
        end.lokiEnding.play();
        end.scene2player.stop();
        declareWinner.setLayoutX(0);
        declareWinner.setLayoutY(0);
        declareWinner.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
        declareWinner.setFill(Color.RED);



    }

    /**
     * Method to find the sum of flags held by a player ID
     * @param id
     * @return
     */
    public static int WinnerSum(int id)
    {
        int sum = 0;
        for(int x=0;x<flag.length;x++)
        {
            if(flag[x] == id)
            {
                sum+=1;
            }
        }
        return sum;
    }

    /**
     * Create the initial setup for the board
     * Author: Rufus Raja (u6275198)
     */
    public void setup() {
        int rown = 0, coln = 0,col=5;
        Random r = new Random();
        int selector = r.nextInt(20);
        Image[][] grid = new Image[6][6];
        placement1 = PLACEMENTS[selector];
        String [] sub = substr(placement1,3);
        scores1.setText(" ");
        scores2.setText(" ");
        scores3.setText(" ");
        scores4.setText(" ");
        /*Loop through and check the positions for each image and store them to the image array*/
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
        gridPane.setStyle("-fx-background-color: Black; -fx-border-color: Black");
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
     * Method to carry out the next step after a player or the bot has selected a position to move to
     * Authors: Rufus Raja (u6275198), Songtuan Lin (u6162630)
     */
    public void nextStep() {
        String move = "z9"+getMove;
        boolean isEnd = true;
        if(!getMove.equals("")) {
            check = isMoveLegal(placement1, getMove.charAt(0));
            if (check) {
                boardMatrix = oneMove(move.charAt(2), placement1, boardMatrix);
                placement1 = matrixToString(boardMatrix);
                gridPane.getChildren().clear();
                boolean validPlacement = isPlacementWellFormed(placement1);
                if (validPlacement) {
                    makePlacement(placement1);
                } else
                    exit();
                moveSequence = moveSequence + getMove;
                int score1,score2,score3,score4;
                score1=score2=score3=score4=0;
                flag = getFlags(setup, moveSequence, num_players);
                /*Counts the number of flags and cards for a player*/
                for (int i = 0; i < flag.length; i++) {
                    if (flag[i] == -1) {
                        roundGains[i] = " ";
                    } else if (flag[i] == 0) {
                        cardCount[flag[i]] = numOfCards(flag[i]);
                        roundGains[i] = playerName[flag[i]];//+" ("+cardCount[flag[i]]+")";
                        score1+=1;
                    } else if (flag[i] == 1) {
                        cardCount[flag[i]] = numOfCards(flag[i]);
                        roundGains[i] = playerName[flag[i]];//+" ("+cardCount[flag[i]]+")";
                        score2+=1;
                    } else if (flag[i] == 2) {
                        cardCount[flag[i]] = numOfCards(flag[i]);
                        roundGains[i] = playerName[flag[i]];//+" ("+cardCount[flag[i]]+")";
                        score3+=1;
                    } else if (flag[i] == 3) {
                        cardCount[flag[i]] = numOfCards(flag[i]);
                        roundGains[i] = playerName[flag[i]];//+" ("+cardCount[flag[i]]+")";
                        score4+=1;
                    }
                }

                /*Displays the scores for each player*/
                if(score1 != 0){
                    scores1.setText(" "+Integer.toString(score1));
                    cards1.setText(" "+Integer.toString(cardCount[0]));
                }
                if(score2 != 0){
                    scores2.setText(" "+Integer.toString(score2));
                    cards2.setText(" "+Integer.toString(cardCount[1]));
                }
                if(score3 != 0){
                    scores3.setText(" "+Integer.toString(score3));
                    cards3.setText(" "+Integer.toString(cardCount[2]));
                }
                if(score4 != 0){
                    scores4.setText(" "+Integer.toString(score4));
                    cards4.setText(" "+Integer.toString(cardCount[3]));
                }

                qinF.setText(roundGains[0]);
                qiF.setText(roundGains[1]);
                chuF.setText(roundGains[2]);
                zhaoF.setText(roundGains[3]);
                hanF.setText(roundGains[4]);
                weiF.setText(roundGains[5]);
                yanF.setText(roundGains[6]);


            }
        }
        else
            check = false;

        /*Check if there are any legal moves, if there are then continue game, else set the game for end sequence*/
        for(int y=0;y<c.length;y++)
            if(isMoveLegal(placement1,c[y]))
                isEnd = false;

        /*If the game is still going on, keep the music playing*/
        if(!isEnd && !scene2player.isPlaying())
            scene2player.play();

        /*If the game end has been reached, start the winner declaration process*/
        if(isEnd == true) {
            winner();
            botPlay = false;
        }

    }

    /**
     * Counts the number of cards based on the player ID
     * @param id
     * @return
     * Author: Rufus Raja (u6275198)
     */
    private int numOfCards(int id) {
        String supporters = getSupporters(setup,moveSequence,num_players,id);
        String numofcards[] = new String[supporters.length()];
        numofcards = substr(supporters,2);
        return numofcards.length;
    }

    /**
     * Advanced AI search for best move available
     * @param placement
     * @param boardState
     * @return
     * Author: Songtuan Lin (u6162630)
     */
    char alpha_beta_search(String placement, String[][] boardState)
    {
        List<Character> moveList = WarringStatesGame.LegalMoves(placement);
        String nextState[][];
        String nextPlacement;
        int deep = 0;
        int finalValue = -1000;
        int value;
        int alpha = -1000, beta = 1000; //the beta store the best score (the lowest value) that the most up min node which is current in calculating can get.
        //the alpha store the best score (the highest value) that the most up max node which is current in calculating can get.
        char nextMove = 'A';
        boolean limit = true;
        if(placement.length() < 66)
            limit = false;
        for(char move : moveList)
        {
            String newMovesequence = this.moveSequence + move;
            nextState = WarringStatesGame.oneMoveGame(move, placement, boardState);
            nextPlacement = WarringStatesGame.matrixToString(nextState);
            value = min_value(nextPlacement, nextState, newMovesequence, alpha, beta, deep + 1, limit);
            if(finalValue < value)
            {
                finalValue = value;
                nextMove = move;
            }
        }
        return nextMove;
    }

    /**
     * Returns the minimum value based on the minimax algorithm
     * @param placement
     * @param boardState
     * @param oldMoveSequence
     * @param alpha
     * @param beta
     * @param deep
     * @param limit
     * @return
     * Author: Songtuan Lin (u6162630)
     */
    int min_value(String placement, String[][] boardState, String oldMoveSequence, int alpha, int beta, int deep, boolean limit)
    {
        int minValue = 1000;
        int value;
        List<Character> moveList = WarringStatesGame.LegalMoves(placement);
        String nextState[][];
        String nextPlacement;
        if(moveList.size() == 0)
            return getUtility(oldMoveSequence);
        else if(deep > 8 && limit == true)
            return getUtility(oldMoveSequence);
        for(char move : moveList)
        {
            String newMovesequence = oldMoveSequence + move;
            nextState = WarringStatesGame.oneMoveGame(move, placement, boardState);
            nextPlacement = WarringStatesGame.matrixToString(nextState);
            value = max_value(nextPlacement, nextState, newMovesequence, alpha, beta, deep + 1, limit);
            if(value <= alpha)
                //if the one of the value (say y in this example) this min node get is smaller than the alpha
                //then, the value domain of this node is [some value smaller than y]
                //which means there is some better score for a upper max node can choose whose score is greater
                //than y. As a result, the subtree that after this min node will not be choosed by the upper max
                //node, the search after this node can be terminated.
                return value;
            if(value < minValue)
                minValue = value;
            if(value < beta)
                beta = value;
        }
        return minValue;
    }

    /**
     * Return the max value based on the minimax algorithm
     * @param placement
     * @param boardState
     * @param oldMovesequence
     * @param alpha
     * @param beta
     * @param deep
     * @param limit
     * @return
     * Author: Songtuan Lin (u6162630)
     */
    int max_value(String placement, String[][] boardState, String oldMovesequence, int alpha, int beta, int deep, boolean limit)
    {
        int maxValue = -1000;
        int value;
        List<Character> moveList = WarringStatesGame.LegalMoves(placement);
        String nextState[][];
        String nextPlacement;
        if(moveList.size() == 0)
            return getUtility(oldMovesequence);
        else if(deep > 8 && limit == true)
            return getUtility(oldMovesequence);
        for(char move : moveList)
        {
            String newMovesequence = oldMovesequence + move;
            nextState = WarringStatesGame.oneMoveGame(move, placement, boardState);
            nextPlacement = WarringStatesGame.matrixToString(nextState);
            value = min_value(nextPlacement, nextState, newMovesequence, alpha, beta, deep + 1, limit);
            if(value >= beta)
                //if the one of the value (say x in this example) this max node get is greater than the beta
                //then, the value domain of this node is [x, some value greater than x]
                //which means there is some better score for a upper min node can choose whose score is smaller
                //than x. As a result, the subtree that after this max node will not be choosed by the upper min
                //node, the search after this node can be terminated.
                return value;
            if(value > maxValue)
                maxValue = value;
            if(value > alpha)
                alpha = value;
        }
        return maxValue;
    }

    /**
     * Checks the results based on the movesequence
     * @param moveSequence
     * @return
     * Author: Songtuan Lin (u6162630)
     */
    int getUtility(String moveSequence)
    {
        int flag[] = WarringStatesGame.getFlags(this.setup, moveSequence, 2);
        int flagNumber = 0;
        int cardNumber = 0;
        int utility = 0;
        for(int i = 0; i < flag.length; i++)
            if(flag[i] == 1)
                flagNumber++;
        cardNumber = WarringStatesGame.getSupporters(this.setup, moveSequence, 2, 1).length() / 2;
        utility = flagNumber * 10 + cardNumber;
        return utility;
    }


}