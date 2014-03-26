

package com.uia.is12.data;

import com.uia.is12.connections.MySQLDB;
import com.uia.is12.domain.Proyecto;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ProyectoDAO {
    
    private MySQLDB mysqlDB;
    
    /**
     * Inserta el proyecto
     * @param proyecto
     * @throws SQLException 
     */
    public void insertar(Proyecto proyecto) throws SQLException{
        mysqlDB = new MySQLDB();
        String sql = "INSERT INTO proyect(name, description, idCreatorUser) VALUES ('"+proyecto.getName()+"', '"+proyecto.getDescription()+"','"+proyecto.getIdUserCreador()+"')";
        
        mysqlDB.execute(sql);
        mysqlDB.closeExecute();
    }
    /**
     * Inserta el arreglo de usuarios para el proyecto
     * @param proyecto
     * @throws SQLException 
     */
    public void insertarArregloUsuarios(Proyecto proyecto) throws SQLException{
        mysqlDB = new MySQLDB();
        for(int i:proyecto.getIdsUsuariosIncluidos()){
            String sql = "INSERT INTO userproyect(iduser,idproyect) VALUES ("+i+",'"+this.getProyectId(proyecto)+"')";
            mysqlDB.execute(sql);
            mysqlDB.closeExecute();
        }
    }
    /**
     * Obtiene el id del proyecto
     * @param proyecto
     * @return
     * @throws SQLException 
     */
    public int getProyectId(Proyecto proyecto) throws SQLException{
        mysqlDB = new MySQLDB();
        int id = 0;
        String sql = "SELECT * from proyect WHERE name='"+proyecto.getName()+"'";
        ResultSet res = mysqlDB.executeQuery(sql);
        while(res.next()){
            id = res.getInt("idproyect");
        }
        return id;
    }
    
}
