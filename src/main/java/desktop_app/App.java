package desktop_app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class App extends Application {
    static Stage primarystage;
    static Scene thescene;
    public static boolean Is_search_on=false;
    public static boolean Is_listening_on=false;
    public static boolean Is_calc_on=false;
    public static boolean Is_imperial_on=false;
    public static boolean Is_clipboard_on=false;
    public static boolean Is_training_on=false;

    private void setstage(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("base.fxml"));
        primaryStage.setScene(new Scene(root, 100, 30));
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        double x = bounds.getMaxX() - 60;
        double y = bounds.getMaxY() / 1.5;
        primaryStage.setX(x);
        primaryStage.setY(y);
        primaryStage.show();
        thescene=primaryStage.getScene();
        primarystage = primaryStage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.initStyle(StageStyle.UNDECORATED);
        setstage(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getstage() {
        return primarystage;
    }
    public static Scene getscene(){
        return thescene;
    }
}