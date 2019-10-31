package ovo.Intent.fridayapp;


import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * 懸浮窗體管理工具類
 */
public class CustomViewManager {
    String TAG = CustomViewManager.class.getSimpleName();
    //上下文
    private Context mContext;
    //本類例項
    private static CustomViewManager instance;
    //自定義的FloatView
    private View mFloatView;
    //懸浮球寬度
    public static int floatWidth = 400;
    //懸浮球高度
    public static  int floatHeight = 800;
    //視窗管理類
    private WindowManager mWindowManager;
    WindowManager.LayoutParams parmas;
//    public static final String Json = "{" +
//            "                \"contentId\": 42554,\n" +
//            "                \"contentType\": 1,\n" +
//            "                \"chineseName\": \"安妮華達 最後一堂課\",\n" +
//            "                \"englishName\": \"Varda By Agnès\",\n" +
//            "                \"effectiveDate\": \"2019/10/18\",\n" +
//            "                \"introduction\": \"「法國新浪潮教母」安妮華達最後作品。回顧安妮華達從攝影師、電影導演到裝置藝術家的一生，大家一定要來上《安妮華達 最後一堂課》。燈光緩緩亮起，頂著一頭棕紅漸層髮色的安妮華達，坐在導演椅上娓娓道來，細數她六十餘年的電影生涯。早期攝影工作的摸索試探，到部部經典代表作的誕生，躍身成為眾人熟悉的華達奶奶。安妮以幽默語氣述說感性回憶，穿插伴侶德米和合作演員的過往趣事，將影像化作時光機，為影迷們獻上最後一堂關於華達的電影大師課。\",\n" +
//            "                \"duration\": \"114\",\n" +
//            "                \"newestEpisode\": \"\",\n" +
//            "                \"totalEpisode\": -1,\n" +
//            "                \"fridayScore\": 5,\n" +
//            "                \"year\": 2019,\n" +
//            "                \"area\": \"法國\",\n" +
//            "                \"paymentTagList\": [\n" +
//            "                    1,\n" +
//            "                    3\n" +
//            "                ],\n" +
//            "                \"propertyTagList\": [\n" +
//            "                    2\n" +
//            "                ],\n" +
//            "                \"isEmpty\": false,\n" +
//            "                \"largeImageUrl\": \"/HOTMOVIE/WIDE/44615/44615_4370721.jpg\",\n" +
//            "                \"themeImageUrl\": \"/HOTMOVIE/3TO2/44615/44615_4389676.jpg\",\n" +
//            "                \"imageUrl\": \"/MOVIE/44615/44615_307517.jpg\",\n" +
//            "                \"url\": \"\",\n" +
//            "                \"rating\": 2\n" +
//            "            }";


    private float mTouchStartX;
    private float mTouchStartY;
    private float x;
    private float y;

    private CustomViewManager(Context context) {
        this.mContext = context;
        mFloatView = getFloatView2(mContext);
        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);

        mFloatView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,"You click the Float! contentId:42554",Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setAction(MainActivity.callFriDayAction);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(MainActivity.KeyWord,"");
                intent.putExtra(MainActivity.LinkType,"");
                intent.putExtra(MainActivity.LinkValue,"");
//                startActivity(intent);
                mContext.sendBroadcast(intent);
            }
        });

        mFloatView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                return false;
            }
        });

        mFloatView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                Log.d(TAG,"motionEvent:"+event);
                x = event.getRawX();
                y = event.getRawY()-50;   //25是系统状态栏的高度
                Log.i("currP", "currX:"+x+"====currY:"+y);
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //获取相对View的坐标，即以此View左上角为原点
                        mTouchStartX =  event.getX();
                        mTouchStartY =  event.getY();
                        Log.i("startP", "startX"+mTouchStartX+"====startY"+mTouchStartY);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        updateViewPosition();
                        break;

                    case MotionEvent.ACTION_UP:
                        updateViewPosition();
                        mTouchStartX=mTouchStartY=0;
                        break;
                }
                return true;
            }
        });
    }

    private void updateViewPosition(){
        //更新浮动窗口位置参数
        parmas.x=(int)( x-mTouchStartX);
        parmas.y=(int) (y-mTouchStartY);
        Log.i("updateViewPosition", "parmas.x:"+parmas.x+"====parmas.y:"+parmas.y);
        mWindowManager.updateViewLayout(mFloatView, parmas);

    }



    private View getFloatView2(Context context){
        return new FloatView2().getView(context);
    }

    private FloatView getFloatView(Context context){
        return new FloatView(context);
    }

    /**
     * @param
     * @description 通過單例模式獲取例項物件
     * @author ldm
     * @time 2016/8/17 11:59
     */
    public static CustomViewManager getInstance(Context mContext) {
        if (null == instance) {
            synchronized (CustomViewManager.class) {
                if (null == instance) {
                    instance = new CustomViewManager(mContext);
                }
            }
        }
        return instance;
    }
    /**
     * @param
     * @description 在手機螢幕上顯示自定義的FloatView
     * @author ldm
     * @time 2016/8/17 13:47
     */
    public void showFloatViewOnWindow() {
        parmas = new WindowManager.LayoutParams();
        floatWidth = mWindowManager.getDefaultDisplay().getWidth()/2;
        floatHeight = mWindowManager.getDefaultDisplay().getHeight()/2;
        parmas.width = floatWidth;
        parmas.height = floatHeight;
//視窗圖案放置位置
        parmas.gravity = Gravity.LEFT | Gravity.CENTER;
// 如果忽略gravity屬性，那麼它表示視窗的絕對X位置。
        parmas.x = mWindowManager.getDefaultDisplay().getWidth()-floatWidth;
//如果忽略gravity屬性，那麼它表示視窗的絕對Y位置。
        parmas.y = 0;
////電話視窗。它用於電話互動（特別是呼入）。它置於所有應用程式之上，狀態列之下。
        parmas.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            parmas.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            parmas.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        }
//FLAG_NOT_FOCUSABLE讓window不能獲得焦點，這樣使用者快就不能向該window傳送按鍵事件及按鈕事件
//FLAG_NOT_TOUCH_MODAL即使在該window在可獲得焦點情況下，仍然把該window之外的任何event傳送到該window之後的其他window.
        parmas.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
// 期望的點陣圖格式。預設為不透明。參考android.graphics.PixelFormat。
        parmas.format = PixelFormat.RGBA_8888;
        mWindowManager.addView(mFloatView, parmas);
    }


    public void onDestroy() {
        if (mWindowManager!=null&&mWindowManager != null) {
            mWindowManager.removeView(mFloatView);
        }
    }
}
