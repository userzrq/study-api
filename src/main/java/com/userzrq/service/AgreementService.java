package com.userzrq.service;

import java.util.Map;

public interface AgreementService {

    Map<String, Object> queryUserAgreementInfo(String custNo);

    Long recordUserAgreeInfo(String custNo);
}
