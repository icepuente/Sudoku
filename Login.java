package sudoku;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;

public class Login extends JFrame {

    public Login() {
        initComponents();
        setComboBox();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(SudokuTheme.BG_DARK);

        // Main card panel
        JPanel card = SudokuTheme.createCardPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        // Title
        JLabel titleLabel = new JLabel("Sudoku");
        titleLabel.setFont(SudokuTheme.TITLE_FONT);
        titleLabel.setForeground(SudokuTheme.PRIMARY_LIGHT);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Select your profile to continue");
        subtitleLabel.setFont(SudokuTheme.SMALL_FONT);
        subtitleLabel.setForeground(SudokuTheme.TEXT_MUTED);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Username section
        jLabel1 = new JLabel("User Name");
        jLabel1.setFont(SudokuTheme.BODY_BOLD);
        jLabel1.setForeground(SudokuTheme.TEXT_SECONDARY);
        jLabel1.setAlignmentX(Component.LEFT_ALIGNMENT);

        jComboBox1 = new JComboBox<>();
        jComboBox1.setFont(SudokuTheme.BODY_FONT);
        jComboBox1.setBackground(SudokuTheme.BG_INPUT);
        jComboBox1.setForeground(SudokuTheme.TEXT_PRIMARY);
        jComboBox1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        jComboBox1.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel comboWrapper = new JPanel();
        comboWrapper.setOpaque(false);
        comboWrapper.setLayout(new BoxLayout(comboWrapper, BoxLayout.Y_AXIS));
        comboWrapper.setAlignmentX(Component.CENTER_ALIGNMENT);
        comboWrapper.setMaximumSize(new Dimension(280, 70));
        comboWrapper.add(jLabel1);
        comboWrapper.add(Box.createVerticalStrut(6));
        comboWrapper.add(jComboBox1);

        // Buttons
        jButton1 = SudokuTheme.createStyledButton("Login");
        jButton1.setAlignmentX(Component.CENTER_ALIGNMENT);
        jButton1.setMaximumSize(new Dimension(280, 42));
        jButton1.addActionListener(e -> loginAction());

        jButton2 = SudokuTheme.createSecondaryButton("Create New User");
        jButton2.setAlignmentX(Component.CENTER_ALIGNMENT);
        jButton2.setMaximumSize(new Dimension(280, 42));
        jButton2.addActionListener(e -> newUserAction());

        jButton4 = SudokuTheme.createSecondaryButton("Delete User");
        jButton4.setAlignmentX(Component.CENTER_ALIGNMENT);
        jButton4.setMaximumSize(new Dimension(280, 42));
        jButton4.addActionListener(e -> deleteAction());

        jButton3 = SudokuTheme.createSecondaryButton("Cancel");
        jButton3.setAlignmentX(Component.CENTER_ALIGNMENT);
        jButton3.setMaximumSize(new Dimension(280, 42));
        jButton3.addActionListener(e -> dispose());

        // Assemble
        card.add(Box.createVerticalStrut(8));
        card.add(titleLabel);
        card.add(Box.createVerticalStrut(4));
        card.add(subtitleLabel);
        card.add(Box.createVerticalStrut(24));
        card.add(comboWrapper);
        card.add(Box.createVerticalStrut(20));
        card.add(jButton1);
        card.add(Box.createVerticalStrut(10));
        card.add(jButton2);
        card.add(Box.createVerticalStrut(8));
        card.add(jButton4);
        card.add(Box.createVerticalStrut(8));
        card.add(jButton3);
        card.add(Box.createVerticalStrut(8));

        // Wrap card in a centered panel
        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(SudokuTheme.BG_DARK);
        wrapper.setBorder(new EmptyBorder(30, 40, 30, 40));
        wrapper.add(card);

        getContentPane().add(wrapper);
        pack();
    }

    private void newUserAction() {
        NewLogin newLogin = new NewLogin(this, true);
        newLogin.setLocationRelativeTo(null);
        newLogin.setTitle("Create Profile");
        newLogin.setVisible(true);
        setComboBox();
    }

    private void loginAction() {
        String s = (String) jComboBox1.getSelectedItem();
        if (s != null && !s.isEmpty()) {
            for (Save save : saves) {
                if (s.equals(save.getName())) {
                    File saveFolder = new File(Tools.getDocumentsPath() + "/Sudoku/" + save.getName());
                    save.setSaveFile(saveFolder);
                    break;
                }
            }
            MainMenu m = new MainMenu(s);
            m.setLocationRelativeTo(null);
            m.setVisible(true);
            m.setTitle(s);
            setVisible(false);
            dispose();
        }
    }

    private void deleteAction() {
        String s = (String) jComboBox1.getSelectedItem();
        if (s != null && !s.isEmpty()) {
            int removeIndex = jComboBox1.getSelectedIndex();
            Save toDelete = saves.get(removeIndex);
            Path removePath = toDelete.getSaveFile().toPath();
            System.out.println("DEBUG: REMOVING FILE AT PATH " + removePath.toString());
            jComboBox1.removeItemAt(removeIndex);
            saves.remove(removeIndex);
            Save.deleteSave(removePath);
        }
    }

    private void setComboBox() {
        saves = Save.loadSaves();
        jComboBox1.removeAllItems();
        for (Save save : saves) {
            jComboBox1.addItem(save.getName());
        }
    }

    public static void main(String args[]) {
        SudokuTheme.applyTheme();
        EventQueue.invokeLater(() -> new Login().setVisible(true));
    }

    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JButton jButton4;
    private JComboBox<String> jComboBox1;
    private JLabel jLabel1;
    private ArrayList<Save> saves;
}
