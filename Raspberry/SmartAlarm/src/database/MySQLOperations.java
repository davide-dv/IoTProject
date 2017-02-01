package database;

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

    public synchronized boolean addEvent(TYPOLOGY typology) {
        return this.insertQuery("INSERT INTO events(typology) " + "VALUES ('"+typology.getTypology()+"')");
    }

    public synchronized boolean addTemperature(String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        return this.insertQuery("INSERT INTO temperature(value) " + "VALUES ('"+value+"')");
    }

    private boolean insertQuery(String sql) {
        try {
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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
