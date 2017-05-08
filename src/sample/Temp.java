package sample;

/**
 * Created by 53638138e on 08/05/17.
 */
public class Temp {

    //Variables
    private String temperatura;
    private String humedad;
    private String presion;


    //Constructor
    public Temp(String temperatura, String humedad, String presion) {
        this.temperatura = temperatura;
        this.humedad = humedad;
        this.presion = presion;
    }

    public Temp() {
    }


    //Getters
    public String getTemperatura() {
        return temperatura;
    }

    public String getHumedad() {
        return humedad;
    }

    public String getPresion() {
        return presion;
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
}
