package thedodger.main;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;

import java.awt.Image;
import javax.swing.ImageIcon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * <h1>The Dodger</h1>
 * Gracz porusza się obiektem (autem), którego celem jest zebranie jak najwięszej
 * ilości monet. <p>
 * <h2>Podstawowe informacje</h2>
 * <li> Gra kończy się wraz z uderzeniem w przeszkode(inny pojazd).</li>
 * <li> Przeszkody mają różne prędkości.</li>
 * <li> Czas na zebranie monety to 5 sekund. Po tym czasie pojawia się w innym miejscu</li>
 * <li> Gracz ma możliwość zmiany wyglądu pojazdu (Menu>Ustawienia)</li>
 * 
 * 
 * Klasa główna. W niej umieszczony jest cały interfejs gry. Bazuje na Card Layout.
 * 
 * Domyślna plansza to menu, na której znajdują się cztery przyciski z następującymi działaniami:
 * <li><b>Nowa gra</b> - Otwiera się karta z planszą do gry</li>
 * <li><b>Ustawienia</b> - Otwiera się karta, na której możemy zmienić nasz pojazd</li>
 * <li><b>O grze</b> - Otwiera się karta z informacjami o grze, a także instrukcja</li>
 * <li><b>Zakońćż</b> - Następuję całkowite wyłączenie gry</li>
 * 
 * @author Patryk Chmiel
 * @since 2016-11-11
 * @version v1.0
 *
 */
public class CLayout {
    
    Font fontLogo = new Font("Arial Black", Font.BOLD | Font.ITALIC, 40);
    
    
    /**Okno całej gry**/
    static JFrame okno = new JFrame("Dodger The Game");
    
    JLabel tlo = new JLabel();
    
    /*Grafiki drogi podczas rozgrywki*/
    static JLabel droga1 = new CTloGry("drogaStop.png"); 
    static JLabel droga2 = new CTloGry("droga.gif");
    static JLabel droga3 = new CTloGry("droga.png");
    
    

    
    static JPanel panelLayout = new JPanel(); //static (i przy oknie) by zmieniać karty poza klasą
    static JPanel panelGra = new CRozgrywka();
    static JPanel panelMenu = new JPanel();
    static JPanel panelUst = new CUstawienia();
    
    JLabel labelHeader = new CHeader();
    
    JLabel logo = new JLabel();
    /**Przycisk "Nowa Gra" w menu*/
    JButton bNowa = new JButton("Nowa gra");
    /**Przycisk "Ustawienia" w menu*/
    JButton bOpcje = new JButton("Ustawienia");
    /**Przycisk "O grze" w menu*/
    JButton bInfo = new JButton("O grze");
    /**Przycisk "Zakończ" w menu*/
    JButton bKoniec = new JButton("Zakończ");
    
    Thread t1;
    
    JButton bMenu = new JButton(new ImageIcon("src/images/buttonMenu.png"));
    JButton bStart = new JButton(new ImageIcon("src/images/buttonStart.png"));
    
    
    static CardLayout cl = new CardLayout(); // Tworzenie Layoutu

    
    public static Boolean rozpoczeto=false;
    
    public CLayout() {
        
        okno.setSize(300, 400);
        okno.setResizable(false);
        okno.setLocationRelativeTo(null);
        
        final CReader czyt = new CReader();
        
        tlo.setBounds(0, 0, 300, 400);
        panelLayout.setLayout(cl); //layout
        
        Thread t1 = new Thread((Runnable) panelGra);       //uruchamianie wątku 
        t1.start();
        
        tlo.setIcon(new ImageIcon(new ImageIcon("src/images/background.png").getImage().getScaledInstance(300, 400, Image.SCALE_SMOOTH)));
        
        ActionListener StartListener = new ActionListener() {   //Działanie przy kliknięciu przycisku start
            @Override
            public void actionPerformed(ActionEvent arg0) {
                
                
                panelGra.addKeyListener(czyt);
                
                t1.resume();
                zmianaTla(2);  //zmiana tła na gifa 
                
               
                panelGra.removeKeyListener(czyt);
                bStart.setEnabled(false);
                panelGra.requestFocusInWindow();
                //
                
                
                
                rozpoczeto=true;
            }
        }; //do przycisku Start
        bStart.addActionListener(StartListener);
        
        //Warianty tła gry
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
                
                cl.removeLayoutComponent(okno);
                okno.setSize(375, 510); //wielkosc okna gry
                
                panelGra.revalidate(); //odswiezenie gry
                cl.show(panelLayout, "2"); //przelaczenie na zakladke rozgrywki
                
                //Coin.setIlosc(0);

            }
        });
        
         bOpcje.addActionListener(new ActionListener() { //Przycisk "Ustawienia"
            @Override
            public void actionPerformed(ActionEvent arg0) {
                cl.removeLayoutComponent(okno);
                cl.show(panelLayout, "3");
            }
        });
        /**Przycisk "Koniec"*/
        bKoniec.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
                //panelUst.setEnabled(false);
            }
        });
       
        
        /**Działanie przycisku "Menu" w rozgrywce*/
        bMenu.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent arg0) {
                okno.setSize(300, 400);
                 cl.removeLayoutComponent(okno);     //Wyczyszczenie okna 
                cl.show(panelLayout, "1");            //Zmiana karty na Menu
                Thread.currentThread().interrupt(); //Zakończenie wątku po przerwaniu gry
                bStart.setEnabled(true);
                
                zmianaTla(1);
                
                Coin.resetIlosc();
                rozpoczeto=false;
                CPrzeszkoda.setKolizja(false);
                CDodger.defXY();
                //Resetowanie pozycji przeszkód po przejściu do menu
                for(int i=0;i<2;i++) CRozgrywka.getPrzeszkoda(i).resetL();
                for(int i=2;i<4;i++) CRozgrywka.getPrzeszkoda(i).resetP();
            }
        });
        /*
         * Ustawianie menu
         */

        logo.setBounds(30,10,300,100);
        logo.setIcon(new ImageIcon("src/images/logo2.png"));
;
        
        /*
        * Dodawanie komponentów do karty menu i dodanie jej do layoutu
        */
 
        
        panelMenu.add(bNowa);
        panelMenu.add(bOpcje);
        panelMenu.add(bInfo);
        panelMenu.add(bKoniec);
        panelMenu.add(logo);
        panelMenu.add(tlo);
        
        panelLayout.add(panelMenu, "1");
        
        cl.show(panelLayout, "1"); //karty layoutu
        panelLayout.add(panelGra, "2");
        panelLayout.add(panelUst, "3"); 
        
        bStart.setBounds(10, 5, 75, 25);
        bStart.setFocusable(false);
        bMenu.setBounds(280, 5, 75, 25);
        bMenu.setFocusable(false);
        labelHeader.setBounds(0, 0, 400, 35);
        

        
        panelGra.setLayout(null);
        panelGra.add(bMenu);
        panelGra.add(bStart);
        panelGra.requestFocusInWindow();
        panelGra.setFocusable(true);
        panelGra.add(labelHeader);
        
        
        
        bStart.addActionListener(StartListener);
        
        
        cl.show(panelLayout, "1"); //karty layoutu
        panelLayout.add(panelGra, "2");
        panelLayout.add(panelUst, "3"); 
        okno.add(panelLayout);
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //okno.pack();
        okno.setVisible(true);
        
    }
    /**Metoda zmieniająca grafikę drogi na której poruszają się obiekty
     * 
     * @param droga Grafika na którą zmieniane jest tło
     *<li><b>1</b> - grafika początkowa z informacją o zatrzymaniu gry</li>
     *<li><b>2</b> - grafika ruchoma, ustawiana wraz z rozpoczęciem gry</li>
     *<li><b>3</b> - grafika nieruchoma</li>
     */
    public static void zmianaTla(int droga){
        
        switch(droga){
            case 1: panelGra.remove(droga1); panelGra.remove(droga2); panelGra.remove(droga3);  panelGra.add(droga1);break;
            case 2: panelGra.remove(droga1); panelGra.remove(droga2); panelGra.remove(droga3);  panelGra.add(droga2);break;
            case 3: panelGra.remove(droga1); panelGra.remove(droga2); panelGra.remove(droga3);  panelGra.add(droga3);break;
            default: break;
        }
    }
   
    /**Metoda zmieniająca karty w naszym "Layout"
     * 
     * @param karta Numer karty na którą chcemy przejść
     *<li><b>1</b> - Karta menu</li>
     *<li><b>2</b> - karta z rozgrywką</li>
     *<li><b>3</b> - karta ustawień</li>
     */
   public static void zmianaKarty(String karta){
       cl.removeLayoutComponent(okno);
       cl.show(panelLayout, karta);
   }
    public static void main(String[] args) {
        CLayout clay = new CLayout();
        
        
    }
}
