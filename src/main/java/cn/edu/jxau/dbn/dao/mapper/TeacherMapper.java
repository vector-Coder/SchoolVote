package cn.edu.jxau.dbn.dao.mapper;

import cn.edu.jxau.dbn.pojo.model.Teacher;
import cn.edu.jxau.dbn.pojo.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Vector
 * @Date 2019/11/18
 * @Desc ...
 * @Since 1.0.0
 */
public interface TeacherMapper {

    List<User> findTeacherAllVote(Integer teacherId);


    Teacher findById(@Param("teacherId") Integer teacherId);

    Integer updateById(Teacher user);

    Integer insertNewData(Teacher user);

    Integer deleteById(@Param("teacherId")Integer teacherId);

    List<Teacher> findAllData();

    List<Teacher> findAllTeacherData();
}
