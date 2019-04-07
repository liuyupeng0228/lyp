package com.fh.shop.backend.controller.log;

import com.fh.shop.backend.biz.log.ILogService;
import com.fh.shop.backend.po.log.LogInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LogController {

    @Resource(name = "logService")
    private ILogService logService;

    @RequestMapping("toLogList")
    public String toLogList(){
        return "log/logList";
    }

    @RequestMapping("queryLog")
    @ResponseBody
    public Map queryLog(LogInfo log, Integer draw, Integer start, Integer length){
        Map map = new HashMap();
        Long totalCount = logService.queryCount(log);
        log.setStartPos(start);
        log.setPageSize(length);
        List<LogInfo> logList = logService.queryLog(log);

        //将数据整合
        map.put("draw",draw);
        map.put("recordsTotal",totalCount);
        map.put("recordsFiltered",totalCount);
        map.put("data",logList);
        return map;
    }
}
