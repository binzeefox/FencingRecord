package com.binzee.fencing.fencingrecord.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.binzee.fencing.fencingrecord.R;
import com.binzee.fencing.fencingrecord.base.mvp.IBasePresenter;

import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;

public abstract class FoxActivity<P extends IBasePresenter> extends AppCompatActivity {

    protected P mPresenter;
    protected CompositeDisposable dContainer;   //RxJava订阅回收

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dContainer = new CompositeDisposable();

        //加载布局
        setContentView(R.layout.activity_base);
        ((ViewGroup)findViewById(R.id.container)).addView(onInflateView(getLayoutInflater()));
        ButterKnife.bind(this);

        setNoDataMask(false);
        onBegin();
    }

//    ******↓内部回调

    /**
     * 加载布局
     */
    protected View onInflateView(LayoutInflater inflater){
        return inflater.inflate(onInflateResource(), null);
    }

    /**
     * 加载布局
     */
    protected abstract int onInflateResource();

    /**
     * onCreate实际业务
     */
    protected abstract void onBegin();

//    ******↓生命周期

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dContainer.dispose();
        if (mPresenter != null) {
            mPresenter.onDestroy();
            mPresenter = null;
        }
    }

//    ******↓内部方法

    /**
     * 设置是否没有数据
     */
    protected void setNoDataMask(boolean isNoData){
        int visible = View.VISIBLE;
        int gone = View.GONE;

        findViewById(R.id.nodata_mask).setVisibility(isNoData ? visible : gone);
//        findViewById(R.id.container).setVisibility(isNoData ? gone : visible);
    }
}
