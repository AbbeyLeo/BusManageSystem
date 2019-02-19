/**
 * 
 */
package hong.bus.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import hong.bus.common.ServerResponse;
import hong.bus.pojo.Station;

/**
 * @author Abbey
 *
 */
public interface IStationService {
	ServerResponse<String> insertStation(Station station, HttpSession session);

	ServerResponse<String> deleteStation(Station station, HttpSession session);

	ServerResponse<Integer> deleteStations(List<Station> stations, HttpSession session);

	ServerResponse<Integer> updateStations(List<Station> stations, HttpSession session);

	ServerResponse<List<Station>> selectStationsByStationCode(String values, HttpSession session);

	ServerResponse<List<Station>> selectStationsByStationConditions(String values, HttpSession session);
}
