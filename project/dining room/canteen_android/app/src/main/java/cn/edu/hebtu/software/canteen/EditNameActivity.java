package cn.edu.hebtu.software.canteen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditNameActivity extends AppCompatActivity {
    private EditText etname;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_name);

        etname = findViewById(R.id.et_name);

        Intent intent = getIntent();
        etname.setText(intent.getStringExtra("name"));

        //绑定按钮监听器
        Button btnOk = findViewById(R.id.btn_ok);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rename = etname.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("rename", rename);
                setResult(2,intent);
                finish();
            }
        });
    }
}
