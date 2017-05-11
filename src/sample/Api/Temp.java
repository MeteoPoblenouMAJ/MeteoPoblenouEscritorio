package sample.Api;

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
    private String Image;
    private String fecha;
    private int media;




    //Constructor


    public Temp() {
    }

    public Temp(String temperatura, String humedad, String presion, String descripcion, String image, String fecha, int media) {
        this.temperatura = temperatura;
        this.humedad = humedad;
        this.presion = presion;
        this.descripcion = descripcion;
        Image = image;
        this.fecha = fecha;
        this.media = media;
    }

    //Getters


    public String getFecha() {
        return fecha;
    }

    public String getTemperatura() {
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

    public String getImage() {
        return Image;
    }

    public int getMedia() {
        return media;
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

    public void setImage(String image) {
        Image = image;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setMedia(int media) {
        this.media = media;
    }
}
