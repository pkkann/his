/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author patrick
 */
public abstract class MyMouseListener implements MouseMotionListener {

    @Override
    public void mouseDragged(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        action();
    }
    
    public abstract void action();
    
}
