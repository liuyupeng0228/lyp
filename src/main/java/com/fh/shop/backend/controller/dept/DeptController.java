package com.fh.shop.backend.controller.dept;

import com.fh.shop.backend.biz.dept.IDeptService;
import com.fh.shop.backend.common.ResponseEnum;
import com.fh.shop.backend.common.ServerResponse;
import com.fh.shop.backend.po.dept.Dept;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/dept")
public class DeptController {

    @Resource(name = "deptService")
    private IDeptService deptService;

    @RequestMapping("queryDeptList")
    public ServerResponse queryDeptList(){
        List<Dept> deptList = deptService.queryDeptList();
        return ServerResponse.successData(ResponseEnum.SUCCESS,deptList);
    }

    @RequestMapping("addDeptInfo")
    public ServerResponse addDeptInfo(Dept dept){
        deptService.addDeptInfo(dept);
        return ServerResponse.successData(ResponseEnum.SUCCESS,dept.getId());
    }

    @RequestMapping("updateDeptInfo")
    public ServerResponse updateDeptInfo(Dept dept){
        deptService.updateDeptInfo(dept);
        return ServerResponse.success(ResponseEnum.SUCCESS);
    }

    @RequestMapping("deleteDpt")
    public ServerResponse deleteDpt(@RequestParam("ids[]") List<Integer> ids){
        deptService.deleteDpt(ids);
        return ServerResponse.success(ResponseEnum.SUCCESS);
    }
}
