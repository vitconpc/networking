package vn.com.example.demoparsejson.model;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import vn.com.example.demoparsejson.model.data.Data;
import vn.com.example.demoparsejson.presenter.MainContact;
import vn.com.example.demoparsejson.utils.Constans;

public class DataASynctask extends AsyncTask<String, Void, String> {

    private MainContact.View mainView;

    public DataASynctask(MainContact.View mainView) {
        this.mainView = mainView;
    }

    @Override
    protected String doInBackground(String... strings) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            InputStream is = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        if (s != null) {
            parseJson(s);

        } else {
            mainView.getDataError(Constans.ERROR_GET_DATA);
        }
    }

    private void parseJson(String s)  {
        List<Data> datas = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(s);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                String title = object.getString(Constans.TITLE);
                String body = object.getString(Constans.BODY);
                int userId = object.getInt(Constans.USER_ID);
                int id = object.getInt(Constans.ID);
                datas.add(new Data(userId,id,title,body));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mainView.getDataSuccess(datas);
    }
}
