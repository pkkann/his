/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package date;

import control.person.PersonHandler;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.MainGUI;

/**
 *
 * @author patrick
 */
public class DateChangeDetecter implements Runnable {

    private Thread th;
    private Calendar c;
    private PersonHandler personHandler;
    private ADate currentDate;
    private MainGUI mainGUI;

    public DateChangeDetecter(PersonHandler personHandler, MainGUI mainGUI) {
        this.personHandler = personHandler;
        this.mainGUI = mainGUI;
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
        System.out.println(c.getTime());
        int date = c.get(Calendar.DATE);
        currentDate = new ADate(c.get(Calendar.DATE), c.get(Calendar.MONTH), c.get(Calendar.YEAR));
        mainGUI.setDate(currentDate);
        
        if (Thread.currentThread() == th) {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(DateChangeDetecter.class.getName()).log(Level.SEVERE, null, ex);
                }
                c = Calendar.getInstance();
                if (c.get(Calendar.DATE) != date) {
                    date = c.get(Calendar.DATE);
                    currentDate.setDay(c.get(Calendar.DATE));
                    currentDate.setMonth(c.get(Calendar.MONTH));
                    currentDate.setYear(c.get(Calendar.YEAR));
                    mainGUI.setDate(currentDate);
                    personHandler.checkExpirationDates(currentDate);
                }

            }
        }
    }
}
