/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npetanjek_zadaca_2.displays.decorators;

import npetanjek_zadaca_2.entities.Emisija;
import npetanjek_zadaca_2.entities.Vrsta;
import npetanjek_zadaca_2.helpers.ScheduleHelper;
import npetanjek_zadaca_2.schedulers.ProgramComponent;

/**
 *
 * @author Nikola
 */
public class RevenueDecorator extends Decorator {

    ScheduleHelper scheduleHelper;

    public RevenueDecorator(ProgramComponent decoratedComponent) {
        super(decoratedComponent);
        scheduleHelper = ScheduleHelper.getInstance();
    }

    @Override
    public void print() {
        Emisija show = (Emisija) decoratedComponent;
        int showId = show.getId();
        String showName = show.getNazivEmisije();
        String showStart = show.getPocetakEmisije().toString();
        String showEnd = show.getZavrsetakEmisije().toString();
        Vrsta showType = scheduleHelper.getVrstaById(show.getVrstaEmisije());
        if (showType.getMozeImatiReklame() == 1) {
            System.out.format("%2s\t%-40s\t%s-%s\t%22s\n", showId, showName, showStart, showEnd, showType.getMaksTrajanjeReklama());
        }
    }

}
