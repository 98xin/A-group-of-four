package cn.edu.hebtu.software.canteen;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Handler handler1;
    private static String role;

    private Button login,btnPop;
    private EditText id,password;
    private final static int LOGIN_JUDGE = 1;
    private int RequestCode = 1;
    //自定义的弹出框类
    SelectPicPopupWindow menuWindow;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button) findViewById(R.id.btn_login);
        login.setOnClickListener(this);
        btnPop = findViewById(R.id.btn_more);
        btnPop.setOnClickListener(this);
        id = (EditText) findViewById(R.id.et_login_num);
        password = (EditText) findViewById(R.id.et_login_password);

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==2){
            id.setText(data.getStringExtra("id"));
            password.setText(data.getStringExtra("password"));
        }
    }

    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {

        public void onClick(View v) {
            menuWindow.dismiss();
            switch (v.getId()) {
                case R.id.btn_popup_account:
                    Intent intent = new Intent(LoginActivity.this,Account.class);
                    startActivity(intent);
                    break;
                case R.id.btn_popup_change:
                    break;
                default:
                    break;
            }
        }
    };

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:{
                RadioGroup radioGroup = findViewById(R.id.rg_role);
                RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());

                if(radioButton.getText().toString().equals("学生")){
                    StudentLogin login = new StudentLogin();
                    login.execute();
                    handler1 = new Handler(){
                        @Override
                        public void handleMessage(Message msg) {
                            switch (msg.what){
                                case 1:{
                                    if(msg.obj.toString().equals("true")){
                                        Intent intent = new Intent();
                                        intent.setClass(LoginActivity.this,MainActivity.class);
                                        startActivity(intent);
                                    } else if(msg.obj.toString().equals("false")){
                                        Toast.makeText(LoginActivity.this,"您还没有注册",Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent();
                                        intent.setClass(LoginActivity.this,Account.class);
                                        startActivity(intent);
                                    }else{
                                        Toast.makeText(LoginActivity.this,msg.obj.toString(),Toast.LENGTH_SHORT).show();
                                    }
                                    break;
                                }
                                default:
                                    break;
                            }
                        }
                    };
                }else{
                    AttendantLogin login = new AttendantLogin();
                    login.execute();
                    handler1 = new Handler(){
                        @Override
                        public void handleMessage(Message msg) {
                            switch (msg.what){
                                case 3:{
                                    if(msg.obj.toString().equals("true")){
                                        Intent intent = new Intent();
                                        intent.setClass(LoginActivity.this,BackActivity.class);
                                        startActivity(intent);
                                    } else if(msg.obj.toString().equals("false")){
                                        Toast.makeText(LoginActivity.this,"您还没有注册",Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent();
                                        intent.setClass(LoginActivity.this,Account.class);
                                        startActivity(intent);
                                    }else{
                                        Toast.makeText(LoginActivity.this,msg.obj.toString(),Toast.LENGTH_SHORT).show();
                                    }
                                    break;
                                }
                                default:
                                    break;
                            }
                        }
                    };
                }
            }
            break;
            case R.id.btn_more:{
                //实例化SelectPicPopupWindow
                menuWindow = new SelectPicPopupWindow(LoginActivity.this, itemsOnClick);
                //显示窗口
                menuWindow.showAtLocation(LoginActivity.this.findViewById(R.id.popup_main),
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
            }
            break;
        }
    }

    public class StudentLogin extends AsyncTask<Void,Void,String>{
        private String message;
        @Override
        protected String doInBackground(Void... voids) {
            String path = "http://10.7.88.242:8080/canteen1/StudentLoginServlet?userName="
                    +id.getText().toString()
                    +"&passWord="
                    +password.getText().toString();
            try {
                URL url = new URL(path);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("contentType","utf-8");

                InputStream in = connection.getInputStream();
                InputStreamReader input = new InputStreamReader(in);
                BufferedReader buffer = new BufferedReader(input);

                String str = buffer.readLine();
                JSONObject json = new JSONObject(str);
                message = json.opt("message").toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Log.e("test",e.toString());
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("test", e.toString());
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("test",e.toString());
            }
            return message;
        }

        @Override
        protected void onPostExecute(String s) {
            Message msg = Message.obtain();
            if(s != null){
                msg.what = 1;
                msg.obj = message;
            }else{
                msg.what = 2;
            }
            handler1.sendMessage(msg);
        }
    }

    public class AttendantLogin extends AsyncTask<Void,Void,String>{
        private String message;
        @Override
        protected String doInBackground(Void... voids) {
            String path = "http://10.7.88.242:8080/canteen1/AttendantLoginServlet?userName="
                    +id.getText().toString()
                    +"&passWord="
                    +password.getText().toString();
            try {
                URL url = new URL(path);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("contentType","utf-8");

                InputStream in = connection.getInputStream();
                InputStreamReader input = new InputStreamReader(in);
                BufferedReader buffer = new BufferedReader(input);

                String str = buffer.readLine();
                JSONObject json = new JSONObject(str);
                message = json.opt("message").toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Log.e("test",e.toString());
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("test", e.toString());
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("test",e.toString());
            }
            return message;
        }

        @Override
        protected void onPostExecute(String s) {
            Message msg = Message.obtain();
            if(s != null){
                msg.what = 3;
                msg.obj = message;
            }else{
                msg.what = 4;
            }
            handler1.sendMessage(msg);
        }
    }
}

