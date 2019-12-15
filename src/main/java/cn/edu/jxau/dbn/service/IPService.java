package cn.edu.jxau.dbn.service;

import cn.edu.jxau.dbn.pojo.model.IP;
import cn.edu.jxau.dbn.service.base.BaseService;
import cn.edu.jxau.dbn.util.JsonResult;

import java.util.List;

/**
 * @Author Vector
 * @Date 2019/11/18
 * @Desc ...
 * @Since 1.0.0
 */
public interface IPService extends BaseService<IP> {

    JsonResult<List<IP>> findAllIPLimit();
}
