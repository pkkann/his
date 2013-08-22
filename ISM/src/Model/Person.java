package Model;

import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * @author patrick
 */
public class Person {

    private String navn;
    private boolean indskrevet;
    private Image billed;
    private boolean hone;
    private boolean reserve;
    private ArrayList<String> geaster;

    public Person(String navn, Image billed) {
        this.navn = navn;
        this.billed = billed;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public boolean isIndskrevet() {
        return indskrevet;
    }

    public void setIndskrevet(boolean indskrevet) {
        this.indskrevet = indskrevet;
    }

    public Image getBilled() {
        return billed;
    }

    public void setBilled(Image billed) {
        this.billed = billed;
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
}
