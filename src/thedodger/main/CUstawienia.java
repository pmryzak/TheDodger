/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thedodger.main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Klasa panelu ustawień
 * Umożliwia zmianę pojazdu, którym sterujemy
 * @author Patryk
 */
public class CUstawienia extends JPanel{
    
    //       Dodawanie przycisków
    JButton bPrev = new JButton("Poprzedni");
    JButton bNext = new JButton("Następny");
    JButton bBack = new JButton("Powrót");
    
    /** Konstruktor zakładki ustawień */
    public CUstawienia(){
        setSize(300,400);
        setVisible(true);
        setLayout(null);
        bPrev.setBounds(0,0,150,40);
        bNext.setBounds(150,0,150,40);
        bBack.setBounds(-2,332,300,40);
       
        this.add(bPrev);
        this.add(bNext);
        this.add(bBack);
        
        //Działanie przycisku "Poprzedni"
        bPrev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (nr>1) nr--;                                                 //Zmiana numeru grafiki wyświetlanego auta
                ikona=new ImageIcon("src/images/vehicles/v"+nr+".png");         //Aktualizacja grafiki
            }
        });
        //Działanie przycisku "Następny"
        bNext.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (nr<9) nr++;                                                //Zmiana numeru grafiki wyświetlanego auta
                ikona=new ImageIcon("src/images/vehicles/v"+nr+".png");         //Aktualizacja grafiki
            }
        });
        //Działanie przycisku "Powrót"
        bBack.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent arg0) {
                CLayout.zmianaKarty("1");                                       //Zmiana karty layoutu na menu
                CRozgrywka.ZmianaAuta(nr);                                      //Zmiana grafiki pojazdu, którym gramy.
                 if (nr<9) { CDodger.width=60; CDodger.height=100; }            //Zmiana informacji o wymiarach naszego pojazdu
                 
                 else {CDodger.width=50; CDodger.height=100;}
                
            }
        });
        
       
    }
    private int nr=5;                                                           //Numer grafiki wyświetlanego pojazdu
    private Image dbImage;
    private Graphics dbg; //Podwójne buforowanie
  
    ImageIcon ikona=new ImageIcon("src/images/Vehicles/v"+nr+".png");
    static Image obrazek;
    /**Metoda rysująca */
    @Override
    public void paint(Graphics g){
        super.paint(g);
        dbImage=createImage(getWidth(),getHeight());
        dbg=dbImage.getGraphics();
        paintComponent(dbg);
        g.drawImage(dbImage,0,0,this);
        repaint();
        
    }
  /** 
    *Metoda rysująca modele pojazdów do sterowania
    *   Zależnie od rozmiarów pojazdu, grafika jest inaczej skalowana
    **/
    @Override
    public void paintComponent(Graphics g){
        super.paintComponents(g);

        if (nr < 9 )    g.drawImage(ikona.getImage(),70,45,160,270,this);
        else            g.drawImage(ikona.getImage(),80,45,140,275,this);
        
        repaint();
    }
    
}
