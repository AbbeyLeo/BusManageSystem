package hong.bus.dao;

import hong.bus.pojo.Weight;
import hong.bus.pojo.WeightKey;

public interface WeightMapper {
    int deleteByPrimaryKey(WeightKey key);

    int insert(Weight record);

    int insertSelective(Weight record);

    Weight selectByPrimaryKey(WeightKey key);

    int updateByPrimaryKeySelective(Weight record);

    int updateByPrimaryKey(Weight record);
}