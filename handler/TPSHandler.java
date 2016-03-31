/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handler;

import db.DBConnector;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Gudni
 */
public class TPSHandler {
    
    private DBConnector db;

    public TPSHandler() throws ClassNotFoundException, SQLException, IOException {
        
        db = DBConnector.getDB();
    }
    
    public void close(PreparedStatement stmt, ResultSet rs) throws SQLException {
        if (stmt != null) {
            stmt.close();
        }
        if (rs != null) {
            rs.close();
        }
    }
    
    
    
}
