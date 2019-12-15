package cn.edu.jxau.dbn.web.controller;

import cn.edu.jxau.dbn.pojo.model.User;
import cn.edu.jxau.dbn.service.UserService;
import cn.edu.jxau.dbn.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @Author Vector
 * @Date 2019/11/25
 * @Desc ...
 * @Since 1.0.0
 */
@RestController
//@CrossOrigin(allowCredentials = "true")
@Validated
@RequestMapping("/Student")
public class StudentController {

    @Autowired
    private UserService userService;


    @GetMapping("/allUserLimit")
    public JsonResult findAllVoteUserLimit(@NotNull(message = "投票者对象不可为空")
                                           @Min(value = 1, message = "分页下标最小为1") Integer pageIndex) {
        return userService.findAllUserLimit(pageIndex);
    }

    @GetMapping("/findUserChoose")
    public JsonResult findUserAllChoose(@NotNull(message = "投票者对象不可为空") User user) {
        return userService.findUserAllChoose(user);
    }
}
