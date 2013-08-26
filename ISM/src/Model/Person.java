package Model;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author patrick
 */
public class Person {

    private String navn;
    private String adresse;
    private String fodselsdag;
    private boolean indskrevet;
    private File imagePath;
    private boolean hone;
    private boolean reserve;
    private ArrayList<String> geaster;
    private HoneUser honeUser;

    public Person(String navn, String adresse, String fodselsdag, File imagePath) {
        this.navn = navn;
        this.imagePath = imagePath;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getFodselsdag() {
        return fodselsdag;
    }

    public void setFodselsdag(String fodselsdag) {
        this.fodselsdag = fodselsdag;
    }

    public boolean isIndskrevet() {
        return indskrevet;
    }

    public void setIndskrevet(boolean indskrevet) {
        this.indskrevet = indskrevet;
    }

    public File getImagePath() {
        return imagePath;
    }

    public void setImagePath(File imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isHone() {
        return hone;
    }

    public void setHone(boolean hone) {
        this.hone = hone;
    }

    public boolean isReserve() {
        return reserve;
    }

    public void setReserve(boolean reserve) {
        this.reserve = reserve;
    }

    public ArrayList<String> getGeaster() {
        return geaster;
    }

    public void setGeaster(ArrayList<String> geaster) {
        this.geaster = geaster;
    }

    public HoneUser getHoneUser() {
        return honeUser;
    }

    public void setHoneUser(HoneUser honeUser) {
        this.honeUser = honeUser;
    }
}
