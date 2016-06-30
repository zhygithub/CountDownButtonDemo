package network.scau.com.countdownbuttondemo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/6/30 0030.
 */

public class CountDownButton extends Button{
    /**
     * 默认倒计时时间
     */
    private final int DEFAULT_COUNT = 60;

    /**
     * 倒计时时间
     */
    private int iTotalCount = DEFAULT_COUNT;

    private int iCountingNum = DEFAULT_COUNT;

    /**
     * 默认开始显示的字符串
     */
    private final String DEFAULT_START_STR = "开始";

    /**
     * 开始显示的字符串
     */
    private String sStartStr = DEFAULT_START_STR;

    /**
     * 等待颜色
     */
    private int normalColor = 0xffff4000;

    /**
     * 倒计时颜色
     */
    private int countingColor = 0xffffffff;

    /**
     * 倒计时订阅者
     */
    private Subscriber<Long> countDownTimer;


    public CountDownButton(Context context) {
        super(context);
        init();
    }

    public CountDownButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CountDownButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        onPre();
    }

    public int getiTotalCount() {
        return iTotalCount;
    }

    public void setiTotalCount(int iTotalCount) {
        this.iTotalCount = iTotalCount;
        this.iCountingNum = iTotalCount;
    }

    /**
     * 开始倒计时
     */
    public void countDown() {
        initTimer();
        onCounting();
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(countDownTimer);

    }

    private void initTimer() {
        countDownTimer = new Subscriber<Long>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Long aLong) {
                iCountingNum--;

                setText(iCountingNum + "");
                if (iCountingNum == 0l) {
                    countDownTimer.unsubscribe();
                    setClickable(true);
                    onPre();

                }
            }
        };
    }

    /**
     * 停止倒计时 恢复成最初样子
     */
    public void shutDown() {
        if (countDownTimer != null) {
            countDownTimer.unsubscribe();
            onPre();
        }
    }

    private void onPre() {
        setClickable(true);
        iCountingNum = iTotalCount;
        setText(sStartStr);
        setTextColor(normalColor);
    }

    private void onCounting() {
        setClickable(false);
        setTextColor(countingColor);
    }
}
