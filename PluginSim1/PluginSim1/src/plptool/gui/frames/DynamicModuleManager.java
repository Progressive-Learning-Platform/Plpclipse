/*
    Copyright 2011-2013 David Fritz, Brian Gordon, Wira Mulia

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

 */

package plptool.gui.frames;

import plptool.dmf.*;
import plptool.*;
import plptool.gui.ProjectDriver;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author wira
 */
public class DynamicModuleManager extends javax.swing.JDialog {

    private ProjectDriver plp;
    private boolean embedOnly = false;

    /** Creates new form DynamicModuleManager */
    public DynamicModuleManager(java.awt.Frame parent, boolean modal, ProjectDriver plp) {
        super(parent, modal);
        initComponents();
        this.plp = plp;
        updateClassList();
        setLocationRelativeTo(parent);

        cmbCallbackEvent.addItem("EXIT                        = 0");
        cmbCallbackEvent.addItem("START                       = 1");
        cmbCallbackEvent.addItem("COMMAND                     = 2");
        cmbCallbackEvent.addItem("EVENT                       = 3");

        cmbCallbackEvent.addItem("PROJECT_NEW                 = 4");
        cmbCallbackEvent.addItem("PROJECT_SAVE                = 5");
        cmbCallbackEvent.addItem("PROJECT_OPEN                = 6");
        cmbCallbackEvent.addItem("PROJECT_OPEN_SUCCESSFUL     = 7");
        cmbCallbackEvent.addItem("PROJECT_OPEN_ENTRY          = 8");
        cmbCallbackEvent.addItem("PROJECT_OPENASM_CHANGE      = 9");
        cmbCallbackEvent.addItem("PROJECT_NEW_ASM             = 10");
        cmbCallbackEvent.addItem("PROJECT_REMOVE_ASM          = 11");

        cmbCallbackEvent.addItem("LOAD_CONFIG_LINE            = 12");
        cmbCallbackEvent.addItem("SAVE_CONFIG                 = 13");

        cmbCallbackEvent.addItem("EVENT_SIMULATE              = 14");
        cmbCallbackEvent.addItem("EVENT_DESIMULATE            = 15");
        cmbCallbackEvent.addItem("EVENT_SIM_INIT              = 16");
        cmbCallbackEvent.addItem("EVENT_SIM_POST_INIT         = 17");
        cmbCallbackEvent.addItem("EVENT_SIM_POST_UNINIT       = 18");
        cmbCallbackEvent.addItem("EVENT_ASSEMBLE              = 19");
        cmbCallbackEvent.addItem("EVENT_POST_ASSEMBLE         = 20");
        cmbCallbackEvent.addItem("EVENT_PROGRAM               = 21");

        cmbCallbackEvent.addItem("SIM_STEP_AGGREGATE          = 22");
        cmbCallbackEvent.addItem("SIM_POST_STEP_AGGREGATE     = 23");
        cmbCallbackEvent.addItem("SIM_RUN_START               = 24");
        cmbCallbackEvent.addItem("SIM_RUN_STOP                = 25");
        cmbCallbackEvent.addItem("SIM_STEP                    = 26");
        cmbCallbackEvent.addItem("SIM_POST_STEP               = 27");
        cmbCallbackEvent.addItem("SIM_RESET                   = 28");

        cmbCallbackEvent.addItem("BUS_READ                    = 29");
        cmbCallbackEvent.addItem("BUS_POST_READ               = 30");
        cmbCallbackEvent.addItem("BUS_WRITE                   = 31");
        cmbCallbackEvent.addItem("BUS_EVAL                    = 32");
        cmbCallbackEvent.addItem("BUS_ADD                     = 33");
        cmbCallbackEvent.addItem("BUS_REMOVE                  = 34");
        cmbCallbackEvent.addItem("BUS_GUI_EVAL                = 35");

        cmbCallbackEvent.addItem("EDITOR_TEXT_SET             = 36");
        cmbCallbackEvent.addItem("GUI_UPDATE                  = 37");
        cmbCallbackEvent.addItem("GUI_VIEW_REFRESH            = 38");
        cmbCallbackEvent.addItem("CRITICAL_ERROR              = 39");
        cmbCallbackEvent.addItem("EVENT_ASSEMBLE_INIT         = 40");
        cmbCallbackEvent.addItem("EVENT_PROGRAM_INIT          = 41");
        cmbCallbackEvent.addItem("EVENT_HEADLESS_START        = 42");
        cmbCallbackEvent.addItem("OPTIONS_UPDATE              = 43");        
    }

    public void setEmbedOnly() {
        tabMain.setSelectedIndex(2);
        tabMain.setEnabledAt(0, false);
        tabMain.setEnabledAt(1, false);
        embedOnly = true;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabMain = new javax.swing.JTabbedPane();
        paneDynamicModuleClasses = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblModuleClasses = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtClassName = new javax.swing.JTextField();
        btnBrowseClass = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        txtClassFile = new javax.swing.JTextField();
        btnRegisterClass = new javax.swing.JButton();
        paneRegisteredCallbacks = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        cmbCallbackEvent = new javax.swing.JComboBox<String>();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCallbacks = new javax.swing.JTable();
        paneEmbedManifest = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtEmbedManifestJAR = new javax.swing.JTextField();
        btnEmbedManifestBrowse = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtEmbedManifestLicense = new javax.swing.JTextField();
        txtEmbedManifestAuthor = new javax.swing.JTextField();
        txtEmbedManifestTitle = new javax.swing.JTextField();
        txtEmbedManifestVersion = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        btnEmbedManifest = new javax.swing.JButton();
        txtEmbedManifestDescription = new javax.swing.JTextField();
        btnClose = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(plptool.gui.PLPToolApp.class).getContext().getResourceMap(DynamicModuleManager.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        setResizable(false);

        tabMain.setName("tabMain"); // NOI18N

        paneDynamicModuleClasses.setName("paneDynamicModuleClasses"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tblModuleClasses.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Index", "Name", "Superclass"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblModuleClasses.setName("tblModuleClasses"); // NOI18N
        tblModuleClasses.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblModuleClasses);
        tblModuleClasses.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tblModuleClasses.columnModel.title0")); // NOI18N
        tblModuleClasses.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tblModuleClasses.columnModel.title1")); // NOI18N
        tblModuleClasses.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("tblModuleClasses.columnModel.title2")); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        txtClassName.setText(resourceMap.getString("txtClassName.text")); // NOI18N
        txtClassName.setName("txtClassName"); // NOI18N

        btnBrowseClass.setText(resourceMap.getString("btnBrowseClass.text")); // NOI18N
        btnBrowseClass.setName("btnBrowseClass"); // NOI18N
        btnBrowseClass.setPreferredSize(new java.awt.Dimension(89, 23));
        btnBrowseClass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseClassActionPerformed(evt);
            }
        });

        jSeparator1.setName("jSeparator1"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        txtClassFile.setText(resourceMap.getString("txtClassFile.text")); // NOI18N
        txtClassFile.setName("txtClassFile"); // NOI18N

        btnRegisterClass.setText(resourceMap.getString("btnRegisterClass.text")); // NOI18N
        btnRegisterClass.setName("btnRegisterClass"); // NOI18N
        btnRegisterClass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterClassActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout paneDynamicModuleClassesLayout = new javax.swing.GroupLayout(paneDynamicModuleClasses);
        paneDynamicModuleClasses.setLayout(paneDynamicModuleClassesLayout);
        paneDynamicModuleClassesLayout.setHorizontalGroup(
            paneDynamicModuleClassesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneDynamicModuleClassesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneDynamicModuleClassesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                    .addGroup(paneDynamicModuleClassesLayout.createSequentialGroup()
                        .addGroup(paneDynamicModuleClassesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(23, 23, 23)
                        .addGroup(paneDynamicModuleClassesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtClassFile, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtClassName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(paneDynamicModuleClassesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnRegisterClass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBrowseClass, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE))
                .addContainerGap())
        );
        paneDynamicModuleClassesLayout.setVerticalGroup(
            paneDynamicModuleClassesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneDynamicModuleClassesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneDynamicModuleClassesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtClassName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegisterClass))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneDynamicModuleClassesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtClassFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBrowseClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabMain.addTab(resourceMap.getString("paneDynamicModuleClasses.TabConstraints.tabTitle"), paneDynamicModuleClasses); // NOI18N

        paneRegisteredCallbacks.setName("paneRegisteredCallbacks"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        cmbCallbackEvent.setFont(resourceMap.getFont("cmbCallbackEvent.font")); // NOI18N
        cmbCallbackEvent.setName("cmbCallbackEvent"); // NOI18N
        cmbCallbackEvent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cmbCallbackEventMousePressed(evt);
            }
        });
        cmbCallbackEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCallbackEventActionPerformed(evt);
            }
        });

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        tblCallbacks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order", "Class"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCallbacks.setName("tblCallbacks"); // NOI18N
        jScrollPane3.setViewportView(tblCallbacks);
        tblCallbacks.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tblCallbacks.columnModel.title0")); // NOI18N
        tblCallbacks.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tblCallbacks.columnModel.title1")); // NOI18N

        javax.swing.GroupLayout paneRegisteredCallbacksLayout = new javax.swing.GroupLayout(paneRegisteredCallbacks);
        paneRegisteredCallbacks.setLayout(paneRegisteredCallbacksLayout);
        paneRegisteredCallbacksLayout.setHorizontalGroup(
            paneRegisteredCallbacksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneRegisteredCallbacksLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneRegisteredCallbacksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                    .addGroup(paneRegisteredCallbacksLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbCallbackEvent, 0, 541, Short.MAX_VALUE)))
                .addContainerGap())
        );
        paneRegisteredCallbacksLayout.setVerticalGroup(
            paneRegisteredCallbacksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneRegisteredCallbacksLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneRegisteredCallbacksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cmbCallbackEvent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabMain.addTab(resourceMap.getString("paneRegisteredCallbacks.TabConstraints.tabTitle"), paneRegisteredCallbacks); // NOI18N

        paneEmbedManifest.setName("paneEmbedManifest"); // NOI18N

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        txtEmbedManifestJAR.setText(resourceMap.getString("txtEmbedManifestJAR.text")); // NOI18N
        txtEmbedManifestJAR.setName("txtEmbedManifestJAR"); // NOI18N

        btnEmbedManifestBrowse.setText(resourceMap.getString("btnEmbedManifestBrowse.text")); // NOI18N
        btnEmbedManifestBrowse.setName("btnEmbedManifestBrowse"); // NOI18N
        btnEmbedManifestBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmbedManifestBrowseActionPerformed(evt);
            }
        });

        jSeparator2.setName("jSeparator2"); // NOI18N

        jLabel8.setFont(resourceMap.getFont("jLabel8.font")); // NOI18N
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N

        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        txtEmbedManifestLicense.setText(resourceMap.getString("txtEmbedManifestLicense.text")); // NOI18N
        txtEmbedManifestLicense.setName("txtEmbedManifestLicense"); // NOI18N

        txtEmbedManifestAuthor.setText(resourceMap.getString("txtEmbedManifestAuthor.text")); // NOI18N
        txtEmbedManifestAuthor.setName("txtEmbedManifestAuthor"); // NOI18N

        txtEmbedManifestTitle.setText(resourceMap.getString("txtEmbedManifestTitle.text")); // NOI18N
        txtEmbedManifestTitle.setName("txtEmbedManifestTitle"); // NOI18N

        txtEmbedManifestVersion.setText(resourceMap.getString("txtEmbedManifestVersion.text")); // NOI18N
        txtEmbedManifestVersion.setName("txtEmbedManifestVersion"); // NOI18N

        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N

        btnEmbedManifest.setText(resourceMap.getString("btnEmbedManifest.text")); // NOI18N
        btnEmbedManifest.setName("btnEmbedManifest"); // NOI18N
        btnEmbedManifest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmbedManifestActionPerformed(evt);
            }
        });

        txtEmbedManifestDescription.setText(resourceMap.getString("txtEmbedManifestDescription.text")); // NOI18N
        txtEmbedManifestDescription.setName("txtEmbedManifestDescription"); // NOI18N

        javax.swing.GroupLayout paneEmbedManifestLayout = new javax.swing.GroupLayout(paneEmbedManifest);
        paneEmbedManifest.setLayout(paneEmbedManifestLayout);
        paneEmbedManifestLayout.setHorizontalGroup(
            paneEmbedManifestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneEmbedManifestLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneEmbedManifestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                    .addGroup(paneEmbedManifestLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmbedManifestJAR, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEmbedManifestBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8)
                    .addGroup(paneEmbedManifestLayout.createSequentialGroup()
                        .addGroup(paneEmbedManifestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9)
                            .addComponent(jLabel12))
                        .addGap(10, 10, 10)
                        .addGroup(paneEmbedManifestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmbedManifestLicense, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                            .addComponent(txtEmbedManifestAuthor, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                            .addComponent(txtEmbedManifestTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                            .addComponent(txtEmbedManifestDescription, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneEmbedManifestLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(28, 28, 28)
                        .addComponent(txtEmbedManifestVersion, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE))
                    .addComponent(btnEmbedManifest, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        paneEmbedManifestLayout.setVerticalGroup(
            paneEmbedManifestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneEmbedManifestLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneEmbedManifestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtEmbedManifestJAR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEmbedManifestBrowse))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paneEmbedManifestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtEmbedManifestTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paneEmbedManifestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtEmbedManifestAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paneEmbedManifestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtEmbedManifestLicense, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paneEmbedManifestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmbedManifestDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paneEmbedManifestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmbedManifestVersion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addComponent(btnEmbedManifest)
                .addContainerGap(235, Short.MAX_VALUE))
        );

        tabMain.addTab(resourceMap.getString("paneEmbedManifest.TabConstraints.tabTitle"), paneEmbedManifest); // NOI18N

        btnClose.setText(resourceMap.getString("btnClose.text")); // NOI18N
        btnClose.setName("btnClose"); // NOI18N
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        jLabel5.setFont(resourceMap.getFont("jLabel5.font")); // NOI18N
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 296, Short.MAX_VALUE)
                        .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tabMain, javax.swing.GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabMain, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClose)
                    .addComponent(jLabel5))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void updateClassList() {
        DefaultTableModel tbl = (DefaultTableModel) tblModuleClasses.getModel();
        Class sC;
        while(tbl.getRowCount() > 0)
            tbl.removeRow(0);
        Class c;
        for(int i = 0; i < DynamicModuleFramework.getNumberOfClasses(); i++) {
            c = DynamicModuleFramework.getDynamicModuleClass(i);
            sC = c.getSuperclass();
            Object[] row = {i, c.getName(), sC != null ? c.getSuperclass().getName() : "N/A"};
            tbl.addRow(row);
        }
    }
    
    private void btnBrowseClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseClassActionPerformed
        final javax.swing.JFileChooser fc = new javax.swing.JFileChooser();
        int retVal = fc.showOpenDialog(null);
        if(retVal == javax.swing.JFileChooser.APPROVE_OPTION) {
            txtClassFile.setText(fc.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_btnBrowseClassActionPerformed

    private void btnRegisterClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterClassActionPerformed
        String className = txtClassName.getText();
        if(className.equals("*"))
            DynamicModuleFramework.loadAllFromJar(txtClassFile.getText());
        else
            DynamicModuleFramework.loadModuleClass(txtClassFile.getText(), txtClassName.getText());
        updateClassList();
    }//GEN-LAST:event_btnRegisterClassActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        if(!embedOnly) {
            this.setVisible(false);
            this.dispose();
        } else {
            System.exit(0);
        }
    }//GEN-LAST:event_btnCloseActionPerformed

    private void cmbCallbackEventMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbCallbackEventMousePressed

    }//GEN-LAST:event_cmbCallbackEventMousePressed

    private void cmbCallbackEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCallbackEventActionPerformed
        if(cmbCallbackEvent.getSelectedIndex() > -1) {
            DefaultTableModel tbl = (DefaultTableModel) tblCallbacks.getModel();
            while(tbl.getRowCount() > 0)
                tbl.removeRow(0);
            Object[] callbacks = CallbackRegistry.getCallbacks(cmbCallbackEvent.getSelectedIndex());
            for(int i = 0; i < callbacks.length; i++) {
                String[] row = {"" + i, callbacks[i].getClass().getCanonicalName()};
                tbl.addRow(row);
            }
            tblCallbacks.setModel(tbl);
        }
    }//GEN-LAST:event_cmbCallbackEventActionPerformed

    private void btnEmbedManifestBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmbedManifestBrowseActionPerformed
        java.io.File f = PLPToolbox.openFileDialog(Constants.launchPath);
        if(f != null)
            txtEmbedManifestJAR.setText(f.getAbsolutePath());
    }//GEN-LAST:event_btnEmbedManifestBrowseActionPerformed

    private void btnEmbedManifestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmbedManifestActionPerformed
        int ret;
        String manifest = DynamicModuleFramework.generateManifest(
                txtEmbedManifestJAR.getText(), txtEmbedManifestTitle.getText(),
                txtEmbedManifestAuthor.getText(), txtEmbedManifestLicense.getText(),
                txtEmbedManifestDescription.getText(), txtEmbedManifestVersion.getText());
        if(manifest != null) {
            ret = PLPToolbox.addToJar(txtEmbedManifestJAR.getText(), "plp.manifest", manifest.getBytes());
            if(ret != Constants.PLP_OK)
                PLPToolbox.showErrorDialog(null, "Failed to embed manifest!");
            else
                Msg.M("Done!");
        } else
            PLPToolbox.showErrorDialog(null, "Failed to generate manifest!");
        System.exit(0);
    }//GEN-LAST:event_btnEmbedManifestActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBrowseClass;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnEmbedManifest;
    private javax.swing.JButton btnEmbedManifestBrowse;
    private javax.swing.JButton btnRegisterClass;
    private javax.swing.JComboBox<String> cmbCallbackEvent;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel paneDynamicModuleClasses;
    private javax.swing.JPanel paneEmbedManifest;
    private javax.swing.JPanel paneRegisteredCallbacks;
    private javax.swing.JTabbedPane tabMain;
    private javax.swing.JTable tblCallbacks;
    private javax.swing.JTable tblModuleClasses;
    private javax.swing.JTextField txtClassFile;
    private javax.swing.JTextField txtClassName;
    private javax.swing.JTextField txtEmbedManifestAuthor;
    private javax.swing.JTextField txtEmbedManifestDescription;
    private javax.swing.JTextField txtEmbedManifestJAR;
    private javax.swing.JTextField txtEmbedManifestLicense;
    private javax.swing.JTextField txtEmbedManifestTitle;
    private javax.swing.JTextField txtEmbedManifestVersion;
    // End of variables declaration//GEN-END:variables

}
