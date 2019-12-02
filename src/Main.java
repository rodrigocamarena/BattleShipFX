import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(MenuGame.CreateStage(primaryStage));
        primaryStage.show();
        primaryStage.centerOnScreen();
    }

    public static void main(String[]args){
        Application.launch(Main.class, args);
    }
}
