package com.binzee.fencing.fencingrecord.util;

import com.binzee.fencing.fencingrecord.model.RecordBean;

import org.litepal.LitePal;
import org.litepal.crud.callback.UpdateOrDeleteCallback;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class DatabaseUtil {

    /**
     * 获取全部数据
     */
    public static Observable<List<RecordBean>> getRecords(){
        synchronized (DatabaseUtil.class) {
            return Observable.create(new ObservableOnSubscribe<List<RecordBean>>() {
                @Override
                public void subscribe(ObservableEmitter<List<RecordBean>> emitter) throws Exception {
                    emitter.onNext(LitePal.findAll(RecordBean.class));
                }
            }).compose(RxUtil.<List<RecordBean>>applySchedulers());
        }
    }

    /**
     * 清空列表
     */
    public static void clearRecords(){
        synchronized (DatabaseUtil.class) {
            for (RecordBean bean : LitePal.findAll(RecordBean.class)){
                bean.deleteAsync().listen(new UpdateOrDeleteCallback() {
                    @Override
                    public void onFinish(int rowsAffected) {

                    }
                });
            }
        }
    }

    public static boolean addRecord(RecordBean bean){
        bean.save();
        return bean.isSaved();
    }
}
