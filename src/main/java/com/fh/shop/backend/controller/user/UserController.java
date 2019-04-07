package com.fh.shop.backend.controller.user;

import com.fh.shop.backend.biz.dept.IDeptService;
import com.fh.shop.backend.biz.login.ILoginService;
import com.fh.shop.backend.biz.user.IUserService;
import com.fh.shop.backend.common.DataTableResult;
import com.fh.shop.backend.common.ResponseEnum;
import com.fh.shop.backend.common.ServerResponse;
import com.fh.shop.backend.po.dept.Dept;
import com.fh.shop.backend.po.user.User;
import com.fh.shop.backend.util.MD5Util;
import com.fh.shop.backend.util.SystemContext;
import com.fh.shop.backend.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @Resource(name = "deptService")
    private IDeptService deptService;

    @Resource(name="loginService")
    private ILoginService loginService;


    @RequestMapping("userToIndex")
    public String toIndex(){
        return "redirect:toProductList.jhtml";
    }

    @RequestMapping("toAddUser")
    public String toAddUser(){
        return "user/addUser";
    }

    @RequestMapping("toUserList")
    public String toUserList(){
        return "user/user";
    }

    //查询用户
    @RequestMapping("queryUserList")
    @ResponseBody
    public ServerResponse queryUserList(User user, Integer draw, Integer start, Integer length, HttpServletRequest request){
        //只有数据库的字段与属性名一致时才可直接用
        //排序的列号
        String order = request.getParameter(SystemContext.ORDER_INDEX);
        //排序的顺序asc or desc
        String orderDir = request.getParameter(SystemContext.ORDER_DIR);
        //排序的列。注意，我认为页面上的列的名字要和表中列的名字一致，否则，会导致SQL拼接错误
        String beanName = request.getParameter("columns["+order+"][data]");

        //查询数据
        DataTableResult result = userService.getDataTableResult(user, draw, start, length, orderDir, beanName);

        //响应数据
        return ServerResponse.successData(ResponseEnum.SUCCESS,result);
    }

    //添加用户
    @RequestMapping("addUserInfo")
    @ResponseBody
    public ServerResponse addUserInfo(User user){
        userService.addUserInfo(user);
        return ServerResponse.success(ResponseEnum.SUCCESS);
    }

    //回显
    @RequestMapping("findUserById")
    @ResponseBody
    public ServerResponse findUserById(Integer id){
        UserVo userVo = userService.findUserById(id);
        return ServerResponse.successData(ResponseEnum.SUCCESS,userVo);
    }

    @RequestMapping("updateUser")
    @ResponseBody
    public ServerResponse updateUser(User user){
        userService.updateUser(user);
        return ServerResponse.success(ResponseEnum.SUCCESS);
    }


    //删除用户
    @RequestMapping("deleteUser")
    @ResponseBody
    public ServerResponse deleteUser(@RequestParam("ids[]") List<Integer> ids){
        userService.deleteUser(ids);
        return ServerResponse.success(ResponseEnum.SUCCESS);
    }

    //批量修改用户部门
    @RequestMapping("updateUserDeptId")
    @ResponseBody
    public ServerResponse updateUserDeptId(@RequestParam("ids[]") List<Integer> ids,Integer deptId){
        userService.updateUserDeptId(ids,deptId);
        return ServerResponse.success(ResponseEnum.SUCCESS);
    }

    //根据部门导出用户信息
    @RequestMapping("exportExcelByDept")
    @ResponseBody
    public void exportExcelByDept(HttpServletResponse response,
                                            @RequestParam("childNodes") List<Integer> childNodes){
        List<Dept> deptList = deptService.findChildByChildId(childNodes);

    }

    /*注册用户*/
    @RequestMapping("addUser")
    public ServerResponse toAddUser(User user){
        User userByUserName = loginService.findUserByUserName(user.getUsername());
        if (null != userByUserName) {
            return ServerResponse.error(ResponseEnum.ERROR_ADD);
        }
        String uuid = UUID.randomUUID().toString();
        user.setSalt(uuid);
        user.setPassword(MD5Util.getStringMD5(MD5Util.getStringMD5(user.getPassword())+uuid));
        userService.addUser(user);
        return ServerResponse.success(ResponseEnum.SUCCESS_ADD);
    }

    /*解锁*/
    @RequestMapping("updateUserType")
    public String updateUserType(Integer id){
        userService.updateUserType(id);
        return "redirect:/queryUserList.jhtml";
    }

    @RequestMapping("toLogin")
    public String toLogin(){
        return "redirect:/login.jsp";
    }

}
