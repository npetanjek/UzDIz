/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npetanjek_zadaca_2.schedulers.schedule_builder;

import npetanjek_zadaca_2.entities.Program;
import npetanjek_zadaca_2.schedulers.Day;
import npetanjek_zadaca_2.schedulers.algorithms.FirstPriorityScheduleMaker;
import npetanjek_zadaca_2.schedulers.algorithms.ScheduleMaker;
import npetanjek_zadaca_2.schedulers.algorithms.Scheduler;
import npetanjek_zadaca_2.schedulers.algorithms.SecondPriorityScheduleMaker;
import npetanjek_zadaca_2.schedulers.algorithms.ThirdPriorityScheduleMaker;

/**
 *
 * @author Nikola
 */
public class ProgramBuilderImpl implements ProgramBuilder {

    private Program program;

    public ProgramBuilderImpl() {      
    }

    @Override
    public Program build() {
        return program;
    }

    @Override
    public ProgramBuilder setChannel(Program channel) {
        this.program = channel;
        return this;
    }
    
    @Override
    public ProgramBuilder createWeek() {
        for (int i = 1; i <= 7; i++) {
            Day day = new Day();
            day.setDay(i);
            program.add(day);
        }
        return this;
    }

    @Override
    public ProgramBuilder addShows() {
        
        Scheduler scheduler = new ScheduleMaker(new FirstPriorityScheduleMaker(program));
        scheduler.getChannelSchedule();
        scheduler.addShow();
        
        scheduler = new ScheduleMaker(new SecondPriorityScheduleMaker(program));
        scheduler.getChannelSchedule();
        scheduler.addShow();
        
        scheduler = new ScheduleMaker(new ThirdPriorityScheduleMaker(program));
        scheduler.getChannelSchedule();
        scheduler.addShow();
        
        return this;
    }

    @Override
    public ProgramBuilder print() {
        program.print();
        return this;
    }

}
