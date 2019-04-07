package com.fh.shop.backend.mapper.area;

import com.fh.shop.backend.po.area.Area;

import java.util.List;

public interface IAreaMapper {
    List<Area> queryAreaList();

    void addAreaInfo(Area area);

    void updateAreaInfo(Area area);

    void deleteArea(List<Integer> ids);
}
