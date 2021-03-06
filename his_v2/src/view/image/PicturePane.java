/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.image;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author patrick
 */
public class PicturePane extends javax.swing.JPanel {

    private Image img;
    private boolean clear;

    public PicturePane() {
        initComponents();
    }

    @Override
    public void paint(Graphics g) {
        if (this.clear) {
            g.clearRect(0, 0, this.getWidth(), this.getHeight());
        }
        if (img != null) {
            g.drawImage(img, 0, 0, null);
        } else {
            g.setColor(new Color(51, 51, 51));
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
    }

    /**
     * Set picture
     *
     * @param img
     */
    public void setPicture(Image img, boolean clear) {
        this.clear = clear;
        if (img != null) {
            this.img = img.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT);
            repaint();
        } else {
            this.img = null;
            repaint();
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
