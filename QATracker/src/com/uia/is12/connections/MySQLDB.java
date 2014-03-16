

package com.uia.is12.connections;

import java.sql.SQLException;
import com.uia.is12.domain.Usuario;
import java.sql.*;

public class MySQLDB {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/qa_tracker";
    
    static final String USER = "root";
    static final String PASSWORD = "root";
    
    private Connection con = null;
    private CallableStatement cstmt = null;
    private Statement stmt = null;

    public MySQLDB() {
        
    }
    
    public ResultSet executeQuery(String sql) throws SQLException{
        con = DriverManager.getConnection(DB_URL,USER,PASSWORD);
        cstmt = con.prepareCall(sql);
        ResultSet res = cstmt.executeQuery();
        
        return res;
        
    }
    public void execute(String sql) throws SQLException{
        con = DriverManager.getConnection(DB_URL,USER,PASSWORD);
        stmt = con.prepareCall(sql);
        stmt.execute(sql, Statement.RETURN_GENERATED_KEYS);
    }
    public void closeExecuteQuery() throws SQLException{
        cstmt.close();
        con.close();
    }
    public void closeExecute() throws SQLException{
        stmt.close();
        con.close();
    }
    
    
}
