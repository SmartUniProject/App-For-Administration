/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartuni.ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import smartuni.database.MySqlConnector;
import static smartuni.ui.CourseUnits.f1;
import static smartuni.ui.CourseUnits.f2;
import static smartuni.ui.CourseUnits.f3;
import static smartuni.ui.Lecturers.con;
import static smartuni.ui.Lecturers.loadLectures;
import static smartuni.ui.Lecturers.tbl1;

/**
 *
 * @author Shalitha Suranga
 */



public class AssignLecturers extends javax.swing.JFrame {
    
    public static String courseId;
    public static Connection con=null;
    public static JTable tbl1;
    public static JTable tbl2;
    public static JComboBox f1;
    

    

    /**
     * Creates new form AssignLecturers
     */
    public AssignLecturers(String id) {
        AssignLecturers.courseId=id;
        
        con=MySqlConnector.getConnection();
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
        tbl1=jTable1;
        tbl2=jTable2;
        f1=jComboBox1;
        
        try{
            Statement st=con.createStatement();
            jComboBox1.removeAll();
            jComboBox1.addItem("Any");
            ResultSet result=st.executeQuery("SELECT * FROM sm_department ORDER BY name ASC;");
            while(result.next()){
                jComboBox1.addItem(result.getString("id")+"─"+result.getString("name"));
            }

        }
        catch(Exception e){
        }
        
        loadAssigned();
        loadLectures();

    }
    
    
    private static void setWidthAsPercentages(JTable table, double... percentages) {
        final double factor = 10000;

        TableColumnModel model = table.getColumnModel();
        for (int columnIndex = 0; columnIndex < percentages.length; columnIndex++) {
            TableColumn column = model.getColumn(columnIndex);
            column.setPreferredWidth((int) (percentages[columnIndex] * factor));
        }
    }
 public static boolean loadLectures(){
 
        try{
            
            
                StringBuilder f=new StringBuilder("");
           
            if(!f1.getSelectedItem().toString().equals("Any")) f.append(" AND sm_lecturer.dept_id='"+f1.getSelectedItem().toString().split("─")[0]+"'");

           
            Statement st=con.createStatement();
            ResultSet result=st.executeQuery("SELECT * FROM sm_lecturer WHERE id NOT IN(SELECT sm_lecturer.id FROM sm_lecturer INNER JOIN sm_lecturing ON sm_lecturing.lecturer_id=sm_lecturer.id AND sm_lecturing.course_id ='"+ courseId +"' GROUP BY sm_lecturer.id) "+f+"  ORDER BY sm_lecturer.firstname ASC;"); 
            DefaultTableModel model=  new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int i, int i1) {
                    return false;
                }
            };
            Object[] columns=new Object[5];
            columns[0]="ID";
            columns[1]="Name";
            columns[2]="Mobile No.";
            columns[3]="Email ID";
            columns[4]="Address";
 
            model.setColumnIdentifiers(columns);

            while(result.next()){
                model.addRow(new Object[]{result.getString("id"),result.getString("title")+" "+result.getString("firstname")+" "+result.getString("lastname"),result.getString("mobileno"),result.getString("email"),result.getString("address")});
            }
  
            AssignLecturers.tbl2.setModel(model);
            
            tbl2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent me) {
                   // facultyId=Integer.parseInt(tbl1.getValueAt(tbl1.getSelectedRow(), 0).toString());
                    //loadDepartments();
                    
                    super.mouseClicked(me);
                }
                
            });

            setWidthAsPercentages(tbl2,0.05,0.2,0.15,0.2,0.3);

            return true;
        
        }
        catch(Exception e){
            return false;
        }


        
        
    }
 
 
 
  public static boolean loadAssigned(){
 
        try{
           
            Statement st=con.createStatement();
            ResultSet result=st.executeQuery("SELECT sm_lecturer.* FROM sm_lecturing INNER JOIN sm_course ON sm_lecturing.course_id=sm_course.id INNER JOIN sm_lecturer ON sm_lecturing.lecturer_id=sm_lecturer.id WHERE sm_lecturing.course_id='"+ courseId +"';"); 
            DefaultTableModel model=  new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int i, int i1) {
                    return false;
                }
            };
            Object[] columns=new Object[5];
            columns[0]="ID";
            columns[1]="Name";
            columns[2]="Mobile No.";
            columns[3]="Email ID";
            columns[4]="Address";
 
            model.setColumnIdentifiers(columns);

            while(result.next()){
                model.addRow(new Object[]{result.getString("id"),result.getString("title")+" "+result.getString("firstname")+" "+result.getString("lastname"),result.getString("mobileno"),result.getString("email"),result.getString("address")});
            }
  
            AssignLecturers.tbl1.setModel(model);
            
            tbl1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent me) {
                   // facultyId=Integer.parseInt(tbl1.getValueAt(tbl1.getSelectedRow(), 0).toString());
                    //loadDepartments();
                    
                    super.mouseClicked(me);
                }
                
            });

            setWidthAsPercentages(tbl1,0.05,0.2,0.15,0.2,0.3);

            return true;
        
        }
        catch(Exception e){
            return false;
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

        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Assign lecturers");
        setAlwaysOnTop(true);

        jPanel5.setBackground(new java.awt.Color(153, 153, 153));

        jLabel4.setBackground(new java.awt.Color(204, 204, 204));
        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 153));
        jLabel4.setText(" Assign lecturers");
        jLabel4.setOpaque(true);

        jButton8.setText("OK");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton1.setText("Cancel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Assigned Lecturers");

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Select Lecturers");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Department ");

        jButton2.setText("Search");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Reset");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Assign selected");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Unassign");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/smartuni/graphics/helpicon.png"))); // NOI18N
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 758, Short.MAX_VALUE)
                            .addComponent(jScrollPane2)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(14, 14, 14))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton8)
                    .addComponent(jButton4))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        this.dispose();

    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int a=JOptionPane.showConfirmDialog(rootPane, "Are you sure you want to perform?", "Assign Lecturer", JOptionPane.YES_NO_OPTION);
        if(a==JOptionPane.YES_OPTION){
            try{
                Statement st=con.createStatement();
                int selectedId=Integer.parseInt(jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString());
                System.out.println(selectedId);
                int b=st.executeUpdate("INSERT INTO sm_lecturing(course_id,lecturer_id) VALUES('"+ courseId +"','"+ selectedId +"');"); 
                if(b>0){
                    loadAssigned();
                    loadLectures();
                }
                
            }
            catch(Exception e){
                
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int a=JOptionPane.showConfirmDialog(rootPane, "Are you sure you want to perform?", "Unassign Lecturer", JOptionPane.YES_NO_OPTION);
        if(a==JOptionPane.YES_OPTION){
            try{
                Statement st=con.createStatement();
                int selectedId=Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
                int b=st.executeUpdate("DELETE FROM sm_lecturing WHERE course_id= '"+ courseId +"' AND lecturer_id='"+ selectedId +"' LIMIT 1;"); 
                //System.out.println("DELETE FROM sm_lecturing WHERE course_id= '"+ courseId +"' AND lecturer_id='"+ selectedId +"' LIMIT 1;");
                if(b>0){
                    loadAssigned();
                    loadLectures();
                }
                
            }
            catch(Exception e){
                
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        AssignLecturers.loadLectures();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        jComboBox1.setSelectedIndex(0);
        AssignLecturers.loadLectures();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        JOptionPane.showMessageDialog(rootPane, "<html>"
            + "<h3>Assign lecturers</h3>"
            + "Click <span color=red>Assign</span> to add access to this course unit.<br>"
            + "By clicking <span color=red>Unassign</span> lecturers can be removed from course unit.<br>"
            + "</html>","SmartUni Help",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jLabel6MouseClicked

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
            java.util.logging.Logger.getLogger(AssignLecturers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AssignLecturers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AssignLecturers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AssignLecturers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AssignLecturers("1").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
