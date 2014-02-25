/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hismtilhis;

import db.DBTool;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Person;

/**
 *
 * @author Patrick
 */
public class HismTilHis {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        HismDAO hismDAO = new HismDAO();
        ArrayList<Person> persons = hismDAO.getPersons();
        System.out.println(persons.size());
    }
    
}
