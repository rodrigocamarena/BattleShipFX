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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.media.MediaPlayer;
import java.io.FileNotFoundException;

public class MenuGame {

    public static Scene CreateStage(Stage scene) throws FileNotFoundException {
        MediaPlayer mediaPlayer = Music.PlayMusic("src/Static/Sound/TheArtOfWar.mp3");
        BorderPane layout = new BorderPane();
        VBox VerticalLayout = new VBox(10);
        scene.setTitle("BattleShip");
        CreateToolBarMenu(layout);
        Scene MenuGame = new Scene(layout, 700, 700);
        AddTitle(layout, VerticalLayout);
        AddingButtonstoMenu(mediaPlayer, layout, VerticalLayout, scene);
        AddingBackgroundtoScene(MenuGame);
        scene.setResizable(false);


        return MenuGame;
    }

    public static void AddTitle(BorderPane layout, VBox VerticalLayout) throws FileNotFoundException {
        Label label = new Label();
        javafx.scene.image.Image image = new javafx.scene.image.Image("Static/CSS/Images/TitleMediano.png");
        label.setGraphic(new ImageView(image));
        label.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(label, 0.0);
        AnchorPane.setRightAnchor(label, 0.0);
        label.setAlignment(Pos.CENTER);
        VerticalLayout.getChildren().addAll(label);
    }
    public static void CreateToolBarMenu(BorderPane layout){
        //File menu
        Menu menu = new Menu("Menu");
        menu.setId("MenuItems");
        menu.getItems().add(new MenuItem("About Us"));
        menu.getItems().add(new SeparatorMenuItem());
        menu.getItems().add(new MenuItem("Help"));
        menu.getItems().add(new SeparatorMenuItem());
        menu.getItems().add(new MenuItem("Exit"));


        //Main Menu Bar
        MenuBar menubar = new MenuBar();
        menubar.getMenus().addAll(menu);
        menubar.setId("ToolBar");

        layout.setTop(menubar);
    }

    public static void AddingButtonstoMenu(MediaPlayer mediaPlayer, BorderPane layout, VBox VerticalLayout, Stage scene){
        Button button = new Button("1vsMachine");
        button.setOnAction(e -> {
            Music.StopMusic(mediaPlayer);
            scene.setScene(CreatingBoard.CreateStage(scene));
            scene.show();
            scene.centerOnScreen();
        });
        scene.centerOnScreen();
        Button button2 = new Button("1vs1");
        button2.setOnAction( e -> {
            Music.StopMusic(mediaPlayer);
            scene.setScene(CreatingBoard1.CreateStage(scene));
            scene.show();
            scene.centerOnScreen();
        });
        VerticalLayout.getChildren().addAll(button, button2);
        VerticalLayout.setAlignment(Pos.CENTER);
        layout.setCenter(VerticalLayout);
    }
    public static void AddingBackgroundtoScene(Scene scene){
        scene.getStylesheets().add("src/Static/CSS/Menu.css");
    }
}
