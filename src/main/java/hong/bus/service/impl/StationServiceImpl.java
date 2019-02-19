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
import hong.bus.dao.StationMapper;
import hong.bus.pojo.Bus;
import hong.bus.pojo.Station;
import hong.bus.pojo.User;
import hong.bus.service.IStationService;
import hong.bus.util.IdTool;
import hong.bus.util.SystemCommonUtil;

/**
 * @author Abbey
 *
 */
@Service("iStationService")
public class StationServiceImpl implements IStationService {

	@Autowired
	private StationMapper stationMapper;
	
	@Override
	public ServerResponse<String> insertStation(Station station, HttpSession session) {
		// TODO Auto-generated method stub
		if(null == session.getAttribute(Const.CURRENT_USER))
			return ServerResponse.createResponse("请先登录！", ResponseCode.NEED_LOGIN.getCode());//返回需要登录的代码
		User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
		if(station == null)
			return ServerResponse.createByErrorMessage("数值为空！");
//		自动生成station_code的代码
//		int id_length = 6;
//		IdTool.getIdStringByNum(id_length);
		if(StringUtil.isEmpty(station.getStationCode()))
			return ServerResponse.createByErrorMessage("车站编码数值为空！");
		
		int checkCode = stationMapper.checkStationCode(station.getStationCode());
		if(StringUtil.isEmpty(station.getStationName()))
			return ServerResponse.createByErrorMessage("车站名称数值为空！");
		boolean isChinese = SystemCommonUtil.isGBK(station.getStationCode());	//是否中文
		boolean isPunctuation = SystemCommonUtil.isPunctuation(station.getStationCode());	//是否标点
		if(isChinese || isPunctuation)
			return ServerResponse.createByErrorMessage("编码含有中文或者标点符号!");
		station.setTs(SystemCommonUtil.getServerDate());
		if(StringUtil.isEmpty(station.getCreateTime()))
			station.setCreateTime(SystemCommonUtil.getServerDate());
		station.setCreator(currentUser.getUserId());
		station.setPkId(null);
		station.setDr(0);
		int insertCount = stationMapper.insertSelective(station);
		if(insertCount <= 0)
			return ServerResponse.createByErrorMessage("未知错误，添加失败");
		return ServerResponse.createBySuccessMessage("添加成功！");
	}

	@Override
	public ServerResponse<String> deleteStation(Station station, HttpSession session) {
		if(null == session.getAttribute(Const.CURRENT_USER))
			return ServerResponse.createResponse("请先登录！", ResponseCode.NEED_LOGIN.getCode());
		
		if(station == null || StringUtil.isEmpty(station.getStationCode()))
			return ServerResponse.createByErrorMessage("删除id空值错误，删除失败！");
		int deleteCount = stationMapper.deleteLogicalByPrimaryKey(station);
		if(deleteCount <= 0)
			return ServerResponse.createByErrorMessage("未知错误，删除失败！");
		return ServerResponse.createBySuccessMessage("删除成功");
	}

	@Override
	public ServerResponse<Integer> deleteStations(List<Station> stations, HttpSession session) {
		// TODO Auto-generated method stub
		if(null == session.getAttribute(Const.CURRENT_USER))
			return ServerResponse.createResponse("请先登录！", ResponseCode.NEED_LOGIN.getCode());
		if(stations == null || stations.isEmpty())
			return ServerResponse.createByErrorMessage("删除空值错误，删除失败！");
		int deleteSuccessCount = 0;
		for(int i = 0; i < stations.size(); i++ ) {
			Station station = stations.get(i);
			if(station == null || StringUtil.isEmpty(station.getStationCode()))
				continue;
			int deleteCount = stationMapper.deleteLogicalByPrimaryKey(station);
//			if(deleteCount <= 0)
//				return ServerResponse.createByErrorMessage("未知错误，删除失败！");
			if(deleteCount > 0)
				deleteSuccessCount += deleteCount;	//记录删除成功的条数
		}
		return ServerResponse.createBySuccess("删除成功，共删除'" + deleteSuccessCount + "'条记录。", deleteSuccessCount);
	}

	@Override
	public ServerResponse<Integer> updateStations(List<Station> stations, HttpSession session) {
		// TODO Auto-generated method stub
		//判断是否登录
		if(session.getAttribute(Const.CURRENT_USER) == null)
			return ServerResponse.createResponse("请先登录！", ResponseCode.NEED_LOGIN.getCode());
		User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
		//判断是否空
		if(stations == null || stations.isEmpty())
			return ServerResponse.createByErrorMessage("空值错误！更新失败！");
		int totalUpdate = 0;
		for(int i = 0; i < stations.size(); i++) {
			Station station = stations.get(i);
			station.setDr(null);
			station.setTs(null);
			station.setModifier(currentUser.getUserId());
			station.setModifyTime(SystemCommonUtil.getServerDate());
			int countUpdate = stationMapper.updateByPrimaryKeySelective(station);
			if(countUpdate > 0)
				totalUpdate += countUpdate;
		}
		return ServerResponse.createBySuccess("修改成功，成功'" + totalUpdate + "'条记录。", totalUpdate);
	}

	@Override
	public ServerResponse<List<Station>> selectStationsByStationCode(String values, HttpSession session) {
		// TODO Auto-generated method stub
		if(session.getAttribute(Const.CURRENT_USER) == null)
			return ServerResponse.createResponse("请先登录！", ResponseCode.NEED_LOGIN.getCode());
		if(StringUtil.isEmpty(values))
		{
			return ServerResponse.createByErrorMessage("空值错误！查询失败！");
		}
 		List<Station> stations = stationMapper.selectAnywhereById("station_code", values);
		return ServerResponse.createBySuccess(stations);
	}

	@Override
	public ServerResponse<List<Station>> selectStationsByStationConditions(String values, HttpSession session) {
		// TODO Auto-generated method stub
		if(session.getAttribute(Const.CURRENT_USER) == null)
			return ServerResponse.createResponse("请先登录！", ResponseCode.NEED_LOGIN.getCode());
		if(StringUtil.isEmpty(values))
		{
			return ServerResponse.createByErrorMessage("空值错误！查询失败！");
		}
		StringBuffer sbf = new StringBuffer();
		
 		List<Station> stations = stationMapper.selectAnywhereById("station_name", values);
		return ServerResponse.createBySuccess(stations);
	}

}
