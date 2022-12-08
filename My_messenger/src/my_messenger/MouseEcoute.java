/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my_messenger;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author PC
 */
public class MouseEcoute implements MouseListener{
    
    Principale principale;
    

    public Principale getPrincipale() {
        return principale;
    }

    public void setPrincipale(Principale principale) {
        this.principale = principale;
    }

    public MouseEcoute(Principale principale) {
        setPrincipale(principale);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (getPrincipale() != null) {
            if (getPrincipale().getValidation() == e.getSource()) {
                getPrincipale().getClient().conect();
                getPrincipale().getClient().SetNomClient();
                new Index(getPrincipale().getTextArea().getText());
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
     
    }

    @Override
    public void mouseReleased(MouseEvent e) {
     
    }

    @Override
    public void mouseEntered(MouseEvent e) {
     
    }

    @Override
    public void mouseExited(MouseEvent e) {
     
    }
    
}
