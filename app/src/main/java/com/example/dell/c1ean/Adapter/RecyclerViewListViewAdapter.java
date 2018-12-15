package com.example.dell.c1ean.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.c1ean.Bean.CompanyActivity;
import com.example.dell.c1ean.Bean.Order;
import com.example.dell.c1ean.DAO.CompanyActivityDao;
import com.example.dell.c1ean.DAO.CompanyDao;
import com.example.dell.c1ean.DAO.OrderDao;
import com.example.dell.c1ean.R;

import java.util.List;

/**
 * Created by DELL on 2018/12/11.
 */

public class RecyclerViewListViewAdapter extends RecyclerView.Adapter<RecyclerViewListViewAdapter.ViewHolder> {

    private Context context;
    private List<Order> orderList;
    private CompanyDao companyDao;
    private CompanyActivityDao companyActivityDao;

    public RecyclerViewListViewAdapter(Context context,List<Order> orderList,CompanyDao companyDao,CompanyActivityDao companyActivityDao) {

        this.context = context;
        this.orderList = orderList;
        this.companyDao = companyDao;
        this.companyActivityDao = companyActivityDao;
    }

    @NonNull
    @Override
    public RecyclerViewListViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = View.inflate(context,R.layout.order_list_item,null);
        RecyclerViewListViewAdapter.ViewHolder viewHolder = new RecyclerViewListViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewListViewAdapter.ViewHolder holder, int position) {

        Order order = orderList.get(position);
        holder.setData(order);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView company_name,order_state,activity_describe,booking_time,order_price;
        private ImageView order_img;
        private TextView delete_order,evaluation_order,cancel_order;

        public ViewHolder(View itemView) {
            super(itemView);
            company_name = itemView.findViewById(R.id.company_name);
            order_state = itemView.findViewById(R.id.order_state);
            activity_describe = itemView.findViewById(R.id.activity_describe);
            booking_time = itemView.findViewById(R.id.booking_time);
            order_price = itemView.findViewById(R.id.order_price);
            order_img = itemView.findViewById(R.id.order_img);
            delete_order = itemView.findViewById(R.id.delete_order);
            evaluation_order = itemView.findViewById(R.id.evaluation_order);
            cancel_order = itemView.findViewById(R.id.cancel_order);
        }

        public void setData(Order order){

            int state = order.getState();
            company_name.setText(companyDao.queryBuilder().where(CompanyDao.Properties.Company_id.eq(order.getCompany_id())).unique().getCompany_name());
            order_state.setText(order.getState());
            activity_describe.setText(companyActivityDao.queryBuilder().where(CompanyActivityDao.Properties.Id.eq(order.getActivity_id())).unique().getTitle());
            booking_time.setText(order.getBookingTime());
            order_price.setText(order.getMoney()+"");
            Bitmap bitmap = BitmapFactory.decodeFile(companyActivityDao.queryBuilder().where(CompanyActivityDao.Properties.Id.eq(order.getActivity_id())).unique().getImg1());
            order_img.setImageBitmap(bitmap);

            //订单已完成，显示“删除订单”和“评价订单”
            if (state == 3){

                delete_order.setVisibility(View.VISIBLE);
                evaluation_order.setVisibility(View.VISIBLE);


            }else if (state == 0 || state == 1){
                //如果订单未付款或已付款（商家未接单），显示“取消订单”
                cancel_order.setVisibility(View.VISIBLE);
            }

            delete_order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            evaluation_order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
