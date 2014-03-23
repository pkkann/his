/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import entity.Person;
import java.awt.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author patrick
 */
public class PersonDAO {

    private PersonDAO() {
    }

    public static void deletePerson(Person p) {
        try {
            Statement st = DBTool.getInstance().createStatement();

            st.executeUpdate("DELETE FROM person WHERE idperson = " + p.getIdPerson() + ";");

            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(PersonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deletePersons(Iterator<Person> i) {
        try {
            Statement st = DBTool.getInstance().createStatement();

            while (i.hasNext()) {
                Person p = i.next();
                st.addBatch("DELETE FROM person WHERE idperson = " + p.getIdPerson() + ";");
            }
            st.executeBatch();

            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(PersonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void updatePerson(Person p) {
        try {
            Statement st = DBTool.getInstance().createStatement();

            int idPerson = p.getIdPerson();
            String firstname = p.getFirstname();
            String middlename = p.getMiddlename();
            String lastname = p.getLastname();
            String address = p.getAddress();
            String birthdayDate = p.getBirthdayDate();
            String creationDate = p.getCreationDate();
            String email = p.getEmail();
            String phone = p.getPhone();
            String expirationDate = p.getExpirationDate();
            String picturePath = p.getPicturePath();
            String expired = "0";
            if (p.isExpired()) {
                expired = "1";
            }
            String hoene = "0";
            if (p.isHoene()) {
                hoene = "1";
            }
            String oneOne = "0";
            if (p.isOneOne()) {
                oneOne = "1";
            }
            String reserve = "0";
            if (p.isReserve()) {
                reserve = "1";
            }

            st.executeUpdate("UPDATE person SET "
                    + "firstname = '" + firstname + "', "
                    + "middlename = '" + middlename + "', "
                    + "lastname = '" + lastname + "', "
                    + "address = '" + address + "', "
                    + "birthdaydate = '" + birthdayDate + "', "
                    + "creationdate = '" + creationDate + "', "
                    + "email = '" + email + "', "
                    + "phone = '" + phone + "', "
                    + "expirationdate = '" + expirationDate + "', "
                    + "picturepath = '" + picturePath + "', "
                    + "expired = " + expired + ", "
                    + "hoene = " + hoene + ", "
                    + "oneone = " + oneOne + ", "
                    + "reserve = " + reserve + " "
                    + "WHERE idperson = " + idPerson + ";");

            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(PersonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Person getPerson(int idperson) {
        try {
            Statement st = DBTool.getInstance().createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM person WHERE idperson = " + idperson + ";");

            ArrayList<Person> result = new ArrayList<>();
            while (rs.next()) {
                String firstname = rs.getString("firstname");
                String middlename = rs.getString("middlename");
                String lastname = rs.getString("lastname");
                String address = rs.getString("address");
                String birthdayDate = rs.getString("birthdaydate");
                String creationDate = rs.getString("creationdate");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String expirationDate = rs.getString("expirationdate");
                String picturePath = rs.getString("picturepath");
                boolean expired = false;
                if (rs.getString("expired").equals("1")) {
                    expired = true;
                }
                boolean hoene = false;
                if (rs.getString("hoene").equals("1")) {
                    hoene = true;
                }
                boolean oneOne = false;
                if (rs.getString("oneone").equals("1")) {
                    oneOne = true;
                }
                boolean reserve = false;
                if (rs.getString("reserve").equals("1")) {
                    reserve = true;
                }

                result.add(new Person(firstname, middlename, lastname, address, email, phone, birthdayDate, expirationDate, creationDate, hoene, reserve, oneOne, picturePath));
            }

            rs.close();
            st.close();

            if (result.size() > 0) {
                return result.get(0);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PersonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static ArrayList<Person> getDublicatePersons() {
        try {
            Statement st = DBTool.getInstance().createStatement();
            
            ResultSet rs = st.executeQuery(""
                    + "SELECT a.* from his.person a "
                    + "INNER JOIN his.person b ON a.firstname = b.firstname AND a.middlename = b.middlename AND a.lastname = b.lastname "
                    + "WHERE a.idperson <> b.idperson");
            ArrayList<Person> persons = new ArrayList<>();
            
            while(rs.next()) {
                int idperson = rs.getInt("idperson");
                String firstname = rs.getString("firstname");
                String middlename = rs.getString("middlename");
                String lastname = rs.getString("lastname");
                String address = rs.getString("address");
                String birthdayDate = rs.getString("birthdaydate");
                String creationDate = rs.getString("creationdate");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String expirationDate = rs.getString("expirationdate");
                String picturePath = rs.getString("picturepath");
                boolean expired = false;
                if (rs.getString("expired").equals("1")) {
                    expired = true;
                }
                boolean hoene = false;
                if (rs.getString("hoene").equals("1")) {
                    hoene = true;
                }
                boolean oneOne = false;
                if (rs.getString("oneone").equals("1")) {
                    oneOne = true;
                }
                boolean reserve = false;
                if (rs.getString("reserve").equals("1")) {
                    reserve = true;
                }
                
                Person p = new Person(firstname, middlename, lastname, address, email, phone, birthdayDate, expirationDate, creationDate, hoene, reserve, oneOne, picturePath);
                p.setIdPerson(idperson);
                persons.add(p);
            }
            
            return persons;
        } catch (SQLException ex) {
            Logger.getLogger(PersonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ArrayList<Person> getPersons() {
        try {
            Statement st = DBTool.getInstance().createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM person;");
            ArrayList<Person> persons = new ArrayList<>();

            while (rs.next()) {
                int idPerson = rs.getInt("idperson");
                String firstname = rs.getString("firstname");
                String middlename = rs.getString("middlename");
                String lastname = rs.getString("lastname");
                String address = rs.getString("address");
                String birthdayDate = rs.getString("birthdaydate");
                String creationDate = rs.getString("creationdate");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String expirationDate = rs.getString("expirationdate");
                String picturePath = rs.getString("picturepath");
                boolean expired = false;
                if (rs.getString("expired").equals("1")) {
                    expired = true;
                }
                boolean hoene = false;
                if (rs.getString("hoene").equals("1")) {
                    hoene = true;
                }
                boolean oneOne = false;
                if (rs.getString("oneone").equals("1")) {
                    oneOne = true;
                }
                boolean reserve = false;
                if (rs.getString("reserve").equals("1")) {
                    reserve = true;
                }

                Person p = new Person(firstname, middlename, lastname, address, email, phone, birthdayDate, expirationDate, creationDate, hoene, reserve, oneOne, picturePath);
                p.setIdPerson(idPerson);
                persons.add(p);
            }
            
            rs.close();
            st.close();
            return persons;

        } catch (SQLException ex) {
            Logger.getLogger(PersonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void runSQLScriptBatch(ArrayList<String> a) {
        try {
            Statement st = DBTool.getInstance().createStatement();
            
            Iterator<String> i = a.iterator();
            while(i.hasNext()) {
                String sql = i.next();
                st.addBatch(sql);
            }
            st.executeBatch();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(PersonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void runSQLScript(String sql) {
        try {
            Statement st = DBTool.getInstance().createStatement();
            st.execute(sql);
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(PersonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
