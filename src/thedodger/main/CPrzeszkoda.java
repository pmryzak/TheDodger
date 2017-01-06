/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thedodger.main;

import java.awt.Image;
import static java.lang.Math.abs; 
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author Patryk
 */
public class CPrzeszkoda {
    protected int x,y; //dla x: 1 pas = 25, 2 pas = 105, 3 = 205, 4 = 285
    protected int yDir;
    protected Image obrazek;
    protected ImageIcon ikona;
    static private Boolean kolizja=false;
    
    
    
    public CPrzeszkoda(int pas,int nrAuta) {
        switch(pas){  //ustawianie pozycji x przeszkody
            case 1: this.x=25; break;
            case 2: this.x=105; break;
            case 3: this.x=205; break;
            case 4: this.x=285; break;
            default: break;
        }
        Random rand = new Random();
        int  n = rand.nextInt(1000) -1050; //losowe umieszczanie przeszków
        this.y=n;
        
        this.ikona = new ImageIcon("src/images/Rotated/rcar"+nrAuta+".png"); //grafika przeszkody
        obrazek=ikona.getImage();
    }
    
    public void setAuto(int nrAuta){ //zmiana grafiki przeszkody
        this.ikona = new ImageIcon("src/images/Rotated/rcar"+nrAuta+".png");
        this.obrazek=this.ikona.getImage();
    }
    public void reset(){ //reset pozycji 
        Random rand = new Random();
        this.y=rand.nextInt(1000)-1050;
    }
    
    public void sprawdzKolizje(){ //sprawdzanie czy nie ma kolizji miedzy naszym obiekte a jakas przeszkodą
        if(  (abs(CDodger.x - this.x) < 50) && (abs(CDodger.y - this.y) <90) ) kolizja=true;
    }
    public static Boolean getKolizja(){ //zwraca informacje czy zaszła kolizja
        return kolizja;
    }
    public static void setKolizja(Boolean bool){ //ustawia informacje o kolizji
        kolizja=bool;
    }
}

