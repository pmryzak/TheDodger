/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thedodger.main;


import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *  Coin to klasa opisująca monetę, którą należy zbierać podczas gry.
 *  Zawiera informacje o położeniu, ile razy została zebrana oraz o jej grafice.
 *  
 *  @see sprawdz()
 *
 */
public class Coin extends JPanel{
    static private int x=-50,y=0;
    static private int ilosc=0;
    static ImageIcon ikona = new ImageIcon("src/images/coin.gif");
    static Random rand = new Random();
    static private Boolean zebrane=false; 
   
    
    public static void reset(){
        
        x=rand.nextInt(350)+10;
        y=rand.nextInt(420)+50;
       
        zebrane=false;
    }
    /** Metoda sprawdzająca, czy moneta została zebrana.
     *  Jeżeli została zebrana to <ul>
     * <li>ustawia zmienną informującą o zebraniu na wartość true</li>
     * <li> Zwiększa ilość zebranych monet o 1</li>
     * <li> Usuwa(przesuwa poza ekran) monetę</li></ul>
     */
    public static void sprawdz(){
                                                                                     //Położenie względen naszego pojazdu//
       if (( ((CDodger.x - x < 0) && (CDodger.x - x >-CDodger.width)) ||        //Jeżeli moneta jest po prawej stronie
               (CDodger.x - x > 0) && (CDodger.x - x < 20))                     //Jeżeli moneta jest po lewej stronie 
           && ( ((CDodger.y - y < 0) && (CDodger.y - y >-CDodger.height)) ||    //Jeżeli moneta jest od góry
               (CDodger.y - y > 0) && (CDodger.y - y < 24))                     //Jeżeli moneta jest od dołu
               )
       {
           
           zebrane=true;
           ilosc++;
           remove();
           
       }
    }
    /**@return Informacja o zebranej monecie (true/false)*/
    public static Boolean getZebrane(){
        
        return zebrane;
    }
    /**@return Pierwsza współrzędna monety*/
    
    public static int getx() {
        return x;
    }
    /**@return druga współrzędna monety */
    public static int gety() {
        return y;
    }
    /**@return Ilość zebranych monet do momentu wywołania metody*/
    public static int getIlosc(){
        return ilosc;
    }
    /** Zeruje ilosc zebranych monet (i.e. przy rozpoczęciu nowej gry) */
    public static void resetIlosc(){
        ilosc=0;
    }
    /** Umieszcza monetę poza ekranem (gdy zostanie zebrana)*/
    public static void remove(){
        x=-50;
        y=-50;
    }
    /** @return Grafika monety */
    public static ImageIcon getIkona(){
        return ikona;
    }

    
}

