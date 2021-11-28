/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cronometro;

import java.util.Observable;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author adryc
 */
public class Cronometro implements Runnable{

        private IntegerProperty segundos;
	private IntegerProperty minutos;
	private IntegerProperty horas;
        private StringProperty seconds;
        private StringProperty minutes;
        private StringProperty hours;
	Suspender susp = new Suspender();

	public Cronometro() {
		super();
		this.segundos = new SimpleIntegerProperty(0);
		this.minutos = new SimpleIntegerProperty(0);
		this.horas = new SimpleIntegerProperty(0);
		this.hours = new SimpleStringProperty("00");
		this.minutes = new SimpleStringProperty(":00");
		this.seconds = new SimpleStringProperty(":00");
		this.susp.setSuspendido(false);
	}

	public Suspender getSuspendido() {
		return susp;
	}

	public void setSuspendido(Suspender susp) {
        this.susp = susp;
    }

    public StringProperty getSegundos() {
        return seconds;
    }

    public void setSegundos(int segundos) {
        this.segundos.set(segundos);
    }

    public StringProperty getMinutos() {
        return minutes;
    }

    public void setMinutos(int minutos) {
        this.minutos.set(minutos);
    }

    public StringProperty getHoras() {
        return hours;
    }

    public void setHoras(int horas) {
        this.horas.set(horas);
    }

    @Override
    public void run() {

        while (true) {
            try {

                while (!this.susp.getSuspendido()) {
                    int contador = 0;
                    Thread.sleep(990);
                    this.susp.waitReanudar();
                    Platform.runLater(() -> {
                        segundos.set(segundos.get() + 1);
                        if (segundos.get() < 10) {
                            seconds.set(":0" + segundos.get());
                        } else {
                            seconds.set(":"+segundos.get());
                        }
                    });
                    if (segundos.get() == 59) {
                        Platform.runLater(() -> {
                            minutos.set(minutos.get() + 1);
                            if (minutos.get() < 10) {
                                minutes.set(":0" + minutos.get());
                            } else {
                                minutes.set(":"+minutos.get());
                            }
                            segundos.set(0);
                            seconds.set(":0"+segundos.get());
                        });

                        if (minutos.get() == 59) {
                            Platform.runLater(() -> {
                                minutos.set(0);
                                minutes.set(minutos.get() + "");
                                horas.set(horas.get() + 1);
                                hours.set(horas.get() + "");
                            });

                            if (horas.get() == 24) {
                                Platform.runLater(() -> {
                                    horas.set(0);
                                    hours.set(horas.get() + "");
                                });

                            }

                        }
                    }
                }
            }catch (Exception er) {
			segundos.set(0);
			minutos.set(0);
			horas.set(0);

                                }
        }
    }
}
            
        
