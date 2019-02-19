/**
 * 
 */
package hong.bus.controller.portal;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.StringUtil;

import hong.bus.common.Const;
import hong.bus.common.ServerResponse;
import hong.bus.pojo.Bus;
import hong.bus.pojo.Station;
import hong.bus.service.IBusService;
import hong.bus.service.IStationService;

/**
 * @author Abbey
 *
 */
@Controller
@RequestMapping("/station/")
public class StationController {

	@Autowired
	private IStationService iStationService;
	
	/**
	 * 添加公交车
	 * @param bus
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "insert_station.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<String> insertBus(Station station,HttpSession session){
		ServerResponse<String> response = iStationService.insertStation(station, session);
		return response;
	}
	
	@RequestMapping(value = "update_stations.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<Integer> updateBuses(HttpSession session, String stations) throws UnsupportedEncodingException {
		//System.out.println("当前sessionId  =>  " + session.getId());
		if(StringUtil.isEmpty(stations)) {
			return ServerResponse.createByErrorMessage("数据为空，请选择要删除的数据！");
		}
		//----将json转成list
		String temp = URLDecoder.decode(stations, "UTF-8");
		String jsonString = JSON.toJSONString(temp);
		List<Station> stationsList = JSON.parseArray(temp, Station.class);
		if(stationsList == null || stationsList.isEmpty() || stationsList.size() == 0) {
			return ServerResponse.createByErrorMessage("数据为空，请选择要删除的数据！");
		}
		//----将json转成list结束
		ServerResponse<Integer> response = iStationService.updateStations(stationsList, session);
		return response;
		
	}
	
	/**
	 * 模糊查询站点编码
	 * @param stationCode
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "search_station_code.do", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse<List<Station>> searchStationByCode(String stationCode,HttpSession session){
		ServerResponse<List<Station>> response = iStationService.selectStationsByStationCode(stationCode, session);
		return response;
	}
	
	/**
	 * 模糊查询站点名字
	 * @param stationName
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "search_station_name.do", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse<List<Station>> searchStationByName(String stationName,HttpSession session){
		ServerResponse<List<Station>> response = iStationService.selectStationsByStationConditions(stationName, session);
		return response;
	}
	
	
	@RequestMapping(value = "delete_station.do", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse<String> deleteBus(Station station,HttpSession session){
		ServerResponse<String> response = iStationService.deleteStation(station, session);
		return response;
	}
	
	@RequestMapping(value = "delete_stations.do", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse<Integer> deleteBuses(String stations,HttpSession session) throws UnsupportedEncodingException{
		//----将json转成list
		if(StringUtil.isEmpty(stations)) {
			return ServerResponse.createByErrorMessage("数据为空，请选择要删除的数据！");
		}
		String temp = URLDecoder.decode(stations, "UTF-8");
		String jsonString = JSON.toJSONString(temp);
		List<Station> stationsList = JSON.parseArray(temp, Station.class);
		if(stationsList == null || stationsList.isEmpty() || stationsList.size() == 0) {
			return ServerResponse.createByErrorMessage("数据为空，请选择要删除的数据！");
		}
		//----将json转成list结束
		ServerResponse<Integer> response = iStationService.deleteStations(stationsList, session);
		return response;
	}
	
}
