package com.example.dell.c1ean.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.c1ean.Application.BaseApplication;
import com.example.dell.c1ean.Bean.Company;
import com.example.dell.c1ean.Bean.CompanyActivity;
import com.example.dell.c1ean.DAO.CompanyDao;
import com.example.dell.c1ean.DAO.OrderDao;
import com.example.dell.c1ean.R;

import java.util.List;

/**
 * Created by 李雯晴 on 2018/12/5.
 */

public class RecyclerViewStaggeredAdapter extends RecyclerView.Adapter<RecyclerViewStaggeredAdapter.StaggerHolder> {

    private Context context;
    private List<CompanyActivity> companyActivityList;
    private CompanyDao companyDao;
    private OrderDao orderDao;

    public RecyclerViewStaggeredAdapter(Context context, List<CompanyActivity> companyActivityList, CompanyDao companyDao, OrderDao orderDao) {
        this.context = context;
        this.companyActivityList = companyActivityList;
        this.companyDao = companyDao;
        this.orderDao = orderDao;
    }

    @NonNull
    @Override
    public RecyclerViewStaggeredAdapter.StaggerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = View.inflate(context,R.layout.activity_item,null);
        RecyclerViewStaggeredAdapter.StaggerHolder staggerHolder = new RecyclerViewStaggeredAdapter.StaggerHolder(view);
        return staggerHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewStaggeredAdapter.StaggerHolder holder, int position) {

        CompanyActivity companyActivity = companyActivityList.get(position);
        holder.setData(companyActivity);

    }


    @Override
    public int getItemCount() {
        if(companyActivityList != null&companyActivityList.size() > 0){
            return companyActivityList.size();
        }else return 0;
    }


    public class StaggerHolder extends RecyclerView.ViewHolder {

        private final ImageView activity_img;
        private final TextView company_name,activity_describe,activity_price,buyer_count;
        public StaggerHolder(View itemView) {
            super(itemView);
            this.activity_img = itemView.findViewById(R.id.activity_img);
            this.company_name = itemView.findViewById(R.id.companyName);
            this.activity_describe = itemView.findViewById(R.id.activity_describe);
            this.activity_price = itemView.findViewById(R.id.activity_price);
            this.buyer_count = itemView.findViewById(R.id.buyer_count);
        }

        public void setData(CompanyActivity companyActivity){

            Bitmap bitmap = BitmapFactory.decodeFile(companyActivity.getImg());
            activity_img.setImageBitmap(bitmap);

            Company company = companyDao.loadByRowId(companyActivity.getCompany_id());
            company_name.setText(company.getCompany_name());

            activity_describe.setText(companyActivity.getTitle());

            activity_price.setText(companyActivity.getPrice()+"");

            long count = orderDao.queryBuilder().where(OrderDao.Properties.Company_id.eq(companyActivity.getCompany_id())).count();
            buyer_count.setText(count+"");

        }
    }
}
