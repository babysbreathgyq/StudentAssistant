package com.example.studentassistant.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import com.example.studentassistant.R;
import com.example.studentassistant.db.MyDBHelper;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class DataAnalyseActivity extends AppCompatActivity {

    // 1.定义对象
    LineChart income_chart, payout_chart;
    MyDBHelper mHelper;
    SQLiteDatabase db;
    //    String[] inData = getResources().getStringArray(R.array.incomeType);
    String[] inData = {"工资收入", "加班收入", "奖金收入", "兼职收入", "经营所得", "投资收入", "利息收入", "礼金收入", "中奖收入"};
    // 收入类型数据统计的初始值
    int wagesMoney = 0;
    int workOvertimeMoney = 0;
    int bonusMoney = 0;
    int partTimeJobMoney = 0;
    int managementMoney = 0;
    int investmentMoney = 0;
    int interestMoney = 0;
    int cashGiftMoney = 0;
    int winPriceMoney = 0;

    String[] outData = {"学习-书籍","学习-培训","日常-美食","食品-水果","食品-零食","交通-出行","娱乐-购物","娱乐-电影","其他"};
    // 支出类型数据统计的初始值
    int bookMoney = 0;
    int trainMoney = 0;
    int foodMoney = 0;
    int fruitMoney = 0;
    int snacksMoney = 0;
    int travelMoney = 0;
    int shoppingMoney = 0;
    int filmMoney = 0;
    int other = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_analyse);

        // 2.绑定控件
        initView();

        // 3.收入汇总分析
        incomeData();

        // 4.支出汇总分析
        payoutData();
    }

    private void initView() {
        income_chart = findViewById(R.id.income_chart_data);
        payout_chart = findViewById(R.id.payout_chart_data);
        mHelper = new MyDBHelper(DataAnalyseActivity.this);
        db = mHelper.getWritableDatabase();
    }

    // 3.收入汇总分析-----------------------
    @SuppressLint("Range")
    private void incomeData() {
        // 3.1 获取数据
        // 从数据库查询所有的新增收入信息
        Cursor cursor = db.rawQuery("select * from in_come",null);
        // 采用循环的方式读数据，存数据
        while (cursor.moveToNext()) {
            // 取出数据
            double myMoney = cursor.getDouble(cursor.getColumnIndex("inMoney"));
            String myType = cursor.getString(cursor.getColumnIndex("inType"));
            // 每种数据对应的金额累加到不同变量当中
            switch (myType) {
                case "工资收入" :
                    wagesMoney += myMoney;
                    break;
                case "加班收入" :
                    workOvertimeMoney += myMoney;
                    break;
                case "奖金收入" :
                    bonusMoney += myMoney;
                    break;
                case "兼职收入" :
                    partTimeJobMoney += myMoney;
                    break;
                case "经营所得" :
                    managementMoney += myMoney;
                    break;
                case "投资收入" :
                    investmentMoney += myMoney;
                    break;
                case "利息收入" :
                    interestMoney += myMoney;
                    break;
                case "礼金收入" :
                    cashGiftMoney += myMoney;
                    break;
                case "中奖收入" :
                    winPriceMoney += myMoney;
                    break;
            }
        }

        // 3.2 LineChart图标初始化设置---xy轴的设置
        XAxis xAxis = income_chart.getXAxis(); // 获取x图标的x轴轴线
        YAxis yAxisLeft = income_chart.getAxisLeft(); // 获取此图标的y轴左侧轴线
        YAxis yAxisRight = income_chart.getAxisRight(); // 获取此图标的y轴左侧轴线
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // 设置x轴线的位置为底部
        yAxisLeft.setAxisMinimum(0f); // 保证y轴从0开始，不然会上移一点
        yAxisRight.setAxisMinimum(0f);
        xAxis.setValueFormatter(new IAxisValueFormatter() { // X轴自定义标签的设置
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                return inData[(int) v];
            }
        });

        // 3.3 曲线初始化设置
        List<Entry> inEntries = new ArrayList<>(); // Y轴的数据
        inEntries.add(new Entry(0,wagesMoney));
        inEntries.add(new Entry(1,workOvertimeMoney));
        inEntries.add(new Entry(2,bonusMoney));
        inEntries.add(new Entry(3,partTimeJobMoney));
        inEntries.add(new Entry(4,managementMoney));
        inEntries.add(new Entry(5,investmentMoney));
        inEntries.add(new Entry(6,interestMoney));
        inEntries.add(new Entry(7,cashGiftMoney));
        inEntries.add(new Entry(8,winPriceMoney));
        LineDataSet lineDataSet = new LineDataSet(inEntries,"金额"); // 代表一条线，金额是曲线名称
        lineDataSet.setValueTextSize(25); // 文字的大小
        lineDataSet.setValueTextColor(Color.WHITE); // 文字的颜色
        lineDataSet.setDrawFilled(true); // 设置曲线图填充

        // 3.4 展示曲线
        LineData data = new LineData(lineDataSet); // 创建LineData对象 属于LineChart折线图的数据集合
        income_chart.setData(data); // 添加到图表中
    }

    // 4.支出汇总分析-----------------------
    @SuppressLint("Range")
    private void payoutData() {
        // 3.1 获取数据
        // 从数据库查询所有的新增收入信息
        Cursor cursor = db.rawQuery("select * from pay_out",null);
        // 采用循环的方式读数据，存数据
        while (cursor.moveToNext()) {
            // 取出数据
            double myMoney = cursor.getDouble(cursor.getColumnIndex("outMoney"));
            String myType = cursor.getString(cursor.getColumnIndex("outType"));
            // 每种数据对应的金额累加到不同变量当中
            switch (myType) {
                case "学习-书籍" :
                    bookMoney += myMoney;
                    break;
                case "学习-培训" :
                    trainMoney += myMoney;
                    break;
                case "日常-美食" :
                    foodMoney += myMoney;
                    break;
                case "食品-水果" :
                    fruitMoney += myMoney;
                    break;
                case "食品-零食" :
                    snacksMoney += myMoney;
                    break;
                case "交通-出行" :
                    travelMoney += myMoney;
                    break;
                case "娱乐-购物" :
                    shoppingMoney += myMoney;
                    break;
                case "娱乐-电影" :
                    filmMoney += myMoney;
                    break;
                case "其他" :
                    other += myMoney;
                    break;
            }
        }

        // 3.2 LineChart图标初始化设置---xy轴的设置
        XAxis xAxis = payout_chart.getXAxis(); // 获取x图标的x轴轴线
        YAxis yAxisLeft = payout_chart.getAxisLeft(); // 获取此图标的y轴左侧轴线
        YAxis yAxisRight = payout_chart.getAxisRight(); // 获取此图标的y轴左侧轴线
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // 设置x轴线的位置为底部
        yAxisLeft.setAxisMinimum(0f); // 保证y轴从0开始，不然会上移一点
        yAxisRight.setAxisMinimum(0f);
        xAxis.setValueFormatter(new IAxisValueFormatter() { // X轴自定义标签的设置
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                return outData[(int) v];
            }
        });

        // 3.3 曲线初始化设置
        List<Entry> outEntries = new ArrayList<>(); // Y轴的数据
        outEntries.add(new Entry(0,bookMoney));
        outEntries.add(new Entry(1,trainMoney));
        outEntries.add(new Entry(2,foodMoney));
        outEntries.add(new Entry(3,fruitMoney));
        outEntries.add(new Entry(4,snacksMoney));
        outEntries.add(new Entry(5,travelMoney));
        outEntries.add(new Entry(6,shoppingMoney));
        outEntries.add(new Entry(7,filmMoney));
        outEntries.add(new Entry(8,other));
        LineDataSet lineDataSet = new LineDataSet(outEntries,"金额"); // 代表一条线，金额是曲线名称
        lineDataSet.setValueTextSize(25); // 文字的大小
        lineDataSet.setValueTextColor(Color.WHITE); // 文字的颜色
        lineDataSet.setDrawFilled(true); // 设置曲线图填充

        // 3.4 展示曲线
        LineData data = new LineData(lineDataSet); // 创建LineData对象 属于LineChart折线图的数据集合
        payout_chart.setData(data); // 添加到图表中
    }
}