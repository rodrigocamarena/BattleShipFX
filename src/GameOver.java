import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class GameOver {

    public static Scene CreateStage(Stage scene){
        BorderPane layout = new BorderPane();
        VBox VerticalLayout = new VBox();
        scene.setTitle("BattleShip");
        CreateToolBarMenu(layout, scene);
        Scene GameOver = new Scene(layout, 1000, 700);
        AddingBackgroundGameOverUser(GameOver);
        AddingButtonstoGameBoard(layout, VerticalLayout, scene);
        scene.setResizable(false);
        scene.show();
        scene.centerOnScreen();

        return GameOver;
    }

    public static void AddingButtonstoGameBoard(BorderPane layout, VBox VerticalLayout, Stage scene){

        Button Return = new Button("Accept Failure");
        Return.setId("GameOptions");

        Return.setOnAction(e ->{
            try {
                scene.setScene(MenuGame.CreateStage(scene));
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            scene.show();
            scene.centerOnScreen();
        });

        VerticalLayout.getChildren().add(Return);
        VerticalLayout.setAlignment(Pos.CENTER);
        layout.setCenter(VerticalLayout);
        layout.setPrefWidth(Region.USE_COMPUTED_SIZE);
        BorderPane.setMargin(VerticalLayout, new Insets(30, 0, 0, 0));
    }

    public static void CreateToolBarMenu(BorderPane layout, Stage scene){
        //File menu
        Menu menu = new Menu("Menu");
        menu.getItems().add(new MenuItem("About Us"));
        menu.getItems().add(new SeparatorMenuItem());
        menu.getItems().add(new MenuItem("Help"));
        menu.getItems().add(new SeparatorMenuItem());
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e -> {
            try {
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

    public static void AddingBackgroundGameOverUser(Scene scene){
        scene.getStylesheets().add("Static/CSS/GameOver.css");
    }
}
