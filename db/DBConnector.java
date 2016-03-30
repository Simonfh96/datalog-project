/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Gudni
 */
public class DBConnector {
    
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private String database_usr = "";
    private String database_pwd = "";
    private String database_url = "";
    private String schema = "";
    private FileHandler fw;
    private Connection con;
    private PreparedStatement stmt;
    private static DBConnector instance;

    private DBConnector() throws ClassNotFoundException, SQLException, IOException {
        Class.forName(JDBC_DRIVER);
        fw = new FileHandler("DatabaseIndstillinger.txt");
        ArrayList<String> dbSettings = fw.openFile();
        database_usr = dbSettings.get(0);
        database_pwd = dbSettings.get(1);
        database_url = dbSettings.get(2);
        schema = dbSettings.get(3);
    }

    public void connectToDB() throws SQLException {
        if (isConnected() == false || con == null) {
            con = DriverManager.getConnection("jdbc:mysql://" + database_url + "/" + schema, database_usr, database_pwd);
        }
    }

    public void disconnectDB() throws SQLException {
        if (stmt != null) {
            if (!stmt.isClosed()) {
                stmt.close();
            }
        }
        con.close();
    }

    public void reconnectToDB() throws SQLException {
        if (isConnected()) {
            disconnectDB();
        }
        connectToDB();
    }

    public void updateDBConn(String usr, String pw, String url, String schema) throws SQLException, FileNotFoundException, UnsupportedEncodingException {
        database_usr = usr;
        database_pwd = pw;
        database_url = url;
        this.schema = schema;
        reconnectToDB();
        fw.updateDBFile(usr, pw, url, schema);
    }

    public boolean isConnected() throws SQLException {
        if (con != null) {
            return con.isValid(0);
        }
        return false;
    }

    public static DBConnector getDB() throws ClassNotFoundException, SQLException, IOException {
        if (instance == null) {
            instance = new DBConnector();
        }
        return instance;
    }

    public PreparedStatement getPrepStmt(String stm) throws SQLException {
        connectToDB();
        stmt = con.prepareStatement(stm);
        return stmt;
    }

}
    

