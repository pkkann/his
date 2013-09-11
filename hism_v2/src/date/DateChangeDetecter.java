/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package date;

import control.person.PersonHandler;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author patrick
 */
public class DateChangeDetecter implements Runnable{

    private Thread th;
    private Calendar c;
    private PersonHandler personHandler;
    
    public DateChangeDetecter(PersonHandler personHandler) {
        this.personHandler = personHandler;
        
    }
    
    public void start() {
        th = new Thread(this);
        th.start();
    }
    
    public void stop() {
        th = null;
    }
    
    @Override
    public void run() {
        c = Calendar.getInstance();
        int date = c.get(Calendar.DATE);
        if(Thread.currentThread() == th) {
            while(true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(DateChangeDetecter.class.getName()).log(Level.SEVERE, null, ex);
                }
                c = Calendar.getInstance();
                if(c.get(Calendar.DATE) == date) {
                    System.out.println("NO change");
                    System.out.println(date);
                } else {
                    date = c.get(Calendar.DATE);
                    System.out.println("CHANGE");
                    System.out.println(date);
                }
                
            }
        }
    }
    
}
