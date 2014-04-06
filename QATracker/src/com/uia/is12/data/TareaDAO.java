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
        String sql = "INSERT INTO task(name, description, image,idProyect) VALUES('"+tarea.getName()+"','"+tarea.getDescription()+"','"+tarea.getAttachment()+"','"+tarea.getIdProyect()+"')";
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
   
    
}
