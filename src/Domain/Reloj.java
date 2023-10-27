/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aleja
 */

public class Reloj extends Thread {

    private int horas;
    private int minutos;
    private int segundos;
    private boolean banderaInicio;
    private boolean banderaPausa;

    public Reloj() {
        this.horas = 0;
        this.minutos = 0;
        this.segundos = 0;
        this.banderaInicio = false;
        this.banderaPausa = false;
        
        this.start();
    }

    public void iniciar() {
        this.banderaInicio = true;
        this.banderaPausa = false;
       
    }

    public void resetear() {
        this.horas = 0;
        this.minutos = 0;
        this.segundos = 0;
        
        this.banderaInicio = false;
    }
      

    public void pausarReanudar() {
        this.banderaPausa = !this.banderaPausa;
    }

    

    @Override
    public void run() {
        while (true) {
            if (banderaInicio && !banderaPausa) {
                segundos++;
                if (segundos == 60) {
                    segundos = 0;
                    minutos++;
                    if (minutos == 60) {
                        minutos = 0;
                        horas++;
                        if (horas == 24) {
                            horas = 0;
                        }
                    }
                }
               
            }

            try {
                this.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Reloj.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public int getSegundos() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    public boolean isBanderaInicio() {
        return banderaInicio;
    }

    public void setBanderaInicio(boolean banderaInicio) {
        this.banderaInicio = banderaInicio;
    }

    
}// class
