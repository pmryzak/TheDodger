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
 *  Klasa opisująca przeszkody.
 *  Zawiera metodę sprawdząjącą czy zaszła kolizja pomiędzy
 *  naszym obiektem, a przeszkodą.
 *  @author Patryk
 */
public class CPrzeszkoda {
    /**Pierwsza współrzędna położenia pojazdu
     * <li> Dla x=25 - pierwszy pas</li><li> Dla x=105 - drugi pas</li>
     * <li> Dla x=205 - trzeci pas</li><li> Dla x=285 - czwarty pas</li>
     */
    private double x; 
    /**Druga współrzędna*/
    private double y;
    /**Prędkość przeszkody*/
    private double v=1.;
    protected Image obrazek;
    protected ImageIcon ikona;
    static private Boolean kolizja=false;
    Random rand = new Random();
    
    /** Konstruktor przeszkód
     * @param   pas Zmienna określająca pas przeszkody (1-4)
     * @param   nrAuta Numer pojazdu z zakresu 1-10
     * @see     #kolizja
     * @see     #getX()
     * @see     #setX(double x)
     * @see     #getY()
     * @see     #setY(double y)
     */
    public CPrzeszkoda(int pas,int nrAuta) {
        switch(pas){  //ustawianie pozycji x przeszkody
            case 1: this.x=25; break;
            case 2: this.x=105; break;
            case 3: this.x=205; break;
            case 4: this.x=285; break;
            default: break;
        }
        if (pas < 3) {
            resetL(); //Ustawianie pozycji
            this.ikona = new ImageIcon("src/images/Vehicles/Rotated/rcar"+nrAuta+".png");//grafika przeszkody dla lewego pasa
        } 
        else {
            resetP(); //Ustawianie pozycji
            this.ikona = new ImageIcon("src/images/Vehicles/v"+nrAuta+".png");
        }
        obrazek=ikona.getImage();
    }
    
    /** @return  współrzędna x przeszkody*/
    public double getX() {
        return x;
    }
    /** @return  współrzędna y przeszkody*/
    public double getY() {
        return y;
       
    }
    
    /** Ustawia pierwszą współrzędną przeszkody
     * @param x pierwsza współrzędna*/
    public void setX(double x) {
        this.x = x;
    }
    /** Ustawia drugą współrzędną przeszkody
     * @param y druga współrzędna*/
    public void setY(double y) {
        this.y = y;
    }
    /** @return Zmienna określająca szybkość przeszkody*/
    public double getV() {
        return v;
    }
    /** Ustawia szybkość przeszkody
     * @param y szybkość przeszkody*/
    public void setV(double v) {
        this.v = v;
    }
    
    
 
    /** Ustawia grafike auta jeżdżącego po prawym pasie 
     @param strona Lewy(L)/prawy(P) pas**/
    public void setAuto(int nrAuta, char strona){ 
        if (strona=='L') 
            this.ikona = new ImageIcon("src/images/Vehicles/Rotated/rcar"+nrAuta+".png");
        else 
            this.ikona = new ImageIcon("src/images/Vehicles/v"+nrAuta+".png");
        this.obrazek=this.ikona.getImage();
    }
   /**Resetuje pozycje pojazdu jeżdżącego po lewym pasie**/
    public void resetL(){ 
        this.v=rand.nextDouble()+1;
        this.y=rand.nextInt(500)-600;
        
    }
    /**Resetuje pozycje pojazdu jeżdżącego po prawym pasie**/
    public void resetP(){  
        this.v=rand.nextDouble()+0.7;
        this.y=rand.nextInt(500)+500;
    }
    /**Sprawdzanie czy nie ma kolizji miedzy naszym obiekte a jakas przeszkodą**/
    public void sprawdzKolizje(){ 
                                                                                    //współrzędna X
        if ( ( ( (CDodger.x - (int)this.x <= 0) && (CDodger.x - (int)this.x >-CDodger.width+3) )  || //gdy nasz pojazd jest po lewej
               ( (CDodger.x - (int)this.x > 0) && (CDodger.x - (int)this.x < 55  ) )   )            //gdy nasz pojadd jest po prawej (60 - szer. przeszkody)
                                                                                    //współrzędna Y
            &&( ((CDodger.y - (int)this.y <0) && (CDodger.y - (int)this.y >-CDodger.height+5)    || //gdy nasz pojazd jest u góry
               ( (CDodger.y - (int)this.y >0) && (CDodger.y - (int)this.y <95)   ) ) ) )            //  gdy nasz pojazd jest u dołu (100 - wysokość przeszkody)
                kolizja=true;
        
    }
    /**Zwraca informacje czy zaszła kolizja**/
    public static Boolean getKolizja(){ //zwraca informacje czy zaszła kolizja
        return kolizja;
    }
    /**Ustawia informacje o kolizjii (true/false)**/
    public static void setKolizja(Boolean bool){ 
        kolizja=bool;
    }
    
}

