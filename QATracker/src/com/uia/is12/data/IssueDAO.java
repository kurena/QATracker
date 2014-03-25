/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uia.is12.data;

import com.uia.is12.connections.MySQLDB;
import com.uia.is12.domain.Issue;
import com.uia.is12.domain.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author raiam.quesada.urena
 */
public class IssueDAO {
        private MySQLDB mysqlDB;
        private UsuarioDAO userDAO;
        
       public void insertarDatos(Issue issue) throws SQLException{
        mysqlDB = new MySQLDB();
        issue.setAttachment(issue.getAttachment().replaceAll("\\\\", "\\\\\\\\"));
        String sql = "INSERT INTO issue(name, description, idUserCreador, idUserAsignar,path) VALUES ('"+issue.getName()+"', '"+issue.getDescription()+"','"+issue.getIdUserCreador()+"', '"+issue.getIdUserAsignar()+"','"+issue.getAttachment()+"')";
        mysqlDB.execute(sql);
        mysqlDB.closeExecute();
    }
    
    public ArrayList<Issue> search() throws SQLException{
        ArrayList<Issue> arreglo = new ArrayList<Issue>();
        mysqlDB = new MySQLDB();
        String sql = "SELECT * from issue m, user u,user us WHERE idUserCreador="+getUserID()+" AND m.idUserCreador=u.iduser AND m.idUserAsignar=us.iduser";
        ResultSet res = mysqlDB.executeQuery(sql);
        while(res.next()){
            arreglo.add(new Issue(res.getString("name"), res.getString("description"), res.getInt("idUserCreador"), res.getInt("idUserAsignar"), res.getInt("idissue"), res.getString("u.username"),res.getString("us.username"),res.getString("path")));
        }
        mysqlDB.closeExecuteQuery();
        return arreglo;
    }
    public Issue search(int id) throws SQLException{
        Issue arreglo = new Issue();
        mysqlDB = new MySQLDB();
        String sql = "SELECT * from issue m, user u,user us WHERE m.idissue="+id+" AND m.idUserCreador=u.iduser AND m.idUserAsignar=us.iduser";
        ResultSet res = mysqlDB.executeQuery(sql);
        while(res.next()){
            arreglo = new Issue(res.getString("name"), res.getString("description"), res.getInt("idUserCreador"), res.getInt("idUserAsignar"), res.getInt("idissue"), res.getString("u.username"),res.getString("us.username"), res.getString("path"));
        }
        mysqlDB.closeExecuteQuery();
        return arreglo;
    }
    
    public int getUserID() throws SQLException{
        mysqlDB = new MySQLDB();
        int id = 0;
        String sql = "SELECT * from user WHERE username='"+new UsuarioDAO().getUser()+"'";
        ResultSet res = mysqlDB.executeQuery(sql);
        while(res.next()){
            id = res.getInt("iduser");
        }
        return id;
    }
    
    public void updateData(int id, Issue issue) throws SQLException{
        mysqlDB = new MySQLDB();
        issue.setAttachment(issue.getAttachment().replaceAll("\\\\", "\\\\\\\\"));
        System.out.println(issue.getIdUserAsignar());
        String sql="UPDATE issue SET name='"+issue.getName()+"',description='"+issue.getDescription()+"', idUserAsignar='"+issue.getIdUserAsignar()+"', path='"+issue.getAttachment()+"' WHERE idissue='"+id+"'";
        mysqlDB.execute(sql);
        mysqlDB.closeExecute();
    }
    
}
