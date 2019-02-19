/**
 * 
 */
package hong.bus.controller.portal;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.StringUtil;

import hong.bus.common.Const;
import hong.bus.common.ServerResponse;
import hong.bus.pojo.Bus;
import hong.bus.service.IBusService;


/**
 * @author Abbey
 *
 */
@Controller
@RequestMapping("/bus/")
public class BusController {

	@Autowired
	private IBusService iBusService;
	
	/**
	 * 添加公交车
	 * @param bus
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "insertBus.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<String> insertBus(Bus bus,HttpSession session){
		ServerResponse<String> response = iBusService.insertBus(bus, session);
		return response;
	}
	
	@RequestMapping(value = "update_buses.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<Integer> updateBuses(HttpSession session, String buses) throws UnsupportedEncodingException {
		//System.out.println("当前sessionId  =>  " + session.getId());
		if(StringUtil.isEmpty(buses)) {
			return ServerResponse.createByErrorMessage("数据为空，请选择要删除的数据！");
		}
		//----将json转成list
		String temp = URLDecoder.decode(buses, "UTF-8");
		String jsonString = JSON.toJSONString(temp);
		List<Bus> busesList = JSON.parseArray(temp, Bus.class);
		if(busesList == null || busesList.isEmpty() || busesList.size() == 0) {
			return ServerResponse.createByErrorMessage("数据为空，请选择要删除的数据！");
		}
		//----将json转成list结束
		ServerResponse<Integer> response = iBusService.updateBuses(busesList, session);
		return response;
		
	}
	
	/**
	 * 根据BusId查询公交车
	 * @param busId
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "search_buses_id.do", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse<List<Bus>> searchBusesByBusId(String busId,HttpSession session){
		ServerResponse<List<Bus>> response = iBusService.selectBusesByBusId(busId, session);
		return response;
	}
	
	/**
	 * 根据路线查询公交车
	 * @param busId
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "search_buses_route.do", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse<List<Bus>> searchBusesByRoute(String route,HttpSession session){
		ServerResponse<List<Bus>> response = iBusService.selectBusesByBusRoutes(route, session);
		return response;
	}
	
	@RequestMapping(value = "delete_bus.do", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse<String> deleteBus(Bus bus,HttpSession session){
		ServerResponse<String> response = iBusService.deleteBus(bus, session);
		return response;
	}
	
	@RequestMapping(value = "delete_buses.do", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse<Integer> deleteBuses(String buses,HttpSession session) throws UnsupportedEncodingException{
		//----将json转成list
		if(StringUtil.isEmpty(buses)) {
			return ServerResponse.createByErrorMessage("数据为空，请选择要删除的数据！");
		}
		String temp = URLDecoder.decode(buses, "UTF-8");
		String jsonString = JSON.toJSONString(temp);
		List<Bus> busesList = JSON.parseArray(temp, Bus.class);
		if(busesList == null || busesList.isEmpty() || busesList.size() == 0) {
			return ServerResponse.createByErrorMessage("数据为空，请选择要删除的数据！");
		}
		//----将json转成list结束
		ServerResponse<Integer> response = iBusService.deleteBuses(busesList, session);
		return response;
	}
}
