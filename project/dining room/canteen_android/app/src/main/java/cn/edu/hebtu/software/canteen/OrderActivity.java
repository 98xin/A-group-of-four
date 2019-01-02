package cn.edu.hebtu.software.canteen;

import android.content.Context;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import cn.edu.hebtu.software.canteen.Picture;

public class OrderActivity extends AppCompatActivity {
    private static final int WORK_TO_MAIN = 1;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Button btnReturn = findViewById(R.id.btn_return);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        DetailTask task = new DetailTask();
        task.execute();

        handler = new Handler(){
            @Override
            public void handleMessage(Message message) {
                switch (message.what){
                    case WORK_TO_MAIN:
                        ListView listView = findViewById(R.id.lv_list);
                        MyAdapter adapter = new MyAdapter(OrderActivity.this,R.layout.activity_ordercontent,(List) message.obj);
                        listView.setAdapter(adapter);
                        break;
                    default:
                        break;
                }
            }
        };
    }

    public class MyAdapter extends BaseAdapter {
        private List<Map<String,Object>> list;
        private Context context;
        private int itemId;

        public MyAdapter(Context context,int itemId,List<Map<String,Object>> list){
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
            if(convertView == null){
                LayoutInflater inflater = LayoutInflater.from(context);
                convertView = inflater.inflate(itemId,null);
            }
            ImageView imageUrl = convertView.findViewById(R.id.img_name);
            TextView orderStatement = convertView.findViewById(R.id.tv_status);
            TextView foodName = convertView.findViewById(R.id.tv_names);
            TextView foodPrice = convertView.findViewById(R.id.tv_price);
            TextView foodNum = convertView.findViewById(R.id.tv_count);
            TextView orderTp = convertView.findViewById(R.id.tv_amount);

            Map<String,Object> map = list.get(position);
            imageUrl.setImageResource((int)map.get("imageUrl"));
            orderStatement.setText((String)map.get("orderStatement"));
            foodName.setText((String)map.get("foodName"));
            foodPrice.setText("单价￥"+(String)map. get("foodPrice"));
            //foodNum.setText("数量×"+(String)map.get("foodNum"));
            orderTp.setText("合计￥"+(String)map.get("orderTp"));

            final Button btnReceive = convertView.findViewById(R.id.btn_receive);
            btnReceive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(OrderActivity.this,"收货成功", Toast.LENGTH_SHORT).show();
                    btnReceive.setEnabled(false);
                }
            });

            return convertView;
        }
    }

    public class DetailTask extends AsyncTask{

        private  List<Map<String,Object>> details = new ArrayList<>();

        @Override
        protected Object doInBackground(Object[] objects) {
            //通过网络访问服务器端实现获取评论
            try {
                URL url = new URL("http://10.7.88.242:8080/canteen1/DetailServlet");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                //设置请求参数
                connection.setRequestProperty("contentType", "UTF-8");
                InputStream is = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(is);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String res = reader.readLine();

                //解析JSONArray字符串
                JSONArray array = new JSONArray(res);
                Map pmap = Picture.getPic();

                for (int i = 0 ; i < array.length(); i++){
                    JSONObject object = array.getJSONObject(i);

                    Map<String,Object> map = new HashMap<>();
                    map.put("foodName",object.getString("foodName"));
                    map.put("foodNum",object.getInt("foodNum")+"");
                    map.put("orderStatement",object.getString("orderStatement"));
                    map.put("orderTp",object.getInt("orderTp")+"");
                    map.put("foodPrice",object.getInt("foodPrice")+"");
                    map.put("imageUrl",pmap.get(object.get("imageUrl").toString()));

                    details.add(map);
                }
                return null;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Log.e("Detail","第二个异常");
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("Detail","IO异常");
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("Detail","最后的json异常");
            }
            return details;
        }

        @Override
        protected void onPostExecute(Object o) {
            Message message = Message.obtain();
            message.what = 1;
            message.obj = details;
            handler.sendMessage(message);
        }
    }
}
