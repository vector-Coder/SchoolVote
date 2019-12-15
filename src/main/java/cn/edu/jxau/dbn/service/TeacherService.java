package cn.edu.jxau.dbn.service;

import cn.edu.jxau.dbn.pojo.model.Teacher;
import cn.edu.jxau.dbn.pojo.model.User;
import cn.edu.jxau.dbn.service.base.BaseService;
import cn.edu.jxau.dbn.util.JsonResult;

import java.util.List;

/**
 * @Author Vector
 * @Date 2019/11/18
 * @Desc ...
 * @Since 1.0.0
 */
public interface TeacherService extends BaseService<Teacher> {

    /**
     * 找到一个老师全部的投票者信息
     *
     * @param teacher 老师对象
     * @return JsonResult<List<User>>
     */
    JsonResult<List<User>> findTeacherAllVote(Teacher teacher);

    JsonResult<List<Teacher>> findAllTeacherIntroduce();

    JsonResult<List<Teacher>> findAllTeacherData();

    JsonResult<Boolean> reloadTeacherDesc();

    JsonResult<List<Teacher>> findAllData();
}
