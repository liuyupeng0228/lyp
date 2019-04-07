package com.fh.shop.backend.biz.user;

import com.fh.shop.backend.common.DataTableResult;
import com.fh.shop.backend.mapper.user.IUserMapper;
import com.fh.shop.backend.po.user.User;
import com.fh.shop.backend.util.MD5Util;
import com.fh.shop.backend.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class IUserServiceImpl implements IUserService {

    @Autowired
    private IUserMapper userMapper;

    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    @Override
    public void updateUserType(Integer id) {
        userMapper.updateUserType(id);
    }

    @Override
    public List<User> queryUserList(User user) {
        return userMapper.queryUserList(user);
    }

    public DataTableResult getDataTableResult(User user, Integer draw, Integer start, Integer length, String orderDir, String beanName) {
        //进行部门id的分割
        String deptIds = user.getDeptIds();
        //判断：若部门id的字符串不为空才可进行切分
        if (!StringUtils.isEmpty(deptIds)){
            String[] idArr = deptIds.split(",");
            List<Integer> idList = new ArrayList<>();
            for (String ids : idArr) {
                idList.add(Integer.parseInt(ids));
            }
            user.setDeptList(idList);
        }

        /*查询总条数*/
        Long totalCount = queryCount(user);
        user.setStartPos(start);
        user.setPageSize(length);
        user.setSort(orderDir);
        user.setSortField(beanName);
        //查询出数据
        List<User> userList = queryUserList(user);
        //po转为vo
        List<UserVo> userVoList = new ArrayList<>();
        for (User userPo : userList) {
            //每次都会有一个新的用户，所以要在循环内对每个用户都创建一个对象
            UserVo userVo = new UserVo();
            //将数据库查询出来的数据赋给 UserVo（就是前台对应的属性）
            userVo.setId(userPo.getId());
            userVo.setUsername(userPo.getUsername());
            userVo.setRealname(userPo.getRealname());
            userVo.setBirthday(userPo.getBirthday());
            userVo.setSex(userPo.getSex());
            userVo.setSalary(userPo.getSalary());
            userVo.setUserType(userPo.getUserType());
            userVo.setName(userPo.getDeptName());
            userVo.setHeadPortrait(userPo.getHeadPortrait());
            //将数据放到vo实体类中
            userVoList.add(userVo);
        }
        //将前台需要的数据整合
        return DataTableResult.build(draw, totalCount, totalCount, userVoList);
    }

    @Override
    public Long queryCount(User user) {
        return userMapper.queryCount(user);
    }

    @Override
    public void addUserInfo(User user) {
        String salt = UUID.randomUUID().toString();
        user.setSalt(salt);
        user.setPassword(MD5Util.getStringMD5(MD5Util.getStringMD5(user.getPassword())+salt));
        userMapper.addUserInfo(user);
    }

    @Override
    public void deleteUser(List<Integer> ids) {
        userMapper.deleteUser(ids);
    }

    @Override
    public UserVo findUserById(Integer id) {
        User userPo = userMapper.findUserById(id);
        UserVo userVo = new UserVo();
        userVo.setId(userPo.getId());
        userVo.setUsername(userPo.getUsername());
        userVo.setRealname(userPo.getRealname());
        userVo.setSex(userPo.getSex());
        userVo.setBirthday(userPo.getBirthday());
        userVo.setSalary(userPo.getSalary());
        userVo.setName(userPo.getDeptName());
        userVo.setDeptId(userPo.getDeptId());
        return userVo;
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void updateUserDeptId(List<Integer> ids, Integer deptId) {
        userMapper.updateUserDeptId(ids,deptId);
    }

}
