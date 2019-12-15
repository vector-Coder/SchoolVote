package cn.edu.jxau.dbn.dao.mapper;

import cn.edu.jxau.dbn.pojo.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Vector
 * @Date 2019/11/18
 * @Desc ...
 * @Since 1.0.0
 */
public interface UserMapper {


    User findById(@Param("userId") String userId);

    Integer updateById( User user);

    Integer insertNewData( User user);

    Integer deleteById(@Param("userId") String userId);
}
