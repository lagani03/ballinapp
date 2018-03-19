package com.ballinapp.data.info;

import com.ballinapp.enum_values.AppearanceUpdateEnum;

/**
 * User: dusan <br/> Date: 3/14/18
 */
public class AppearanceUpdateBean {

    private int id;
    private AppearanceUpdateEnum updateEnum;

    public AppearanceUpdateBean(int id, AppearanceUpdateEnum updateEnum) {
        this.id = id;
        this.updateEnum = updateEnum;
    }

    public AppearanceUpdateBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AppearanceUpdateEnum getUpdateEnum() {
        return updateEnum;
    }

    public void setUpdateEnum(AppearanceUpdateEnum updateEnum) {
        this.updateEnum = updateEnum;
    }
}
