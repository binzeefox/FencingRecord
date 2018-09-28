package com.binzee.fencing.fencingrecord.mvp.main;

import com.binzee.fencing.fencingrecord.model.RecordBean;
import com.binzee.fencing.fencingrecord.util.DatabaseUtil;

import java.util.List;

import io.reactivex.Observable;

public class MainModel implements IMainContract.Model {
    @Override
    public Observable<List<RecordBean>> getRecords() {
        return DatabaseUtil.getRecords();
    }

    @Override
    public boolean clearRecords() {
        DatabaseUtil.clearRecords();
        return true;
    }
}
