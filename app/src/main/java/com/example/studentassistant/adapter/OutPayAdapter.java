package com.example.studentassistant.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.studentassistant.R;
import com.example.studentassistant.bean.OutPayBean;
import com.example.studentassistant.other.InManagerActivity;
import com.example.studentassistant.other.OutManagerActivity;

import java.util.List;

public class OutPayAdapter extends RecyclerView.Adapter<OutPayAdapter.ViewHolder>{

    Context mContext;
    List<OutPayBean> arr2;

    // 使用适配器时，会应用它的构造方法
    public OutPayAdapter(Context context, List<OutPayBean> arr2) {
        mContext = context;
        this.arr2 = arr2;
    }

    // 用于创建ViewHolder实例，
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 用布局管理器加载子布局，将它作为根布局parent出现，上面不放任何东西false
        View view = LayoutInflater.from(mContext).inflate(R.layout.recy_item_out,parent,false);
        // 把子布局放入ViewHolder中
        ViewHolder mHolder = new ViewHolder(view);
        return mHolder;
    }

    // 对RecyclerView的子项进行赋值
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // 每一条记录就是我们封装的一个类
        final OutPayBean outPayBean = arr2.get(position);
        holder.item_payee.setText("付款给" + outPayBean.getPayee());
        holder.item_type.setText(outPayBean.getType());
        holder.item_time.setText(outPayBean.getTime());
        holder.item_remark.setText(outPayBean.getRemark());
        holder.item_money.setText("-" + outPayBean.getMoney());
        // 完善：点击某一个条目，跳转到支出管理页面
        // 找出每一行的子布局，给其添加单击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 跳转到收入管理界面的代码
                Intent intent = new Intent(mContext, OutManagerActivity.class);
                // 跳转之前把数据传递过去
                intent.putExtra("sero",outPayBean);
                mContext.startActivity(intent);
                ((Activity)mContext).finish();
            }
        });
    }

    // RecyclerView一共有多少个子项
    @Override
    public int getItemCount() {
        return arr2.size();
    }

    // 创建内部类,将控件放入ViewHolder中
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView item_payee, item_type, item_time, item_remark, item_money;
        public ViewHolder(View itemView) {
            super(itemView);
            item_payee = itemView.findViewById(R.id.item_payee_out);
            item_type = itemView.findViewById(R.id.item_type_out);
            item_time = itemView.findViewById(R.id.item_time_out);
            item_remark = itemView.findViewById(R.id.item_remark_out);
            item_money = itemView.findViewById(R.id.item_money_out);
        }
    }
}
