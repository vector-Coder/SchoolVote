package cn.edu.jxau.dbn.service.impl;

import cn.edu.jxau.dbn.config.GlobalConfig;
import cn.edu.jxau.dbn.dao.mapper.ConfigMapper;
import cn.edu.jxau.dbn.global.GlobalData;
import cn.edu.jxau.dbn.pojo.model.Config;
import cn.edu.jxau.dbn.service.ConfigService;
import cn.edu.jxau.dbn.util.JsonResult;
import cn.edu.jxau.dbn.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Vector
 * @Date 2019/11/21
 * @Desc ...
 * @Since 1.0.0
 */
@Service("configService")
public class ConfigServiceImpl implements ConfigService {


    @Autowired
    private ConfigMapper configMapper;

    @Override
    public JsonResult<Config> findById(Config params) {
        Config config = configMapper.findById(GlobalConfig.CONFIG_ID);
        if (config == null) {
            return new JsonResult<>("查询失败", ResultCode.NOT_FOUND.getCode(), false);
        }
        return new JsonResult<>("查询成功", config, ResultCode.SUCCESS.getCode(), true);
    }

    @Override
    public JsonResult<Boolean> updateById(Config params) {
        int res = configMapper.updateById(params);
        if (res <= 0) {
            return new JsonResult<>("更新失败", ResultCode.NOT_FOUND.getCode(), false);
        }
        return new JsonResult<>("更新成功", ResultCode.SUCCESS.getCode(), true);
    }

    @Override
    public JsonResult<Boolean> insertNewData(Config params) {
        return null;
    }

    @Override
    @Deprecated
    public JsonResult<Boolean> deleteById(Config params) {
        return null;
    }


    @Override
    public JsonResult<Config> findDefaultConfig() {
        Config config = GlobalConfig.getConfig();
        if (config == null) {
            config = configMapper.findById(GlobalConfig.CONFIG_ID);
            if (config == null) {
                return new JsonResult<>("查询失败", ResultCode.NOT_FOUND.getCode(), false);
            }
            GlobalConfig.init(config);
        }
        return new JsonResult<>("查询成功", config, ResultCode.SUCCESS.getCode(), true);
    }

    @Override
    public JsonResult<Boolean> reloadConfig() {
        Config config = configMapper.findById(GlobalConfig.CONFIG_ID);
        GlobalConfig.init(config);
        return new JsonResult<>("更新成功",ResultCode.SUCCESS.getCode(),true);
    }
}
