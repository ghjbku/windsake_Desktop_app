package desktop_app;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.net.URISyntaxException;
import java.time.LocalDateTime;


public class listener_controller {
    Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
    File file = new File(App.class.getProtectionDomain().getCodeSource().getLocation().toURI());
    static Scene thescene;
    private EventHandler<MouseEvent> eventhandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            log_on(text_field, mouseEvent);
        }
    };


    public listener_controller() throws URISyntaxException {
    }

    private void write_to_file(File file, String text) throws IOException{
        if (file==null){
            throw new IllegalArgumentException("file cant be null");
        }
        if (text==null)
        {
            throw new IllegalArgumentException("text cant be null");
        }
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file.getParent() + "/mouse_log.txt")))){
            writer.write(text);
        }
        System.out.println("file created at path: "+file.getParent());
    }

    @FXML
    TextArea text_field;
    @FXML
    AnchorPane thepane;
    @FXML
    Button switch_button;

    @FXML
    public void exit_button_processing() throws IOException {
        write_to_file(file,text_field.getText());
        System.exit(0);
    }

    @FXML
    public void back_button_processing() throws IOException {
        int app_stage_width = 600;
        int app_stage_height = 400;
        Stage stage = App.getstage();
        App.Is_listening_on = false;
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
        App.Is_listening_on = true;
        thescene = stage.getScene();
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

    boolean is_pressed = false;

    @FXML
    public void start_button_processing() throws IOException {
        System.out.println("start pressed ");
        if (!is_pressed) {
            is_pressed = true;
            text_field.setVisible(false);
            App.getstage().setOpacity(0.1);
            thepane.addEventFilter(MouseEvent.MOUSE_CLICKED, eventhandler);
            App.Is_listening_on=true;
        } else if (is_pressed) {
            is_pressed = false;
            text_field.setVisible(true);
            App.getstage().setOpacity(1);
            thepane.removeEventFilter(MouseEvent.MOUSE_CLICKED, eventhandler);
            write_to_file(file,text_field.getText());
            App.Is_listening_on=false;
        }
    }

    private void log_on(TextArea text, MouseEvent event) {
        Stage stage = App.getstage();
        stage.setWidth(bounds.getMaxX());
        stage.setHeight(bounds.getMaxY());
        double x = 0;
        double y = 0;
        stage.setX(x);
        text.setLayoutX(x+50);
        stage.setY(y);
        text.setLayoutY(y+50);
        stage.show();
        int hour = LocalDateTime.now().getHour();
        int minute = LocalDateTime.now().getMinute();
        int sec = LocalDateTime.now().getSecond();

            //TimeUnit.MILLISECONDS.sleep(100);
            text.setText(text.getText() + "\n" + hour + ":" + minute + ":" + sec + "\nevent type: " + event.getEventType().toString()+" source: "+event.getSource().toString()+"\n coords: x="+MouseInfo.getPointerInfo().getLocation().x+" y="+MouseInfo.getPointerInfo().getLocation().y);
            System.out.println(text.getText() + "\n" + hour + ":" + minute + ":" + sec + "\nevent type: " + event.getEventType().toString()+" source: "+event.getSource().toString()+"\n coords: x="+MouseInfo.getPointerInfo().getLocation().x+" y="+MouseInfo.getPointerInfo().getLocation().y);
    }

    public static Scene getscene() {
        return thescene;
    }

}
