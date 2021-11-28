/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.cronometro;

import java.net.URL;

import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author adryc
 */
public class ViewController{

    
    
    @FXML
    Label horas;
	
    @FXML	
    Label minutos;

    @FXML
    Label segundos;
    
    @FXML
    private Button iniciar;
    
    @FXML
    private Button detener;
    
    @FXML
    private Button reiniciar;
    
    @FXML
    Label marcador;
    
    @FXML
    private Button marcar;
    
    boolean result=false;
    
    
    public ViewController(){
        
    }
    
    @FXML
    private void initialize(){
        Cronometro c = new Cronometro();
        Thread t = new Thread(c);
        iniciar.setOnAction(event ->{
            detener.setText("Detener");
            if(result = false);
            t.start();
            result = true;
            
            c.getSuspendido().setSuspendido(false);
            iniciar.setDisable(true);
            iniciar.setText("Contando");
            horas.textProperty().bind(c.getHoras());
            minutos.textProperty().bind(c.getMinutos());
            segundos.textProperty().bind(c.getSegundos());
        });
        
        detener.setOnAction(event -> {
            if(c.susp.getSuspendido()==false){
                c.getSuspendido().setSuspendido(true);
		detener.setText("Continuar");
            } else {
		c.getSuspendido().setSuspendido(false);
		detener.setText("Detener");
			}
        });

            
        reiniciar.setOnAction(event -> {
            t.interrupt();
            
            c.getSuspendido().setSuspendido(true);
            iniciar.setDisable(false);
            iniciar.setText("Iniciar");
            horas.textProperty().unbind();
            minutos.textProperty().unbind();
            segundos.textProperty().unbind();
            horas.setText("00:");
            minutos.setText("00:");
            segundos.setText("00");
            initialize();
        });
        
        marcar.setOnAction(event -> {
            marcador.setText("Último marcador: "+horas.getText()+minutos.getText()+segundos.getText());
        });
        
    }
    


    
}
