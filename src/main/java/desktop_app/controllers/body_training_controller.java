package desktop_app.controllers;

import desktop_app.App;
import desktop_app.database.get_db_data;
import desktop_app.database.levelup_values;
import desktop_app.database.sql_connect;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class body_training_controller {
    private static final Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
    static Scene thescene;
    private static get_db_data db_data;
    private static String table_name;
    private static sql_connect connection;
    private static levelup_values levels;
    @FXML
    TextField max_pushups_textfield;
    @FXML
    TextField max_situps_textfield;
    @FXML
    Label last_max_push;
    @FXML
    Label last_max_sit;
    @FXML
    Label str_label;
    @FXML
    Label level_string;
    @FXML
    ListView<String> history_list;

    public body_training_controller() {
    }

    @FXML
    void initialize() {
        db_data = new get_db_data("training");
        table_name = db_data.get_db_table();
        this.connection = new sql_connect(db_data.get_db_url(), db_data.get_db_port(), db_data.get_db_name(), db_data.get_db_user(), db_data.get_db_pw());
        this.levels = new levelup_values();
        this.set_max_of_methods();
    }

    @FXML
    public void exit_button_processing() {
        System.exit(0);
    }

    @FXML
    public void back_button_processing() throws IOException {
        int app_stage_width = 600;
        int app_stage_height = 400;
        Stage stage = App.getstage();
        App.Is_training_on = false;
        stage.close();
        Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("app_stage.fxml"));
        stage.setScene(app_stage_controller.getscene());
        double x = this.bounds.getMaxX() / 2.0D - 300.0D;
        double y = this.bounds.getMaxY() / 2.0D - 200.0D;
        stage.setWidth((double)app_stage_width);
        stage.setHeight((double)app_stage_height);
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
        App.Is_training_on = true;
        Parent root = FXMLLoader.load(App.get_base());
        stage.setScene(new Scene(root, (double)base_width, (double)base_height));
        double x = this.bounds.getMaxX() - 60.0D;
        double y = this.bounds.getMaxY() / 1.5D;
        stage.setWidth((double)base_width);
        stage.setHeight((double)base_height);
        stage.setX(x);
        stage.setY(y);
        stage.show();
    }

    @FXML
    public void send_new_max() {
        try {
            this.connection = this.connect_to_sql();
            int level = this.show_level();
            String max_p = "'" + this.max_pushups_textfield.getText() + "'";
            String max_s = "'" + this.max_situps_textfield.getText() + "'";
            String lvl = "'" + level + "'";
            this.last_max_push.setText("last maximum pushups: " + this.max_pushups_textfield.getText());
            this.last_max_sit.setText("last maximum situps: " + this.max_situps_textfield.getText());
            this.connection.insert_data(this.connection.get_connection(), "body_training", "str,max_pushups,max_situps", lvl + "," + max_p + "," + max_s);
            this.show_level();
            this.show_data();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public int show_level() {
        String max_push = this.last_max_push.getText();
        String max_sit = this.last_max_sit.getText();
        int level = this.levels.get_level(Integer.parseInt(max_push.substring(max_push.indexOf(":")).substring(2)), Integer.parseInt(max_sit.substring(max_sit.indexOf(":")).substring(2)));
        this.level_string.setText(this.levels.get_level_string(level));
        this.str_label.setText("Strength level:" + level);
        return level;
    }

    private void set_max_of_methods() {
        Label var10000 = this.last_max_push;
        sql_connect var10001 = this.connection;
        Connection var10002 = this.connection.get_connection();
        var10000.setText("last maximum pushups: " + var10001.get_last_data(var10002, "body_training", "max_pushups"));
        var10000 = this.last_max_sit;
        var10001 = this.connection;
        var10002 = this.connection.get_connection();
        var10000.setText("last maximum situps: " + var10001.get_last_data(var10002, "body_training", "max_situps"));
        this.show_level();
    }

    @FXML
    public void show_data() {
        try {
            this.history_list.getItems().clear();
            this.connection = this.connect_to_sql();
            this.connection.select_query(this.connection.get_connection(), "body_training");
            this.display_data_on_list(this.connection.get_result());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void clear_history() {
        this.history_list.getItems().clear();
        this.connection.purge_table(this.connection.get_connection(), "body_training");
        this.set_max_of_methods();
    }

    private void display_data_on_list(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        if (!rs.next()) {
            System.out.println("its empty inside display");
        }

        rs.beforeFirst();

        while(rs.next()) {
            int i = 1;
            StringBuilder data = new StringBuilder();

            while(i <= columnsNumber) {
                try {
                    data.append(rsmd.getColumnName(i)).append(": ").append(rs.getObject(i)).append(" ");
                    ++i;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            this.history_list.getItems().add(data.toString());
        }

    }

    public static Scene getscene() {
        return thescene;
    }

    private sql_connect connect_to_sql() throws SQLException {
        return !this.connection.get_connection().isClosed() ? this.connection : new sql_connect("db4free.net", "3306", "windsake", "windsake", "123456789");
    }
}
