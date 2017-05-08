package sample;

import javafx.event.ActionEvent;
import javafx.scene.text.Text;

public class Controller {

    // Campos que actualizaremos en cada sección

    public Text campoTexto1;
    public Text campoTexto2;
    public Text campoTexto3;
    public Text campoTexto4;
    public Text campoTexto5;


    //Lo que hara al inicarse
    public void initialize() {

    }

    //Meotodo que mostrara los ultimos datos recogidos

    public void UltimosDatos(ActionEvent actionEvent){

        campoTexto1.setText("Temperatura Raspberry: ");
        campoTexto2.setText("Humedad: ");
        campoTexto3.setText("Presión: ");
        campoTexto4.setText("Tiempo: ");
        campoTexto5.setText("Temperatura Api: ");

        //Muetro los textos
        campoTexto1.setVisible(true);
        campoTexto2.setVisible(true);
        campoTexto3.setVisible(true);
        campoTexto4.setVisible(true);
        campoTexto5.setVisible(true);



    }

}
