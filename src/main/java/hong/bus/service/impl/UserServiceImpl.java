/**
 * 用户的service层代理实现
 */
package hong.bus.service.impl;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.StringUtil;

import hong.bus.common.Const;
import hong.bus.common.ServerResponse;
import hong.bus.common.VerifyCache;
import hong.bus.dao.UserMapper;
import hong.bus.pojo.User;
import hong.bus.service.IUserService;
import hong.bus.util.IdTool;
import hong.bus.util.MD5Util;
import hong.bus.util.MsgUtil;
import hong.bus.util.SystemCommonUtil;
import hong.bus.util.UserCommonUtil;

/**
 * @author Abbey
 *
 */

@Service("iUserService")//注入Controller之前，声明Service
public class UserServiceImpl implements IUserService {
	@Autowired	//用以注入Mapper
	private UserMapper userMapper;
	
	/**
	 * 用户密码登录
	 */
	@Override
	public ServerResponse<User> userPwdLogin(String username, String password) {
		// TODO Auto-generated method stub
		//检查用户名是否存在
		int resultNames = userMapper.checkUsername(username);
		if(resultNames == 0) {
			return ServerResponse.createByErrorMessage("用户名不存在！");
		}
		
		//进行登录判断
		String checkpassword = MD5Util.MD5EncodeUtf8(password);
		User user = userMapper.selectLogin(username, checkpassword);
		if(user == null) {
			return ServerResponse.createByErrorMessage("用户名或密码错误");
		}
		
		user = UserCommonUtil.UserInitToUI(user);
		return ServerResponse.createBySuccess("登录成功", user);
	}
	
	/**
	 * 用户手机登录
	 * @param mobileNum 用户的手机号
	 * @param verifyNum 用户填写的验证码
	 */
	@Override
	public ServerResponse<User> userMobileLogin(String mobileNum, String verifyNum) {
		// TODO Auto-generated method stub
		if(null == mobileNum || "".equals(mobileNum) || null == verifyNum || "".equals(verifyNum))
			return ServerResponse.createByErrorMessage("请填写手机号或验证码！");
		//检查手机是否存在
		int resultNames = userMapper.checkMobileNum(mobileNum);
		if(resultNames == 0) {
			return ServerResponse.createByErrorMessage("该手机号尚未注册！");
		}
		
		//进行登录判断
		ServerResponse response = MsgUtil.verifyValid(Const.LOGIN_PREFIX, mobileNum, verifyNum);
		if(!response.isSuccess())
			return response;
		User user = userMapper.selectByMobile(mobileNum);
		UserCommonUtil.UserInitToUI(user);
		return ServerResponse.createBySuccess("登录成功", user);
	}
	/**
	 * 注册/添加  用户
	 * @param user 要添加的用户
	 * @param parentUser 当前登录的用户
	 */
	@Override
	public ServerResponse<String> userRegister(User user, User parentUser){
		int userCount = userMapper.checkUsername(user.getUsername());
		int phoneCount = 0;
		if(user.getMobile() != null)
			phoneCount = userMapper.checkMobileNum(user.getMobile());
		if(userCount > 0 || phoneCount > 0) {
			return ServerResponse.createByErrorMessage("用户已存在");
		}
		//MD5加密
		user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
		//设置User的常规属性
		user.setCreator(parentUser.getUserId()+"");
		user.setCreateTime(SystemCommonUtil.getServerDate());
		user.setDr(0);//设置dr用户的0表示未删除
		user.setUserId(IdTool.createUserId(user.getUserPower()));
		user.setTs(SystemCommonUtil.getServerDate());
		userCount = userMapper.insert(user);
		if(userCount == 0) {
			return ServerResponse.createByErrorMessage("注册失败");
		}
		return ServerResponse.createBySuccessMessage("注册成功");
	}
	/**
	 *根据手机号获取验证码，放进缓存中，区分验证码是什么用图的，并向手机发送短信
	 *@param mobileNum 手机号
	 *@param prefix 获取验证码的使用前缀
	 */
	//
	@Override
	public ServerResponse<String> getVerifyNum(String mobileNum, String prefix){
		if(null == mobileNum || "".equals(mobileNum) || null == prefix || "".equals(prefix))
			return ServerResponse.createByErrorMessage("获取验证码失败，信息为空");
		
		String cacheKey = prefix + mobileNum;
		if(null != VerifyCache.getValueByKey(cacheKey) && VerifyCache.getValueByKey(cacheKey) == Const.ERROR_LOCK){
			return ServerResponse.createByErrorMessage("您尝试登录的次数过多，请5分钟后重试！");
		}
		String verifyNum = MsgUtil.radomVerifyNum();//获取验证码
		VerifyCache.setKeyValue(cacheKey, verifyNum);//将验证码放入缓存中
		//获取配置中的Uid和Key
		String result = null;
		try {
			//result = MsgUtil.sendMessage(mobileNum, prefix, verifyNum);
			result = "1";
		} catch (Exception e) {//IOException
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(cacheKey + "------>" + VerifyCache.getValueByKey(cacheKey));
		if("1".equals(result))
			return ServerResponse.createBySuccessMessage("已向手机发送短信");
		VerifyCache.removeValueByKey(cacheKey);
		return ServerResponse.createByErrorMessage(result);
	}

	/**
	 * 获取验证码后，忘记密码的校检方法
	 * @param mobileNum	手机号
	 * @param verifyNum	验证码
	 */
	@Override
	public ServerResponse<String> forgetPwd(String mobileNum, String verifyNum) {
		// TODO Auto-generated method stub
		if(null == mobileNum || "".equals(mobileNum) || null == verifyNum || "".equals(verifyNum))
			return ServerResponse.createByErrorMessage("请填写手机号或验证码！");
		ServerResponse response = MsgUtil.verifyValid(Const.FORGET_PREFIX, mobileNum, verifyNum);//验证码检验
		if(!response.isSuccess())
			return response;
		User user = userMapper.selectByMobile(mobileNum);
		if(user == null)
			ServerResponse.createByErrorMessage("手机号尚未注册！");
		String token = UUID.randomUUID().toString();
		VerifyCache.setKeyValue( Const.CHANGE_PWD_PREFIX + token, user.getUserId());//加前缀简单防范
		return ServerResponse.createBySuccess("验证成功，请于5分钟内修改密码！", token);
	}
	
	@Override
	public ServerResponse<String> changePasswordWithoutLogin(String token, String password) {
		if(StringUtil.isEmpty(token) || StringUtil.isEmpty(password))
			return ServerResponse.createByErrorMessage("请传递需要token的参数和规范的密码！");
		String userId = VerifyCache.getValueByKey(Const.CHANGE_PWD_PREFIX + token);
		if(StringUtil.isEmpty(userId))
			return ServerResponse.createByErrorMessage("token无效！");
		String pwdNew = MD5Util.MD5EncodeUtf8(password);
		int result = userMapper.updatePwdByUserId(userId, pwdNew);
		if(result != 1)
			return ServerResponse.createByErrorMessage("未知错误，更新失败");
		VerifyCache.removeValueByKey(Const.CHANGE_PWD_PREFIX + token);
		return ServerResponse.createBySuccessMessage("更新成功！");
		
	}
	
	//登陆后的修改密码
	/**
	 * 登陆后的修改密码
	 */
	@Override
	public ServerResponse<String> changePasswordLogined(String passwordOld, String passwordNew, HttpSession session){
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if(StringUtil.isEmpty(passwordOld) || StringUtil.isEmpty(passwordNew))
			return ServerResponse.createByErrorMessage("新密码和旧密码不能为空");
		if(user == null)
			return ServerResponse.createByErrorMessage("请重新登录");
		String userId = user.getUserId();
		String password = MD5Util.MD5EncodeUtf8(passwordOld);
		int updateCount = userMapper.checkSessionUserPassword(userId, password);
		if(updateCount <= 0)
			return ServerResponse.createByErrorMessage("密码错误");
		password = MD5Util.MD5EncodeUtf8(passwordNew);
		int changeCount = userMapper.updatePwdByUserId(userId, password);
		if(changeCount <= 0)
			return ServerResponse.createByErrorMessage("未知错误，修改失败！");
		session.removeAttribute(Const.CURRENT_USER);
		return ServerResponse.createBySuccessMessage("修改成功，请重新登录！");
		
	}
	
	/**
	 * 本用户登陆后，本用户信息修改
	 * @param user
	 * @param session
	 * @return
	 */
	@Override
	public ServerResponse<String> updateUserInfo(User user, HttpSession session){
		if(user == null) {
			return ServerResponse.createByErrorMessage("请填写用户信息后再提交！");
		}
		User loginUser = (User) session.getAttribute(Const.CURRENT_USER);
		if(!StringUtil.isEmpty(user.getPassword()) || (!StringUtil.isEmpty(user.getUsername()) && !user.getUsername().equals(loginUser.getUsername()))) {
			return ServerResponse.createByErrorMessage("修改用户唯一性信息失败，请联系后台开发组");
		}
		
		if(!StringUtil.isEmpty(user.getUserPower()) && !user.getUserPower().equals(loginUser.getUserPower())) {//当权限发生改变时
			user.setUserPower(loginUser.getUserPower());
			return ServerResponse.createByErrorMessage("修改错误，权限不可更改！");
		}
		
		if(!StringUtil.isEmpty(user.getMobile()) && !user.getMobile().equals(loginUser.getMobile())) {
			user.setMobile(loginUser.getMobile());
			return ServerResponse.createByErrorMessage("该接口不可更改手机号！");
		}
		
		if(!StringUtil.isEmpty(user.getEmail()) && !user.getEmail().equals(loginUser.getEmail())) {
			user.setEmail(loginUser.getEmail());
			return ServerResponse.createByErrorMessage("该接口不可更改电子邮箱！");
		}
		
		String userId = loginUser.getUserId();
		Long pkId = loginUser.getPkId();
		user.setPkId(pkId);
		user.setUserId(userId);
		UserCommonUtil.UserInitToDatabase(user, loginUser);
		int updateCount = userMapper.updateByPrimaryKeySelective(user);
		if(updateCount <= 0)
			return ServerResponse.createByErrorMessage("修改失败，未知错误");
		//session.removeAttribute(Const.CURRENT_USER);			//重新登录功能
		return ServerResponse.createBySuccessMessage("修改成功");
	}
	
	/**
	 * 更改手机号前的验证
	 * @param mobileNum
	 * @param verifyNum
	 * @param session
	 * @return
	 */
	@Override
	public ServerResponse<String> changeMobileLogined(String mobileNum, String verifyNum, HttpSession session){
		if(StringUtil.isEmpty(mobileNum) || StringUtil.isEmpty(verifyNum))
			return ServerResponse.createByErrorMessage("请填写手机号和验证码");
		String serverVerify = VerifyCache.getValueByKey(Const.RESET_MOBILE_PREFIX + mobileNum);
		if(StringUtil.isEmpty(serverVerify))
			return ServerResponse.createByErrorMessage("请获取验证码！");
		if(serverVerify.length() != 6)
			return ServerResponse.createByErrorMessage("验证码失效，请重新获取验证码！");
		User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
		if(currentUser == null)
			return ServerResponse.createByErrorMessage("请先登录");
		if(!mobileNum.equals(currentUser.getMobile()))
			return ServerResponse.createByErrorMessage("请正确填写手机号码");
		if(!verifyNum.equals(serverVerify))
			return ServerResponse.createByErrorMessage("验证码错误！"); 
		String token = UUID.randomUUID().toString();//创建修改手机号的token
		VerifyCache.setKeyValue(Const.RESET_MOBILE_PREFIX + mobileNum, token);
		System.out.println(Const.RESET_MOBILE_PREFIX + mobileNum + "----------------->" + token);
		return ServerResponse.createBySuccessMessage("验证成功！");
	}
	
	@Override
	public ServerResponse<String> changeMobileNum(String mobileNum, String verifyNum, String token, HttpSession session){
		if(StringUtil.isEmpty(mobileNum) || StringUtil.isEmpty(verifyNum))
			return ServerResponse.createByErrorMessage("请填写手机号和验证码");//验证空值
		//前端传的token 空校验  
		if(StringUtil.isEmpty(token))
			return ServerResponse.createByErrorMessage("token empty！");
		
		User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
		//检验缓存中是否有token,没有则回去从新获取验证码
		if(StringUtil.isEmpty(VerifyCache.getValueByKey(Const.RESET_MOBILE_PREFIX + currentUser.getMobile())))
			return ServerResponse.createByErrorMessage("token失效！");
		//检验是否登录
		if(currentUser == null)
			return ServerResponse.createByErrorMessage("请先登录");
		//检验token是否匹配
		if(!StringUtil.isEmpty(token) && !token.equals(VerifyCache.getValueByKey(Const.RESET_MOBILE_AFTER_PREFIX + mobileNum)))
			ServerResponse.createByErrorMessage("token error！");
		//检验手机号有无修改
		if(mobileNum.equals(currentUser.getMobile()))
			return ServerResponse.createByErrorMessage("您到底是否要修改新的手机号，还是在玩我？");
		//检验手机号是否存在
		int mobileCount = userMapper.checkMobileNum(mobileNum);
		if(mobileCount > 0)
			return ServerResponse.createByErrorMessage("该手机号已绑定别的账号！");
		String serverVerify = VerifyCache.getValueByKey(Const.RESET_MOBILE_AFTER_PREFIX + mobileNum);
		//缓存验证码空校检
		if(StringUtil.isEmpty(serverVerify))
			return ServerResponse.createByErrorMessage("请获取验证码！");
		//更换手机验证码匹配校检
		if(!verifyNum.equals(serverVerify))
			return ServerResponse.createByErrorMessage("验证码错误！"); 
		User updateUser = new User();
		updateUser.setUserId(currentUser.getUserId());
		updateUser.setPkId(currentUser.getPkId());
		updateUser.setMobile(mobileNum);
		userMapper.updateByPrimaryKeySelective(updateUser);
		return ServerResponse.createBySuccessMessage("验证成功！");
		
	}
	
}
