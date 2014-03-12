

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
    
    public static ArrayList<Usuario> usuario= new ArrayList();
    
    Connection con = null;
    CallableStatement stmt = null;
    
     public void openConnection() throws SQLException{
          con = DriverManager.getConnection(DB_URL,USER,PASSWORD); 
     }
     
     public ResultSet executeQuery(String sql) throws SQLException{
        stmt = con.prepareCall(sql);
        ResultSet res = stmt.executeQuery();
        return res;
     }
     
     public void getUserInfo() throws SQLException{
        openConnection();
        ResultSet res = executeQuery("SELECT * FROM user");
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
    
    public boolean insertarDatos(String username, String password) throws SQLException{
        openConnection();
        String sql = "INSERT INTO user (username, password, age, name) VALUES ('"+username+"', '"+password+"', '12', '"+username+"');";
        stmt = con.prepareCall(sql);
        stmt.execute(sql);
        closeConnection(stmt,con);
        return true;
    }
    
    public void closeConnection(Statement stmt, Connection con) throws SQLException{
        stmt.close();
        con.close();
    }
    
    public boolean search(String search) throws SQLException{
        boolean returning = false;
        openConnection();
        ResultSet res = executeQuery("SELECT * from user WHERE username='"+search+"'");
        while(res.next()){
            returning = true;
        }
        return returning;
    }
    
}
