package desktop_app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class calculator_controller {
    Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
    static Scene thescene;
    static final String minute_label = "Value/minutes:",hour_label = "Value/hours:",goal_label_value = "Time to reach that goal:";
    @FXML
    TextField second_area;
    @FXML
    TextField goal_value;
    @FXML
    TextField current_value;
    @FXML
    Label minutes_area;
    @FXML
    Label hours_area;
    @FXML
    Label goal_label;

    @FXML
    public void exit_button_processing() {
        System.exit(0);
    }

    @FXML
    public void back_button_processing() throws IOException {
        int app_stage_width = 600;
        int app_stage_height = 400;
        Stage stage = App.getstage();
        App.Is_calc_on = false;
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
        int base_width=100;
        int base_height=30;
        Stage stage = App.getstage();
        thescene = stage.getScene();
        App.Is_calc_on = true;
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
    public void calculate_values(){
        float second_value;
        if(second_area.getLength()>0){
            second_value = Float.parseFloat(second_area.getText());
            minutes_area.setText(minute_label+" "+ (second_value* 60));
            hours_area.setText(hour_label+" "+((second_value* 60)*60));
            if(goal_value.getLength()>0) {
                float seconds,minutes,hours;
                if(current_value.getLength()>0){
                    seconds = (
                            (Float.parseFloat(goal_value.getText())-
                            Float.parseFloat(current_value.getText())
                            )
                            / second_value);
                }else{
                    seconds = (Float.parseFloat(goal_value.getText()) / second_value);
                }
                minutes = seconds / 60;
                hours = minutes / 60;

                minutes = minutes - (int) hours * 60;
                seconds = seconds - (((int) minutes + (int) hours * 60) * 60);
                hours = (int) hours;
                goal_label.setText(goal_label_value + " " +
                        (int) hours + " hours " + (int) minutes +
                        " minutes " + seconds+" seconds");
            }
        }
    }

    public static Scene getscene() {
        return thescene;
    }
}
