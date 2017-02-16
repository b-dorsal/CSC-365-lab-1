package pkg365ass1;
//Brian Dorsey 2016

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class compareUI extends javax.swing.JFrame {

    public compareUI() {
        initComponents();
        lblSearching.setText("Loading...");
        firstLoad();
        lblSearching.setText("Ready.");
        lblResult.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGo = new javax.swing.JButton();
        tbxSearch = new javax.swing.JTextField();
        lblSearching = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblResult = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        btnGo.setText("Go");
        btnGo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoActionPerformed(evt);
            }
        });

        jLabel1.setText("Best URL:");

        jLabel2.setText("URL to compare:");

        lblResult.setText("Best Result lbl");
        lblResult.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblResultMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblResult, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE))
                            .addComponent(tbxSearch))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSearching, javax.swing.GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE)
                        .addGap(6, 6, 6)
                        .addComponent(btnGo)
                        .addGap(15, 15, 15))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblResult))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tbxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGo)
                    .addComponent(lblSearching))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    static Set<dataSource> sourceSet = new HashSet<>();
    private void btnGoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoActionPerformed
        lblSearching.setText("Searching...");
        String searchTerm = tbxSearch.getText();

        Iterator iterator = sourceSet.iterator();
        dataSource best;
        String bestURL = "";
        int lastS = -1;

        while (iterator.hasNext()) {
            dataSource element = (dataSource) iterator.next();
            try {
                int tempS = element.compareSource(searchTerm);

                if (lastS == -1) {
                    best = element;
                    bestURL = best.getURL();
                    lastS = tempS;
                } else if (tempS > lastS) {
                    best = element;
                    bestURL = best.getURL();
                    lastS = tempS;
                }
            } catch (Exception ex) {
                System.out.println("Error: Bad URL");
                lblSearching.setText("Error: bad URL!");
            }
        }
        lblResult.setText(bestURL);

        lblSearching.setText("Done.");
    }//GEN-LAST:event_btnGoActionPerformed

    private void lblResultMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblResultMouseClicked
        try {
            Desktop.getDesktop().browse(new URI(lblResult.getText()));
        } catch (Exception ex) {
            System.out.println("Bad URL.");
        }
    }//GEN-LAST:event_lblResultMouseClicked

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
            java.util.logging.Logger.getLogger(compareUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(compareUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(compareUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(compareUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                firstLoad();
                new compareUI().setVisible(true);
            }
        });

    }

    private static void firstLoad() {//Loads each source URL data at launch to reduce search times.

        try {
            System.out.print("Loading...");
            dataSource source0 = new dataSource("https://en.wikipedia.org/wiki/Computer_mouse");
            dataSource source1 = new dataSource("https://en.wikipedia.org/wiki/Keyboard");
            dataSource source2 = new dataSource("https://en.wikipedia.org/wiki/Game_controller");
            dataSource source3 = new dataSource("https://en.wikipedia.org/wiki/Webcam");
            dataSource source4 = new dataSource("https://en.wikipedia.org/wiki/Motherboard");
            dataSource source5 = new dataSource("https://en.wikipedia.org/wiki/Computer_monitor");
            dataSource source6 = new dataSource("https://en.wikipedia.org/wiki/Video_card");
            dataSource source7 = new dataSource("https://en.wikipedia.org/wiki/Random-access_memory");
            dataSource source8 = new dataSource("https://en.wikipedia.org/wiki/Central_processing_unit");
            dataSource source9 = new dataSource("https://en.wikipedia.org/wiki/Laptop");

            sourceSet.add(source0);
            sourceSet.add(source1);
            sourceSet.add(source2);
            sourceSet.add(source3);
            sourceSet.add(source4);
            sourceSet.add(source5);
            sourceSet.add(source6);
            sourceSet.add(source7);
            sourceSet.add(source8);
            sourceSet.add(source9);
            System.out.println("Done.");
        } catch (Exception ex) {
            Logger.getLogger(compareUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblResult;
    private javax.swing.JLabel lblSearching;
    private javax.swing.JTextField tbxSearch;
    // End of variables declaration//GEN-END:variables
}
