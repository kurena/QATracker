

package com.uia.is12.business;

import com.uia.is12.data.QATrackerDAO;
import com.uia.is12.domain.Usuario;
import java.sql.SQLException;

public class QATrackerBusiness {
    private QATrackerDAO qaTrackerDAO;
    
    public QATrackerBusiness() {
    
        this.qaTrackerDAO = new QATrackerDAO();
    }
    
    public boolean getUserInfo(String username, String password) throws SQLException{
        return qaTrackerDAO.getUserInfo(username,password);
    }
    
}
