package com.example.studentassistant.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.studentassistant.R;
import com.example.studentassistant.view.MainActivity;

public class EconomicsActivity extends AppCompatActivity {

    // 1.定义对象
    Button bt_newIncome, bt_incomeDetail, bt_newPay, bt_payDetail, bt_dataAnalyse, bt_returnMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_economics);

        // 2.绑定控件
        initView();

        // 3.按钮单击事件
        btnOnClick();
    }

//     绑定控件---------------
    private void initView() {
        bt_newIncome = findViewById(R.id.bt_newIncome_main);
        bt_incomeDetail = findViewById(R.id.bt_incomeDetail_main);
        bt_newPay = findViewById(R.id.bt_newPay_main);
        bt_payDetail = findViewById(R.id.bt_payDetail_main);
        bt_dataAnalyse = findViewById(R.id.dataAnalyse_main);
        bt_returnMain = findViewById(R.id.bt_return_main);
    }

    // 按钮单击事件-----------------------
    private void btnOnClick() {
        bt_newIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EconomicsActivity.this, NewIncomeActivity.class);
                startActivity(intent);
            }
        });
        bt_incomeDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EconomicsActivity.this, IncomeDetailActivity.class);
                startActivity(intent);
            }
        });
        bt_newPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EconomicsActivity.this, NewPayActivity.class);
                startActivity(intent);
            }
        });
        bt_payDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EconomicsActivity.this, PayDetailActivity.class);
                startActivity(intent);
            }
        });
        bt_dataAnalyse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EconomicsActivity.this, DataAnalyseActivity.class);
                startActivity(intent);
            }
        });
        bt_returnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EconomicsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}