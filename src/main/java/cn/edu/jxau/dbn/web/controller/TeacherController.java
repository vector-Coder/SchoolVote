package cn.edu.jxau.dbn.web.controller;

import cn.edu.jxau.dbn.pojo.model.Teacher;
import cn.edu.jxau.dbn.service.TeacherService;
import cn.edu.jxau.dbn.util.JsonResult;
import cn.edu.jxau.dbn.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @Author Vector
 * @Date 2019/11/25
 * @Desc ...
 * @Since 1.0.0
 */
@RestController
@CrossOrigin(allowCredentials = "true")
@Validated
@RequestMapping("/Teacher")
public class TeacherController {


    @Autowired
    private TeacherService teacherService;

    @GetMapping("/allTeacherData")
    public JsonResult findAllTeacherData() {
        return teacherService.findAllTeacherData();
    }


    @GetMapping("/allTeacherDesc")
    public JsonResult findAllTeacherDesc() {
        return teacherService.findAllTeacherIntroduce();
    }

    @GetMapping("/reloadTeacherDesc")
    public JsonResult reloadTeacherDesc() {
        return teacherService.reloadTeacherDesc();
    }

//    @GetMapping("/find")
//    public JsonResult findTeacher(Teacher teacher) {
//        return teacherService.findById(teacher);
//    }
//
//    @GetMapping("/update")
//    public JsonResult updateTeacher(@NotNull(message = "老师数据不可为空") Teacher teacher) {
//        return teacherService.updateById(teacher);
//    }
//
//
//    @GetMapping("/insert")
//    public JsonResult insertTeacher(@Valid Teacher teacher) {
//        return teacherService.insertNewData(teacher);
//    }
//
//
//    @GetMapping("/del")
//    public JsonResult deleteTeacher(@NotNull(message = "老师信息不可为空") Teacher teacher) {
//        return teacherService.deleteById(teacher);
//    }
}
