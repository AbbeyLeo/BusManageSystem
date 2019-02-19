/**
 * 
 */
package hong.bus.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.StringUtil;

import hong.bus.common.Const;
import hong.bus.common.ServerResponse;
import hong.bus.common.constant.ResponseCode;
import hong.bus.dao.BusMapper;
import hong.bus.pojo.Bus;
import hong.bus.pojo.User;
import hong.bus.service.IBusService;
import hong.bus.util.IdTool;
import hong.bus.util.SystemCommonUtil;

/**
 * @author Abbey
 *
 */
@Service("iBusService")
public class BusServiceImpl implements IBusService {
	
	@Autowired
	private BusMapper busMapper;
	/**
	 * 公交车添加
	 * @param bus	公交车类
	 * @param session  当前服务器session 
	 */
	@Override
	public ServerResponse<String> insertBus(Bus bus, HttpSession session){
		if(null == session.getAttribute(Const.CURRENT_USER))
			return ServerResponse.createResponse("请先登录！", ResponseCode.NEED_LOGIN.getCode());//返回需要登录的代码
		User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
		if(bus == null)
			return ServerResponse.createByErrorMessage("数值为空！");
		if(StringUtil.isEmpty(bus.getCanId()))
			return ServerResponse.createByErrorMessage("必须配置公交车的总线的编号！");
		if(StringUtil.isEmpty(bus.getBusName()))
			return ServerResponse.createByErrorMessage("公交车名字必须填！");
		if(StringUtil.isEmpty(bus.getBusFuel()))
			return ServerResponse.createByErrorMessage("公交车汽油类型必须填！");
		if(StringUtil.isEmpty(bus.getBusProducer()))
			return ServerResponse.createByErrorMessage("公交车的生产商必须填！");
		if(null == bus.getBusSize() || bus.getBusSize() == 0)
			return ServerResponse.createByErrorMessage("公交车的核载规格必须填！");
		if(StringUtil.isEmpty(bus.getBusPlateNumber()))
			return ServerResponse.createByErrorMessage("公交车的车牌号必须填！");
		if(busMapper.checkPlateNumber(bus.getBusPlateNumber()) > 0)
			return ServerResponse.createByErrorMessage("该车牌号已经绑定其它车辆！");
//		if(busMapper.checkCANId(bus.getCanId()) > 0)
//			return ServerResponse.createResponse("提示， 公交车总线已绑定其他车辆！",
//					ResponseCode.WARNING.getCode());
		int id_length = 6;
		bus.setBusId(IdTool.getIdStringByNum(id_length));
		bus.setCreator(currentUser.getUserId());
		bus.setCreateTime(SystemCommonUtil.getServerDate());
		bus.setTs(SystemCommonUtil.getServerDate());
		bus.setPkId(null);
		bus.setDr(0);
		bus.setModifier(null);
		bus.setModifyTime(null);
		int insertCount = busMapper.insert(bus);
		if(insertCount <= 0)
			return ServerResponse.createByErrorMessage("未知错误，添加失败");
		return ServerResponse.createBySuccessMessage("添加成功！");
	}
	
	/**
	 * 单独删除
	 * @param bus 想要删除的bus类
	 * @param session 当前登录session
	 * @return
	 */
	@Override
	public ServerResponse<String> deleteBus(Bus bus, HttpSession session){
		if(null == session.getAttribute(Const.CURRENT_USER))
			return ServerResponse.createResponse("请先登录！", ResponseCode.NEED_LOGIN.getCode());
		
		if(bus == null || StringUtil.isEmpty(bus.getBusId()))
			return ServerResponse.createByErrorMessage("删除id空值错误，删除失败！");
		int deleteCount = busMapper.deleteLogicalByPrimaryKey(bus);
		if(deleteCount <= 0)
			return ServerResponse.createByErrorMessage("未知错误，删除失败！");
		return ServerResponse.createBySuccessMessage("删除成功");
	}
	
	/**
	 * 批量删除
	 * @param buses 公交车类的ArrayList
	 * @param session 当前登录session
	 * @return
	 */
	@Override
	public ServerResponse<Integer> deleteBuses(List<Bus> buses, HttpSession session){
		if(null == session.getAttribute(Const.CURRENT_USER))
			return ServerResponse.createResponse("请先登录！", ResponseCode.NEED_LOGIN.getCode());
		if(buses == null || buses.isEmpty())
			return ServerResponse.createByErrorMessage("删除空值错误，删除失败！");
		int deleteSuccessCount = 0;
		for(int i = 0; i < buses.size(); i++ ) {
			Bus bus = buses.get(i);
			if(bus == null || StringUtil.isEmpty(bus.getBusId()))
				continue;
			int deleteCount = busMapper.deleteLogicalByPrimaryKey(bus);
//			if(deleteCount <= 0)
//				return ServerResponse.createByErrorMessage("未知错误，删除失败！");
			if(deleteCount > 0)
				deleteSuccessCount += deleteCount;	//记录删除成功的条数
		}
		return ServerResponse.createBySuccess("删除成功，共删除'" + deleteSuccessCount + "'条记录。", deleteSuccessCount);
	}
	
	//更新
	/**
	 * 批量修改
	 * @param buses
	 * @param session
	 * @return
	 */
	@Override
	public ServerResponse<Integer> updateBuses(List<Bus> buses, HttpSession session) {
		//判断是否登录
		if(session.getAttribute(Const.CURRENT_USER) == null)
			return ServerResponse.createResponse("请先登录！", ResponseCode.NEED_LOGIN.getCode());
		//判断是否空
		if(buses == null || buses.isEmpty())
			return ServerResponse.createByErrorMessage("空值错误！更新失败！");
		int totalUpdate = 0;
		for(int i = 0; i < buses.size(); i++) {
			Bus bus = buses.get(i);
			bus.setDr(null);
			bus.setTs(null);
			int countUpdate = busMapper.updateByPrimaryKeySelective(bus);
			if(countUpdate > 0)
				totalUpdate += countUpdate;
		}
		return ServerResponse.createBySuccess("修改成功，成功'" + totalUpdate + "'条记录。", totalUpdate);
	}
	
	/**
	 * 根据BusId进行模糊查询
	 * @param values	查询值
	 * @param session
	 * @return
	 */
	@Override
	public ServerResponse<List<Bus>> selectBusesByBusId(String values, HttpSession session) {
		if(session.getAttribute(Const.CURRENT_USER) == null)
			return ServerResponse.createResponse("请先登录！", ResponseCode.NEED_LOGIN.getCode());
		if(StringUtil.isEmpty(values))
		{
			return ServerResponse.createByErrorMessage("空值错误！查询失败！");
		}
 		List<Bus> buses = busMapper.selectAnywhereById("bus_id", values);
		return ServerResponse.createBySuccess(buses);
	}
	
	/**
	 * 根据公交车路线编号或者公交车路线查询
	 * @param values
	 * @param session
	 * @return
	 */
	@Override
	public ServerResponse<List<Bus>> selectBusesByBusRoutes(String values, HttpSession session) {
		if(session.getAttribute(Const.CURRENT_USER) == null)
			return ServerResponse.createResponse("请先登录！", ResponseCode.NEED_LOGIN.getCode());
		if(StringUtil.isEmpty(values))
		{
			return ServerResponse.createByErrorMessage("空值错误！查询失败！");
		}
		StringBuffer sbf = new StringBuffer();
		sbf.append("route_code like '%");
		sbf.append(values);
		sbf.append("%' or route_detail");
		List<Bus> buses = busMapper.selectAnywhereByConditions(sbf.toString(), values);
		return ServerResponse.createBySuccess(buses);
	}
}
