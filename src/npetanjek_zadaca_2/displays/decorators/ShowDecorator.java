/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npetanjek_zadaca_2.displays.decorators;

import java.util.List;
import npetanjek_zadaca_2.entities.Emisija;
import npetanjek_zadaca_2.entities.Uloga;
import npetanjek_zadaca_2.observers.Participant;
import npetanjek_zadaca_2.schedulers.ProgramComponent;

/**
 *
 * @author Nikola
 */
public class ShowDecorator extends Decorator {

    private Emisija show;
    private String print;

    public ShowDecorator(ProgramComponent decoratedComponent) {
        super(decoratedComponent);
        this.show = (Emisija) decoratedComponent;
    }

    @Override
    public void print() {
        int showId = show.getId();
        String showName = show.getNazivEmisije();
        String showStart = show.getPocetakEmisije().toString();
        String showEnd = show.getZavrsetakEmisije().toString();
        String participants = getParticipants(show);
        print = String.format("%2s\t%-40s\t%s-%s\t%-40s", showId, showName, showStart, showEnd, participants);
        System.out.print(print);
    }

    private String getParticipants(Emisija emisija) {
        StringBuilder osobeUloge = new StringBuilder();
        List<Participant> participants = emisija.getParticipants();
        if (participants != null && !participants.isEmpty()) {
            for (Participant participant : participants) {
                Uloga role = participant.getState();
                if (role != null) {
                    osobeUloge.append(participant.getOsoba().getImeIPrezime());
                    osobeUloge.append(" - ");
                    osobeUloge.append(participant.getState().getOpisUloge());
                    osobeUloge.append(" ");
                }
            }
        }
        return osobeUloge.toString();
    }

}
