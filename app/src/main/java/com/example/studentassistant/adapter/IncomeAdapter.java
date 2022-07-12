package com.example.studentassistant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.studentassistant.R;
import com.example.studentassistant.bean.IncomeBean;

import java.util.List;

public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.ViewHolder>{

    Context mContext;
    List<IncomeBean> arr2;

    // 使用适配器时，会应用它的构造方法
    public IncomeAdapter(Context context, List<IncomeBean> arr2) {
        mContext = context;
        this.arr2 = arr2;
    }

    // 用于创建ViewHolder实例，
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 用布局管理器加载子布局，将它作为根布局parent出现，上面不放任何东西false
        View view = LayoutInflater.from(mContext).inflate(R.layout.recy_item_in,parent,false);
        // 把子布局放入ViewHolder中
        ViewHolder mHolder = new ViewHolder(view);
        return mHolder;
    }

    // 对RecyclerView的子项进行赋值
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // 每一条记录就是我们封装的一个类
        IncomeBean incomeBean = arr2.get(position);
        holder.item_payer.setText("收款-来自" + incomeBean.getPayer());
        holder.item_type.setText(incomeBean.getType());
        holder.item_time.setText(incomeBean.getTime());
        holder.item_remark.setText(incomeBean.getRemark());
        holder.item_money.setText("+" + incomeBean.getMoney());
    }

    // RecyclerView一共有多少个子项
    @Override
    public int getItemCount() {
        return arr2.size();
    }

    // 创建内部类,将控件放入ViewHolder中
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView item_payer, item_type, item_time, item_remark, item_money;
        public ViewHolder(View itemView) {
            super(itemView);
            item_payer = itemView.findViewById(R.id.item_payer_in);
            item_type = itemView.findViewById(R.id.item_type_in);
            item_time = itemView.findViewById(R.id.item_time_in);
            item_remark = itemView.findViewById(R.id.item_remark_in);
            item_money = itemView.findViewById(R.id.item_money_in);
        }
    }
}
