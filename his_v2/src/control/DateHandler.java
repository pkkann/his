/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package control;

import control.PersonHandler;
import date.DateChecker;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Patrick
 */
public class DateHandler implements Observer {
    
    private DateChecker dc;
    private PersonHandler peH;
    
    public DateHandler(DateChecker dc, PersonHandler peH) {
        this.dc = dc;
        this.peH = peH;
        dc.addObserver(this);
        dc.start();
    }

    @Override
    public void update(Observable o, Object arg) {
        peH.checkExpirationDates();
    }
    
}
