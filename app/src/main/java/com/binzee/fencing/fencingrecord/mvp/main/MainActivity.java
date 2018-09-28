package com.binzee.fencing.fencingrecord.mvp.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;

import com.binzee.fencing.fencingrecord.R;
import com.binzee.fencing.fencingrecord.base.FoxActivity;
import com.binzee.fencing.fencingrecord.base.adapter.RecordListAdapter;
import com.binzee.fencing.fencingrecord.model.RecordBean;
import com.binzee.fencing.fencingrecord.mvp.record.RecordActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends FoxActivity<MainPresenter> implements IMainContract.View {
    @BindView(R.id.list_field)
    ListView listField;
    private List<RecordBean> data = new ArrayList<>();

    @Override
    protected int onInflateResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onBegin() {
        mPresenter = new MainPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.requestData();
    }

    @Override
    public void showList(List<RecordBean> data) {
        if (data == null || data.isEmpty()) {
            listField.setVisibility(View.GONE);
            setNoDataMask(true);
            return;
        }
        listField.setVisibility(View.VISIBLE);
        setNoDataMask(false);
        if (data.size() == this.data.size())   //说明数据没有变化
            return;
        //更新列表数据
        RecordListAdapter adapter = new RecordListAdapter(this, R.layout.record_list_item, data);
        listField.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_clear:
                new AlertDialog.Builder(this)
                        .setTitle("警告")
                        .setMessage("是否清空列表？")
                        .setCancelable(false)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mPresenter.clearList();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
                break;
            case R.id.menu_exit:
                new AlertDialog.Builder(this)
                        .setTitle("警告")
                        .setMessage("是否退出？")
                        .setCancelable(false)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                System.exit(0);
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void jump() {
        startActivity(new Intent(MainActivity.this, RecordActivity.class));
    }

    @OnClick(R.id.fab_record)
    public void onViewClicked() {
        jump();
    }
}
