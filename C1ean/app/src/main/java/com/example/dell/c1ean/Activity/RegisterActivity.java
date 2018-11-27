package com.example.dell.c1ean.Activity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dell.c1ean.Bean.User;
import com.example.dell.c1ean.DAO.DaoMaster;
import com.example.dell.c1ean.DAO.DaoSession;
import com.example.dell.c1ean.DAO.UserDao;
import com.example.dell.c1ean.R;

import java.util.List;

/**
 * Created by DELL on 2018/11/21.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    UserDao userDao;
    private Spinner spinner;
    private EditText phone,password;
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        spinner = findViewById(R.id.userType);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        button = findViewById(R.id.register);

        initDbHelp();

        button.setOnClickListener(this);
    }

    private boolean isNotEmpty(String s) {
        if (s != null && !s.equals("") || s.length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isEmpty(String s) {
        if (isNotEmpty(s)) {
            return false;
        } else {
            return true;
        }
    }

    /*初始化数据库相关*/
    private void initDbHelp() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "USER", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        userDao = daoSession.getUserDao();
    }

    @Override
    public void onClick(View view) {

//        String type = spinner.getPrompt().toString();
        String tel = phone.getText().toString();
        String pwd = password.getText().toString();

        User user = new User();
        user.setName("测试1");
        user.setType("用户");
        user.setPassword(pwd);
        user.setTel(tel);
        userDao.insert(user);
        List<User> list = userDao.queryl
        Toast.makeText(RegisterActivity.this,"注册成功:"+userDao.queryRaw("type = 用户",null),Toast.LENGTH_LONG).show();
    }
}
