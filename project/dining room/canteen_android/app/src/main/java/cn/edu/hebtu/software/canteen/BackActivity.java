package cn.edu.hebtu.software.canteen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class BackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back);

        Button btnEvaluate = findViewById(R.id.btn_main2_evaluate);
        Button btnVote = findViewById(R.id.btn_main2_vote);

        ImageView ivEvaluate = findViewById(R.id.iv_main2_evaluate);
        ImageView ivVote = findViewById(R.id.iv_main2_vote);
        //评论跳转
        btnEvaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BackActivity.this,EvaluateActivity.class);
                startActivity(intent);
            }
        });
        ivEvaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BackActivity.this,EvaluateActivity.class);
                startActivity(intent);
            }
        });

        //投票跳转
        btnVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BackActivity.this,ManagerVoteActivity.class);
                startActivity(intent);
            }
        });
        ivVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BackActivity.this,ManagerVoteActivity.class);
                startActivity(intent);
            }
        });
    }
}
