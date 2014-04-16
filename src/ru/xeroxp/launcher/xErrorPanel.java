package ru.xeroxp.launcher;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

class xErrorPanel extends JDialog {

    public xErrorPanel(Frame parent, String error) {
        super(parent);
        setTitle("������");
        setSize(xSettingsOfTheme.ErrorPanelSize[0], xSettingsOfTheme.ErrorPanelSize[1] + 20);
        setResizable(false);
        setModal(true);
        setLocationRelativeTo(parent);
        JPanel backgroundBg = new BackgroundRegisterPanel();
        backgroundBg.setLayout(null);
        backgroundBg.setBorder(null);
        backgroundBg.setOpaque(false);
        add(backgroundBg);
        InputStream is = xTheme.class.getResourceAsStream("/font/" + xSettingsOfTheme.FontFile1);
        Font arial = null;
        try {
            arial = Font.createFont(0, is);
            arial = arial.deriveFont(0, xSettingsOfTheme.ErrorPanelTextSize);
        } catch (FontFormatException e2) {
            System.out.println("Failed load font");
            System.out.println(e2.getMessage());
        } catch (IOException e2) {
            System.out.println("Failed load font");
            System.out.println(e2.getMessage());
        }
        JLabel errorlabel = new JLabel(error);
        errorlabel.setBounds(0, 0, xSettingsOfTheme.ErrorPanelSize[0], xSettingsOfTheme.ErrorPanelSize[1]);
        errorlabel.setFont(arial);
        errorlabel.setForeground(xSettingsOfTheme.ErrorPanelTextColor);
        errorlabel.setHorizontalAlignment(JLabel.CENTER);
        backgroundBg.add(errorlabel);
    }

    public class BackgroundRegisterPanel extends JPanel {
        private Image img;
        private Image bgImage;

        public BackgroundRegisterPanel() {
            setOpaque(true);
            try {
                bgImage = ImageIO.read(xTheme.class.getResource("/images/" + xSettingsOfTheme.ErrorPanelImage)).getScaledInstance(xSettingsOfTheme.ErrorPanelSize[0], xSettingsOfTheme.ErrorPanelSize[1], 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void update(Graphics g) {
            paint(g);
        }

        public void paintComponent(Graphics g2) {
            int w = getWidth();
            int h = getHeight();
            if ((img == null) || (img.getWidth(null) != w) || (img.getHeight(null) != h)) {
                img = createImage(w, h);

                Graphics g = img.getGraphics();
                for (int x = 0; x <= w / xSettingsOfTheme.ErrorPanelSize[0]; x++) {
                    for (int y = 0; y <= h / xSettingsOfTheme.ErrorPanelSize[1]; y++)
                        g.drawImage(bgImage, x * xSettingsOfTheme.ErrorPanelSize[0], y * xSettingsOfTheme.ErrorPanelSize[1], null);
                }

                g.dispose();
            }
            g2.drawImage(img, 0, 0, w, h, null);
        }
    }
}