import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class Game2vs2{

    public static Scene CreateStage(Stage scene, Button [][]Board1, GridPane gridPane1, Button [][]Board2, GridPane gridPane2) throws InterruptedException {
        MediaPlayer mediaPlayer = Music.PlayMusic("src/Static/Sound/WaterDisplacement.mp3");
        BorderPane layout = new BorderPane();
        VBox box = new VBox();
        scene.setTitle("BattleShip");
        CreateToolBarMenu(layout, scene, mediaPlayer);
        Scene MenuGame = new Scene(layout, 1000, 700);
        Button Next = CreateNextButton(mediaPlayer, scene, Board1, gridPane1, Board2, gridPane2);
        AddingLeftBoard(mediaPlayer, Next, layout, gridPane1, gridPane2, Board1, Board2, scene);
        AddingRightBoard(mediaPlayer, Next, layout, gridPane1, gridPane2, Board1, Board2, scene);
        box.getChildren().add(Next);
        layout.setBottom(box);
        BorderPane.setMargin(box,  new Insets(0, 0, 100, 30));
        layout.setCenter(null);
        AddingBackgroundtoScene(MenuGame);
        scene.setResizable(false);
        scene.show();
        scene.centerOnScreen();
        return MenuGame;
    }
    public static Button CreateNextButton(MediaPlayer mediaPlayer, Stage scene, Button [][]Board1, GridPane gridPane1, Button [][]Board2, GridPane gridPane2){
        Button Next = new Button("Next");
        Next.setId("GameOptions");
        Next.setOnAction(e ->{
                for(int i=1; i<11;i++){
                    for(int j=1; j<11; j++){
                        if(Board1[i][j].getId().equalsIgnoreCase("Selected")){
                            Board1[i][j].setId("Player2");
                        }
                    }
                }
                for(int i=1; i<11;i++){
                    for(int j=1; j<11; j++){
                        if(Board2[i][j].getId().equalsIgnoreCase("Player1")){
                            Board2[i][j].setId("Selected");
                        }
                    }
                }
                Music.StopMusic(mediaPlayer);
                scene.setScene(GifCountdown2.CreateStage(scene, Board1, gridPane1, Board2, gridPane2));
                scene.show();
        });
        Next.setDisable(true);
        return Next;
    }
    public static void CreateToolBarMenu(BorderPane layout, Stage scene, MediaPlayer mediaPlayer){
        //File menu
        Menu menu = new Menu("Menu");
        menu.getItems().add(new MenuItem("About Us"));
        menu.getItems().add(new SeparatorMenuItem());
        menu.getItems().add(new MenuItem("Help"));
        menu.getItems().add(new SeparatorMenuItem());
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e -> {
            try {
                Music.StopMusic(mediaPlayer);
                scene.setScene(MenuGame.CreateStage(scene));
                scene.show();
                scene.centerOnScreen();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        menu.getItems().add(exit);
        //Main Menu Bar
        MenuBar menubar = new MenuBar();
        menubar.getMenus().addAll(menu);

        layout.setTop(menubar);
    }
    public static void AddingLeftBoard(MediaPlayer mediaPlayer, Button Next, BorderPane layout, GridPane gridPane1, GridPane gridPane2, Button [][]Board1, Button [][]Board2, Stage scene) throws InterruptedException {
        ActionOfButtons(mediaPlayer, Next, Board1, Board2, scene, gridPane1, gridPane2);
        VBox Vertical = new VBox(10);
        Label LabelBoard = new Label();
        javafx.scene.image.Image image = new javafx.scene.image.Image("Static/CSS/Images/player2.png");
        LabelBoard.setGraphic(new ImageView(image));
        LabelBoard.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(LabelBoard, 0.0);
        AnchorPane.setRightAnchor(LabelBoard, 0.0);
        LabelBoard.setAlignment(Pos.CENTER);
        gridPane1.setDisable(true);
        Vertical.getChildren().addAll(LabelBoard, gridPane1);
        layout.setLeft(Vertical);
        BorderPane.setMargin(Vertical, new Insets(20, 10, 10, 30));
    }
    public static void AddingRightBoard(MediaPlayer mediaPlayer, Button Next, BorderPane layout, GridPane gridPane1, GridPane gridPane2, Button[][] Board1, Button[][] Board2, Stage scene) throws InterruptedException {
        ActionOfButtons(mediaPlayer, Next, Board2, Board1, scene, gridPane2, gridPane1);
        VBox Vertical = new VBox(10);
        Label LabelBoard = new Label();
        javafx.scene.image.Image image = new javafx.scene.image.Image("Static/CSS/Images/player1.png");
        LabelBoard.setGraphic(new ImageView(image));
        LabelBoard.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(LabelBoard, 0.0);
        AnchorPane.setRightAnchor(LabelBoard, 0.0);
        LabelBoard.setAlignment(Pos.CENTER);
        Vertical.getChildren().addAll(LabelBoard, gridPane2);
        layout.setRight(Vertical);
        BorderPane.setMargin(Vertical, new Insets(20, 10, 10, 30));
    }
    public static void ActionOfButtons(MediaPlayer mediaPlayer, Button Next, Button [][]PlayerBoard, Button [][]EnemyBoard, Stage scene, GridPane gridPane1, GridPane gridPane2) throws InterruptedException {
        for(int i=1; i<11; i++){
            for(int j=1; j<11; j++){
                final Button b1 = PlayerBoard[i][j];
                PlayerBoard[i][j].setOnAction( e -> {
                    try {
                        AddingNewActionButtons(mediaPlayer, Next, b1, PlayerBoard, EnemyBoard, scene, gridPane1, gridPane2);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                });
            }
        }
    }
    public static void AddingNewActionButtons(MediaPlayer mediaPlayer, Button Next, Button button, Button [][]PlayerBoard, Button [][]EnemyBoard, Stage scene, GridPane PlayerPane, GridPane EnemyPane) throws InterruptedException {
        javafx.scene.image.Image image = new javafx.scene.image.Image("Static/CSS/Images/explosion.gif", 20, 20, false, false);
        ImageView imageView = new ImageView(image);
        if(button.getId().equalsIgnoreCase("Player1")){
            button.setId("RedState");
            button.setGraphic(imageView);
            Music.ExplosionSoundEffect("src/Static/Sound/explosion.mp3");
            if(CheckMachineBoard(PlayerBoard)){
                //Game Over the User Wins
                Label label = new Label("Player 2 WINS");
                Music.StopMusic(mediaPlayer);
                scene.setScene(GameOver1vs1.CreateStage(scene, label));
                scene.show();
                scene.centerOnScreen();
            }else {
                Next.setDisable(false);
                PlayerPane.setDisable(true);


            }
        }else{
            button.setId("BlackState");
            Next.setDisable(false);
            PlayerPane.setDisable(true);
        }
    }
    public static boolean CheckMachineBoard(Button [][]EnemyBoard){
        boolean flag = true;
        for(int i=1; i<11; i++){
            for(int j=1; j<11; j++){
                if(EnemyBoard[i][j].getId().equalsIgnoreCase("Player1")){
                    flag = false;
                }
            }
        }
        return flag;
    }
    public static void AddingBackgroundtoScene(Scene scene){
        scene.getStylesheets().add("Static/CSS/Game2vs2.css");
    }
}
