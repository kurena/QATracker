

package com.uia.is12.business;

import com.sun.corba.se.spi.orbutil.fsm.Input;
import com.uia.is12.data.*;
import com.uia.is12.domain.*;
import com.uia.is12.view.QATrackerCreateIssue;
import java.awt.Image;
import static java.awt.image.ImageObserver.WIDTH;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class QATrackerBusiness {
    private final UsuarioDAO usuarioDAO;
    private String user;
    private final IssueDAO issueDAO;
    private ProyectoDAO proyectoDAO;
    private TareaDAO tareaDAO;
    
    public QATrackerBusiness() {
        this.usuarioDAO = new UsuarioDAO();
        this.issueDAO = new IssueDAO(this);
        this.proyectoDAO = new ProyectoDAO();
        this.tareaDAO = new TareaDAO();
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
     * Obtiene el nombre del usuario loggueado
     * @return 
     * @throws java.sql.SQLException 
     */
    public Usuario getLoggedUserInfo() throws SQLException{
            return this.usuarioDAO.getCurrentUserInfo().get(0);
        
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
    
    public void createProyect(Proyecto proyecto) throws SQLException{
        proyectoDAO.insertar(proyecto);
        proyectoDAO.insertarArregloUsuarios(proyecto);
    }
    
    public void actualizarIssue(int id,Issue issue) throws SQLException{
        issueDAO.updateData(id, issue);
    }
    
    public void actualizarProyecto(Proyecto proyecto) throws SQLException{
        proyectoDAO.actualizarProyecto(proyecto);
        proyectoDAO.actualizarArregloUsuarios(proyecto);
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
    
    public int getProyectID(ArrayList<Proyecto> arreglo, String username){
        int id=-1;
        for(Proyecto proyect: arreglo){
            if(proyect.getName().equals(username)){
                id=proyect.getId();
                break;
            }
        }
        return id;
    }
    
    
    public ArrayList<Integer> getAsignadorIDArreglo(ArrayList<Usuario> arreglo, ArrayList<String>usernames){
        ArrayList<Integer> usuarios = new ArrayList();
        for(Usuario userAsignee: arreglo){
            for(String i : usernames){
                if(userAsignee.getUsername().equals(i)){
                    usuarios.add(userAsignee.getId());
                }
            }
            
        }
        return usuarios;
    }
    
    public Issue getIDIssue(int id) throws SQLException{
        return issueDAO.search(id);
    }
    
    public boolean searchIDIssue(int id) throws SQLException{
        if(issueDAO.search(id).getId() ==-1){
            return false;
        } else {
            return true;
        }
    }
    
     public Proyecto getIDProyect(int id) throws SQLException{
        return proyectoDAO.search(id);
    }
    
    public boolean searchIDProyect(int id) throws SQLException{
        if(proyectoDAO.search(id).getId() == 0){
            return false;
        } else {
            return true;
        }
    }
    /**
     * Obtener el ID del usuario por medio del username
     * @param username
     * @return 
     */
    public int getIdFromUsername(String username) throws SQLException{
        int id=-1;
         id = usuarioDAO.getIDByUsername(username);
        return id;
    }
    /**
     * Obtener el la posicion del nombre en relacion con el arrelo
     * @param usuarios
     * @param nombre
     * @return 
     */
    public int returnIndex(ArrayList<Usuario> usuarios, String nombre){
        int id=-1;
        int cont=0;
        for(Usuario usuario:usuarios){
            if(usuario.getUsername().equals(nombre)){
                id=cont;
            }
            cont++;
        }
        return id;
    }
    
    /**
     * Obtener el la posicion del nombre en relacion con el arrelo de strings
     * @param variables
     * @param variable
     * @return 
     */
    public int returnIndex(String[] variables, String variable){
        int value=-1;
        for(int x=0;x<variables.length;x++){
            if(variables[x].equals(variable)){
                value = x;
            }
        }
        return value;
    }
    
    /**
     * obtener todos los comentarios 
     * @param issue
     * @return 
     */
    public ArrayList<Comentario> getComentarios(Issue issue){
        ArrayList<Comentario> comentarios = new ArrayList();
        try {
            comentarios = issueDAO.getComments(issue);
        } catch (SQLException ex) {
            Logger.getLogger(QATrackerBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }
        return comentarios;
    }
    
    
    public String getUsernameByID(int id) throws SQLException{
        return this.usuarioDAO.getUsernameByID(id);
    }
    /**
     * Agregar una nueva tarea
     * @param tarea
     * @throws java.sql.SQLException
     */
    public void createTask(Tarea tarea) throws SQLException{
        tareaDAO.insertTarea(tarea);
    }
    /**
     * Obtener los proyectos que el usuario posee
     * @return 
     */
    public ArrayList<Proyecto> getProyectsCurrentUser() {
        ArrayList<Proyecto> proyects = new ArrayList();
        this.proyectoDAO = new ProyectoDAO(this);
        try {
            proyects = this.proyectoDAO.getProyectsCurrentUser();
        } catch (SQLException ex) {
            Logger.getLogger(QATrackerBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return proyects;
    }
    
    public ArrayList<Tarea> getTasksCurrentProyect(int id) {
        ArrayList<Tarea> tasks = new ArrayList();
        tareaDAO = new TareaDAO();
        try {
            tasks = tareaDAO.getTasksCurrentProyect(id);
        } catch (SQLException ex) {
            Logger.getLogger(QATrackerBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return tasks;
    }
    public void insertComment(Issue issue, String comment){
        try {
            issueDAO.insertComment(issue, comment);
        } catch (SQLException ex) {
            Logger.getLogger(QATrackerBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Obtener fecha
     */
    public String getDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
       // System.out.println(dateFormat.format(cal.getTime()));
    }
}
