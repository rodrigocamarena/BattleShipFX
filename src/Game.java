import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.Random;

public class Game{

    public static Scene CreateStage(Stage scene, GridPane grid, Button [][]UserButtons){
        MediaPlayer mediaPlayer = Music.PlayMusic("src/Static/Sound/WaterDisplacement.mp3");
        BorderPane layout = new BorderPane();
        scene.setTitle("BattleShip");
        CreateToolBarMenu(layout, scene, mediaPlayer);
        Scene MenuGame = new Scene(layout, 1000, 700);
        AddingLeftBoard(layout, grid);
        Button [][] MachineButton = AddingRightBoard(mediaPlayer, layout, UserButtons, scene);
        SelectRandom5Ship(MachineButton);
        SelectRandom4Ship(MachineButton);
        SelectRandom3Ship(MachineButton);
        SelectRandom2Ship(MachineButton);
        SetSelectedMachineIdtoMachineSelected(MachineButton);
        layout.setCenter(null);
        AddingBackgroundtoScene(MenuGame);
        scene.setResizable(false);
        scene.show();
        scene.centerOnScreen();




        return MenuGame;
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

    public static void SetSelectedMachineIdtoMachineSelected(Button [][]MachineButton){
        for(int i=1; i<11; i++){
            for(int j=1; j<11; j++){
                if(MachineButton[i][j].getId().equalsIgnoreCase("Selected")){
                    MachineButton[i][j].setId("MachineSelected");
                }
            }
        }
    }

    public static void SelectRandom5Ship(Button [][] MachineButton){
        Random random = new Random();
        int counter=1;
        int row, col;
                while(true){
                    row = random.nextInt(10);
                    if(row>0 && row<11){
                        break;
                    }
                }
                while(true){
                    col = random.nextInt(10);
                    if(col>0 && col<11){
                        break;
                    }
                }

                boolean flag = random.nextBoolean();
                if(flag){ //filas
                    if(row<6){
                        while(counter<6){
                            MachineButton[row][col].setId("Selected");
                            counter++;
                            row++;
                        }
                    }else{
                        while(counter<6){
                            MachineButton[row][col].setId("Selected");
                            counter++;
                            row--;
                        }
                    }
                }else{ //columnas
                    if(col<6){
                        while(counter<6){
                            MachineButton[row][col].setId("Selected");
                            counter++;
                            col++;
                        }
                    }else{
                        while(counter<6){
                            MachineButton[row][col].setId("Selected");
                            counter++;
                            col--;
                        }
                    }
                }
            }

    public static void SelectRandom4Ship(Button [][] MachineButton){
        Random random = new Random();
        int counter=1;
        int row, col;
            while(true){
                while(true){
                    row = random.nextInt(10);
                    col = random.nextInt(10);
                    if(row>0 && row<11 && col>0 && col<11){
                        if(!MachineButton[row][col].getId().equalsIgnoreCase("Selected")){
                            break;
                        }
                    }
                }
                boolean flag = random.nextBoolean();
                if(flag){ //filas
                    if(row<6 && !MachineButton[row+3][col].getId().equalsIgnoreCase("Selected") && !MachineButton[row+2][col].getId().equalsIgnoreCase("Selected")
                    && !MachineButton[row+1][col].getId().equalsIgnoreCase("Selected")){
                        while(counter<5){
                            MachineButton[row][col].setId("Selected");
                            counter++;
                            row++;
                        }
                        break;
                    }else if(row>6 && !MachineButton[row-3][col].getId().equalsIgnoreCase("Selected") && !MachineButton[row-2][col].getId().equalsIgnoreCase("Selected")
                    && !MachineButton[row-1][col].getId().equalsIgnoreCase("Selected")){
                        while(counter<5){
                            MachineButton[row][col].setId("Selected");
                            counter++;
                            row--;
                        }
                        break;
                    }
                }else{ //columnas
                    if(col<6 && !MachineButton[row][col+3].getId().equalsIgnoreCase("Selected") && !MachineButton[row][col+2].getId().equalsIgnoreCase("Selected")
                    && !MachineButton[row][col+1].getId().equalsIgnoreCase("Selected")){
                        while(counter<5){
                            MachineButton[row][col].setId("Selected");
                            counter++;
                            col++;
                        }
                        break;
                    }else if(col>6 && !MachineButton[row][col-3].getId().equalsIgnoreCase("Selected") && !MachineButton[row][col-2].getId().equalsIgnoreCase("Selected")
                            && !MachineButton[row][col-1].getId().equalsIgnoreCase("Selected")){
                        while(counter<5){
                            MachineButton[row][col].setId("Selected");
                            counter++;
                            col--;
                        }
                        break;
                    }
                }
            }
    }

    public static void SelectRandom3Ship(Button [][] MachineButton){
        Random random = new Random();
        int counter=1;
        int row, col;
        while(true){
            while(true){
                row = random.nextInt(10);
                col = random.nextInt(10);
                if(row>0 && row<11 && col>0 && col<11){
                    if(!MachineButton[row][col].getId().equalsIgnoreCase("Selected")){
                        break;
                    }
                }
            }
            boolean flag = random.nextBoolean();
            if(flag){ //filas
                if(row<6 && !MachineButton[row+2][col].getId().equalsIgnoreCase("Selected") && !MachineButton[row+1][col].getId().equalsIgnoreCase("Selected")){
                    while(counter<4){
                        MachineButton[row][col].setId("Selected");
                        counter++;
                        row++;
                    }
                    break;
                }else if(row>6 &&!MachineButton[row-2][col].getId().equalsIgnoreCase("Selected") && !MachineButton[row-1][col].getId().equalsIgnoreCase("Selected")){
                    while(counter<4){
                        MachineButton[row][col].setId("Selected");
                        counter++;
                        row--;
                    }
                    break;
                }
            }else{ //columnas
                if(col<6 && !MachineButton[row][col+2].getId().equalsIgnoreCase("Selected") && !MachineButton[row][col+1].getId().equalsIgnoreCase("Selected")){
                    while(counter<4){
                        MachineButton[row][col].setId("Selected");
                        counter++;
                        col++;
                    }
                    break;
                }else if(col>6 && !MachineButton[row][col-2].getId().equalsIgnoreCase("Selected") && !MachineButton[row][col-1].getId().equalsIgnoreCase("Selected")){
                    while(counter<4){
                        MachineButton[row][col].setId("Selected");
                        counter++;
                        col--;
                    }
                    break;
                }
            }
        }
    }

    public static void SelectRandom2Ship(Button [][] MachineButton){
        Random random = new Random();
        int counter=1;
        int row, col;
        while(true){
            while(true){
                row = random.nextInt(10);
                col = random.nextInt(10);
                if(row>0 && row<11 && col>0 && col<11){
                    if(!MachineButton[row][col].getId().equalsIgnoreCase("Selected")){
                        break;
                    }
                }
            }
            boolean flag = random.nextBoolean();
            if(flag){ //filas
                if(row<6 && !MachineButton[row+1][col].getId().equalsIgnoreCase("Selected")){
                    while(counter<3){
                        MachineButton[row][col].setId("Selected");
                        counter++;
                        row++;
                    }
                    break;
                }else if(row>6 && !MachineButton[row-1][col].getId().equalsIgnoreCase("Selected")){
                    while(counter<3){
                        MachineButton[row][col].setId("Selected");
                        counter++;
                        row--;
                    }
                    break;
                }
            }else{ //columnas
                if(col<6 && !MachineButton[row][col+1].getId().equalsIgnoreCase("Selected")){
                    while(counter<3){
                        MachineButton[row][col].setId("Selected");
                        counter++;
                        col++;
                    }
                    break;
                }else if(col>6 && !MachineButton[row][col-1].getId().equalsIgnoreCase("Selected")){
                    while(counter<3){
                        MachineButton[row][col].setId("Selected");
                        counter++;
                        col--;
                    }
                    break;
                }
            }
        }
    }

    public static void AddingLeftBoard(BorderPane layout, GridPane grid){
        VBox Vertical = new VBox(10);
        Label LabelBoard = new Label();
        javafx.scene.image.Image image = new javafx.scene.image.Image("Static/CSS/Images/player1.png");
        LabelBoard.setGraphic(new ImageView(image));
        LabelBoard.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(LabelBoard, 0.0);
        AnchorPane.setRightAnchor(LabelBoard, 0.0);
        LabelBoard.setAlignment(Pos.CENTER);
        grid.setDisable(true);
        Vertical.getChildren().addAll(LabelBoard, grid);
        layout.setLeft(Vertical);
        BorderPane.setMargin(Vertical, new Insets(20, 10, 10, 30));
    }

    public static Button[][] AddingRightBoard(MediaPlayer mediaPlayer, BorderPane layout, Button [][]UserButtons, Stage scene){
        GridPane grid =new GridPane();
        VBox Vertical = new VBox(10);
        Label LabelBoard = new Label();
        javafx.scene.image.Image image = new javafx.scene.image.Image("Static/CSS/Images/machineboard.png");
        LabelBoard.setGraphic(new ImageView(image));
        LabelBoard.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(LabelBoard, 0.0);
        AnchorPane.setRightAnchor(LabelBoard, 0.0);
        LabelBoard.setAlignment(Pos.CENTER);
        int labelnumber = 1;
        char labelletter = 'A';
        Button [][]button = new Button[11][11];

        for(int i=0; i<11; i++){
            for(int j=0; j<11;j++){
                Button button1 = new Button();
                button1.setMinSize(40, 40);
                if(i==0){
                    if(j == 0){
                        button1.setText("");
                    }else{
                        button1.setText(Integer.toString(labelnumber));
                        labelnumber++;
                    }
                }
                if(j==0){
                    if(i == 0){
                        button1.setText("");
                    }else{
                        button1.setText(Character.toString(labelletter));
                        labelletter++;

                    }
                }
                button[i][j] = button1;
                if(i!=0 && j!=0){
                    button[i][j].setOnAction(e -> {
                        try {
                            ActionOfButtons(mediaPlayer, button1, UserButtons, button, scene);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    });
                }
                button[i][j].setId("BoardButtons");
                grid.add(button[i][j], i, j);
                grid.setHgap(2);
                grid.setVgap(2);
                grid.setAlignment(Pos.CENTER);
            }
        }
        Vertical.getChildren().addAll(LabelBoard, grid);
        layout.setRight(Vertical);
        BorderPane.setMargin(Vertical, new Insets(20, 30, 10, 10));

        return button;
    }

    public static void ActionOfButtons(MediaPlayer mediaPlayer, Button button, Button [][]UserButtons, Button [][]MachineButtons, Stage scene) throws InterruptedException {
        javafx.scene.image.Image image = new javafx.scene.image.Image("Static/CSS/Images/explosion.gif", 20, 20, false, false);
        ImageView imageView = new ImageView(image);
        if(button.getId().equalsIgnoreCase("MachineSelected")){
            button.setId("RedState");
            button.setGraphic(imageView);
            Music.ExplosionSoundEffect("src/Static/Sound/explosion.mp3");
            if(CheckMachineBoard(MachineButtons)){
                //Game Over the User Wins
                Music.StopMusic(mediaPlayer);
                scene.setScene(Victory.CreateStage(scene));
                scene.show();
                scene.centerOnScreen();

            }
            Juego(mediaPlayer, UserButtons, MachineButtons, scene);
        }else if(button.getId().equalsIgnoreCase("BoardButtons")){
            button.setId("BlackState");
            //Music.PlaySoundEffect("src/Static/nope.mp3");
            Juego(mediaPlayer, UserButtons, MachineButtons, scene);
        }else if(button.getId().equalsIgnoreCase("RedState")){

        }
    }

    public static boolean CheckMachineBoard(Button [][]MachineButtons){
        boolean flag = true;
        for(int i=1; i<11; i++){
            for(int j=1; j<11; j++){
                if(MachineButtons[i][j].getId().equalsIgnoreCase("MachineSelected")){
                    flag = false;
                }
            }
        }
        return flag;
    }

    public static boolean CheckUserBoard(Button [][]UserButtons){
        boolean flag = true;
        for(int i=1; i<11; i++){
            for(int j=1; j<11; j++){
                if(UserButtons[i][j].getId().equalsIgnoreCase("GreenState")){
                    flag = false;
                }
            }
        }
        return flag;
    }

    public static void Juego(MediaPlayer mediaPlayer, Button [][]UserButton, Button [][]MachineButtons, Stage scene){
        javafx.scene.image.Image image = new javafx.scene.image.Image("src/Static/CSS/Images/explosion.gif", 20, 20, false, false);
        ImageView imageView = new ImageView(image);
        Random random = new Random();
        int row, col;
        while(true){
            row = random.nextInt(10);
            col = random.nextInt(10);
            if(row>0 && col>0){
                break;
            }
        }
        if(UserButton[row][col].getId().equalsIgnoreCase("NormalState")){
            UserButton[row][col].setId("BlackState");
        }else if(UserButton[row][col].getId().equalsIgnoreCase("GreenState")){
            UserButton[row][col].setId("RedState");
            UserButton[row][col].setGraphic(imageView);
            if(CheckUserBoard(UserButton)){
                //GameOver the Machine wins.
                Music.StopMusic(mediaPlayer);
                scene.setScene(GameOver.CreateStage(scene));
                scene.show();
                scene.centerOnScreen();
            }
        }
    }

    public static void AddingBackgroundtoScene(Scene scene){
        scene.getStylesheets().add("src/Static/CSS/Game.css");
    }
}
