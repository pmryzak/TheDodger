/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thedodger.main;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

/**
 * Klasa na której rozgrywa się gra.
 
 * @author Patryk
 */
public class CRozgrywka extends JPanel implements Runnable{
    /** 
     *Link to a method
     * 
     */
    private int Width;
    private int Height;
    
    static private int numerAuta=5;
    
    Font font=new Font("Arial", Font.BOLD | Font.ITALIC, 30); //czcionka
    
    private Image dbImage; //podwójne buforowanie
    private Graphics dbg; 
    
    Image auto1; //60x100
    
    int czasL; //pomocnicza
    String czas1,czas2; //do odliczania
    
  
    //Rozgrywka gra;
    ///Obrazki
    static ImageIcon ikona=new ImageIcon("src/images/car"+numerAuta+".png");
    ImageIcon plomien=new ImageIcon("src/images/plomien.png");
    ImageIcon koniecgry=new ImageIcon("src/images/drogaKoniec.png");
    ImageIcon header = new ImageIcon("src/images/header.png");
    ///
    static List<CPrzeszkoda> Przeszkody = new ArrayList<CPrzeszkoda>(); //lista z przeszkodami
    public CPrzeszkoda getPrzeszkoda(int nr){
        return Przeszkody.get(nr);
    }
    
    public CRozgrywka() {
        
        Width=370;
        Height=400;
        setBorder(BorderFactory.createLineBorder(Color.black));
        
        addKeyListener(new CReader());
        requestFocus();
        
        //Dodawanie przeszkód do listy
        CPrzeszkoda prz1 = new CPrzeszkoda(1,2);
        CPrzeszkoda prz2 = new CPrzeszkoda(2,3);
        CPrzeszkoda prz3 = new CPrzeszkoda(3,4);
        CPrzeszkoda prz4 = new CPrzeszkoda(4,6);
        Przeszkody.add(prz1);
        Przeszkody.add(prz2);
        Przeszkody.add(prz3);
        Przeszkody.add(prz4);
        
        
 
        
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        dbImage=createImage(getWidth(),getHeight());
        dbg=dbImage.getGraphics();
        paintComponent(dbg);
        g.drawImage(dbImage,0,0,this);
        
        repaint();
        
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponents(g);
        g.drawImage(ikona.getImage(),(int)CDodger.x,(int)CDodger.y,this);
        //g.drawImage(auto2,285,30,this);
        g.drawImage(Przeszkody.get(0).obrazek,Przeszkody.get(0).x,Przeszkody.get(0).y,this);
        g.drawImage(Przeszkody.get(1).obrazek,Przeszkody.get(1).x,Przeszkody.get(1).y,this);
        g.drawImage(Przeszkody.get(2).obrazek,Przeszkody.get(2).x,Przeszkody.get(2).y,this);
        g.drawImage(Przeszkody.get(3).obrazek,Przeszkody.get(3).x,Przeszkody.get(3).y,this);
        if (CLayout.rozpoczeto) g.drawImage(header.getImage(),0,0,this); //roboczo nagłówek
        if (CPrzeszkoda.getKolizja()) {
            g.drawImage(plomien.getImage(),(int)CDodger.x+15,(int)CDodger.y-5,this);
            g.drawImage(koniecgry.getImage(),0,35,this);
            repaint();
        }
        
        //g.drawOval(100, 100, 50, 50);
       //super.paint(g);
        repaint();
    }
    
        Random rand = new Random(); //do losowania nowego położenia
        
    public static void ZmianaAuta(int nr){
        ikona=new ImageIcon("src/images/car"+nr+".png");
    }
    @Override
    public void run(){
        
        try{
            while(!Thread.currentThread().isInterrupted() ){
                           
                            if(CLayout.rozpoczeto && !CPrzeszkoda.getKolizja()) { //jeżeli gra się zaczęła i nie ma kolizji
                                CDodger.move();
                                for(int i=0;i<4;i++) {
                                    Przeszkody.get(i).y++;   
                                    if(Przeszkody.get(i).y>500) {
                                        Przeszkody.get(i).y=rand.nextInt(400)-460; //ustaw losowo następna pozycję przeszkody
                                        Przeszkody.get(i).setAuto(rand.nextInt(8)+1); //ustaw losowo kolor przeszkody
                                    }
                                    Przeszkody.get(i).sprawdzKolizje(); //sprawdz czy nie ma kolizji
                                }
                                if(CPrzeszkoda.getKolizja()) {
                                    CLayout.zmianaTla(3);
                                    
                                }
                           }
                              
                               
                               
                Thread.sleep(5); 

            }
        }
        catch(Exception e){
            System.out.println("Error");
        }
    }

    /**
     *
     */
   

    

   
    
   

    
   
    
}

