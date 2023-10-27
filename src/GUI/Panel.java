/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Domain.Reloj;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author aleja
 */

public class Panel  extends JPanel implements ActionListener{
   
    private Reloj reloj;
   
    private int ancho, alto;
    private int centerX, centerY;
  
    private BufferedImage imagenReloj; 
    private BufferedImage fondo; 
    
    
    private JButton iniciar;
    private JButton pausarReanudar;
    private JButton resetear;
    private JButton iniciarConHoraEstablecida;
    
    
    public Panel() {
        
        setLayout(null);
        
        this.reloj = new Reloj();
       
        init();
        
        this.ancho = 800;
        this.alto = 400;
        this.centerX = ancho / 2;
        this.centerY = alto / 2;
       
        
        setSize(ancho, alto);
        setLocation(0, 0);
        setVisible(true);
    }
    
    
    private void init(){
        
        iniciar = new JButton("Iniciar");
        iniciar.setBounds(40, 430, 150, 30); // Establecer la posición y el tamaño del botón (x, y, ancho, alto)
        this.add(iniciar);
        iniciar.addActionListener(this);
        
        pausarReanudar = new JButton("Pausar / Reanudar");
        pausarReanudar.setBounds(211, 430, 150, 30); // Establecer la posición y el tamaño del botón (x, y, ancho, alto)
        this.add(pausarReanudar);
        pausarReanudar.addActionListener(this);
        
        resetear = new JButton("Resetaer");
        resetear.setBounds(381, 430, 150, 30); // Establecer la posición y el tamaño del botón (x, y, ancho, alto)
        this.add(resetear);
        resetear.addActionListener(this);
        
        iniciarConHoraEstablecida = new JButton("Iniciar con hora establecida");
        iniciarConHoraEstablecida.setBounds(550, 430, 200, 30); // Establecer la posición y el tamaño del botón (x, y, ancho, alto)
        this.add(iniciarConHoraEstablecida);
        iniciarConHoraEstablecida.addActionListener(this);
        
        
        try {
            this.imagenReloj = ImageIO.read(getClass().getResourceAsStream("/imagenes/reloj.png"));
        } catch (IOException ex) {
            Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            this.fondo = ImageIO.read(getClass().getResourceAsStream("/imagenes/fondo.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//init
    
    
    
    @Override
    public void paintComponent (Graphics g) {
        
       g.drawImage(this.fondo, 0, 0, null);  //dibujo del fondo 
       g.drawImage(imagenReloj, centerX-164, centerY-150, 324, 300, null);//dibujo imagen del reloj
       
     
       //////////////////////  dibujo del reloj digital /////////////////////////////////////////////
       g.setColor(Color.gray);
       g.setFont(new Font("Andale Mono", 1, 15));
       String tiempoFormateado = String.format("%02d:%02d:%02d", this.reloj.getHoras(), this.reloj.getMinutos(), this.reloj.getSegundos());
       g.drawString(tiempoFormateado, centerX-31, centerY+35);
       
    
       
       //////////////////////////////// Dibujo del reloj analogico ////////////////////////////////
     
       Graphics2D g2d = (Graphics2D) g;
        
       int largoHora = 60;
       int largoMinuto = 80;
       int largoSegundo = 100;

       double anguloHora = Math.toRadians(90 - (this.reloj.getHoras()+ this.reloj.getMinutos() / 60.0) * 360 / 12);
       double anguloMinuto = Math.toRadians(90 - this.reloj.getMinutos() * 360 / 60);
       double anguloSegundo = Math.toRadians(90 - this.reloj.getSegundos() * 360 / 60);

       g2d.setColor(Color.gray);
       g2d.setStroke(new BasicStroke(5));
       g2d.drawLine(centerX, centerY, centerX + (int) (largoHora * Math.cos(anguloHora)), centerY - (int) (largoHora * Math.sin(anguloHora)));
      
       g2d.setStroke(new BasicStroke(4));
       g2d.drawLine(centerX, centerY, centerX + (int) (largoMinuto * Math.cos(anguloMinuto)), centerY - (int) (largoMinuto * Math.sin(anguloMinuto)));
       g2d.setColor(Color.red);
       g2d.setStroke(new BasicStroke(3));
       g2d.drawLine(centerX, centerY, centerX + (int) (largoSegundo * Math.cos(anguloSegundo)), centerY - (int) (largoSegundo * Math.sin(anguloSegundo)));
        
       
       g.fillOval(centerX-9, centerY-9, 18, 18);// es para poner un circulito pequnio encima de mis agujas
       g2d.setColor(Color.white);
       g.fillOval(centerX-5, centerY-5, 10, 10);// es para poner un circulito pequnio encima de mis agujas
     
       
       repaint();
        
    }//paintComponent
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
		
        if(e.getSource()==this.iniciar) {
			
            this.reloj.iniciar();
            
	}else if(e.getSource()==this.pausarReanudar){
            
            this.reloj.pausarReanudar();
            
        }else if(e.getSource()==this.resetear){
            
            this.reloj.resetear();
            
        }else if(e.getSource()==this.iniciarConHoraEstablecida){
            
            this.reloj.resetear();

            this.reloj.setHoras( obtenerHoraValida());
            this.reloj.setMinutos(obtenerMinutosValido());
            this.reloj.setSegundos(obtenerSegundosValidos());
            
            this.reloj.iniciar();
        }
            
    }//actionperformed
    
    
    public int obtenerHoraValida(){
       
        int numero = 0;

        while (true) {
            String input = JOptionPane.showInputDialog("Ingrese la hora:");

            try {
                numero = Integer.parseInt(input);

                if (numero >= 0 && numero <= 23) {
                    return numero;  // Si es un número válido, salimos del bucle.
                } else {
                    JOptionPane.showMessageDialog(null, "El número debe estar entre 0 y 23.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada no válida. Debe ingresar un número entero.");
            }
        }
    
    }
    
    
    public int obtenerMinutosValido(){
       
        int numero = 0;

        while (true) {
            String input = JOptionPane.showInputDialog("Ingrese los minutos:");

            try {
                numero = Integer.parseInt(input);

                if (numero >= 0 && numero <= 59) {
                    return numero;  // Si es un número válido, salimos del bucle.
                } else {
                    JOptionPane.showMessageDialog(null, "El número debe estar entre 0 y 59.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada no válida. Debe ingresar un número entero.");
            }
        }
    
    }
    
      public int obtenerSegundosValidos(){
       
        int numero = 0;

        while (true) {
            String input = JOptionPane.showInputDialog("Ingrese los segundos:");

            try {
                numero = Integer.parseInt(input);

                if (numero >= 0 && numero <= 59) {
                    return numero;  // Si es un número válido, salimos del bucle.
                } else {
                    JOptionPane.showMessageDialog(null, "El número debe estar entre 0 y 23.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada no válida. Debe ingresar un número entero.");
            }
        }
    
    }
  
}//class
