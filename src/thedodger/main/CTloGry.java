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
 * Klasa zawierająca tło gry
 */
public class CTloGry extends JLabel {

    /** Konstruktor 
    @param tlo Ścieżka do obrazu tła
    */
    public CTloGry(String tlo) {
        setIcon(new ImageIcon("src/images/" + tlo));
    }


}
