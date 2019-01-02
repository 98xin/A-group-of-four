package cn.edu.hebtu.software.canteen;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart extends AsyncTask<Void,Void,List> {

    private final int CART = 1;
    private final int NO_CART = 2;
    private List<Map<String,Object>> mapList;
    Handler handler;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mapList = new ArrayList<>();
    }

    @Override
    protected List doInBackground(Void... voids) {
        String path = "http://10.7.88.242:8080/canteen1/CartServlet";
        try {
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("contentType","utf-8");
            InputStream in = connection.getInputStream();
            InputStreamReader input = new InputStreamReader(in);
            BufferedReader buffer = new BufferedReader(input);
            String str = buffer.readLine();

            JSONArray array = new JSONArray(str);
            for(int i = 0;i<array.length();++i){
                JSONObject json = array.getJSONObject(i);
                Map<String,Object> map = new HashMap<>();
                map.put("name1",json.get("name"));
                map.put("allPrice","12");
                map.put("allCounts","3");
                mapList.add(map);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mapList;
    }

    @Override
    protected void onPostExecute(List list) {
        Message message = new Message();
        if(list != null){
            message.what = CART;
            message.obj = list;
        }else{
            message.what = NO_CART;
        }
        Log.e("test",message.obj.toString());
        handler.sendMessage(message);
    }
}
