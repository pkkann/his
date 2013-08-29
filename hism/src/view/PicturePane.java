/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author pkann
 */
public class PicturePane extends javax.swing.JPanel {

    private Image image;

    public PicturePane() {
        initComponents();
    }

    @Override
    public void paint(Graphics g) {
        if (image != null) {
            g.drawImage(image, 0, 0, null);
        } else {
            try {
                image = ImageIO.read(this.getClass().getResourceAsStream("image.png"));
                g.drawImage(image, 0, 0, null);
            } catch (IOException ex) {
                Logger.getLogger(PicturePane.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setPicture(Image img) {
        if (img != null) {
            image = img.getScaledInstance(150, 170, Image.SCALE_SMOOTH);
        } else {
            image = null;
        }
        repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(java.awt.Color.lightGray);
        setMaximumSize(new java.awt.Dimension(150, 170));
        setMinimumSize(new java.awt.Dimension(150, 170));
        setPreferredSize(new java.awt.Dimension(150, 170));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
