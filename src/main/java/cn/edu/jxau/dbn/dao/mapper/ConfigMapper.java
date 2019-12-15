package cn.edu.jxau.dbn.dao.mapper;

import cn.edu.jxau.dbn.pojo.model.Config;
import cn.edu.jxau.dbn.pojo.model.IP;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Vector
 * @Date 2019/11/21
 * @Desc ...
 * @Since 1.0.0
 */
public interface ConfigMapper {

    Config findById(@Param("configId") Integer configId);

    Integer updateById(Config ip);
}
