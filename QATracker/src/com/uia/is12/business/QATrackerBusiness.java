

package com.uia.is12.business;

import com.uia.is12.data.IssueDAO;
import com.uia.is12.data.UsuarioDAO;
import com.uia.is12.domain.Issue;
import com.uia.is12.domain.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class QATrackerBusiness {
    private UsuarioDAO usuarioDAO;
    private String user;
    private IssueDAO issueDAO;
    
    public QATrackerBusiness() {
        this.usuarioDAO = new UsuarioDAO();
        this.issueDAO = new IssueDAO();
    }
    
    /**
     * Valida los datos del login
     * @param usuario
     * @return boolean
     * @throws SQLException 
     */
    public boolean validarDatosLogin(Usuario usuario) throws SQLException{
        return usuarioDAO.validarDatosLogin(usuario);
    }
    /**
     * Inserta los datos, si no encuentra el username
     * @param usuario
     * @return 
     * @throws SQLException
     */
    public boolean insertarDatos(Usuario usuario) throws SQLException{
        boolean result = usuarioDAO.search(usuario);
        if(!result){
           usuarioDAO.insertarDatos(usuario);
        }
        
        return result;
    }
    
     /**
     * Inserta el nombre del nuevo usuario logueado
=     */
    public void cerrarConexion(){
        setLoggedUser("");
    }
    
    /**
     * Inserta el nombre del nuevo usuario logueado
     * @param Name
     */
    public void setLoggedUser(String Name){
        UsuarioDAO userDao = new UsuarioDAO();
        userDao.setUser(Name);
    }
     /**
     * Obtiene el nombre del usuario loggueado
     * @return 
     */
    public String getLoggedUser(){
        UsuarioDAO userDao = new UsuarioDAO();
        return userDao.getUser();
    }
     /**
     * retorna true o false dependiendo de si el usuario esta conectado o no
     * @return 
     */
    public boolean getLogged(){
        boolean exist = true;
        if("".equals(getLoggedUser())){
            exist = false;
        }
        return exist;
    }
    
    public ArrayList<Issue> returnIssuesFromCurrentUser() {
        ArrayList<Issue> issues = new ArrayList();
        try {
            issues = issueDAO.search();
        } catch (SQLException ex) {
            Logger.getLogger(QATrackerBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }
        return issues;
    }
}
