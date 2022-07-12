package com.example.studentassistant.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentassistant.R;
import com.example.studentassistant.db.MyDBHelper;

public class LoginActivity extends AppCompatActivity {

    // 定义对象
    EditText et_name, et_pwd;
    Button btn_newRegister, btn_login;
    MyDBHelper mHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 绑定控件
        initView();

        // 登录按钮功能的实现
        btnLogin();

        // 新用户注册按钮功能的实现
        btnNewRegister();
    }

    // 绑定控件-------------
    private void initView() {
        et_name = findViewById(R.id.et_name_lg);
        et_pwd = findViewById(R.id.et_pwd_lg);
        btn_login = findViewById(R.id.bt_login_lg);
        btn_newRegister = findViewById(R.id.bt_newRegister_lg);
        mHelper = new MyDBHelper(LoginActivity.this);
        db = mHelper.getWritableDatabase();  // 获得数据库的操作权限
    }


    // 登录按钮功能的实现
    private void btnLogin() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1. 获取输入的用户名和密码
                String inputName = et_name.getText().toString();
                String inputPwd = et_pwd.getText().toString();
                // 对获取的用户名和密码进行判断，为空弹出提示
                if (inputName.equals("") || inputPwd.equals("")) {
                    Toast.makeText(LoginActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                } else { // 不为空时，进行判断
                    // 根据输入的用户名和密码从数据库中进行查询
                    // 该方法接收两个参数，第一个参数为sql语句，第二个参数条件的参数
                    Cursor cursor = db.rawQuery("select * from tb_userinfo where name=? and pwd=?", new String[]{inputName,inputPwd});
                    // 根据查询到的结果进行判断
                    if (cursor.moveToNext()) { // 查询到时，输入正确
                        @SuppressLint("Range") String getName = cursor.getString(cursor.getColumnIndex("name"));
                        @SuppressLint("Range") String getPwd = cursor.getString(cursor.getColumnIndex("pwd"));
                        // 不区分大小写
                        if (inputName.equalsIgnoreCase(getName) && inputPwd.equalsIgnoreCase(getPwd)) {

                            // 将用户名和密码保存到键值对(xml文件)中，userinfo为文件名
                            SharedPreferences.Editor editor = getSharedPreferences("userinfo",0).edit();
                            editor.putString("username",inputName);
                            editor.putString("userPwd",inputPwd);
                            editor.commit();

                            Toast.makeText(LoginActivity.this, "用户名和密码正确，欢迎登陆", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    } else { // 输入错误
                        Toast.makeText(LoginActivity.this, "用户名或密码错误，请重新输入", Toast.LENGTH_SHORT).show();
                        et_name.setText("");
                        et_pwd.setText("");
                    }
                }
            }
        });
    }

    // 新用户注册按钮功能的实现
    private void btnNewRegister() {
        btn_newRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}