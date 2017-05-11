package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import sample.Api.ApiTemps;
import sample.Api.Temp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
    public String temp;
    public String hume;
    public String pre;
    public String tiempo;
    public String imagen;
    public ArrayList<Temp> tempsResum = new ArrayList<Temp>();;

    //Variables
    String FechaControl;
    public int media;



    //Lo que hara al inicarse
    public void initialize() {

        Temp t = ApiTemps.getCardsTypes();
        Temp s = ApiTemps.getWeather();

        if (t != null) {
            temp = t.getTemperatura();
            hume = t.getHumedad();
            pre =  t.getPresion();
            tiempo = s.getDescripcion();
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


    //Pestaña de Ultimos Datos

    public void UltDatos() throws IOException {

        campoTexto1.setText("Tiempo: "+ tiempo);
        campoTexto2.setText("Humedad: " + hume);
        campoTexto3.setText("Presión: "+ pre);
        campoTexto4.setText("Temperatura Raspberry: " +"......");
        campoTexto5.setText("Temperatura Api: "+temp);

        //Muetro los textos
        campoTexto1.setVisible(true);
        campoTexto2.setVisible(true);
        campoTexto3.setVisible(true);
        campoTexto4.setVisible(true);
        campoTexto5.setVisible(true);

        CompruebaImagen();


    }

    //Comprueba  la id de la imagen y la cambia 

    private void CompruebaImagen() throws IOException {
        if(imagen.equals("03d")||imagen.equals("03n")){
            nublado();
        }
        if(imagen.equals("01d")){
            soleado();
        }
        if(imagen.equals("01n")){
            luna();
        }
        if(imagen.equals("04n")||imagen.equals("02n")){
            lunanube();
        }
        if(imagen.equals("04d")||imagen.equals("02d")){
            parsoleado();
        }
        if(imagen.equals("13d")||imagen.equals("13n")){
            snow();
        }
        if(imagen.equals("10d")||imagen.equals("09d")||imagen.equals("09n")||imagen.equals("10n")){
            lluvia();
        }
        if(imagen.equals("11d")||imagen.equals("11n")){
            tormenta();
        }

    }


    //Pestaña Resumen dia

    public void ResuDiario() throws IOException{

        initialize();
        Calendar calendario = new GregorianCalendar();
        int hora;
        hora =calendario.get(Calendar.HOUR_OF_DAY);
        //Calendario
        Calendar c2 =  Calendar.getInstance();

        String dia = Integer.toString(c2.get(Calendar.DATE));
        String mes = Integer.toString(c2.get(Calendar.MONTH)+1);
        String anyo = Integer.toString(c2.get(Calendar.YEAR));

        FechaControl = dia+"-"+mes+"-"+anyo;

         //Aqui saco la ultima fecha recojida para compararla con la nueva
        Temp recojer = new Temp();
        //Si la hora esta entre las 12 de la noche se limpia la Array
        if(tempsResum == null){
            //Recoje los datos y los mete en la Array como objeto
            recojer.setHumedad(hume);
            recojer.setTemperatura(temp);
            recojer.setPresion(pre);
            recojer.setDescripcion(tiempo);
            recojer.setFecha(FechaControl);

            int mediaDia = Integer.parseInt(temp);
            recojer.setMedia(mediaDia);

            tempsResum.add(recojer);

        }

        if(hora == 00||hora>00 && hora<01){
            tempsResum.clear();
            media = 0 ;
        }

        else {
            //Recoje los datos y los mete en la Array como objeto
                recojer.setHumedad(hume);
                recojer.setTemperatura(temp);
                recojer.setPresion(pre);
                recojer.setDescripcion(tiempo);
                recojer.setFecha(FechaControl);

            System.out.println("temperatura-----" + temp + "---");
                int mediaDia = Integer.parseInt(temp);
                mediaTemp(mediaDia);
                recojer.setMedia(media);

                tempsResum.add(recojer);
            }


        //Mostrar Media
        campoTexto1.setText("La media del dia de hoy es : "+media);

        //Muetro los textos
        campoTexto1.setVisible(true);
        campoTexto2.setVisible(false);
        campoTexto3.setVisible(false);
        campoTexto4.setVisible(false);
        campoTexto5.setVisible(false);

        //Comprobar Imagen
        CompruebaImagen();
    }

    //Calcula la media del dia un poco chapuza
    private void mediaTemp(int mediaDia) {
        if(media == 0){
            media = mediaDia;
        }else{
            media = media + mediaDia % 2;
        }
    }

    //Configuracion de Imagenes segun el tiempo

    private void lunanube() {
        Image image = new Image((getClass().getResourceAsStream("Imagen/nubeluna.png")));
        ImagenTiempo.setImage(image);
    }

    private void luna() {
        Image image = new Image((getClass().getResourceAsStream("Imagen/Luna.png")));
        ImagenTiempo.setImage(image);
    }

    private void tormenta() {
        Image image = new Image((getClass().getResourceAsStream("Imagen/rayos.png")));
        ImagenTiempo.setImage(image);
    }

    private void lluvia() {
        Image image = new Image((getClass().getResourceAsStream("Imagen/lluvia.png")));
        ImagenTiempo.setImage(image);
    }

    private void snow() {
        Image image = new Image((getClass().getResourceAsStream("Imagen/snow.png")));
        ImagenTiempo.setImage(image);
    }

    private void parsoleado() {
        Image image = new Image((getClass().getResourceAsStream("Imagen/parsol.png")));
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
