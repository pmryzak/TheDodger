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

    //private static String tlo = "droga.png";

    public CTloGry(String tlo) {
        setIcon(new ImageIcon("src/images/" + tlo));
    }

//    public static void zmienTlo(String nazwa) {
//        tlo = nazwa;
//    }
}
