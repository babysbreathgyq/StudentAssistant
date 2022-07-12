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

public class NewPayActivity extends AppCompatActivity {

    // 定义对象
    EditText et_money, et_time, et_payee, et_remark;
    Spinner sp_type;
    Button bt_save, bt_cancel;

    MyDBHelper myDBHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pay);

        // 绑定控件
        initView();

        // 保存按钮功能的实现
        btnSave();

        // 取消按钮功能的实现
        btnCancel();
    }

    // 绑定控件-----------------
    private void initView() {
        et_money = findViewById(R.id.et_money_newOut);
        et_time = findViewById(R.id.et_time_newOut);
        et_payee = findViewById(R.id.et_payee_newOut);
        et_remark = findViewById(R.id.et_remark_newOut);
        sp_type = findViewById(R.id.sp_type_newOut);
        bt_save = findViewById(R.id.bt_save_newOut);
        bt_cancel = findViewById(R.id.bt_cancel_newOut);
        myDBHelper = new MyDBHelper(NewPayActivity.this);
        db = myDBHelper.getWritableDatabase();
    }

    // 保存按钮功能的实现------------
    private void btnSave() {
        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 获取输入的内容保存到数据库的收入表中
                ContentValues values = new ContentValues();
                values.put("outMoney", et_money.getText().toString());
                values.put("outTime", et_time.getText().toString());
                values.put("outType", sp_type.getSelectedItem().toString());
                values.put("outPayee", et_payee.getText().toString());
                values.put("outRemark", et_remark.getText().toString());
                db.insert("pay_out",null,values);
                Toast.makeText(NewPayActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                // 刷新本页面
                Intent intent = new Intent(NewPayActivity.this,NewPayActivity.class);
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
                Intent intent = new Intent(NewPayActivity.this, EconomicsActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}