/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.person;

import java.util.ArrayList;
import model.Person;

/**
 *
 * @author patrick
 */
public class PersonRegister {

    private ArrayList<Person> persons;

    public PersonRegister() {
        persons = new ArrayList<>();
    }

    public int size() {
        return persons.size();
    }

    public boolean isEmpty() {
        return persons.isEmpty();
    }

    public int indexOf(Object o) {
        return persons.indexOf(o);
    }

    public Person get(int index) {
        return persons.get(index);
    }

    public Person set(int index, Person element) {
        return persons.set(index, element);
    }

    public boolean add(Person e) {
        return persons.add(e);
    }

    public void add(int index, Person element) {
        persons.add(index, element);
    }

    public Person remove(int index) {
        return persons.remove(index);
    }

    public boolean remove(Object o) {
        return persons.remove(o);
    }
}
