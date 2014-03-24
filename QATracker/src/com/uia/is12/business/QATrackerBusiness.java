

package com.uia.is12.business;

import com.sun.corba.se.spi.orbutil.fsm.Input;
import com.uia.is12.data.IssueDAO;
import com.uia.is12.data.UsuarioDAO;
import com.uia.is12.domain.Issue;
import com.uia.is12.domain.Usuario;
import com.uia.is12.view.QATrackerCreateIssue;
import java.awt.Image;
import static java.awt.image.ImageObserver.WIDTH;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
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
    
    /**
     * Devuelve los issues del usuario conectado
     * @return 
    */
    public ArrayList<Issue> returnIssuesFromCurrentUser() {
        ArrayList<Issue> issues = new ArrayList();
        try {
            issues = issueDAO.search();
        } catch (SQLException ex) {
            Logger.getLogger(QATrackerBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }
        return issues;
    }
    /**
     * crea un nuevo archivo
     * @param sourceFile
     * @param destinationFile
     */
    public void copyFile(File sourceFile, File destinationFile) {
         try {
             FileInputStream fileInputStream = new FileInputStream(sourceFile);
             FileOutputStream fileOutputStream = new FileOutputStream(destinationFile);

             int bufferSize;
             byte[] bufffer = new byte[512];
             while ((bufferSize = fileInputStream.read(bufffer)) > 0) {
                     fileOutputStream.write(bufffer, 0, bufferSize);
             }
             fileInputStream.close();
             fileOutputStream.close();
        } catch (Exception e) {
                 e.printStackTrace();
        }

    }
    /**
     * hace un resize de la imagen
     * @param width
     * @param height
     * @param selector
=
     * @return 
     * @throws java.io.IOException    */
    public Image getImage(int width, int height, File selector) throws IOException{
        Image img = ImageIO.read(selector);
        Image resizedImage = img.getScaledInstance(width, height, WIDTH + 10);
        return resizedImage;
    }
     /**
     * Inserta un nuevo archivo de imagen al documento
     * @param pathFile
     * @param filetocopy
=    */
    public void addOrRepalceImg(String pathFile, File filetocopy){
        File archivo4 = new File(pathFile);
           if (!archivo4.exists()) {
              try {
                   archivo4.createNewFile();
                   new FileOutputStream(pathFile, false).close();
              } catch (IOException ex) {
                        Logger.getLogger(QATrackerCreateIssue.class.getName()).log(Level.SEVERE, null, ex);
              }
                    copyFile(filetocopy, archivo4);
          } else {
                    System.out.println("NOTExists");
                }
    }
     /**
     * Retorna el perfil del usuario loggeado
     * @return 
     * @throws java.sql.SQLException
     */
    public ArrayList<Usuario> getCurrentUsername() throws SQLException{
        return this.usuarioDAO.getCurrentUserID();   
    }
    
    /**
     * Retorna el perfil de todos los usuarios
     * @return 
     * @throws java.sql.SQLException
     */
    public ArrayList<Usuario> getAllUsername() throws SQLException{
        return usuarioDAO.getAllUsers();
    }
    /**
     * Crea un nuevo Issue
     * @param issue
     * @throws java.sql.SQLException
     */
    public void createIssue(Issue issue) throws SQLException{
        issueDAO.insertarDatos(issue);
    }
     /**
     * Obtener el ID del usuaro al que se envian
     * @param arreglo
     * @param username
     * @return 
     */
    public int getAsignadorID(ArrayList<Usuario> arreglo, String username){
        int id=-1;
        for(Usuario userAsignee: arreglo){
            if(userAsignee.getUsername().equals(username)){
                id=userAsignee.getId();
                break;
            }
        }
        return id;
    }
    
    public boolean searchIDIssue(int id) throws SQLException{
        if(issueDAO.search(id).getId() !=-1){
            return false;
        } else {
            return true;
        }
    }
}
