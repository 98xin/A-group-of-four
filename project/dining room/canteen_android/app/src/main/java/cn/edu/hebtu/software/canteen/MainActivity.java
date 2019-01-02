package cn.edu.hebtu.software.canteen;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends TabActivity implements ViewPager.OnPageChangeListener, View.OnTouchListener{

    //listview
    private List<Map<String,Object>> datalist;
    private ListView listView;

    //创建一个列表保存选项卡视图
    private List<View> viewList = new ArrayList<>();
    private String[] tabHostTag = {"tab1","tab2","tab3"};
    private int[] tabHostIconNormal = {R.drawable.index2_hb,R.drawable.cart2_hb,R.drawable.my_hb};
    private int[] tabHostIconSelect = {R.drawable.index2,R.drawable.cart2,R.drawable.my2};

    //轮播图
    public static final int VIEW_PAGER_DELAY = 2000;
    private MyPagerAdapter mAdapter;
    private List<ImageView> mItems;
    private ImageView[] mBottomImages;
    private LinearLayout mBottomLiner;
    private ViewPager mViewPager;
    private int currentViewPagerItem;
    //是否自动播放
    private boolean isAutoPlay;
    private MyHandler mHandler;
    private Thread mThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //listview
        listView=findViewById(R.id.lv_menu);
        initData();
        ListViewAdapter adapter=new ListViewAdapter(this,
                R.layout.menu_item,datalist);
        listView.setAdapter(adapter);

        //获取首页分类imageView，绑定监听事件跳转到商品列表页
        ImageView juice = findViewById(R.id.iv_index_sort_juice);//饮品
        ImageView chaocai = findViewById(R.id.iv_index_sort_chaocai);//炒菜
        ImageView noodle = findViewById(R.id.iv_index_sort_noodle);//面条
        ImageView hamburg = findViewById(R.id.iv_index_sort_hamburg);//汉堡
        ImageView porridge = findViewById(R.id.iv_index_sort_porridge);//粥
        ImageView cold = findViewById(R.id.iv_index_sort_cold);//凉菜
        ImageView halogen = findViewById(R.id.iv_index_sort_halogen);//卤味
        ImageView wheaten = findViewById(R.id.iv_index_sort_wheaten);//面食

        juice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Detail.class);
                intent.putExtra("sortName","饮料甜品");
                startActivity(intent);
            }
        });
        chaocai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Detail.class);
                intent.putExtra("sortName","炒菜");
                startActivity(intent);
            }
        });
        noodle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Detail.class);
                intent.putExtra("sortName","面条");
                startActivity(intent);
            }
        });
        hamburg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Detail.class);
                intent.putExtra("sortName","炸鸡汉堡");
                startActivity(intent);
            }
        });
        halogen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Detail.class);
                intent.putExtra("sortName","卤味");
                startActivity(intent);
            }
        });
        porridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Detail.class);
                intent.putExtra("sortName","粉面粥");
                startActivity(intent);
            }
        });
        cold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Detail.class);
                intent.putExtra("sortName","凉菜");
                startActivity(intent);
            }
        });
        wheaten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Detail.class);
                intent.putExtra("sortName","面食");
                startActivity(intent);
            }
        });


        //投票跳转
        //点击投票->投票专区
        Button btnToupiao = findViewById(R.id.btn_toupiao);
        btnToupiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent touPiaoIntent = new Intent();
                touPiaoIntent.setClass(MainActivity.this,VoteActivity.class);
                startActivity(touPiaoIntent);
            }
        });

        //1.获取tabhost控件
        TabHost tabHost = findViewById(android.R.id.tabhost);
        //2.对tabhost进行初始化
        tabHost.setup();
        tabHost.setup(this.getLocalActivityManager());
        //3.添加选项卡
        //第一个选项卡
        tabHost.addTab(tabHost.newTabSpec(tabHostTag[0])
                .setIndicator(getTabView("首页",tabHostIconNormal[0]))
                .setContent(R.id.tab1));
        //第二个选项卡,//链式调用
        tabHost.addTab(tabHost.newTabSpec(tabHostTag[1])
                .setIndicator(getTabView("订单",tabHostIconNormal[1]))
                .setContent(new Intent(this,OrderActivity.class)));
        //第三个选项卡
        tabHost.addTab(tabHost.newTabSpec(tabHostTag[2])
                .setIndicator(getTabView("我的",tabHostIconNormal[2]))
                .setContent(new Intent(this,MineActivity.class)));

        //给tabHost添加事件监听器
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                setTabHostIcon(tabId);
            }
        });
        setTabHostIcon(tabHost.getCurrentTabTag());

        //轮播图
        mHandler = new MyHandler(this);
        //配置轮播图ViewPager
        mViewPager = ((ViewPager) findViewById(R.id.live_view_pager));
        mItems = new ArrayList<>();
        mAdapter = new MyPagerAdapter(mItems, this);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnTouchListener(this);
        mViewPager.addOnPageChangeListener(this);
        isAutoPlay = true;
        //TODO: 添加ImageView
        addImageView();
        mAdapter.notifyDataSetChanged();
        //设置底部4个小点
        setBottomIndicator();
    }
    //listview
    private class ListViewAdapter extends BaseAdapter {
        private Context context;
        private int itemLayoutID;
        private List<Map<String,Object>> datalist;
        public ListViewAdapter(Context context,
                               int itemLayoutID,
                               List<Map<String ,Object>> datalist){
            this.context=context;
            this.itemLayoutID=itemLayoutID;
            this.datalist=datalist;
        }
        @Override
        public int getCount() {
            return datalist.size();
        }

        @Override
        public Object getItem(int position) {
            return datalist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView==null){
                LayoutInflater inflater=LayoutInflater.from(context);
                convertView =inflater.inflate(itemLayoutID,null);
            }
            ImageView imageView=convertView.findViewById(R.id.img_iv);
            TextView name=convertView.findViewById(R.id.txt_name);
            TextView age=convertView.findViewById(R.id.txt_price);
            Map<String,Object> map=datalist.get(position);
            imageView.setImageResource((int)map.get("image"));
            name.setText((String)map.get("name"));
            age.setText((String)map.get("price"));
            return convertView;
        }
    }
    private void initData(){
        int image[]={R.drawable.xgz,R.drawable.jjrs, R.drawable.dxm1,R.drawable.hb6, R.drawable.yb, R.drawable.bz1};
        String name[]={"西瓜汁","京酱肉丝","刀削面","麦辣鸡腿堡","鸭脖","猪肉大葱包"};
        String price[]={"4","5","4","10","5","1"};
        datalist=new ArrayList<Map<String,Object>>();
        for(int i=0;i<6;i++){
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("image",image[i]);
            map.put("name",name[i]);
            map.put("price",price[i]);
            datalist.add(map);
        }
    }
    //选项卡
    private void setTabHostIcon(String tabId){
        ImageView imageView1 = viewList.get(0).findViewById(R.id.iv_img);
        ImageView imageView2 = viewList.get(1).findViewById(R.id.iv_img);
        ImageView imageView3 = viewList.get(2).findViewById(R.id.iv_img);
        imageView1.setImageResource(R.drawable.index2_hb);
        imageView2.setImageResource(R.drawable.cart2_hb);
        imageView3.setImageResource(R.drawable.my_hb);

        if(tabId.equals(tabHostTag[0])) {
            ImageView imageView = viewList.get(0).findViewById(R.id.iv_img);
            imageView.setImageResource(tabHostIconSelect[0]);
        }
        else if(tabId.equals(tabHostTag[1])) {
            ImageView imageView = viewList.get(1).findViewById(R.id.iv_img);
            imageView.setImageResource(tabHostIconSelect[1]);
        }
        else {
            ImageView imageView = viewList.get(2).findViewById(R.id.iv_img);
            imageView.setImageResource(tabHostIconSelect[2]);
        }
    }
    //==用于判断原始数据类型
    private View getTabView(String text, int imageID){
        //1.通过布局填充器，根据布局文件创建视图对象
        View view = LayoutInflater.from(this).inflate(R.layout.tabview,null);
        //2.通过findViewById方法，找到相应控件
        TextView textView = view.findViewById(R.id.tv_text);
        ImageView imageView = view.findViewById(R.id.iv_img);
        //3.对相应控件进行设置
        textView.setText(text);
        imageView.setImageResource(imageID);
        //将创建好的选项卡视图控件放入viewList中
        viewList .add(view);
        //4.返回设置好的view
        return view;
    }

    //轮播图
    private void addImageView(){
        ImageView view0 = new ImageView(this);
        view0.setImageResource(R.drawable.banner6);

        ImageView view1 = new ImageView(this);
        view1.setImageResource(R.drawable.banner2);

        ImageView view2 = new ImageView(this);
        view2.setImageResource(R.drawable.banner5);

        view0.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view1.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view2.setScaleType(ImageView.ScaleType.CENTER_CROP);

        mItems.add(view0);
        mItems.add(view1);
        mItems.add(view2);
    }

    private void setBottomIndicator() {
        //获取指示器(下面三个小点)
        mBottomLiner = (LinearLayout) findViewById(R.id.live_indicator);

        //右下方小圆点
        mBottomImages = new ImageView[mItems.size()];
        for (int i = 0; i < mBottomImages.length; i++) {
            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            params.setMargins(5, 0, 5, 0);
            imageView.setLayoutParams(params);

            //如果当前是第一个 设置为选中状态
            if (i == 0) {
                imageView.setImageResource(R.drawable.select);
            } else {
                imageView.setImageResource(R.drawable.no_select);
            }
            mBottomImages[i] = imageView;
            //添加到父容器
            mBottomLiner.addView(imageView);
        }

        //让其在最大值的中间开始滑动, 在 mBottomImages初始化之前完成
        int mid = MyPagerAdapter.MAX_SCROLL_VALUE / 2;
        mViewPager.setCurrentItem(mid);
        currentViewPagerItem = mid;

        //定时发送消息
        mThread = new Thread(){
            @Override
            public void run() {
                super.run();
                while (true) {
                    mHandler.sendEmptyMessage(0);
                    try {
                        Thread.sleep(MainActivity.VIEW_PAGER_DELAY);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        mThread.start();
    }

    // ViewPager的监听事件

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currentViewPagerItem = position;
        if (mItems != null) {
            position %= mBottomImages.length;
            int total = mBottomImages.length;
            for (int i = 0; i < total; i++) {
                if (i == position) {
                    mBottomImages[i].setImageResource(R.drawable.select);
                } else {
                    mBottomImages[i].setImageResource(R.drawable.no_select);
                }
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                isAutoPlay = false;
                break;

            case MotionEvent.ACTION_UP:
                isAutoPlay = true;
                break;
        }
        return false;
    }

    // 为防止内存泄漏, 声明自己的Handler并弱引用Activity
    private static class MyHandler extends Handler {
        private WeakReference<MainActivity> mWeakReference;
        public MyHandler(MainActivity activity) {
            mWeakReference = new WeakReference<MainActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    MainActivity activity = mWeakReference.get();
                    if (activity.isAutoPlay) {
                        activity.mViewPager.setCurrentItem(++activity.currentViewPagerItem);
                    }
                    break;
            }
        }
    }
}

