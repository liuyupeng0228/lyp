package com.fh.shop.backend.biz.user;

import com.fh.shop.backend.common.DataTableResult;
import com.fh.shop.backend.po.user.User;
import com.fh.shop.backend.vo.UserVo;

import java.util.List;

public interface IUserService {
    void addUser(User user);

    void updateUserType(Integer id);

    List<User> queryUserList(User user);

    public DataTableResult getDataTableResult(User user, Integer draw, Integer start, Integer length, String orderDir, String beanName);

    Long queryCount(User user);

    void addUserInfo(User user);

    void deleteUser(List<Integer> ids);

    UserVo findUserById(Integer id);

    void updateUser(User user);

    void updateUserDeptId(List<Integer> ids, Integer deptId);
}
