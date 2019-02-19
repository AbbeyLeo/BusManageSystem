package hong.bus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import hong.bus.pojo.Bus;
import hong.bus.pojo.BusKey;

public interface BusMapper {
    int deleteByPrimaryKey(BusKey key);

    int insert(Bus record);

    int insertSelective(Bus record);

    Bus selectByPrimaryKey(BusKey key);

    int updateByPrimaryKeySelective(Bus record);

    int updateByPrimaryKey(Bus record);
    
    /**
     * 检查车牌号是否重复
     * @param plateNumber 车牌号
     * @return
     */
    int checkPlateNumber(@Param("plateNumber")String plateNumber);
    
    /**
     * 检查总线ID是否重复
     * @param plateNumber 总线ID
     * @return
     */
    int checkCANId(@Param("CANId")String CANId);
    /**
     * 逻辑删除
     * @param record
     * @return
     */
    int deleteLogicalByPrimaryKey(Bus record);
    
    /**
     * 根据route联合routes表实现条件模糊查询
     * @param conditions
     * @param values1
     * @return
     */
    List<Bus> selectAnywhereByConditions(@Param("conditions") String conditions, @Param("values1") String values1);
    /**
     * 在本表实现模糊查询
     * @param conditions
     * @param values1
     * @return
     */
    List<Bus> selectAnywhereById(@Param("searchConditions") String conditions, @Param("searchValues") String values1);
}
