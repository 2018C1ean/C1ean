package com.example.dell.c1ean.Activity.User;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.c1ean.Fragment.User.UserActivityFragment;
import com.example.dell.c1ean.Fragment.User.UserHomePageFragment;
import com.example.dell.c1ean.Fragment.User.UserOrdersFragment;
import com.example.dell.c1ean.Fragment.PersonalPageFragment;
import com.example.dell.c1ean.R;
import com.ycl.tabview.library.TabView;
import com.ycl.tabview.library.TabViewChild;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eskii on 2018/11/30.
 * 首页
 */

public class UserMainPageActivity extends AppCompatActivity {

    private TabView tabView;
    private UserHomePageFragment userHomePageFragment;
    private UserOrdersFragment userOrdersFragment;
    private UserActivityFragment userActivityFragment;
    private PersonalPageFragment personalPageFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_mainpage);

        initView();

    }

    private void initView(){

        tabView = findViewById(R.id.tabView);

        userHomePageFragment = new UserHomePageFragment();
        userOrdersFragment = new UserOrdersFragment();
        userActivityFragment = new UserActivityFragment();
        personalPageFragment = new PersonalPageFragment();

        TabViewChild tabViewChild0 = new TabViewChild(R.mipmap.home,R.mipmap.home_noselect,"首页",userHomePageFragment);
        TabViewChild tabViewChild1 = new TabViewChild(R.mipmap.activity,R.mipmap.activity_noselect,"活动",userActivityFragment);
        TabViewChild tabViewChild2 = new TabViewChild(R.mipmap.orders,R.mipmap.orders_noselect,"订单",userOrdersFragment);
        TabViewChild tabViewChild3 = new TabViewChild(R.mipmap.user,R.mipmap.user_noselect,"我的", personalPageFragment);

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

    @Override
    public void onAttachFragment(Fragment fragment) {
        if (userHomePageFragment == null){
            userHomePageFragment = new UserHomePageFragment();
        }else if (userActivityFragment == null){
            userActivityFragment = new UserActivityFragment();
        }else if (userOrdersFragment == null){
            userOrdersFragment = new UserOrdersFragment();
        }else if (personalPageFragment == null){
            personalPageFragment = new PersonalPageFragment();
        }
    }
}
