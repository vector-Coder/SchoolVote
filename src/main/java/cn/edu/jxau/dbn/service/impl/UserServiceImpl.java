package cn.edu.jxau.dbn.service.impl;

import cn.edu.jxau.dbn.config.GlobalConfig;
import cn.edu.jxau.dbn.dao.mapper.UserMapper;
import cn.edu.jxau.dbn.exception.BaseException;
import cn.edu.jxau.dbn.exception.ControlFailException;
import cn.edu.jxau.dbn.exception.NotSuchTeacherException;
import cn.edu.jxau.dbn.pojo.model.Choose;
import cn.edu.jxau.dbn.pojo.model.IP;
import cn.edu.jxau.dbn.pojo.model.Teacher;
import cn.edu.jxau.dbn.pojo.model.User;
import cn.edu.jxau.dbn.service.ChooseService;
import cn.edu.jxau.dbn.service.IPService;
import cn.edu.jxau.dbn.service.TeacherService;
import cn.edu.jxau.dbn.service.UserService;
import cn.edu.jxau.dbn.util.JsonResult;
import cn.edu.jxau.dbn.util.ResultCode;
import cn.edu.jxau.dbn.util.jxauApi.JxauUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author Vector
 * @Date 2019/11/18
 * @Desc ...
 * @Since 1.0.0
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JxauUtil jxauUtil;

    @Autowired
    private IPService ipService;

    @Autowired
    private ChooseService chooseService;

    @Autowired
    private TeacherService teacherService;

    @Override
    public JsonResult<User> userLogin(User user) throws BaseException {
        JSONObject result;
        try {
            Map map = jxauUtil.getStudentData(user.getUserId(), user.getUserPassword());
//            result = JSONObject.fromObject("{Success=true, Message='教务登录成功！', Data={UserCode=20171905, UserName='袁飞煌', Department='软件工程1706', UserType=null, IPAdress='219.229.200.104', Class=null}}");
            result = JSONObject.fromObject(map);
            //登录成功
            if ((boolean) result.get("Success")) {
                logger.info("登录成功:" + user.getUserId() + " ----- 事务开始");
                //第一次登陆
                User data;
                if ((data = this.findById(user).getData()) == null) {
                    JSONObject studentData = JSONObject.fromObject(result.get("Data"));
                    data = new User();
                    data.setUserLoginIp(user.getUserLoginIp());
                    data.setUserId(user.getUserId());
                    data.setUserClass((String) studentData.get("Department"));
                    data.setUserName((String) studentData.get("UserName"));
                    String userIdStr = user.getUserId();
                    //判断投票者身份信息
                    if (userIdStr.length() == 8) {
                        data.setUserType("学生");
                    } else if (userIdStr.length() == 4) {
                        data.setUserType("教师");
                    } else {
                        data.setUserType("未知");
                    }
                    data.setUserHasVoteCount(0);
                    data.setUserVoteCount(0);
                    data.setLineInsertTime(new Date());
                    data.setLineUpdateTime(new Date());
                    //插入新的数据
                    try {
                        if (!this.insertNewData(data).getData()) {
                            logger.info("插入新投票者数据失败:" + data);
                            throw new ControlFailException("插入新投票者数据失败:" + data);
                        }
                    } catch (DuplicateKeyException e) {
                        logger.info("学生添加发生主键冲突：" + data.getUserId());
                        return new JsonResult<>("学生存在", data, ResultCode.ILLEGALITY.getCode(), true);
                    }
                    logger.info("投票者数据插入成功");
                }
                logger.info("登录成功:" + data);
                return new JsonResult<>("", data, ResultCode.SUCCESS.getCode(), true);
            }
            logger.info("登录失败:" + user);
            return new JsonResult<>("登录失败,账号密码错误", null, ResultCode.FAIL.getCode(), false);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("登录异常:" + user + " exception:" + e.getMessage());
            return new JsonResult<>("账号密码错误", null, ResultCode.FAIL.getCode(), false);
        }
    }

    @Override
    @Transactional
    public JsonResult<Boolean> userVote(User user, Choose[] chooses) throws BaseException {
        //校验数据
        JsonResult<User> checkResult = this.userLogin(user);
        User tempUser = checkResult.getData();
        if (tempUser == null) {
            logger.info("投票者信息不存在");
            return new JsonResult<>(checkResult.getMessage(), false, checkResult.getCode(), checkResult.getResult());
        }
        //检查投票次数是否合法
        if (tempUser.getUserHasVoteCount() >= GlobalConfig.MAX_VOTE_LIMIT) {
            logger.info("用户投票次数达到上限:" + user);
            return new JsonResult<>("亲，你已经投过票了！", false, ResultCode.ILLEGALITY.getCode(), false);
        }
        logger.info("用户投票次数正常,投票总次数正常:" + tempUser.getUserHasVoteCount() + ":" + tempUser.getUserVoteCount());
        int newVoteCount = 0;
        for (Choose choose : chooses) {
            newVoteCount += choose.getUserVoteCount();
        }
        int totalCount = newVoteCount + tempUser.getUserVoteCount();
        //判断累计次数是不是超过了总次数
        if (totalCount > GlobalConfig.MAX_VOTE_COUNT) {
            return new JsonResult<>("您的选票数量还剩" + (GlobalConfig.MAX_VOTE_COUNT - tempUser.getUserVoteCount()) + "张,请重新选择", ResultCode.ILLEGALITY.getCode(), false);
        }
        //是不是累计次数小于最低要求次数
        if (totalCount < GlobalConfig.MIN_VOTE_COUNT) {
            return new JsonResult<>("您最少累计投满" + GlobalConfig.MIN_VOTE_COUNT + "票，至少再选" + (GlobalConfig.MIN_VOTE_COUNT - totalCount) + "请重新选择", ResultCode.ILLEGALITY.getCode(), false);
        }
        //查询IP使用数量
        IP ip = new IP();
        ip.setIpId(tempUser.getUserLoginIp());
        ip = ipService.findById(ip).getData();
        //ip地址信息不存在
        if (ip == null) {
            ip = new IP();
            ip.setIpId(tempUser.getUserLoginIp());
            ip.setIpUseCount(1);
            ip.setLineInsertTime(new Date());
            ip.setLineUpdateTime(new Date());
            if (!ipService.insertNewData(ip).getData()) {
                logger.info("插入新IP地址失败:" + ip.getIpId());
                throw new ControlFailException("插入新IP地址失败:" + ip.getIpId());
            }
            logger.info("插入新IP地址成功:" + ip.getIpId());
        } else {
            int ipUseCount = ip.getIpUseCount();
            //先添加一次使用次数
            ip.setIpUseCount(1);
            if (!ipService.updateById(ip).getData()) {
                logger.info("更新IP地址使用次数失败:" + ip.getIpId());
                throw new ControlFailException("更新IP地址使用次数失败:" + ip.getIpId());
            }
            logger.info("更新IP地址使用次数成功:" + ip.getIpId());
            if (ipUseCount >= GlobalConfig.MAX_IP_USE_COUNT) {
                logger.info("IP地址使用次数达到上限:" + ip.getIpId());
                return new JsonResult<>("您的设备投票次数已用尽,请更换设备重新投票", false, ResultCode.ILLEGALITY.getCode(), false);
            }
            logger.info("IP地址使用次数正常:" + ipUseCount);
        }
        //检查老师数据是否存在
        for (Choose choose : chooses) {
            //更新投票记录
            choose.setUserChooseIp(tempUser.getUserLoginIp());
            choose.setUserChooseTime(new Date());
            choose.setLineInsertTime(new Date());
            choose.setLineUpdateTime(new Date());
            //这个老师为进行选举
            if (chooseService.findById(choose).getData() == null) {
                try {
                    if (!chooseService.insertNewData(choose).getResult()) {
                        logger.info("插入投票记录失败:" + choose.getUserId() + "->" + choose.getUserChooseTeacher());
                        throw new ControlFailException("插入投票记录失败:" + choose.getUserId() + "->" + choose.getUserChooseTeacher());
                    }
                    logger.info("插入投票记录成功:" + choose.getUserId() + "->" + choose.getUserChooseTeacher());
                    Teacher teacher = new Teacher();
                    teacher.setTeacherId(choose.getUserChooseTeacher());
                    if ((teacher = teacherService.findById(teacher).getData()) == null) {
                        logger.info("选择的老师不存在:" + choose.getUserChooseTeacher());
                        throw new NotSuchTeacherException("您选择的老师不存在:" + choose.getUserChooseTeacher());
                    }
                    //记录插入成功再更新老师的数据
                    teacher.setTeacherCount(choose.getUserVoteCount());
                    //老师数据更新失败
                    if (!teacherService.updateById(teacher).getResult()) {
                        logger.info("更新老师得票数据失败:" + teacher.getTeacherName());
                        throw new ControlFailException("更新老师得票数据失败:" + teacher.getTeacherName());
                    }
                    logger.info("更新老师得票数据成功:" + teacher.getTeacherName() + "->" + choose.getUserVoteCount());
                } catch (DuplicateKeyException e) {
                    //发生了异常  可能是出现主键冲突
                    logger.info("发生投票异常：" + e.getMessage() + ",id:" + choose.getUserId());
                    throw new ControlFailException("你已经完成投票!");
                }
            } else {
                //对第二个老师再次投票
                logger.info("重复完成投票->忽略此操作：" + choose);
                //票数相减
                newVoteCount -= choose.getUserVoteCount();
            }
        }
        logger.info("更新老师得票数据完成:" + tempUser.getUserId());
        //更新学生投票的数据
        //设置投票次数
        tempUser.setUserHasVoteCount(1);
        if (newVoteCount > 0) {
            tempUser.setUserVoteCount(newVoteCount);
        } else {
            tempUser.setUserVoteCount(null);
        }
        if (!this.updateById(tempUser).getResult()) {
            logger.info("更新学生投票次数失败:" + tempUser.getUserId());
            throw new ControlFailException("更新学生投票次数失败" + tempUser.getUserId());
        }
        logger.info("投票成功:" + user.getUserId() + " ----- 事务结束");
        return new JsonResult<>("投票成功", true, ResultCode.SUCCESS.getCode(), true);
    }

    @Override
    public JsonResult<List<User>> findAllUserLimit(Integer pageIndex) {
        return null;
    }

    @Override
    public JsonResult<User> findUserAllChoose(User user) {
        return null;
    }

    @Override
    public JsonResult<User> findById(User params) {
        User user = userMapper.findById(params.getUserId());
        if (user == null) {
            new JsonResult<>("查询失败", ResultCode.FAIL.getCode(), false);
        }
        return new JsonResult<>("查询成功", user, ResultCode.SUCCESS.getCode(), true);
    }

    @Override
    public JsonResult<Boolean> updateById(User params) {
        int res = userMapper.updateById(params);
        if (res <= 0) {
            new JsonResult<>("更新失败", false, ResultCode.FAIL.getCode(), false);
        }
        return new JsonResult<>("更新成功", true, ResultCode.SUCCESS.getCode(), true);
    }

    @Override
    public JsonResult<Boolean> insertNewData(User params) {
        int res = userMapper.insertNewData(params);
        if (res <= 0) {
            new JsonResult<>("添加失败", false, ResultCode.FAIL.getCode(), false);
        }
        return new JsonResult<>("添加成功", true, ResultCode.SUCCESS.getCode(), true);
    }

    @Override
    public JsonResult<Boolean> deleteById(User params) {
        int res = userMapper.deleteById(params.getUserId());
        if (res <= 0) {
            new JsonResult<>("删除失败", false, ResultCode.FAIL.getCode(), false);
        }
        return new JsonResult<>("删除成功", true, ResultCode.SUCCESS.getCode(), true);
    }

}
