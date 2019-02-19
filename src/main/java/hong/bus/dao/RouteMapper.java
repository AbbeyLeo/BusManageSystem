package hong.bus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import hong.bus.pojo.Route;
import hong.bus.pojo.RouteKey;

public interface RouteMapper {
    int deleteByPrimaryKey(RouteKey key);

    int insert(Route record);

    int insertSelective(Route record);

    Route selectByPrimaryKey(RouteKey key);

    int updateByPrimaryKeySelective(Route record);

    int updateByPrimaryKey(Route record);
    
    
    int checkRouteCode(@Param("code") String code);
    
    
    /**
     * 逻辑删除
     * @param record
     * @return
     */
    int deleteLogicalByPrimaryKey(Route record);
    
    /**
     * 模糊查询
     * @param searchConditions 拼凑sql后面的where
     * @return
     */
    List<Route> selectAnywhereByConditions(@Param("searchConditions") String searchConditions);
    /**
     * 在本表实现单个模糊查询
     * @param searchCondition
     * @param searchValue
     * @return
     */
    List<Route> selectAnywhereByCondition(@Param("searchCondition") String searchCondition, @Param("searchValue") String searchValue);
    
    
    List<Route> selectRouteByStationName(@Param("searchValues") String searchValues);
}