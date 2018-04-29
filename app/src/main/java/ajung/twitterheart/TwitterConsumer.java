package ajung.twitterheart;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Hyunah on 2018-03-25.
 * this is my access token
 */

public class TwitterConsumer extends Thread{
    private final String ACCESS_TOKEN = "1208262667-ZipmRkLYxNPJUAZuq5RdFAvZ5abQoBBKpafOzCd";
    private final String ACCESS_SECRET = "aFTq3FeiplifJdo5mUtNr0AZDJX9NbieCGYqPrTKEgUEo";
    private final String CONSUMER_KEY = "9PIn71z3n1UH8mC50LKYSVA8c";
    private final String CONSUMER_SECRET = "eVtDENEpzalS7jW7VVGWXOZBTZBOVlh6wYIHTHQoVIeuispbUH";

    private HttpURLConnection connection;
    private final String FEED_URL = "http://stream.twitter.com/1.1/statuses/filter.json?track=socurites,football";

    public String getContents(){
        String result = "ERROR";
        try {
            Log.e("TwtConsumer", "1");
            if (connection == null) {
                connection = (HttpURLConnection) new URL(FEED_URL).openConnection();
                connection.setConnectTimeout(100000);
                connection.setReadTimeout(100000);
            }
            Log.e("TwtConsumer", "2");
            connection.connect();
            if(connection.getResponseCode() != HttpURLConnection.HTTP_OK){
                Log.e("TwtConsumer", "Cannot connect");
                connection.disconnect();
                return "ERROR";
            }

            Log.e("TwtConsumer", "3");
            InputStream is = connection.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            String line = "";
            result = "";

            Log.e("TwtConsumer", "4");
            while((line = in.readLine()) != null)
                result = result.concat(line);

            Log.e("TwtConsumer", result);
            connection.disconnect();
        }catch(MalformedURLException mue){
            Log.e("TwtConsumer", "URL Error");
            Log.e("TwtConsumer", mue.toString());
        }catch(IOException ie){
            Log.e("TwtConsumer", "connect method error");
            Log.e("TwtConsumer", ie.toString());
        }
        return result;
    }
}