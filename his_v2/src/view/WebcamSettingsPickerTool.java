/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import com.github.sarxos.webcam.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Patrick
 */
public class WebcamSettingsPickerTool {
    
    private static JFrame frame;
    private static JPanel panel;
    private static WebcamPicker picker;
    private static JButton okB;
    
    private WebcamSettingsPickerTool(){}
    
    public static void spawnWebcamPicker(final SettingsDIA dia) {
        frame = new JFrame();
        panel = new JPanel();
        panel.setBackground(new Color(51,51,51));
        frame.add(panel);
        
        okB = new JButton("OK");
        panel.add(okB);
        
        picker = new WebcamPicker(Webcam.getWebcams());
        panel.add(picker);
        
        okB.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String camName = picker.getSelectedWebcam().getName();
                dia.setDefaultCam(camName);
                frame.dispose();
            }
        });
        
        frame.setUndecorated(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
    }
    
}
