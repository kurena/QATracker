

package com.uia.is12.business;

import com.uia.is12.data.QATrackerDAO;
import com.uia.is12.domain.Usuario;
import java.sql.SQLException;

public class QATrackerBusiness {
    private QATrackerDAO qaTrackerDAO;
    
    public QATrackerBusiness() {
    
        this.qaTrackerDAO = new QATrackerDAO();
    }
    
    public void getUserInfo() throws SQLException{
        qaTrackerDAO.getUserInfo();
    }
    
    public boolean validarDatosLogin(String username, String password){
        return qaTrackerDAO.validarDatosLogin(username,password);
    }
    
    public boolean insertarDatos(String userName, String password) throws SQLException{
        return qaTrackerDAO.insertarDatos(userName,password);
    }
    
    public boolean buscarCoincidencia(String search) throws SQLException{
        return qaTrackerDAO.search(search);
    }
    
    
}
