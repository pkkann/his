/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hismtilhis;

import date.ADate;
import db.DBTool;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import model.Person;

/**
 *
 * @author Patrick
 */
public class HisDAO {
    
    Connection conn;
    
    public void insertAllPersons(ArrayList<Person> persons) throws SQLException {
        conn = DBTool.getInstanceHIS();
        
        Statement s = conn.createStatement();
        
        Iterator<Person> i = persons.iterator();
        
        while(i.hasNext()) {
            Person p = i.next();
            String firstname = p.getFirstname();
            String middlename = p.getMiddlename();
            String lastname = p.getLastname();
            String address = p.getAddress();
            String creationdate = ADate.formatADate(p.getCreationDate(), "/");
            String birthdaydate = ADate.formatADate(p.getBirthdayDate(), "/");
            String expirationdate = ADate.formatADate(p.getExpirationDate(), "/");
            String sql = "INSERT INTO Person (firstname, middlename, lastname, address, creationdate, birthdaydate, hoene, oneone, reserve, expired, expirationdate) "
                    + "VALUES();";
            s.execute(sql);
        }
    }
}
