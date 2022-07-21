package com.example.studentassistant.bean;

import android.widget.CheckBox;

public class ToDoListBean {
    // toDoListBean中有两个字段，一个是文本name，一个是复选框
    private String name;
    private CheckBox cb;

    public ToDoListBean(String name, CheckBox cb) {
        this.name = name;
        this.cb = cb;
    }

    public String getName() {
        return name;
    }

    public CheckBox getCb() {
        return cb;
    }
}
