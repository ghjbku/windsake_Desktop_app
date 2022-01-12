package desktop_app.database;

import java.sql.*;

public class sql_connect {
    Connection con;
    ResultSet result_set;

    /**
     * creates connection to the database.
     * @param url the url of the database, string
     * @param port the port of the database, string
     * @param db_name the name of the database, string
     * @param username the username, string
     * @param password the password, string
     */
    public sql_connect(String url, String port, String db_name, String username, String password) {
        try {
            con = connect_to_db(url, port, db_name, username, password);
            System.out.println("connected to db");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Connection connect_to_db(String url, String port, String db_name, String username, String password) throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://" + url + ":" + port + "/" + db_name, username, password);
        return con;
    }

    /**
     *select all data from the given table
     * @param con a connection you can get from sql_connect.get_connection()
     * @param table_name a string
     * @throws SQLException if the connection is not available
     */
    public void select_query(Connection con, String table_name) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from " + table_name);
        set_result(rs);
        ResultSetMetaData rsmd= rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();

        if(!rs.next()){
            System.out.println("its empty");
        }
        rs.beforeFirst();
        System.out.println("\n the database contains:\n");
        while (rs.next()) {
            int i = 1;
            StringBuilder data = new StringBuilder();
            while (i<=columnsNumber) {
                try {
                    //Display values
                    data.append(rsmd.getColumnName(i)).append(": ").append(rs.getObject(i)).append(" ");
                    i++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println(data);
        }
    }

    /**
     * gets the last data of the chosen column
     * @param con connection you can get with sql_connect.get_connection()
     * @param table_name name of the table
     * @param col_name name of the column
     * @return the last value of the chosen col
     */
    public String get_last_data(Connection con, String table_name,String col_name){
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select "+col_name+" from " + table_name);
            String last_max = "0";
            while (rs.next()) {
                last_max =String.valueOf(rs.getInt(1));
                System.out.println(last_max);
            }
            return last_max;
        }
        catch (Exception e){
            e.printStackTrace();
            return "0";
        }
    }

    private void set_result(ResultSet res){
        result_set = res;
    }

    public ResultSet get_result(){
        return result_set;
    }

    /**
     * delete data based on id in a give table.
     * @param conn a connection you can get from sql_connect.get_connection()
     * @param table_name a string
     * @param data_name a string (can be multiple if separated with ,)
     * @param data a string (can be multiple if separated with ,) eg: 'asd','asd2'
     */
    public void insert_data(Connection conn, String table_name,String data_name, String data) {
        try {
            Statement st = conn.createStatement();

            // note that i'm leaving "date_created" out of this insert statement
            st.executeUpdate("INSERT INTO " + table_name + " ("+data_name+") "
                    + "VALUES (" + data + ")");
        } catch (Exception e) {
            System.err.println("Got an exception while inserting! tablename: "+table_name + " dataname: "+data_name+" data:"+data);
            System.err.println(e.getMessage());
        }
    }

    /**
     * delete data based on id in a give table.
     * @param conn a connection you can get from sql_connect.get_connection()
     * @param table_name a string
     * @param id an integer
     */
    public void delete_data(Connection conn, String table_name, int id) {
        try {
            String query = "delete from " + table_name + " where id = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, id);
            preparedStmt.execute();

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    /**
     * creates a table with an id and 1 data row,which you can name and give type to.
     * the id is set to autoincrement.
     * @param table_name a string
     * @param data a string that contains data_name and data_type.(after the last line, put a , )
     * @param conn a connection you can get from sql_connect.get_connection()
     * example: con.create_table(table_name,"str INTEGER not NULL,max_pushups INTEGER not NULL,max_situps INTEGER not NULL,", con.get_connection());
     */
    public void create_table(String table_name,String data, Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE " + table_name +
                    " (id INTEGER auto_increment not NULL, " +
                    data+
                    " PRIMARY KEY ( id ))";

            stmt.executeUpdate(sql);
            System.out.println("Table created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * removes all data from the given table.
     * @param conn a connection you can get from sql_connect.get_connection()
     * @param table_name a string
     */
    public void purge_table(Connection conn,String table_name){
        try {
            Statement stmt = conn.createStatement();
            String sql = "TRUNCATE TABLE " + table_name;
            stmt.executeUpdate(sql);
            System.out.println("Table purged");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * drops the table
     * @param conn a connection you can get from sql_connect.get_connection()
     * @param table_name a string
     */
    public void drop_table(Connection conn, String table_name) {
        try (Statement stmt = conn.createStatement();
        ) {
            String sql = "DROP TABLE " + table_name;
            stmt.executeUpdate(sql);
            System.out.println("Table deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close_connection(Connection conn) throws SQLException {
        conn.close();
    }

    /**
     * gets the current sql connection.
     * @return the sql connection
     */
    public Connection get_connection() {
        return con;
    }
}
