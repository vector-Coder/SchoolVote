package cn.edu.jxau.dbn.service.impl;

import cn.edu.jxau.dbn.dao.mapper.IPMapper;
import cn.edu.jxau.dbn.pojo.model.IP;
import cn.edu.jxau.dbn.service.IPService;
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
@Service("ipService")
public class IPServiceImpl implements IPService {

    @Autowired
    private IPMapper ipMapper;

    @Override
    public JsonResult<IP> findById(IP params) {
        IP ip = ipMapper.findById(params.getIpId());
        if (ip == null) {
            return new JsonResult<>("暂无数据", ResultCode.NOT_FOUND.getCode(), false);
        }
        return new JsonResult<>("查询成功", ip, ResultCode.SUCCESS.getCode(), true);
    }

    @Override
    public JsonResult<Boolean> updateById(IP params) {
        int res = ipMapper.updateById(params);
        if (res <= 0) {
            new JsonResult<>("更新ip失败", false, ResultCode.FAIL.getCode(), false);
        }
        return new JsonResult<>("更新ip成功", true, ResultCode.SUCCESS.getCode(), true);
    }

    @Override
    public JsonResult<Boolean> insertNewData(IP params) {
        int res = ipMapper.insertNewData(params);
        if (res <= 0) {
            new JsonResult<>("插入ip失败", false, ResultCode.FAIL.getCode(), false);
        }
        return new JsonResult<>("插入ip成功", true, ResultCode.SUCCESS.getCode(), true);
    }

    @Override
    public JsonResult<Boolean> deleteById(IP params) {
        int res = ipMapper.insertNewData(params);
        if (res <= 0) {
            new JsonResult<>("删除ip失败", false, ResultCode.FAIL.getCode(), false);
        }
        return new JsonResult<>("删除ip成功", true, ResultCode.SUCCESS.getCode(), true);
    }

    @Override
    public JsonResult<List<IP>> findAllIPLimit() {
        return null;
    }
}
