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
public class ScheduleMaker extends Scheduler {
    
    public ScheduleMaker(Schedule scheduler) {
        super(scheduler);
    }

    @Override
    public void addShow() {
        scheduler.addShow();
    }

    @Override
    public void getChannelSchedule() {
        scheduler.getChannelSchedule();
    }
    
}
