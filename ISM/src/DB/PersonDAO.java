/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import DB.tool.DBTool;
import Model.Person;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author pkann
 */
public class PersonDAO {

    private Connection dbConn;

    public PersonDAO() {
        dbConn = DBTool.getInstance();
    }

    public void insertPerson(Person p) {
        try {
            Statement st = dbConn.createStatement();
            
            st.execute("INSERT INTO person (navn, adresse, fodselsdag, udlobsdato, billedPath) VALUES('"+p.getNavn()+"', '"+p.getAdresse()+"', '"+p.getFodselsdag()+"', '"+p.getUdlobsdato()+"', '"+p.getImagePath()+"')");
            st.close();
        } catch (SQLException ex) {
        }
    }
    
    public int getLastID() {
        try {
            Statement st = dbConn.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT idperson FROM person ORDER BY idperson DESC LIMIT 1");
            
            rs.next();
            return rs.getInt(1);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }
}
