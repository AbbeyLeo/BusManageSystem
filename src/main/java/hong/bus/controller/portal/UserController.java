/**
 * 
 */
package hong.bus.controller.portal;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.StringUtil;

import hong.bus.common.Const;
import hong.bus.common.ServerResponse;
import hong.bus.pojo.User;
import hong.bus.service.IUserService;
import hong.bus.util.MsgUtil;

/**
 * @author Abbey
 *
 */
@Controller
@RequestMapping("/user/")
public class UserController {
	//首先注入Service
	@Autowired
	private IUserService iUserService;
	/**
	 * 用户密码登录
	 * @param username 用户名
	 * @param password	密码
	 * @param session  当前服务器session
	 * @return
	 */
	@RequestMapping(value = "login.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<User> login( String username, String password, HttpSession session) {
		ServerResponse<User> response = iUserService.userPwdLogin(username, password);
		if(response.isSuccess()) {
			session.setAttribute(Const.CURRENT_USER, response.getData());
		}
		//System.out.println("当前sessionId  =>  " + session.getId());
		return response;
	}
	
	/**
	 * 用户手机登录
	 * @param mobilNum 手机号
	 * @param password	验证码
	 * @param session  当前服务器session
	 * @return
	 */
	@RequestMapping(value = "mobile_login.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<User> mobileLogin(String mobileNum, String verifyNum, HttpSession session) {
		ServerResponse<User> response = iUserService.userMobileLogin(mobileNum, verifyNum);
		if(response.isSuccess()) {
			session.setAttribute(Const.CURRENT_USER, response.getData());
		}
		return response;
	}
	
	/**
	 * 获取验证码
	 * @param mobileNum
	 * @param perfix
	 * @return
	 */
	@RequestMapping(value = "mobile_verify.do", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse<String> getMobileVerifyNum(String mobileNum, String prefix) {
		ServerResponse response = iUserService.getVerifyNum(mobileNum, prefix);
        return response;
	}
	
	
	/**
	 * 退出登录
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "logout.do", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse<String> logout(HttpSession session) {
		
		if(session == null || session.getAttribute(Const.CURRENT_USER) == null)
		{
			return ServerResponse.createByErrorMessage("用户尚未登录");
		}
		session.removeAttribute(Const.CURRENT_USER);
		return ServerResponse.createBySuccessMessage("成功退出登录");
	}
	/**
	 * 注册/添加 用户
	 * @param user
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "registerUser.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<String> register(User user, HttpSession session){
		if(session == null || session.getAttribute(Const.CURRENT_USER) == null)
		{
			return ServerResponse.createByErrorMessage("添加用户前，请先登录");
		}
		User nowUser = (User) session.getAttribute(Const.CURRENT_USER);
		return iUserService.userRegister(user, nowUser);
		
	}
	
	/**
	 * 获取当前登录的用户信息
	 * @param session	当前的HttpSession
	 * @return ServerResponse
	 */
	@RequestMapping(value = "get_login_user_info.do", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse<User> getLoginUserInfo(HttpSession session){
		User user = (User) session.getAttribute(Const.CURRENT_USER);//login的时候放了个没有密码的user进去
		if(user != null) {
			return ServerResponse.createBySuccess(user);
		}
		
		return ServerResponse.createByErrorMessage("用户尚未登录，无法获取当前登录的用户信息");
	}
	
	/**
	 * 未登录的忘记密码
	 * @param mobileNum
	 * @param verifyNum
	 */
	@RequestMapping(value = "forget_pwd.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<String> forgetPwd(String mobileNum, String verifyNum){
		ServerResponse response = iUserService.forgetPwd(mobileNum, verifyNum);
		if(response == null)
			return ServerResponse.createBySuccessMessage("调用service失败！");
		return response;
	}
	
	/**
	 * 未登录，忘记密码下的修改密码
	 * @param token 获取验证码的手机的token
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "forget_reset_password.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<String> changePasswordWithoutLogin(String token, String password){
		if(StringUtil.isEmpty(token) || StringUtil.isEmpty(password))
			return ServerResponse.createByErrorMessage("请传递需要token的参数和规范的密码！");
		ServerResponse response = iUserService.changePasswordWithoutLogin(token, password);
		return response;
		
	}
	
	/**
	 * 登录后的修改密码
	 * @param passwordOld
	 * @param passwordNew
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "reset_password_logined.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<String> changePasswordLogined(String passwordOld, String passwordNew, HttpSession session){
		if(session == null || session.getAttribute(Const.CURRENT_USER) == null)
		{
			return ServerResponse.createByErrorMessage("请先登录");
		}
		ServerResponse response = iUserService.changePasswordLogined(passwordOld, passwordNew, session);
		return response;
		
	}
	
	/**
	 * 本用户登陆后，本用户信息修改
	 * @param user
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "reset_userinfo.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<String> updateUserInfo(User user, HttpSession session){
		if(session == null || session.getAttribute(Const.CURRENT_USER) == null)
		{
			return ServerResponse.createByErrorMessage("请先登录");
		}
		ServerResponse response = iUserService.updateUserInfo(user, session);
		return response;
	}
	
	/**
	 * 更改手机号前的验证
	 * @param mobileNum
	 * @param verifyNum
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "change_mobile_logined_check.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<String> changeMobileLogined(String mobileNum, String verifyNum, HttpSession session){
		
		if(session == null || session.getAttribute(Const.CURRENT_USER) == null)
		{
			return ServerResponse.createByErrorMessage("请先登录");
		}
		ServerResponse response = iUserService.changeMobileLogined(mobileNum, verifyNum, session);
		return response;
	}
	
	@RequestMapping(value = "reset_mobile_check.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<String> changeMobileNum(String mobileNum, String verifyNum, String token, HttpSession session){
		if(session == null || session.getAttribute(Const.CURRENT_USER) == null)
		{
			return ServerResponse.createByErrorMessage("请先登录");
		}
		ServerResponse response = iUserService.changeMobileNum(mobileNum, verifyNum, token, session);
		return response;
		
	}
}
