/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hismtilhis;

import java.sql.SQLException;
import java.util.ArrayList;
import model.Person;
import model.User;

/**
 *
 * @author Patrick
 */
public class HismTilHis {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        HismDAO hismDAO = new HismDAO();
        ArrayList<Person> persons = hismDAO.getPersons();
        ArrayList<User> users = hismDAO.getUsers();
        
        HisDAO hisDAO = new HisDAO();
        hisDAO.insertPersons(persons);
        hisDAO.insertUsers(users);
    }
    
}
