package com.example.dell.c1ean.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.example.dell.c1ean.Activity.Worker.PayActivity;
import com.example.dell.c1ean.Bean.Order;
import com.example.dell.c1ean.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderActivity extends AppCompatActivity {
    private String[] typeArray = {"专业保洁", "家电清洗", "家居养护", "洗护服务"};
    private TextView clean_type,time,button2,reservation;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);

        clean_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(OrderActivity.this);
                b.setTitle("清扫类型")
                        .setSingleChoiceItems(typeArray,  //装载数组信息
                                //默认选中第一项
                                0,
                                //为列表添加监听事件
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        switch (which) {
                                            case 0:
                                                clean_type.setText(typeArray[0]);
                                                break;
                                            case 1:
                                                clean_type.setText(typeArray[1]);
                                                break;
                                            case 2:
                                                clean_type.setText(typeArray[2]);
                                                break;
                                        }
                                        dialog.cancel();  //用户选择后，关闭对话框
                                    }
                                })
                        .create()
                        .show();
            }
        });


        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickDialog datePickDialog = new DatePickDialog(OrderActivity.this);
                //设置上下年分限制
                datePickDialog.setYearLimt(5);
                //设置标题
                datePickDialog.setTitle("选择时间");
                //设置类型
                datePickDialog.setType(DateType.TYPE_ALL);
                //设置消息体的显示格式，日期格式
                datePickDialog.setMessageFormat("yyyy-MM-dd-hh-mm");
                //设置选择回调
                datePickDialog.setOnChangeLisener(null);
                //设置点击确定按钮回调
                datePickDialog.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        //设置时间格式
                        SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy年MM月dd日hh时mm分");
                        time.setText(timeStampFormat.format(date));
                    }
                });

                datePickDialog.show();
            }
        });



        reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(OrderActivity.this, PayActivity.class);

                startActivity(intent);
            }


        });
    }}
