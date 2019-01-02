package cn.edu.hebtu.software.canteen;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.PathMeasure;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Detail extends AppCompatActivity {

    private Handler handler;
    private Handler handler1;
    private ListAdapter listAdapter;
    private ListView listView;

    private static int b = 0;
    private static int c = 0;
    private List<Map<String,Object>> mapList;

    private static int m = 0;
    private MyAdapter myAdapter;
    private ListView popupListView;
    private PopupWindow popupWindow;
    private Button showCart;

    private Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_detail);

        Cart cart = new Cart();
        cart.execute();

        handler1 = new Handler(){
            @Override
            public void handleMessage(Message message) {
                switch (message.what){
                    case 1:
                        myAdapter = new MyAdapter(Detail.this,
                                R.layout.activity_cartcontent,(List)message.obj);
                        mapList = (List)message.obj;
                        break;
                    default:
                        break;
                }
            }
        };

        ShowDrinkDetail showDrink = new ShowDrinkDetail();
        showDrink.execute();
        ShowCookDetail showCook = new ShowCookDetail();
        showCook.execute();
        ShowNoodlesDetail showNoodles = new ShowNoodlesDetail();
        showNoodles.execute();
        ShowHamburgDetail showHamburg = new ShowHamburgDetail();
        showHamburg.execute();
        ShowPorridgeDetail showPorridge = new ShowPorridgeDetail();
        showPorridge.execute();
        ShowColdDetail showCold = new ShowColdDetail();
        showCold.execute();
        ShowHalogenDetail showHalogen = new ShowHalogenDetail();
        showHalogen.execute();
        ShowWheatenDetail showWheaten = new ShowWheatenDetail();
        showWheaten.execute();
        Intent intent = getIntent();
        TextView detailSort  = findViewById(R.id.tv_detail_sortname);
        detailSort.setText(intent.getStringExtra("sortName"));

        if(detailSort.getText().toString().equals("饮料甜品")) {
            handler = new Handler() {
                @Override
                public void handleMessage(Message message1) {
                    switch (message1.what) {
                        case 2:
                            listView = findViewById(R.id.lv_detail_view);
                            listAdapter = new ListAdapter(Detail.this,
                                    R.layout.activity_detail, (List) message1.obj);
                            listView.setAdapter(listAdapter);
                            break;
                        default:
                            break;
                    }
                }
            };
        }else if(detailSort.getText().toString().equals("炒菜")){
            handler = new Handler() {
                @Override
                public void handleMessage(Message message1) {
                    switch (message1.what) {
                        case 3:
                            listView = findViewById(R.id.lv_detail_view);
                            listAdapter = new ListAdapter(Detail.this,
                                    R.layout.activity_detail, (List) message1.obj);
                            listView.setAdapter(listAdapter);
                            break;
                        default:
                            break;
                    }
                }
            };
        }else if(detailSort.getText().toString().equals("面条")){
            handler = new Handler() {
                @Override
                public void handleMessage(Message message1) {
                    switch (message1.what) {
                        case 4:
                            listView = findViewById(R.id.lv_detail_view);
                            listAdapter = new ListAdapter(Detail.this,
                                    R.layout.activity_detail, (List) message1.obj);
                            listView.setAdapter(listAdapter);
                            break;
                        default:
                            break;
                    }
                }
            };
        }else if(detailSort.getText().toString().equals("炸鸡汉堡")){
            handler = new Handler() {
                @Override
                public void handleMessage(Message message1) {
                    switch (message1.what) {
                        case 5:
                            listView = findViewById(R.id.lv_detail_view);
                            listAdapter = new ListAdapter(Detail.this,
                                    R.layout.activity_detail, (List) message1.obj);
                            listView.setAdapter(listAdapter);
                            break;
                        default:
                            break;
                    }
                }
            };
        }else if(detailSort.getText().toString().equals("粉面粥")){
            handler = new Handler() {
                @Override
                public void handleMessage(Message message1) {
                    switch (message1.what) {
                        case 6:
                            listView = findViewById(R.id.lv_detail_view);
                            listAdapter = new ListAdapter(Detail.this,
                                    R.layout.activity_detail, (List) message1.obj);
                            listView.setAdapter(listAdapter);
                            break;
                        default:
                            break;
                    }
                }
            };
        }else if(detailSort.getText().toString().equals("凉菜")){
            handler = new Handler() {
                @Override
                public void handleMessage(Message message1) {
                    switch (message1.what) {
                        case 7:
                            listView = findViewById(R.id.lv_detail_view);
                            listAdapter = new ListAdapter(Detail.this,
                                    R.layout.activity_detail, (List) message1.obj);
                            listView.setAdapter(listAdapter);
                            break;
                        default:
                            break;
                    }
                }
            };
        }else if(detailSort.getText().toString().equals("卤味")){
            handler = new Handler() {
                @Override
                public void handleMessage(Message message1) {
                    switch (message1.what) {
                        case 8:
                            listView = findViewById(R.id.lv_detail_view);
                            listAdapter = new ListAdapter(Detail.this,
                                    R.layout.activity_detail, (List) message1.obj);
                            listView.setAdapter(listAdapter);
                            break;
                        default:
                            break;
                    }
                }
            };
        }else if(detailSort.getText().toString().equals("面食")){
            handler = new Handler() {
                @Override
                public void handleMessage(Message message1) {
                    switch (message1.what) {
                        case 9:
                            listView = findViewById(R.id.lv_detail_view);
                            listAdapter = new ListAdapter(Detail.this,
                                    R.layout.activity_detail, (List) message1.obj);
                            listView.setAdapter(listAdapter);
                            break;
                        default:
                            break;
                    }
                }
            };
        }

        popupWindow = new PopupWindow(this);
        showCart = findViewById(R.id.btn_detail_add);

        View view = View.inflate(this,R.layout.activity_shoppingcart_popup,null);
        popupListView = view.findViewById(R.id.lv_cart);
        popupListView.setAdapter(myAdapter);

        //从商品列表页跳转回首页
        Button detailReturn = findViewById(R.id.btn_detail_return);
        detailReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        showCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(popupWindow.isShowing()){
                    popupWindow.dismiss();
                }else{
                    showCart();
                }
            }
        });

        btnCalculate = findViewById(R.id.btn_detail_charge);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(Detail.this,BillActivity.class);
                startActivity(intent1);
            }
        });
    }

    public void showCart(){
        View view = View.inflate(this,R.layout.activity_shoppingcart_popup,null);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(440);

        popupWindow.setAnimationStyle(R.style.anim_menu_bottombar);
        popupWindow.setBackgroundDrawable(
                getApplicationContext().getResources().getDrawable(R.drawable.white));

        popupListView = view.findViewById(R.id.lv_cart);
        popupListView.setAdapter(myAdapter);

        popupWindow.setContentView(view);
        popupWindow.update();

        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int popupWidth = view.getMeasuredWidth();
        int popupHeight = view.getMeasuredWidth();
        int[] location = new int[2];
        showCart.getLocationOnScreen(location);

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 1;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
        //调小是往上,调大是往下
        popupWindow.showAtLocation(showCart,
                Gravity.NO_GRAVITY,
                (location[0]+showCart.getWidth()/2)-popupWidth/2,
                location[1]-popupHeight+1920);
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
                        mapList.remove(position);
                        m=0;
                        myAdapter.notifyDataSetChanged();
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
                    popupWindow.update();
                }
            });

            name1.setText((String)list.get(position).get("popupName1"));
            allPrice.setText(String.valueOf(list.get(position).get("allPrice")));
            allCounts.setText(String.valueOf(list.get(position).get("allCounts")));
            return convertView;
        }

        public void update(int position,String name1,String allPrice,String allCounts){
            int first = popupListView.getFirstVisiblePosition();
            View view = popupListView.getChildAt(position - first);
            Map<String,Object> map = new HashMap<>();
            map.put("popupName1",name1);
            map.put("allPrice",allPrice);
            map.put("allCounts",allCounts);
            list.set(position,map);
        }
    }
    public class ListAdapter extends BaseAdapter {
        private Context context;
        private int itemid;
        private List<Map<String,Object>> list;

        public ListAdapter(Context context,int itemid,List<Map<String,Object>> list){
            this.context=context;
            this.itemid=itemid;
            this.list=list;
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
            if(convertView==null){
                LayoutInflater inflater=LayoutInflater.from(context);
                convertView=inflater.inflate(itemid,null);
            }
            ImageView image1=convertView.findViewById(R.id.iv_detail_image1);
            ImageView image2=convertView.findViewById(R.id.iv_detail_image2);
            final TextView name1=convertView.findViewById(R.id.tv_detail_name1);
            final TextView name2=convertView.findViewById(R.id.tv_detail_name2);
            final TextView detailPrice1=convertView.findViewById(R.id.tv_detail_price1);
            final TextView detailPrice2=convertView.findViewById(R.id.tv_detail_price2);
            image1.setBackgroundResource(Integer.parseInt(list.get(position).get("image1").toString()));
            image2.setBackgroundResource(Integer.parseInt(list.get(position).get("image2").toString()));
            name1.setText((String)list.get(position).get("name1"));
            name2.setText((String)list.get(position).get("name2"));
            detailPrice1.setText(String.valueOf(list.get(position).get("detailPrice1")));
            detailPrice2.setText(String.valueOf(list.get(position).get("detailPrice2")));

            //获取商品列表页的添加购物车按钮，并绑定点击监听事件
            Button btnDetailAdd1 = convertView.findViewById(R.id.btn_detail_add1);
            Button btnDetailAdd2 = convertView.findViewById(R.id.btn_detail_add2);

            //获取商品列表页的ImageView，绑定监听事件，跳转到商品详情页
            ImageView detailImage1 = convertView.findViewById(R.id.iv_detail_image1);
            ImageView detailImage2 = convertView.findViewById(R.id.iv_detail_image2);

            btnDetailAdd1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("test",((String) list.get(position).get("name1")).toString());
                    b++;

                    boolean bool = true;

                    for(int  i = 0;i<mapList.size();++i){
                        if(!name1.getText().toString().equals(mapList.get(i).get("popupName1"))){
                            bool = true;
                        }else{
                            bool = false;
                            m = i;
                            break;
                        }
                    }

                    if(bool == true){
                        Map<String,Object> map = new HashMap<>();
                        map.put("popupName1",name1.getText().toString());
                        map.put("allPrice",detailPrice1.getText().toString());
                        map.put("allCounts","1");
                        mapList.add(map);
                        myAdapter.notifyDataSetChanged();
                        popupWindow.update();
                    }else{
                        int i = Integer.parseInt(mapList.get(m).get("allPrice").toString());
                        int d = Integer.parseInt(detailPrice1.getText().toString().trim());
                        int k = Integer.parseInt(mapList.get(m).get("allCounts").toString());
                        Map<String,Object> map = new HashMap<>();
                        map.put("popupName1",name1.getText().toString());
                        map.put("allPrice",String.valueOf(i+d));
                        map.put("allCounts",++k);
                        myAdapter.update(m,map.get("popupName1").toString(),map.get("allPrice").toString(),map.get("allCounts").toString());
                        myAdapter.notifyDataSetChanged();
                        popupWindow.update();
                    }
                }
            });
            btnDetailAdd2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("test",((String) list.get(position).get("name2")).toString());
                    c++;
                    boolean bool = true;

                    for(int  i = 0;i<mapList.size();++i){
                        if(!name2.getText().toString().equals(mapList.get(i).get("popupName1"))){
                            bool = true;
                        }else{
                            bool = false;
                            m = i;
                            break;
                        }
                    }

                    if(bool == true){
                        Map<String,Object> map = new HashMap<>();
                        map.put("popupName1",name2.getText().toString());
                        map.put("allPrice",detailPrice2.getText().toString());
                        map.put("allCounts","1");
                        mapList.add(map);
                        myAdapter.notifyDataSetChanged();
                        popupWindow.update();
                    }else{
                        int i = Integer.parseInt(mapList.get(m).get("allPrice").toString());
                        int d = Integer.parseInt(detailPrice2.getText().toString().trim());
                        int k = Integer.parseInt(mapList.get(m).get("allCounts").toString());
                        Map<String,Object> map = new HashMap<>();
                        map.put("popupName1",name2.getText().toString());
                        map.put("allPrice",String.valueOf(i+d));
                        map.put("allCounts",++k);
                        myAdapter.update(m,map.get("popupName1").toString(),map.get("allPrice").toString(),map.get("allCounts").toString());
                        myAdapter.notifyDataSetChanged();
                        popupWindow.update();
                    }
                }
            });
            detailImage1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Detail.this,DetailActivity.class);
                    intent.putExtra("name",(String)list.get(position).get("name1"));
                    intent.putExtra("price",String.valueOf(list.get(position).get("detailPrice1")));
                    intent.putExtra("image",(int)list.get(position).get("image1"));
                    startActivity(intent);
                }
            });
            detailImage2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Detail.this,DetailActivity.class);
                    intent.putExtra("name",(String)list.get(position).get("name2"));
                    intent.putExtra("price",String.valueOf(list.get(position).get("detailPrice2")));
                    intent.putExtra("image",(int)list.get(position).get("image2"));
                    startActivity(intent);
                }
            });
            return convertView;
        }
    }

    public class ShowDrinkDetail extends AsyncTask<Void,Void,List>{

        private List<Map<String,Object>> mapList1;
        @Override
        protected void onPreExecute() {
            mapList1 = new ArrayList<>();
        }

        @Override
        protected List doInBackground(Void... voids) {
            String path = "http://10.7.88.242:8080/canteen1/SelectDrink";
            try {
                URL url = new URL(path);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("contentType","utf-8");
                InputStream in = connection.getInputStream();
                InputStreamReader input = new InputStreamReader(in);
                BufferedReader buffer = new BufferedReader(input);

                String str = buffer.readLine();

                JSONArray array1 = new JSONArray(str);

                Map pmap = Picture.getPic();
                for(int i = 0;i<array1.length();i+=2){
                    JSONObject json1 = array1.getJSONObject(i);
                    JSONObject json2 = array1.getJSONObject(i+1);

                    Map<String,Object> map = new HashMap<>();
                    map.put("image1",pmap.get(json1.get("foodImageUrl").toString()));
                    map.put("name1",json1.get("foodName"));
                    map.put("detailPrice1",json1.get("foodPrice"));
                    map.put("image2",pmap.get(json2.get("foodImageUrl").toString()));
                    map.put("name2",json2.get("foodName"));
                    map.put("detailPrice2",json2.get("foodPrice"));
                    mapList1.add(map);
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
            return mapList1;
        }

        @Override
        protected void onPostExecute(List l) {
            Message message1 = Message.obtain();
            if(l != null){
                message1.what = 2;
                message1.obj = mapList1;
            }else{
                message1.what = 9;
            }

            handler.sendMessage(message1);
        }
    }

    public class ShowCookDetail extends AsyncTask<Void,Void,List>{

        private List<Map<String,Object>> mapList1;
        @Override
        protected void onPreExecute() {
            mapList1 = new ArrayList<>();
        }

        @Override
        protected List doInBackground(Void... voids) {
            String path = "http://10.7.88.242:8080/canteen1/SelectCook";
            try {
                URL url = new URL(path);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("contentType","utf-8");
                InputStream in = connection.getInputStream();
                InputStreamReader input = new InputStreamReader(in);
                BufferedReader buffer = new BufferedReader(input);

                String str = buffer.readLine();

                JSONArray array1 = new JSONArray(str);

                Map pmap = Picture.getPic();
                for(int i = 0;i<array1.length();i+=2){
                    JSONObject json1 = array1.getJSONObject(i);
                    JSONObject json2 = array1.getJSONObject(i+1);

                    Map<String,Object> map = new HashMap<>();
                    map.put("image1",pmap.get(json1.get("foodImageUrl").toString()));
                    map.put("name1",json1.get("foodName"));
                    map.put("detailPrice1",json1.get("foodPrice"));
                    map.put("image2",pmap.get(json2.get("foodImageUrl").toString()));
                    map.put("name2",json2.get("foodName"));
                    map.put("detailPrice2",json2.get("foodPrice"));
                    mapList1.add(map);
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
            return mapList1;
        }

        @Override
        protected void onPostExecute(List l) {
            Message message1 = Message.obtain();
            if(l != null){
                message1.what = 3;
                message1.obj = mapList1;
            }else{
                message1.what = 10;
            }

            handler.sendMessage(message1);
        }
    }

    public class ShowNoodlesDetail extends AsyncTask<Void,Void,List>{

        private List<Map<String,Object>> mapList1;
        @Override
        protected void onPreExecute() {
            mapList1 = new ArrayList<>();
        }

        @Override
        protected List doInBackground(Void... voids) {
            String path = "http://10.7.88.242:8080/canteen1/SelectNoodles";
            try {
                URL url = new URL(path);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("contentType","utf-8");
                InputStream in = connection.getInputStream();
                InputStreamReader input = new InputStreamReader(in);
                BufferedReader buffer = new BufferedReader(input);

                String str = buffer.readLine();

                JSONArray array1 = new JSONArray(str);

                Map pmap = Picture.getPic();
                for(int i = 0;i<array1.length();i+=2){
                    JSONObject json1 = array1.getJSONObject(i);
                    JSONObject json2 = array1.getJSONObject(i+1);

                    Map<String,Object> map = new HashMap<>();
                    map.put("image1",pmap.get(json1.get("foodImageUrl").toString()));
                    map.put("name1",json1.get("foodName"));
                    map.put("detailPrice1",json1.get("foodPrice"));
                    map.put("image2",pmap.get(json2.get("foodImageUrl").toString()));
                    map.put("name2",json2.get("foodName"));
                    map.put("detailPrice2",json2.get("foodPrice"));
                    mapList1.add(map);
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
            return mapList1;
        }

        @Override
        protected void onPostExecute(List l) {
            Message message1 = Message.obtain();
            if(l != null){
                message1.what = 4;
                message1.obj = mapList1;
            }else{
                message1.what = 12;
            }

            handler.sendMessage(message1);
        }
    }

    public class ShowHamburgDetail extends AsyncTask<Void,Void,List>{

        private List<Map<String,Object>> mapList1;
        @Override
        protected void onPreExecute() {
            mapList1 = new ArrayList<>();
        }

        @Override
        protected List doInBackground(Void... voids) {
            String path = "http://10.7.88.242:8080/canteen1/SelectHamburg";
            try {
                URL url = new URL(path);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("contentType","utf-8");
                InputStream in = connection.getInputStream();
                InputStreamReader input = new InputStreamReader(in);
                BufferedReader buffer = new BufferedReader(input);

                String str = buffer.readLine();

                JSONArray array1 = new JSONArray(str);

                Map pmap = Picture.getPic();
                for(int i = 0;i<array1.length();i+=2){
                    JSONObject json1 = array1.getJSONObject(i);
                    JSONObject json2 = array1.getJSONObject(i+1);

                    Map<String,Object> map = new HashMap<>();
                    map.put("image1",pmap.get(json1.get("foodImageUrl").toString()));
                    map.put("name1",json1.get("foodName"));
                    map.put("detailPrice1",json1.get("foodPrice"));
                    map.put("image2",pmap.get(json2.get("foodImageUrl").toString()));
                    map.put("name2",json2.get("foodName"));
                    map.put("detailPrice2",json2.get("foodPrice"));
                    mapList1.add(map);
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
            return mapList1;
        }

        @Override
        protected void onPostExecute(List l) {
            Message message1 = Message.obtain();
            if(l != null){
                message1.what = 5;
                message1.obj = mapList1;
            }else{
                message1.what = 13;
            }

            handler.sendMessage(message1);
        }
    }

    public class ShowPorridgeDetail extends AsyncTask<Void,Void,List>{

        private List<Map<String,Object>> mapList1;
        @Override
        protected void onPreExecute() {
            mapList1 = new ArrayList<>();
        }

        @Override
        protected List doInBackground(Void... voids) {
            String path = "http://10.7.88.242:8080/canteen1/SelectPorridge";
            try {
                URL url = new URL(path);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("contentType","utf-8");
                InputStream in = connection.getInputStream();
                InputStreamReader input = new InputStreamReader(in);
                BufferedReader buffer = new BufferedReader(input);

                String str = buffer.readLine();

                JSONArray array1 = new JSONArray(str);

                Map pmap = Picture.getPic();
                for(int i = 0;i<array1.length();i+=2){
                    JSONObject json1 = array1.getJSONObject(i);
                    JSONObject json2 = array1.getJSONObject(i+1);

                    Map<String,Object> map = new HashMap<>();
                    map.put("image1",pmap.get(json1.get("foodImageUrl").toString()));
                    map.put("name1",json1.get("foodName"));
                    map.put("detailPrice1",json1.get("foodPrice"));
                    map.put("image2",pmap.get(json2.get("foodImageUrl").toString()));
                    map.put("name2",json2.get("foodName"));
                    map.put("detailPrice2",json2.get("foodPrice"));
                    mapList1.add(map);
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
            return mapList1;
        }

        @Override
        protected void onPostExecute(List l) {
            Message message1 = Message.obtain();
            if(l != null){
                message1.what = 6;
                message1.obj = mapList1;
            }else{
                message1.what = 14;
            }
            handler.sendMessage(message1);
        }
    }

    public class ShowColdDetail extends AsyncTask<Void,Void,List>{

        private List<Map<String,Object>> mapList1;
        @Override
        protected void onPreExecute() {
            mapList1 = new ArrayList<>();
        }

        @Override
        protected List doInBackground(Void... voids) {
            String path = "http://10.7.88.242:8080/canteen1/SelectCold";
            try {
                URL url = new URL(path);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("contentType","utf-8");
                InputStream in = connection.getInputStream();
                InputStreamReader input = new InputStreamReader(in);
                BufferedReader buffer = new BufferedReader(input);

                String str = buffer.readLine();

                JSONArray array1 = new JSONArray(str);

                Map pmap = Picture.getPic();
                for(int i = 0;i<array1.length();i+=2){
                    JSONObject json1 = array1.getJSONObject(i);
                    JSONObject json2 = array1.getJSONObject(i+1);

                    Map<String,Object> map = new HashMap<>();
                    map.put("image1",pmap.get(json1.get("foodImageUrl").toString()));
                    map.put("name1",json1.get("foodName"));
                    map.put("detailPrice1",json1.get("foodPrice"));
                    map.put("image2",pmap.get(json2.get("foodImageUrl").toString()));
                    map.put("name2",json2.get("foodName"));
                    map.put("detailPrice2",json2.get("foodPrice"));
                    mapList1.add(map);
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
            return mapList1;
        }

        @Override
        protected void onPostExecute(List l) {
            Message message1 = Message.obtain();
            if(l != null){
                message1.what = 7;
                message1.obj = mapList1;
            }else{
                message1.what = 15;
            }

            handler.sendMessage(message1);
        }
    }

    public class ShowHalogenDetail extends AsyncTask<Void,Void,List>{

        private List<Map<String,Object>> mapList1;
        @Override
        protected void onPreExecute() {
            mapList1 = new ArrayList<>();
        }

        @Override
        protected List doInBackground(Void... voids) {
            String path = "http://10.7.88.242:8080/canteen1/SelectHalogen";
            try {
                URL url = new URL(path);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("contentType","utf-8");
                InputStream in = connection.getInputStream();
                InputStreamReader input = new InputStreamReader(in);
                BufferedReader buffer = new BufferedReader(input);

                String str = buffer.readLine();

                JSONArray array1 = new JSONArray(str);

                Map pmap = Picture.getPic();
                for(int i = 0;i<array1.length();i+=2){
                    JSONObject json1 = array1.getJSONObject(i);
                    JSONObject json2 = array1.getJSONObject(i+1);

                    Map<String,Object> map = new HashMap<>();
                    map.put("image1",pmap.get(json1.get("foodImageUrl").toString()));
                    map.put("name1",json1.get("foodName"));
                    map.put("detailPrice1",json1.get("foodPrice"));
                    map.put("image2",pmap.get(json2.get("foodImageUrl").toString()));
                    map.put("name2",json2.get("foodName"));
                    map.put("detailPrice2",json2.get("foodPrice"));
                    mapList1.add(map);
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
            return mapList1;
        }

        @Override
        protected void onPostExecute(List l) {
            Message message1 = Message.obtain();
            if(l != null){
                message1.what = 8;
                message1.obj = mapList1;
            }else{
                message1.what = 16;
            }

            handler.sendMessage(message1);
        }
    }

    public class ShowWheatenDetail extends AsyncTask<Void,Void,List>{

        private List<Map<String,Object>> mapList1;
        @Override
        protected void onPreExecute() {
            mapList1 = new ArrayList<>();
        }

        @Override
        protected List doInBackground(Void... voids) {
            String path = "http://10.7.88.242:8080/canteen1/SelectWheaten";
            try {
                URL url = new URL(path);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("contentType","utf-8");
                InputStream in = connection.getInputStream();
                InputStreamReader input = new InputStreamReader(in);
                BufferedReader buffer = new BufferedReader(input);

                String str = buffer.readLine();

                JSONArray array1 = new JSONArray(str);

                Map pmap = Picture.getPic();
                for(int i = 0;i<array1.length();i+=2){
                    JSONObject json1 = array1.getJSONObject(i);
                    JSONObject json2 = array1.getJSONObject(i+1);

                    Map<String,Object> map = new HashMap<>();
                    map.put("image1",pmap.get(json1.get("foodImageUrl").toString()));
                    map.put("name1",json1.get("foodName"));
                    map.put("detailPrice1",json1.get("foodPrice"));
                    map.put("image2",pmap.get(json2.get("foodImageUrl").toString()));
                    map.put("name2",json2.get("foodName"));
                    map.put("detailPrice2",json2.get("foodPrice"));
                    mapList1.add(map);
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
            return mapList1;
        }

        @Override
        protected void onPostExecute(List l) {
            Message message1 = Message.obtain();
            if(l != null){
                message1.what = 9;
                message1.obj = mapList1;
            }else{
                message1.what = 17;
            }

            handler.sendMessage(message1);
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

                JSONArray array = new JSONArray(str);
                for(int i = 0;i<array.length();++i){
                    JSONObject json = array.getJSONObject(i);
                    Map<String,Object> map = new HashMap<>();
                    map.put("popupName1",json.get("name"));
                    map.put("allPrice",json.get("price"));
                    map.put("allCounts",json.get("counts"));
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
                message.what = 10;
            }
            handler1.sendMessage(message);
        }
    }
}