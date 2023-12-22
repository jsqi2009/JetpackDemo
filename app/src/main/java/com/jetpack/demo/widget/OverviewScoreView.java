package com.jetpack.demo.widget;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;


import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.jetpack.demo.R;
import com.jetpack.demo.utils.DisplayUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jsqi
 * @time 2019/12/9 18:03
 */
public class OverviewScoreView extends View {

    // 默认宽高值
    private int defaultSize;

    // 距离圆环的值
    private int arcDistance;

    // view宽度
    private int width;

    // view高度
    private int height;

    // 默认Padding值
    private final static int defaultPadding = 60;

    //  圆环起始角度
    private final static float mStartAngle = 165f;

    // 圆环结束角度
    private final static float mEndAngle = 210f;

    //外层圆环画笔
    private Paint mMiddleArcPaint;
    private Paint mOuterLinePaint;

    //中间文本画笔
    private Paint mTextPaint;

    //刻度画笔
    private Paint mCalibrationPaint;

    //进度圆环画笔
    private Paint mInnerArcPaint;
    private Paint mArcProgressPaint;

    //分数描述画笔
    private Paint mDescriptionPaint;

    //半径
    private int radius;
    private int radius1;

    //外层矩形
    private RectF mMiddleRect;

    //进度矩形
    private RectF mMiddleProgressRect;

    // 最小数字
    private int mMinNum = 0;

    // 最大数字
    private int mMaxNum = 100;

    // 当前进度
    private float mCurrentAngle = 0f;

    //总进度
    private float mTotalAngle = 210f;

    //描述
    private String description = "";

    //矩阵
    private Matrix matrix;
    private Context mContext;

    private float outerCircleRadius;
    private float innerCircleRadius;
    private float centerX;
    private float centerY;


    public OverviewScoreView(Context context) {
        this(context, null);
    }

    public OverviewScoreView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OverviewScoreView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    /**
     * 初始化
     */
    private void init(Context context) {
        mContext = context;


        //defaultSize = ScreenUtils.getScreenWidth() - DisplayUtil.INSTANCE.dp2px(context,90F);
        defaultSize = DisplayUtil.INSTANCE.dp2px(context,270);

        //defaultSize = dp2px(200);
        arcDistance = dp2px(14);

        //外层线
        mOuterLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mOuterLinePaint.setStrokeWidth(DisplayUtil.INSTANCE.dp2px(context, 1F));
        mOuterLinePaint.setColor(getResources().getColor(R.color.line_1));
        mOuterLinePaint.setStyle(Paint.Style.STROKE);
        mOuterLinePaint.setAlpha(80);
        mOuterLinePaint.setStrokeCap(Paint.Cap.ROUND);

        //外层圆环画笔
        mMiddleArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mMiddleArcPaint.setStrokeWidth(DisplayUtil.INSTANCE.dp2px(context, 35F));
        mMiddleArcPaint.setColor(getResources().getColor(R.color.outer_circle_color));
        mMiddleArcPaint.setStyle(Paint.Style.STROKE);
        mMiddleArcPaint.setAlpha(80);
        mMiddleArcPaint.setStrokeCap(Paint.Cap.ROUND);

        //正中间字体画笔
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(getResources().getColor(R.color.black));
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        /*Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/MyriadPro_Semibold.otf");
        mTextPaint.setTypeface(typeface);*/

        //分数描述画笔
        mDescriptionPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDescriptionPaint.setColor(getResources().getColor(R.color.text_color_4));
        mDescriptionPaint.setTextAlign(Paint.Align.CENTER);
        /*Typeface desTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/MyriadPro_Regular.otf");
        mDescriptionPaint.setTypeface(desTypeface);*/
        mDescriptionPaint.setTextSize(60);

        //圆环刻度画笔
        mCalibrationPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCalibrationPaint.setStrokeWidth(DisplayUtil.INSTANCE.dp2px(context, 18F));
        mCalibrationPaint.setStyle(Paint.Style.STROKE);
        mCalibrationPaint.setColor(getResources().getColor(R.color.ring_bg_color));
        mCalibrationPaint.setAlpha(80);
        mCalibrationPaint.setStrokeCap(Paint.Cap.ROUND);

        //内层进度画笔
        mInnerArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mInnerArcPaint.setStrokeWidth(DisplayUtil.INSTANCE.dp2px(context, 16F));
        mInnerArcPaint.setStyle(Paint.Style.STROKE);
        mInnerArcPaint.setColor(getResources().getColor(R.color.inner_arc_color));
        mInnerArcPaint.setStrokeCap(Paint.Cap.SQUARE);

        //内层进度画笔
        mArcProgressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mArcProgressPaint.setStrokeWidth(DisplayUtil.INSTANCE.dp2px(context, 16F));
        mArcProgressPaint.setStyle(Paint.Style.STROKE);
        mArcProgressPaint.setStrokeCap(Paint.Cap.SQUARE);

        matrix = new Matrix();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        setMeasuredDimension(resolveMeasure(widthMeasureSpec, defaultSize),
                resolveMeasure(heightMeasureSpec, defaultSize));
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        super.onSizeChanged(w, h, oldw, oldh);

        width = w;
        height = h;
        centerX = width / 2f;
        centerY = height / 2f;
        radius = (width - 2) / 2;
        radius1 = (width - 80 - (2 * 3)) / 2;

        mMiddleRect = new RectF(
                defaultPadding, defaultPadding,
                width - defaultPadding, height - defaultPadding);

        mMiddleProgressRect = new RectF(
                defaultPadding, defaultPadding,
                width - defaultPadding, height - defaultPadding);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        drawOuterLine(canvas);
        drawMiddleArc(canvas);
        drawInnerArc(canvas);
        drawRingProgress(canvas);
        drawCenterText(canvas);
        drawDescriptionText(canvas);
    }

    /**
     * 绘制外层线
     */
    private void drawOuterLine(Canvas canvas) {

        canvas.drawCircle(centerX, centerY, radius, mOuterLinePaint);
    }

    /**
     * 绘制外层圆环
     */
    private void drawMiddleArc(Canvas canvas) {

        canvas.drawCircle(centerX, centerY, radius1, mMiddleArcPaint);
    }

    /**
     * 绘制内层半圆
     */
    private void drawInnerArc(Canvas canvas) {

        canvas.drawArc(mMiddleProgressRect, mStartAngle, mEndAngle, false, mInnerArcPaint);

    }

    private int[] colors = {getResources().getColor(R.color.progress_color_1), getResources().getColor(R.color.progress_color_2)};
    /**
     * 绘制内层进度
     */
    private void drawRingProgress(Canvas canvas) {

        /*SweepGradient sweepGradient  = new SweepGradient(radius, radius,
                getResources().getColor(R.color.progress_color_1), getResources().getColor(R.color.progress_color_2));
        matrix.setRotate(-300, radius, radius);
        sweepGradient.setLocalMatrix(matrix);*/

        // 设置渐变色
        Shader shader = new SweepGradient(centerX, centerY, colors, null);
        mArcProgressPaint.setShader(shader);

        Path path = new Path();
        path.addArc(mMiddleProgressRect, mStartAngle, mCurrentAngle);
        matrix.reset();


        canvas.drawPath(path, mArcProgressPaint);
    }

    /**
     * 绘制中间文本
     */
    private void drawCenterText(Canvas canvas) {
        //绘制分数
        mTextPaint.setTextSize(DisplayUtil.INSTANCE.sp2px(mContext, 60));
        //mTextPaint.setStyle(Paint.Style.STROKE);
        canvas.drawText(String.valueOf(mMinNum), radius, radius, mTextPaint);

    }

    /**
     * 绘制分数描述文本
     */
    private void drawDescriptionText(Canvas canvas) {

        //绘制说明文字
        mDescriptionPaint.setTextSize(DisplayUtil.INSTANCE.sp2px(mContext, 14));
        canvas.drawText(description, radius, radius + 70, mDescriptionPaint);

    }



    /**
     * 根据传入的值进行测量
     */
    public int resolveMeasure(int measureSpec, int defaultSize) {

        int result = 0;
        int specSize = MeasureSpec.getSize(measureSpec);
        switch (MeasureSpec.getMode(measureSpec)) {

            case MeasureSpec.UNSPECIFIED:
                result = defaultSize;
                break;

            case MeasureSpec.AT_MOST:
                //设置warp_content时设置默认值
                result = Math.min(specSize, defaultSize);
                break;
            case MeasureSpec.EXACTLY:
                //设置math_parent 和设置了固定宽高值
                break;

            default:
                result = defaultSize;
        }

        return result;
    }


    public void setSesameValues(int values, String des) {

        mMaxNum = values;
        mTotalAngle = (mEndAngle * values)/100;
        //description = "Overview Score";
        description = des;

        startAnim();
    }


    public void startAnim() {

        ValueAnimator mAngleAnim = ValueAnimator.ofFloat(mCurrentAngle, mTotalAngle);
        mAngleAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        mAngleAnim.setDuration(3000);
        mAngleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                mCurrentAngle = (float) valueAnimator.getAnimatedValue();
                postInvalidate();
            }
        });
        mAngleAnim.start();

        ValueAnimator mNumAnim = ValueAnimator.ofInt(mMinNum, mMaxNum);
        mNumAnim.setDuration(3000);
        mNumAnim.setInterpolator(new LinearInterpolator());
        mNumAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                mMinNum = (int) valueAnimator.getAnimatedValue();
                postInvalidate();
            }
        });
        mNumAnim.start();
    }


    /**
     * dp2px
     */
    public int dp2px(int values) {

        float density = getResources().getDisplayMetrics().density;
        return (int) (values * density + 0.5f);
    }

    /**
     * 获取当前时间
     */
    public String getCurrentTime() {

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat format = new SimpleDateFormat("yyyy:MM:dd");
        return format.format(new Date());
    }

}
