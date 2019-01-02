package cn.edu.hebtu.software.canteen;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
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

public class BillActivity extends AppCompatActivity {

    private TextView priceText;
    private List<Map<String,Object>> list;
    private int price = 0;
    private Handler handler1;
    private ListView listView;
    private BillActivity.MyAdapter myAdapter;
    private ShowPoppupWindow showPoppupWindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        List<String> listTime = new ArrayList<String>();
        listTime.add("11:30");
        listTime.add("12:00");
        listTime.add("12:30");

        List<String> listPay = new ArrayList<String>();
        listPay.add("支付宝");
        listPay.add("微信");

        ArrayAdapter<String> adapterTime = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listTime);
        adapterTime.setDropDownViewResource(android.R.layout.simple_list_item_checked);

        ArrayAdapter<String> adapterPay = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listPay);
        adapterPay.setDropDownViewResource(android.R.layout.simple_list_item_checked);

        Spinner sp1 = (Spinner) findViewById(R.id.spinner1);
        Spinner sp2 = findViewById(R.id.spinner2);

        sp1.setAdapter(adapterTime);
        sp2.setAdapter(adapterPay);

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            // parent： 为控件Spinner view：显示文字的TextView position：下拉选项的位置从0开始
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tvResult = (TextView) findViewById(R.id.tvTime);
                //获取Spinner控件的适配器
                ArrayAdapter<String> adapter = (ArrayAdapter<String>) parent.getAdapter();
                tvResult.setText(adapter.getItem(position));
            }

            //没有选中时的处理
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tvResult = (TextView) findViewById(R.id.tvResult);
                //获取Spinner控件的适配器
                ArrayAdapter<String> adapter = (ArrayAdapter<String>) parent.getAdapter();
                tvResult.setText(adapter.getItem(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Cart cart = new Cart();
        cart.execute();
        handler1 = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 1:{
                        listView = findViewById(R.id.lv);
                        myAdapter = new MyAdapter(BillActivity.this,
                                R.layout.activity_billlist,(List)msg.obj);
                        list = (List)msg.obj;
                        for(int i = 0;i<list.size();++i){
                            price = price + Integer.parseInt(list.get(i).get("allPrice").toString());
                        }
                        priceText = findViewById(R.id.tv_price);
                        priceText.setText("￥"+String.valueOf(price)+".00");
                        listView.setAdapter(myAdapter);
                        break;
                    }
                    default:
                        break;
                }
            }
        };

        Button btnCount = findViewById(R.id.btn_count);
        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //实例化ShowPoppupWindow
                showPoppupWindow = new ShowPoppupWindow(BillActivity.this,
                        itemsOnClick,priceText.getText().toString());
                //显示窗口
                showPoppupWindow.showAtLocation(BillActivity.this.findViewById(R.id.poppup_main),
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                //设置layout在PopupWindow中显示的位置

            }
        });

    }

    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {

        public void onClick(View v) {
            showPoppupWindow.dismiss();
            switch (v.getId()) {
                case R.id.btn_deal:
                    Intent intent=new Intent();
                    intent.setClass(BillActivity.this,QRCodeActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_close:
                    break;
                default:
                    break;
            }
        }
    };

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
            TextView countView = convertView.findViewById(R.id.tv_counts);
            TextView priceView = convertView.findViewById(R.id.tv_result);
            imageView.setImageResource((int) list.get(position).get("image"));
            textView.setText((String) list.get(position).get("popupName1"));
            countView.setText(String.valueOf(list.get(position).get("allCounts")));
            priceView.setText(String.valueOf(list.get(position).get("allPrice")));
            return convertView;
        }

    }

    public class Cart extends AsyncTask<Void,Void,List> {

        private List<Map<String,Object>> mapList;
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

                Log.e("test",str);
                JSONArray array = new JSONArray(str);
                for(int i = 0;i<array.length();++i){
                    JSONObject json = array.getJSONObject(i);
                    Map pmap = Picture.getPic();
                    Map<String,Object> map = new HashMap<>();
                    map.put("image",pmap.get(json.get("image").toString()));
                    map.put("popupName1",json.get("name"));
                    map.put("allPrice",json.get("price"));
                    map.put("allCounts",json.get("counts"));
                    mapList.add(map);
                }
                JSONObject object = new JSONObject(str);
                Log.e("test",object.get("foodName").toString());
                Log.e("test",object.get("update").toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Log.e("test",e.toString());
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("test",e.toString());
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("test",e.toString());
            }
            return mapList;
        }

        @Override
        protected void onPostExecute(List list) {
            Message message = new Message();
            if(list != null){
                message.what = 1;
                message.obj = mapList;
            }else{
                message.what = 10;
            }
            handler1.sendMessage(message);
        }
    }
}