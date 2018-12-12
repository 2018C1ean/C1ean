package com.example.dell.c1ean.Fragment.User;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.c1ean.Adapter.RecyclerViewStaggeredAdapter;
import com.example.dell.c1ean.Application.BaseApplication;
import com.example.dell.c1ean.Bean.CompanyActivity;
import com.example.dell.c1ean.DAO.CompanyActivityDao;
import com.example.dell.c1ean.DAO.CompanyDao;
import com.example.dell.c1ean.DAO.OrderDao;
import com.example.dell.c1ean.R;
import com.example.dell.c1ean.Util.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李雯晴 on 2018/12/4.
 */

public class UserHomePageFragment extends Fragment {

    private Banner banner;  //轮播图控件
    private List<String> images = new ArrayList<>();    //轮播图图片路径队列
    private List<String> titles = new ArrayList<>();    //轮播图图片对应的标题
    private CompanyActivityDao companyActivityDao;
    private OrderDao orderDao;
    private CompanyDao companyDao;
    private Long user_id;   //当前用户id
    private ImageView zybj, jdqx, jjyh, xhfw;
    private RecyclerView recyclerView;
    private RecyclerViewStaggeredAdapter recyclerViewStaggeredAdapter;
    private List<CompanyActivity> companyActivityList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_homepage, container, false);

        companyActivityDao = ((BaseApplication) getActivity().getApplication()).getCompanyActivityDao();
        orderDao = ((BaseApplication) getActivity().getApplication()).getOrderDao();
        companyDao = ((BaseApplication) getActivity().getApplication()).getCompanyDao();
        user_id = ((BaseApplication) getActivity().getApplication()).getUSER_ID();

        zybj = view.findViewById(R.id.type1);
        jdqx = view.findViewById(R.id.type2);
        jjyh = view.findViewById(R.id.type3);
        xhfw = view.findViewById(R.id.type4);
        recyclerView = view.findViewById(R.id.recyclerView);
        banner = view.findViewById(R.id.banner);
        setData();  //设置轮播图数据
        setBanner();    //初始化轮播图控件
        setView();
        return view;
    }

    private void setView() {
        zybj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        jdqx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        jjyh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        xhfw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        recyclerViewStaggeredAdapter = new RecyclerViewStaggeredAdapter(getContext(), companyActivityList, companyDao, orderDao);
        recyclerView.setAdapter(recyclerViewStaggeredAdapter);
        recyclerView.setNestedScrollingEnabled(false);  //设置为不可滚动
        //布局管理器
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setReverseLayout(false);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

    }

    /**
     * 设置轮播图控件参数
     */
    private void setBanner() {

        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    /**
     * 设置用户首页轮播图的数据
     * 先从用户下的订单中得出用户使用最频繁的服务
     * 如果用户没有下过任何一个类型的订单，则轮播任意类型最新发出的五个活动，
     * 否则轮播用户使用最频繁的服务类型的最新发出的五个活动
     */
    private void setData() {

        String favourite = getFavourite();  //获取用户最喜爱的服务类型

        if (favourite != null) {
            companyActivityList = companyActivityDao.queryBuilder().where(CompanyActivityDao.Properties.Type.eq(favourite))
                    .orderDesc(CompanyActivityDao.Properties.Company_id).limit(5).list();

        } else {
            companyActivityList = companyActivityDao.queryBuilder().orderDesc(CompanyActivityDao.Properties.Company_id).limit(5).list();
        }
        for (int i = 0; i < companyActivityList.size(); i++) {
            images.add(companyActivityList.get(i).getImg());    //放入服务的照片
            titles.add(companyActivityList.get(i).getTitle());    //放入服务的描述
        }
    }

    //"专业保洁","家电清洗","家居养护","洗护服务"
    private String getFavourite() {

        //查找该用户在每种类型下下订单的数量
        Long zyby = orderDao.queryBuilder().where(OrderDao.Properties.User_id.eq(user_id), OrderDao.Properties.Type.eq("专业保洁")).count();
        Long jdqx = orderDao.queryBuilder().where(OrderDao.Properties.User_id.eq(user_id), OrderDao.Properties.Type.eq("家电清洗")).count();
        Long jjyh = orderDao.queryBuilder().where(OrderDao.Properties.User_id.eq(user_id), OrderDao.Properties.Type.eq("家居养护")).count();
        Long xhfw = orderDao.queryBuilder().where(OrderDao.Properties.User_id.eq(user_id), OrderDao.Properties.Type.eq("洗护服务")).count();

        Long n[] = {zyby, jdqx, jjyh, xhfw};
        Long max = zyby;
        //获取数量最多的
        for (int i = 1; i < n.length; i++) {
            if (max < n[i]) {
                max = n[i];
            }
        }

        //根据数量返回相应的服务类型
        if (max != 0) {
            if (max.equals(zyby)) {
                return "专业保洁";
            } else if (max.equals(jdqx)) {
                return "家电清洗";
            } else if (max.equals(jjyh)) {
                return "家居养护";
            } else {
                return "洗护服务";
            }
        } else {
            return null;
        }
    }
}
