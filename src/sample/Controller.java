package sample;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import sample.Api.ApiTemps;
import sample.Api.Temp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

public class Controller {

    // Campos que actualizaremos en cada sección
    @FXML
    public Text campoTexto1;
    public Text campoTexto2;
    public Text campoTexto3;
    public Text campoTexto4;
    public Text campoTexto5;
    public ImageView ImagenTiempo;
    public Pane pane;


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
    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    Firebase database = new Firebase("https://estaciometeo-73e65.firebaseio.com/");
    //  DatabaseReference referencia;



//Funcion que recoje todos los datos de la Api nada mas entrar
    public void ultimos(){
        Query lastQuery = database.getRef().getRoot();
        System.out.println("ok"+lastQuery.getRef());
        System.out.println("ok1"+database.getName());
        System.out.println("ok2"+database.getRepo());

        lastQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String hour = "";
                double temp = 0.0;
                double humidity = 0.0;
                double press = 0.0;
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                while (iterator.hasNext()) {
                    DataSnapshot dt = iterator.next();
                    for (DataSnapshot test : dt.getChildren()) {
                        //Hora de la actualitzacio
                        hour = test.getName();
                        //Temperatura de la ultima actualitzacio
                        temp = test.child("TEMPERATURA").getValue(Double.class);
                        humidity = test.child("HUMIDITY").getValue(Double.class);
                        press = test.child("PRESSURE").getValue(Double.class);
                    }
                }
                System.out.println(hour+" --> TEMPERATURA: "+temp);
                System.out.println(hour+" --> HUMIDITY: "+humidity);
                System.out.println(hour+" --> PRESSURE: "+press);
            }

            @Override
            public void onCancelled() {

            }
        });

    }

    //Lo que hara al inicarse
    public void initialize() {

        ultimos();
        Temp t = ApiTemps.getCardsTypes();
        Temp s = ApiTemps.getWeather();

        if (t != null) {
            temp = t.getTemperatura();
            hume = t.getHumedad();
            pre =  t.getPresion();
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

        //La compruebo antes para saber el tiempo

        CompruebaImagen();

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

    }

    //Cam en Directo

    public void Live(ActionEvent actionEvent){
        Scene scene = new Scene(new Group());
        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        Hyperlink hpl = new Hyperlink("http://motion:arriquitan@virtual.ecaib.org/motion");
        webEngine.load("http://motion:arriquitan@virtual.ecaib.org/motion");
        pane.getChildren().addAll(hpl,browser);
        scene.setRoot(pane);
    }

    //Graficas

    public void Graficas(ActionEvent actionEvent){
        Scene scene = new Scene(new Group());
        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        Hyperlink hpl = new Hyperlink("http://www.aemet.es/es/eltiempo/prediccion/municipios/grafica/temperatura/barcelona-id08019");
        webEngine.load("http://www.aemet.es/es/eltiempo/prediccion/municipios/grafica/temperatura/barcelona-id08019");
        pane.getChildren().addAll(hpl,browser);
        scene.setRoot(pane);
    }



    //Ayudas

    public void AyudaUlDatos(ActionEvent actionEvent){
        alert.setTitle("Sobre Dia de hoy");
        alert.setHeaderText("MeteoPoblenou  v0.1");
        alert.setContentText("" +
                "\n Tiempo Actua: Aqui puedes observar el tiempo real de tu ciudad Poblenou.  " +
                "\n Resumen Diario: Aqui muestra la media de la temperatura del dia de hoy" +
                "\n               " +
                "\n               ");

        alert.showAndWait();
    }



    public void AyudaTiempoDirecto(ActionEvent actionEvent){
        alert.setTitle("Tiempo en directo");
        alert.setHeaderText("MeteoPoblenou  v0.1");
        alert.setContentText("" +"\n Observa la cam en tiempo real") ;

        alert.showAndWait();
    }

    public void Ayudainfo(ActionEvent actionEvent){
        alert.setTitle("METEO POBLENOU");
        alert.setHeaderText("MeteoPoblenou  v0.1");
        alert.setContentText("Estas en Meteo Poblenou una aplicación desarrollada por :" +
                "\n Mireia Fernández Casals, Adonis Gomez Correia, Josep Rabada Colls" +
                "\n Creada para la obtención de datos metereologicós para uso escolar." +
                "\n               " +
                "\n               ");

        alert.showAndWait();
    }



    //Comprueba public void UltimosDatos(ActionEvent actionEvent) la id de la imagen y la cambia

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
        tiempo = "Nublado";
    }

    private void luna() {
        Image image = new Image((getClass().getResourceAsStream("Imagen/Luna.png")));
        ImagenTiempo.setImage(image);
        tiempo = "Despejado";
    }

    private void tormenta() {
        Image image = new Image((getClass().getResourceAsStream("Imagen/rayos.png")));
        ImagenTiempo.setImage(image);
        tiempo = "Tormenta";
    }

    private void lluvia() {
        Image image = new Image((getClass().getResourceAsStream("Imagen/lluvia.png")));
        ImagenTiempo.setImage(image);
        tiempo = "Lluvias";
    }

    private void snow() {
        Image image = new Image((getClass().getResourceAsStream("Imagen/snow.png")));
        ImagenTiempo.setImage(image);
        tiempo = "Nieva";
    }

    private void parsoleado() {
        Image image = new Image((getClass().getResourceAsStream("Imagen/parsol.png")));
        ImagenTiempo.setImage(image);
        tiempo = "Parcialmente Nublado";
    }

    public void nublado() throws IOException {
        Image image = new Image((getClass().getResourceAsStream("Imagen/4-cloud-png-image-thumb.png")));
        ImagenTiempo.setImage(image);
        tiempo = "Nublado";
    }

    public void soleado() throws IOException {
        Image image = new Image((getClass().getResourceAsStream("Imagen/sol.png")));
        ImagenTiempo.setImage(image);
        tiempo = "Soleado";
    }




}
