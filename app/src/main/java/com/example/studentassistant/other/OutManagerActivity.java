package com.example.studentassistant.other;

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
import com.example.studentassistant.activity.IncomeDetailActivity;
import com.example.studentassistant.activity.NewPayActivity;
import com.example.studentassistant.activity.PayDetailActivity;
import com.example.studentassistant.bean.IncomeBean;
import com.example.studentassistant.bean.OutPayBean;
import com.example.studentassistant.db.MyDBHelper;

public class OutManagerActivity extends AppCompatActivity {

    // 定义对象
    private EditText et_money, et_time, et_payer, et_remark;
    private Spinner sp_type;
    private Button bt_modify, bt_delete;

    private MyDBHelper myDBHelper;
    private SQLiteDatabase db;
    private OutPayBean outPayBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_manager);

        // 绑定控件
        initView();

        // 3. 获取单击的那条数据并显示出来
        getDataDisplay();

        // 4. 修改按钮功能的实现
        btnModify();

        // 5. 删除按钮功能的实现
        btnDelete();
    }

    // 绑定控件-----------------
    private void initView() {
        et_money = findViewById(R.id.et_money_outMag);
        et_time = findViewById(R.id.et_time_outMag);
        et_payer = findViewById(R.id.et_payer_outMag);
        et_remark = findViewById(R.id.et_remark_outMag);
        sp_type = findViewById(R.id.sp_type_outMag);
        bt_modify = findViewById(R.id.bt_modify_outMag);
        bt_delete = findViewById(R.id.bt_delete_outMag);
        myDBHelper = new MyDBHelper(OutManagerActivity.this);
        db = myDBHelper.getWritableDatabase();
    }

    private void getDataDisplay() {
        outPayBean = (OutPayBean) getIntent().getSerializableExtra("sero");
        // 将取出来的数据显示到界面上
        et_money.setText(outPayBean.getMoney() + ""); // money是double类型
        et_time.setText(outPayBean.getTime());
        switch (outPayBean.getType()) {
            case "学习-书籍":
                sp_type.setSelection(1);
                break;
            case "学习-培训":
                sp_type.setSelection(2);
                break;
            case "日常-美食":
                sp_type.setSelection(3);
                break;
            case "食品-水果":
                sp_type.setSelection(4);
                break;
            case "食品-零食":
                sp_type.setSelection(5);
                break;
            case "交通-出行":
                sp_type.setSelection(6);
                break;
            case "娱乐-购物":
                sp_type.setSelection(7);
                break;
            case "娱乐-电影":
                sp_type.setSelection(8);
                break;
            case "其他":
                sp_type.setSelection(9);
                break;
        }
        et_payer.setText(outPayBean.getPayee());
        et_remark.setText(outPayBean.getRemark());
    }

    // 4.修改按钮功能的实现---------------------
    private void btnModify() {
        bt_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 创建一个对象，封装一行数据
                ContentValues values = new ContentValues();
                values.put("outMoney",et_money.getText().toString());
                values.put("outTime",et_time.getText().toString());
                values.put("outType",sp_type.getSelectedItem().toString());
                values.put("outPayee",et_payer.getText().toString());
                values.put("outRemark",et_remark.getText().toString());
                // 把该行数据更新到收入表中
                db.update("pay_out",values, "id=?", new String[]{outPayBean.getId()+""});
                Toast.makeText(OutManagerActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                // 关闭本页面，重新打开收入明细界面，即可查询修改后的结果
                // 创建Intent对象
                Intent intent = new Intent(OutManagerActivity.this, PayDetailActivity.class);
                startActivity(intent); // 执行intent操作
                finish(); // 退出当前程序，或关闭当前页面
            }
        });
    }

    // 5. 删除按钮功能的实现------------------------
    private void btnDelete() {
        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 从数据库中删除该条记录即可
                db.delete("pay_out", "id=?", new String[]{outPayBean.getId()+""});
                Toast.makeText(OutManagerActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
                // 关闭本页面，重新打开收入明细界面，即可查询删除后的结果
                // 创建Intent对象
                Intent intent = new Intent(OutManagerActivity.this, PayDetailActivity.class);
                startActivity(intent); // 执行intent操作
                finish(); // 退出当前程序，或关闭当前页面
            }
        });
    }
}