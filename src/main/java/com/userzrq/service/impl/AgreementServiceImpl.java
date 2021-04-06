package com.userzrq.service.impl;

import com.userzrq.service.AgreementService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AgreementServiceImpl implements AgreementService {
    @Override
    public Map<String, Object> queryUserAgreementInfo(String custNo) {
        // 模拟查询过程
        return null;
    }

    @Override
    public Long recordUserAgreeInfo(String custNo) {
        // 模拟数据库插入数据过程
        return null;
    }
}
