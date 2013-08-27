/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author pkann
 */
public class HoneUser {
    
    private String username;
    private String password;
    private boolean admin;
    private boolean reserve;

    public HoneUser(String username, String password, boolean admin, boolean reserve) {
        this.username = username;
        this.password = password;
        this.admin = admin;
        this.reserve = reserve;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isReserve() {
        return reserve;
    }

    public void setReserve(boolean reserve) {
        this.reserve = reserve;
    }
    
    
    
}
