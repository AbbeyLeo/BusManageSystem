/**
 * 
 */
package hong.bus.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import hong.bus.common.ServerResponse;
import hong.bus.pojo.Route;

/**
 * @author Abbey
 *
 */
public interface IRouteService {
	ServerResponse<String> insertRoute(Route route, HttpSession session);

	ServerResponse<String> deleteRoute(Route route, HttpSession session);

	ServerResponse<Integer> deleteRoutes(List<Route> routes, HttpSession session);

	ServerResponse<Integer> updateRoutes(List<Route> routes, HttpSession session);

	ServerResponse<List<Route>> selectRoutesByRouteCode(String code, HttpSession session);

	ServerResponse<List<Route>> selectRoutesByConditions(String conditionsSql, HttpSession session);
	
	ServerResponse<List<Route>> selectRoutesByStationName(String name, HttpSession session);

}
