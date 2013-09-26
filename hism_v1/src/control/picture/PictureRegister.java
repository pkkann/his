/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.picture;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author patrick
 */
public class PictureRegister {

    private ArrayList<File> pictures;

    public PictureRegister() {
        pictures = new ArrayList<>();
    }

    public ArrayList<File> getPictures() {
        return pictures;
    }

    public void setPictures(ArrayList<File> pictures) {
        this.pictures = pictures;
    }

    public int size() {
        return pictures.size();
    }

    public boolean isEmpty() {
        return pictures.isEmpty();
    }

    public boolean contains(Object o) {
        return pictures.contains(o);
    }

    public int indexOf(Object o) {
        return pictures.indexOf(o);
    }

    public File get(int index) {
        return pictures.get(index);
    }

    public File set(int index, File element) {
        return pictures.set(index, element);
    }

    public boolean add(File e) {
        return pictures.add(e);
    }

    public void add(int index, File element) {
        pictures.add(index, element);
    }

    public File remove(int index) {
        return pictures.remove(index);
    }

    public boolean remove(Object o) {
        return pictures.remove(o);
    }
}
