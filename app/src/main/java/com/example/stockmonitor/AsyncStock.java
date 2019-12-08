package com.example.stockmonitor;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AsyncStock extends AsyncTask<String, Void, String> {

    private String buffer ="";
    private ArrayList<String> hinnat;

    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL("https://financialmodelingprep.com/api/v3/stock/real-time-price/AAPL,GOOGL,FB,NOK?datatype=json");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream stream = urlConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();

            String line ="";
            while((line = reader.readLine())!= null){
                buffer.append(line);
            }
            String finalJson = buffer.toString();

            JSONObject parentObject = new JSONObject(finalJson);
            JSONArray parentArray = parentObject.getJSONArray("companiesPriceList");

                StringBuffer finalBufferData = new StringBuffer();
                    for (int i = 0;i< parentArray.length();i++){
                        JSONObject finalObject = parentArray.getJSONObject(i);

                        String symbols = finalObject.getString("symbol");
                        Double prices = finalObject.getDouble("price");
                        finalBufferData.append(symbols + " - " + prices + " USD\n");
                    }

            return finalBufferData.toString();
           // String result = fromStream(stream);
         //   buffer = parsePrice(fromStream(stream));
          //  return buffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
 /*   private String parsePrice(String dataString) {
        try {
            String priceStringInJson = "\"price\": ";
            int indexOfPrice = dataString.indexOf(priceStringInJson);
            int indexOfNewLine = dataString.indexOf("\n", indexOfPrice + priceStringInJson.length());
            String result = dataString.substring(indexOfPrice + priceStringInJson.length(), indexOfNewLine);
            return result;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/
  /*  private static String fromStream(InputStream in) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder out = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
            out.append(newLine);
        }
        return out.toString();
    }*/

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        MainActivity.textView.setText(result);
    }
}
