/**
 * 
 */
package hong.bus.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.StringUtil;

import hong.bus.common.Const;
import hong.bus.common.ServerResponse;
import hong.bus.common.constant.ResponseCode;
import hong.bus.dao.DeviceMapper;
import hong.bus.pojo.Device;
import hong.bus.pojo.Station;
import hong.bus.pojo.User;
import hong.bus.service.IDeviceService;
import hong.bus.util.IdTool;
import hong.bus.util.SystemCommonUtil;

/**
 * @author Abbey
 *
 */
@Service("iDeviceService")
public class DeviceServiceImpl implements IDeviceService{
	
	@Autowired
	private DeviceMapper deviceMapper;
	
	@Override
	public ServerResponse<Integer> insertDevice(Device device,String isPassDeviceUpdate, HttpSession session) {
		// TODO Auto-generated method stub
		if(null == session.getAttribute(Const.CURRENT_USER))
			return ServerResponse.createResponse("请先登录！", ResponseCode.NEED_LOGIN.getCode());//返回需要登录的代码
		User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
		if(!StringUtil.isEmpty(isPassDeviceUpdate) && 
				(!isPassDeviceUpdate.equals("T") || !isPassDeviceUpdate.equals("F")) )
			return ServerResponse.createByErrorMessage("参数非法");
		
		StringBuffer sbfResponse = new StringBuffer("添加成功！");
		if(device == null)
			return ServerResponse.createByErrorMessage("数值为空！");
		
		if( !StringUtil.isEmpty(device.getDeviceCode())) {
			int countDevice = deviceMapper.checkDevice(device.getDeviceCode());
			if(countDevice <= 0) {
				device.setDeviceCode(null);
			}
			if(countDevice > 0 && (StringUtil.isEmpty(isPassDeviceUpdate))) 
				return ServerResponse.createResponse("该编号已存在相应的设备，是否更新？", ResponseCode.CHECK_AGAIN.getCode());
			
			if(countDevice > 0 && !StringUtil.isEmpty(isPassDeviceUpdate) && isPassDeviceUpdate.equals("F"))
				return ServerResponse.createByErrorMessage("更新失败，您取消了更新，更新前请确认编号是否存在！");
			if(countDevice > 0 && !StringUtil.isEmpty(isPassDeviceUpdate) && isPassDeviceUpdate.equals("T")) {
				List<Device> devices = new ArrayList<Device>();
				devices.add(device);
				return updateDevices(devices, session);
			}
		}
		
		if(StringUtil.isEmpty(device.getDeviceCode())) {
			//自动生成device_code的代码
			int id_length = 6;
			String code = IdTool.getIdStringByNum(id_length);
			device.setDeviceCode(code);
		}
		
		if(StringUtil.isEmpty(device.getDeviceName()))
			return ServerResponse.createByErrorMessage("设备名为空！");
		
		if(StringUtil.isEmpty(device.getDeviceName()))
			sbfResponse.append("公交车尚未关联！");
		
		device.setCreator(currentUser.getUserId());
		if(StringUtil.isEmpty(device.getCreateTime()))
			device.setCreateTime(SystemCommonUtil.getServerDate());
		device.setTs(SystemCommonUtil.getServerDate());
		device.setDr(0);
		device.setPkId(null);
		device.setModifier(null);
		device.setModify(null);
		int insertCount = deviceMapper.insertSelective(device);
		if(insertCount <= 0)
			return ServerResponse.createByErrorMessage("unfind error!");
		return ServerResponse.createBySuccess("添加成功", 1);
	}

	@Override
	public ServerResponse<String> deleteDevice(Device device, HttpSession session) {
		// TODO Auto-generated method stub
		if(null == session.getAttribute(Const.CURRENT_USER))
			return ServerResponse.createResponse("请先登录！", ResponseCode.NEED_LOGIN.getCode());
		
		if(device == null || StringUtil.isEmpty(device.getDeviceCode()))
			return ServerResponse.createByErrorMessage("删除id空值错误，删除失败！");
		int deleteCount = deviceMapper.deleteLogicalByPrimaryKey(device);
		if(deleteCount <= 0)
			return ServerResponse.createByErrorMessage("未知错误，删除失败！");
		return ServerResponse.createBySuccessMessage("删除成功");
	}

	@Override
	public ServerResponse<Integer> deleteDevices(List<Device> devices, HttpSession session) {
		// TODO Auto-generated method stub
		if(null == session.getAttribute(Const.CURRENT_USER))
			return ServerResponse.createResponse("请先登录！", ResponseCode.NEED_LOGIN.getCode());
		if(devices == null || devices.isEmpty())
			return ServerResponse.createByErrorMessage("删除空值错误，删除失败！");
		int deleteSuccessCount = 0;
		for(int i = 0; i < devices.size(); i++ ) {
			Device device = devices.get(i);
			if(device == null || StringUtil.isEmpty(device.getDeviceCode()))
				continue;
			int deleteCount = deviceMapper.deleteLogicalByPrimaryKey(device);
//			if(deleteCount <= 0)
//				return ServerResponse.createByErrorMessage("未知错误，删除失败！");
			if(deleteCount > 0)
				deleteSuccessCount += deleteCount;	//记录删除成功的条数
		}
			return ServerResponse.createBySuccess("删除成功，共删除'" + deleteSuccessCount + "'条记录。", deleteSuccessCount);
	}

	@Override
	public ServerResponse<Integer> updateDevices(List<Device> devices, HttpSession session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServerResponse<Device> selectDevicesByDeviceId(String deviceId, HttpSession session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServerResponse<List<Device>> selectDevicesByBusId(String busId, HttpSession session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServerResponse<List<Device>> selectDevicesByDeviceName(String deviceName, HttpSession session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServerResponse<List<Device>> selectDevicesByCondition(String condition, String values, HttpSession session) {
		// TODO Auto-generated method stub
		return null;
	}

}
