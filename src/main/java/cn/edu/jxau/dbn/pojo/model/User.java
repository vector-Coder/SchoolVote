package cn.edu.jxau.dbn.pojo.model;

import cn.edu.jxau.dbn.pojo.BaseModel;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author Vector
 * @Date 2019/11/18
 * @Desc ...
 * @Since 1.0.0
 */
public class User extends BaseModel {
    //学生ID
    @NotNull
    private String userId;
    //登录的密码
    @NotNull
    private String userPassword;
    //学生的名字
    private String userName;
    //学生的班级
    private String userClass;
    //登录的IP
    private String userLoginIp;
    //用户的类型
    private String userType;
    //投票数量
    private Integer userVoteCount;
    //已投票次数
    private Integer userHasVoteCount;

    //投票记录
    private List<Choose> chooseList;

    public Integer getUserHasVoteCount() {
        return userHasVoteCount;
    }

    public void setUserHasVoteCount(Integer userHasVoteCount) {
        this.userHasVoteCount = userHasVoteCount;
    }

    public List<Choose> getChooseList() {
        return chooseList;
    }

    public void setChooseList(List<Choose> chooseList) {
        this.chooseList = chooseList;
    }

    public Integer getUserVoteCount() {
        return userVoteCount;
    }

    public void setUserVoteCount(Integer userVoteCount) {
        this.userVoteCount = userVoteCount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserClass() {
        return userClass;
    }

    public void setUserClass(String userClass) {
        this.userClass = userClass;
    }

    public String getUserLoginIp() {
        return userLoginIp;
    }

    public void setUserLoginIp(String userLoginIp) {
        this.userLoginIp = userLoginIp;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userPassword='" + userPassword + '\'' +
                ", userName='" + userName + '\'' +
                ", userClass='" + userClass + '\'' +
                ", userLoginIp='" + userLoginIp + '\'' +
                ", userType='" + userType + '\'' +
                ", userVoteCount=" + userVoteCount +
                ", userHasVoteCount=" + userHasVoteCount +
                ", chooseList=" + chooseList + super.toString() +
                '}';
    }
}
