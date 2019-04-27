package com.example.giuakyandroid.AsyncTask;

import android.content.Context;
import android.os.AsyncTask;

import com.example.giuakyandroid.IView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

public class PhoneAsyncTask extends AsyncTask<String, Void, JSONObject> {
    private IView iData;
    private Context context;
    private Map<String,String> resource;
    public PhoneAsyncTask(Context context, IView iData, Map<String,String>resource)
    {
        this.resource = resource;
        this.iData = iData;
        this.resource = resource;
    }
    @Override
    protected JSONObject doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("Content-Type","application/json");
            connection.setRequestMethod("POST");
            JSONObject data = new JSONObject();
            Iterator iterator = resource.keySet().iterator();
            while (iterator.hasNext()){
                String key = (String) iterator.next();
                String value = resource.get(key);
                data.put(key,value);
            }

            OutputStream outputStream = connection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            bufferedWriter.write(String.valueOf(data));
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();
            String result;
            while ((result = bufferedReader.readLine()) != null){
                stringBuffer.append(result);
            }
            result  = stringBuffer.toString();
            JSONObject parentObject = new JSONObject(result);
            return parentObject;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
        try {
            int mResult = jsonObject.getInt("result");
            if (mResult > 0){
                JSONArray jsonArray = jsonObject.getJSONArray("response_data");
                iData.onGetDataSuccess(jsonArray);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}