package com.example.studentassistant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


import androidx.annotation.NonNull;

import com.example.studentassistant.R;
import com.example.studentassistant.bean.ToDoListBean;

import java.util.List;

public class ToDoListAdapter extends ArrayAdapter<ToDoListBean> {

    private int resourceId;

    public ToDoListAdapter(Context context, int textViewResourceId, List<ToDoListBean> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ToDoListBean toDoListBean = getItem(position); // 获取当前项的实例
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.mCheckBox = view.findViewById(R.id.cb_todolist);
            viewHolder.mTextView = view.findViewById(R.id.textview);
            view.setTag(viewHolder);
        } else {
            view = convertView; // 不为空，则直接对convertView重用
            viewHolder = (ViewHolder) view.getTag(); // 重新获取ViewHolder
        }
        viewHolder.mTextView.setText(toDoListBean.getName());
        return view;
    }

    // 新增一个内部类ViewHolder，用于对控件的实例进行缓存。
    class ViewHolder {
        CheckBox mCheckBox;
        TextView mTextView;
    }
}
