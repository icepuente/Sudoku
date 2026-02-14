package sudoku;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class NewLogin extends JDialog {

    public NewLogin(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    private void initComponents() {
        setResizable(false);
        getContentPane().setBackground(SudokuTheme.BG_DARK);

        JPanel card = SudokuTheme.createCardPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JLabel heading = new JLabel("New Profile");
        heading.setFont(SudokuTheme.HEADING_FONT);
        heading.setForeground(SudokuTheme.TEXT_PRIMARY);
        heading.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel("Enter Name");
        nameLabel.setFont(SudokuTheme.BODY_BOLD);
        nameLabel.setForeground(SudokuTheme.TEXT_SECONDARY);
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        jTextFieldName = new JTextField(20);
        jTextFieldName.setFont(SudokuTheme.BODY_FONT);
        jTextFieldName.setBackground(SudokuTheme.BG_INPUT);
        jTextFieldName.setForeground(SudokuTheme.TEXT_PRIMARY);
        jTextFieldName.setCaretColor(SudokuTheme.TEXT_PRIMARY);
        jTextFieldName.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(SudokuTheme.CELL_BORDER, 1),
                new EmptyBorder(6, 10, 6, 10)));
        jTextFieldName.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        jTextFieldName.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel fieldWrapper = new JPanel();
        fieldWrapper.setOpaque(false);
        fieldWrapper.setLayout(new BoxLayout(fieldWrapper, BoxLayout.Y_AXIS));
        fieldWrapper.setAlignmentX(Component.CENTER_ALIGNMENT);
        fieldWrapper.setMaximumSize(new Dimension(240, 65));
        fieldWrapper.add(nameLabel);
        fieldWrapper.add(Box.createVerticalStrut(6));
        fieldWrapper.add(jTextFieldName);

        jButtonCreate = SudokuTheme.createStyledButton("Create");
        jButtonCreate.setAlignmentX(Component.CENTER_ALIGNMENT);
        jButtonCreate.setMaximumSize(new Dimension(240, 42));
        jButtonCreate.addActionListener(e -> createAction());

        jButtonCancel = SudokuTheme.createSecondaryButton("Cancel");
        jButtonCancel.setAlignmentX(Component.CENTER_ALIGNMENT);
        jButtonCancel.setMaximumSize(new Dimension(240, 42));
        jButtonCancel.addActionListener(e -> {
            setVisible(false);
            dispose();
        });

        card.add(heading);
        card.add(Box.createVerticalStrut(20));
        card.add(fieldWrapper);
        card.add(Box.createVerticalStrut(18));
        card.add(jButtonCreate);
        card.add(Box.createVerticalStrut(8));
        card.add(jButtonCancel);

        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(SudokuTheme.BG_DARK);
        wrapper.setBorder(new EmptyBorder(20, 30, 20, 30));
        wrapper.add(card);

        getContentPane().add(wrapper);
        pack();
    }

    private void createAction() {
        String input = jTextFieldName.getText();
        if (input != null && !input.trim().isEmpty()) {
            Save.createSave(input.trim(), Difficulty.EASY);
            setVisible(false);
            dispose();
        }
    }

    public static void main(String args[]) {
        SudokuTheme.applyTheme();
        EventQueue.invokeLater(() -> {
            NewLogin dialog = new NewLogin(new Frame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    private JButton jButtonCancel;
    private JButton jButtonCreate;
    private JTextField jTextFieldName;
}
