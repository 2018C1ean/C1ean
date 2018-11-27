package com.example.dell.c1ean.Bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by DELL on 2018/11/27.
 */

@Entity
public class Worker {

    @Id(autoincrement = true)
    private long id;
    @NotNull
    private String name;
    private String img;
    @NotNull
    private String type;
    @NotNull
    private String password;
    @NotNull
    private String tel;
    @NotNull
    private int company_id;
    private String introduction;
    private String start;

    public Worker() {
    }

    @Generated(hash = 66671869)
    public Worker(long id, @NotNull String name, String img, @NotNull String type, @NotNull String password, @NotNull String tel, int company_id,
            String introduction, String start) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.type = type;
        this.password = password;
        this.tel = tel;
        this.company_id = company_id;
        this.introduction = introduction;
        this.start = start;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setId(long id) {
        this.id = id;
    }
}
