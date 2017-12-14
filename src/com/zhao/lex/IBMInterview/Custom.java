package com.zhao.lex.IBMInterview;

import java.util.Date;

/**
 * Created by qtfs on 2017/12/7.
 */
public class Custom {
    private long id;
    private CustomType customType;
    private int customServiceTime;
    private Date customArriveTime;

    public long getId(){
        return this.id;
    }

    public CustomType getCustomType() {
        return this.customType;
    }

    public int getCustomServiceTime() {
        return this.customServiceTime;
    }

    public Date getCustomArriveTime() {
        return this.customArriveTime;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCustomType(CustomType customType) {
        this.customType = customType;
    }

    public void setCustomArriveTime(Date customArriveTime) {
        this.customArriveTime = customArriveTime;
    }

    public void setCustomServiceTime(int serviceTime) {
        this.customServiceTime = serviceTime;
    }




}
enum CustomType {
    VIP("the VIP custom."),
    NORMAL("the COMMON custom.");

    private String description;

    private CustomType(String desc) {
        this.description = desc;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}

