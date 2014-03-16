

package com.uia.is12.business;

import com.uia.is12.data.UsuarioDAO;
import com.uia.is12.domain.Usuario;
import java.sql.SQLException;

public class QATrackerBusiness {
    private UsuarioDAO usuarioDAO;
    
    public QATrackerBusiness() {
        this.usuarioDAO = new UsuarioDAO();
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
     * @throws SQLException
     * @throws Exception 
     */
    public boolean insertarDatos(Usuario usuario) throws SQLException{
        boolean result = usuarioDAO.search(usuario);
        if(!result){
           usuarioDAO.insertarDatos(usuario);
        }
        
        return result;
       
    }
    
    
}
