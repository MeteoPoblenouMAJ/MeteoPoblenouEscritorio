package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.util.ArrayList;

public class Controller {

    // Campos que actualizaremos en cada sección

    public Text campoTexto1;
    public Text campoTexto2;
    public Text campoTexto3;
    public Text campoTexto4;
    public Text campoTexto5;
    public ListView<Temp> lvLlistaCartes;


    //Lo que hara al inicarse
    public void initialize() {

        ApiTemps apicart = new ApiTemps();
        ArrayList<Temp> listacartas = apicart.getCardsTypes();

        lvLlistaCartes.setCellFactory(new Callback<ListView<Temp>, ListCell<Temp>>() {
            @Override
            public ListCell<Temp> call(ListView<Temp> p) {

                ListCell<Temp> cell = new ListCell<Temp>() {

                    @Override
                    protected void updateItem(Temp t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            /*String titulo="Nombre: "+t.getNombre();
                            String rareza="rareza: "+t.getRareza();
                            String color="color: "+t.getColor();
                            String texto=titulo+"\n"+rareza+"\n"+color;
                            setText(texto);*/
                        }
                    }
                };
                return cell;
            }
        });

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
