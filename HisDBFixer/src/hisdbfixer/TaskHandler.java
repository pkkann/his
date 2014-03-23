/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hisdbfixer;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Patrick
 */
public class TaskHandler {
    
    private final LinkedList<Task> tasks;
    
    public TaskHandler() {
        tasks = new LinkedList<>();
    }
    
    public void createTask(String name, Runnable task) {
        Task t = new Task(name, task);
        tasks.add(t);
    }
    
    public int getTaskCount() {
        return tasks.size();
    }
    
    public void runTasks() {
        while(tasks.size() != 0) {
            try {
                tasks.pop().runTask();
            } catch (InterruptedException ex) {
                Logger.getLogger(TaskHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
