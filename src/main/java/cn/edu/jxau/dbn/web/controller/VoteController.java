package cn.edu.jxau.dbn.web.controller;

import cn.edu.jxau.dbn.pojo.model.Choose;
import cn.edu.jxau.dbn.pojo.model.User;
import cn.edu.jxau.dbn.service.UserService;
import cn.edu.jxau.dbn.util.JsonResult;
import cn.edu.jxau.dbn.util.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


/**
 * @Author Vector
 * @Date 2019/11/18
 * @Desc ...
 * @Since 1.0.0
 */
@RestController
@CrossOrigin(allowCredentials = "true")
@RequestMapping("/Vote")
@Validated
public class VoteController {

    private final static Logger logger = LoggerFactory.getLogger(VoteController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/vote")
    public JsonResult userVote(@Valid User user,
                               @RequestParam("chooseIdArray") Integer[] chooseIdArray,
                               @RequestParam("voteArray") Integer[] voteArray,
                               HttpServletRequest request) {
        if (chooseIdArray.length != voteArray.length) {
            return new JsonResult("数据非法", ResultCode.ILLEGALITY.getCode(), false);
        }
        try {
            Choose[] chooses = new Choose[chooseIdArray.length];
            for (int index = 0; index < chooseIdArray.length; index++) {
                Choose choose = new Choose();
                choose.setUserId(user.getUserId());
                choose.setUserChooseTeacher(chooseIdArray[index]);
                choose.setUserVoteCount(voteArray[index]);
                chooses[index] = choose;
            }
            //获取IP地址
            user.setUserLoginIp(request.getRemoteAddr());
            return userService.userVote(user, chooses);
        } catch (Exception e) {
            logger.info(e.getMessage());
            return new JsonResult<>("投票失败", ResultCode.ILLEGALITY.getCode(), false);
        }
    }
}
