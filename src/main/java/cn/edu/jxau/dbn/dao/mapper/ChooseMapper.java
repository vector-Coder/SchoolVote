package cn.edu.jxau.dbn.dao.mapper;

import cn.edu.jxau.dbn.pojo.model.Choose;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Vector
 * @Date 2019/11/18
 * @Desc ...
 * @Since 1.0.0
 */
public interface ChooseMapper {

    Choose findById(Choose Choose);

    Integer insertNewData(Choose Choose);

    Integer deleteById(Choose Choose);
}
