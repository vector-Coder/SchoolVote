package cn.edu.jxau.dbn.service;

import cn.edu.jxau.dbn.exception.BaseException;
import cn.edu.jxau.dbn.pojo.model.Choose;
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
public interface UserService extends BaseService<User> {

    /**
     * 登录的方法
     *
     * @param user 登录对象
     * @return JsonResult<Boolean>
     */
    JsonResult<User> userLogin(User user) throws BaseException;

    /**
     * 用户投票
     *
     * @param user   用户对象
     * @param chooses 选择的老师
     * @return JsonResult<Boolean>
     */
    JsonResult<Boolean> userVote(User user, Choose[] chooses) throws BaseException;


    JsonResult<List<User>> findAllUserLimit(Integer pageIndex);

    JsonResult<User> findUserAllChoose(User user);
}
