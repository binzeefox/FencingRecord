package com.binzee.fencing.fencingrecord.mvp.main;

import com.binzee.fencing.fencingrecord.base.mvp.IBaseModel;
import com.binzee.fencing.fencingrecord.base.mvp.IBasePresenter;
import com.binzee.fencing.fencingrecord.base.mvp.IBaseView;
import com.binzee.fencing.fencingrecord.model.RecordBean;

import java.util.List;

import io.reactivex.Observable;

public interface IMainContract {

    interface View extends IBaseView{
        /**
         * 显示列表
         * @param data 数据
         */
        void showList(List<RecordBean> data);

        /**
         * 跳转下一页
         */
        void jump();
    }

    interface Presenter extends IBasePresenter{
        /**
         * 清空列表
         */
        void clearList();

        /**
         * 请求数据
         */
        void requestData();
    }

    interface Model extends IBaseModel{
        /**
         * 请求数据
         */
        Observable<List<RecordBean>> getRecords();

        /**
         * 清空数据
         * @return  是否成功
         */
        boolean clearRecords();
    }
}
