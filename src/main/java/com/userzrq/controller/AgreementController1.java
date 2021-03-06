package com.userzrq.controller;

import com.userzrq.service.AgreementService;
import com.userzrq.util.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 第一版 Controller
 * 频繁操作数据库，如果存在高并发，数据库压力会很大
 */

@RestController
@Slf4j
@RequestMapping("/sys/agreement")
public class AgreementController1 {

    @Autowired
    private AgreementService agreementService;

    @RequestMapping("/hasAgree")
    public Map<String, Object> hasAgree(HttpServletRequest request) {

        Map<String, Object> response = new HashMap<>();
        String custNo = (String) SessionUtil.getSessionAttribute("CUST_NO");
        Map<String, Object> userAgreementInfo = agreementService.queryUserAgreementInfo(custNo);

        /**
         *  <dependency>
         *      <groupId>org.apache.commons</groupId>
         *      <artifactId>commons-collections4</artifactId>
         *      <version>4.2</version>
         *  </dependency>
         */
        String hasAgree = MapUtils.isEmpty(userAgreementInfo) ? "0" : "1";
        response.put("hasAgree", hasAgree);
        return response;
    }

    @RequestMapping("/recordAgree")
    public Map<String, Object> recordAgree() {
        Map<String, Object> response = new HashMap<>();

        String custNo = (String) SessionUtil.getSessionAttribute("CUST_NO");
        Long result = agreementService.recordUserAgreeInfo(custNo);   // Add to DB
        if (1 == result) {
            response.put("STATUS", "1");
            response.put("MSG", "成功");
        } else {
            response.put("STATUS", "0");
            response.put("MSG", "失败");
        }

        return response;
    }
}
