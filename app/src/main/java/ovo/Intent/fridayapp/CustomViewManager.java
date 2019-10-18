package ovo.Intent.fridayapp;


import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * 懸浮窗體管理工具類
 */
public class CustomViewManager {
    //上下文
    private Context mContext;
    //本類例項
    private static CustomViewManager instance;
    //自定義的FloatView
    private FloatView mFloatView;
    //視窗管理類
    private WindowManager mWindowManager;

    public static final String Json = "{" +
            "                \"contentId\": 42554,\n" +
            "                \"contentType\": 1,\n" +
            "                \"chineseName\": \"安妮華達 最後一堂課\",\n" +
            "                \"englishName\": \"Varda By Agnès\",\n" +
            "                \"effectiveDate\": \"2019/10/18\",\n" +
            "                \"introduction\": \"「法國新浪潮教母」安妮華達最後作品。回顧安妮華達從攝影師、電影導演到裝置藝術家的一生，大家一定要來上《安妮華達 最後一堂課》。燈光緩緩亮起，頂著一頭棕紅漸層髮色的安妮華達，坐在導演椅上娓娓道來，細數她六十餘年的電影生涯。早期攝影工作的摸索試探，到部部經典代表作的誕生，躍身成為眾人熟悉的華達奶奶。安妮以幽默語氣述說感性回憶，穿插伴侶德米和合作演員的過往趣事，將影像化作時光機，為影迷們獻上最後一堂關於華達的電影大師課。\",\n" +
            "                \"duration\": \"114\",\n" +
            "                \"newestEpisode\": \"\",\n" +
            "                \"totalEpisode\": -1,\n" +
            "                \"fridayScore\": 5,\n" +
            "                \"year\": 2019,\n" +
            "                \"area\": \"法國\",\n" +
            "                \"paymentTagList\": [\n" +
            "                    1,\n" +
            "                    3\n" +
            "                ],\n" +
            "                \"propertyTagList\": [\n" +
            "                    2\n" +
            "                ],\n" +
            "                \"isEmpty\": false,\n" +
            "                \"largeImageUrl\": \"/HOTMOVIE/WIDE/44615/44615_4370721.jpg\",\n" +
            "                \"themeImageUrl\": \"/HOTMOVIE/3TO2/44615/44615_4389676.jpg\",\n" +
            "                \"imageUrl\": \"/MOVIE/44615/44615_307517.jpg\",\n" +
            "                \"url\": \"\",\n" +
            "                \"rating\": 2\n" +
            "            }";

    private CustomViewManager(Context context) {
        this.mContext = context;
        mFloatView = new FloatView(mContext);
        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        mFloatView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,"You click the Float! contentId:42554",Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setAction(MainActivity.callFriDayAction);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(MainActivity.POSTER,Json);
                intent.putExtra(MainActivity.ACTION,MainActivity.ActionPlay);
//                mContext.startActivity(intent);
                mContext.sendBroadcast(intent);
            }
        });
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
        WindowManager.LayoutParams parmas = new WindowManager.LayoutParams();
        parmas.width = mFloatView.getFloatWidth();
        parmas.height = mFloatView.getFloatHeight();
//視窗圖案放置位置
        parmas.gravity = Gravity.LEFT | Gravity.CENTER;
// 如果忽略gravity屬性，那麼它表示視窗的絕對X位置。
        parmas.x = 0;
//如果忽略gravity屬性，那麼它表示視窗的絕對Y位置。
        parmas.y = 0;
////電話視窗。它用於電話互動（特別是呼入）。它置於所有應用程式之上，狀態列之下。
        parmas.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
//FLAG_NOT_FOCUSABLE讓window不能獲得焦點，這樣使用者快就不能向該window傳送按鍵事件及按鈕事件
//FLAG_NOT_TOUCH_MODAL即使在該window在可獲得焦點情況下，仍然把該window之外的任何event傳送到該window之後的其他window.
        parmas.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
// 期望的點陣圖格式。預設為不透明。參考android.graphics.PixelFormat。
        parmas.format = PixelFormat.RGBA_8888;
        mWindowManager.addView(mFloatView, parmas);
    }
}
