/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npetanjek_zadaca_2.schedulers;

import java.util.List;

/**
 *
 * @author Nikola
 */
public interface ProgramComponent {
    
    public int getId();
    public void add(ProgramComponent programComponent);
    public List<ProgramComponent> getChildren();
    public void print();
    
}
