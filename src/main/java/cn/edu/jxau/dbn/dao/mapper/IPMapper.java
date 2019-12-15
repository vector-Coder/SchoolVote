package cn.edu.jxau.dbn.dao.mapper;

import cn.edu.jxau.dbn.pojo.model.IP;
import cn.edu.jxau.dbn.pojo.model.Teacher;
import cn.edu.jxau.dbn.pojo.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Vector
 * @Date 2019/11/18
 * @Desc ...
 * @Since 1.0.0
 */
public interface IPMapper{

    IP findById(@Param("ipId") String ipId);

    Integer updateById(IP ip);

    Integer insertNewData(IP ip);

    Integer deleteById(@Param("ipId")Integer ipId);
}
