/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thedodger.main;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 *Klasa Menu, obecnie nieużywana.
 */
public class CMenu extends JPanel implements ActionListener{
   Font FontNazwa=new Font("Arial", Font.BOLD | Font.ITALIC, 40);
   JButton bStart,bOpcje,bInformacje,bKoniec; 
   //Boolean started=false;
   
   public CMenu(){
        //setTitle("Dodger - Menu");
        setSize(300,400);
        //setResizable(false);
        setVisible(true);
       
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        setLayout(null);
        JLabel gameName= new JLabel("The Dodger",SwingConstants.CENTER);
            gameName.setBounds(0,0,300,100);
       
            gameName.setFont(FontNazwa);
            add(gameName);
            
        bStart= new JButton("Nowa ");
            bStart.setBounds(100,100,100,40);
            add(bStart);
        
        bOpcje=new JButton("Ustawienia");
            bOpcje.setBounds(100,160,100,40);
            add(bOpcje);
        
        bInformacje=new JButton("O grze");
            bInformacje.setBounds(100,220,100,40);
            add(bInformacje);    
        bKoniec=new JButton("Zakończ");
            bKoniec.setBounds(100,280,100,40);
            add(bKoniec);
            
        bStart.addActionListener(this);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
   
   
    
}
