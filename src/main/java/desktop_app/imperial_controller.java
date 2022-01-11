package desktop_app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class imperial_controller {
    Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
    static Scene thescene;
    private final imperial_data data = new imperial_data();
    int box_tier_bonus_price = 0;
    long accumulated = 0;
    boolean is_true = false;
    @FXML
    TextField mastery_points;
    @FXML
    TextField box_tier_field;
    @FXML
    TextField boxes_sold;
    @FXML
    TextField silver_spent;
    @FXML
    Label gain_label;
    @FXML
    Label bonus_label;
    @FXML
    Label acc_profit_label;
    @FXML
    Label tier_label;
    @FXML
    CheckBox hasbonus;

    @FXML
    public void exit_button_processing() {
        System.exit(0);
    }

    @FXML
    public void back_button_processing() throws IOException {
        int app_stage_width = 600;
        int app_stage_height = 400;
        Stage stage = App.getstage();
        App.Is_imperial_on = false;
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
        App.Is_imperial_on = true;
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
    public void calculate() {
        if (mastery_points.getLength() > 0 && box_tier_field.getLength() > 0 && boxes_sold.getLength() > 0) {
            final int fetched_mastery_points = Integer.parseInt(mastery_points.getText());
            final float percentage = data.get_percentage(fetched_mastery_points);
            bonus_label.setText("% bonus from your mastery: " + percentage);

            String fetched_box_tier = box_tier_field.getText();
            final int box_tier_base_price = data.get_tier_data(fetched_box_tier);
            if (is_true) {
                box_tier_bonus_price = (int) (box_tier_base_price * 2.5);
            } else {
                box_tier_bonus_price = box_tier_base_price;
            }

            final int fetched_box_number = Integer.parseInt(boxes_sold.getText());
            int gain = fetched_box_number * (box_tier_bonus_price + (int) (box_tier_base_price * (percentage / 100)));
            accumulated += gain;
            if (String.valueOf(gain).chars().filter(ch -> ch != ' ').count() > 6) {
                final int how_many_million = gain / 1000000;
                final int removed = gain - (how_many_million * 1000000);
                gain_label.setText("your gain this round: " + how_many_million + " million " + removed);
            } else {
                gain_label.setText("your gain this round: " + gain);
            }
            //System.out.println(String.valueOf(accumulated).chars().filter(ch -> ch != ' ').count());
            StringBuilder helper_string = new StringBuilder(String.valueOf(accumulated).length() + 4);
            for (int i = 0; i < String.valueOf(accumulated).length(); i++) {
                if (i == 2 || i == 5 || i == 7 || i == 9) {
                    helper_string.append(String.valueOf(accumulated).charAt(i));
                    helper_string.append(' ');
                } else {
                    helper_string.append(String.valueOf(accumulated).charAt(i));
                }
            }
            acc_profit_label.setText("your accumulated profit: " + helper_string/*.reverse()*/);
        } else {
            System.out.println("something is empty");
        }
    }

    @FXML
    public void show_box_tiers() {
        tier_label.setVisible(true);
    }

    @FXML
    public void hide_box_tiers() {
        tier_label.setVisible(false);
    }

    @FXML
    public void on_clicked() {
        is_true = !is_true;
    }

    public static Scene getscene() {
        return thescene;
    }
}
