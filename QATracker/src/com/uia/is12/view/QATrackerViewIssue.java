/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uia.is12.view;

import com.uia.is12.business.QATrackerBusiness;
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
    private boolean flag;
    
    /**
     * Creates new form QATrackerViewIssue
     */
    public QATrackerViewIssue() {
        callTODO();
    }
    public QATrackerViewIssue(Issue issue) {
        callTODO();
        this.issueData = issue;
        loadAllInfo();
        fillBoxes();

    }
    
    public final void callTODO(){
        initComponents();
        addPanel();
    }
    public final void addPanel(){
        QAGradient as = new QAGradient("MAIN");
        this.setLayout(new BorderLayout());
        this.add(as, BorderLayout.CENTER);
        disableComponents(false);}
    
    public void disableComponents(boolean res){
        Component[] com = jPanel1.getComponents();
        for(int x=0;x<com.length;x++){
            com[x].setEnabled(res);
        }
        imagenField.setEnabled(true);
        creador.setEnabled(false);
    }
    
    public void fillBoxes(){

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
                System.out.println("asigandpr"+this.issueData.getNombreAsignador());
                creador.setSelectedIndex(qatrackerBusiness.returnIndex(usersToAssign,this.issueData.getNombreCreador()));
        } catch (SQLException ex) {
            Logger.getLogger(QATrackerCreateIssue.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    public final void loadAllInfo(){
        nombre.setText(this.issueData.getName());
        descripcion.setText(this.issueData.getDescription());
        if(!this.issueData.getAttachment().equals("")){
            try {
                File archivo = new File(this.issueData.getAttachment());//selector.getSelectedFile();
                Image resizedImage=qatrackerBusiness.getImage(imagenField.getWidth(), imagenField.getHeight(), archivo);
                imagenField.setIcon(new ImageIcon(resizedImage));
            } catch (IOException ex) {
                Logger.getLogger(QATrackerViewIssue.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            imagenField.setText("No hay imagen para este Issue");
        }
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
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        creador = new javax.swing.JComboBox();
        asignador = new javax.swing.JComboBox();
        Volver = new javax.swing.JButton();
        actualizar = new javax.swing.JButton();
        guardar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();

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

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/uia/is12/images/cargar.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel6.setText("Nombre Creador:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel7.setText("Nombre Asignador:");

        creador.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        asignador.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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
                            .addComponent(imagenField, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))))
                .addGap(57, 57, 57)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(asignador, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(creador, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
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
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(asignador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jButton1))
                .addGap(11, 11, 11)
                .addComponent(imagenField, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
        actualizar.setToolTipText("");
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Volver, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addComponent(jSeparator2)
            .addComponent(jSeparator1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Volver, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(actualizar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(guardar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarActionPerformed
        disableComponents(true);
    }//GEN-LAST:event_actualizarActionPerformed

    private void VolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VolverActionPerformed
        new QATrackerDashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_VolverActionPerformed

    private void VolverMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VolverMouseEntered
        Volver.setIcon(updateImageButton("../images/volverHover.png"));
    }//GEN-LAST:event_VolverMouseEntered

    private void actualizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_actualizarMouseEntered
        actualizar.setIcon(updateImageButton("../images/actualizarHover.png"));
    }//GEN-LAST:event_actualizarMouseEntered

    private void guardarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guardarMouseEntered
       guardar.setIcon(updateImageButton("../images/guardar.png"));
    }//GEN-LAST:event_guardarMouseEntered

    private void VolverMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VolverMouseExited
        Volver.setIcon(updateImageButton("../images/volver.png"));
    }//GEN-LAST:event_VolverMouseExited

    private void actualizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_actualizarMouseExited
       actualizar.setIcon(updateImageButton("../images/actualizar.png"));
    }//GEN-LAST:event_actualizarMouseExited

    private void guardarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guardarMouseExited
        guardar.setIcon(updateImageButton("../images/guardarHover.png"));
    }//GEN-LAST:event_guardarMouseExited

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
       validarFiels();
    }//GEN-LAST:event_guardarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int returnval = fileChooser.showOpenDialog(QATrackerViewIssue.this);
    }//GEN-LAST:event_jButton1ActionPerformed

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
                new QATrackerViewIssue().setVisible(true);
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
            issue = new Issue(nombre.getText(), descripcion.getText(), this.issueData.getId(), qabusiness.getAsignadorID(usersToAssign, (String) asignador.getSelectedItem()), 0,  (String) asignador.getSelectedItem(), this.issueData.getNombreCreador(), filePath);
            try {
                qabusiness.actualizarIssue(this.issueData.getId(),issue);
                JOptionPane.showMessageDialog(this, "Los datos se han anctualizado", "Enhorabuena", JOptionPane.INFORMATION_MESSAGE);
                new QATrackerDashboard().setVisible(true);
                this.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(QATrackerCreateIssue.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Volver;
    private javax.swing.JButton actualizar;
    private javax.swing.JComboBox asignador;
    private javax.swing.JComboBox creador;
    private javax.swing.JTextArea descripcion;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JButton guardar;
    private javax.swing.JLabel imagenField;
    private javax.swing.JButton jButton1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField nombre;
    // End of variables declaration//GEN-END:variables
}
