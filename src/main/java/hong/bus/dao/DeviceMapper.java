package hong.bus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import hong.bus.pojo.Device;
import hong.bus.pojo.Route;

public interface DeviceMapper {
    int deleteByPrimaryKey(Long pkId);

    int insert(Device record);

    int insertSelective(Device record);

    Device selectByPrimaryKey(Long pkId);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKey(Device record);
    
    int checkDevice(@Param("code") String code);
    
    /**
     * 逻辑删除
     * @param record
     * @return
     */
    int deleteLogicalByPrimaryKey(Device record);
    
    /**
     * 模糊查询
     * @param searchConditions 拼凑sql后面的where
     * @return
     */
    List<Device> selectAnywhereByConditions(@Param("searchConditions") String searchConditions);
   
    List<Device> selectByBusId(@Param("busId") String busId);
    
    List<Device> selectByName(@Param("deviceName") String deviceName);
    
    /**
     * 在本表实现单个模糊查询
     * @param searchCondition
     * @param searchValue
     * @return
     */
    List<Device> selectAnywhereByCondition(@Param("searchCondition") String searchCondition, @Param("searchValue") String searchValue);
    
    
}