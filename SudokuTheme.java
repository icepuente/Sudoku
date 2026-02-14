package sudoku;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

public class SudokuTheme {

    // Primary palette
    public static final Color PRIMARY = new Color(79, 70, 229);       // Indigo
    public static final Color PRIMARY_LIGHT = new Color(129, 120, 248);
    public static final Color PRIMARY_DARK = new Color(55, 48, 163);

    // Accent
    public static final Color ACCENT = new Color(16, 185, 129);       // Emerald
    public static final Color ACCENT_LIGHT = new Color(52, 211, 153);

    // Neutrals
    public static final Color BG_DARK = new Color(30, 30, 46);        // Dark background
    public static final Color BG_MEDIUM = new Color(45, 45, 65);      // Card background
    public static final Color BG_LIGHT = new Color(55, 55, 78);       // Raised surface
    public static final Color BG_INPUT = new Color(40, 40, 58);       // Input fields

    public static final Color TEXT_PRIMARY = new Color(226, 226, 240);
    public static final Color TEXT_SECONDARY = new Color(160, 160, 185);
    public static final Color TEXT_MUTED = new Color(120, 120, 150);

    // Board-specific colors
    public static final Color CELL_BG = new Color(38, 38, 56);
    public static final Color CELL_SELECTED = new Color(79, 70, 229, 60);
    public static final Color CELL_HIGHLIGHT = new Color(79, 70, 229, 35);
    public static final Color CELL_BORDER = new Color(60, 60, 85);
    public static final Color BOX_BORDER = new Color(90, 90, 120);

    public static final Color GIVEN_NUMBER = new Color(165, 180, 252);  // Indigo-light for given numbers
    public static final Color GUESS_NUMBER = new Color(226, 226, 240);  // White for guesses
    public static final Color DOODLE_COLOR = new Color(251, 146, 60);   // Orange for doodles
    public static final Color ERROR_COLOR = new Color(239, 68, 68);     // Red for errors
    public static final Color SUCCESS_COLOR = new Color(34, 197, 94);   // Green for success

    // Fonts
    public static final Font TITLE_FONT = new Font("SansSerif", Font.BOLD, 42);
    public static final Font HEADING_FONT = new Font("SansSerif", Font.BOLD, 18);
    public static final Font BODY_FONT = new Font("SansSerif", Font.PLAIN, 14);
    public static final Font BODY_BOLD = new Font("SansSerif", Font.BOLD, 14);
    public static final Font SMALL_FONT = new Font("SansSerif", Font.PLAIN, 12);
    public static final Font CELL_FONT = new Font("SansSerif", Font.BOLD, 22);
    public static final Font CELL_DOODLE_FONT = new Font("SansSerif", Font.BOLD, 11);
    public static final Font BUTTON_FONT = new Font("SansSerif", Font.BOLD, 13);
    public static final Font TIMER_FONT = new Font("SansSerif", Font.BOLD, 20);
    public static final Font VERSION_FONT = new Font("SansSerif", Font.PLAIN, 11);
    public static final Font NUMPAD_FONT = new Font("SansSerif", Font.BOLD, 16);

    public static void applyTheme() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        UIManager.put("Panel.background", BG_DARK);
        UIManager.put("Panel.foreground", TEXT_PRIMARY);

        UIManager.put("Label.foreground", TEXT_PRIMARY);
        UIManager.put("Label.font", BODY_FONT);

        UIManager.put("Button.background", BG_LIGHT);
        UIManager.put("Button.foreground", TEXT_PRIMARY);
        UIManager.put("Button.font", BUTTON_FONT);
        UIManager.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0)));
        UIManager.put("Button.select", PRIMARY_DARK);

        UIManager.put("ToggleButton.background", BG_LIGHT);
        UIManager.put("ToggleButton.foreground", TEXT_PRIMARY);
        UIManager.put("ToggleButton.font", BUTTON_FONT);
        UIManager.put("ToggleButton.select", PRIMARY);

        UIManager.put("TextField.background", BG_INPUT);
        UIManager.put("TextField.foreground", TEXT_PRIMARY);
        UIManager.put("TextField.caretForeground", TEXT_PRIMARY);
        UIManager.put("TextField.font", BODY_FONT);
        UIManager.put("TextField.inactiveBackground", BG_INPUT);

        UIManager.put("TextPane.background", BG_INPUT);
        UIManager.put("TextPane.foreground", TEXT_PRIMARY);
        UIManager.put("TextPane.caretForeground", TEXT_PRIMARY);
        UIManager.put("TextPane.font", BODY_FONT);

        UIManager.put("TextArea.background", BG_MEDIUM);
        UIManager.put("TextArea.foreground", TEXT_SECONDARY);
        UIManager.put("TextArea.font", SMALL_FONT);
        UIManager.put("TextArea.caretForeground", TEXT_PRIMARY);

        UIManager.put("ComboBox.background", BG_INPUT);
        UIManager.put("ComboBox.foreground", TEXT_PRIMARY);
        UIManager.put("ComboBox.selectionBackground", PRIMARY);
        UIManager.put("ComboBox.selectionForeground", Color.WHITE);
        UIManager.put("ComboBox.font", BODY_FONT);

        UIManager.put("ScrollPane.background", BG_DARK);
        UIManager.put("ScrollPane.border", BorderFactory.createEmptyBorder());
        UIManager.put("Viewport.background", BG_DARK);

        UIManager.put("Table.background", BG_MEDIUM);
        UIManager.put("Table.foreground", TEXT_PRIMARY);
        UIManager.put("Table.selectionBackground", PRIMARY);
        UIManager.put("Table.selectionForeground", Color.WHITE);
        UIManager.put("Table.gridColor", CELL_BORDER);
        UIManager.put("Table.font", BODY_FONT);
        UIManager.put("TableHeader.background", BG_LIGHT);
        UIManager.put("TableHeader.foreground", TEXT_PRIMARY);
        UIManager.put("TableHeader.font", BODY_BOLD);

        UIManager.put("TabbedPane.background", BG_DARK);
        UIManager.put("TabbedPane.foreground", TEXT_SECONDARY);
        UIManager.put("TabbedPane.selected", BG_MEDIUM);
        UIManager.put("TabbedPane.contentAreaColor", BG_MEDIUM);
        UIManager.put("TabbedPane.font", BODY_BOLD);
        UIManager.put("TabbedPane.focus", new Color(0, 0, 0, 0));
        UIManager.put("TabbedPane.selectedForeground", TEXT_PRIMARY);

        UIManager.put("OptionPane.background", BG_MEDIUM);
        UIManager.put("OptionPane.foreground", TEXT_PRIMARY);
        UIManager.put("OptionPane.messageForeground", TEXT_PRIMARY);
        UIManager.put("OptionPane.messageFont", BODY_FONT);
        UIManager.put("OptionPane.buttonFont", BUTTON_FONT);

        UIManager.put("Separator.foreground", CELL_BORDER);
        UIManager.put("Separator.background", BG_DARK);
    }

    public static JButton createStyledButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isPressed()) {
                    g2.setColor(PRIMARY_DARK);
                } else if (getModel().isRollover()) {
                    g2.setColor(PRIMARY_LIGHT);
                } else {
                    g2.setColor(PRIMARY);
                }
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                g2.dispose();

                FontMetrics fm = g.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                g.setColor(Color.WHITE);
                g.setFont(getFont());
                g.drawString(getText(), x, y);
            }

            @Override
            protected void paintBorder(Graphics g) {
                // No border painting
            }
        };
        button.setFont(BUTTON_FONT);
        button.setForeground(Color.WHITE);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(200, 42));
        return button;
    }

    public static JButton createSecondaryButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isPressed()) {
                    g2.setColor(BG_INPUT);
                } else if (getModel().isRollover()) {
                    g2.setColor(BG_LIGHT);
                } else {
                    g2.setColor(BG_MEDIUM);
                }
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                g2.setColor(CELL_BORDER);
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 12, 12);
                g2.dispose();

                FontMetrics fm = g.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                g.setColor(TEXT_PRIMARY);
                g.setFont(getFont());
                g.drawString(getText(), x, y);
            }

            @Override
            protected void paintBorder(Graphics g) {
            }
        };
        button.setFont(BUTTON_FONT);
        button.setForeground(TEXT_PRIMARY);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(200, 42));
        return button;
    }

    public static JToggleButton createStyledToggleButton(String text) {
        JToggleButton button = new JToggleButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (isSelected()) {
                    g2.setColor(ACCENT);
                } else if (getModel().isRollover()) {
                    g2.setColor(BG_LIGHT);
                } else {
                    g2.setColor(BG_MEDIUM);
                }
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                if (!isSelected()) {
                    g2.setColor(CELL_BORDER);
                    g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 12, 12);
                }
                g2.dispose();

                FontMetrics fm = g.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                g.setColor(Color.WHITE);
                g.setFont(getFont());
                g.drawString(getText(), x, y);
            }

            @Override
            protected void paintBorder(Graphics g) {
            }
        };
        button.setFont(BUTTON_FONT);
        button.setForeground(Color.WHITE);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    public static JPanel createCardPanel() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(BG_MEDIUM);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.dispose();
            }
        };
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(24, 24, 24, 24));
        return panel;
    }

    public static Border createCellBorder() {
        return BorderFactory.createLineBorder(CELL_BORDER, 1);
    }

    public static Border createBoxBorder() {
        return BorderFactory.createLineBorder(BOX_BORDER, 2);
    }

    public static Border createErrorBorder() {
        return BorderFactory.createLineBorder(ERROR_COLOR, 2);
    }
}
