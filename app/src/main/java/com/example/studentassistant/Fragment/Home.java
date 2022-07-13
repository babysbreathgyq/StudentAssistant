package com.example.studentassistant.Fragment;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.studentassistant.R;
import com.example.studentassistant.activity.ContactActivity;
import com.example.studentassistant.activity.DietActivity;
import com.example.studentassistant.activity.EconomicsActivity;
import com.example.studentassistant.activity.ScheduleActivity;
import com.example.studentassistant.activity.SignInActivity;
import com.example.studentassistant.activity.toDoListActivity;

public class Home extends Fragment {

    private HomeViewModel mViewModel;

    // 1.定义对象
    Button bt_signIn, bt_toDoList, bt_InOut, bt_contact, bt_diet, bt_schedule;

    public static Home newInstance() {
        return new Home();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        /* 每日签到按钮的跳转 */
        bt_signIn = getActivity().findViewById(R.id.bt_signIn_main);
        bt_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SignInActivity.class);
                startActivity(intent);
            }
        });

        /* 待办事项按钮的跳转 */
        bt_toDoList = getActivity().findViewById(R.id.bt_toDoList_main);
        bt_toDoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), toDoListActivity.class);
                startActivity(intent);
            }
        });

        /* 日常收支按钮的跳转 */
        bt_InOut = getActivity().findViewById(R.id.bt_InOut_main);
        bt_InOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EconomicsActivity.class);
                startActivity(intent);
            }
        });

        /* 社交管理按钮的跳转 */
        bt_contact = getActivity().findViewById(R.id.bt_contact_main);
        bt_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ContactActivity.class);
                startActivity(intent);
            }
        });

        /* 饮食管理按钮的跳转 */
        bt_diet = getActivity().findViewById(R.id.bt_diet_main);
        bt_diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DietActivity.class);
                startActivity(intent);
            }
        });

        /* 日程安排按钮的跳转 */
        bt_schedule = getActivity().findViewById(R.id.bt_schedule_main);
        bt_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ScheduleActivity.class);
                startActivity(intent);
            }
        });
    }
}