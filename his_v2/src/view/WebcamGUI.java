/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamPicker;
import com.github.sarxos.webcam.WebcamResolution;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import view.message.DialogMessage;

/**
 *
 * @author Patrick
 */
public class WebcamGUI extends JFrame {

    private Thread t;

    private Webcam webcam;
    private WebcamPicker picker;
    private WebcamPanel webPanel;

    private JPanel buttonPanel;
    private JButton cancelButton;
    private JButton captureButton;

    private BufferedImage img;

    public WebcamGUI() {
        init();
    }

    public void start() {
        t = new Thread() {

            @Override
            public void run() {
                initWebcamPanel();
                webPanel.start();
            }
        };
        t.start();
        this.setVisible(true);
    }

    public void stop() {
        webPanel.stop();
        t = null;
        this.dispose();
    }

    public void init() {
        //Layout
        this.setLayout(new BorderLayout());

        //Button panel
        buttonPanel = new JPanel();
        this.add(buttonPanel, BorderLayout.SOUTH);

        //Cancel button
        cancelButton = new JButton("Annuller");
        buttonPanel.add(cancelButton);
        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                stop();
            }
        });

        //Capture button
        captureButton = new JButton("Tag billed");
        buttonPanel.add(captureButton);
        captureButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                img = webcam.getImage();
                stop();
            }
        });

        //Webcam picker
        picker = new WebcamPicker(Webcam.getWebcams());
        this.add(picker, BorderLayout.NORTH);
        picker.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {

            }
        });

        //Pack gui
        pack();

        //JFrame settings
        //setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initWebcamPanel() {
        webcam = picker.getSelectedWebcam();
        webcam.setViewSize(WebcamResolution.VGA.getSize());
        webPanel = new WebcamPanel(webcam, false);
        this.add(webPanel);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) throws InterruptedException {
        WebcamGUI w = new WebcamGUI();
        w.start();
    }
}
