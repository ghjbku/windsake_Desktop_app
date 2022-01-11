package desktop_app.database;

import desktop_app.App;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class get_db_data {
    private String data ="";
    private final String db_file_name_training ="\\db_data.txt";
    private final String db_file_name_clip ="\\db_data_clipboard.txt";
    private String[] credentials = new String[6];

    public get_db_data(String which){
        data = get_database_data(which);
        process_data(data);
    }

    private String get_database_data(String which_table){
        String url = "";
        //url port db_name username password table_name
        if(which_table.equals("training")){
            url = db_file_name_training;
        }else if(which_table.equals("clipboard")){
            url = db_file_name_clip;
        }

        try {
            File file = new File(App.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            Scanner myReader = new Scanner(new File(file.getParentFile().getParent()+ url));
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
            }
            myReader.close();
            return data;
        } catch (URISyntaxException | FileNotFoundException e) {
            e.printStackTrace();
            return "error";
        }
    }

    private void process_data(String data){
        credentials = data.split(",");
    }

    public String get_db_url(){
        return credentials[0];
    }
    public String get_db_port(){
        return credentials[1];
    }
    public String get_db_name(){
        return credentials[2];
    }
    public String get_db_user(){
        return credentials[3];
    }
    public String get_db_pw(){
        return credentials[4];
    }
    public String get_db_table(){
        return credentials[5];
    }
}
