/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uia.is12.data;

import com.uia.is12.connections.MySQLDB;
import com.uia.is12.domain.Proyecto;
import com.uia.is12.domain.Tarea;
import java.sql.SQLException;

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
        String sql = "INSERT INTO task(name, description, image) VALUES('"+tarea.getName()+"','"+tarea.getDescription()+"','"+tarea.getAttachment()+"')";
        mysqlDB.execute(sql);
        mysqlDB.closeExecute();
    }
   
    
}
