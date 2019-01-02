package cn.edu.hebtu.software.canteen;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Account extends AppCompatActivity implements View.OnClickListener{

    private Handler handler;
    private int ResultCode = 2;
    private final static int REGISTER_JUDGE = 2;
    private Button register,back;
    private EditText id,psw_1,psw_2,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        register = (Button) findViewById(R.id.btn_account);
        register.setOnClickListener(this);
        back = (Button) findViewById(R.id.btn_account_return);
        back.setOnClickListener(this);
        id = (EditText) findViewById(R.id.et_account_num);
        psw_1 = (EditText) findViewById(R.id.et_account_password);
        psw_2 = (EditText) findViewById(R.id.et_account_check_password);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_account:{
                if( ! psw_1.getText().toString().equals(psw_2.getText().toString())){
                    Toast.makeText(Account.this,"两次密码不一致！",Toast.LENGTH_LONG).show();
                }else {
                    RadioGroup radioGroup = findViewById(R.id.rg_role);
                    RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                    if(radioButton.getText().toString().equals("学生")){
                        StudentAcountAsyncTask account = new StudentAcountAsyncTask();
                        account.execute();
                        handler = new Handler(){
                            @Override
                            public void handleMessage(Message msg) {
                                switch (msg.what){
                                    case 1:{
                                        if(msg.obj.toString().equals("true")){
                                            Toast.makeText(Account.this,"注册成功",Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent();
                                            intent.setClass(Account.this,MainActivity.class);
                                            startActivity(intent);
                                        }else{
                                            Toast.makeText(Account.this,msg.obj.toString(),Toast.LENGTH_LONG).show();
                                        }
                                        break;
                                    }
                                    default:
                                        break;
                                }
                            }
                        };
                    }else if(radioButton.getText().toString().equals("食堂管理人员")){
                        AttendantAccountAsyncTask accountAsyncTask = new AttendantAccountAsyncTask();
                        accountAsyncTask.execute();
                        handler = new Handler(){
                            @Override
                            public void handleMessage(Message msg) {
                                switch (msg.what){
                                    case 3:{
                                        if(msg.obj.toString().equals("true")){
                                            Toast.makeText(Account.this,"注册成功",Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent();
                                            intent.setClass(Account.this,LoginActivity.class);
                                            startActivity(intent);
                                        }else{
                                            Toast.makeText(Account.this,msg.obj.toString(),Toast.LENGTH_LONG).show();
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
            }
            case R.id.btn_account_return: {
                finish();
                break;
            }
        }
    }

    public class StudentAcountAsyncTask extends AsyncTask<Void,Void,String>{
        private String message;
        @Override
        protected String doInBackground(Void... voids) {
            String path = "http://10.7.88.242:8080/canteen1/StudentAccountServlet?userName="
                    +id.getText().toString()
                    +"&passWord="
                    +psw_1.getText().toString();
            try {
                URL url = new URL(path);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                InputStream in = connection.getInputStream();
                InputStreamReader input = new InputStreamReader(in);
                BufferedReader buffer = new BufferedReader(input);

                String str = buffer.readLine();
                JSONObject json = new JSONObject(str);
                message = json.get("message").toString();
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
            handler.sendMessage(msg);
        }
    }

    public class AttendantAccountAsyncTask extends AsyncTask<Void,Void,String>{

        private String message;
        @Override
        protected String doInBackground(Void... voids) {
            String path = "http://10.7.88.242:8080/canteen1/AttendantAccountServlet?userName="
                    +id.getText().toString()
                    +"&passWord="
                    +psw_1.getText().toString();
            try {
                URL url = new URL(path);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                InputStream in = connection.getInputStream();
                InputStreamReader input = new InputStreamReader(in);
                BufferedReader buffer = new BufferedReader(input);

                String str = buffer.readLine();
                JSONObject json = new JSONObject(str);
                message = json.get("message").toString();
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
            handler.sendMessage(msg);
        }
    }
}
