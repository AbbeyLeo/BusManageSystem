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
import hong.bus.dao.RouteMapper;
import hong.bus.dao.StationMapper;
import hong.bus.pojo.Route;
import hong.bus.pojo.Station;
import hong.bus.pojo.User;
import hong.bus.service.IRouteService;
import hong.bus.util.SystemCommonUtil;

/**
 * @author Abbey
 *
 */
@Service("iRouteService")
public class RouteServiceImpl implements IRouteService {

	@Autowired
	private RouteMapper routeMapper;
	
	@Autowired
	private StationMapper stationMapper;
	
	@Override
	public ServerResponse<String> insertRoute(Route route, HttpSession session) {
		if(null == session.getAttribute(Const.CURRENT_USER))
			return ServerResponse.createResponse("请先登录！", ResponseCode.NEED_LOGIN.getCode());//返回需要登录的代码
		User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
		if(route == null)
			return ServerResponse.createByErrorMessage("数值为空！");
//		自动生成station_code的代码
//		int id_length = 6;
//		IdTool.getIdStringByNum(id_length);
		if(StringUtil.isEmpty(route.getRouteCode()))
			return ServerResponse.createByErrorMessage("路线编码为空！");
		int checkRouteCode = routeMapper.checkRouteCode(route.getRouteCode());
		if(checkRouteCode > 0)
			return ServerResponse.createByErrorMessage("路线编码已存在！");
		if(StringUtil.isEmpty(route.getRouteDetail()))
			return ServerResponse.createByErrorMessage("路线详情为空！");
		//将json路线转化为List
		List<Station> stationsList = SystemCommonUtil.jsonStringToList(route.getRouteDetail(), Station.class);
		if(stationsList == null || stationsList.isEmpty() || stationsList.size() == 0) {
			return ServerResponse.createByErrorMessage("路线详情为空！");
		}
		
		if(StringUtil.isEmpty(stationsList.get(0).getStationCode())) {
			return ServerResponse.createByErrorMessage("路线编码为空！");
		}
		StringBuffer sbfRouteDetail = new StringBuffer(stationsList.get(0).getStationCode());
		for(int i = 1; i < stationsList.size(); i++) {
			if(StringUtil.isEmpty(stationsList.get(0).getStationCode())) {
				return ServerResponse.createByErrorMessage("路线编码为空！");
			}
			int checkStation = stationMapper.checkStationCode(stationsList.get(i).getStationCode());
			if(checkStation <= 0)
				return ServerResponse.createByErrorMessage("抱歉，服务器查询不到 '" + stationsList.get(i).getStationCode() +"'这个站点");
			sbfRouteDetail.append("-" + stationsList.get(i).getStationCode());
		}
		route.setRouteDetail(sbfRouteDetail.toString());
		route.setCreator(currentUser.getUserId());
		if(StringUtil.isEmpty(route.getCreateTime()))
			route.setCreateTime(SystemCommonUtil.getServerDate());
		route.setTs(SystemCommonUtil.getServerDate());
		route.setDr(0);
		route.setPkId(null);
		route.setModifier(null);
		route.setModifyTime(null);
		int insertCount = routeMapper.insertSelective(route);
		if(insertCount <= 0)
			return ServerResponse.createByErrorMessage("未知错误，添加失败");
		return ServerResponse.createBySuccessMessage("添加成功！");
	}

	@Override
	public ServerResponse<String> deleteRoute(Route route, HttpSession session) {
		// TODO Auto-generated method stub
		if(null == session.getAttribute(Const.CURRENT_USER))
			return ServerResponse.createResponse("请先登录！", ResponseCode.NEED_LOGIN.getCode());
		
		if(route == null || StringUtil.isEmpty(route.getRouteCode()))
			return ServerResponse.createByErrorMessage("删除id空值错误，删除失败！");
		int deleteCount = routeMapper.deleteLogicalByPrimaryKey(route);
		if(deleteCount <= 0)
			return ServerResponse.createByErrorMessage("未知错误，删除失败！");
		return ServerResponse.createBySuccessMessage("删除成功");
	}

	@Override
	public ServerResponse<Integer> deleteRoutes(List<Route> routes, HttpSession session) {
		// TODO Auto-generated method stub
		if(null == session.getAttribute(Const.CURRENT_USER))
			return ServerResponse.createResponse("请先登录！", ResponseCode.NEED_LOGIN.getCode());
		if(routes == null || routes.isEmpty())
			return ServerResponse.createByErrorMessage("删除空值错误，删除失败！");
		int deleteSuccessCount = 0;
		for(int i = 0; i < routes.size(); i++ ) {
			Route route = routes.get(i);
			if(route == null || StringUtil.isEmpty(route.getRouteCode()))
				continue;
			int deleteCount = routeMapper.deleteLogicalByPrimaryKey(route);
//			if(deleteCount <= 0)
//				return ServerResponse.createByErrorMessage("未知错误，删除失败！");
			if(deleteCount > 0)
				deleteSuccessCount += deleteCount;	//记录删除成功的条数
		}
		return ServerResponse.createBySuccess("删除成功，共删除'" + deleteSuccessCount + "'条记录。", deleteSuccessCount);
	}

	@Override
	public ServerResponse<Integer> updateRoutes(List<Route> routes, HttpSession session) {
		// TODO Auto-generated method stub
		if(session.getAttribute(Const.CURRENT_USER) == null)
			return ServerResponse.createResponse("请先登录！", ResponseCode.NEED_LOGIN.getCode());
		User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
		//判断是否空
		if(routes == null || routes.isEmpty())
			return ServerResponse.createByErrorMessage("空值错误！更新失败！");
		int totalUpdate = 0;
		for(int i = 0; i < routes.size(); i++) {
			Route route = routes.get(i);
			route.setDr(null);
			route.setTs(null);
			route.setModifier(currentUser.getUserId());
			route.setModifyTime(SystemCommonUtil.getServerDate());
			int countUpdate = routeMapper.updateByPrimaryKeySelective(route);
			if(countUpdate > 0)
				totalUpdate += countUpdate;
		}
		return ServerResponse.createBySuccess("修改成功，成功'" + totalUpdate + "'条记录。", totalUpdate);
	}

	@Override
	public ServerResponse<List<Route>> selectRoutesByRouteCode(String code, HttpSession session) {
		// TODO Auto-generated method stub
		if(null == session.getAttribute(Const.CURRENT_USER))
			return ServerResponse.createResponse("请先登录！", ResponseCode.NEED_LOGIN.getCode());//返回需要登录的代码
		if(StringUtil.isEmpty(code))
			return  ServerResponse.createByErrorMessage("参数为空，查询失败！");
		List<Route> routesList = routeMapper.selectAnywhereByCondition("route_code", code);
		if(routesList == null || routesList.isEmpty() || routesList.size() == 0)
			return ServerResponse.createResponse("没有查询到相匹配的路线！", ResponseCode.WARNING.getCode());
		return ServerResponse.createBySuccess("查询成功", routesList);
	}

	@Override
	public ServerResponse<List<Route>> selectRoutesByConditions(String searchConditions, HttpSession session) {
		// TODO Auto-generated method stub
		if(null == session.getAttribute(Const.CURRENT_USER))
			return ServerResponse.createResponse("请先登录！", ResponseCode.NEED_LOGIN.getCode());//返回需要登录的代码
		if(StringUtil.isEmpty(searchConditions))
			return  ServerResponse.createByErrorMessage("参数为空，查询失败！");
		List<Route> routesList = routeMapper.selectAnywhereByConditions(searchConditions);
		if(routesList == null || routesList.isEmpty() || routesList.size() == 0)
			return ServerResponse.createResponse("没有查询到相匹配的路线！", ResponseCode.WARNING.getCode());
		return ServerResponse.createBySuccess("查询成功", routesList);
	}

	@Override
	public ServerResponse<List<Route>> selectRoutesByStationName(String name, HttpSession session) {
		// TODO Auto-generated method stub
		if(null == session.getAttribute(Const.CURRENT_USER))
			return ServerResponse.createResponse("请先登录！", ResponseCode.NEED_LOGIN.getCode());//返回需要登录的代码
		if(StringUtil.isEmpty(name))
			return  ServerResponse.createByErrorMessage("参数为空，查询失败！");
		List<Station> stationsList = stationMapper.selectStationByName(name);
		if(stationsList == null || stationsList.isEmpty() || stationsList.size() == 0)
			return ServerResponse.createResponse("该站点不存在！", ResponseCode.WARNING.getCode());
		StringBuffer sbfStationCode = new StringBuffer(stationsList.get(0).getStationCode());
		for(int i = 1; i < stationsList.size(); i++) {
			sbfStationCode.append("|" + stationsList.get(i).getStationCode());
		}
		List<Route> routesList = routeMapper.selectRouteByStationName(sbfStationCode.toString());
		if(routesList == null || routesList.isEmpty() || routesList.size() == 0)
			return ServerResponse.createResponse("该站点没有相匹配的路线！", ResponseCode.WARNING.getCode());
		return ServerResponse.createBySuccess("查询成功", routesList);
	}
	
}
