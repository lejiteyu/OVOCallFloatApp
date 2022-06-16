package ovo.Intent.fridayapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;


/**
 * https://www.jianshu.com/p/529755edc3c5
 * https://tomkuo139.blogspot.com/2017/03/android-package-name-app-google-play.html
 * https://fridaystaging.page.link/?link=https://staging3.video.friday.tw/?linkType%3D12%26linkValue%3D1%26src%3DmWeb&apn=net.fetnet.fetvod.staging&isi=1027671676&ibi=tw.friday.video.iphone.uat&efr=1
 * http://video.friday.tw/home/?linkTyep=1669&linkeValue=1
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
    public static final String SpeechRecognition = "SpeechRecognition";
    Button callfriDayAppBtn,callfriDayAppBtnPackageName;
    int OVERLAY_PERMISSION_REQ_CODE = 999;
    final int FLAG_RECEIVER_INCLUDE_BACKGROUND = 0x01000000;
    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10;
    EditText editText;
    ImageButton previousBtn,playPauseBtn,nextBtn,playNextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context context=this;
        setContentView(R.layout.activity_main);
        callfriDayAppBtn = findViewById(R.id.CallfriDayAppBtn);
        callfriDayAppBtnPackageName  = findViewById(R.id.CallfriDayAppBtnPackageName);
        callfriDayAppBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent activityIntent = new Intent();
//                activityIntent.setClassName(MainActivity.callFriDayPackageName, MainActivity.callFriDayMainClass );
//                activityIntent.putExtra(SpeechRecognition, true);
//                activityIntent.putExtra(MainActivity.KeyWord, "激戰");
//                activityIntent.putExtra(MainActivity.Episode, "");
//                activityIntent.putExtra(MainActivity.LinkType, "");
//                activityIntent.putExtra(MainActivity.LinkValue, "");
//                startActivity(activityIntent);

                //應用程式資訊
//                Uri uri = Uri.parse( "package:" + callFriDayPackageName );
//                Intent intent = new Intent( android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS, uri );
//                startActivity( intent );

                //App 執行
//                Intent intent = getPackageManager().getLaunchIntentForPackage(callFriDayPackageName);
//                if( intent != null )
//                {
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    intent.putExtra(SpeechRecognition, true);
//                    intent.putExtra(MainActivity.KeyWord, "人妻");
//                    intent.putExtra(MainActivity.Episode, "");
//                    intent.putExtra(MainActivity.LinkType, "");
//                    intent.putExtra(MainActivity.LinkValue, "");
//                    startActivity( intent );
//                }
                JSONObject json = new JSONObject();
                try {
                    json.put("contentId","1669");
                    json.put("contentType","3");
                    json.put("isLive",false);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Intent intent = getPackageManager().getLaunchIntentForPackage(callFriDayPackageName);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("poster", json.toString());
                startActivity( intent );
                Log.d(TAG,"poster:"+json);
                //開啟 App 的 Google Play
//                Uri uri = Uri.parse( "market://details?id=" + callFriDayPackageName );
//                Intent intent = new Intent( Intent.ACTION_VIEW, uri );
//                startActivity( intent );

            }
        });

        callfriDayAppBtnPackageName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setAction(callFriDayAction);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra(KeyWord,"激戰");
//                intent.putExtra(LinkType,"");
//                intent.putExtra(LinkValue,"");
////                startActivity(intent);
//                sendBroadcast(intent);
                JSONObject json = new JSONObject();
                try {
                    json.put("contentId","2810");
                    json.put("contentType","0");
                    json.put("streamingId",17);
                    json.put("isLive",true);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Intent intent = getPackageManager().getLaunchIntentForPackage(callFriDayPackageName);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("poster", json.toString());
                //android Oreo Recommendation go to Detail Page
                intent.setAction(String.valueOf(55569));
                startActivity( intent );
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

        btn1 = (Button)findViewById(R.id.btn1);
        final String keyword1 = "激戰";
        String k = btn1.getText()+"("+keyword1+")";
        btn1.setText(k);
        btn1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"the float btn1 is clicked.",Toast.LENGTH_LONG).show();
                Intent intent = getPackageManager().getLaunchIntentForPackage(callFriDayPackageName);
                intent.setAction(MainActivity.callFriDayAction);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(FLAG_RECEIVER_INCLUDE_BACKGROUND);//20210913 sdk > 8.0 要喚起背景app 需要使用
                intent.putExtra(MainActivity.KeyWord,keyword1);
                intent.putExtra(MainActivity.LinkType,"");
                intent.putExtra(MainActivity.LinkValue,"");
//                startActivity(intent);
                context.sendBroadcast(intent);
            }
        });
        btn2 = (Button)findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                String keyword = editText.getText().toString();
                Toast.makeText(context,"the float btn1 is clicked.("+keyword+")",Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setAction(MainActivity.callFriDayAction);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(FLAG_RECEIVER_INCLUDE_BACKGROUND);//20210913 sdk > 8.0 要喚起背景app 需要使用
                intent.putExtra(MainActivity.KeyWord,keyword);
                intent.putExtra(MainActivity.LinkType,"12");
                intent.putExtra(MainActivity.LinkValue,"5");

//                intent.putExtra(MainActivity.LinkType,"10");//進入詳細頁
//                intent.putExtra(MainActivity.LinkValue,"52045_1”);//contnetId_contentType
//                startActivity(intent);
                context.sendBroadcast(intent);
            }
        });
        btn3 = (Button)findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"the float btn1 is clicked.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setAction(MainActivity.callFriDayAction);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(FLAG_RECEIVER_INCLUDE_BACKGROUND);//20210913 sdk > 8.0 要喚起背景app 需要使用
                intent.putExtra(MainActivity.KeyWord,"");
                intent.putExtra(MainActivity.LinkType,"12");
                intent.putExtra(MainActivity.LinkValue,"2");
//                startActivity(intent);
                context.sendBroadcast(intent);
            }
        });
        btn4 = (Button)findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"the float btn1 is clicked.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setAction(MainActivity.callFriDayAction);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(FLAG_RECEIVER_INCLUDE_BACKGROUND);//20210913 sdk > 8.0 要喚起背景app 需要使用
                intent.putExtra(MainActivity.KeyWord,"");
                intent.putExtra(MainActivity.LinkType,"12");
                intent.putExtra(MainActivity.LinkValue,"4_3");
//                startActivity(intent);
                context.sendBroadcast(intent);
            }
        });
        btn5 = (Button)findViewById(R.id.btn5);
        final String keyword5 = "18歲的瞬間";
        final String Episode5 = "SP";
        String k5 = btn5.getText()+"("+keyword5+":"+Episode5+")";
        btn5.setText(k5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"the float btn1 is clicked.",Toast.LENGTH_LONG).show();
//                Intent intent = new Intent();
//                intent.setAction(MainActivity.callFriDayAction);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra(MainActivity.Episode,Episode5);
//                intent.putExtra(MainActivity.KeyWord,keyword5);
//                intent.putExtra(MainActivity.LinkType,"");
//                intent.putExtra(MainActivity.LinkValue,"");
////                startActivity(intent);
//                context.sendBroadcast(intent);

                String  url = "http://video.friday.tw/home/?linkType=10&linkValue=80224_1";
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);

            }
        });

        btn6 = (Button)findViewById(R.id.btn6);
        final String keyword6 = "鬼滅之刃";
        final String Episode6 = "SP";
        String k6 = btn6.getText()+"("+keyword6+":"+Episode6+")";
        btn6.setText(k6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"the float btn1 is clicked.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setAction(MainActivity.callFriDayAction);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(MainActivity.KeyWord,keyword6);
                intent.putExtra(MainActivity.Episode,Episode6);
                intent.putExtra(MainActivity.LinkType,"");
                intent.putExtra(MainActivity.LinkValue,"");
//                startActivity(intent);
                context.sendBroadcast(intent);
            }
        });

        btn7 = (Button)findViewById(R.id.btn7);
        final String keyword7 = "鬼滅之刃";
        final String Episode7 = "7";
        String k7 = btn7.getText()+"("+keyword6+":"+Episode7+")";
        btn7.setText(k7);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"the float btn1 is clicked.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setAction(MainActivity.callFriDayAction);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(MainActivity.KeyWord,keyword7);
                intent.putExtra(MainActivity.Episode,Episode7);
                intent.putExtra(MainActivity.LinkType,"");
                intent.putExtra(MainActivity.LinkValue,"");
//                startActivity(intent);
                context.sendBroadcast(intent);
            }
        });

        btn8 = (Button)findViewById(R.id.btn8);
        final String keyword8 = "鬼滅之刃";
        final String Episode8 = "";
        String k8 = btn8.getText()+"("+keyword6+":"+Episode8+")";
        btn8.setText(k8);
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"the float btn1 is clicked.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setAction(MainActivity.callFriDayAction);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(MainActivity.KeyWord,keyword8);
                intent.putExtra(MainActivity.Episode,Episode8);
                intent.putExtra(MainActivity.LinkType,"");
                intent.putExtra(MainActivity.LinkValue,"");
//                startActivity(intent);
                context.sendBroadcast(intent);
            }
        });

        btn9 = (Button)findViewById(R.id.btn9);
        final String keyword9 = "玩命關頭";
        final String Episode9 = "";
        String k9 = btn9.getText()+"("+keyword9+":"+Episode9+")";
        btn9.setText(k9);
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"the float btn1 is clicked.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setAction(MainActivity.callFriDayAction);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(MainActivity.KeyWord,keyword9);
                intent.putExtra(MainActivity.Episode,Episode9);
                intent.putExtra(MainActivity.LinkType,"");
                intent.putExtra(MainActivity.LinkValue,"");
//                startActivity(intent);
                context.sendBroadcast(intent);
            }
        });

        btn10 = (Button)findViewById(R.id.btn10);
        final String keyword10 = "收藏頁面";
        final String Episode10 = "";
        String k10 = btn10.getText()+"("+keyword10+":"+Episode10+")";
        btn10.setText(k10);
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"the float btn1 is clicked.",Toast.LENGTH_LONG).show();
                String  url = "http://video.friday.tw/home/?linkType=15&linkValue=5";
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });


        previousBtn = (ImageButton)findViewById(R.id.previousBtn);
        playPauseBtn = (ImageButton)findViewById(R.id.playPauseBtn);
        nextBtn = (ImageButton)findViewById(R.id.nextBtn);
        playNextBtn = (ImageButton)findViewById(R.id.playNextBtn);
        previousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Instrumentation inst = new Instrumentation();
                                inst.sendKeyDownUpSync(KeyEvent.KEYCODE_MEDIA_PREVIOUS);
                            }catch (Exception e){

                            }
                        }
                    }).start();}catch (Exception e){

                }
            }
        });
        playPauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Instrumentation inst = new Instrumentation();
                                inst.sendKeyDownUpSync(KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE);
                            }catch (Exception e){

                            }
                        }
                    }).start();}catch (Exception e){

                }

            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Instrumentation inst = new Instrumentation();
                                inst.sendKeyDownUpSync(KeyEvent.KEYCODE_MEDIA_NEXT);
                            }catch (Exception e){

                            }
                        }
                    }).start();}catch (Exception e){

                }
            }
        });
        playNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Instrumentation inst = new Instrumentation();
                                inst.sendKeyDownUpSync(KeyEvent.KEYCODE_NAVIGATE_NEXT);
                            }catch (Exception e){

                            }
                        }
                    }).start();
                }catch (Exception e){

                }
            }
        });


        editText = (EditText) findViewById(R.id.edit_query);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //stopService(new Intent(MainActivity.this, FloatService.class));
    }
}
