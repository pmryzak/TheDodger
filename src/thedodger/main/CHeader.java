/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thedodger.main;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * Nagłówek okna gry.
 */
public class CHeader extends JLabel {
    private int Width;
    private int Height;
    
    public CHeader(){
        Width = 400;
        Height = 80;
        setIcon(new ImageIcon("src/images/background.png"));
    }
    
   
}
