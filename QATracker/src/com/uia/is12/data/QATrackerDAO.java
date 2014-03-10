

package com.uia.is12.data;


import java.sql.SQLException;
import com.uia.is12.domain.Usuario;
import java.sql.*;
import java.util.ArrayList;

public class QATrackerDAO {
    
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/qa_tracker";
    
    static final String USER = "root";
    static final String PASSWORD = "root";
    
    Connection con = null;
    CallableStatement stmt = null;
    
     public boolean getUserInfo(String username, String password) throws SQLException{
        
        con = DriverManager.getConnection(DB_URL,USER,PASSWORD);
        String sql = "SELECT * FROM user WHERE username='" + username +"' AND password='" + password +"'";
        stmt = con.prepareCall(sql);
        ResultSet res = stmt.executeQuery();
        
        if(res.next()){
            
            return true;
            
        }else{
            
            return false;
        }
            
     
    }
    
}
