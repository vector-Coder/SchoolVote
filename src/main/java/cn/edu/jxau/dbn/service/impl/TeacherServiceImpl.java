package cn.edu.jxau.dbn.service.impl;

import cn.edu.jxau.dbn.dao.mapper.TeacherMapper;
import cn.edu.jxau.dbn.exception.BaseException;
import cn.edu.jxau.dbn.global.GlobalData;
import cn.edu.jxau.dbn.pojo.model.Teacher;
import cn.edu.jxau.dbn.pojo.model.User;
import cn.edu.jxau.dbn.service.TeacherService;
import cn.edu.jxau.dbn.util.JsonResult;
import cn.edu.jxau.dbn.util.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Vector
 * @Date 2019/11/18
 * @Desc ...
 * @Since 1.0.0
 */
@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {

    private final static Logger logger = LoggerFactory.getLogger(TeacherServiceImpl.class);

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public JsonResult<List<User>> findTeacherAllVote(Teacher teacher) {
        List<User> list = teacherMapper.findTeacherAllVote(teacher.getTeacherId());
        if (list.isEmpty()) {
            return new JsonResult<>("暂无数据", ResultCode.NOT_FOUND.getCode(), false);
        }
        return new JsonResult<>("查询成功", list, ResultCode.SUCCESS.getCode(), true);
    }

    @Override
    public JsonResult<Teacher> findById(Teacher params) {
        Teacher teacher = teacherMapper.findById(params.getTeacherId());
        if (teacher == null) {
            return new JsonResult<>("暂无数据", ResultCode.NOT_FOUND.getCode(), false);
        }
        return new JsonResult<>("查询成功", teacher, ResultCode.SUCCESS.getCode(), true);
    }

    @Override
    public JsonResult<Boolean> updateById(Teacher params) {
        int res = teacherMapper.updateById(params);
        if (res <= 0) {
            return new JsonResult<>("更新投票数据失败", false, ResultCode.NOT_FOUND.getCode(), false);
        }
        return new JsonResult<>("更新投票数据成功", true, ResultCode.SUCCESS.getCode(), true);
    }

    @Override
    public JsonResult<Boolean> insertNewData(Teacher params) {
        int res = teacherMapper.insertNewData(params);
        if (res <= 0) {
            return new JsonResult<>("添加投票数据失败", false, ResultCode.NOT_FOUND.getCode(), false);
        }
        return new JsonResult<>("添加投票数据成功", true, ResultCode.SUCCESS.getCode(), true);
    }

    @Override
    public JsonResult<Boolean> deleteById(Teacher params) {
        int res = teacherMapper.insertNewData(params);
        if (res <= 0) {
            return new JsonResult<>("删除投票数据失败", false, ResultCode.NOT_FOUND.getCode(), false);
        }
        return new JsonResult<>("删除投票数据成功", true, ResultCode.SUCCESS.getCode(), true);
    }

    @Override
    public JsonResult<List<Teacher>> findAllData() {
        List<Teacher> list = teacherMapper.findAllData();
        if (list == null) {
            return new JsonResult<>("暂无数据", ResultCode.NOT_FOUND.getCode(), false);
        }
        return new JsonResult<>("查询成功", list, ResultCode.SUCCESS.getCode(), true);
    }

    @Override
    public JsonResult<List<Teacher>> findAllTeacherIntroduce() {
        logger.info("获取全部老师的基本信息");
        List<Teacher> teacherList;
        try {
            teacherList = GlobalData.getAllTeacherData();
        } catch (BaseException e) {
            logger.info("系统启动，老师信息未初始化加载");
            teacherList = this.findAllData().getData();
            GlobalData.init(teacherList);
        }
        if (teacherList.isEmpty()) {
            return new JsonResult<>("暂无数据", ResultCode.NOT_FOUND.getCode(), false);
        }
        return new JsonResult<>("查询成功", teacherList, ResultCode.SUCCESS.getCode(), true);
    }

    @Override
    public JsonResult<List<Teacher>> findAllTeacherData() {
        logger.info("获取全部老师的得票信息");
        List<Teacher> teacherList = teacherMapper.findAllTeacherData();
        if (teacherList.isEmpty()) {
            return new JsonResult<>("暂无数据", ResultCode.NOT_FOUND.getCode(), false);
        }
        return new JsonResult<>("查询成功", teacherList, ResultCode.SUCCESS.getCode(), true);
    }

    @Override
    public JsonResult<Boolean> reloadTeacherDesc() {
        List<Teacher> list = teacherMapper.findAllData();
        GlobalData.init(list);
        return new JsonResult<>("更新成功", ResultCode.SUCCESS.getCode(), true);
    }
}
