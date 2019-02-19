package hong.bus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import hong.bus.pojo.Station;
import hong.bus.pojo.StationKey;

public interface StationMapper {
    int deleteByPrimaryKey(StationKey key);

    int insert(Station record);

    int insertSelective(Station record);

    Station selectByPrimaryKey(StationKey key);

    int updateByPrimaryKeySelective(Station record);

    int updateByPrimaryKey(Station record);
    
    /**
     * 检查车站编号是否重复
     * @param code
     * @return
     */
    int checkStationCode(@Param("code")String code);
    
    /**
     * 逻辑删除
     * @param record
     * @return
     */
    int deleteLogicalByPrimaryKey(Station record);
    
    /**
     * 模糊查询
     * @param conditions
     * @param values1
     * @return
     */
    List<Station> selectAnywhereByConditions(@Param("searchConditions") String conditions, @Param("searchValues") String searchValues);
    /**
     * 在本表实现station_code模糊查询
     * @param conditions
     * @param values1
     * @return
     */
    List<Station> selectAnywhereById(@Param("searchConditions") String conditions, @Param("searchValues") String searchValues);
    
    /**
     * 通过名字查询站点
     * @param name
     * @return
     */
    List<Station> selectStationByName(@Param("name") String name);
}