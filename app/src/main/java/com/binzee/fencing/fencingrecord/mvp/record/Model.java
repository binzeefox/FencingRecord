package com.binzee.fencing.fencingrecord.mvp.record;

import com.binzee.fencing.fencingrecord.model.RecordBean;
import com.binzee.fencing.fencingrecord.util.DatabaseUtil;

class Model implements IRecordContract.Model {
    @Override
    public boolean addRecord(RecordBean bean) {
        return DatabaseUtil.addRecord(bean);
    }
}
