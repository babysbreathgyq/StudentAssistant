package com.example.studentassistant.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.studentassistant.R;
import com.example.studentassistant.db.MyDBHelper;

public class NewIncomeActivity extends AppCompatActivity {

    // 定义对象
    EditText et_money, et_time, et_payer, et_remark;
    Spinner sp_type;
    Button bt_save, bt_cancel;

    MyDBHelper myDBHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_income);

        // 绑定控件
        initView();

        // 保存按钮功能的实现
        btnSave();

        // 取消按钮功能的实现
        btnCancel();
    }

    // 绑定控件-----------------
    private void initView() {
        et_money = findViewById(R.id.et_money_newIn);
        et_time = findViewById(R.id.et_time_newIn);
        et_payer = findViewById(R.id.et_payer_newIn);
        et_remark = findViewById(R.id.et_remark_newIn);
        sp_type = findViewById(R.id.sp_type_newIn);
        bt_save = findViewById(R.id.bt_save_newIn);
        bt_cancel = findViewById(R.id.bt_cancel_newIn);
        myDBHelper = new MyDBHelper(NewIncomeActivity.this);
        db = myDBHelper.getWritableDatabase();
    }

    // 保存按钮功能的实现------------
    private void btnSave() {
        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 获取输入的内容保存到数据库的收入表中
                ContentValues values = new ContentValues();
                values.put("inMoney", et_money.getText().toString());
                values.put("inTime", et_time.getText().toString());
                values.put("inType", sp_type.getSelectedItem().toString());
                values.put("inPayer", et_payer.getText().toString());
                values.put("inRemark", et_remark.getText().toString());
                db.insert("in_come",null,values);
                Toast.makeText(NewIncomeActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                // 刷新本页面
                Intent intent = new Intent(NewIncomeActivity.this,NewIncomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    // 取消按钮功能的实现---------------------
    private void btnCancel() {
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewIncomeActivity.this, EconomicsActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}