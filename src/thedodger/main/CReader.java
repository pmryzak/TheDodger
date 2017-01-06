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
 *W tym przypadku sprawdza jakie klawisze są wciśnięte, a następnie (jeżeli są) przesuwa nasz obiekt.
 */
public class CReader implements KeyListener{
/** Metoda opisująca poruszanie się naszego obiektu gdy jakaś strzałka jest wciśnięta*/
    //@Override
    public void keyPressed(KeyEvent e){
        int keyCode = e.getKeyCode();
        if(keyCode== e.VK_LEFT){
            CDodger.setXDir(-1);

        }
        if(keyCode== e.VK_RIGHT){
            CDodger.setXDir(1);
        }
        if(keyCode== e.VK_UP){
            CDodger.setYDir(-1);
        }
        if(keyCode== e.VK_DOWN){
            CDodger.setYDir(1);
        }
    }
 
    //@Override
    
    public void keyReleased(KeyEvent e){
        int keyCode = e.getKeyCode();
        if(keyCode== e.VK_LEFT){
            CDodger.setXDir(0);
        }
        if(keyCode== e.VK_RIGHT){
            CDodger.setXDir(0);
        }
        if(keyCode== e.VK_UP){
            CDodger.setYDir(0);
        }
        if(keyCode== e.VK_DOWN){
            CDodger.setYDir(0);
        }
    }
 
    //@Override
    
    public void keyTyped(KeyEvent e) {
    }
 
    
}
