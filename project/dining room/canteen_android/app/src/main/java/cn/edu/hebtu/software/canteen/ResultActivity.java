package cn.edu.hebtu.software.canteen;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.Intent;
import android.content.SyncResult;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultActivity extends AppCompatActivity {

    private ListView listView;
    private ResultActivity.MyAdapter myAdapter;
    private Handler handler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Button btn = findViewById(R.id.btn_detailcontent_return);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this,VoteActivity.class);
                startActivity(intent);
            }
        });
        showResult show = new showResult();
        show.execute();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 1:
                        listView = findViewById(R.id.lv);
                        myAdapter = new ResultActivity.MyAdapter(ResultActivity.this,
                                R.layout.activity_resultlist, (List)msg.obj);
                        listView.setAdapter(myAdapter);
                        break;
                    default:
                        break;
                }

            }
        };

    }
    public class MyAdapter extends BaseAdapter {

        private Context context;
        private int itemId;
        //后台实现数据
        private List<Map<String, Object>> list;

        public MyAdapter(Context context, int itemId, List<Map<String, Object>> list) {
            this.context = context;
            this.itemId = itemId;
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(context);
                convertView = inflater.inflate(itemId, null);
            }
            ImageView imageView = convertView.findViewById(R.id.iv_img);
            TextView textView = convertView.findViewById(R.id.tv_name);
            TextView resultText = convertView.findViewById(R.id.btn_result);
            //将字体文件保存在assets/fonts/目录下，创建Typeface对象
            imageView.setImageResource((int) list.get(position).get("image"));
            textView.setText((String) list.get(position).get("foodName"));
            resultText.setText(String.valueOf(list.get(position).get("ballotNum"))+"票");
            return convertView;
        }

    }

    public class showResult extends AsyncTask<Void,Void,List> {

        private List<Map<String,Object>> mapList;
        @Override
        protected void onPreExecute() {
            mapList = new ArrayList<>();
        }

        @Override
        protected List doInBackground(Void... voids) {
            String path = "http://10.7.88.242:8080/canteen1/SelectBallotServlet";
            try {
                URL url = new URL(path);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("contentType", "utf-8");
                InputStream in = connection.getInputStream();
                InputStreamReader input = new InputStreamReader(in);
                BufferedReader buffer = new BufferedReader(input);

                String str = buffer.readLine();
                JSONArray array = new JSONArray(str);

                Map pmap = Picture.getPic();
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject json = array.getJSONObject(i);
                    Map<String, Object> map = new HashMap<>();
                    map.put("image", pmap.get(json.get("imageUrl").toString()));
                    map.put("foodName", json.get("foodName"));
                    map.put("ballotNum", json.get("ballotNum"));
                    mapList.add(map);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
                Log.e("test", e.toString());
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("test", e.toString());
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("test", e.toString());
            }
            return mapList;
        }
        protected void onPostExecute(List list) {
            Message message = Message.obtain();
            message.what = 1;
            message.obj = mapList;
            Log.e("test",message.obj.toString());
            handler.sendMessage(message);
        }
    }
}


