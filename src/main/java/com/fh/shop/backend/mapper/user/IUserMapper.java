package com.fh.shop.backend.mapper.user;

import com.fh.shop.backend.po.user.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserMapper {
    void addUser(User user);

    void updateUserType(Integer id);

    List<User> queryUserList(User user);

    Long queryCount(User user);

    void addUserInfo(User user);

    void deleteUser(List<Integer> ids);

    User findUserById(Integer id);

    void updateUser(User user);

    void updateUserDeptId(@Param("ids") List<Integer> ids, @Param("deptId") Integer deptId);
}
