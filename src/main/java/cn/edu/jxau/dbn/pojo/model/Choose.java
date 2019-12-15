package cn.edu.jxau.dbn.pojo.model;

import cn.edu.jxau.dbn.pojo.BaseModel;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author Vector
 * @Date 2019/11/18
 * @Desc ...
 * @Since 1.0.0
 */
public class Choose extends BaseModel {
    //投票者ID
    @NotNull
    private String userId;
    //学生的投票数
    @NotNull
    private Integer userVoteCount;
    //选择的老师ID
    @NotNull
    private Integer userChooseTeacher;
    //选择时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date userChooseTime;
    //IP地址
    private String userChooseIp;


    //被投票的老师的信息
    private Teacher teacher;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getUserChooseTeacher() {
        return userChooseTeacher;
    }

    public void setUserChooseTeacher(Integer userChooseTeacher) {
        this.userChooseTeacher = userChooseTeacher;
    }

    public Date getUserChooseTime() {
        return userChooseTime;
    }

    public void setUserChooseTime(Date userChooseTime) {
        this.userChooseTime = userChooseTime;
    }

    public String getUserChooseIp() {
        return userChooseIp;
    }

    public void setUserChooseIp(String userChooseIp) {
        this.userChooseIp = userChooseIp;
    }

    public Integer getUserVoteCount() {
        return userVoteCount;
    }

    public void setUserVoteCount(Integer userVoteCount) {
        this.userVoteCount = userVoteCount;
    }


    @Override
    public String toString() {
        return "Choose{" +
                "userId=" + userId +
                ", userVoteCount=" + userVoteCount +
                ", userChooseTeacher=" + userChooseTeacher +
                ", userChooseTime=" + userChooseTime +
                ", userChooseIp='" + userChooseIp + '\'' +
                ", teacher=" + teacher + super.toString() +
                '}';
    }
}
