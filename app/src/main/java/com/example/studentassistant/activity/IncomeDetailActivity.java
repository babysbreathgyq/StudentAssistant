package com.example.studentassistant.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.studentassistant.R;
import com.example.studentassistant.adapter.IncomeAdapter;
import com.example.studentassistant.bean.IncomeBean;
import com.example.studentassistant.db.MyDBHelper;

import java.util.ArrayList;
import java.util.List;

public class IncomeDetailActivity extends AppCompatActivity {

    // 1 定义对象
    RecyclerView recyclerView;
    MyDBHelper myDBHelper;
    SQLiteDatabase db;
    List<IncomeBean> arr = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_detail);

        // 2 绑定控件
        initView();

        // 3 准备数据
        initData();

        // 4 设计每一行的子布局

        // 5 定义适配器：数据和子布局关联起来（桥梁的作用）
        IncomeAdapter incomeAdapter = new IncomeAdapter(IncomeDetailActivity.this,arr);

        // 6 将适配器和布局管理器加载到控件中
        // 采用网格布局，每行显示一列数据
        StaggeredGridLayoutManager st = new StaggeredGridLayoutManager(StaggeredGridLayoutManager.VERTICAL,1);
        recyclerView.setLayoutManager(st);
        recyclerView.setAdapter(incomeAdapter);
    }

    // 绑定控件----------------------------
    private void initView() {
        recyclerView = findViewById(R.id.recycler_view_inDetail);
        myDBHelper = new MyDBHelper(IncomeDetailActivity.this);
        db = myDBHelper.getWritableDatabase();
    }

    // 准备数据----------------------------
    @SuppressLint("Range")
    private void initData() {
        // 从数据库查询所有的新增收入信息
        Cursor cursor = db.rawQuery("select * from in_come",null);
        // 采用循环的方式读数据，存数据
        while (cursor.moveToNext()) {
            // 取出数据
            int myId = cursor.getInt(cursor.getColumnIndex("id"));
            double myMoney = cursor.getDouble(cursor.getColumnIndex("inMoney"));
            String myTime = cursor.getString(cursor.getColumnIndex("inTime"));
            String myType = cursor.getString(cursor.getColumnIndex("inType"));
            String myPayer = cursor.getString(cursor.getColumnIndex("inPayer"));
            String myRemark = cursor.getString(cursor.getColumnIndex("inRemark"));
            // 将每条数据封装成一个对象放入数组中
            IncomeBean incomeBean = new IncomeBean(myId,myMoney,myTime,myType,myPayer,myRemark);
            arr.add(incomeBean);
        }
    }
}