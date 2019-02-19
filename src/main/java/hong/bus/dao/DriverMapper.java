package hong.bus.dao;

import hong.bus.pojo.Driver;
import hong.bus.pojo.DriverKey;

public interface DriverMapper {
    int deleteByPrimaryKey(DriverKey key);

    int insert(Driver record);

    int insertSelective(Driver record);

    Driver selectByPrimaryKey(DriverKey key);

    int updateByPrimaryKeySelective(Driver record);

    int updateByPrimaryKey(Driver record);
}