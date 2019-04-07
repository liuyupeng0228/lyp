package com.fh.shop.backend.biz.area;

import com.fh.shop.backend.mapper.area.IAreaMapper;
import com.fh.shop.backend.po.area.Area;
import com.fh.shop.backend.util.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IAreaServiceImpl implements IAreaService {

    @Autowired
    private IAreaMapper areaMapper;

    @Override
    public List<Area> queryAreaList() {
        CacheManager instance = CacheManager.getInstance();
        Object areaListInfo = instance.getObj("areaList");
        if (areaListInfo != null){
            return (List<Area>) areaListInfo;
        }
        List<Area> areaList = areaMapper.queryAreaList();
        instance.putObj("areaList",areaList);
        return areaList;
    }

    @Override
    public void addAreaInfo(Area area) {
        areaMapper.addAreaInfo(area);
        CacheManager.getInstance().remove("areaList");
    }

    @Override
    public void updateAreaInfo(Area area) {
        areaMapper.updateAreaInfo(area);
        CacheManager.getInstance().remove("areaList");
    }

    @Override
    public void deleteArea(List<Integer> ids) {
        areaMapper.deleteArea(ids);
        CacheManager.getInstance().remove("areaList");
    }
}
