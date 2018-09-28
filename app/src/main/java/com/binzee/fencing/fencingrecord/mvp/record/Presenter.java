package com.binzee.fencing.fencingrecord.mvp.record;

import com.binzee.fencing.fencingrecord.base.mvp.BasePresenter;
import com.binzee.fencing.fencingrecord.base.mvp.IBaseView;
import com.binzee.fencing.fencingrecord.model.RecordBean;

class Presenter extends BasePresenter<IRecordContract.View, IRecordContract.Model> implements IRecordContract.Presenter {

    public Presenter(IRecordContract.View view) {
        super(view);
        model = new Model();
    }

    @Override
    public void onFinish(RecordBean bean) {
        if (model.addRecord(bean))
            view.finishing();
    }
}
