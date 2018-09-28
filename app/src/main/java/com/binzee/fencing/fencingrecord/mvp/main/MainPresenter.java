package com.binzee.fencing.fencingrecord.mvp.main;

import com.binzee.fencing.fencingrecord.base.mvp.BasePresenter;
import com.binzee.fencing.fencingrecord.base.mvp.IBasePresenter;
import com.binzee.fencing.fencingrecord.base.mvp.IBaseView;
import com.binzee.fencing.fencingrecord.model.RecordBean;

import java.util.List;

import io.reactivex.functions.Consumer;

public class MainPresenter extends BasePresenter<IMainContract.View, IMainContract.Model> implements IMainContract.Presenter {

    public MainPresenter(IMainContract.View view) {
        super(view);
        model = new MainModel();
    }

    @Override
    public void clearList() {
        model.clearRecords();
        view.showList(null);
    }

    @Override
    public void requestData() {
        dContainer.add(model.getRecords().subscribe(new Consumer<List<RecordBean>>() {
            @Override
            public void accept(List<RecordBean> recordBeans) throws Exception {
                view.showList(recordBeans);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        }));
    }
}
