/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBSProj;

import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Aswath Vinayak K
 */
public class StudentTimeTable extends javax.swing.JFrame {

    /**
     * Creates new form AdminStudList
     */
    int id;
    public StudentTimeTable(int sid) {
        
        initComponents();
        jTable1.setRowHeight(37);
        id=sid;
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        database d=new database();
        List <database.courses> list=d.studCoursesTT(id, 2021);
        for(int i=0;i<list.size();i++)
        {
            String day=list.get(i).days;
            //System.out.println(" "+day);
            if(day.equals("MWF"))
            {
                String time=list.get(i).st_time;
                int timetemp=Integer.parseInt(time);
                switch(timetemp)
                {
                    case 8: model.setValueAt(list.get(i).c_name, 0, 1);
                            model.setValueAt(list.get(i).c_name, 0, 3);
                            model.setValueAt(list.get(i).c_name, 0, 5);
                            break;
                    case 9: model.setValueAt(list.get(i).c_name, 1, 1);
                            model.setValueAt(list.get(i).c_name, 1, 3);
                            model.setValueAt(list.get(i).c_name, 1, 5);
                            break;
                    case 10: model.setValueAt(list.get(i).c_name, 2, 1);
                            model.setValueAt(list.get(i).c_name, 2, 3);
                            model.setValueAt(list.get(i).c_name, 2, 5);
                            break;
                    case 11: model.setValueAt(list.get(i).c_name, 3, 1);
                            model.setValueAt(list.get(i).c_name, 3, 3);
                            model.setValueAt(list.get(i).c_name, 3, 5);
                            break;
                    case 12: model.setValueAt(list.get(i).c_name, 4, 1);
                            model.setValueAt(list.get(i).c_name, 4, 3);
                            model.setValueAt(list.get(i).c_name, 4, 5);
                            break;
                    case 1: model.setValueAt(list.get(i).c_name, 5, 1);
                            model.setValueAt(list.get(i).c_name, 5, 3);
                            model.setValueAt(list.get(i).c_name, 5, 5);
                            break;
                    case 2: model.setValueAt(list.get(i).c_name, 6, 1);
                            model.setValueAt(list.get(i).c_name, 6, 3);
                            model.setValueAt(list.get(i).c_name, 6, 5);
                            break;
                    case 3: model.setValueAt(list.get(i).c_name, 7, 1);
                            model.setValueAt(list.get(i).c_name, 7, 3);
                            model.setValueAt(list.get(i).c_name, 7, 5);
                            break;
                    case 4: model.setValueAt(list.get(i).c_name, 8, 1);
                            model.setValueAt(list.get(i).c_name, 8, 3);
                            model.setValueAt(list.get(i).c_name, 8, 5);
                            break;
                }
                
            }
            else
            {
                String time=list.get(i).st_time;
                int timetemp=Integer.parseInt(time);
                switch(timetemp)
                {
                    case 8: model.setValueAt(list.get(i).c_name, 0, 2);
                            model.setValueAt(list.get(i).c_name, 0, 4);
                            model.setValueAt(list.get(i).c_name, 0, 6);
                            break;
                    case 9: model.setValueAt(list.get(i).c_name, 1, 2);
                            model.setValueAt(list.get(i).c_name, 1, 4);
                            model.setValueAt(list.get(i).c_name, 1, 6);
                            break;
                    case 10: model.setValueAt(list.get(i).c_name, 2, 2);
                            model.setValueAt(list.get(i).c_name, 2, 4);
                            model.setValueAt(list.get(i).c_name, 2, 6);
                            break;
                    case 11: model.setValueAt(list.get(i).c_name, 3, 2);
                            model.setValueAt(list.get(i).c_name, 3, 4);
                            model.setValueAt(list.get(i).c_name, 3, 6);
                            break;
                    case 12: model.setValueAt(list.get(i).c_name, 4, 2);
                            model.setValueAt(list.get(i).c_name, 4, 4);
                            model.setValueAt(list.get(i).c_name, 4, 6);
                            break;
                    case 1: model.setValueAt(list.get(i).c_name, 5, 2);
                            model.setValueAt(list.get(i).c_name, 5, 4);
                            model.setValueAt(list.get(i).c_name, 5, 6);
                            break;
                    case 2: model.setValueAt(list.get(i).c_name, 6, 2);
                            model.setValueAt(list.get(i).c_name, 6, 4);
                            model.setValueAt(list.get(i).c_name, 6, 6);
                            break;
                    case 3: model.setValueAt(list.get(i).c_name, 7, 2);
                            model.setValueAt(list.get(i).c_name, 7, 4);
                            model.setValueAt(list.get(i).c_name, 7, 6);
                            break;
                    case 4: model.setValueAt(list.get(i).c_name, 8, 2);
                            model.setValueAt(list.get(i).c_name, 8, 4);
                            model.setValueAt(list.get(i).c_name, 8, 6);
                            break;
                }
            }
            
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jTable1.setBackground(new java.awt.Color(102, 0, 102));
        jTable1.setForeground(new java.awt.Color(255, 255, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"8-9", null, null, null, null, null, null},
                {"9-10", null, null, null, null, null, null},
                {"10-11", null, null, null, null, null, null},
                {"11-12", null, null, null, null, null, null},
                {"12-1", null, null, null, null, null, null},
                {"1-2", null, null, null, null, null, null},
                {"2-3", null, null, null, null, null, null},
                {"3-4", null, null, null, null, null, null},
                {"4-5", null, null, null, null, null, null}
            },
            new String [] {
                "Time", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Back");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 667, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(133, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_jButton1MouseClicked

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
            java.util.logging.Logger.getLogger(StudentTimeTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentTimeTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentTimeTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentTimeTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentTimeTable(0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
