/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thedodger.main;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

/**
 Klasa tworząca licznik w postaci JLabel.
 Wyświetla w sekundach czas aktualnej rozgrywki.
 */
public class CLicznikLabel extends JLabel{
    
    Font fontCzas=new Font("Arial", Font.BOLD | Font.ITALIC, 26);
    int czasL; //pomocnicza
    String czas1,czas2;
    static Timer SimpleTimer;
    static String sekundy;
    public CLicznikLabel(){
        //wczytanie obrazka
        SimpleTimer = new Timer(1000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
                
                
                //czasL=CLayout.dajCzas();
                czas1=String.valueOf((int)CLayout.czasStart);
                sekundy=String.valueOf((int)(System.currentTimeMillis()/1000)-CLayout.dajCzas());
                setText("Czas:" + sekundy);
                setFont(fontCzas);
                
            }
            
        });
        SimpleTimer.start();
       
            
        }
        
        public static String getSekundy(){
            return sekundy;
        }
    }
    
    
    
            
            
    

