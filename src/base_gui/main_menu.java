/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base_gui;

import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.applet.AudioClip;



/**
 *
 * @author exor_p
 */
public class main_menu extends javax.swing.JFrame {

    /**
     * Creates new form main_menu
     */
    public boolean file_selected;
    public boolean music;
    String m_path;
    private AudioClip sound;
    

    
    public void play_music() throws FileNotFoundException, IOException{
        // play the audio clip with the audioplayer class
        sound.play();
        
    }
    public void stop_music() throws FileNotFoundException, IOException{
        // play the audio clip with the audioplayer class
        sound.stop();
    }
    
    public main_menu() throws FileNotFoundException, IOException {
        file_selected = false;
        music = false;
        //String path = System.getProperty("user.dir");
        //path += "/src/music/blade.mp3";
        //m_path = path;
        //System.out.println(path);
        ///src/music
        sound = java.applet.Applet.newAudioClip(getClass().getResource("/music/blade.wav"));
        
        initComponents();
        pasarcaixa.setVisible(false);
        llistats.setVisible(false);
        file.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT files", "txt");
        FileNameExtensionFilter filter2 = new FileNameExtensionFilter("CSV files", "csv");
        file.addChoosableFileFilter(filter);
        file.addChoosableFileFilter(filter2);
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        file = new javax.swing.JFileChooser();
        pasarcaixa = new javax.swing.JButton();
        llistats = new javax.swing.JButton();
        sortir = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("DialogInput", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(41, 197, 188));
        jLabel1.setText("Menú Principal");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 320, 70));

        file.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileActionPerformed(evt);
            }
        });
        getContentPane().add(file, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        pasarcaixa.setBackground(new java.awt.Color(14, 133, 251));
        pasarcaixa.setFont(new java.awt.Font("DialogInput", 0, 18)); // NOI18N
        pasarcaixa.setForeground(new java.awt.Color(16, 243, 245));
        pasarcaixa.setText("Pasar per caixa");
        pasarcaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasarcaixaActionPerformed(evt);
            }
        });
        getContentPane().add(pasarcaixa, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 270, -1));

        llistats.setBackground(new java.awt.Color(14, 133, 251));
        llistats.setFont(new java.awt.Font("DialogInput", 0, 18)); // NOI18N
        llistats.setForeground(new java.awt.Color(16, 243, 245));
        llistats.setText("Generar llistats");
        llistats.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                llistatsActionPerformed(evt);
            }
        });
        getContentPane().add(llistats, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, 270, -1));

        sortir.setBackground(new java.awt.Color(14, 133, 251));
        sortir.setFont(new java.awt.Font("DialogInput", 0, 18)); // NOI18N
        sortir.setForeground(new java.awt.Color(16, 243, 245));
        sortir.setText("Sortir");
        sortir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortirActionPerformed(evt);
            }
        });
        getContentPane().add(sortir, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 470, 270, -1));

        jRadioButton1.setFont(new java.awt.Font("NanumSquare Bold", 1, 15)); // NOI18N
        jRadioButton1.setForeground(new java.awt.Color(0, 255, 252));
        jRadioButton1.setText("Radio");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 0, -1, -1));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/menu.jpg"))); // NOI18N
        background.setText("jLabel1");
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pasarcaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasarcaixaActionPerformed

    }//GEN-LAST:event_pasarcaixaActionPerformed

    private void llistatsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_llistatsActionPerformed

    }//GEN-LAST:event_llistatsActionPerformed

    private void sortirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_sortirActionPerformed

    private void fileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileActionPerformed
        // TODO validar fitxer and call bd store
        File selectedF = file.getSelectedFile();
        pasarcaixa.setVisible(true);
        llistats.setVisible(true);
        file.setVisible(false);
    }//GEN-LAST:event_fileActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
        if (!music){
            try {
                play_music();
            } catch (IOException ex) {
                Logger.getLogger(main_menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            try {
                stop_music();
            } catch (IOException ex) {
                Logger.getLogger(main_menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        music = !music;
        
    }//GEN-LAST:event_jRadioButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(main_menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main_menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main_menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main_menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new main_menu().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(main_menu.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JFileChooser file;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JButton llistats;
    private javax.swing.JButton pasarcaixa;
    private javax.swing.JButton sortir;
    // End of variables declaration//GEN-END:variables
}
