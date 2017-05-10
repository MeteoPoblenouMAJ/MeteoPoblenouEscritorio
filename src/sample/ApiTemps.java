package sample;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by 53638138e on 09/05/17.
 */
;

public class ApiTemps {

    public ApiTemps() {
    }

    private static String url = "http://api.openweathermap.org/data/2.5/weather?APPID=89e1966d38650a9ab9a6bc73dd8e6c84&lat=41.396620&lon=2.200817&units=metric";
    private static Temp temp = new Temp();
    static String JsonResponse = null;

    public static Temp getCardsTypes() {

        try {
            JsonResponse= HtttpUtils.get(url);
            JSONObject data = new JSONObject(JsonResponse);
            JSONObject object;

            //parte main
                object = data.getJSONObject("main");
                    int tempe =(object.getInt("temp"));
                    temp.setTemperatura(String.valueOf(tempe));
                if (object.has("humidity")) {
                    int hum =(object.getInt("humidity"));
                    temp.setHumedad(String.valueOf(hum));
                }

                if (object.has("pressure")) {
                    int pres = (object.getInt("pressure"));
                    temp.setPresion(String.valueOf(pres));
                }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public static Temp getWeather() {
        try {
            JsonResponse = HtttpUtils.get(url);
            JSONObject data = null;
            data = new JSONObject(JsonResponse);
            JSONArray jsonweather = data.getJSONArray("weather");
            JSONObject object;

           //Parte weather
            for (int i = 0; i <jsonweather.length() ; i++) {
                object = jsonweather.getJSONObject(i);
                    temp.setDescripcion(object.getString("description"));
                //temp.setImage(object.getString("icon"));
                temp.setImage("13n");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return temp;
    }

}
