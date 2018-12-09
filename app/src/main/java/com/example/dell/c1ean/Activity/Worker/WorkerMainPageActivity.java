package com.example.dell.c1ean.Activity.Worker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.c1ean.Fragment.PersonalPageFragment;
import com.example.dell.c1ean.Fragment.Worker.WorkerHomePageFragment;
import com.example.dell.c1ean.Fragment.Worker.WorkerOrdersFragment;
import com.example.dell.c1ean.R;
import com.ycl.tabview.library.TabView;
import com.ycl.tabview.library.TabViewChild;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2018/12/2.
 */

public class WorkerMainPageActivity extends AppCompatActivity{

    public static String user_id = "USER_ID";
    private TabView tabView;
    private WorkerHomePageFragment workerHomePageFragment;
    private WorkerOrdersFragment workerOrdersFragment;
    private PersonalPageFragment personalPageFragment;
    private Long USERID;
    private LocalBroadcastManager localBroadcastManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //给app内所有界面发送用户id的广播
        USERID = getIntent().getLongExtra("user_id",0);
        Intent intent = new Intent(user_id);
        intent.putExtra("user_id",USERID);
        localBroadcastManager.sendBroadcast(intent);

        setContentView(R.layout.worker_mainpage);

        initView();
    }

    private void initView(){

        tabView = findViewById(R.id.tabView);

        workerHomePageFragment = new WorkerHomePageFragment();
        workerOrdersFragment = new WorkerOrdersFragment();
        personalPageFragment = new PersonalPageFragment();

        TabViewChild tabViewChild0 = new TabViewChild(R.mipmap.home,R.mipmap.home_noselect,"首页",workerHomePageFragment);
        TabViewChild tabViewChild1 = new TabViewChild(R.mipmap.orders,R.mipmap.orders_noselect,"订单",workerOrdersFragment);
        TabViewChild tabViewChild2 = new TabViewChild(R.mipmap.user,R.mipmap.user_noselect,"我的",personalPageFragment);

        List<TabViewChild> tabViewChildList = new ArrayList<>();
        tabViewChildList.add(tabViewChild0);
        tabViewChildList.add(tabViewChild1);
        tabViewChildList.add(tabViewChild2);

        tabView.setTabViewChild(tabViewChildList, getSupportFragmentManager());
        tabView.setTextViewSelectedColor(Color.parseColor("#4cbcfa"));  //设置获得焦点文字颜色
        tabView.setTabViewDefaultPosition(0);      //设置启动页面
        tabView.setImageViewHeight(100);         //设置图片高度
        tabView.setTextViewSize(10);
        tabView.setTabViewChild(tabViewChildList, getSupportFragmentManager());
        tabView.setOnTabChildClickListener(new TabView.OnTabChildClickListener() {
            @Override
            public void onTabChildClick(int position, ImageView currentImageIcon, TextView currentTextView) {  //绑定监听器

            }
        });
    }

}

