package hong.bus.dao;

import org.apache.ibatis.annotations.Param;

import hong.bus.pojo.User;
import hong.bus.pojo.UserKey;

public interface UserMapper {
    int deleteByPrimaryKey(UserKey key);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(UserKey key);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    /**查用户名是否存在*/
    int checkUsername(String username);
    /**用户登录*/
    User selectLogin(@Param("username") String username, @Param("password") String password);
    /**查询手机号是否存在*/
    int checkMobileNum(String mobileNum);
    /**根据手机号获取user*/
    User selectByMobile(String mobileNum);
    /**根据user_id和password查询用户是否存在*/
    int checkSessionUserPassword(@Param("userId")String userId, @Param("password") String password);
    /** 根据user_id 修改密码*/
    int updatePwdByUserId(@Param("userId")String userId, @Param("password") String password);
    
}