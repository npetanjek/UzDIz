/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npetanjek_zadaca_2.displays.decorators;

import npetanjek_zadaca_2.entities.Program;
import npetanjek_zadaca_2.schedulers.ProgramComponent;

/**
 *
 * @author Nikola
 */
public class ProgramDecorator extends Decorator {
    
    private final Program program;
    private String print;

    public ProgramDecorator(ProgramComponent decoratedComponent) {
        super(decoratedComponent);
        this.program = (Program) decoratedComponent;
    }

    @Override
    public void print() {
        //decoratedComponent.print();
        String channelName = program.getNazivPrograma();
        String channelStart = program.getVrijemePocetka();
        String channelEnd = program.getVrijemeZavrsetka();
        System.out.println("---------------------------");
        print = String.format("%10s\t%s-%s\n", channelName, channelStart, channelEnd);
        System.out.print(print);
    }
    
}
