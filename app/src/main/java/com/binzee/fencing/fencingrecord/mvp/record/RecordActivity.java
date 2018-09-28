package com.binzee.fencing.fencingrecord.mvp.record;

import android.os.Bundle;
import android.os.Vibrator;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioGroup;

import com.binzee.fencing.fencingrecord.R;
import com.binzee.fencing.fencingrecord.base.FoxActivity;
import com.binzee.fencing.fencingrecord.model.RecordBean;
import com.binzee.fencing.fencingrecord.util.RxUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.iwgang.countdownview.CountdownView;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class RecordActivity extends FoxActivity<IRecordContract.Presenter> implements IRecordContract.View, RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.red_field)
    EditText redField;
    @BindView(R.id.blue_field)
    EditText blueField;
    @BindView(R.id.red_s_field)
    NumberPicker redSField;
    @BindView(R.id.blue_s_field)
    NumberPicker blueSField;
    @BindView(R.id.time_style)
    RadioGroup timeStyle;
    @BindView(R.id.counter_field)
    CountdownView counterField;

    private final long LONG_TIME_MILL = 3 * 60 * 1000;
    private final long SHORT_TIME_MILL = 60 * 1000 + 30 * 1000;
    private final long FIVE_MIN_MILL = 5 * 60 * 1000;
    private long timeMill;
    private Vibrator vibrator;


    @Override
    protected int onInflateResource() {
        return R.layout.activity_record;
    }

    @Override
    protected void onBegin() {
        mPresenter = new Presenter(this);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        vibrator = (Vibrator)this.getSystemService(this.VIBRATOR_SERVICE);

        redSField.setMaxValue(20);
        redSField.setMinValue(0);
        redSField.setWrapSelectorWheel(false);
        blueSField.setMaxValue(20);
        blueSField.setMinValue(0);
        blueSField.setWrapSelectorWheel(false);

        timeStyle.setOnCheckedChangeListener(this);
        timeStyle.check(R.id.check_long);

        counterField.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
            @Override
            public void onEnd(CountdownView cv) {
                finishCountDown();
            }
        });
    }

    /**
     * 开始计时
     */
    @Override
    public void startCountDown() {
        //计时开始后禁止更改时间模式
        timeStyle.getChildAt(0).setEnabled(false);
        timeStyle.getChildAt(1).setEnabled(false);

        counterField.start(timeMill);
    }

    /**
     * 计时暂停
     */
    @Override
    public void pauseCountDown() {
        counterField.pause();
    }

    /**
     * 重启计时
     */
    @Override
    public void restartCountDown() {
        counterField.restart();
    }

    /**
     * 计时结束，发出提醒
     */
    @Override
    public void finishCountDown() {
        //震动
        long[] patter = {500, 500, 500, 500, 500, 500};
        vibrator.vibrate(patter, -1);
    }

    @Override
    public void finishing() {
        finish();
    }

    /**
     * 结束记录
     */
    private void finishRecord() {
        RecordBean bean = new RecordBean();
        String blue = blueField.getText().toString();
        String red = redField.getText().toString();
        blue = blue.isEmpty() ? "蓝方" : blue;
        red = red.isEmpty() ? "红方" : red;
        int blueS = blueSField.getValue();
        int redS = redSField.getValue();
        bean.setScroRed(redS);
        bean.setScroBlue(blueS);
        bean.setRed(red);
        bean.setBlue(blue);
        mPresenter.onFinish(bean);
    }

    /**
     * 点击事件
     */
    @OnClick({R.id.btn_stop, R.id.btn_start})
    public void onViewClicked(Button view) {
        switch (view.getId()) {
            case R.id.btn_stop: //停止并保存
                finishRecord();
                break;
            case R.id.btn_start:    //开始/暂停
                String tag = (String) view.getTag();
                switch (tag) {
                    case "awaiting":    //点击开始
                        view.setTag("running");
                        view.setText("暂停");
                        startCountDown();
                        break;
                    case "running":     //点击暂停
                        view.setTag("pausing");
                        view.setText("继续");
                        pauseCountDown();
                        break;
                    case "pausing":     //点击继续
                        view.setTag("running");
                        view.setText("暂停");
                        restartCountDown();
                        break;
                }
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.check_long:
                //三分钟规则
                timeMill = LONG_TIME_MILL;
                break;
            case R.id.check_short:
                //一分半规则
                timeMill = SHORT_TIME_MILL;
                break;
            case R.id.check_five:
                //一分半规则
                timeMill = FIVE_MIN_MILL;
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        vibrator.cancel();
    }
}
