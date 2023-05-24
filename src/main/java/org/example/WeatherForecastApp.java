package org.example;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;


public class WeatherForecastApp {
    private static final String API_KEY = "f7492d95d68d92aca6cfa8601a965d93";
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather?q=London&appid=" + API_KEY;

    public static void main(String[] args) {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(API_URL);

        try {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String jsonResponse = EntityUtils.toString(entity);

            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONArray weatherArray = jsonObject.getJSONArray("weather");
            JSONObject weatherObject = weatherArray.getJSONObject(0);
            String mainWeather = weatherObject.getString("main");

            System.out.println("Current weather in London: " + mainWeather);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
