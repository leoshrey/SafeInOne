package com.leoshrey.safeinone.manager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class SOSNotificationHandler {

    public String handle(String requestJson) {

        try {
            //URL url = new URL("http://127.0.0.1:7071/api/GPSTwilio");
            URL url = new URL("https://gpscstwilio.azurewebsites.net/api/gpstwilio");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //connection.setRequestProperty("Content-Type", "application/json");
            //connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");

            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(requestJson);
            writer.flush();

            InputStreamReader insb = new InputStreamReader(connection.getInputStream());
            BufferedReader br = new BufferedReader(insb);

            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim() + "\n");
            }
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
