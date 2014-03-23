/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hisdbfixer;

/**
 *
 * @author Patrick
 */
public class Task {
    
    private String name;
    private Thread t;
    
    public Task(String name, Runnable r) {
        t = new Thread(r);
        this.name = name;
    }
    
    public void runTask() throws InterruptedException {
        System.out.println("## Running task: " + name);
        t.start();
        t.join();
        System.out.println("--------------------------");
    }
    
}
