package com.userzrq.threaddemo.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
     String getUserNameForUserId(Integer userId);

}
