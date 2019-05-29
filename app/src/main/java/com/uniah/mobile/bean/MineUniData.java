package com.uniah.mobile.bean;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseData;

import java.io.Serializable;
import java.util.Map;

public class MineUniData extends BaseData implements Serializable {

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

}
