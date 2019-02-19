package hong.bus.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import hong.bus.common.ServerResponse;
import hong.bus.pojo.Device;
import hong.bus.pojo.Route;

public interface IDeviceService {
	ServerResponse<Integer> insertDevice(Device device, String isPassDeviceUpdate, HttpSession session);

	ServerResponse<String> deleteDevice(Device device, HttpSession session);

	ServerResponse<Integer> deleteDevices(List<Device> devices, HttpSession session);

	ServerResponse<Integer> updateDevices(List<Device> devices, HttpSession session);
	
	ServerResponse<Device> selectDevicesByDeviceId(String deviceId, HttpSession session);

	ServerResponse<List<Device>> selectDevicesByBusId(String busId, HttpSession session);

	ServerResponse<List<Device>> selectDevicesByDeviceName(String deviceName, HttpSession session);
	
	ServerResponse<List<Device>> selectDevicesByCondition(String condition, String values, HttpSession session);
}
