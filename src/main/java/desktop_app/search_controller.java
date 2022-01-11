package desktop_app;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.web.*;

import java.io.IOException;

public class search_controller {
    Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
    private static final String GOOGLE_SERACH_URL = "https://www.google.com/search?q=";
    static Scene thescene;

    @FXML
    WebView web_view;
    @FXML
    TextField text_field;

    @FXML
    public void exit_button_processing() {
        System.exit(0);
    }

    @FXML
    public void back_button_processing() throws IOException {
        int app_stage_width = 600;
        int app_stage_height = 400;
        Stage stage = App.getstage();
        App.Is_search_on = false;
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("app_stage.fxml"));
        stage.setScene(app_stage_controller.getscene());
        double x = (bounds.getMaxX() / 2) - 300;
        double y = (bounds.getMaxY() / 2) - 200;
        stage.setWidth(app_stage_width);
        stage.setHeight(app_stage_height);
        stage.setX(x);
        stage.setY(y);
        stage.show();
    }

    @FXML
    public void toSide_button_processing() throws IOException {
        int base_width = 100;
        int base_height = 30;
        Stage stage = App.getstage();
        thescene = stage.getScene();
        App.Is_search_on = true;
        Parent root = FXMLLoader.load(getClass().getResource("base.fxml"));
        stage.setScene(new Scene(root, base_width, base_height));
        double x = bounds.getMaxX() - 60;
        double y = bounds.getMaxY() / 1.5;
        stage.setWidth(base_width);
        stage.setHeight(base_height);
        stage.setX(x);
        stage.setY(y);
        stage.show();
    }

    @FXML
    private void search_key() {
        text_field.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    search_on(text_field);
                }
            }
        });
    }

    @FXML
    public void search_button_processing() {
        System.out.println("search pressed");
        search_on(text_field);
    }

    private void search_on(TextField text) {
        Stage stage = App.getstage();
        stage.setWidth(1280);
        stage.setHeight(768);
        double x = (bounds.getMaxX() / 2) - 640;
        double y = (bounds.getMaxY() / 2) - 384;
        stage.setX(x);
        stage.setY(y);
        web_view.setPrefWidth(1280);
        web_view.setPrefHeight(768);
        stage.show();
        String searchKey = text.getText();
        web_view.getEngine().load(GOOGLE_SERACH_URL + searchKey);
    }

    public static Scene getscene() {
        return thescene;
    }

}
