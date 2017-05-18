package sample.DropBox;

import sample.Api.Temp;

import java.io.*;

/**
 * Created by 53638138e on 18/05/17.
 */
public class leerXml {


    public leerXml() {
    }

    //Variables para leerlo
    public static String linia, text = "";
    public static String frase,sensera = "";

    public Temp llegir(File f) throws FileNotFoundException {

        BufferedReader in = new BufferedReader(new FileReader(f));
        Temp temp = new Temp();
        //extraerInfo(linia)
        try {

            while((linia = in.readLine()) != null) {

                text = text + linia;
                if (in.ready())
                    text = text + "\n";


                if(linia.contains("<TEMPERATURA>")){
                    temp.setTemperatura(extraerInfo(linia));
                }
                else if(linia.contains("<PRESSURE>")){
                    temp.setPresion(extraerInfo(linia));
                }
                else if(linia.contains("<HUMIDITY>")){
                    temp.setHumedad(extraerInfo(linia));
                }
            }

            in.close();
        }
        catch(Exception e) {
            System.out.println("Error de fitxer: " + e.getMessage());
        }
        return  temp;
    }

    public static String extraerInfo(String texto){

        String textoRecortado = texto.split(">")[1].split("<")[0];

        return textoRecortado;
    }

}
