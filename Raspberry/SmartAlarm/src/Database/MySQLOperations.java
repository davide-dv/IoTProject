package Database;

import java.sql.*;
import java.lang.*;

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

    public boolean addEvent(String typology) {
        if (typology == null || typology.isEmpty()) {
            return false;
        }
        try {
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String sql = "INSERT INTO events(typology,note) " + "VALUES ('"+typology+"')";
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
        return true;
    }

    public boolean addTemperature(String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        try {
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String sql = "INSERT INTO temperature(value) " + "VALUES ('"+value+"')";
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
        return true;
    }
}
