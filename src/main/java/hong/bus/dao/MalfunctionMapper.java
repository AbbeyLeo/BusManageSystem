package hong.bus.dao;

import hong.bus.pojo.Malfunction;
import hong.bus.pojo.MalfunctionKey;

public interface MalfunctionMapper {
    int deleteByPrimaryKey(MalfunctionKey key);

    int insert(Malfunction record);

    int insertSelective(Malfunction record);

    Malfunction selectByPrimaryKey(MalfunctionKey key);

    int updateByPrimaryKeySelective(Malfunction record);

    int updateByPrimaryKey(Malfunction record);
}