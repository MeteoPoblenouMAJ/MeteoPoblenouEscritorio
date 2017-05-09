package sample;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by 53638138e on 09/05/17.
 */
;

public class ApiTemps {

    public ApiTemps() {
    }

    private static String url = "https://api.magicthegathering.io/v1/cards?";

    public ArrayList<Temp> getCardsTypes() {
        ArrayList<Temp> cards = new ArrayList<>();
        try {
            String JsonResponse = HtttpUtils.get(url);
            JSONObject data = new JSONObject(JsonResponse);
            JSONArray jsonCartas = data.getJSONArray("main");
            JSONArray jsonweather = data.getJSONArray("main");
            JSONObject object;
            Temp temp = new Temp();

            //parte main
            for (int i = 0; i <jsonCartas.length() ; i++) {

                object = jsonCartas.getJSONObject(i);
                    temp.setTemperatura(object.getString("temp"));

                if (object.has("humidity")) {
                    temp.setHumedad(object.getString("humidity"));
                }

                if (object.has("pressure")) {
                    temp.setHumedad(object.getString("pressure"));
                }
            }

            for (int i = 0; i <jsonweather.length() ; i++) {
                object = jsonweather.getJSONObject(i);
                temp.setTemperatura(object.getString("description"));
            }


            cards.add(temp);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cards;
    }
}
