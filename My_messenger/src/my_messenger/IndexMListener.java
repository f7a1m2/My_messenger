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
public class IndexMListener implements MouseListener{
    Index index ;

    public Index getIndex() {
        return index;
    }
    
    public void setIndex(Index index) {
        this.index = index;
    }

    public IndexMListener(Index index) {
        this.index = index;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (getIndex().getZone_de_message() != null) {
            getIndex().getClient().Send();
            getIndex().Envoyer_message();
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
