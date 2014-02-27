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
import model.User;

/**
 *
 * @author Patrick
 */
public class HisDAO {

    Connection conn;

    public void insertPersons(ArrayList<Person> persons) throws SQLException {
        System.out.println("Inserting " + persons.size() + " persons");
        conn = DBTool.getInstanceHIS();

        Statement s = conn.createStatement();

        Iterator<Person> i = persons.iterator();

        while (i.hasNext()) {
            Person p = i.next();
            String firstname = p.getFirstname();
            String middlename = p.getMiddlename();
            String lastname = p.getLastname();
            String address = p.getAddress();
            String creationdate = ADate.formatADate(p.getCreationDate(), "/");
            String birthdaydate = ADate.formatADate(p.getBirthdayDate(), "/");
            String expirationdate = "";
            if (!p.isOneOne() && !p.isHone()) {
                String e = ADate.formatADate(p.getExpirationDate(), "/");
                String[] es = e.split("/");
                expirationdate = es[1] + "/" + es[2];
            }
            String picturepath = "N";
            int hoene = 0;
            if (p.isHone()) {
                hoene = 1;
            }
            int oneone = 0;
            if (p.isOneOne()) {
                oneone = 1;
            }
            int reserve = 0;
            int expired = 0;

            String sql = "INSERT INTO Person (firstname, middlename, lastname, address, creationdate, birthdaydate, hoene, oneone, reserve, expired, expirationdate, picturepath) "
                    + "VALUES('" + firstname + "', '" + middlename + "', '" + lastname + "', '" + address + "', '" + creationdate + "', '" + birthdaydate + "', " + hoene + ", " + oneone + ", " + reserve + ", " + expired + ", '" + expirationdate + "', '" + picturepath + "');";
            System.out.println("Adding " + firstname + " " + middlename + " " + lastname + " to the batch job list");
            s.addBatch(sql);
        }

        System.out.println("Running batch jobs");
        s.executeBatch();

        s.close();
        conn.close();
        System.out.println("DONE");
    }

    public void insertUsers(ArrayList<User> users) throws SQLException {
        System.out.println("Inserting " + users.size() + " users");
        conn = DBTool.getInstanceHIS();

        Statement s = conn.createStatement();

        Iterator<User> i = users.iterator();

        while (i.hasNext()) {
            User u = i.next();
            String username = u.getEmail();
            String password = u.getPassword();
            String firstname = u.getFirstname();
            String middlename = u.getMiddlename();
            String lastname = u.getLastname();
            String creationdate = ADate.formatADate(u.getCreationDate(), "/");
            int admin = 0;
            int reserve = 0;
            if (u.isReserve()) {
                reserve = 1;
            }

            String sql = "INSERT INTO user (username, password, firstname, middlename, lastname, creationdate, administrator, reserve)"
                    + "VALUES('" + username + "', '" + password + "', '" + firstname + "', '" + middlename + "', '" + lastname + "', '" + creationdate + "', " + admin + ", " + reserve + ");";
            System.out.println("Adding " + firstname + " " + middlename + " " + lastname + " to the batch job list");
            s.addBatch(sql);
        }

        System.out.println("Running batch jobs");
        s.executeBatch();

        s.close();
        conn.close();
        System.out.println("DONE");
    }
}
