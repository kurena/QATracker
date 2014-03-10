

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
    
    ArrayList<Usuario> usuario= new ArrayList();
    
    Connection con = null;
    CallableStatement stmt = null;
    
     public void getUserInfo() throws SQLException{
        
        con = DriverManager.getConnection(DB_URL,USER,PASSWORD);
        String sql = "SELECT * FROM user";
        stmt = con.prepareCall(sql);
        ResultSet res = stmt.executeQuery();
        while(res.next()){
            usuario.add(new Usuario(res.getString("username"),res.getString("password")));
        }
        stmt.close();
        con.close();
    }
     
    public boolean validarDatosLogin(String username, String password){
        boolean dec = false;
        for(Usuario u: usuario){
            if(u.getUsername().equals(username) && u.getPassword().equals(password)){
                dec = true;
            }
        }
        
        return dec;
    } 
    
}
