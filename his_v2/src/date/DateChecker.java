/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package date;

import java.util.Calendar;
import java.util.Date;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Patrick
 */
public class DateChecker extends Observable implements Runnable {
    
    private Thread th;
    private int currentDate;
    private int newDate;
    
    public void start() {
        th = new Thread(this);
        th.start();
    }
    
    public void stop() {
        th = null;
    }
    
    private void setCurrentDate() {
        currentDate = Calendar.getInstance().get(Calendar.DATE);
    }
    
    private void setNewDate() {
        newDate = Calendar.getInstance().get(Calendar.DATE);
    }

    @Override
    public void run() {
        setCurrentDate();
        
        while(true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(DateChecker.class.getName()).log(Level.SEVERE, null, ex);
            }
            setNewDate();
            if(currentDate != newDate) {
                setCurrentDate();
                setChanged();
                notifyObservers();
            }
        }
    }
    
}
