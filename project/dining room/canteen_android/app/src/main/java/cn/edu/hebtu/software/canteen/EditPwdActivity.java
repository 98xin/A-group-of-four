package cn.edu.hebtu.software.canteen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class EditPwdActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwd);

        //点击确认跳转回设置页面
        Button btnPwd = findViewById(R.id.btn_OK);
        btnPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPwd = new Intent();
                intentPwd.setClass(EditPwdActivity.this,MineActivity.class);
                startActivity(intentPwd);
            }
        });
    }

}
