import java.sql.*;

/**
 * Created by davide on 25/01/17.
 */
public class MySQLOperations implements DBOperations{
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/jarvis_db";

    private static final String USER = "root";
    private static final String PASS = "";

    private Connection conn = null;
    private Statement stmt = null;

    public MySQLOperations(){
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addEvent(String date, String typology, String note) {
        if (date == null || date.isEmpty() || typology == null || typology.isEmpty()) {
            return;
        }
        try {
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String sql = "INSERT INTO events(date,typology,note) " + "VALUES ('"+date+"','"+typology+"','"+note+"')";
            //System.out.print(sql);
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }

    public void addTemperature(String date, String value) {
        if (date == null || date.isEmpty() || value == null || value.isEmpty()) {
            return;
        }
        try {
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String sql = "INSERT INTO temperature(date,value) " + "VALUES ('"+date+"','"+value+"')";
            //System.out.print(sql);
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }

}
