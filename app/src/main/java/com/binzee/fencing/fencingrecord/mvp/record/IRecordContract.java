package com.binzee.fencing.fencingrecord.mvp.record;

import com.binzee.fencing.fencingrecord.base.mvp.IBaseModel;
import com.binzee.fencing.fencingrecord.base.mvp.IBasePresenter;
import com.binzee.fencing.fencingrecord.base.mvp.IBaseView;
import com.binzee.fencing.fencingrecord.model.RecordBean;

public interface IRecordContract {
    interface View extends IBaseView{
        void startCountDown();
        void pauseCountDown();
        void restartCountDown();
        void finishCountDown();
        void finishing();
    }
    interface Presenter extends IBasePresenter{
        void onFinish(RecordBean bean);
    }
    interface Model extends IBaseModel{
        boolean addRecord(RecordBean bean);
    }
}
