package com.example.studentassistant.activity;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.studentassistant.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class NewCalendar extends LinearLayout {

    // 定义对象
    private ImageView btnPrev;
    private ImageView btnNext;
    private TextView txtData;
    private GridView grid;

    private Calendar curDate = Calendar.getInstance();
    private String displayFormat;

    public NewCalendar(Context context) {
        super(context);
    }

    public NewCalendar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initControl(context, attrs);
    }

    public NewCalendar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initControl(context, attrs);
    }

    private void initControl(Context context, AttributeSet attrs) {
        // 初始化控件
        initView(context);
        // 绑定事件
        bindControlEvent();

        // 读取attribute
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.NewCalendar);
        try {
            // NewCalendar_dateFormat前面是控件的名字，后面是属性，是系统帮我们拼接的
            String format = ta.getString(R.styleable.NewCalendar_dateFormat);
            displayFormat = format;
            if (displayFormat == null) {
                displayFormat = "MMM yyyy";
            }
        }
        finally {
            ta.recycle();
        }

        renderCalendar();
    }

    // 初始化控件-------------------------
    private void initView(Context context) {

        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.calendar_view, this);

        btnPrev =  findViewById(R.id.btnPrev);
        btnNext =  findViewById(R.id.btnNext);
        txtData =  findViewById(R.id.txtDate);
        grid =  findViewById(R.id.calendar_grid);
    }

    private void bindControlEvent() {

        btnPrev.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                curDate.add(Calendar.MONTH, -1); // 向前翻一个月
                renderCalendar(); // 渲染我们的控件
            }

        });
        btnNext.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                curDate.add(Calendar.MONTH, 1); // 向后翻一个月
                renderCalendar();
            }
        });
    }

    private void renderCalendar() {
        SimpleDateFormat sdf = new SimpleDateFormat(displayFormat); // 格式化日期
        txtData.setText(sdf.format(curDate.getTime()));

        // GridView数据的展示
        ArrayList<Date> cells = new ArrayList<>();
        Calendar calendar = (Calendar) curDate.clone();

        // 计算出当月有多少天
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int prevDays = calendar.get(Calendar.DAY_OF_WEEK)-1;
        calendar.add(Calendar.DAY_OF_MONTH, -prevDays);

        int maxCellCount = 6 * 7;
        while (cells.size() < maxCellCount) {
            cells.add(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        grid.setAdapter(new CalendarAdapter(getContext(), cells));
    }

    private class CalendarAdapter extends ArrayAdapter<Date> {

        LayoutInflater inflater;

        public CalendarAdapter(Context context, ArrayList<Date> days) {
            super(context, R.layout.calendar_text_day, days);
            inflater = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            Date date = getItem(position);

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.calendar_text_day,parent,false);
            }

            int day = date.getDate();
            ((TextView)convertView).setText(String.valueOf(day));

            // 获取当前的日期
            Date now = new Date();

            boolean isTheSameMonth = false;
            // 判断月份是否相同
            if (date.getMonth() == now.getMonth()) {
                isTheSameMonth = true;
            }

            if (isTheSameMonth) {
                // 有效月份
                ((TextView)convertView).setTextColor(Color.parseColor("#0000ff"));
            } else {
                ((TextView)convertView).setTextColor(Color.parseColor("#666666"));
            }


            if (now.getDate() == date.getDate() && now.getMonth() == date.getMonth() && now.getYear() == date.getYear()) {
                ((TextView)convertView).setTextColor(Color.parseColor("#ff0000"));
                ((com.example.studentassistant.activity.Calendar_day_textView)convertView).isToday = true;
            }
            return convertView;
        }
    }
}
