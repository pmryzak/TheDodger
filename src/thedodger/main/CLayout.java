package thedodger.main;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;

import java.awt.Image;
import java.util.List;
import javax.swing.ImageIcon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Klasa główna. To na niej użytkownik się porusza. Bazuje na Card Layout
 *
 * @author Patryk Chmiel
 * @version v0.1
 *
 */
public class CLayout {
    
    Font fontLogo = new Font("Arial Black", Font.BOLD | Font.ITALIC, 40);
    
    ImageIcon bIcon = new ImageIcon("src/images/button.png");
    
    static JFrame okno = new JFrame("Dodger The Game");
    JLabel tlo = new JLabel();
    static JLabel droga1 = new CTloGry("drogaStop.png"); 
    static JLabel droga2 = new CTloGry("droga.gif");
    static JLabel droga3 = new CTloGry("droga.png");
    
    
    JLabel tytul1 = new JLabel("DODGER", JLabel.CENTER);
    JLabel tytul2 = new JLabel("THE GAME", JLabel.CENTER);
    
    static JPanel panelCont = new JPanel(); //static (i przy oknie) by zmieniać karty poza klasą
    JPanel panelMenu = new JPanel();
    static JPanel panelGra = new CRozgrywka();
    JPanel panelUst = new CUstawienia();
    JLabel labelHeader = new CHeader();
    
    JButton bNowa = new JButton("Nowa gra");
    JButton bOpcje = new JButton("Ustawienia");
    JButton bInfo = new JButton("O grze");
    JButton bKoniec = new JButton("Zakończ");
    
    Thread t1;
    
    JButton bMenu = new JButton(new ImageIcon("src/images/buttonMenu.png"));
    JButton bStart = new JButton(new ImageIcon("src/images/buttonStart.png"));
    JLabel timer1 = new CLicznikLabel();
    
    static CardLayout cl = new CardLayout(); // Tworzenie Layoutu

    public static int czasStart;
    public static Boolean rozpoczeto=false;
    
    public CLayout() {
        //ImageIcon newDroga = new ImageIcon("src/images/droga.gif");
        okno.setSize(300, 400);
        okno.setResizable(false);
        okno.setLocationRelativeTo(null);
        
        final CReader czyt = new CReader();
        
        tlo.setBounds(0, 0, 300, 400);
        panelCont.setLayout(cl); //layout
        
        Thread t1 = new Thread((Runnable) panelGra);       //uruchamianie wątku 
        t1.start();
        
        tlo.setIcon(new ImageIcon(new ImageIcon("src/images/background.png").getImage().getScaledInstance(300, 400, Image.SCALE_SMOOTH)));
        
        ActionListener StartListener = new ActionListener() {   //Działanie przy kliknięciu przycisku start
            @Override
            public void actionPerformed(ActionEvent arg0) {
                labelHeader.add(timer1);
                czasStart = (int) (System.currentTimeMillis() / 1000);
                panelGra.addKeyListener(czyt);
                
                t1.resume();
                //zmiana tła na gifa {
                zmianaTla(2); // }
                //panelGra.remove(droga1);
               
                panelGra.removeKeyListener(czyt);
                panelGra.requestFocusInWindow();
                bStart.setEnabled(false);
                
                timer1.setBounds(140, 0, 200, 35);
                
                rozpoczeto=true;
            }
        }; //do przycisku Start
        bStart.addActionListener(StartListener);
        
        droga1.setBounds(0, 35, 370, 450);
        droga2.setBounds(0, 35, 370, 450);
        droga3.setBounds(0, 35, 370, 450);
        
        
        panelGra.add(droga1);
        panelMenu.setLayout(null);

        /**
         * Przyciski w menu i ich działanie
         */
        bNowa.setBounds(100, 100, 100, 40);
        bOpcje.setBounds(100, 160, 100, 40);
        bInfo.setBounds(100, 220, 100, 40);
        bKoniec.setBounds(100, 280, 100, 40);
        
        
        bNowa.addActionListener(new ActionListener() { //przycisk Nowa Gra
            @Override
            public void actionPerformed(ActionEvent arg0) {
                czasStart = (int) (System.currentTimeMillis() / 1000);
                cl.removeLayoutComponent(okno);
                okno.setSize(375, 520); //wielkosc okna gry
                
                panelGra.revalidate(); //odswiezenie gry
                cl.show(panelCont, "2"); //przelaczenie na zakladke rozgrywki
                //okno.setSize(370,500);

            }
        });
        
         bOpcje.addActionListener(new ActionListener() { //przycisk Ustawienia
            @Override
            public void actionPerformed(ActionEvent arg0) {
                cl.removeLayoutComponent(okno);
                cl.show(panelCont, "3");
            }
        });
         
        bKoniec.addActionListener(new ActionListener() { //przycisk Koniec
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
                //panelUst.setEnabled(false);
            }
        });
        bMenu.addActionListener(new ActionListener() { //działanie Menu
            @Override
            public void actionPerformed(ActionEvent arg0) {
                okno.setSize(300, 400);
                CDodger.defXY();
                cl.removeLayoutComponent(okno);
                cl.show(panelCont, "1");
                Thread.currentThread().interrupt();
                bStart.setEnabled(true);
                
                zmianaTla(1);
                labelHeader.remove(timer1);
                //bStart.removeAll();
                //panelGra.removeKeyListener(czyt);
                
                rozpoczeto=false;
                CPrzeszkoda.setKolizja(false);
                
                for(int i=0;i<4;i++) CRozgrywka.Przeszkody.get(i).reset();
            }
        });
        /**
         * Ustawianie menu
         */
        tytul1.setBounds(0, 0, 300, 50);
        tytul2.setBounds(0, 40, 300, 50);
        tytul1.setFont(fontLogo);
        tytul2.setFont(fontLogo);
        tytul1.setForeground(Color.RED);
        tytul2.setForeground(Color.RED);
        
        panelMenu.add(tytul1);
        panelMenu.add(tytul2);
        panelMenu.add(bNowa);
        panelMenu.add(bOpcje);
        panelMenu.add(bInfo);
        panelMenu.add(bKoniec);
        panelMenu.add(tlo);
        panelCont.add(panelMenu, "1");
        
        cl.show(panelCont, "1"); //karty layoutu
        panelCont.add(panelGra, "2");
        panelCont.add(panelUst, "3"); 
        
        bStart.setBounds(10, 8, 75, 25);
        bStart.setFocusable(false);
        bMenu.setBounds(280, 8, 75, 25);
        bMenu.setFocusable(false);
        labelHeader.setBounds(0, 0, 400, 35);
        
        timer1.setBounds(0, 40, 200, 0);
        timer1.setForeground(Color.WHITE);
        //timer1.setText("Czas:0");

        labelHeader.add(timer1);
        timer1.setText("Czas:0");
        
        panelGra.setLayout(null);
        panelGra.add(bMenu);
        panelGra.add(bStart);
        panelGra.requestFocusInWindow();
        panelGra.setFocusable(true);
        panelGra.add(labelHeader);
        czasStart = (int) (System.currentTimeMillis() / 1000);
        
        
        bStart.addActionListener(StartListener);
        
        
        cl.show(panelCont, "1"); //karty layoutu
        panelCont.add(panelGra, "2");
        panelCont.add(panelUst, "3"); 
        okno.add(panelCont);
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //okno.pack();
        okno.setVisible(true);
    }
    public static void zmianaTla(int droga){
        
        switch(droga){
            case 1: panelGra.remove(droga1); panelGra.remove(droga2); panelGra.remove(droga3);  panelGra.add(droga1);break;
            case 2: panelGra.remove(droga1); panelGra.remove(droga2); panelGra.remove(droga3);  panelGra.add(droga2);break;
            case 3: panelGra.remove(droga1); panelGra.remove(droga2); panelGra.remove(droga3);  panelGra.add(droga3);break;
            default: break;
        }
    }
    public static int dajCzas() {
        return czasStart;
    }
   public static void zmianaKarty(String karta){
       cl.removeLayoutComponent(okno);
       cl.show(panelCont, karta);
   }
    public static void main(String[] args) {
        CLayout clay = new CLayout();
       
        
    }
}
