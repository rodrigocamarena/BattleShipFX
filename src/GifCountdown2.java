import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class GifCountdown2 {

    public static Scene CreateStage(Stage scene, Button [][]Board1, GridPane gridPane1, Button [][]Board2, GridPane gridPane2){
        BorderPane layout = new BorderPane();
        VBox box = new VBox();
        Label label = new Label();
        javafx.scene.image.Image image = new javafx.scene.image.Image("src/Static/CSS/Images/Countdown3.gif");
        label.setGraphic(new ImageView(image));
        label.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(label, 0.0);
        AnchorPane.setRightAnchor(label, 0.0);
        label.setAlignment(Pos.CENTER);
        Button Next = CreateNextButton(scene, Board1, gridPane1, Board2, gridPane2);
        AnchorPane.setLeftAnchor(box, 0.0);
        AnchorPane.setRightAnchor(box, 0.0);
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(label, Next);
        layout.setCenter(box);
        BorderPane.setMargin(box, new Insets(0,0,0,0));
        Scene MenuGame = new Scene(layout, 1000, 700);
        AddingBackgroundtoScene(MenuGame);
        scene.setResizable(false);
        scene.show();
        scene.centerOnScreen();
        return MenuGame;
    }

    public static Button CreateNextButton(Stage scene, Button [][]Board1, GridPane gridPane1, Button [][]Board2, GridPane gridPane2){
        Button Next = new Button("Next");
        Next.setId("");
        Next.setOnAction(e ->{
            gridPane1.setDisable(false);
            try {
                gridPane1.setDisable(false);
                scene.setScene(Game1vs1.CreateStage(scene, Board2, gridPane2, Board1, gridPane1));
                scene.show();
                scene.centerOnScreen();
                Next.setDisable(false);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        return Next;
    }

    public static void Timer(Button Next){
        long starttime = System.currentTimeMillis();
        long endtime = starttime + 5000;
        while(starttime<endtime){
            starttime = System.currentTimeMillis();
        }
        Next.setVisible(true);
    }
    public static void AddingBackgroundtoScene(Scene scene){
        scene.getStylesheets().add("Static/CSS/GifCountdown.css");
    }
}
