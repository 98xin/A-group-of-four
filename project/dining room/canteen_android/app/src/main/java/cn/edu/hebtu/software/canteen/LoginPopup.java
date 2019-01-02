package cn.edu.hebtu.software.canteen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class LoginPopup extends AppCompatActivity {

    //自定义的弹出框类
    SelectPicPopupWindow menuWindow;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnPop = findViewById(R.id.btn_more);
        //把文字控件添加监听，点击弹出自定义窗口
        btnPop.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                //实例化SelectPicPopupWindow
                menuWindow = new SelectPicPopupWindow(LoginPopup.this, itemsOnClick);
                //显示窗口
                menuWindow.showAtLocation(LoginPopup.this.findViewById(R.id.popup_main),
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
            }
        });
    }

    //为弹出窗口实现监听类
    private OnClickListener itemsOnClick = new OnClickListener() {

        public void onClick(View v) {
            menuWindow.dismiss();
            switch (v.getId()) {
                case R.id.btn_popup_account:
                    Intent intent = new Intent(LoginPopup.this,Account.class);
                    startActivity(intent);
                    break;
                case R.id.btn_popup_change:
                    break;
                default:
                    break;
            }
        }

    };
}