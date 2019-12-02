import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.LinkedList;


public class CreatingBoard2 {
    public static Scene CreateStage(Stage scene, GridPane gridPane, Button [][]Board1){
        MediaPlayer mediaPlayer = Music.PlayMusic("src/Static/Sound/FirstTransmission.mp3");
        int numberoftimespressed = 5;
        GridPane grid =new GridPane();
        LinkedList<String> positionbuttons = new LinkedList<>();
        String []positions = new String[15];
        BorderPane layout = new BorderPane();
        VBox VerticalLayout = new VBox(10);
        layout.setCenter(null);
        scene.setTitle("BattleShip");
        CreateToolBarMenu(layout, scene, mediaPlayer);
        Scene MenuGame = new Scene(layout, 620, 600);
        Button [][]Board2= AddingUserBoard(grid, layout, numberoftimespressed, positions, positionbuttons);
        System.out.println(numberoftimespressed);
        AddingButtonstoGameBoard(mediaPlayer, layout, VerticalLayout, scene, positionbuttons, numberoftimespressed, Board1, gridPane, Board2, grid);
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
    public static Button[][] AddingUserBoard(GridPane grid, BorderPane layout, int numberoftimespressedbuttons, String []positions, LinkedList<String> positionbuttons){
        VBox Vertical = new VBox(10);
        Boolean flag = false;
        Label LabelBoard = new Label();
        javafx.scene.image.Image image = new javafx.scene.image.Image("Static/CSS/Images/player2.png");
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

                button1.setId("NormalState");
                if(i!=0 && j!=0){
                    button1.setOnAction(e -> ActionOfButtons(button1, positionbuttons));
                }

                button[i][j] = button1;
                String position = i+","+j;
                button[i][j].setAccessibleHelp(position);
                grid.add(button[i][j], i, j);
                grid.setHgap(2);
                grid.setVgap(2);
                grid.setAlignment(Pos.CENTER);
            }
        }
        Vertical.getChildren().addAll(LabelBoard, grid);
        layout.setRight(Vertical);
        BorderPane.setMargin(Vertical, new Insets(5, 20, 0, 0));

        return button;
    }
    public static void ActionOfButtons(Button button, LinkedList<String> positionbuttons){
        if(button.getId().equalsIgnoreCase("NormalState")){
            button.setId("Selected");
            String rowandcolumn = (GridPane.getRowIndex(button)) + ","+(GridPane.getColumnIndex(button));
            System.out.println(rowandcolumn);

            positionbuttons.add(rowandcolumn);

        }else if(button.getId().equalsIgnoreCase("Selected")){
            button.setId("NormalState");
            String idbutton = (GridPane.getRowIndex(button)) + ","+(GridPane.getColumnIndex(button));
            int id = positionbuttons.indexOf(idbutton);
            positionbuttons.remove(id);
        }
    }
    public static void AddingButtonstoGameBoard(MediaPlayer mediaPlayer, BorderPane layout, VBox VerticalLayout, Stage scene,LinkedList<String> positionbuttons, int number, Button [][]Board1, GridPane gridPane1, Button [][]Board2, GridPane gridPane2){

        Button Barco = new Button("5");
        Button Random = new Button("Random");
        Button Reset = new Button("Reset");
        Button Ready = new Button("Ready");
        Barco.setId("GameOptions");
        Random.setId("GameOptions");
        Reset.setId("GameOptions");
        Ready.setId("GameOptions");

        System.out.println(number);
        Barco.setOnAction(e ->{
            //Music.PlaySoundEffect("src/Static/Click.mp3");
            final int numberofships = Integer.parseInt(Barco.getText());
            if(numberofships>2){
                CheckingShipsPositions(positionbuttons, Barco);
            }else{
                Barco.setVisible(false);
            }

        });
        Ready.setOnAction(e -> {
            //Music.PlaySoundEffect("src/Static/Click.mp3");
            for(int i=1; i<11; i++){
                for(int j=1; j<11; j++){
                    if(Board2[i][j].getId().equalsIgnoreCase("GreenState") || Board2[i][j].getId().equalsIgnoreCase("Selected")){
                        Board2[i][j].setId("Player2");
                    }
                }
            }
            if(Barco.getText().equalsIgnoreCase("2")){
                Music.StopMusic(mediaPlayer);
                try {
                    scene.setScene(Game1vs1.CreateStage(scene, Board1, gridPane1, Board2, gridPane2));
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                scene.show();
                scene.centerOnScreen();
            }else{
                //display a window with error
            }
        });

        Reset.setOnAction(e -> {
            for(int i=1; i<11; i++){
                for(int j=1; j<11; j++){
                    Board2[i][j].setId("NormalState");
                }
            }
            Barco.setText("5");
            Barco.setDisable(false);
        });

        Random.setOnAction((e -> {
            for(int i=1; i<11; i++){
                for(int j=1; j<11; j++){
                    Board2[i][j].setId("NormalState");
                }
            }
            Game.SelectRandom5Ship(Board2);
            Game.SelectRandom4Ship(Board2);
            Game.SelectRandom3Ship(Board2);
            Game.SelectRandom2Ship(Board2);
            Barco.setText("2");
            Barco.setDisable(true);
        }));

        VerticalLayout.getChildren().addAll(Ready, Barco, Random, Reset);
        layout.setLeft(VerticalLayout);
        layout.setPrefWidth(Region.USE_COMPUTED_SIZE);
        BorderPane.setMargin(VerticalLayout, new Insets(105, 0, 0, 20));
    }
    public static void CheckingShipsPositions(LinkedList<String> positionbuttons, Button Barco){
        int numberofspaces = Integer.parseInt(Barco.getText());
        if(positionbuttons.size() == numberofspaces){
            String []first = positionbuttons.get(0).split("");
            String []second = positionbuttons.get(1).split("");
            if(first[0].equalsIgnoreCase(second[0])) { //las fila es la misma
                int menornumero = Integer.parseInt(first[2]);
                int mayornumero = Integer.parseInt(first[2]);
                for(int i=1; i<numberofspaces; i++){
                    String []x = positionbuttons.get(i).split("");
                    if(Integer.parseInt(x[2]) < menornumero){
                        menornumero = Integer.parseInt(x[2]);
                    }
                    if(Integer.parseInt(x[2])> mayornumero){
                        mayornumero = Integer.parseInt(x[2]);
                    }
                }
                if(mayornumero-menornumero==numberofspaces-1){
                    positionbuttons.clear();
                    String nombre = ""+ (numberofspaces-1);
                    Barco.setText(nombre);
                }
            }else if(first[2].equalsIgnoreCase(second[2])){ //la columna es la misma
                int menornumero = Integer.parseInt(first[0]);
                int mayornumero = Integer.parseInt(first[0]);
                for(int i=0; i<numberofspaces; i++){
                    String []x = positionbuttons.get(i).split("");
                    if(Integer.parseInt(x[0]) < menornumero){
                        menornumero = Integer.parseInt(x[0]);
                    }
                    if(Integer.parseInt(x[0])> mayornumero){
                        mayornumero = Integer.parseInt(x[0]);
                    }
                }
                if(mayornumero-menornumero==numberofspaces-1){
                    positionbuttons.clear();
                    String nombre = ""+ (numberofspaces-1);
                    Barco.setText(nombre);
                }
            }
        }
    }
    public static void AddingBackgroundtoScene(Scene scene){
        scene.getStylesheets().add("Static/CSS/CreatingBoard2.css");
    }
}

