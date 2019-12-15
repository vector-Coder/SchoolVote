package cn.edu.jxau.dbn.service.impl;

import cn.edu.jxau.dbn.dao.mapper.ChooseMapper;
import cn.edu.jxau.dbn.pojo.model.Choose;
import cn.edu.jxau.dbn.service.ChooseService;
import cn.edu.jxau.dbn.util.JsonResult;
import cn.edu.jxau.dbn.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Vector
 * @Date 2019/11/18
 * @Desc ...
 * @Since 1.0.0
 */
@Service("chooseService")
public class ChooseServiceImpl implements ChooseService {

    @Autowired
    private ChooseMapper chooseMapper;

    @Override
    public JsonResult<Choose> findById(Choose params) {
        Choose choose = chooseMapper.findById(params);
        if (choose == null) {
            return new JsonResult<>("暂无数据", ResultCode.NOT_FOUND.getCode(), false);
        }
        return new JsonResult<>("查询成功", choose, ResultCode.SUCCESS.getCode(), true);
    }

    @Override
    @Deprecated
    public JsonResult<Boolean> updateById(Choose params) {
        return null;
    }


    @Override
    public JsonResult<Boolean> insertNewData(Choose params) {
        int res = chooseMapper.insertNewData(params);
        if (res <= 0) {
            return new JsonResult<>("插入投票失败", false, ResultCode.FAIL.getCode(), false);
        }
        return new JsonResult<>("插入投票成功", true, ResultCode.SUCCESS.getCode(), true);
    }

    @Override
    public JsonResult<Boolean> deleteById(Choose params) {
        int res = chooseMapper.deleteById(params);
        if (res <= 0) {
            return new JsonResult<>("删除投票失败", false, ResultCode.FAIL.getCode(), false);
        }
        return new JsonResult<>("删除投票成功", true, ResultCode.SUCCESS.getCode(), true);
    }

    @Override
    public JsonResult findAllChooseLimit(Integer pageIndex) {
        return null;
    }
}
