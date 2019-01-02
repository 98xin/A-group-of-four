package cn.edu.hebtu.software.canteen;

import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class VoteActivity extends AppCompatActivity {
    private TextView textView;
    private ListView listView;
    private MyAdapter myAdapter;
    private int checkNum;
    private List<Map<String,Object>> mapList;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        Button btn = findViewById(R.id.btn_detailcontent_return);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VoteActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        mapList = MyAdapter.getList();
        listView = findViewById(R.id.lv);
        myAdapter = new MyAdapter(this, R.layout.activity_votelist, mapList);
        listView.setAdapter(myAdapter);
        checkNum = MyAdapter.getCheckNum();

        Log.e("test",String.valueOf(MyAdapter.getCheckNum()));
        Button yes = findViewById(R.id.btn_yes);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( MyAdapter.getCheckNum() == 0) {
                    Toast.makeText(VoteActivity.this, "请投票后查看", Toast.LENGTH_SHORT).show();
                } else if (MyAdapter.getCheckNum() > 2) {
                    Toast.makeText(VoteActivity.this, "最多只能投两票", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(VoteActivity.this, ResultActivity.class);
                    startActivity(intent);
                }
            }
        });
        updateBallot task = new updateBallot();
        task.execute();
    }

    public class updateBallot extends AsyncTask {

        private List<Map<String,Object>> mapList;
        @Override
        protected Object doInBackground(Object[] objects) {
            View view = View.inflate(VoteActivity.this,R.layout.activity_votelist,null);
            textView = view.findViewById(R.id.tv_name);
            String foodName = textView.getText().toString();
            String path = "http://10.7.88.242:8080/canteen1/UpdateBallotServlet?foodName="+foodName;
            try {
                URL url = new URL(path);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("contentType","utf-8");
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Log.e("text",e.toString());
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("text",e.toString());
            }
            return mapList;
        }
    }
}

