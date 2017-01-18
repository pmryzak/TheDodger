/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thedodger.main;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * Klasa służąca do czytania znaków z klawiatury. 
 *W tym przypadku sprawdza jakie klawisze są wciskane, a następnie (jeżeli są) przesuwa nasz obiekt.
 */
public class CReader implements KeyListener{
    /** Metoda opisująca poruszanie się naszego obiektu gdy jakaś strzałka jest wciśnięta
     * @param e klawisz
     */
    @Override
    public void keyPressed(KeyEvent e){
        int keyCode = e.getKeyCode();
        if(keyCode== KeyEvent.VK_LEFT){
            CDodger.setXDir(-1);

        }
        if(keyCode== KeyEvent.VK_RIGHT){
            CDodger.setXDir(1);
        }
        if(keyCode== KeyEvent.VK_UP){
            CDodger.setYDir(-1);
        }
        if(keyCode== KeyEvent.VK_DOWN){
            CDodger.setYDir(1);
        }
    }
 
    
    /** Zatrzymuje obiekt gdy klawisze są zwalniane
     * @param e klawisz
     */
    @Override
    public void keyReleased(KeyEvent e){
        int keyCode = e.getKeyCode();
        if(keyCode== KeyEvent.VK_LEFT){
            CDodger.setXDir(0);
            
        }
        if(keyCode== KeyEvent.VK_RIGHT){
            CDodger.setXDir(0);
        }
        if(keyCode== KeyEvent.VK_UP){
            CDodger.setYDir(0);
        }
        if(keyCode== KeyEvent.VK_DOWN){
            CDodger.setYDir(0);
        }
    }
 
    //@Override
    
    public void keyTyped(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_P) CDodger.x=50;
            
    }
 
    
}
