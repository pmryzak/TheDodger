/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thedodger.main;

/**
 *
 * Klasa opisująca obiekt, którym się poruszamy.
 */
public class CDodger{
    static public double x=200,y=300;
    static public int xDirection,yDirection;

//    
    public static void setXDir(int xdir){
        xDirection=xdir;
    }
    public static void setYDir(int ydir){
        yDirection=ydir;
    } 
  /***/
    public static void move(){
        x+=xDirection;
        if(yDirection==1) y+=0.8; //do tyłu wolniej
        else y+=yDirection;
        if(x<=0)
            x=0;
        if(x>=370-60) //winX
            x=370-60;
        if(y<=35) //ramka
           y=35;
        if(y>=380)
            y=380;
    }
    public static void defXY(){
        x=200;
        y=300;
    }
    
    
   

   

   
}
