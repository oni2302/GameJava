/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main;

import data.Connect;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import utilz.FontHelper;
import utilz.LoadSave;
import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author oni
 */
public class HighScore extends javax.swing.JFrame {

    /**
     * Creates new form HighScore
     */
    public HighScore() {
        this.setUndecorated(true);
        this.setFont(FontHelper.PixelFont(30));
//        this.setUndecorated(true);
        initComponents();
        initCustom();
        initTable();
        this.setLocationRelativeTo(null);
    }

    private void initTable() {
        String sql = """
                     SELECT playerId, lvl, score
                     FROM (
                         SELECT playerId, lvl, score,
                                ROW_NUMBER() OVER(PARTITION BY playerId ORDER BY score DESC) as rank_within_player
                         FROM game.high_score
                     ) ranked_scores
                     WHERE rank_within_player <= 10 and playerId = ?
                     order by score desc""";
        PreparedStatement ps;
        try {
            ps = Connect.getConnection().prepareStatement(sql);
            ps.setInt(1,Game.PLAYER_ID);
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Player ID");
            model.addColumn("Level");
            model.addColumn("Score");
            

            // Iterate through the ResultSet and add rows to the model
            while (rs.next()) {
                int playerId = rs.getInt("playerId");
                int level = rs.getInt("lvl");
                int score = rs.getInt("score");

                // Create a Vector to store data for each row
                Vector<Object> row = new Vector<>();
                row.add(playerId);
                row.add(level);
                row.add(score);

                // Add the row to the model
                model.addRow(row);
            }

            // Create a JTable with the populated model
            JTable table = new JTable(model);
            JTableHeader header = table.getTableHeader();
            header.setFont(FontHelper.PixelFont(30));
//            table.setBackground(new java.awt.Color(0, 0, 0, 0));
            table.setFont(FontHelper.PixelFont(35));
            // Create a JScrollPane to contain the table
            JScrollPane scrollPane = new JScrollPane(table);
            tab_pane.addTab("Me", scrollPane);
        } catch (SQLException ex) {
            Logger.getLogger(HighScore.class.getName()).log(Level.SEVERE, null, ex);
        }
        sql = """
                     SELECT playerId, lvl, score
                     FROM (
                         SELECT playerId, lvl, score,
                                ROW_NUMBER() OVER(PARTITION BY playerId ORDER BY score DESC) as rank_within_player
                         FROM game.high_score
                     ) ranked_scores
                     WHERE rank_within_player <= 5
                     order by score desc""";
        try {
            ps = Connect.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Player ID");
            model.addColumn("Level");
            model.addColumn("Score");
            

            // Iterate through the ResultSet and add rows to the model
            while (rs.next()) {
                int playerId = rs.getInt("playerId");
                int level = rs.getInt("lvl");
                int score = rs.getInt("score");

                // Create a Vector to store data for each row
                Vector<Object> row = new Vector<>();
                row.add(playerId);
                row.add(level);
                row.add(score);

                // Add the row to the model
                model.addRow(row);
            }

            // Create a JTable with the populated model
            JTable table = new JTable(model);
            JTableHeader header = table.getTableHeader();
            header.setFont(FontHelper.PixelFont(30));
//            table.setBackground(new java.awt.Color(0, 0, 0, 0));
            table.setFont(FontHelper.PixelFont(35));
            // Create a JScrollPane to contain the table
            JScrollPane scrollPane = new JScrollPane(table);
            tab_pane.addTab("World", scrollPane);
        } catch (SQLException ex) {
            Logger.getLogger(HighScore.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void initCustom() {
        Font f = FontHelper.PixelFont(30);
        text_bxh.setFont(FontHelper.PixelFont(50));
        text_bxh.setForeground(Color.white);
        tab_pane.setFont(f);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    dispose(); // Close the frame
                }
            }
        });
        panel.setSize(this.getWidth(), this.getHeight());
        ImageIcon backgroundImage = LoadSave.GetImageIcon(LoadSave.HIGH_SCORE_BG);

        JLabel background = new JLabel(backgroundImage);
        background.setSize(this.getWidth(), this.getHeight());
        panel.add(background, BorderLayout.CENTER);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        text_bxh = new javax.swing.JLabel();
        tab_pane = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        text_bxh.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        text_bxh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        text_bxh.setText("BANG XEP HANG");
        text_bxh.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tab_pane)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap(113, Short.MAX_VALUE)
                .addComponent(text_bxh)
                .addGap(98, 98, 98))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(text_bxh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tab_pane, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(HighScore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HighScore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HighScore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HighScore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HighScore().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panel;
    private javax.swing.JTabbedPane tab_pane;
    private javax.swing.JLabel text_bxh;
    // End of variables declaration//GEN-END:variables

}
