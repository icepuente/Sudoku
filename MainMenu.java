/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Alec
 */
public class MainMenu extends javax.swing.JFrame {

    /**
     * Creates new form MainMenu
     *
     * @param s
     */
    public MainMenu(String s) {
        this.setTitle(s);
        initComponents();
        user = s;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButtonContinue = new javax.swing.JButton();
        jButtonNewGame = new javax.swing.JButton();
        jButtonLeaderboard = new javax.swing.JButton();
        jButtonLoginReturn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jButtonContinue.setText("Continue Previous Game");
        jButtonContinue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jButtonContinueActionPerformed(evt);
                } catch (IOException ex) {
                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        jButtonNewGame.setText("Start New Game");
        jButtonNewGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jButtonNewGameActionPerformed(evt);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        jButtonLeaderboard.setText("View Leaderboard");
        jButtonLeaderboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLeaderboardActionPerformed(evt);
            }
        });

        jButtonLoginReturn.setText("Return to Login Screen");
        jButtonLoginReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoginReturnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel1.setText("Version: 1.00");

        jLabel2.setFont(new java.awt.Font("Tempus Sans ITC", 1, 48)); // NOI18N
        jLabel2.setText(" Sudoku");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(64, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jButtonContinue, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                                                        .addComponent(jButtonNewGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jButtonLeaderboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jButtonLoginReturn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonContinue, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonNewGame, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonLeaderboard, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonLoginReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void jButtonContinueActionPerformed(java.awt.event.ActionEvent evt) throws IOException {
        Object[] options = {"Test", "Easy", "Medium", "Hard", "Devilish"};
        Difficulty[] a = {Difficulty.TEST, Difficulty.EASY, Difficulty.MEDIUM, Difficulty.HARD, Difficulty.DEVILISH};
        Object selected = JOptionPane.showInputDialog(null, "Choose Difficulty", "Load Game",
                JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);
        if (selected != null) {
            Difficulty d = null;
            for (int i = 0; i < options.length; i++) {
                if (options[i].equals(selected))
                    d = a[i];
            }
            if (d != null) {
                System.out.println("DEBUG: NEW GAME DIFFICULTY " + d.toString());
                if (Save.loadGame(user, d)) {
                    setVisible(false);
                    this.dispose();
                } else
                    JOptionPane.showMessageDialog(this, "No Save File Found!");

            }
        }
    }

    private void jButtonNewGameActionPerformed(java.awt.event.ActionEvent evt) throws FileNotFoundException, UnsupportedEncodingException {
        // TODO add your handling code here:
        Object[] options = {"Test", "Easy", "Medium", "Hard", "Devilish"};
        Difficulty[] a = {Difficulty.TEST, Difficulty.EASY, Difficulty.MEDIUM, Difficulty.HARD, Difficulty.DEVILISH, Difficulty.TEST};
        Object selected = JOptionPane.showInputDialog(null, "Choose Difficulty", "New Game",
                JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);
        if (selected != null) {
            Difficulty d = null;
            for (int i = 0; i < options.length; i++) {
                if (options[i].equals(selected))
                    d = a[i];
            }
            if (d != null && d != Difficulty.TEST) {
                System.out.println("DEBUG: NEW GAME DIFFICULTY " + d.toString());
                View = new GameView(user, d);
                // This line is to test Random Board Solution Generation
                Tools.generateBoard(user, d);
                View.getBoard().PopulateBoard(new File(Tools.getDocumentsPath() + "/Sudoku/" + user + "/" + d.toString() + ".txt"));
                setVisible(false);
                dispose();
            } else if (d == Difficulty.TEST) {
                System.out.println("DEBUG: NEW GAME DIFFICULTY " + d.toString());
                View = new GameView(user, d);
                Save.initialSave(d, user);
                View.getBoard().PopulateBoard(new File(Tools.getDocumentsPath() + "/Sudoku/" + user + "/" + d.toString() + ".txt"));
                setVisible(false);
                dispose();
            }
        }
    }

    private void jButtonLeaderboardActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        LeaderBoard leaderBoard = new LeaderBoard(user);
        leaderBoard.setLocationRelativeTo(null);
        leaderBoard.setVisible(true);
        leaderBoard.setTitle("Leaderboard");
        this.setVisible(false);
    }

    private void jButtonLoginReturnActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        Login login = new Login();
        login.setLocationRelativeTo(null);
        login.setTitle("Sudoku Login");
        login.setVisible(true);
        setVisible(false);
        dispose();
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
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainMenu(user).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButtonContinue;
    private javax.swing.JButton jButtonLeaderboard;
    private javax.swing.JButton jButtonLoginReturn;
    private javax.swing.JButton jButtonNewGame;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private static String user;
    private static GameView View;

    public static GameView getView() {
        return View;
    }

    public static void setView(GameView View) {
        MainMenu.View = View;
    }
    // End of variables declaration                   
}
