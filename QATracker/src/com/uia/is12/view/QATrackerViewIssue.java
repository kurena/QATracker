/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uia.is12.view;

import com.uia.is12.business.QATrackerBusiness;
import com.uia.is12.domain.Comentario;
import com.uia.is12.domain.Issue;
import com.uia.is12.domain.Usuario;
import com.uia.is12.panel.QAGradient;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author raiam.quesada.urena
 */
public class QATrackerViewIssue extends javax.swing.JFrame {
    private Issue issueData;
    private final QATrackerBusiness qatrackerBusiness=new QATrackerBusiness();
    private ArrayList<Usuario> user,usersToAssign;
    private QATrackerBusiness qabusiness = new QATrackerBusiness();
    private String pathFile="";
    private File fileToCopy;
    private boolean flag, watchRes;
   
    
    /**
     * Creates new form QATrackerViewIssue
     */
    public QATrackerViewIssue() throws SQLException{
        callTODO();
    }
    public QATrackerViewIssue(Issue issue) throws SQLException{
        callTODO();
        this.issueData = issue;
        loadAllInfo();
        checkWatch();
        fillBoxes();
        try {
            loadAllMessages();
        } catch (SQLException ex) {
            Logger.getLogger(QATrackerViewIssue.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public final void callTODO() throws SQLException{
        initComponents();
        addPanel();
        
    }
    public final void addPanel(){
        QAGradient as = new QAGradient("MAIN");
        this.setLayout(new BorderLayout());
        this.add(as, BorderLayout.CENTER);
        disableComponents(false);
    }
    
    public void disableComponents(boolean res){
        nombre.setEditable(res);
        descripcion.setEditable(res);
        cargar.setEnabled(res);
        asignador.setEnabled(res);
        imagenField.setEnabled(true);
        priority.setEnabled(res);
        StateCombo.setEnabled(res);
        creador.setEnabled(false);
        guardar.setEnabled(res);
    }
    
    public void checkWatch() throws SQLException{
        int userId = qabusiness.getIdFromUsername(qabusiness.getLoggedUser());
        int issueId = this.issueData.getId();
        watchRes = qabusiness.checkWatch(issueId, userId);
        
        if(watchRes) {
            guardar.setIcon(updateImageButton("../images/no_seguir.png"));
        }else {
            watcher.setIcon(updateImageButton("../images/seguir.png"));
        }
    }
    
    public final void fillBoxes(){
        String personas [];
        
        int cont=0;
        try {
            user = qabusiness.getCurrentUsername();
            usersToAssign = qatrackerBusiness.getAllUsername();
            personas = new String[usersToAssign.size()];
            for(Usuario users:usersToAssign){
                personas[cont] = users.getUsername();
                cont++;
            }
                creador.setModel(new javax.swing.DefaultComboBoxModel(personas));
                asignador.setModel(new javax.swing.DefaultComboBoxModel(personas));
                asignador.setSelectedIndex(qatrackerBusiness.returnIndex(usersToAssign,this.issueData.getNombreAsignador()));
                creador.setSelectedIndex(qatrackerBusiness.returnIndex(usersToAssign,this.issueData.getNombreCreador()));
                priority.setSelectedIndex(qatrackerBusiness.returnIndex(llenarArregloConNombres(),this.issueData.getPriority()));
                StateCombo.setSelectedIndex(qatrackerBusiness.returnIndex(llenarArregloState(), this.issueData.getState()));
        } catch (SQLException ex) {
            Logger.getLogger(QATrackerCreateIssue.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    public String[] llenarArregloConNombres(){
        String priorityArreglo[] = new String[priority.getItemCount()];
        for(int x=0;x<priority.getItemCount();x++){
            priorityArreglo[x] = (String) priority.getItemAt(x);
        }
        return priorityArreglo;
    }
    
    public String[] llenarArregloState(){
        String state[] = new String[StateCombo.getItemCount()];
        for(int x=0;x<StateCombo.getItemCount();x++){
            state[x] = (String) StateCombo.getItemAt(x);
        }
        return state;
    }
    public final void loadAllInfo(){
        nombre.setText(this.issueData.getName());
        descripcion.setText(this.issueData.getDescription());
        if(!this.issueData.getAttachment().equals("")){
            try {
                File archivo = new File(this.issueData.getAttachment());
                Image resizedImage=qatrackerBusiness.getImage(imagenField.getWidth(), imagenField.getHeight(), archivo);
                imagenField.setIcon(new ImageIcon(resizedImage));
            } catch (IOException ex) {
                Logger.getLogger(QATrackerViewIssue.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            imagenField.setText("No hay imagen para este Issue");
        }
        try {
            tarea.setText(qatrackerBusiness.getTaskName(this.issueData.getIdTask()));
        } catch (SQLException ex) {
            Logger.getLogger(QATrackerViewIssue.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void loadAllMessages() throws SQLException{
        DefaultTableModel modelo = new DefaultTableModel();
        ArrayList<Comentario> Comments = qabusiness.getComentarios(this.issueData);
        String[] datos;
        if(Comments.size()<=0){
            modelo.addColumn("Comentarios");
            datos = new String[1];
            datos[0] = "Este Issue no posee comentarios";
            modelo.addRow(datos);      
        } else {
            modelo.addColumn("Nombre");modelo.addColumn("Comentario");modelo.addColumn("Fecha");
            datos = new String[3];
            for(Comentario comment:Comments){
                datos[0] = qabusiness.getUsernameByID(comment.getIdUser());
                datos[1] = comment.getComment();
                datos[2] = comment.getDate();
                modelo.addRow(datos);
            }
        }
        
        
        comments.setModel(modelo);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        fileChooser = new javax.swing.JFileChooser();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        descripcion = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        imagenField = new javax.swing.JLabel();
        cargar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        creador = new javax.swing.JComboBox();
        asignador = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        priority = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        StateCombo = new javax.swing.JComboBox();
        Volver = new javax.swing.JButton();
        actualizar = new javax.swing.JButton();
        guardar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        tarea = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        comments = new javax.swing.JTable();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        commentArea = new javax.swing.JTextArea();
        agregarComment = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        watcher = new javax.swing.JButton();

        fileChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileChooserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Informacion del Issue");
        setAlwaysOnTop(true);
        setAutoRequestFocus(false);
        setBounds(new java.awt.Rectangle(150, 0, 0, 0));
        setPreferredSize(new java.awt.Dimension(1043, 756));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel1.setText("Información del Issue");

        jPanel1.setEnabled(false);
        jPanel1.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel2.setText("Nombre:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel3.setText("Descripcion:");

        descripcion.setColumns(20);
        descripcion.setLineWrap(true);
        descripcion.setRows(5);
        descripcion.setFocusable(false);
        jScrollPane1.setViewportView(descripcion);

        jLabel4.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel4.setText("Imagen:");

        cargar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/uia/is12/images/cargar.png"))); // NOI18N
        cargar.setBorderPainted(false);
        cargar.setContentAreaFilled(false);
        cargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel6.setText("Creador:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel7.setText("Asignado a:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel5.setText("Prioridad:");

        priority.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Alta", "Media", "Baja", "N/A" }));

        jLabel8.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel8.setText("Estado:");

        StateCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Abierto", "Resuelto", "Cerrado", "Inválido", "Test" }));

        Volver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/uia/is12/images/volver.png"))); // NOI18N
        Volver.setToolTipText("Volver");
        Volver.setBorderPainted(false);
        Volver.setContentAreaFilled(false);
        Volver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Volver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                VolverMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                VolverMouseExited(evt);
            }
        });
        Volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VolverActionPerformed(evt);
            }
        });

        actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/uia/is12/images/actualizar.png"))); // NOI18N
        actualizar.setToolTipText("Actualizar el Issue");
        actualizar.setBorderPainted(false);
        actualizar.setContentAreaFilled(false);
        actualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        actualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                actualizarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                actualizarMouseExited(evt);
            }
        });
        actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarActionPerformed(evt);
            }
        });

        guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/uia/is12/images/guardarHover.png"))); // NOI18N
        guardar.setToolTipText("Almacenar la informacion");
        guardar.setBorderPainted(false);
        guardar.setContentAreaFilled(false);
        guardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        guardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                guardarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                guardarMouseExited(evt);
            }
        });
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel9.setText("Tarea:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cargar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(imagenField, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 79, Short.MAX_VALUE)
                        .addComponent(Volver, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tarea, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(priority, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(asignador, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(creador, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(StateCombo, 0, 201, Short.MAX_VALUE)))
                        .addGap(0, 37, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(creador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(asignador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(priority, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cargar)
                            .addComponent(jLabel4)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(StateCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9)
                                    .addComponent(tarea, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(actualizar, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(Volver, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(guardar, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(imagenField, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Comentarios"));
        jPanel2.setOpaque(false);

        comments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(comments);

        jLabel10.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel10.setText("Escribe un comentario:");

        commentArea.setColumns(20);
        commentArea.setLineWrap(true);
        commentArea.setRows(5);
        jScrollPane3.setViewportView(commentArea);

        agregarComment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/uia/is12/images/comentar.png"))); // NOI18N
        agregarComment.setToolTipText("");
        agregarComment.setBorderPainted(false);
        agregarComment.setContentAreaFilled(false);
        agregarComment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarCommentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jSeparator4)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(agregarComment, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel10))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(agregarComment, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(7, 7, 7))
        );

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        watcher.setBorderPainted(false);
        watcher.setContentAreaFilled(false);
        watcher.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        watcher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                watcherActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(watcher, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(watcher, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fileChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileChooserActionPerformed
        JFileChooser selector = (JFileChooser) evt.getSource();
        String command = evt.getActionCommand();
        switch (command) {
            case JFileChooser.APPROVE_SELECTION:
                File archivo = selector.getSelectedFile();
                try {
                    Image resizedImage=qabusiness.getImage(imagenField.getWidth(), imagenField.getHeight(), selector.getSelectedFile());
                    imagenField.setIcon(new ImageIcon(resizedImage));
                } catch (IOException ex) {
                    Logger.getLogger(QATrackerCreateIssue.class.getName()).log(Level.SEVERE, null, ex);
                    imagenField.setIcon(new ImageIcon(archivo.getPath()));
                }
                pathFile = QATrackerCreateIssue.path + archivo.getName();
                fileToCopy = archivo;
                jDialog1.dispose();
                flag = true;
                break;
        }
    }//GEN-LAST:event_fileChooserActionPerformed

    private void agregarCommentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarCommentActionPerformed
        if(!commentArea.getText().equals("")){
            qabusiness.insertComment(this.issueData, commentArea.getText());
            try {
                loadAllMessages();
            } catch (SQLException ex) {
                Logger.getLogger(QATrackerViewIssue.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "LLene el campo de comentario", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_agregarCommentActionPerformed

    private void watcherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_watcherActionPerformed
        int userId = 0;
        try {
            userId = qabusiness.getIdFromUsername(qabusiness.getLoggedUser());
        } catch (SQLException ex) {
            Logger.getLogger(QATrackerViewIssue.class.getName()).log(Level.SEVERE, null, ex);
        }
        int issueId = this.issueData.getId();
        if (watchRes){
            try {
                qabusiness.removeWatch(userId,issueId);
                checkWatch();
            } catch (SQLException ex) {
                Logger.getLogger(QATrackerViewIssue.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else{
            try {
                qabusiness.createWatch(userId,issueId);
                checkWatch();
            } catch (SQLException ex) {
                Logger.getLogger(QATrackerViewIssue.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_watcherActionPerformed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        validarFiels();
    }//GEN-LAST:event_guardarActionPerformed

    private void guardarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guardarMouseExited
        guardar.setIcon(updateImageButton("../images/guardarHover.png"));
    }//GEN-LAST:event_guardarMouseExited

    private void guardarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guardarMouseEntered
        guardar.setIcon(updateImageButton("../images/guardar.png"));
    }//GEN-LAST:event_guardarMouseEntered

    private void actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarActionPerformed
        disableComponents(true);
        actualizar.setEnabled(false);
    }//GEN-LAST:event_actualizarActionPerformed

    private void actualizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_actualizarMouseExited
        actualizar.setIcon(updateImageButton("../images/actualizar.png"));
    }//GEN-LAST:event_actualizarMouseExited

    private void actualizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_actualizarMouseEntered
        actualizar.setIcon(updateImageButton("../images/actualizarHover.png"));
    }//GEN-LAST:event_actualizarMouseEntered

    private void VolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VolverActionPerformed
        new QATrackerDashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_VolverActionPerformed

    private void VolverMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VolverMouseExited
        Volver.setIcon(updateImageButton("../images/volver.png"));
    }//GEN-LAST:event_VolverMouseExited

    private void VolverMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VolverMouseEntered
        Volver.setIcon(updateImageButton("../images/volverHover.png"));
    }//GEN-LAST:event_VolverMouseEntered

    private void cargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarActionPerformed
        int returnval = fileChooser.showOpenDialog(QATrackerViewIssue.this);
    }//GEN-LAST:event_cargarActionPerformed
    
    public ImageIcon updateImageButton(String path){
        InputStream imgStream = QATrackerView.class.getResourceAsStream(path);
        ImageIcon newImage = new ImageIcon();
        try {
            BufferedImage myImg = ImageIO.read(imgStream);
            newImage = new ImageIcon(myImg);
        } catch (IOException ex) {
            Logger.getLogger(QATrackerView.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return newImage;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QATrackerViewIssue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QATrackerViewIssue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QATrackerViewIssue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QATrackerViewIssue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new QATrackerViewIssue().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(QATrackerViewIssue.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    
     private void validarFiels() {
        String filePath="";
        Issue issue;
        if((!"".equals(nombre.getText()) && (!"".equals(descripcion.getText())))){
            if(flag){
                qabusiness.addOrRepalceImg(pathFile,fileToCopy);
                filePath = pathFile;
            } 
            issue = new Issue(nombre.getText(), descripcion.getText(), this.issueData.getId(), qabusiness.getAsignadorID(usersToAssign, (String) asignador.getSelectedItem()), 0,  (String) asignador.getSelectedItem(), this.issueData.getNombreCreador(), filePath,(String) StateCombo.getSelectedItem(),(String) priority.getSelectedItem(),this.issueData.getIdTask());
            try {
                qabusiness.actualizarIssue(this.issueData.getId(),issue);
                JOptionPane.showMessageDialog(this, "Los datos se han actualizado", "Enhorabuena", JOptionPane.INFORMATION_MESSAGE);
                new QATrackerDashboard().setVisible(true);
                this.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(QATrackerCreateIssue.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox StateCombo;
    private javax.swing.JButton Volver;
    private javax.swing.JButton actualizar;
    private javax.swing.JButton agregarComment;
    private javax.swing.JComboBox asignador;
    private javax.swing.JButton cargar;
    private javax.swing.JTextArea commentArea;
    private javax.swing.JTable comments;
    private javax.swing.JComboBox creador;
    private javax.swing.JTextArea descripcion;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JButton guardar;
    private javax.swing.JLabel imagenField;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextField nombre;
    private javax.swing.JComboBox priority;
    private javax.swing.JLabel tarea;
    private javax.swing.JButton watcher;
    // End of variables declaration//GEN-END:variables
}
