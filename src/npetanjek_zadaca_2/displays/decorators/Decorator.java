/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npetanjek_zadaca_2.displays.decorators;

import java.util.List;
import npetanjek_zadaca_2.schedulers.ProgramComponent;

/**
 *
 * @author Nikola
 */
public abstract class Decorator implements ProgramComponent {
    
    protected ProgramComponent decoratedComponent;

    public Decorator(ProgramComponent decoratedComponent) {
        this.decoratedComponent = decoratedComponent;
    }
    
    @Override
    public int getId() {
        return decoratedComponent.getId();
    }

    @Override
    public void add(ProgramComponent programComponent) {
        decoratedComponent.add(programComponent);
    }

    @Override
    public List<ProgramComponent> getChildren() {
        return decoratedComponent.getChildren();
    }
    
}
