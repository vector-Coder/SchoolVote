package cn.edu.jxau.dbn.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @Author Vector
 * @Date 2019/11/18
 * @Desc ...
 * @Since 1.0.0
 */
public abstract class BaseModel {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lineInsertTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lineUpdateTime;

    public Date getLineInsertTime() {
        return lineInsertTime;
    }

    public void setLineInsertTime(Date lineInsertTime) {
        this.lineInsertTime = lineInsertTime;
    }

    public Date getLineUpdateTime() {
        return lineUpdateTime;
    }

    public void setLineUpdateTime(Date lineUpdateTime) {
        this.lineUpdateTime = lineUpdateTime;
    }

    @Override
    public String toString() {
        return "BaseModel{" +
                "lineInsertTime=" + lineInsertTime +
                ", lineUpdateTime=" + lineUpdateTime +
                '}';
    }
}
