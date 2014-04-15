

package com.uia.is12.data;

import com.uia.is12.business.QATrackerBusiness;
import com.uia.is12.connections.MySQLDB;
import com.uia.is12.domain.Proyecto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProyectoDAO {
    
    private MySQLDB mysqlDB;
    private QATrackerBusiness qabusiness;

    public ProyectoDAO() {
       
    }

    public ProyectoDAO(QATrackerBusiness qabusiness) {
        this.qabusiness = qabusiness;
    }
    
    
    
    /**
     * Inserta el proyecto
     * @param proyecto
     * @throws SQLException 
     */
    public void insertar(Proyecto proyecto) throws SQLException{
        mysqlDB = new MySQLDB();
        
        String sql = "INSERT INTO proyect(name, description, idCreatorUser) VALUES ('"+proyecto.getName()+"', '"+proyecto.getDescription()+"','"+proyecto.getIdUserCreador()+"')";
        System.out.println(proyecto.getIdUserCreador());
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
    
    /**
     * Obtiene el nombre del proyecto
     * @param proyecto
     * @return
     * @throws SQLException 
     */
    public String getProyectName(int id) throws SQLException{
        mysqlDB = new MySQLDB();
        String name ="";
        String sql = "SELECT * from proyect WHERE idproyect='"+id+"'";
        ResultSet res = mysqlDB.executeQuery(sql);
        while(res.next()){
            name = res.getString("name");
        }
        return name;
    }
    
    /**
     * Obtiene el los proyectos del usuario actual
     * @return
     * @throws SQLException 
     */
    public ArrayList<Proyecto> getProyectsCurrentUser() throws SQLException{
        boolean flag = false;
        ArrayList<Proyecto> proyectos = new ArrayList();
        ArrayList<Integer> ids = new ArrayList();
        mysqlDB = new MySQLDB();
        String sql = "SELECT * from proyect p JOIN userproyect u on p.idproyect = u.idproyect WHERE u.iduser='"+qabusiness.getLoggedUserInfo().getId()+"' OR p.idCreatorUser='"+qabusiness.getLoggedUserInfo().getId()+"'";
        ResultSet res = mysqlDB.executeQuery(sql);
        while(res.next()){
            flag = false;
            Proyecto proyect = new Proyecto(res.getString("name"), res.getString("description"),res.getInt("p.idCreatorUser"),qabusiness.getUsernameByID(res.getInt("p.idCreatorUser")),res.getInt("p.idproyect"));
            if(ids.size()<=0){
                flag = true;
            } else {
                for(int x:ids){
                    if(res.getInt("u.idproyect")==x){
                        flag = false;
                        break;
                    } else {
                        flag = true;
                    }
                }
            }
            ids.add(res.getInt("u.idproyect"));     
            if(flag){
                proyectos.add(proyect);    
            } 
         }
        return proyectos;
    }
    /**
     * Obtiene  TODOS los proyectos
     * @return
     * @throws SQLException 
     */
    
    public ArrayList<Proyecto> getProjects(){
        mysqlDB = new MySQLDB();
        ArrayList<Proyecto> projects = new ArrayList();
        try {
            
            String sql="SELECT * from proyect";
            ResultSet res = mysqlDB.executeQuery(sql);
            while(res.next()){
                try {
                    projects.add(new Proyecto(res.getString("name"), res.getString("description"),res.getInt("idCreatorUser"), qabusiness.getUsernameByID(res.getInt("idCreatorUser")), res.getInt("idproyect")));
                } catch (SQLException ex) {
                    Logger.getLogger(ProyectoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProyectoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return projects;
    }
    /**
     * Busca proyecto con los usuarios pertenecientes al mismo
     * @param id
     * @return arreglo
     * @throws SQLException 
     */
    public Proyecto search(int id) throws SQLException{
        Proyecto arreglo = new Proyecto();
        mysqlDB = new MySQLDB();
        ArrayList<Integer> ids = new ArrayList();
        String sql = "SELECT * FROM proyect p JOIN userproyect up on p.idproyect=up.idproyect WHERE p.idproyect="+id+"";
        ResultSet res = mysqlDB.executeQuery(sql);
        while(res.next()){
            ids.add(res.getInt("up.iduser"));
            arreglo = new Proyecto(res.getString("name"), res.getString("description"),qabusiness.getUsernameByID(res.getInt("idCreatorUser")),id);
        }
        arreglo.setIdsUsuariosIncluidos(ids);
        mysqlDB.closeExecuteQuery();
        return arreglo;
    }
    
    public void actualizarProyecto(Proyecto proyecto) throws SQLException{
        mysqlDB = new MySQLDB();
        String sql="UPDATE proyect SET name='"+proyecto.getName()+"',description='"+proyecto.getDescription()+"', idCreatorUser='"+proyecto.getIdUserCreador()+"' WHERE idproyect='"+proyecto.getId()+"'";
        String sqlE = "DELETE FROM userproyect WHERE idproyect="+this.getProyectId(proyecto)+"";
        mysqlDB.execute(sql);
        mysqlDB.execute(sqlE);
        mysqlDB.closeExecute();
    }
    
    public void actualizarArregloUsuarios(Proyecto proyecto) throws SQLException{
        mysqlDB = new MySQLDB();
        for(int i:proyecto.getIdsUsuariosIncluidos()){
            String sql = "INSERT INTO userproyect(iduser,idproyect) VALUES ("+i+",'"+this.getProyectId(proyecto)+"')";
            mysqlDB.execute(sql);
            mysqlDB.closeExecute();
        }
    }
    
    public void eliminarProyecto(int id) throws SQLException{
        mysqlDB = new MySQLDB();
        String sql="DELETE FROM proyect WHERE idproyect='"+id+"'";
        String sqlUP="DELETE FROM userproyect WHERE idproyect='"+id+"'";
        mysqlDB.execute(sqlUP);
        mysqlDB.execute(sql);
        mysqlDB.closeExecute();
    }
}
