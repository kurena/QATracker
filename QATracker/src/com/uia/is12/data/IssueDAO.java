/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uia.is12.data;

import com.uia.is12.business.QATrackerBusiness;
import com.uia.is12.connections.MySQLDB;
import com.uia.is12.domain.Comentario;
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
        private QATrackerBusiness qabusiness;
        
        public IssueDAO(QATrackerBusiness qabusiness) {
            this.qabusiness = qabusiness;
        }

        public IssueDAO() {
        }
        
        
       public void insertarDatos(Issue issue) throws SQLException{
        mysqlDB = new MySQLDB();
        issue.setAttachment(issue.getAttachment().replaceAll("\\\\", "\\\\\\\\"));
        String sql = "INSERT INTO issue(name, description, idUserCreador, idUserAsignar,path,state,priority,idTask) VALUES ('"+issue.getName()+"', '"+issue.getDescription()+"','"+issue.getIdUserCreador()+"', '"+issue.getIdUserAsignar()+"','"+issue.getAttachment()+"','"+issue.getState()+"','"+issue.getPriority()+"','"+issue.getIdTask()+"')";
        mysqlDB.execute(sql);
        mysqlDB.closeExecute();
    }
    
    public ArrayList<Issue> search() throws SQLException{
        ArrayList<Issue> arreglo = new ArrayList();
        mysqlDB = new MySQLDB();
        String sql = "SELECT * from issue m, user u,user us WHERE idUserCreador="+getUserID()+" AND m.idUserCreador=u.iduser AND m.idUserAsignar=us.iduser";
        ResultSet res = mysqlDB.executeQuery(sql);
        while(res.next()){
            arreglo.add(new Issue(res.getString("name"), res.getString("description"), res.getInt("idUserCreador"), res.getInt("idUserAsignar"), res.getInt("idissue"), res.getString("u.username"),res.getString("us.username"),res.getString("path"),res.getString("state"),res.getString("priority"),res.getInt("idTask")));
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
            System.out.println("Primer Username: "+res.getString("u.username"));
            System.out.println("Segundo username: "+res.getString("us.username"));
            arreglo = new Issue(res.getString("name"), res.getString("description"), res.getInt("idUserCreador"), res.getInt("idUserAsignar"), res.getInt("idissue"), res.getString("us.username"),res.getString("u.username"), res.getString("path"),res.getString("state"),res.getString("priority"),res.getInt("idTask"));
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
        String sql="UPDATE issue SET name='"+issue.getName()+"',description='"+issue.getDescription()+"', idUserAsignar='"+issue.getIdUserAsignar()+"', path='"+issue.getAttachment()+"', state='"+issue.getState()+"', priority='"+issue.getPriority()+"' WHERE idissue='"+id+"'";
        mysqlDB.execute(sql);
        mysqlDB.closeExecute();
    }
    
    public ArrayList<Comentario> getComments(Issue issue) throws SQLException{
        mysqlDB = new MySQLDB();
        ArrayList<Comentario> comentarios = new ArrayList();
        String sql="SELECT * FROM comment where idIssue='"+issue.getId()+"'";
        ResultSet res = mysqlDB.executeQuery(sql);
        while(res.next()){
            comentarios.add(new Comentario(res.getInt("idUser"),res.getInt("idIssue"),res.getString("date"), res.getString("comment")));
        }   
        return comentarios;
    }
    
    public void insertComment(Issue issue, String comment) throws SQLException{
        mysqlDB = new MySQLDB();
        String sql="INSERT INTO comment(comment,idUser,date,idIssue)VALUES('"+comment+"','"+qabusiness.getIdFromUsername(qabusiness.getLoggedUser())+"','"+qabusiness.getDate()+"','"+issue.getId()+"')";
        mysqlDB.execute(sql);
        mysqlDB.closeExecute();
    } 
    
    public ArrayList<Issue> getIssuesCurrentTask(int id) throws SQLException{
        boolean flag = false;
        ArrayList<Issue> issues = new ArrayList();
        ArrayList<Integer> ids = new ArrayList();
        mysqlDB = new MySQLDB();
        String sql = "SELECT * from issue i JOIN task t on t.idtask = i.idTask WHERE i.idTask='"+id+"'";
        ResultSet res = mysqlDB.executeQuery(sql);
        while(res.next()){
            Issue issue = new Issue(res.getString("name"), res.getString("description"),res.getInt("i.idissue"));
            issues.add(issue); 
         }
        return issues;
    }
    
    
}
