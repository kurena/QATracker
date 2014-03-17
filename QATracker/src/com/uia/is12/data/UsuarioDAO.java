

package com.uia.is12.data;


import java.sql.*;
import com.uia.is12.domain.Usuario;
import com.uia.is12.connections.MySQLDB;
import java.util.ArrayList;

public class UsuarioDAO {
    
    private MySQLDB mysqlDB;
    private static String user;
   
    public boolean validarDatosLogin(Usuario usuario) throws SQLException{
        boolean exist = false;
        mysqlDB = new MySQLDB();
        String sql = "SELECT * from user WHERE username='"+usuario.getUsername()+"' AND password='"+usuario.getPassword()+"'";
        ResultSet res = mysqlDB.executeQuery(sql);
        if(res.next()){
            exist = true;
        }
        mysqlDB.closeExecuteQuery();
        return exist;
    } 
    
    public void insertarDatos(Usuario usuario) throws SQLException{
        mysqlDB = new MySQLDB();
        String sql = "INSERT INTO user (username, password, name, age, lastname, role) VALUES ('"+usuario.getUsername()+"', '"+usuario.getPassword()+"', '"+usuario.getName()+"',"+usuario.getEdad()+", '"+usuario.getLastname()+"','desarrollador');";
        mysqlDB.execute(sql);
        mysqlDB.closeExecute();
    }
    
    public boolean search(Usuario usuario) throws SQLException{
        boolean returning = false;
        mysqlDB = new MySQLDB();
        String sql = "SELECT * from user WHERE username='"+usuario.getUsername()+"'";
        ResultSet res = mysqlDB.executeQuery(sql);
        if(res.next()){
            returning = true;
        }
        mysqlDB.closeExecuteQuery();
        return returning;
    }
    
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        UsuarioDAO.user = user;
    }
}
