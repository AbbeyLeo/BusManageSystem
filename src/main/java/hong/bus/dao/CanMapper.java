package hong.bus.dao;

import hong.bus.pojo.Can;
import hong.bus.pojo.CanKey;

public interface CanMapper {
    int deleteByPrimaryKey(CanKey key);

    int insert(Can record);

    int insertSelective(Can record);

    Can selectByPrimaryKey(CanKey key);

    int updateByPrimaryKeySelective(Can record);

    int updateByPrimaryKey(Can record);
}