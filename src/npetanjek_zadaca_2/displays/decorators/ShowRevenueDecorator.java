/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npetanjek_zadaca_2.displays.decorators;

import npetanjek_zadaca_2.displays.iterators.ScheduleIterator;
import npetanjek_zadaca_2.entities.Vrsta;
import npetanjek_zadaca_2.helpers.ScheduleHelper;
import npetanjek_zadaca_2.schedulers.ProgramComponent;

/**
 *
 * @author Nikola
 */
public class ShowRevenueDecorator extends Decorator {
    
    private final ScheduleHelper scheduleHelper;

    public ShowRevenueDecorator(ProgramComponent decoratedComponent) {
        super(decoratedComponent);
        scheduleHelper = ScheduleHelper.getInstance();
    }
    
    @Override
    public void print() {
        int totalRevenue = 0;        
        decoratedComponent.print();
        System.out.println("---------------------------");
        System.out.format("ID\t%-40s\tTrajanje\t%-40s\n", "Naziv", "Maks. trajanje reklama");
        System.out.format("--\t%-40s\t--------\t%-40s\n", "---------------", "---------------");
        ScheduleIterator iterator = new ScheduleIterator(decoratedComponent.getChildren());
        while (iterator.hasNext()) {
            ProgramComponent component = (ProgramComponent) iterator.next();
            RevenueDecorator revenueDecorator = new RevenueDecorator(component);
            revenueDecorator.print();
            Vrsta showType = scheduleHelper.getVrstaById(scheduleHelper.getEmisijaById(component.getId()).getVrstaEmisije());
            totalRevenue += showType.getMaksTrajanjeReklama();
        }
        System.out.println("Potencijalni prihod od reklama: " + totalRevenue + " min");
    }
    
}
