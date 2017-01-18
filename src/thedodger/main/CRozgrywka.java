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
 * <h1>Zmienne</h1><ul>
 * <li>Width        Szerokość planszy</li>
 * <li>Height       Wysokość planszy</li>
 * <li>numerAuta    Numer pojazdu którym sterujemy</li>
 * <li>ikona        Grafika naszego pojazdu</li>
 * <li>plomien      Grafika płomienia pojawiającego się po zderzeniu</li>
 * <li>koniecgry    Grafika z informacją o końcu gry</li>
 * <li>header Grafika nagłówka w oknie rozgrywki</li>
 * <li>Przeszkody Lista z objektami przeszkód które omijamy</li></ul>
 * @
 * 
 */
public class CRozgrywka extends JPanel implements Runnable{

    static private int numerAuta=5;
    
    Font font=new Font("Arial", Font.BOLD | Font.ITALIC, 30); //czcionka
    
    
    private Image dbImage; //podwójne buforowanie
    private Graphics dbg; 
    Random rand = new Random();                                                 //Zmienna losowa do losowania położenia

    ///Obrazki
    static ImageIcon ikona=new ImageIcon("src/images/vehicles/v"+numerAuta+".png");
    ImageIcon plomien=new ImageIcon("src/images/plomien.png");
    ImageIcon koniecgry=new ImageIcon("src/images/drogaKoniec.png");
    ImageIcon header = new ImageIcon("src/images/header.png");
    
    ///
    static List<CPrzeszkoda> Przeszkody = new ArrayList<CPrzeszkoda>(); //lista z przeszkodami
    /** Metoda zwracająca obiekt przeszkody
     * @param nr
     * @return  */
    public static CPrzeszkoda getPrzeszkoda(int nr){
        return Przeszkody.get(nr);
        
    }
    
    public CRozgrywka() {
        //CSound.sound1.loop();
       //Width=370;
        //Height=400;
        //setBorder(BorderFactory.createLineBorder(Color.black));
        
        addKeyListener(new CReader());
        requestFocus();
        
        //Dodawanie przeszkód do listy
        CPrzeszkoda prz1 = new CPrzeszkoda(1,2);CPrzeszkoda prz3 = new CPrzeszkoda(3,5);
        CPrzeszkoda prz2 = new CPrzeszkoda(2,3);CPrzeszkoda prz4 = new CPrzeszkoda(4,6);

        Przeszkody.add(prz1);Przeszkody.add(prz2);
        Przeszkody.add(prz3);Przeszkody.add(prz4);
       
        
        
        
 
        
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
    /** Metoda rysująca wszystko, co pojawia się podczas rozgrywki
     *
     
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponents(g);
        g.setFont(new Font("Arial", Font.BOLD , 26));                           //Styl czcionki
        g.setColor(Color.WHITE);                                                //Kolor czcionki                                  
        g.drawImage(ikona.getImage(),(int)CDodger.x,(int)CDodger.y,this);       //Rysowanie naszego pojazdu
        
        //Rysowanie przeszkód z listy
        g.drawImage(getPrzeszkoda(0).obrazek,(int)getPrzeszkoda(0).getX(),(int)getPrzeszkoda(0).getY(),this);
        g.drawImage(getPrzeszkoda(1).obrazek,(int)getPrzeszkoda(1).getX(),(int)getPrzeszkoda(1).getY(),this);
        g.drawImage(getPrzeszkoda(2).obrazek,(int)getPrzeszkoda(2).getX(),(int)getPrzeszkoda(2).getY(),this);
        g.drawImage(getPrzeszkoda(3).obrazek,(int)getPrzeszkoda(3).getX(),(int)getPrzeszkoda(3).getY(),this);
        
        if (CLayout.rozpoczeto) {                                               //Jeżeli gra trwa
            g.drawImage(header.getImage(),0,0,this);                            //rysowany jest nagłówek z czasem
        } 
        if (CPrzeszkoda.getKolizja()) {                                              //Jeżeli uderzymy w przeszkodę 
            g.drawImage(plomien.getImage(),(int)CDodger.x+15,(int)CDodger.y-5,this); //grafika płomienia
            g.drawImage(koniecgry.getImage(),0,35,this);                             //informacja o końcu gry
            repaint();
        }
        g.drawImage(Coin.getIkona().getImage(),120,8,this);                     //Grafika monety w nagłówku
        g.drawString("x "+String.valueOf(Coin.getIlosc()), 150,28);             //Napis z informacją o ilości monet
        
        if(!Coin.getZebrane())                                                  //Jeżeli moneta nie jest zebrana:
            g.drawImage(Coin.ikona.getImage(),Coin.getx(),Coin.gety(),this);    //Rysowanie monety 
      
    //super.paint(g);
       repaint();
    }
    
        
       
    /** Metoda pozwalająca zmienić pojazd, którym sterujemy
     * 
     * @param nr Numer grafiki auta
     */
    public static void ZmianaAuta(int nr){ 
        numerAuta=nr;
        ikona=new ImageIcon("src/images/vehicles/v"+nr+".png");
       
    }
    /**
     * Metoda zwracająca informacje o aktualnym pojeździe
     * @return Numer pojazdu
     */
    public static int getNrAuta(){
        return numerAuta;
    }
  
    
    
    
    @Override
    public void run(){
        
        try{
            
            while(!Thread.currentThread().isInterrupted() ){                    //Jeżeli działanie wątku nie zostało przerwane
                           
                if(CLayout.rozpoczeto && !CPrzeszkoda.getKolizja()) {           //Jeżeli gra się zaczęła i nie ma kolizji
                    CDodger.move();                                             //Poruszanie się      
                    for(int i=0;i<2;i++) {                                      //Działanie dla przeszkód na lewym pasie
                        getPrzeszkoda(i).setY(getPrzeszkoda(i).getY()+getPrzeszkoda(i).getV()); //Ruch przeszkody o 'v'
                        if(getPrzeszkoda(i).getY()>500) {                       //Gdy pojazd zniknie z ekranu
                            getPrzeszkoda(i).resetL();                          //Ustawienie losowo następnej pozycji przeszkody
                            getPrzeszkoda(i).setAuto(rand.nextInt(8)+1,'L');    //Ustawienie losowo koloru przeszkody
                        }
                        getPrzeszkoda(i).sprawdzKolizje();                      //Sprawdzanie czy nie ma kolizji
                    }
                    for(int i=2;i<4;i++) {                                      //Działanie dla przeszkód na prawym pasie
                        getPrzeszkoda(i).setY(getPrzeszkoda(i).getY()-getPrzeszkoda(i).getV());  //Ruch o 'v' przeszkody
                        if(getPrzeszkoda(i).getY()<-100) {                      //Gdy pojazd zniknie z ekranu
                            getPrzeszkoda(i).resetP();                          //Ustawienie losowo następnej pozycji przeszkody
                            getPrzeszkoda(i).setAuto(rand.nextInt(8)+1,'P');    //Ustawienie losowo koloru przeszkody
                            //ustaw losowo kolor przeszkody
                           
                        }
                        getPrzeszkoda(i).sprawdzKolizje();                     //sprawdz czy nie ma kolizji
                    }
                    
                    
                    
                    if(CPrzeszkoda.getKolizja()) {                              //Jeżeli gra się zatrzymuje:
                        CLayout.zmianaTla(3);                                   //Zmień tło
                        Coin.remove();                                          //Usuń grafikę monety
                        
                      }
                    Coin.sprawdz();                                             
                    
                    if ((System.currentTimeMillis()/5)%1000==0) Coin.reset(); //Co 5 sekund nowa pozycja monety
                   }             
                Thread.sleep(5);                                                //Odświeżanie wątku co 5ms
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

