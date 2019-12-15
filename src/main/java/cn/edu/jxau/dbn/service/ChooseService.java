package cn.edu.jxau.dbn.service;

import cn.edu.jxau.dbn.pojo.model.Choose;
import cn.edu.jxau.dbn.service.base.BaseService;
import cn.edu.jxau.dbn.util.JsonResult;

/**
 * @Author Vector
 * @Date 2019/11/18
 * @Desc ...
 * @Since 1.0.0
 */
public interface ChooseService extends BaseService<Choose> {

    JsonResult findAllChooseLimit(Integer pageIndex);
}
