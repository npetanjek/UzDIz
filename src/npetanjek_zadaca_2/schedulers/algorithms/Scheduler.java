/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npetanjek_zadaca_2.schedulers.algorithms;

/**
 *
 * @author nikol
 */
public abstract class Scheduler {
    
    protected Schedule scheduler;
    
    public Scheduler(Schedule scheduler) {
        this.scheduler = scheduler;
    }
    
    public abstract void addShow();
    public abstract void getChannelSchedule();
    
}
