package sample;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 53638138e on 09/05/17.
 */
//Clase que sirve para conectar con la appi
public class HtttpUtils {
    public static String get(String dataUrl) throws IOException {
        URL url = new URL(dataUrl);
        String response = null;
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            urlConnection.addRequestProperty("User-Agent","Mozilla/4.0");
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            response = readStream(in);
        } finally {
            urlConnection.disconnect();
        }
        return response;

    }

    private static String readStream(InputStream in) throws IOException {
        InputStreamReader is = new InputStreamReader(in);
        BufferedReader rd = new BufferedReader(is);
        String line;
        StringBuilder response = new StringBuilder();
        while ((line = rd.readLine()) != null) {
            response.append(line);
            response.append('\r');
        }
        rd.close();
        return response.toString();
    }
}
