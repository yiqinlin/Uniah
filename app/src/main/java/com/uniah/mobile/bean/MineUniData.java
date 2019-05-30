package com.uniah.mobile.bean;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseData;

import java.io.Serializable;
import java.util.Map;

public class MineUniData extends BaseData implements Serializable {

    private int uniId;
    private String uniLogo;
    private String uniName;
    private String uniSlogan;

    private String flag;


    public MineUniData() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.mine_uni_item;
    }

    public int getUniId() {
        return uniId;
    }

    public void setUniId(int uniId) {
        this.uniId = uniId;
    }

    public String getUniLogo() {
        return uniLogo;
    }

    public void setUniLogo(String uniLogo) {
        this.uniLogo = uniLogo;
    }

    public String getUniName() {
        return uniName;
    }

    public void setUniName(String uniName) {
        this.uniName = uniName;
    }

    public String getUniSlogan() {
        return uniSlogan;
    }

    public void setUniSlogan(String uniSlogan) {
        this.uniSlogan = uniSlogan;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
