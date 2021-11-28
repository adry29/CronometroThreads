/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cronometro;

/**
 *
 * @author adryc
 */
public class Suspender {
    

 private boolean suspendido; //false el hilo esta corriendo cuando este en true el hilo esta detenido 
    
    public synchronized void setSuspendido(boolean b){
        this.suspendido=b;        
        notifyAll();
        
    }
    
    public synchronized void waitReanudar() throws InterruptedException{
        while(this.suspendido){
            wait();
        }
    }

    public boolean getSuspendido() {
        return suspendido;
    }
    
    
}