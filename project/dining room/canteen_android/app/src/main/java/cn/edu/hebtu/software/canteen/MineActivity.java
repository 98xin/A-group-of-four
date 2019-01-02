package cn.edu.hebtu.software.canteen;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import static android.media.MediaRecorder.VideoSource.CAMERA;


public class MineActivity extends AppCompatActivity {
    private TranslateAnimation animation;
    //private TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);

        //返回
        Button btn = findViewById(R.id.btn_return);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MineActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        //退出登录
        TextView exit = findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MineActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

       /* //跳转修改姓名
        LinearLayout linearLayout_name = findViewById(R.id.edit_name);
        linearLayout_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvName = findViewById(R.id.tv_my_name);
                Intent intent = new Intent();
                intent.putExtra("name",tvName.getText().toString());
                intent.setClass(MineActivity.this,EditNameActivity.class);
                //带返回值的启动activity的方法
                startActivityForResult(intent,1);
            }
        });*/
        //跳转修改密码
        LinearLayout linearLayout_pwd = findViewById(R.id.edit_pwd);
        linearLayout_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPwd = new Intent();
                intentPwd.setClass(MineActivity.this,EditPwdActivity.class);
                startActivity(intentPwd);

            }
        });

        // 弹出PopupWindow
        LinearLayout linearLayout = findViewById(R.id.pop_tx);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow();
                lightoff();
            }
        });
    }
    // 当有由startActivityForResult方法启动的Activity关闭时，
    // 本方法被调用
    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String str = data.getStringExtra("rename");
        tvName = findViewById(R.id.tv_my_name);
        tvName.setText(str);
    }*/

    // 弹出PopupWindow，修改头像
    private void showPopupWindow() {
        // 1. 创建PopupWindow显示的view
        View view = getLayoutInflater().inflate(R.layout.popup_tx, null);
        // 2. 创建PopupWindow
        final PopupWindow popupWindow = new PopupWindow(this);
        // 3. 设置PopupWindow的长宽
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 4. 设置PopupWindow显示的view
        popupWindow.setContentView(view);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                lighton();
            }
        });
        // 设置背景图片，必须设置，不然动画没作用
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);

        // 设置点击popupwindow外屏幕其它地方消失
        popupWindow.setOutsideTouchable(true);

        // 平移动画相对于手机屏幕的底部开始，X轴不变，Y轴从1变0
        animation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_PARENT, 0);
        animation.setInterpolator(new AccelerateInterpolator());
        animation.setDuration(200);

        // 在点击之后设置popupwindow的销毁
        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
            lighton();
        }
        // 5. view中按钮添加监听器
        TextView tvPhoto = view.findViewById(R.id.tv_photo);
        TextView tvSelect = view.findViewById(R.id.tv_select);
        TextView tvCancel = view.findViewById(R.id.tv_cancel);

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 隐藏PopupWindow
                popupWindow.dismiss();
            }
        });
        tvPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 打开系统拍照程
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera, CAMERA);
                popupWindow.dismiss();
                lighton();
            }
        });
        tvSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 打开系统图库选择图片
                Intent picture = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(picture, CAMERA);
                popupWindow.dismiss();
                lighton();
            }
        });
        // 设置popupWindow的显示位置，此处是在手机屏幕底部且水平居中的位置
        view.startAnimation(animation);
        popupWindow.showAsDropDown(tvCancel);
    }

    //设置手机屏幕亮度变暗
    private void lightoff() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.3f;
        getWindow().setAttributes(lp);
    }

    //设置手机屏幕亮度显示正常
    private void lighton() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 1f;
        getWindow().setAttributes(lp);
    }
}



