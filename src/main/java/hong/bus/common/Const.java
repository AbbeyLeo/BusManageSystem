/**
 * 
 */
package hong.bus.common;

/**
 * @author Abbey
 *
 */
public class Const {
	/**当前用户*/
	public static final String CURRENT_USER = "currentUser";
	public static final String MOBILE_BIND_PREFIX = "bind_";
	public static final String ERROR_LOCK = "error_lock";
	
	//验证码缓存前缀
	public static final String LOGIN_PREFIX = "login_";//登录
	public static final String FORGET_PREFIX = "forget_";//忘记密码
	public static final String RESET_MOBILE_PREFIX = "reset_mobile_";//修改手机号前的手机验证
	public static final String RESET_MOBILE_AFTER_PREFIX = "reset_mobile_after_";//修改手机号前的手机验证
	
	//忘记密码Token前缀
	public static final String CHANGE_PWD_PREFIX = "change_pwd_";
}
