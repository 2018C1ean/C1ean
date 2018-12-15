package com.example.dell.c1ean.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.c1ean.Activity.User.UserMainPageActivity;
import com.example.dell.c1ean.Application.BaseApplication;
import com.example.dell.c1ean.Bean.CompanyActivity;
import com.example.dell.c1ean.DAO.CompanyActivityDao;
import com.example.dell.c1ean.DAO.CompanyDao;
import com.example.dell.c1ean.DAO.OrderDao;
import com.example.dell.c1ean.DAO.UserDao;
import com.example.dell.c1ean.R;
import com.example.dell.c1ean.Util.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2018/12/14.
 */

public class ActivityDetailPageActivity extends AppCompatActivity{

    private CompanyActivityDao companyActivityDao;
    private OrderDao orderDao;
    private CompanyDao companyDao;
    private UserDao userDao;
    private long exitTime;
    private int activity_id;
    private Banner banner;
    private TextView activity_price,activity_type,good_rate,sale_count,order_user_name,evaluation,company_reference,activity_describe;
    private RelativeLayout evaluation_layout;
    private RatingBar evaluation_stars;
    private List<String> images = new ArrayList<>();    //轮播图图片路径队列
    private CompanyActivity companyActivity;    //当前界面所展示的活动

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);

        activity_id = getIntent().getIntExtra("activity_item_id",0);

        userDao = ((BaseApplication) getApplication()).getUserDao();
        companyActivityDao = ((BaseApplication) getApplication()).getCompanyActivityDao();
        orderDao = ((BaseApplication) getApplication()).getOrderDao();
        companyDao = ((BaseApplication) getApplication()).getCompanyDao();

        companyActivity = companyActivityDao.queryBuilder().where(CompanyActivityDao.Properties.Id.eq(activity_id)).unique();

        initView();
    }

    private void initView(){

        banner = findViewById(R.id.activity_imgs);
        activity_price = findViewById(R.id.activity_price);
        activity_type = findViewById(R.id.activity_type);
        good_rate = findViewById(R.id.good_rate);
        sale_count = findViewById(R.id.sale_count);
        order_user_name = findViewById(R.id.order_user_name);
        evaluation = findViewById(R.id.evaluation_text);
        evaluation_stars = findViewById(R.id.evaluation_stars);
        company_reference = findViewById(R.id.company_reference);
        activity_describe = findViewById(R.id.activity_describe);

        setData();

        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        banner.isAutoPlay(false);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

    }

    private void setData(){

        //加载图片数据
        images.add(companyActivityDao.queryBuilder().where(CompanyActivityDao.Properties.Id.eq(activity_id)).unique().getImg1());
        if (companyActivityDao.queryBuilder().where(CompanyActivityDao.Properties.Id.eq(activity_id)).unique().getImg2() != null){
            images.add(companyActivityDao.queryBuilder().where(CompanyActivityDao.Properties.Id.eq(activity_id)).unique().getImg2());
        }
        if (companyActivityDao.queryBuilder().where(CompanyActivityDao.Properties.Id.eq(activity_id)).unique().getImg3() != null){
            images.add(companyActivityDao.queryBuilder().where(CompanyActivityDao.Properties.Id.eq(activity_id)).unique().getImg3());
        }

    }
}
