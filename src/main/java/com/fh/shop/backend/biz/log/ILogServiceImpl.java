package com.fh.shop.backend.biz.log;

import com.fh.shop.backend.mapper.log.ILogMapper;
import com.fh.shop.backend.po.log.LogInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("logService")
public class ILogServiceImpl implements ILogService{

    @Autowired
    private ILogMapper logMapper;

    @Override
    public void addLog(LogInfo log) {
        logMapper.addLog(log);
    }

    @Override
    public Long queryCount(LogInfo log) {
        return logMapper.queryCount(log);
    }

    @Override
    public List<LogInfo> queryLog(LogInfo log) {
        return logMapper.queryLog(log);
    }
}
