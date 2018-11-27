package com.example.dell.c1ean.Bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * Created by DELL on 2018/11/26.
 */

@Entity
public class User {

    @Id(autoincrement = true)
    private long id;
    @NotNull
    private String name;
    @NotNull
    private String type;
    private String img;
    @NotNull
    private String password;
    @NotNull
    private String tel;

    public User() {
    }

    @Keep
    public User(long id, String name,String type,String img,String password,String tel) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.img = img;
        this.password = password;
        this.tel = tel;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
