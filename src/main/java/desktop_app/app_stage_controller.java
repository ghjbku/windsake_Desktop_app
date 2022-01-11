package desktop_app;

import desktop_app.database.get_db_data;
import desktop_app.database.sql_connect;
import desktop_app.key_listener.global_key_listener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ToolBar;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class app_stage_controller {
    private static boolean ctrlPressed = false;
    static Scene thescene;
    static global_key_listener dummy_listener;
    private static get_db_data db_data;
    sql_connect connection;
    private static String table_name;
    Rectangle2D bounds = Screen.getPrimary().getVisualBounds();

    @FXML
    ListView<String> clipboard_list;
    @FXML
    ToolBar first_tab;
    @FXML
    ToolBar second_tab;

    @FXML
    void initialize() {
        set_up_listener();
        db_data = new get_db_data("clipboard");
        table_name = db_data.get_db_table();
        connection = new sql_connect(db_data.get_db_url(), db_data.get_db_port(), db_data.get_db_name(), db_data.get_db_user(), db_data.get_db_pw());
    }

    public void set_up_listener() {
        global_key_listener global_key_listener;
        if (dummy_listener == null) {
            global_key_listener = new global_key_listener(ctrlPressed, clipboard_list);
            dummy_listener = global_key_listener;
        } else {
            global_key_listener = dummy_listener;
        }
    }

    @FXML
    public void exit_button_processing() throws SQLException {
        if (!connection.get_connection().isClosed()) {
            connection.close_connection(connection.get_connection());
        }

        System.exit(0);
    }

    private static void setThescene(Stage stage) {
        thescene = stage.getScene();
    }

    @FXML
    public void toSide_button_processing() throws IOException {
        int base_width = 100;
        int base_height = 30;
        Stage stage = App.getstage();
        App.Is_clipboard_on = true;
        setThescene(stage);
        Parent root = FXMLLoader.load(getClass().
                getResource("base.fxml"));
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
    public void search_button_processing() throws IOException {
        Stage primarystage = App.getstage();
        setThescene(primarystage);
        Parent root = FXMLLoader.load(getClass().
                getResource("search_stage.fxml"));
        primarystage.setScene(new Scene(root, 600, 400));
        double x = (bounds.getMaxX() / 2) - 300;
        double y = (bounds.getMaxY() / 2) - 200;
        primarystage.setX(x);
        primarystage.setY(y);
        primarystage.show();
    }

    @FXML
    public void mouse_listener_button_processing() throws IOException {
        Stage primarystage = App.getstage();
        setThescene(primarystage);
        Parent root = FXMLLoader.load(getClass().
                getResource("listener_stage.fxml"));
        primarystage.setScene(new Scene(root, 600, 400));
        double x = (bounds.getMaxX() / 2) - 300;
        double y = (bounds.getMaxY() / 2) - 200;
        primarystage.setX(x);
        primarystage.setY(y);
        primarystage.show();
    }

    @FXML
    public void calculator_button_processing() throws IOException {
        Stage primarystage = App.getstage();
        setThescene(primarystage);
        Parent root = FXMLLoader.load(getClass().
                getResource("calculator_stage.fxml"));
        primarystage.setScene(new Scene(root, 600, 400));
        double x = (bounds.getMaxX() / 2) - 300;
        double y = (bounds.getMaxY() / 2) - 200;
        primarystage.setX(x);
        primarystage.setY(y);
        primarystage.show();
    }

    @FXML
    public void imperial_button_processing() throws IOException {
        Stage primarystage = App.getstage();
        setThescene(primarystage);
        Parent root = FXMLLoader.load(getClass().
                getResource("imperial_stage.fxml"));
        primarystage.setScene(new Scene(root, 600, 400));
        double x = (bounds.getMaxX() / 2) - 300;
        double y = (bounds.getMaxY() / 2) - 200;
        primarystage.setX(x);
        primarystage.setY(y);
        primarystage.show();
    }

    @FXML
    public void next_tab(){
        first_tab.setVisible(false);
        second_tab.setVisible(true);
    }
    @FXML
    public void back_to_last_tab(){
        first_tab.setVisible(true);
        second_tab.setVisible(false);
    }

    @FXML
    public void open_body_training(){
        try{
            Stage primarystage = App.getstage();
            setThescene(primarystage);
            Parent root = FXMLLoader.load(getClass().
                    getResource("body_training_stage.fxml"));
            primarystage.setScene(new Scene(root, 600, 400));
            double x = (bounds.getMaxX() / 2) - 300;
            double y = (bounds.getMaxY() / 2) - 200;
            primarystage.setX(x);
            primarystage.setY(y);
            primarystage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    @FXML
    public void handleMouseClick(MouseEvent arg0) throws SQLException {
        final ClipboardContent content = new ClipboardContent();

        if (connection.get_connection().isClosed()) {
            connection = new sql_connect(db_data.get_db_url(), db_data.get_db_port(), db_data.get_db_name(), db_data.get_db_user(), db_data.get_db_pw());
        }
        Connection con = connection.get_connection();
        try {
            if (!clipboard_list.getItems().isEmpty()){
                content.putString(clipboard_list.getSelectionModel().getSelectedItem());
                Clipboard.getSystemClipboard().
                        setContent(content);
                connection.insert_data(con, table_name,"data", content.getString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        connection.select_query(con, table_name);
    }

    @FXML
    public void create_db() throws SQLException {
        if (connection.get_connection().isClosed()) {
            connection = new sql_connect(db_data.get_db_url(), db_data.get_db_port(), db_data.get_db_name(), db_data.get_db_user(), db_data.get_db_pw());
        }
        Connection con = connection.get_connection();
        connection.select_query(con, table_name);
    }

    @FXML
    public void purge_table() throws SQLException {
        if (connection.get_connection().isClosed()) {
            connection = new sql_connect(db_data.get_db_url(), db_data.get_db_port(), db_data.get_db_name(), db_data.get_db_user(), db_data.get_db_pw());
        }
        Connection con = connection.get_connection();
        connection.purge_table(con, table_name);
        connection.select_query(con, table_name);
    }

    @FXML
    public void clear_list() {
        clipboard_list.getItems().clear();
    }

    public static Scene getscene() {
        return thescene;
    }
}
