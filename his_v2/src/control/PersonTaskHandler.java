/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package control;

import entity.Person;
import entity.PersonTask;
import entity.User;
import model.PersonTaskRegister;

/**
 *
 * @author Patrick
 */
public class PersonTaskHandler implements HismHandlerIF {
    
    private PersonTaskRegister ptR;
    
    public PersonTaskHandler(PersonTaskRegister ptR) {
        this.ptR = ptR;
    }
    
    public int createPersonTask(String taskText, Person person, User author) {
        if(!taskText.isEmpty() && person != null && author != null) {
            PersonTask pt = new PersonTask(taskText, person, author);
            ptR.registerPersonTask(pt);
        } else {
            return FIELDS_NOT_FILLED_ERROR;
        }
        return NO_ERROR;
    }
    
    public int setPersonTaskFinished(int idPersonTask) {
        PersonTask pt = ptR.getPersonTask(idPersonTask);
        if(pt != null) {
            pt.setFinished(true);
            ptR.savePersonTask(pt);
        } else {
            return GET_ERROR;
        }
        return NO_ERROR;
    }
    
    
    
}
