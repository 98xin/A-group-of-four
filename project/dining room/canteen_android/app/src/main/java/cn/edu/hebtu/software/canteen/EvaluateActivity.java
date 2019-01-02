package cn.edu.hebtu.software.canteen;

import android.content.Context;
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
import android.widget.ListView;
import android.widget.TextView;

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

public class EvaluateActivity extends AppCompatActivity {
    private static final int WORK_TO_MAIN = 1;
    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate);

        Button contentReturn = findViewById(R.id.btn_evaluate_return);
        contentReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        AssessTask assessTask = new AssessTask();
        assessTask.execute();

        handler = new Handler() {
            @Override
            public void handleMessage(Message message) {
                switch (message.what) {
                    case WORK_TO_MAIN:
                        ListView listView=findViewById(R.id.lv_evaluate_view);
                        ListAdapter listAdapter = new ListAdapter(EvaluateActivity.this,R.layout.activity_evaluate_item,(List) message.obj);
                        listView.setAdapter(listAdapter);
                        break;
                    default:
                        break;
                }
            }
        };
    }

    public class ListAdapter extends BaseAdapter {
        private Context context;
        private int itemid;
        private List<Map<String,Object>> list;

        public ListAdapter(Context context, int itemid, List<Map<String,Object>> list) {
            this.context = context;
            this.itemid = itemid;
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
            if(convertView == null){
                LayoutInflater inflater=LayoutInflater.from(context);
                convertView=inflater.inflate(itemid,null);
            }
            TextView name = convertView.findViewById(R.id.tv_evaluate_user);
            TextView dish = convertView.findViewById(R.id.tv_evaluate_dish);
            TextView content = convertView.findViewById(R.id.tv_evaluate_content);

            Map<String,Object> map = list.get(position);
            name.setText((String)map.get("studentName"));
            dish.setText((String)map.get("foodName"));
            content.setText((String)map.get("assessContent"));
            return convertView;
        }
    }
    public class AssessTask extends AsyncTask{

        private  List<Map<String,Object>> assesses = new ArrayList<>();

        @Override
        protected Object doInBackground(Object[] objects) {
            //通过网络访问服务器端实现获取评论
            try {
                URL url = new URL("http://10.7.88.242:8080/canteen1/AssessServlet");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                //设置请求参数
                connection.setRequestProperty("contentType", "UTF-8");
                InputStream is = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(is);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String res = reader.readLine();
                //解析JSONArray字符串
                JSONArray array = new JSONArray(res);
                for (int i = 0 ; i < array.length(); i++){
                    JSONObject object = array.getJSONObject(i);

                    Map<String,Object> map = new HashMap<>();
                    map.put("studentName",object.getString("studentName"));
                    map.put("foodName",object.getString("foodName"));
                    map.put("assessContent",object.getString("assessContent"));

                    assesses.add(map);
                }
                Log.e("Assess","解析完有问题");
                return null;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Log.e("Assess","第二个异常");
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("Assess","IO异常");
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("Assess","json异常");
            }
            return assesses;
        }
        @Override
        protected void onPostExecute(Object o) {
            Message message = Message.obtain();
            message.what = 1;
            message.obj = assesses;
            handler.sendMessage(message);
        }
    }
}
