package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class Controller {

    // Campos que actualizaremos en cada sección

    public TextField campoTexto1;
    public TextField campoTexto2;
    public TextField campoTexto3;
    public TextField campoTexto4;
    public TextField campoTexto5;
    public TextField campoTexto6;

    public void UltimosDatos(ActionEvent actionEvent){

        campoTexto1.setPromptText("Titulo");
        campoTexto2.setPromptText("Autor ");
        campoTexto3.setPromptText("Editorial");
        campoTexto4.setPromptText("Numero de paginas");
        campoTexto5.setPromptText("Año de edicion ");
        campoTexto6.setPromptText("Numero de ejemplares");


    }

}
