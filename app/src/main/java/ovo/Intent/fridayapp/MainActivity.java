package ovo.Intent.fridayapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
     final String TAG = MainActivity.class.getSimpleName();
    public static final String callFriDayAction = "com.friday.tvapp";
    public static final String callFriDayPackageName = "net.fetnet.fetvod.tv";
    public static final String callFriDayMainClass = ".MainActivity";
    public static final String POSTER = "poster";
    public static final String ACTION = "action";
    public static final int ActionPlay=1;
    public static final String Json = "{" +
            "                \"contentId\": 970,\n" +
            "                \"contentType\": 2,\n" +
            "                \"chineseName\": \"優雅的家\",\n" +
            "                \"englishName\": \"우아한 가\",\n" +
            "                \"effectiveDate\": \"2019/08/22\",\n" +
            "                \"introduction\": \"爆紅狗血韓劇!!!講述於15年前因殺人事件失去媽媽的財閥繼承女子，與只為金錢服務的邊緣三流律師相遇後，發現財閥家隱藏之祕密的故事。\",\n" +
            "                \"duration\": \"60\",\n" +
            "                \"newestEpisode\": \"15\",\n" +
            "                \"totalEpisode\": 16,\n" +
            "                \"fridayScore\": 4.8,\n" +
            "                \"year\": 2019,\n" +
            "                \"area\": \"韓國\",\n" +
            "                \"paymentTagList\": [\n" +
            "                    0,\n" +
            "                    1\n" +
            "                ],\n" +
            "                \"propertyTagList\": [\n" +
            "                    3\n" +
            "                ],\n" +
            "                \"isEmpty\": false,\n" +
            "                \"largeImageUrl\": \"/HOTGROUP/WIDE/970/970_8771408.jpg\",\n" +
            "                \"themeImageUrl\": \"/HOTGROUP/3TO2/970/970_8902698.jpg\",\n" +
            "                \"imageUrl\": \"/DRAMA/970/970_8096184.jpg\",\n" +
            "                \"url\": \"\",\n" +
            "                \"rating\": 2\n" +
            "            }";

    Button callfriDayAppBtn,callfriDayAppBtnPackageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callfriDayAppBtn = findViewById(R.id.CallfriDayAppBtn);
        callfriDayAppBtnPackageName  = findViewById(R.id.CallfriDayAppBtnPackageName);
        Log.d(TAG,"JAON:"+Json);
        callfriDayAppBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(callFriDayAction);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(POSTER,Json);
                intent.putExtra(ACTION,ActionPlay);
//                startActivity(intent);
                sendBroadcast(intent);
            }
        });

        callfriDayAppBtnPackageName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activityIntent = new Intent();
                activityIntent.setComponent(new ComponentName(callFriDayPackageName,callFriDayPackageName+callFriDayMainClass));
                activityIntent.putExtra(POSTER,Json);
                startActivity(activityIntent);
            }
        });


        //目的就是啟動Service來開啟懸浮窗體
        startService(new Intent(MainActivity.this, FloatService.class));
    }
}
