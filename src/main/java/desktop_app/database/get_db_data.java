package desktop_app.database;

import desktop_app.App;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Scanner;

public class get_db_data {

    public String get_database_data(){
        //url port db_name username password table_name
        String data="empty";
        try {
            File file = new File(App.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            Scanner myReader = new Scanner(file.getParent() + "/db_data.txt");
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
            }
            myReader.close();
            return data;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return "error";
        }
    }
}
