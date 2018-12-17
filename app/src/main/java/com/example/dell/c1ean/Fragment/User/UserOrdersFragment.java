package com.example.dell.c1ean.Fragment.User;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.dell.c1ean.R;

/**
 * Created by DELL on 2018/12/4.
 */

public class UserOrdersFragment extends Fragment implements View.OnClickListener{

    private AllOrderFragment allOrderFragment;
    private WaitingPayFragment waitingPayFragment;
    private WaitingEvaluationFragment waitingEvaluationFragment;
    private TextView all_order,wait_pay,wait_evaluate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_order_page,container,false);

        all_order = view.findViewById(R.id.all_order);
        wait_pay = view.findViewById(R.id.wait_pay);
        wait_evaluate = view.findViewById(R.id.wait_evaluate);

        all_order.setOnClickListener(this);
        wait_pay.setOnClickListener(this);
        wait_evaluate.setOnClickListener(this);
        //第一次初始化首页默认显示第一个fragment
        initFragment1();

        return view;
    }

    //显示第一个fragment
    private void initFragment1(){
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

        //第一种方式（add），初始化fragment并添加到事务中，如果为null就new一个
        if(allOrderFragment == null){
            allOrderFragment = new AllOrderFragment();
            transaction.add(R.id.order_page, allOrderFragment);
        }
        //隐藏所有fragment
        hideFragment(transaction);
        //显示需要显示的fragment
        transaction.show(allOrderFragment);

        //第二种方式(replace)，初始化fragment
//        if(f1 == null){
//            f1 = new MyFragment("消息");
//        }
//        transaction.replace(R.id.main_frame_layout, f1);

        //提交事务
        transaction.commit();
    }

    //显示第二个fragment
    private void initFragment2(){
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

        if(waitingPayFragment == null){
            waitingPayFragment = new WaitingPayFragment();
            transaction.add(R.id.order_page,waitingPayFragment);
        }
        hideFragment(transaction);
        transaction.show(waitingPayFragment);

//        if(f2 == null) {
//            f2 = new MyFragment("联系人");
//        }
//        transaction.replace(R.id.main_frame_layout, f2);

        transaction.commit();
    }

    //显示第三个fragment
    private void initFragment3(){
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

        if(waitingEvaluationFragment == null){
            waitingEvaluationFragment = new WaitingEvaluationFragment();
            transaction.add(R.id.order_page,waitingEvaluationFragment);
        }
        hideFragment(transaction);
        transaction.show(waitingEvaluationFragment);

//        if(f3 == null) {
//            f3 = new MyFragment("动态");
//        }
//        transaction.replace(R.id.main_frame_layout, f3);

        transaction.commit();
    }

    //隐藏所有的fragment
    private void hideFragment(FragmentTransaction transaction){
        if(allOrderFragment != null){
            transaction.hide(allOrderFragment);
        }
        if(waitingPayFragment != null){
            transaction.hide(waitingPayFragment);
        }
        if(waitingEvaluationFragment != null){
            transaction.hide(waitingEvaluationFragment);
        }
    }


    @Override
    public void onClick(View v) {
        if (v == all_order) {
            initFragment1();
        } else if (v == wait_pay) {
            initFragment2();
        } else if (v == wait_evaluate) {
            initFragment3();
        }

    }
}
