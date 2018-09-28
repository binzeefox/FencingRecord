package com.binzee.fencing.fencingrecord.base.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.binzee.fencing.fencingrecord.R;
import com.binzee.fencing.fencingrecord.model.RecordBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecordListAdapter extends ArrayAdapter<RecordBean> {

    private final int resource;

    public RecordListAdapter(@NonNull Context context, int resource, @NonNull List<RecordBean> data) {
        super(context, resource, data);
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        RecordBean bean = getItem(position);
        String red = bean.getRed();
        String blue = bean.getBlue();
        int redS = bean.getScroRed();
        int blueS = bean.getScroBlue();
        View view = LayoutInflater.from(getContext()).inflate(resource, null);
        TextView fighterField = view.findViewById(R.id.fighter_field);
        TextView resultField = view.findViewById(R.id.result_field);
        fighterField.setText(red + "\0VS\0" + blue);
        resultField.setText(String.valueOf(redS) + ":" + blueS);

        return view;
    }
}
