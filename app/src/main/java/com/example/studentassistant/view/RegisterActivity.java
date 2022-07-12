package com.example.studentassistant.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentassistant.R;
import com.example.studentassistant.db.MyDBHelper;

public class RegisterActivity extends AppCompatActivity {

    // 3.定义对象
    EditText et_name, et_pwd, et_email, et_phone;
    Button btn_register, btn_cancel;
    MyDBHelper mHelper; // 创建一个数据库类文件
    SQLiteDatabase db; // 创建一个可以操作的数据库对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // 4.绑定控件
        initView();

        // 5. 注册按钮功能的实现
        btnRegister();

        // 6.取消按钮功能的实现
        btnCancel();
    }



    private void initView() {
        et_name = findViewById(R.id.et_name_rg);
        et_pwd = findViewById(R.id.et_pwd_rg);
        et_email = findViewById(R.id.et_email_rg);
        et_phone = findViewById(R.id.et_phone_rg);
        btn_register = findViewById(R.id.bt_ok_rg);
        btn_cancel = findViewById(R.id.bt_cancel_rg);
        mHelper = new MyDBHelper(RegisterActivity.this);
        db = mHelper.getWritableDatabase();
    }

    // 5.注册按钮功能的实现----------------------
    private void btnRegister() {
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 创建一个对象，用来封装一行数据
                ContentValues values = new ContentValues();
                values.put("name",et_name.getText().toString());
                values.put("pwd",et_name.getText().toString());
                values.put("email",et_email.getText().toString());
                values.put("phone",et_phone.getText().toString());

                // 将封装好的一行数据保存到数据库的tb_userinfo表中。
                // insert()方法的三个参数
                // insert("tb_userinfo",null, values)
                /*
                 * 1. 第一个参数是表名;
                 * 2. 第二个参数是某些为空的列自动赋值null;
                 * 3. 第三个参数是ContentValue对象，它提供了一系列put()方法重载，用于向ContentValues中添加对象，只需要将表中的每个列名以及相应的待添加的数据传入即可
                 * */
                db.insert("tb_userinfo",null,values);
                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 6.取消按钮功能的实现----------------------
    private void btnCancel() {
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}