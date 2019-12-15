/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npetanjek_zadaca_2.schedulers.schedule_builder;

import npetanjek_zadaca_2.entities.Program;

/**
 *
 * @author Nikola
 */
public interface ProgramBuilder {
    
    Program build();
    ProgramBuilder setChannel(Program channel);
    ProgramBuilder createWeek();
    ProgramBuilder addShows();
    ProgramBuilder print();
    
}
