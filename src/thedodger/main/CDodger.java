/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thedodger.main;

/**
 *
 * Klasa opisująca obiekt, którym się poruszamy.
 * Zawiera informacje o położeniu i to z jej poziomu
 * poruszamy się pojazdem zmieniając współrzędne położenia.
 * 
 */
public class CDodger{
    /** Współrzędna położenia pojazdu 
     <li>x - w poziomie</li>
     <li>y - w pionie</li>
     **/
    static public double x=105,y=300;
    
    /** Zmienna opisująca kierunek pojazdu<ul>
     * <li>xDirection - w poziomie</li>
     * <li>yDirection - w pionie</li></ul>
     **/
    static public int xDirection,yDirection;
    
    /** <b>width</b> - szerokość pojazdu <p> <b>height</b> - wysokość pojazdu **/
    static public int width=60,height=100;
    
    /** Zmienia zwrot (w poziomie)pojazdu 
     @param xdir zwrot (-1,0,1): <ul>
     <li>-1- w lewo </li>
     <li> 0 - zatrzymanie w poziomie </li>
     <li> 1 - w prawo </li></ul> **/
    public static void setXDir(int xdir){
        xDirection=xdir;
    }
    /** Zmienia zwrot (w pionie) pojazdu 
     @param ydir zwrot (-1,0,1): <ul>
     <li>-1- w górę </li>
     <li> 0 - zatrzymanie w pionie </li>
     <li> 1 - w dół </li></ul> **/
    public static void setYDir(int ydir){
        yDirection=ydir;
        
    } 
    /** Metoda, która umożliwia zmiane położenia naszego pojazdu **/
    public static void move(){
        
        x+=xDirection;
        if(yDirection==1) y+=0.7;                                               //Do tyłu pojazd porusza się wolniej
        else y+=yDirection;
        if(x<=0)                                                                //Blokada ruchu od lewej
            x=0;
        if(x>=370-CDodger.width)                                                //Blokada ruchuod prawej
            x=370-CDodger.width ;
        if(y<=35)                                                               //Blokada ruchu od góry
           y=35;
        if(y>=480-CDodger.height)                                               //Blokada ruchu od dołu
            y=480-CDodger.height;
    }
    /**Metoda ustawiająca współrzędne naszego pojazdu na domyślne**/
    public static void defXY(){
        x=105;
        y=300;
    }
    
}
    
   

   

   

