/**
 * 
 */
package hong.bus.util;

import org.apache.commons.lang3.StringUtils;

import hong.bus.pojo.User;

/**
 * @author Abbey
 *
 */
public class UserCommonUtil {
	public static User UserInitToUI(User user) {
		user.setPassword(null);
		user.setDr(null);
		user.setTs(null);
		return user;
	}
	/**
	 * 修改用户的用户格式化
	 * @param user
	 * @param modifier
	 * @return
	 */
	public static User UserInitToDatabase(User user,User modifier) {
		user.setUsername(null);
		user.setPassword(null);
		user.setCreateTime(null);
		user.setCreator(null);
		user.setDr(null);
		user.setModifyTime(SystemCommonUtil.getServerDate());
		user.setTs(null);
		user.setModifier(modifier.getUserId());
		return user;
	}
}
