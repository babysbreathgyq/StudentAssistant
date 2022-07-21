package com.example.studentassistant.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentassistant.R;
import com.example.studentassistant.adapter.ToDoListAdapter;
import com.example.studentassistant.bean.IncomeBean;
import com.example.studentassistant.bean.ToDoListBean;
import com.example.studentassistant.db.MyDBHelper;

import java.util.ArrayList;
import java.util.List;

public class toDoListActivity extends AppCompatActivity {

    // 1.定义对象
    ListView list_doing, list_completed;
    EditText et_add;
    CheckBox cb;
    MyDBHelper mHelper;
    SQLiteDatabase db;
    private TextView.OnEditorActionListener EnterListenter;
    private List<ToDoListBean> arr = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        // 2.绑定控件
        initView();

        // 3.准备数据
        // 先进行初始化数据
        initData();

        // 点击回车之后保存数据
        saveData();

        // 4.适配器
        ToDoListAdapter adapter = new ToDoListAdapter(toDoListActivity.this,R.layout.todolist_item,arr);
        list_doing.setAdapter(adapter);

        // 设置ListView的点击事件

    }

    private void initView() {
        list_doing = findViewById(R.id.doing_todolist);
        list_completed = findViewById(R.id.completed_todolist);
        et_add = findViewById(R.id.et_add_todolist);
        mHelper = new MyDBHelper(toDoListActivity.this);
        db = mHelper.getWritableDatabase(); // 调用数据库的写方法可以得到一个可供操作的数据库
    }

    private void saveData() {
        et_add.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN)
                    if ((i == KeyEvent.KEYCODE_DPAD_CENTER) || (i == KeyEvent.KEYCODE_ENTER)) {
                        // 获取输入的内容保存到数据库的待办事项表中
                        ContentValues values = new ContentValues();
                        values.put("textData",et_add.getText().toString());
                        db.insert("todolist",null,values);
                        Toast.makeText(toDoListActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                        et_add.setText("");
                        Intent intent = new Intent(toDoListActivity.this,toDoListActivity.class);
                        startActivity(intent);
                        return true;
                    }
                return false;
            }
        });
    }
    @SuppressLint("Range")
    private void initData() {
        // 从数据库查询所有的新增收入信息
        Cursor cursor = db.rawQuery("select * from todolist",null);
        // 采用循环的方式读数据，存数据
        while (cursor.moveToNext()) {
            // 取出数据
            String text = cursor.getString(cursor.getColumnIndex("textData"));
            // 将每条数据封装成一个对象放入数组中
            ToDoListBean toDoListBean = new ToDoListBean(text,cb);
            arr.add(toDoListBean);
        }
    }
}