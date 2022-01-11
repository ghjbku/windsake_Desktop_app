package desktop_app.database;

import desktop_app.App;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class get_db_data {
    private String data ="";
    private String[] credentials = new String[6];

    public get_db_data(){
        data = get_database_data();
        process_data(data);
    }

    private String get_database_data(){
        //url port db_name username password table_name
        try {
            File file = new File(App.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            System.out.println(file);
            Scanner myReader = new Scanner(new File(file.getParentFile().getParent()+ "\\db_data.txt"));
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
