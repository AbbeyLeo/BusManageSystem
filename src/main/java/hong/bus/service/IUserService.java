/**
 * 用户的service层代理
 */
package hong.bus.service;

import javax.servlet.http.HttpSession;

import hong.bus.common.ServerResponse;
import hong.bus.pojo.User;

/**
 * @author Abbey
 *
 */
public interface IUserService {
	ServerResponse<User> userPwdLogin(String username, String password);
	ServerResponse<String> userRegister(User user, User parentUser);
	ServerResponse<User> userMobileLogin(String mobileNum, String verifyNum);
	ServerResponse<String> getVerifyNum(String mobileNum, String prefix);
	ServerResponse<String> forgetPwd(String mobileNum, String verifyNum);
	ServerResponse<String> changePasswordWithoutLogin(String token, String password);
	ServerResponse<String> changePasswordLogined(String passwordOld, String passwordNew, HttpSession session);
	ServerResponse<String> updateUserInfo(User user, HttpSession session);
	ServerResponse<String> changeMobileLogined(String mobileNum, String verifyNum, HttpSession session);
	ServerResponse<String> changeMobileNum(String mobileNum, String verifyNum, String token, HttpSession session);
}
                      