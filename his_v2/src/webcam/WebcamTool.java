/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcam;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamPicker;
import com.github.sarxos.webcam.WebcamResolution;
import configuration.PropertiesTool;
import file.FileTool;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import view.CreatePersonDIA;
import view.EditPersonDIA;

/**
 *
 * @author Patrick
 */
public class WebcamTool {

    //Webcam frame
    private static JFrame frame;
    private static JPanel frameButtonPanel;
    private static JButton cancelButton;
    private static JButton captureButton;

    //Picture dialog
    private static JDialog dialog;
    private static JPanel dialogButtonPanel;
    private static JPanel dialogPicturePanel;
    private static JButton yesButton;
    private static JButton noButton;

    //Webcam stuff
    private static Webcam webcam;
    private static WebcamPanel webPanel;
    private static Thread webThread;
    private static BufferedImage capturedImage;
    private static final String defaultCapturePath = "webcam/capture.jpg";

    public WebcamTool() {
    }

    public static void spawnWebcamFrame(final CreatePersonDIA dia) throws InterruptedException {
        //Clean
        FileTool.deleteFile(new File(defaultCapturePath));

        try {

            //Init gui stuff
            frame = new JFrame();
            frame.setLayout(new BorderLayout());

            frameButtonPanel = new JPanel();
            frame.add(frameButtonPanel, BorderLayout.SOUTH);

            cancelButton = new JButton("Annuller");
            cancelButton.setEnabled(false);
            frameButtonPanel.add(cancelButton);

            captureButton = new JButton("Tag billed");
            captureButton.setEnabled(false);
            frameButtonPanel.add(captureButton);

            //Add action listeners
            cancelButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    webPanel.stop();
                    webThread = null;
                }
            });

            captureButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    capturedImage = webcam.getImage();
                    try {
                        ImageIO.write(capturedImage, "jpg", new FileOutputStream(new File(defaultCapturePath)));
                    } catch (IOException ex) {
                        Logger.getLogger(WebcamTool.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    dia.setPicturePath(defaultCapturePath);
                    dia.setPicturePanel();
                    frame.dispose();
                    webPanel.stop();
                    webThread = null;
                }
            });

            //Init webcam stuff
            webcam = getDefaultWebcam();
            webcam.setViewSize(WebcamResolution.VGA.getSize());

            webPanel = new WebcamPanel(webcam, false);
            frame.add(webPanel, BorderLayout.CENTER);

            webThread = new Thread() {

                @Override
                public void run() {
                    webPanel.start();
                    cancelButton.setEnabled(true);
                    captureButton.setEnabled(true);
                }
            };

            //start
            frame.setUndecorated(true);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setAlwaysOnTop(true);
            webThread.start();
            frame.setVisible(true);
        } catch (IllegalArgumentException | NullPointerException ex) {
            dia.setAlwaysOnTop(false);
            JOptionPane.showMessageDialog(frame,
                    "Webcamet ser ikke ud til at være tilsluttet.\nKontakt en administrator for at få hjælp",
                    "Webcam fejl",
                    JOptionPane.ERROR_MESSAGE);
            dia.setAlwaysOnTop(true);
        }
    }

    public static void spawnWebcamFrame(final EditPersonDIA dia) throws InterruptedException {
        //Clean
        FileTool.deleteFile(new File(defaultCapturePath));

        try {

            //Init gui stuff
            frame = new JFrame();
            frame.setLayout(new BorderLayout());

            frameButtonPanel = new JPanel();
            frame.add(frameButtonPanel, BorderLayout.SOUTH);

            cancelButton = new JButton("Annuller");
            cancelButton.setEnabled(false);
            frameButtonPanel.add(cancelButton);

            captureButton = new JButton("Tag billed");
            captureButton.setEnabled(false);
            frameButtonPanel.add(captureButton);

            //Add action listeners
            cancelButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    webPanel.stop();
                    webThread = null;
                }
            });

            captureButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    capturedImage = webcam.getImage();
                    try {
                        ImageIO.write(capturedImage, "jpg", new FileOutputStream(new File(defaultCapturePath)));
                    } catch (IOException ex) {
                        Logger.getLogger(WebcamTool.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    dia.setPicturePath(defaultCapturePath);
                    dia.setPicturePanel();
                    frame.dispose();
                    webPanel.stop();
                    webThread = null;
                }
            });

            //Init webcam stuff
            webcam = getDefaultWebcam();
            webcam.setViewSize(WebcamResolution.VGA.getSize());

            webPanel = new WebcamPanel(webcam, false);
            frame.add(webPanel, BorderLayout.CENTER);

            webThread = new Thread() {

                @Override
                public void run() {
                    webPanel.start();
                    cancelButton.setEnabled(true);
                    captureButton.setEnabled(true);
                }
            };

            //start
            frame.setUndecorated(true);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setAlwaysOnTop(true);
            webThread.start();
            frame.setVisible(true);
        } catch (IllegalArgumentException | NullPointerException ex) {
            dia.setAlwaysOnTop(false);
            JOptionPane.showMessageDialog(frame,
                    "Webcamet ser ikke ud til at være tilsluttet.\nKontakt en administrator for at få hjælp",
                    "Webcam fejl",
                    JOptionPane.ERROR_MESSAGE);
            dia.setAlwaysOnTop(true);
        }
    }

    private static Webcam getDefaultWebcam() {
        String camName = PropertiesTool.getInstance().getProperty("defaultcam");

        List<Webcam> cams = Webcam.getWebcams();

        for (Webcam w : cams) {
            if (w.getName().equals(camName)) {
                return w;
            }
        }
        return null;
    }

}
