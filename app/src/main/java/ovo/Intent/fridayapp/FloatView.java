package ovo.Intent.fridayapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class FloatView extends View {
    //懸浮球寬度
    public static int floatWidth = 300;
    //懸浮球高度
    public static  int floatHeight = 500;
    //懸浮球畫筆
    private Paint mPaint;
    //繪製文字畫筆
    private Paint mTextPaint;
    private String text = "50%";
    public FloatView(Context context) {
        super(context);
        initPaint();
    }
    public int getFloatWidth() {
        return floatWidth;
    }
    public int getFloatHeight() {
        return floatHeight;
    }
    public FloatView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }
    public FloatView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }
    /**
     * @param
     * @description 初始化畫筆
     * @author ldm
     * @time 2016/8/17 11:37
     */
    private void initPaint() {
//設定懸浮球畫筆
        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
//設定文字畫筆
        mTextPaint = new Paint();
        mTextPaint.setTextSize(25);
        mPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setFakeBoldText(true);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//設定大小
        setMeasuredDimension(floatWidth, floatHeight);
    }
    /**
     * @param
     * @description 繪製圖案
     * @author ldm
     * @time 2016/8/17 11:44
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//繪製懸浮球
        canvas.drawCircle(floatWidth / 2, floatHeight / 2, floatWidth / 2, mPaint);
//繪製文字
        Paint.FontMetrics metrics = mTextPaint.getFontMetrics();
//文字大小計算可以參考：http://mikewang.blog.51cto.com/3826268/871765/
        float textWidth = mTextPaint.measureText(text);
        float x = floatWidth / 2 - textWidth / 2;
        float dy = (metrics.descent - metrics.ascent) / 2;
        float y = floatHeight / 2  - dy;
        canvas.drawText(text, x, y, mTextPaint);
    }
}
