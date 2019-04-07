package com.fh.shop.backend.controller.area;

import com.fh.shop.backend.biz.area.IAreaService;
import com.fh.shop.backend.common.ResponseEnum;
import com.fh.shop.backend.common.ServerResponse;
import com.fh.shop.backend.po.area.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/area")
public class AreaController {

    @Autowired
    private IAreaService areaService;

    @RequestMapping("toAreaList")
    public String toAreaList(){
        return "area/areaList";
    }

    @RequestMapping("queryAreaList")
    @ResponseBody
    public ServerResponse queryAreaList(){
        List<Area> areaList = areaService.queryAreaList();
        return ServerResponse.successData(ResponseEnum.SUCCESS,areaList);
    }

    @RequestMapping("addAreaInfo")
    @ResponseBody
    public ServerResponse addAreaInfo(Area area){
        areaService.addAreaInfo(area);
        return ServerResponse.successData(ResponseEnum.SUCCESS,area.getId());
    }

    @RequestMapping("updateAreaInfo")
    @ResponseBody
    public ServerResponse updateAreaInfo(Area area){
        areaService.updateAreaInfo(area);
        return ServerResponse.success(ResponseEnum.SUCCESS);
    }

    @RequestMapping("deleteArea")
    @ResponseBody
    public ServerResponse deleteArea(@RequestParam("ids[]") List<Integer> ids){
        areaService.deleteArea(ids);
        return ServerResponse.success(ResponseEnum.SUCCESS);
    }
}
