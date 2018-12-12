package com.example.dell.c1ean.Fragment.User;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.c1ean.Adapter.RecyclerViewListViewAdapter;
import com.example.dell.c1ean.Application.BaseApplication;
import com.example.dell.c1ean.Bean.Order;
import com.example.dell.c1ean.DAO.CompanyActivityDao;
import com.example.dell.c1ean.DAO.CompanyDao;
import com.example.dell.c1ean.DAO.OrderDao;
import com.example.dell.c1ean.R;

import java.util.List;

/**
 * Created by 李雯晴 on 2018/12/11.
 */

public class WaitingPayFragment extends Fragment{

    private RecyclerView recyclerView;
    private RecyclerViewListViewAdapter listViewAdapter;
    private List<Order> orderList;
    private CompanyActivityDao companyActivityDao;
    private CompanyDao companyDao;
    private OrderDao orderDao;
    private Long user_id;
    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.user_order_list,container,false);

        recyclerView = view.findViewById(R.id.recyclerView);
        textView = view.findViewById(R.id.valuable);
        user_id = ((BaseApplication) getActivity().getApplication()).getUSER_ID();
        companyActivityDao = ((BaseApplication) getActivity().getApplication()).getCompanyActivityDao();
        companyDao = ((BaseApplication) getActivity().getApplication()).getCompanyDao();
        orderDao = ((BaseApplication) getActivity().getApplication()).getOrderDao();

        setData();
        initView();

        return view;
    }

    private void setData(){

        orderList = orderDao.queryBuilder().where(OrderDao.Properties.User_id.eq(user_id),OrderDao.Properties.State.eq(0)).list();
    }

    private void initView(){
        if (orderList.size() >= 1) {
            textView.setVisibility(View.INVISIBLE);
            listViewAdapter = new RecyclerViewListViewAdapter(getContext(), orderList, companyDao, companyActivityDao);
            recyclerView.setAdapter(listViewAdapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(linearLayoutManager);
        }
    }
}
