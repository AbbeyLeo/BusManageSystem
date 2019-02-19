/**
 * 
 */
package hong.bus.controller.portal;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hong.bus.common.ServerResponse;
import hong.bus.pojo.Route;
import hong.bus.pojo.Station;
import hong.bus.service.IRouteService;

/**
 * @author Abbey
 *
 */
@Controller
@RequestMapping("/route/")
public class RouteController {

	@Autowired
	private IRouteService iRouteService;
	
	@RequestMapping(value = "insert_route.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<String> insertRoute(Route route,HttpSession session){
		ServerResponse<String> response = iRouteService.insertRoute(route, session);
		return response;
	}
	
	@RequestMapping(value = "search_route_by_stationName.do", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse<List<Route>> selectRouteByStationName(String stationName,HttpSession session){
		ServerResponse<List<Route>> response = iRouteService.selectRoutesByStationName(stationName, session);
		return response;
	}
}
