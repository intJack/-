package edu.just.resource_management_system.mapper;

import edu.just.resource_management_system.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    /**
     * 查询所有用户，用于后台管理的查询功能
     * @return 存储所有用户的list
     */
    List<User> selectAll();

    /**
     * 查询指定 用户,用于后台管理的查询功能
     * @param id 用户id
     * @return user
     */
    User selectUserById(Long id);

    /**
     * 实现用户的登录功能
     * @param username 用户名
     * @param password 从前台接收并进行加密加工之后的password
     * @return null or Not null
     */
    User login(@Param("username")String username,@Param("password")String password);

    /**
     * 实现新用户的注册功能
     * @param user 通过 消息转换器 将表单数据封装成 用户
     */
    void register(User user);


    void UpdateUserInfo(@Param("userName")String userName,
                        @Param("email")String email,@Param("phoneNumber")String phoneNumber,
                        @Param("id")Long id);
    void UpdateUserPassword(@Param("userPassword")String userPassword,@Param("id")Long id);

    /**
     * 用户提交资源 并不会直接提交到资源表 需要经过管理员的审核 才能加入到资源部
     */
    void InsertResource(@Param("user_id")Long user_id,@Param("resourceTitle")String resourceTitle,@Param("tagName")String tagName,
                        @Param("languageName")String languageName,@Param("resourceDescription")String resourceDescription);

}
