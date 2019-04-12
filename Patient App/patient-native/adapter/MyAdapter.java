package com.pitt.patient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pitt.patientapp.R;

import java.util.List;

public class MyAdapter extends BaseAdapter implements View.OnClickListener{

    private Context context;

    private List<String> data;
    private LayoutInflater mInflater;
    private Callback mCallback;

    public MyAdapter(List<String> data, Callback callback, Context context) {
        this.data = data;
        this.mCallback = callback;
        this.mInflater = LayoutInflater.from(context);
    }

    public interface Callback {
        public void click(View v);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.textView = (TextView) convertView
                    .findViewById(R.id.tv_date);
            holder.button = (Button) convertView.findViewById(R.id.btn_finish);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(data.get(position));

        holder.button.setOnClickListener(this);
        holder.button.setTag(position);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        mCallback.click(v);
    }

    static class ViewHolder{
        TextView textView;
        Button button;
    }


}
