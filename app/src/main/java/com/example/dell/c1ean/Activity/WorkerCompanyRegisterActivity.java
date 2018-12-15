package com.example.dell.c1ean.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dell.c1ean.Application.BaseApplication;
import com.example.dell.c1ean.DAO.CompanyDao;
import com.example.dell.c1ean.DAO.RegisterDAO;
import com.example.dell.c1ean.DAO.WorkerDao;
import com.example.dell.c1ean.R;
import com.gyf.barlibrary.ImmersionBar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 李雯晴 on 2018/11/29.
 * 员工和公司注册界面
 */

public class WorkerCompanyRegisterActivity extends AppCompatActivity{

    private TextInputLayout t0,t1,t2,t3;
    private ImageView back;
    private Button register;
    private RegisterDAO registerDAO;
    private String type;
    private BaseApplication baseApplication;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workercompany_register);

        ImmersionBar.with(this).init();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);    //设置全屏
        type = getIntent().getStringExtra("type");  //获取注册的用户类型

        initView();
    }

    private void initView(){

        t0 = findViewById(R.id.t0);
        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);
        back = findViewById(R.id.back);
        register = findViewById(R.id.register);

        baseApplication = (BaseApplication)getApplication();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkerCompanyRegisterActivity.this,RegisterTypeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = t0.getEditText().getText().toString();
                String phone = t1.getEditText().getText().toString();
                String pwd = t2.getEditText().getText().toString();
                String cpwd = t3.getEditText().getText().toString();
                registerDAO = new RegisterDAO((BaseApplication)getApplication());

                if (validateCode(code)&&validateTel(phone)&&validatePassword(pwd,cpwd)){    //验证公司代码格式、手机是否为空，两次密码输入是否相同

                    if (type.equals("家政人员")){
                        if (registerDAO.existValid(type,phone)){    //验证是否存在此工作人员

                            if (registerDAO.isRegister(type,phone)){    //验证是否已注册

                                Toast.makeText(WorkerCompanyRegisterActivity.this, "您已注册，请登录", Toast.LENGTH_SHORT).show();

                            }else {

                                registerDAO.setPassword(type,phone,pwd);    //设置密码
                                Toast.makeText(WorkerCompanyRegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                Intent intent1 = new Intent(WorkerCompanyRegisterActivity.this,LoginActivity.class);
                                startActivity(intent1);
                                finish();
                            }
                        }else {
                            Toast.makeText(WorkerCompanyRegisterActivity.this, "请联系您的公司添加您的信息", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        if(registerDAO.existValid(type,phone)){ //验证是否存在此格式

                            if (registerDAO.isRegister(type,phone)){    //验证是否注册
                                Toast.makeText(WorkerCompanyRegisterActivity.this, "您已注册，请登录", Toast.LENGTH_SHORT).show();
                            }else {
                                registerDAO.setPassword(type,phone,pwd);    //设置密码
                                Toast.makeText(WorkerCompanyRegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                Intent intent2 = new Intent(WorkerCompanyRegisterActivity.this,LoginActivity.class);
                                startActivity(intent2);
                                finish();
                            }
                        }else {
                            Toast.makeText(WorkerCompanyRegisterActivity.this, "请先联系客服提交公司信息审核", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }

    /**
     * 显示错误提示，并获取焦点
     * @param textInputLayout
     * @param error
     */
    private void showError(TextInputLayout textInputLayout,String error){
        textInputLayout.setError(error);
        textInputLayout.getEditText().setText("");
        textInputLayout.getEditText().setFocusable(true);
        textInputLayout.getEditText().setFocusableInTouchMode(true);
        textInputLayout.getEditText().requestFocus();
    }

    /**
     * 判定是否公司代码为空
     * @param string
     * @return
     */
    private boolean validateCode(String string){
        String format = "[0-9]{8}[-]\\p{Upper}{1}";
        if (!string.matches(format)){
            showError(t0,"公司代码格式错误");
            return false;
        }else if (string.isEmpty()){
            showError(t0,"公司代码不能为空");
            return false;
        }return true;
    }

    /**
     * 验证手机号是否为空
     * @param phone
     * @return
     */
    private boolean validateTel(String phone){
        if (phone.isEmpty()) {
            return false;
        }
        if (phone.length() != 11) {
            return false;
        }
        Pattern pattern = Pattern.compile("^1[3,5]\\d{9}||18[6,8,9]\\d{8}$");
        Matcher matcher = pattern.matcher(phone);

        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 验证密码是否符合格式，验证两次密码是否输入相同
     * @param password
     * @return
     */
    private boolean validatePassword(String password,String cpassword) {
        if (password.isEmpty()) {
            showError(t2,"密码不能为空");
            return false;
        } else if (password.length() < 6 || password.length() > 18) {
            showError(t2,"密码长度为6-18位");
            return false;
        }else if (!password.equals(cpassword)){
            showError(t3,"两次密码输入不相同");
            return false;
        }

        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }
}
