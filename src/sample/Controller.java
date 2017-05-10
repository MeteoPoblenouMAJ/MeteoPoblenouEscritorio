package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;

public class Controller {

    // Campos que actualizaremos en cada sección

    @FXML
    public Text campoTexto1;
    public Text campoTexto2;
    public Text campoTexto3;
    public Text campoTexto4;
    public Text campoTexto5;
    public ImageView ImagenTiempo;




    //Variables a rellenar
    String temp;
    String hume;
    String pre;
    String tiempo;
    String imagen;


    //Lo que hara al inicarse
    public void initialize() {

        Temp t = ApiTemps.getCardsTypes();
        Temp s = ApiTemps.getWeather();


        if (t != null) {
            temp = "Temperatura: " + t.getTemperatura();
            hume = "Humedad: " + t.getHumedad();
            pre = "Presión: "+ t.getPresion();
            tiempo = "Tiempo: "+ s.getDescripcion();
            imagen = s.getImage();
                        }

        try {
            UltDatos();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Meotodo que mostrara los ultimos datos recogidos

    public void UltimosDatos(ActionEvent actionEvent){
        initialize();
    }


    public void UltDatos() throws IOException {

        campoTexto1.setText("Temperatura Raspberry: " +"......");
        campoTexto2.setText(hume);
        campoTexto3.setText(pre);
        campoTexto4.setText(tiempo);
        campoTexto5.setText("Temperatura Api: "+temp);

        //Muetro los textos
        campoTexto1.setVisible(true);
        campoTexto2.setVisible(true);
        campoTexto3.setVisible(true);
        campoTexto4.setVisible(true);
        campoTexto5.setVisible(true);

        if(imagen.equals("03d")||imagen.equals("02d")){
            nublado();
        }
        if(imagen.equals("01d")||imagen.equals("1n")){
            soleado();
        }
        if(imagen.equals("04d")||imagen.equals("09d")||imagen.equals("04n")||imagen.equals("09n")){
            parsoleado();
        }
        if(imagen.equals("13d")||imagen.equals("13n")){
            snow();
        }

    }

    private void snow() {
        Image image = new Image((getClass().getResourceAsStream("Imagen/snow")));
        ImagenTiempo.setImage(image);
    }

    private void parsoleado() {
        Image image = new Image((getClass().getResourceAsStream("Imagen/parsol")));
        ImagenTiempo.setImage(image);
    }

    public void nublado() throws IOException {
        Image image = new Image((getClass().getResourceAsStream("Imagen/4-cloud-png-image-thumb.png")));
        ImagenTiempo.setImage(image);
    }

    public void soleado() throws IOException {
        Image image = new Image((getClass().getResourceAsStream("Imagen/sol.png")));
        ImagenTiempo.setImage(image);
    }


}
