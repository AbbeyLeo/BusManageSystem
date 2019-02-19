package hong.bus.dao;

import hong.bus.pojo.Power;
import hong.bus.pojo.PowerKey;

public interface PowerMapper {
    int deleteByPrimaryKey(PowerKey key);

    int insert(Power record);

    int insertSelective(Power record);

    Power selectByPrimaryKey(PowerKey key);

    int updateByPrimaryKeySelective(Power record);

    int updateByPrimaryKey(Power record);
}