package com.fh.shop.backend.mapper.dept;

import com.fh.shop.backend.po.dept.Dept;

import java.util.List;

public interface IDeptMapper {
    List<Dept> queryDeptList();

    void addDeptInfo(Dept dept);

    void deleteDpt(List<Integer> ids);

    void updateDeptInfo(Dept dept);

    List<Dept> findChildByChildId(List<Integer> childNodes);
}
