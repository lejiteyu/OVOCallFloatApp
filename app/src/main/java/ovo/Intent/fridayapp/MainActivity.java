package ovo.Intent.fridayapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


/**
 * https://www.jianshu.com/p/529755edc3c5
 */
public class MainActivity extends AppCompatActivity {
     final String TAG = MainActivity.class.getSimpleName();
    public static final String callFriDayAction = "com.friday.tvapp";
    public static final String callFriDayPackageName = "net.fetnet.fetvod.tv";
    public static final String callFriDayMainClass = ".MainActivity";
    public static final String KeyWord = "KeyWord";
    public static final String Episode = "Episode";
    public static final String LinkType = "linkType";
    public static final String LinkValue = "linkValue";
    Button callfriDayAppBtn,callfriDayAppBtnPackageName;
    int OVERLAY_PERMISSION_REQ_CODE = 999;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callfriDayAppBtn = findViewById(R.id.CallfriDayAppBtn);
        callfriDayAppBtnPackageName  = findViewById(R.id.CallfriDayAppBtnPackageName);
        callfriDayAppBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(callFriDayAction);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(KeyWord,"玩命關頭2");
                intent.putExtra(LinkType,12);
                intent.putExtra(LinkValue,2);
//                startActivity(intent);
                sendBroadcast(intent);
            }
        });

        callfriDayAppBtnPackageName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(callFriDayAction);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(KeyWord,"玩命關頭1");
                intent.putExtra(LinkType,12);
                intent.putExtra(LinkValue,2);
//                startActivity(intent);
                sendBroadcast(intent);
            }
        });


        if(Build.VERSION.SDK_INT>=23)
        {
            if(Settings.canDrawOverlays(this))
            {
                //有悬浮窗权限开启服务绑定 绑定权限
                //目的就是啟動Service來開啟懸浮窗體
                startService(new Intent(MainActivity.this, FloatService.class));

            }else{
                //没有悬浮窗权限m,去开启悬浮窗权限
                try{
                    Intent  intent=new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                    startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        } else{
            //默认有悬浮窗权限  但是 华为, 小米,oppo等手机会有自己的一套Android6.0以下  会有自己的一套悬浮窗权限管理 也需要做适配
            //目的就是啟動Service來開啟懸浮窗體
            startService(new Intent(MainActivity.this, FloatService.class));
        }


    }

    @SuppressLint("MissingSuperCall")
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == OVERLAY_PERMISSION_REQ_CODE) {
            if(Build.VERSION.SDK_INT>=23) {
                if (!Settings.canDrawOverlays(this)) {
                    Toast.makeText(this, "权限授予失败，无法开启悬浮窗", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "权限授予成功！", Toast.LENGTH_SHORT).show();
                    //有悬浮窗权限开启服务绑定 绑定权限
                    //目的就是啟動Service來開啟懸浮窗體
                    startService(new Intent(MainActivity.this, FloatService.class));
                }
            }
        }
    }


}
