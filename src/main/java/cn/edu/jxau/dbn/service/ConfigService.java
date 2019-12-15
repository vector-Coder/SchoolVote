package cn.edu.jxau.dbn.service;

import cn.edu.jxau.dbn.pojo.model.Config;
import cn.edu.jxau.dbn.service.base.BaseService;
import cn.edu.jxau.dbn.util.JsonResult;

/**
 * @Author Vector
 * @Date 2019/11/21
 * @Desc ...
 * @Since 1.0.0
 */
public interface ConfigService extends BaseService<Config> {

    JsonResult<Config> findDefaultConfig();

    JsonResult<Boolean> reloadConfig();
}
