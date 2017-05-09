package sample;

import java.io.Serializable;

/**
 * Created by 53638138e on 08/05/17.
 */
public class Temp implements Serializable {

    //Variables
    private String temperatura;
    private String humedad;
    private String presion;
    private String descripcion;


    //Constructor


    public Temp(String temperatura, String humedad, String presion, String descripcion) {
        this.temperatura = temperatura;
        this.humedad = humedad;
        this.presion = presion;
        this.descripcion = descripcion;
    }

    public Temp() {
    }


    //Getters
    public String getTemperatura(String temp) {
        return temperatura;
    }

    public String getHumedad() {
        return humedad;
    }

    public String getPresion() {
        return presion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    //Setters
    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public void setHumedad(String humedad) {
        this.humedad = humedad;
    }

    public void setPresion(String presion) {
        this.presion = presion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
