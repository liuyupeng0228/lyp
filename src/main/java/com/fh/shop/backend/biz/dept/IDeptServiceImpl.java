package com.fh.shop.backend.biz.dept;

import com.fh.shop.backend.mapper.dept.IDeptMapper;
import com.fh.shop.backend.po.dept.Dept;
import com.fh.shop.backend.util.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("deptService")
public class IDeptServiceImpl implements IDeptService {

    @Autowired
    private IDeptMapper deptMapper;

    @Override
    public List<Dept> queryDeptList() {
        CacheManager instance = CacheManager.getInstance();
        //字符串可以自己定义，但是要与下方保持一致
        Object deptListInfo = instance.getObj("deptList");
        if (deptListInfo != null){
            return (List<Dept>) deptListInfo;
        }
        List<Dept> deptList = deptMapper.queryDeptList();
        instance.putObj("deptList",deptList);
        return deptList;
    }

    @Override
    public void addDeptInfo(Dept dept) {
        deptMapper.addDeptInfo(dept);
        //手动刷新缓存
        CacheManager.getInstance().remove("deptList");
    }

    @Override
    public void deleteDpt(List<Integer> ids) {
        deptMapper.deleteDpt(ids);
        //手动刷新缓存
        CacheManager.getInstance().remove("deptList");
    }

    @Override
    public void updateDeptInfo(Dept dept) {
        deptMapper.updateDeptInfo(dept);
        //手动刷新缓存
        CacheManager.getInstance().remove("deptList");
    }

    @Override
    public List<Dept> findChildByChildId(List<Integer> childNodes) {
        return deptMapper.findChildByChildId(childNodes);
    }
}
