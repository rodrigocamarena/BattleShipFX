import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class GameOver1vs1 {

    public static Scene CreateStage(Stage scene, Label label){
        label.setId("Winner");
        BorderPane layout = new BorderPane();
        VBox VerticalLayout = new VBox();
        scene.setTitle("BattleShip");
        CreateToolBarMenu(layout, scene);
        Scene GameOver = new Scene(layout, 1000, 700);
        AddTitle(layout, VerticalLayout);
        VerticalLayout.getChildren().add(label);
        AddingBackgroundGameOverUser(GameOver);
        AddingButtonstoGameBoard(layout, VerticalLayout, scene);
        scene.setResizable(false);
        scene.show();
        scene.centerOnScreen();

        return GameOver;
    }

    public static void AddTitle(BorderPane layout, VBox VerticalLayout){
        Label label = new Label();
        javafx.scene.image.Image image = new javafx.scene.image.Image("Static/CSS/Images/Victory.gif");
        ImageView title = new ImageView(image);
        title.setFitWidth(750);
        title.setFitHeight(240);
        label.setGraphic(title);
        label.setMaxWidth(Double.MAX_VALUE);
        label.setAlignment(Pos.CENTER);
        VerticalLayout.getChildren().addAll(label);
    }

    public static void AddingButtonstoGameBoard(BorderPane layout, VBox VerticalLayout, Stage scene){

        Button Return = new Button("Victory Royale");
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
        VerticalLayout.setAlignment(Pos.TOP_CENTER);
        layout.setCenter(VerticalLayout);
        layout.setPrefWidth(Region.USE_COMPUTED_SIZE);
        BorderPane.setMargin(VerticalLayout, new Insets(70, 0, 0, 0));
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
        scene.getStylesheets().add("Static/CSS/Victory.css");
    }
}
