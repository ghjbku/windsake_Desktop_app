package desktop_app.controllers;

import desktop_app.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class Controller {
    Rectangle2D bounds = Screen.getPrimary().getVisualBounds();

    private void set_primarystage(String fxml_name) throws IOException {
        int app_stage_width = 600;
        int app_stage_height = 400;
        Stage primarystage = App.getstage();
        primarystage.hide();
        Parent root = FXMLLoader.load(getClass().getResource(fxml_name));
        primarystage.setScene(new Scene(root, app_stage_width, app_stage_height));
        double x = (bounds.getMaxX() / 2) - 300;
        double y = (bounds.getMaxY() / 2) - 200;
        primarystage.setWidth(app_stage_width);
        primarystage.setHeight(app_stage_height);
        primarystage.setX(x);
        primarystage.setY(y);
        primarystage.show();
        App.Is_clipboard_on=true;
    }

    private void set_stage_search() {
            Stage primarystage = App.getstage();
            primarystage.setScene(search_controller.getscene());
            double x = (bounds.getMaxX() / 2) - 640;
            double y = (bounds.getMaxY() / 2) - 384;
            primarystage.setWidth(1280);
            primarystage.setHeight(768);
            primarystage.setX(x);
            primarystage.setY(y);
            primarystage.show();
        App.Is_search_on=false;
    }
    private void set_stage_clipboard() {
        Stage primarystage = App.getstage();
        primarystage.setScene(app_stage_controller.getscene());
        double x = (bounds.getMaxX() / 2) - 300;
        double y = (bounds.getMaxY() / 2) - 200;
        primarystage.setWidth(600);
        primarystage.setHeight(400);
        primarystage.setX(x);
        primarystage.setY(y);
        primarystage.show();
    }

    private void set_stage_listener() {
        Stage primarystage = App.getstage();
        primarystage.setScene(listener_controller.getscene());
        double x = (bounds.getMaxX() / 2) - 640;
        double y = (bounds.getMaxY() / 2) - 384;
        primarystage.setWidth(1280);
        primarystage.setHeight(768);
        primarystage.setX(x);
        primarystage.setY(y);
        primarystage.show();
        App.Is_listening_on=false;
    }

    private void set_stage_calculator() {
        Stage primarystage = App.getstage();
        primarystage.setScene(calculator_controller.getscene());
        double x = (bounds.getMaxX() / 2) - 300;
        double y = (bounds.getMaxY() / 2) - 200;
        primarystage.setWidth(600);
        primarystage.setHeight(400);
        primarystage.setX(x);
        primarystage.setY(y);
        primarystage.show();
        App.Is_calc_on=false;
    }
    private void set_stage_imperial() {
        Stage primarystage = App.getstage();
        primarystage.setScene(imperial_controller.getscene());
        double x = (bounds.getMaxX() / 2) - 300;
        double y = (bounds.getMaxY() / 2) - 200;
        primarystage.setWidth(600);
        primarystage.setHeight(400);
        primarystage.setX(x);
        primarystage.setY(y);
        primarystage.show();
        App.Is_imperial_on=false;
    }
    private void set_stage_training() {
        Stage primarystage = App.getstage();
        primarystage.setScene(body_training_controller.getscene());
        double x = (bounds.getMaxX() / 2) - 300;
        double y = (bounds.getMaxY() / 2) - 200;
        primarystage.setWidth(600);
        primarystage.setHeight(400);
        primarystage.setX(x);
        primarystage.setY(y);
        primarystage.show();
        App.Is_training_on=false;
    }

    @FXML
    public void open_button_processing() throws IOException {
        if (App.Is_search_on) {
            set_stage_search();
        } else if (App.Is_listening_on) {
            set_stage_listener();
        }
        else if(App.Is_calc_on){
            set_stage_calculator();
        }
        else if(App.Is_imperial_on){
            set_stage_imperial();
        }
        else if(App.Is_training_on){
            set_stage_training();
        }
        else if(App.Is_clipboard_on){
            set_stage_clipboard();
        }
        else {
            set_primarystage("app_stage.fxml");
        }
    }

    @FXML
    public void mouse_entered() {
        Stage primarystage = App.getstage();
        double x = bounds.getMaxX() - 80;
        primarystage.setX(x);
    }

    @FXML
    public void mouse_left() {
        Stage primarystage = App.getstage();
        double x = bounds.getMaxX() - 60;
        primarystage.setX(x);
    }

    @FXML
    public void dragging() {
        Stage stage = App.getstage();
        Point p = MouseInfo.getPointerInfo().getLocation();
        stage.setY(p.y);
    }


}
