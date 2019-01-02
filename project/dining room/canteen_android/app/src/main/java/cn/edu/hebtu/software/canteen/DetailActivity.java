package cn.edu.hebtu.software.canteen;

import android.content.Context;
import android.content.Intent;
import android.net.Proxy;
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
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DetailActivity extends AppCompatActivity {
    private Handler handler;
    private String dprice;

    private static int b = 0;
    private MyAdapter adapter;
    private PopupWindow popupWindow;
    private static int m = 0;
    private ListView listView;
    private Button btnCart;
    //结算按钮
    private Button btnCalculate;
    private List<Map<String,Object>> list = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        final Intent intent = getIntent();
        ImageView image = findViewById(R.id.img_detail);
        TextView text = findViewById(R.id.tv_name);
        image.setBackgroundResource(intent.getIntExtra("image",0));
        text.setText(intent.getStringExtra("name"));
        dprice = intent.getStringExtra("price");

        //返回上级页面
        Button imgReturn = findViewById(R.id.btn_detailcontent_return);
        imgReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Cart cart = new Cart();
        cart.execute();

        handler = new Handler(){
            @Override
            public void handleMessage(Message message) {
                switch (message.what){
                    case 1:
                        adapter = new MyAdapter(DetailActivity.this,
                                R.layout.activity_cartcontent,(List)message.obj);
                        list = (List)message.obj;
                        break;
                    default:
                        break;
                }
            }
        };
        popupWindow = new PopupWindow(this);


        View view = View.inflate(this,R.layout.activity_cart,null);
        listView = view.findViewById(R.id.lv_cart);
        listView.setAdapter(adapter);

        btnCart = findViewById(R.id.btn_cart);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(popupWindow.isShowing()){
                    popupWindow.dismiss();
                }else{
                    showCart();
                }
            }
        });
        Button btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b++;
                View view = View.inflate(DetailActivity.this,R.layout.activity_cartcontent,null);
                TextView name = findViewById(R.id.tv_name);
                TextView price = view.findViewById(R.id.allPrice);
                TextView counts = view.findViewById(R.id.allCounts);

                boolean bool = true;

                for(int  i = 0;i<list.size();++i){
                    if(!name.getText().toString().equals(list.get(i).get("name1"))){
                        bool = true;
                    }else{
                        bool = false;
                        m = i;
                        break;
                    }
                }
                if(bool == true){
                    Map<String,Object> map = new HashMap<>();
                    map.put("name1",name.getText().toString());
                    map.put("allPrice",dprice);
                    map.put("allCounts","1");
                    list.add(map);
                    adapter.notifyDataSetChanged();
                    popupWindow.update();

                }else{
                    int i = Integer.parseInt(list.get(m).get("allPrice").toString());
                    int k = Integer.parseInt(list.get(m).get("allCounts").toString());
                    Map<String,Object> map = new HashMap<>();
                    map.put("name1",name.getText().toString());
                    map.put("allPrice",i+dprice);
                    map.put("allCounts",++k);
                    adapter.update(m,map.get("name1").toString(),map.get("allPrice").toString(),map.get("allCounts").toString());
                    adapter.notifyDataSetChanged();
                    popupWindow.update();
                }
                adapter.notifyDataSetChanged();
            }
        });

        //句
        //结算按钮绑定监听事件跳转到结算页面
        btnCalculate = findViewById(R.id.btn_cal);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(DetailActivity.this,BillActivity.class);
                startActivity(intent1);
            }
        });

        //处理列表页跳转

    }


    public void showCart(){
        View view = View.inflate(this,R.layout.activity_cart,null);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(400);

        listView = view.findViewById(R.id.lv_cart);
        listView.setAdapter(adapter);

        popupWindow.setContentView(view);
        popupWindow.update();


        popupWindow.setAnimationStyle(R.style.anim_menu_bottombar);
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int popupWidth = view.getMeasuredWidth();
        int popupHeight = view.getMeasuredWidth();
        int[] location = new int[2];
        btnCart.getLocationOnScreen(location);

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 1;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
        popupWindow.setBackgroundDrawable(
                getApplicationContext().getResources().getDrawable(R.drawable.white));

        popupWindow.showAtLocation(btnCart,
                Gravity.NO_GRAVITY,
                (location[0]+btnCart.getWidth()/2)-popupWidth/2,
                location[1]-popupHeight+2000);
    }

    public class MyAdapter extends BaseAdapter{

        private Context context;
        private int itemId;
        private List<Map<String,Object>> list;
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
        public View getView(final int position, View convertView, ViewGroup parent) {

            if(convertView == null){
                LayoutInflater inflater = LayoutInflater.from(context);
                convertView = inflater.inflate(itemId,null);
            }
            final TextView name1 = convertView.findViewById(R.id.tv_name1);
            final TextView allPrice = convertView.findViewById(R.id.allPrice);
            final TextView allCounts = convertView.findViewById(R.id.allCounts);

            Button btnDelete = convertView.findViewById(R.id.btn_delete);
            Button btnAdd1 = convertView.findViewById(R.id.btn_add1);
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = Integer.parseInt(allCounts.getText().toString().trim());
                    int k = Integer.parseInt(allPrice.getText().toString().trim());
                    int d = k/i;
                    i--;
                    if(i > 0){
                        allCounts.setText(String.valueOf(i));
                        allPrice.setText(String.valueOf(k-d));
                        update(position,name1.getText().toString(),allPrice.getText().toString(),String.valueOf(i));
                        popupWindow.update();
                    }else{
                        list.remove(position);
                        m=0;
                        adapter.notifyDataSetChanged();
                        popupWindow.update();
                    }
                }
            });
            btnAdd1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = Integer.parseInt(allCounts.getText().toString());
                    int k = Integer.parseInt(allPrice.getText().toString().trim());
                    int d = k/i;
                    i++;
                    allCounts.setText(String.valueOf(i));
                    allPrice.setText(String.valueOf(k+d));
                    update(position,name1.getText().toString(),allPrice.getText().toString(),String.valueOf(i));
                    //myAdapter.notifyDataSetChanged();
                    popupWindow.update();
                }
            });

            name1.setText((String)list.get(position).get("name1"));
            allPrice.setText(String.valueOf(list.get(position).get("allPrice")));
            allCounts.setText(String.valueOf(list.get(position).get("allCounts")));
            return convertView;
        }

        public void update(int position,String name1,String allPrice,String allCounts){
            int first = listView.getFirstVisiblePosition();
            View view = listView.getChildAt(position - first);
            Map<String,Object> map = new HashMap<>();
            map.put("name1",name1);
            map.put("allPrice",allPrice);
            map.put("allCounts",allCounts);
            list.set(position,map);
        }
    }

    public class Cart extends AsyncTask<Void,Void,List> {

        private List<Map<String,Object>> mapList;
        //        private Handler handler = new Handler();
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
                message.what = 2;
            }
            handler.sendMessage(message);
        }
    }
}
