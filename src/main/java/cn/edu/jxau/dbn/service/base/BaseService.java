package cn.edu.jxau.dbn.service.base;

import cn.edu.jxau.dbn.util.JsonResult;

import java.util.List;

/**
 * @Author Vector
 * @Date 2019/11/18
 * @Desc 基础的业务接口
 * @Since 1.0.0
 */
public interface BaseService<T> {

    JsonResult<T> findById(T params);

    JsonResult<Boolean> updateById(T params);

    JsonResult<Boolean> insertNewData(T params);

    JsonResult<Boolean> deleteById(T params);
}
