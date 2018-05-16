package comp1110.ass2.gui;

//import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import comp1110.ass2.Card;
import java.util.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
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
import javafx.scene.media.AudioTrack;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import comp1110.ass2.WarringStatesGame;

import javafx.scene.media.AudioTrack;
//import jdk.nashorn.internal.runtime.Property;

import javax.jws.soap.SOAPBinding;

import static comp1110.ass2.WarringStatesGame.*;
import static javafx.application.Platform.accessibilityActiveProperty;
import static javafx.application.Platform.exit;

public class Game extends Application {
    static int num_players = 0;

    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    Card p = new Card();
    String getMove;
    String getBotMove;
    GridPane gridPane = new GridPane();
    private static final int VIEWER_WIDTH = 1033;
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
    public int cardCount[] = new int[7];
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
    static Text test=new Text(45,350,"");
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
/*    String scene1Music = "/resource/scene1.mp3";     // For example
    String scene2Music = "/resource/scene2.mp3";
    Media sound1 = new Media(new File(scene1Music).toURI().toString());
    Media sound2 = new Media(new File(scene2Music).toURI().toString());
    MediaPlayer mediaPlayer1 = new MediaPlayer(sound1);
    MediaPlayer mediaPlayer2 = new MediaPlayer(sound2);

*/

    static final String[] PLACEMENTS = {
            "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"
    };
    static final char c[] = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9'};


    // FIXME Task 9: Implement a basic playable Warring States game in JavaFX

    // FIXME Task 11: Allow players of your Warring States game to play against your simple agent


    // FIXME Task 12: Integrate a more advanced opponent into your game


    void makePlacement(String placement) {

        button.setDisable(false);
        textField.setDisable(false);
        textField.requestFocus();


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
                        {p.z9}
                };

        sub = substr(placement,3);

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
    private void makeControls() {
        Label label1 = new Label("Player Move:");
        label1.setTextFill(Color.WHITE);
        textField = new TextField();
        textField.setPrefWidth(150);
   //     textField.se
        Button newGame = new Button("New Game");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if(!textField.getText().equals("") || !textField.getText().equals(" "))
                singleMove();
            }
        });

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

        newGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
        HBox hb = new HBox();
        HBox hb1 = new HBox();
        hb.getChildren().addAll(label1, textField, button);
    //    hb1.getChildren().add(newGame);
        hb1.setSpacing(10);
        hb1.setLayoutX(800);
        hb1.setLayoutY(50);
        hb.setSpacing(10);
        hb.setLayoutX(250);
        hb.setLayoutY(VIEWER_HEIGHT-50);
        controls.getChildren().add(hb);
        controls.getChildren().add(hb1);
    }

    private void singleMove() {
        click1.setVolume(0.3);
        click1.play();
        mCount=mCount+1;
        numOfTimes = 0;
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
        if(playerName[1].equals("Omega(AI)") && check && botPlay)
        {
            button.setDisable(true);
            textField.setDisable(true);
      //      getMove = alpha_beta_search(placement1, boardMatrix) + "";//BotMove()+"";
            getMove = BotMove()+"";
            delay(1000, new Runnable(){ public void run(){ nextStep();} });
            //nextStep();
        }
        textField.clear();
        if(playerName[1].equals("Omega(AI)") && check && botPlay)
        {
          //  advancedAI();
        }

    }

    public void advancedAI()
    {
        /*
        getMove = BotMove()+"";
        botMoveSequence = botMoveSequence+getMove.charAt(0);
//        botFlag[y] = getFlags(setup, moveSequence, 2);
        int x=0;
        char possible[] = new char[10];
        for(int y=0;y<c[y];y++) {
            if (isMoveLegal(placement1, c[y])) {
                possible[x] = c[y];
                x++;
            }
        }
        for(int y=0;y<possible.length;y++)
        {
            botMoveSequence = botMoveSequence+possible[y];

        }

        button.setDisable(true);
        textField.setDisable(true);
        getMove = BotMove()+"";
        int bestVal = Integer.MIN_VALUE;
        delay(1000, new Runnable(){ public void run(){ nextStep();} });
//                 nextStep();
        */
    }

    public char BotMove()
    {
        return generateMove(placement1);
    }

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

        gridPane.setLayoutX(100);
        gridPane.setLayoutY(10);
     //   t = new Thread(sleeper);
        root.getChildren().add(gridPane);

        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);
        Scene intro = new Scene(introroot, VIEWER_WIDTH,VIEWER_HEIGHT);
        intro.getStylesheets().addAll(this.getClass().getResource("/resource/style.css").toExternalForm());
        Image img = new Image("/resource/z9_background.jpg");
        Image img2 = new Image("/resource/z10_background.jpg");
      //  root.setBackground();
      //  root.getChildren().add(bgImg);
   //     introroot.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
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
        Label qin  = new Label("Qin :");
        Label qi   = new Label("Qi :");
        Label chu  = new Label("Chu :");
        Label zhao = new Label("Zhao :");
        Label han  = new Label("Han :");
        Label wei  = new Label("Wei :");
        Label yan  = new Label("Yan :");
        Label p1   = new Label("Player IDs");
        qin.setTextFill(Color.WHITE);
        qi.setTextFill(Color.WHITE);
        chu.setTextFill(Color.WHITE);
        zhao.setTextFill(Color.WHITE);
        han.setTextFill(Color.WHITE);
        wei.setTextFill(Color.WHITE);
        yan.setTextFill(Color.WHITE);
        p1.setTextFill(Color.WHITE);
        p1.setFont(Font.font("Arial Black", FontWeight.BOLD, 14));





    //    kingdoms.getChildren().addAll(qin,qi,chu,zhao,han,wei,yan);
        kingdoms.setSpacing(30);
        kingdoms.setLayoutX(750);
        kingdoms.setLayoutY(70);
        flags = new VBox();
        flags.setSpacing(50);
        flags.setLayoutX(835);
        flags.setLayoutY(40);
        playerIDs.setSpacing(20);
        playerIDs.setLayoutX(830);
        playerIDs.setLayoutY(10);

        Button startGame = new Button("Start Game");
        Button help = new Button("Instructions");
        Button mutemusic = new Button("Music On/Off");
        Button reset = new Button("Reset");
        HBox sounds = new HBox();
        sounds.getChildren().addAll(mutemusic);
        sounds.setSpacing(40);
        sounds.setLayoutX(850);
        sounds.setLayoutY(650);
        startGame.setLayoutX(450);
        startGame.setLayoutY(550);
        help.setLayoutX(550);
        help.setLayoutY(550);
        player.setItems(FXCollections.observableArrayList(
                "1 Player","2 Players","3 Players","4 Players"));
        player.setValue("1 Player");
        selection.setLayoutX(VIEWER_HEIGHT/2);
        selection.setLayoutY(VIEWER_WIDTH/2);
        player.setLayoutY(250);
        player.setLayoutX(500);
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
    //    player1.setDisable(true);
        player2.setDisable(true);
        player3.setDisable(true);
        player4.setDisable(true);
        playerLabels.setLayoutX(400);
        playerLabels.setLayoutY(300);
        playerLabels.setSpacing(30);
        playerLabels.getChildren().addAll(player1name,player2name,player3name,player4name);
        playernames.setLayoutX(465);
        playernames.setLayoutY(300);
        playernames.getChildren().addAll(player1,player2,player3,player4);
        playernames.setSpacing(20);
        player.setMaxSize(200,200);
    //    introroot.getChildren().add(startGame);
        introroot.getChildren().add(playernames);
        introroot.getChildren().add(playerLabels);
        root.getChildren().add(controls);
   //     root.getChildren().add(kingdoms);
        root.getChildren().add(playerIDs);
        root.getChildren().add(flags);
        root.getChildren().add(winnerID);
        root.getChildren().add(test);
        root.getChildren().add(sounds);
        scene1player.setVolume(0.2);
        scene1player.play();
        introroot.getChildren().add(player);
        introroot.getChildren().add(startGame);
        introroot.getChildren().add(help);
   //     selection.getChildren().addAll(startGame);
   //     selection.setSpacing(50);


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
        startGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                createGrid();




                click1.play();
                scene1player.stop();
                lokiIntro.play();
                scene2player.setVolume(0.1);
                scene2player.setCycleCount(AudioClip.INDEFINITE);
                scene2player.play();
                num_players = Integer.parseInt(player.getValue().toString().substring(0,1));
                System.out.println("number of players"+num_players);
  //              gridPane.setGridLinesVisible(true);
                if(!player1.getText().equals(""))
                playerName[0] = player1.getText();
                if(num_players == 1)
                {
                    playerName[1] = "Omega(AI)";
                    num_players = 2;
                }
                if(num_players == 2)
                {
                    if(!player2.getText().equals(""))
                    playerName[1] = player2.getText();
                }
                if(num_players == 3)
                {
                    if(!player2.getText().equals("") || !player3.getText().equals("")) {
                        playerName[1] = player2.getText();
                        playerName[2] = player3.getText();
                    }
                }
                if(num_players == 4)
                {
                    if(!player2.getText().equals("") || !player3.getText().equals("") || !player4.getText().equals("")) {
                        playerName[1] = player2.getText();
                        playerName[2] = player3.getText();
                        playerName[3] = player4.getText();
                    }
                }
                for(String s:roundGains)
                {
                    s = " ";
                }

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

                reset.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                    primaryStage.setScene(intro);
                    }
                });

     /*           gridPane.getChildren().clear();
                if(num_players == 1 || num_players == 2)
                    playerIDs.getChildren().addAll(p1,p2);
                else if(num_players == 3 )
                    playerIDs.getChildren().addAll(p1,p2,p3);
                else {
                    System.out.println(num_players);
                    playerIDs.getChildren().addAll(p1, p2, p3, p4);
                }*/

                playerIDs.getChildren().addAll(p1);
                setup();
                textField.clear();
                flags.getChildren().addAll(qinF,qiF,chuF,zhaoF,hanF);
                weiF.setLayoutX(833);
                weiF.setLayoutY(365);
                yanF.setLayoutX(833);
                yanF.setLayoutY(410);
                root.getChildren().add(weiF);
                root.getChildren().add(yanF);
                setup = placement1;
                boardMatrix = createMatrix(placement1);
                primaryStage.setScene(scene);
            }
        });

        help.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final Stage helpScreen = new Stage();
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
    //    primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setScene(intro);
        primaryStage.getIcons().add(new Image("/resource/comicwars.JPG"));
        primaryStage.show();

    }

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
   //     GridPane pane = new GridPane();
        Label setupDisplay = new Label("Board Layout = ");
        setupDisplay.setLayoutX(740);
        setupDisplay.setLayoutY(530);
        setupDisplay.setTextFill(Color.WHITE);
        setupDisplay.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        pane.setLayoutX(860);
        pane.setLayoutY(480);
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


    public static void winner() {
        //count the number of flags and cards of each player, decide who is the winner
        int playerSums [] = new int[num_players];
        for(int x=0; x<playerSums.length;x++)
        {
            playerSums[x] = WinnerSum(x);
        }
        int max = playerSums[0];
        winner = 0;
        String winnername = " ";
        for(int x=0;x<playerSums.length;x++)
        {
            if(playerSums[x] >= max)
            {
                max=playerSums[x];
                winner= x;

            }
        }
  //      winner = winner;
        winnername = playerName[winner];
        System.out.println("Winner ID:"+winner);
        String win = Integer.toString(winner);
  /*      winnerID.setText("Player:"+win+" is the Winner!");
        winnerID.resize(500,500);
        winnerID.setStyle("-fx-font-weight: bold");
        winnerID.setLayoutX(VIEWER_WIDTH/2-100);
        winnerID.setLayoutY(VIEWER_HEIGHT/2-100);
        winnerID.setTextFill(Color.RED); */
        Game end = new Game();
        test.setText("Player "+winnername+" is the Winner!");
        end.scene2player.setVolume(0.08);
        end.lokiEnding.play();
        end.scene2player.stop();
        test.setLayoutX(0);
        test.setLayoutY(0);
        test.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
        test.setFill(Color.RED);



    }

    public static int WinnerSum(int y)
    {
        int sum = 0;
        for(int x=0;x<flag.length;x++)
        {
            if(flag[x] == y)
            {
                sum+=1;
            }
        }
        return sum;
    }


    public void setup() {
        //check if the placement is valid and  make it visible

    //    String sub[] = new String[placement.length() / 3];
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
                        {p.z9}
                };

        placement1 = PLACEMENTS[selector];
        String [] sub = substr(placement1,3);

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


    public void nextStep() {
        String move = "z9"+getMove;
        boolean isEnd = true;
        if(!getMove.equals("")) {
            check = isMoveLegal(placement1, getMove.charAt(0));
            if (check) {
                boardMatrix = oneMove(move.charAt(2), placement1, boardMatrix);
                placement1 = matrixToString(boardMatrix);
                System.out.println("String placement " + placement1);
                gridPane.getChildren().clear();
                System.out.println("Placement before the move " + placement1);
                boolean val = isPlacementWellFormed(placement1);
                if (val) {
                    makePlacement(placement1);
                } else
                    exit();
                moveSequence = moveSequence + getMove;
    /*            if(player >= (num_players-1))
                {
                    player = 0;
                }
                else
                player = player+1;
    */
     //           System.out.println("This was player:"+(player+1)+" move");
                flag = getFlags(setup, moveSequence, num_players);
                for (int i = 0; i < flag.length; i++) {
                    if (flag[i] == -1) {
                        roundGains[i] = " ";
                    } else if (flag[i] == 0) {
                        cardCount[flag[i]] = numOfCards(flag[i]);
    /*                    if(titan && player!=flag[i] && cardCount[flag[i]]!=0)
                        {
                            cardCount[flag[i]] = cardCount[flag[i]]-1;
                            titan = false;
                            System.out.println("reduced because of Thanos");
                        }
    */                    roundGains[i] = playerName[flag[i]]+" ("+cardCount[flag[i]]+")";
                    } else if (flag[i] == 1) {
                        cardCount[flag[i]] = numOfCards(flag[i]);
    /*                    if(titan && player!=flag[i] && cardCount[flag[i]]!=0)
                        {
                            cardCount[flag[i]] = cardCount[flag[i]]-1;
                            titan = false;
                            System.out.println("reduced because of Thanos");
                        }
    */                    roundGains[i] = playerName[flag[i]]+" ("+cardCount[flag[i]]+")";
                    } else if (flag[i] == 2) {
                        cardCount[flag[i]] = numOfCards(flag[i]);
     /*                   if(titan && player!=flag[i] && cardCount[flag[i]]!=0)
                        {
                            cardCount[flag[i]] = cardCount[flag[i]]-1;
                            titan = false;
                        }
      */                  roundGains[i] = playerName[flag[i]]+" ("+cardCount[flag[i]]+")";
                    } else if (flag[i] == 3) {
                        cardCount[flag[i]] = numOfCards(flag[i]);
     /*                   if(titan && player!=flag[i] && cardCount[flag[i]]!=0)
                        {
                            cardCount[flag[i]] = cardCount[flag[i]]-1;
                            titan = false;
                        }
      */                  roundGains[i] = playerName[flag[i]]+" ("+cardCount[flag[i]]+")";
                    }
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
        for(int y=0;y<c.length;y++)
            if(isMoveLegal(placement1,c[y]))
                isEnd = false;

        if(!isEnd && !scene2player.isPlaying())
            scene2player.play();

        if(isEnd == true) {
            winner();
            botPlay = false;
        }

    }

    private int numOfCards(int id) {
        String supporters = getSupporters(setup,moveSequence,num_players,id);
   //     System.out.println("These are the supporters:"+ supporters);
        String numofcards[] = new String[supporters.length()];
        numofcards = substr(supporters,2);
  /*      for(int x=0;x<numofcards.length;x++)
            System.out.println("These are each of the cards in numofcards for player"+(id+1)+": "+numofcards[x]);
        for(int x=0;x<numofcards.length;x++){
            if(numofcards[x].equals("g0") && titanCount == 0) {
                titan = true;
                System.out.println("reached here");
                titanCount = 1;
            }
        }*/

        return numofcards.length;
    }

    char alpha_beta_search(String placement, String[][] boardState)
    {
        List<Character> moveList = WarringStatesGame.LegalMoves(placement);
        String nextState[][];
        String nextPlacement;
        int deep = 0;
        int finalValue = -10;
        int value;
        int alpha = -10, beta = 10;
        char nextMove = 'A';
        boolean limit = true;
        if(placement.length() < 72)
            limit = false;
        for(char move : moveList)
        {
            String newMovesequence = this.moveSequence + move;
            nextState = WarringStatesGame.oneMoveGame(move, placement, boardState);
            nextPlacement = WarringStatesGame.matrixToString(nextState);
            value = max_value(nextPlacement, nextState, newMovesequence, alpha, beta, deep + 1, limit);
            if(finalValue < value)
            {
                finalValue = value;
                nextMove = move;
            }
        }
        return nextMove;
    }

    int min_value(String placement, String[][] boardState, String oldMoveSequence, int alpha, int beta, int deep, boolean limit)
    {
        int minValue = 10;
        int value;
        List<Character> moveList = WarringStatesGame.LegalMoves(placement);
        String nextState[][];
        String nextPlacement;
        if(moveList.size() == 0)
            return getUtility(oldMoveSequence);
        else if(deep > 12 && limit == true)
            return getUtility(oldMoveSequence);
        for(char move : moveList)
        {
            String newMovesequence = oldMoveSequence + move;
            nextState = WarringStatesGame.oneMoveGame(move, placement, boardState);
            nextPlacement = WarringStatesGame.matrixToString(nextState);
            value = max_value(nextPlacement, nextState, newMovesequence, alpha, beta, deep + 1, limit);
            if(value <= alpha)
                return value;
            if(value < minValue)
                minValue = value;
            if(value < beta)
                beta = value;
        }
        return minValue;
    }

    int max_value(String placement, String[][] boardState, String oldMovesequence, int alpha, int beta, int deep, boolean limit)
    {
        int maxValue = 10;
        int value;
        List<Character> moveList = WarringStatesGame.LegalMoves(placement);
        String nextState[][];
        String nextPlacement;
        if(moveList.size() == 0)
            return getUtility(oldMovesequence);
        else if(deep > 12 && limit == true)
            return getUtility(oldMovesequence);
        for(char move : moveList)
        {
            String newMovesequence = oldMovesequence + move;
            nextState = WarringStatesGame.oneMoveGame(move, placement, boardState);
            nextPlacement = WarringStatesGame.matrixToString(nextState);
            value = min_value(nextPlacement, nextState, newMovesequence, alpha, beta, deep + 1, limit);
            if(value >= beta)
                return value;
            if(value > maxValue)
                maxValue = value;
            if(value > alpha)
                alpha = value;
        }
        return maxValue;
    }

    int getUtility(String moveSequence)
    {
        int flag[] = WarringStatesGame.getFlags(this.setup, moveSequence, 2);
        int player1 = 0;
        int player2 = 0;
        for(int i = 0; i < flag.length; i++)
        {
            if(flag[i] == 0)
                player1++;
            else if(flag[i] == 1)
                player2++;
        }
        if(player2 >= player1)
            return 1;
        else
            return -1;
    }


}