/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uia.is12.data;

import com.uia.is12.connections.MySQLDB;
import com.uia.is12.domain.Proyecto;
import com.uia.is12.domain.Tarea;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author raiam.quesada.urena
 */
public class TareaDAO {
    private MySQLDB mysqlDB;

    public TareaDAO() {
        mysqlDB = new MySQLDB();
    }
    public void insertTarea(Tarea tarea) throws SQLException{
        tarea.setAttachment(tarea.getAttachment().replaceAll("\\\\", "\\\\\\\\"));
        String sql = "INSERT INTO task(name, description, image,idProyect,state,idCreatorUser,idUserAssign) VALUES('"+tarea.getName()+"','"+tarea.getDescription()+"','"+tarea.getAttachment()+"','"+tarea.getIdProyect()+"','"+tarea.getState()+"','"+tarea.getIdCreatorUser()+"','"+tarea.getIdUserAsignar()+"')";
        mysqlDB.execute(sql);
        mysqlDB.closeExecute();
    }
    
     public ArrayList<Tarea> getTasksCurrentProyect(int id) throws SQLException{
        boolean flag = false;
        ArrayList<Tarea> tareas = new ArrayList();
        ArrayList<Integer> ids = new ArrayList();
        mysqlDB = new MySQLDB();
        String sql = "SELECT * from task t JOIN proyect p on p.idproyect = t.idProyect WHERE t.idproyect='"+id+"'";
        ResultSet res = mysqlDB.executeQuery(sql);
        while(res.next()){
            Tarea tarea = new Tarea(res.getString("name"), res.getString("description"),res.getInt("t.idtask"));
            tareas.add(tarea); 
         }
        return tareas;
    }
     
     public Tarea search(int id) throws SQLException{
        Tarea arreglo = new Tarea();
        mysqlDB = new MySQLDB();
        ArrayList<Integer> ids = new ArrayList();
        String sql = "SELECT * FROM task t WHERE t.idtask="+id+"";
        ResultSet res = mysqlDB.executeQuery(sql);
        while(res.next()){
            arreglo = new Tarea(id, res.getString("name"), res.getString("description"),res.getString("image"),res.getString("state"),res.getInt("idProyect"),res.getInt("idCreatorUser"),res.getInt("idUserAssign"));
        }
        mysqlDB.closeExecuteQuery();
        return arreglo;
    }
     
     public ArrayList<Tarea> getAllTasks(){
        mysqlDB = new MySQLDB();
        ArrayList<Tarea> tasks = new ArrayList();
        try {
            
            String sql="SELECT * from task";
            ResultSet res = mysqlDB.executeQuery(sql);
            while(res.next()){
                try {
                    tasks.add(new Tarea(res.getInt("idTask"),res.getString("name")));
                } catch (SQLException ex) {
                    Logger.getLogger(ProyectoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProyectoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tasks;
    }
     
    public String getTaskNameById(int id) throws SQLException{
        mysqlDB = new MySQLDB();
        String name ="";
        String sql = "SELECT * from task WHERE idtask='"+id+"'";
        ResultSet res = mysqlDB.executeQuery(sql);
        while(res.next()){
            name = res.getString("name");
        }
        return name;
    }
    
    public void updateData(int id,Tarea tarea) throws SQLException{
        mysqlDB = new MySQLDB();
        String sql="UPDATE task SET name='"+tarea.getName()+"',description='"+tarea.getDescription()+"', idUserAssign='"+tarea.getIdUserAsignar()+"', state='"+tarea.getState()+"' WHERE idtask='"+id+"'";
        mysqlDB.execute(sql);
        mysqlDB.closeExecute();
    }
   
    
}
