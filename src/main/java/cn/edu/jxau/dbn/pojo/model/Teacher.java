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
public class Teacher extends BaseModel {
    //老师的序号
    @NotNull
    private Integer teacherId;
    //老师的名字
    @NotNull
    private String teacherName;
    //老师的票数
    private Integer teacherCount;
    //老师的部门或者院系
    @NotNull
    private String teacherDepartment;
    //老师的描述
    @NotNull
    private String teacherDesc;
    private String teacherImgUrl;


    public String getTeacherImgUrl() {
        return teacherImgUrl;
    }

    public void setTeacherImgUrl(String teacherImgUrl) {
        this.teacherImgUrl = teacherImgUrl;
    }

    //投票的学生集合
    private List<User> userList;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public String getTeacherDesc() {
        return teacherDesc;
    }

    public String getTeacherDepartment() {
        return teacherDepartment;
    }

    public void setTeacherDepartment(String teacherDepartment) {
        this.teacherDepartment = teacherDepartment;
    }

    public void setTeacherDesc(String teacherDesc) {
        this.teacherDesc = teacherDesc;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Integer getTeacherCount() {
        return teacherCount;
    }

    public void setTeacherCount(Integer teacherCount) {
        this.teacherCount = teacherCount;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", teacherCount=" + teacherCount +
                ", teacherDepartment='" + teacherDepartment + '\'' +
                ", teacherDesc='" + teacherDesc + '\'' +
                ", teacherImgUrl='" + teacherImgUrl + '\'' +
                ", userList=" + userList +
                '}';
    }
}
