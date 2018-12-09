package com.example.dell.c1ean.Activity.Company;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.c1ean.Application.BaseApplication;
import com.example.dell.c1ean.Fragment.Company.CompanyHomePageFragment;
import com.example.dell.c1ean.Fragment.Company.CompanyOrdersFragment;
import com.example.dell.c1ean.Fragment.Company.CompanyEventsFragment;
import com.example.dell.c1ean.Fragment.PersonalPageFragment;
import com.example.dell.c1ean.R;
import com.ycl.tabview.library.TabView;
import com.ycl.tabview.library.TabViewChild;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2018/12/2.
 */

public class CompanyMainPageActivity extends AppCompatActivity {

    private TabView tabView;
    private CompanyHomePageFragment companyHomePageFragment;
    private CompanyEventsFragment companyEventsFragment;
    private CompanyOrdersFragment companyOrdersFragment;
    private PersonalPageFragment personalPageFragment;
    private Long user_id;
//    private LocalBroadcastManager localBroadcastManager;
//    public static final String USER_ID = "USER_ID";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_mainpage);

        user_id = ((BaseApplication)getApplication()).getUSER_ID();
//        localBroadcastManager = LocalBroadcastManager.getInstance(this);

//        user_id = getIntent().getLongExtra("user_id",0);
//        CompanyEventsFragment companyEventsFragment = new CompanyEventsFragment();
//        Bundle bundle = new Bundle();
//        bundle.putLong("user_id",user_id);
//        companyEventsFragment.setArguments(bundle);
//        localBroadcastManager.sendBroadcast(new Intent(USER_ID).putExtra("user_id",user_id));
        Toast.makeText(CompanyMainPageActivity.this,user_id+"",Toast.LENGTH_LONG).show();
        initView();

    }

    private void initView() {

        tabView = findViewById(R.id.tabView);

        companyHomePageFragment = new CompanyHomePageFragment();
        companyEventsFragment = new CompanyEventsFragment();
        companyOrdersFragment = new CompanyOrdersFragment();
        personalPageFragment = new PersonalPageFragment();

        //参数详解（获得焦点的图标，未获得焦点的图标，图标下的文字，对应的fragment）
        TabViewChild tabViewChild0 = new TabViewChild(R.mipmap.home, R.mipmap.home_noselect, "首页", companyHomePageFragment);
        TabViewChild tabViewChild1 = new TabViewChild(R.mipmap.add, R.mipmap.add_noselect, "活动", companyEventsFragment);
        TabViewChild tabViewChild2 = new TabViewChild(R.mipmap.orders, R.mipmap.orders_noselect, "订单", companyOrdersFragment);
        TabViewChild tabViewChild3 = new TabViewChild(R.mipmap.user, R.mipmap.user_noselect, "我的", personalPageFragment);

        List<TabViewChild> tabViewChildList = new ArrayList<>();
        tabViewChildList.add(tabViewChild0);
        tabViewChildList.add(tabViewChild1);
        tabViewChildList.add(tabViewChild2);
        tabViewChildList.add(tabViewChild3);

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


    //重写该activity的fragment创建方法
    @Override
    public void onAttachFragment(Fragment fragment) {
        if (companyHomePageFragment == null){
            companyHomePageFragment = new CompanyHomePageFragment();
        }else if (companyOrdersFragment == null){
            companyOrdersFragment = new CompanyOrdersFragment();
        }else if (companyEventsFragment == null){
            companyEventsFragment = new CompanyEventsFragment();
        }else if (personalPageFragment == null){
            personalPageFragment = new PersonalPageFragment();
        }
    }
}