package cn.edu.hebtu.software.canteen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private int itemId;
    //后台实现数据
    private List<Map<String, Object>> list;
    // 用来控制CheckBox的选中状况
    private static Map<Integer,Boolean> isSelected;
    // 用来导入布局
    private LayoutInflater inflater = null;

    private static int checkNum = 0;

    public MyAdapter(Context context, int itemId, List<Map<String, Object>> list) {
        this.context = context;
        this.itemId = itemId;
        this.list = list;
        inflater = LayoutInflater.from(context);
        isSelected = new HashMap<Integer, Boolean>();
        initDate();
    }
    // 初始化isSelected的数据
    private void initDate(){
        for(int i=0; i<list.size();i++) {
            getIsSelected().put(i,false);
        }
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
        ViewHolder holder = null;
        if (convertView == null) {
            // 获得ViewHolder对象
            holder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(itemId, null);
            holder.imageView = (ImageView)convertView.findViewById(R.id.iv_img);
            holder.textView = (TextView)convertView.findViewById(R.id.tv_name);
            holder.vote = convertView.findViewById(R.id.btn_vote);
            // 为view设置标签
            convertView.setTag(holder);
        }else{
            // 取出holder
            holder = (ViewHolder) convertView.getTag();
        }

        /*设置TextView显示的内容，即我们存放在动态数组中的数据*/
        holder.imageView.setBackgroundResource((int)getList().get(position).get("img"));
        holder.textView.setText(getList().get(position).get("name").toString());
        // 根据isSelected来设置checkbox的选中状况
        holder.vote.setChecked(getIsSelected().get(position));
        CheckBox checkBox = convertView.findViewById(R.id.btn_vote);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true){
                    checkNum++;
                }else{
                    checkNum--;
                }
            }
        });
        return convertView;
    }


    public static Map<Integer,Boolean> getIsSelected() {
        return isSelected;
    }

    public static void setIsSelected(Map<Integer,Boolean> isSelected) {
        MyAdapter.isSelected = isSelected;
    }
    /** 创建 ViewHolder */
    class   ViewHolder{
        ImageView imageView;
        TextView textView;
        CheckBox vote;
    }
    public static List<Map<String, Object>> getList() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        //从数据库接受数据
        map1.put("img", R.drawable.sbj);
        map1.put("name", "三杯鸡");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("img", R.drawable.fmyz);
        map2.put("name", "蜂蜜柚子");
        Map<String, Object> map3 = new HashMap<>();
        map3.put("img", R.drawable.cqxm);
        map3.put("name", "重庆小面");
        Map<String, Object> map4 = new HashMap<>();
        map4.put("img", R.drawable.zjc);
        map4.put("name", "炸鸡翅");
        Map<String, Object> map5 = new HashMap<>();
        map5.put("img", R.drawable.pdsrz);
        map5.put("name", "皮蛋瘦肉粥");
        Map<String, Object> map6 = new HashMap<>();
        map6.put("img", R.drawable.lf);
        map6.put("name", "凉粉");
        Map<String, Object> map7 = new HashMap<>();
        map7.put("img", R.drawable.yb);
        map7.put("name", "鸭脖");
        Map<String, Object> map8 = new HashMap<>();
        map8.put("img", R.drawable.b1);
        map8.put("name", "三鲜包");
        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        list.add(map5);
        list.add(map6);
        list.add(map7);
        list.add(map8);
        return list;
    }

    public static int getCheckNum(){
        return checkNum;
    }

}
