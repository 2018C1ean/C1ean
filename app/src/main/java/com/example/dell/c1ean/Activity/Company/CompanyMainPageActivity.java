package com.example.dell.c1ean.Activity.Company;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.c1ean.Application.BaseApplication;
import com.example.dell.c1ean.Application.SystemApplication;
import com.example.dell.c1ean.Fragment.Company.CompanyHomePageFragment;
import com.example.dell.c1ean.Fragment.Company.CompanyOrdersFragment;
import com.example.dell.c1ean.Fragment.Company.CompanyEventsFragment;
import com.example.dell.c1ean.Fragment.PersonalPageFragment;
import com.example.dell.c1ean.R;
import com.gyf.barlibrary.ImmersionBar;
import com.wuhenzhizao.titlebar.statusbar.StatusBarUtils;
import com.ycl.tabview.library.TabView;
import com.ycl.tabview.library.TabViewChild;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2018/12/2.
 */

public class CompanyMainPageActivity extends AppCompatActivity {

    private long exitTime;
    private TabView tabView;
    private CompanyHomePageFragment companyHomePageFragment;
    private CompanyEventsFragment companyEventsFragment;
    private CompanyOrdersFragment companyOrdersFragment;
    private PersonalPageFragment personalPageFragment;
    private Long user_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SystemApplication.getInstance().addActivity(this);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);    //设置全屏
        user_id = ((BaseApplication)getApplication()).getUSER_ID();
        //设置状态栏为白色
        StatusBarUtils.setStatusBarColor(getWindow(),getResources().getColor(R.color.colorWhite),1);
        ImmersionBar.with(this).init();
        setContentView(R.layout.mainpage);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }

    /**
     * 重写返回键，实现双击退出效果
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(CompanyMainPageActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                SystemApplication.getInstance().exit();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}