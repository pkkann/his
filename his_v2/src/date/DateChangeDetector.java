/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package date;

import control.PersonHandler;
import control.QuarantineHandler;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.MainGUI;

/**
 *
 * @author Patrick
 */
public class DateChangeDetector implements Runnable {
    
    private Thread th;
    private PersonHandler peH;
    private QuarantineHandler quH;
    private MainGUI mainGUI;
    
    public DateChangeDetector(MainGUI mainGUI, PersonHandler peH, QuarantineHandler quH) {
        this.mainGUI = mainGUI;
        this.peH = peH;
        this.quH = quH;
    }
    
    public void start() {
        System.out.println("##### Starting date detector... #####");
        th = new Thread(this);
        th.start();
    }
    
    public void stop() {
        th = null;
    }

    @Override
    public void run() {
        System.out.println("##### Date detector started! #####");
        Calendar currentDate = Calendar.getInstance();
        
        // Do start stuff
        peH.checkExpirationDates();
        mainGUI.updateDate("");
        /////////////////
        
        if(Thread.currentThread() == th) {
            while(true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(DateChangeDetector.class.getName()).log(Level.SEVERE, null, ex);
                }
                Calendar checkDate = Calendar.getInstance();
                if(currentDate.get(Calendar.DATE) != checkDate.get(Calendar.DATE)) {
                    currentDate = Calendar.getInstance();
                    
                    //Do stuff on detect
                    System.out.println("##### Date Changed! #####");
                    peH.checkExpirationDates();
                    mainGUI.updateDate("");
                    ////////////////////
                }
                
            }
        }
        
    }
    
}
