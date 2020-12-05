package com.example.helper.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.helper.R;
import com.example.helper.beans.DataPoint;
import com.example.helper.beans.DataStreams;
import com.example.helper.widget.FakeAppleListItemView;

public class DevInfoAdapter extends RecyclerView.Adapter<DevInfoAdapter.ViewHolder> {
    private DataStreams mModel;

    public DevInfoAdapter(DataStreams dataStreams) {
        this.mModel = dataStreams;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        FakeAppleListItemView mItemView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemView = (FakeAppleListItemView) itemView;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        //这里好像只能用打气筒创建对象？
        return new ViewHolder(layoutInflater.inflate(R.layout.dev_info_item,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        DataPoint dataPoint = mModel.getList().get(i);
        String ID = dataPoint.getId();
        String desc = dataPoint.getCurrent_value().toString();
        viewHolder.mItemView.setTitle(ID);
        viewHolder.mItemView.setDesc(desc);
    }

    @Override
    public int getItemCount() {
        return mModel.getList().size();
    }
}
