/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thedodger.main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Patryk
 */
public class CUstawienia extends JPanel{
    
    JButton bPrev = new JButton("Poprzedni");
    JButton bNext = new JButton("Następny");
    JButton bBack = new JButton("Powrót");
    
    public CUstawienia(){
        setSize(300,400);
        setVisible(true);
        setLayout(null);
        bPrev.setBounds(0,0,150,40);
        bNext.setBounds(150,0,150,40);
        bBack.setBounds(-2,327,300,40);
       
        this.add(bPrev);
        this.add(bNext);
        this.add(bBack);
        
        bPrev.addActionListener(new ActionListener() { //przycisk Poprzedni
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (nr>1) nr--; //zmiana nr auta
                ikona=new ImageIcon("src/images/car"+nr+".png");
            }
        });
        bNext.addActionListener(new ActionListener() { //przycisk Następny
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (nr<8) nr++; //zmiana nr auta
                ikona=new ImageIcon("src/images/car"+nr+".png");
            }
        });
        
        bBack.addActionListener(new ActionListener() { //przycisk Powrót(do menu)
            @Override
            public void actionPerformed(ActionEvent arg0) {
                CLayout.zmianaKarty("1");
                CRozgrywka.ZmianaAuta(nr);
            }
        });
        
        //Image auto = ikona.getImage();
    }
    private int nr=5;
    private Image dbImage;
    private Graphics dbg; //podwójne buforowanie
    //Image auto = ikona.getImage();
    ImageIcon ikona=new ImageIcon("src/images/car"+nr+".png");
    static Image obrazek;
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        dbImage=createImage(getWidth(),getHeight());
        dbg=dbImage.getGraphics();
        paintComponent(dbg);
        g.drawImage(dbImage,0,0,this);
        //g.drawImage(tlo,0,0,this);
        repaint();
        
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponents(g);
        
        
      // yourImage.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);
        ikona.getImage().getScaledInstance(120,200, Image.SCALE_DEFAULT);
        //g.drawImage(ikona.,50,50,this);
        g.drawImage(ikona.getImage(),70,45,160,270,this);
        
        
       
        
        
        
        
        //g.drawOval(100, 100, 50, 50);
       //super.paint(g);
        repaint();
    }
    
}
