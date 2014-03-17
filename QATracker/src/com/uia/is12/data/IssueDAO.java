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
        String sql = "INSERT INTO issue (name, description, issuecol, idUserCreador,idUserAsignar) VALUES ('"+issue.getName()+"', '"+issue.getDescription()+"', 'null',"+issue.getIdUserCreador()+", '"+issue.getIdUserAsignar()+"'";
        mysqlDB.execute(sql);
        mysqlDB.closeExecute();
    }
    
    public ArrayList<Issue> search() throws SQLException{
        ArrayList<Issue> arreglo = new ArrayList<Issue>();
        mysqlDB = new MySQLDB();
        String sql = "SELECT * from issue WHERE idUserCreador=1";
        ResultSet res = mysqlDB.executeQuery(sql);
        if(res.next()){
            arreglo.add(new Issue(res.getString("name"), res.getString("description"), res.getInt("idUserCreador"), res.getInt("idUserAsignar"), res.getInt("idissue")));
        }
        mysqlDB.closeExecuteQuery();
        return arreglo;
    }
    
}
