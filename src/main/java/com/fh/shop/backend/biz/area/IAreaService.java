package com.fh.shop.backend.biz.area;

import com.fh.shop.backend.po.area.Area;

import java.util.List;

public interface IAreaService {
    List<Area> queryAreaList();

    void addAreaInfo(Area area);

    void updateAreaInfo(Area area);

    void deleteArea(List<Integer> ids);
}
