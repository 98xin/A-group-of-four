package cn.edu.hebtu.software.canteen;

import android.content.Context;
import android.graphics.Picture;
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

public class ManagerVoteActivity extends AppCompatActivity {

    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_vote);

        Button voteReturn = findViewById(R.id.btn_vote_return);
        voteReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ShowBallot ballot = new ShowBallot();
        ballot.execute();

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 1:
                        ListView listView=findViewById(R.id.lv_manager_vote_view);
                        ListAdapter listAdapter = new ListAdapter(ManagerVoteActivity.this,
                                R.layout.activity_manager_vote_item,(List)msg.obj);
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
        private List<Map<String, Object>> list;

        public ListAdapter(Context context, int itemid, List<Map<String, Object>> list) {
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
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(context);
                convertView = inflater.inflate(itemid, null);
            }
            ImageView voteImg = convertView.findViewById(R.id.iv_manager_vote1);
            TextView name = convertView.findViewById(R.id.tv_manager_vote_name);
            TextView count = convertView.findViewById(R.id.tv_manager_vote_count);
            voteImg.setBackgroundResource((int)list.get(position).get("image"));
            name.setText((String) list.get(position).get("foodName"));
            count.setText(String.valueOf(list.get(position).get("ballotNum")));
            return convertView;
        }
    }

    public class ShowBallot extends AsyncTask<Void,Void,List>{

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
                connection.setRequestProperty("contentType","utf-8");
                InputStream in = connection.getInputStream();
                InputStreamReader input = new InputStreamReader(in);
                BufferedReader buffer = new BufferedReader(input);

                String str = buffer.readLine();
                Log.e("text",str);
                JSONArray array = new JSONArray(str);

                Map pmap = cn.edu.hebtu.software.canteen.Picture.getPic();
                for(int i=0;i<array.length();++i){
                    JSONObject json=array.getJSONObject(i);

                    Log.e("test",json.opt("imageUrl").toString());

                    Map<String,Object> map = new HashMap<>();
                    map.put("image",pmap.get(json.opt("imageUrl").toString()));
                    map.put("foodName",json.opt("foodName"));
                    map.put("ballotNum",json.opt("ballotNum"));
                    mapList.add(map);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Log.e("text",e.toString());
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("text",e.toString());
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("text",e.toString());
            }
            return mapList;
        }

        @Override
        protected void onPostExecute(List list) {
            Message message = Message.obtain();
            message.what=1;
            message.obj=mapList;
            handler.sendMessage(message);
        }
    }
}
