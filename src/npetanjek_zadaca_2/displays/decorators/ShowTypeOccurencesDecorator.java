/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npetanjek_zadaca_2.displays.decorators;

import npetanjek_zadaca_2.displays.display_composite.menu_composite.MenuItem;
import npetanjek_zadaca_2.displays.iterators.ScheduleIterator;
import npetanjek_zadaca_2.displays.iterators.ShowTypeIterator;
import npetanjek_zadaca_2.entities.Vrsta;
import npetanjek_zadaca_2.schedulers.Day;
import npetanjek_zadaca_2.schedulers.ProgramComponent;

/**
 *
 * @author nikol
 */
public class ShowTypeOccurencesDecorator extends Decorator implements MenuItem {

    private Vrsta showType;

    public ShowTypeOccurencesDecorator(ProgramComponent decoratedComponent, Vrsta vrsta) {
        super(decoratedComponent);
        this.showType = vrsta;
    }

    @Override
    public void print() {
        if (showType != null) {
            decoratedComponent.print();
            ScheduleIterator scheduleIterator = new ScheduleIterator(decoratedComponent.getChildren());
            while (scheduleIterator.hasNext()) {
                ProgramComponent dayComponent = scheduleIterator.next();
                System.out.println(((Day) dayComponent).getDayName());
                System.out.println("---------------------------");
                System.out.format("ID\t%-40s\tTrajanje\t%-40s\n", "Naziv", "Osoba - Uloga");
                System.out.format("--\t%-40s\t--------\t%-40s\n", "---------------", "---------------");
                ShowTypeIterator showTypeIterator = new ShowTypeIterator(dayComponent.getChildren(), showType.getId());
                while (showTypeIterator.hasNext()) {
                    ProgramComponent component = showTypeIterator.next();
                    if (component != null) {
                        ShowDecorator showDecorator = new ShowDecorator(component);
                        showDecorator.print();
                        System.out.println();
                    }
                }
                System.out.println();
            }
        }
    }

}
