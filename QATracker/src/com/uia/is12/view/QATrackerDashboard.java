/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uia.is12.view;

import com.uia.is12.business.QATrackerBusiness;
import com.uia.is12.domain.Issue;
import com.uia.is12.domain.Proyecto;
import com.uia.is12.panel.QAGradient;
import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author raiam.quesada.urena
 */
public class QATrackerDashboard extends javax.swing.JFrame {
    private QATrackerBusiness qaTrackerBusiness = new QATrackerBusiness();
    /**
     * Creates new form QATrackerDashboard
     */
    public QATrackerDashboard() {
        initComponents();
        addPanel();
        loadPerfilStuff();
        fillProjects();
        fillWatchers();
    }
    public final void addPanel(){
        QAGradient as = new QAGradient("MAIN");
        this.setLayout(new BorderLayout());
        this.add(as, BorderLayout.CENTER);
        panelIntro.setOpaque(false);
        panelIssues.setOpaque(false);
        panelBuscar.setVisible(false);
    }
    
    private void loadPerfilStuff(){
        try {
            welcome.setText("Bienvenido: "+qaTrackerBusiness.getLoggedUser() + " / " + qaTrackerBusiness.getRole(qaTrackerBusiness.getLoggedUser()));
        } catch (SQLException ex) {
            Logger.getLogger(QATrackerDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Issue> issues = new ArrayList<Issue>();
        issues = qaTrackerBusiness.returnIssuesFromCurrentUser();
        DefaultTableModel modelo = new DefaultTableModel();
        if(issues.size() > 0){
            String datos[]= new String[5];
            modelo.addColumn("Número de Issue");
            modelo.addColumn("Nombre");
            modelo.addColumn("Descripcion");
            modelo.addColumn("Usuario Creador");
            modelo.addColumn("Usuario a Resolver");
            for(Issue issue: issues){
                datos[4]=issue.getNombreCreador();
                datos[3]=issue.getNombreAsignador();
                datos[2]=issue.getDescription();
                datos[1]=issue.getName();
                datos[0]=String.valueOf(issue.getId());
                modelo.addRow(datos);
            }    
        }  else {
            String value[] = new String[1];
            value[0]="No hay issues asignados a este usuario";
            modelo.addColumn("Issues");
            modelo.addRow(value);
            
        }
        issuesNaming.setModel(modelo);
        
    }
    
    public void fillWatchers() {
        int userId = 0;
        ArrayList<Issue> issues = null;
        try {
            userId = qaTrackerBusiness.getIdFromUsername(qaTrackerBusiness.getLoggedUser());
        } catch (SQLException ex) {
            Logger.getLogger(QATrackerDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            issues = qaTrackerBusiness.getWatchersCurrrentUser(userId);
        } catch (SQLException ex) {
            Logger.getLogger(QATrackerDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
        DefaultTableModel modelo = new DefaultTableModel();
        if(issues.size() > 0){
                modelo.addColumn("Número de Issue");modelo.addColumn("Nombre");modelo.addColumn("Descripción");
                String datos[] = new String[3];
                for(Issue issue: issues){
                    datos[0] = String.valueOf(issue.getId());
                    datos[1] = issue.getName();
                    datos[2] = issue.getDescription();
                    modelo.addRow(datos);
                }            
            } else{
                String value[] = new String[1];
                value[0]="No estás como observador en ningún issue";
                modelo.addColumn("Issues");
                modelo.addRow(value);
            }
       lector.setModel(modelo);
    
    }
    
    
    public void fillProjects(){
        ArrayList<Proyecto> proyects = qaTrackerBusiness.getProyectsCurrentUser();
        DefaultTableModel modelo = new DefaultTableModel();

            if(proyects.size() > 0){
                modelo.addColumn("Número de Proyecto");modelo.addColumn("Descripcion");modelo.addColumn("Creador");
                String datos[] = new String[3];
                for(Proyecto project: proyects){
                    datos[0] = String.valueOf(project.getId());
                    datos[1] = project.getDescription();
                    datos[2] = project.getNombreUserCreador();
                    modelo.addRow(datos);
                }            
            } else{
                String value[] = new String[1];
                value[0]="No hay proyectos asignados a este usuario";
                modelo.addColumn("Proyectos");
                modelo.addRow(value);
            }
            
            Proyectos.setModel(modelo);
           
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu5 = new javax.swing.JMenu();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu6 = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu8 = new javax.swing.JMenu();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        welcome = new javax.swing.JLabel();
        panelIntro = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        panelIssues = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        issuesNaming = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        Proyectos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jScrollPane4 = new javax.swing.JScrollPane();
        lector = new javax.swing.JTable();
        panelBuscar = new javax.swing.JPanel();
        proyectoPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        idProyect = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        issuePanel = new javax.swing.JPanel();
        cargarIssue = new javax.swing.JButton();
        idIssueBox = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tareasPanel = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        idTareasBox = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        creartarea = new javax.swing.JMenuItem();
        jMenu10 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();

        jMenu5.setText("jMenu5");

        jMenu6.setText("File");
        jMenuBar2.add(jMenu6);

        jMenu7.setText("Edit");
        jMenuBar2.add(jMenu7);

        jMenu8.setText("File");
        jMenuBar3.add(jMenu8);

        jMenu9.setText("Edit");
        jMenuBar3.add(jMenu9);

        jMenuItem6.setText("jMenuItem6");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dashboard");
        setBackground(new java.awt.Color(255, 255, 255));
        setBounds(new java.awt.Rectangle(200, 100, 700, 500));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        welcome.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        welcome.setText("Bienvenido: ");
        getContentPane().add(welcome, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 820, 40));

        panelIntro.setBackground(new java.awt.Color(255, 255, 255));
        panelIntro.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        panelIntro.setOpaque(false);

        jLabel3.setBackground(new java.awt.Color(0, 102, 204));
        jLabel3.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel3.setText("Introduccion del programa");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("Podras llevar la informacion mas detallada acerca del control de los bugs y demas errores que tu software pueda tener, asi como llevar un control total de todo. Ademas de ayudamos a llevar un control de proyectos trabajados.");
        jTextArea1.setAutoscrolls(false);
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout panelIntroLayout = new javax.swing.GroupLayout(panelIntro);
        panelIntro.setLayout(panelIntroLayout);
        panelIntroLayout.setHorizontalGroup(
            panelIntroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelIntroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelIntroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addGroup(panelIntroLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)))
                .addContainerGap())
        );
        panelIntroLayout.setVerticalGroup(
            panelIntroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelIntroLayout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(panelIntro, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 390, 240));

        panelIssues.setBackground(new java.awt.Color(255, 255, 255));
        panelIssues.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        panelIssues.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel2.setText("Issues creados y asignados a su nombre");

        issuesNaming.setAutoCreateRowSorter(true);
        issuesNaming.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        issuesNaming.setToolTipText("");
        issuesNaming.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        issuesNaming.setGridColor(new java.awt.Color(0, 0, 0));
        issuesNaming.setOpaque(false);
        jScrollPane1.setViewportView(issuesNaming);

        javax.swing.GroupLayout panelIssuesLayout = new javax.swing.GroupLayout(panelIssues);
        panelIssues.setLayout(panelIssuesLayout);
        panelIssuesLayout.setHorizontalGroup(
            panelIssuesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelIssuesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelIssuesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
                    .addComponent(jSeparator2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelIssuesLayout.setVerticalGroup(
            panelIssuesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelIssuesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(274, 274, 274))
        );

        getContentPane().add(panelIssues, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 90, 620, 240));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setOpaque(false);

        jLabel5.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel5.setText("Proyectos creados");

        Proyectos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(Proyectos);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 350, 620, 230));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setOpaque(false);

        jLabel4.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel4.setText("Issues que esta siguiendo");

        lector.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(lector);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                        .addGap(28, 28, 28))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jSeparator4))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 390, 230));

        panelBuscar.setBackground(new java.awt.Color(130, 192, 230));
        panelBuscar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        proyectoPanel.setOpaque(false);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/uia/is12/images/cargar.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        idProyect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idProyectActionPerformed(evt);
            }
        });

        jLabel1.setText("Buscar proyectos por id: ");

        javax.swing.GroupLayout proyectoPanelLayout = new javax.swing.GroupLayout(proyectoPanel);
        proyectoPanel.setLayout(proyectoPanelLayout);
        proyectoPanelLayout.setHorizontalGroup(
            proyectoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, proyectoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(idProyect, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );
        proyectoPanelLayout.setVerticalGroup(
            proyectoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(proyectoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(proyectoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(proyectoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(idProyect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        panelBuscar.add(proyectoPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, -1));

        issuePanel.setOpaque(false);

        cargarIssue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/uia/is12/images/cargar.png"))); // NOI18N
        cargarIssue.setBorderPainted(false);
        cargarIssue.setContentAreaFilled(false);
        cargarIssue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarIssueActionPerformed(evt);
            }
        });

        idIssueBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idIssueBoxActionPerformed(evt);
            }
        });

        jLabel6.setText("Buscar issues por id: ");

        javax.swing.GroupLayout issuePanelLayout = new javax.swing.GroupLayout(issuePanel);
        issuePanel.setLayout(issuePanelLayout);
        issuePanelLayout.setHorizontalGroup(
            issuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, issuePanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(idIssueBox, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cargarIssue, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        issuePanelLayout.setVerticalGroup(
            issuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(issuePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(issuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(issuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(idIssueBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6))
                    .addComponent(cargarIssue, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        panelBuscar.add(issuePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 70));

        tareasPanel.setOpaque(false);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/uia/is12/images/cargar.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        idTareasBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idTareasBoxActionPerformed(evt);
            }
        });

        jLabel7.setText("Buscar tareas por id: ");

        javax.swing.GroupLayout tareasPanelLayout = new javax.swing.GroupLayout(tareasPanel);
        tareasPanel.setLayout(tareasPanelLayout);
        tareasPanelLayout.setHorizontalGroup(
            tareasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tareasPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(idTareasBox, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );
        tareasPanelLayout.setVerticalGroup(
            tareasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tareasPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tareasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tareasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(idTareasBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        panelBuscar.add(tareasPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(panelBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 110));

        jMenu1.setText("Buscar");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem2.setText("Proyecto");
        jMenuItem2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem2MouseClicked(evt);
            }
        });
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem4.setText("Tarea");
        jMenuItem4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem4MouseClicked(evt);
            }
        });
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem5.setText("Issue");
        jMenuItem5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem5MouseClicked(evt);
            }
        });
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Issue");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });

        jMenuItem1.setText("Crear");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Proyecto");

        jMenuItem3.setText("Crear");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Tareas");

        creartarea.setText("Crear");
        creartarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creartareaActionPerformed(evt);
            }
        });
        jMenu4.add(creartarea);

        jMenuBar1.add(jMenu4);

        jMenu10.setText("Opciones");
        jMenu10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu10ActionPerformed(evt);
            }
        });

        jMenuItem7.setText("Salir");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem7);

        jMenuBar1.add(jMenu10);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked

    }//GEN-LAST:event_jMenu1MouseClicked

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
       new QATrackerCreateIssue().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        int id = Integer.parseInt(idProyect.getText());
           try {
            Boolean res=qaTrackerBusiness.searchIDProyect(id);
            if(res){
                new QATrackerViewProyect(qaTrackerBusiness.getIDProyect(id)).setVisible(true);
                this.dispose();
            }
            else 
            {
                JOptionPane.showMessageDialog(this, "No hemos encontrado el Proyecto con el ID: "+idProyect.getText(), "Vuelve a intentarlo", JOptionPane.ERROR_MESSAGE);
            }
            System.out.println(res);
        } catch (SQLException ex) {
            Logger.getLogger(QATrackerDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        new QATrackerCreateProyect().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void creartareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creartareaActionPerformed
        new QATrackerCreateTask().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_creartareaActionPerformed

    private void idProyectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idProyectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idProyectActionPerformed

    private void cargarIssueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarIssueActionPerformed
        int id = Integer.parseInt(idIssueBox.getText());
           try {
            Boolean res=qaTrackerBusiness.searchIDIssue(id);
            if(res){
                new QATrackerViewIssue(qaTrackerBusiness.getIDIssue(id)).setVisible(true);
                this.dispose();
            }
            else 
            {
                JOptionPane.showMessageDialog(this, "No hemos encontrado el Issue con el ID: "+idProyect.getText(), "Vuelve a intentarlo", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QATrackerDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cargarIssueActionPerformed

    private void idIssueBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idIssueBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idIssueBoxActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int id = Integer.parseInt(idTareasBox.getText());
           try {
            Boolean res=qaTrackerBusiness.searchIDTask(id);
            if(res){
                new QATrackerViewTask(qaTrackerBusiness.getIDTask(id)).setVisible(true);
                this.dispose();
            }
            else 
            {
                JOptionPane.showMessageDialog(this, "No hemos encontrado la tarea con el ID: "+id, "Vuelve a intentarlo", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QATrackerDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void idTareasBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idTareasBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idTareasBoxActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        panelBuscar.setVisible(true);
        proyectoPanel.setVisible(true);
        issuePanel.setVisible(false);
        tareasPanel.setVisible(false);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem5MouseClicked

    }//GEN-LAST:event_jMenuItem5MouseClicked

    private void jMenuItem2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem2MouseClicked

    }//GEN-LAST:event_jMenuItem2MouseClicked

    private void jMenuItem4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem4MouseClicked

    }//GEN-LAST:event_jMenuItem4MouseClicked

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        panelBuscar.setVisible(true);
        proyectoPanel.setVisible(false);
        issuePanel.setVisible(false);
        tareasPanel.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        panelBuscar.setVisible(true);
        proyectoPanel.setVisible(false);
        issuePanel.setVisible(true);
        tareasPanel.setVisible(false);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenu10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu10ActionPerformed
       
    }//GEN-LAST:event_jMenu10ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        new QATrackerView().setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

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
            java.util.logging.Logger.getLogger(QATrackerDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QATrackerDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QATrackerDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QATrackerDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QATrackerDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Proyectos;
    private javax.swing.JButton cargarIssue;
    private javax.swing.JMenuItem creartarea;
    private javax.swing.JTextField idIssueBox;
    private javax.swing.JTextField idProyect;
    private javax.swing.JTextField idTareasBox;
    private javax.swing.JPanel issuePanel;
    private javax.swing.JTable issuesNaming;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTable lector;
    private javax.swing.JPanel panelBuscar;
    private javax.swing.JPanel panelIntro;
    private javax.swing.JPanel panelIssues;
    private javax.swing.JPanel proyectoPanel;
    private javax.swing.JPanel tareasPanel;
    private javax.swing.JLabel welcome;
    // End of variables declaration//GEN-END:variables
}
