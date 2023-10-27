/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package GUI;

import javax.swing.JFrame;

/**
 *
 * @author aleja
 */

public class JFVentanaPrincipal extends JFrame{
    
    
    public JFVentanaPrincipal() {
        	      
       this.add(new Panel());
      
       setDefaultCloseOperation(this.EXIT_ON_CLOSE);
       setSize(800, 600);
       setResizable(false);
       setLocationRelativeTo(null);
       setVisible(true);
       
    }
    
}//class