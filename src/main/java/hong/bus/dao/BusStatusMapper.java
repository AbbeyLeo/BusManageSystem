package hong.bus.dao;

import hong.bus.pojo.BusStatus;
import hong.bus.pojo.BusStatusKey;

public interface BusStatusMapper {
    int deleteByPrimaryKey(BusStatusKey key);

    int insert(BusStatus record);

    int insertSelective(BusStatus record);

    BusStatus selectByPrimaryKey(BusStatusKey key);

    int updateByPrimaryKeySelective(BusStatus record);

    int updateByPrimaryKey(BusStatus record);
}