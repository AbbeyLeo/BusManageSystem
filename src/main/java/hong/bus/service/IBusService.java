/**
 * 
 */
package hong.bus.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import hong.bus.common.ServerResponse;
import hong.bus.pojo.Bus;

/**
 * @author Abbey
 *
 */
public interface IBusService {

	ServerResponse<String> insertBus(Bus bus, HttpSession session);

	ServerResponse<String> deleteBus(Bus bus, HttpSession session);
	
	ServerResponse<Integer> deleteBuses(List<Bus> buses, HttpSession session);

	ServerResponse<Integer> updateBuses(List<Bus> buses, HttpSession session);

	ServerResponse<List<Bus>> selectBusesByBusId(String values, HttpSession session);

	ServerResponse<List<Bus>> selectBusesByBusRoutes(String values, HttpSession session);
}
