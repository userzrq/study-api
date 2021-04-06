package com.userzrq.threaddemo.threadLocalDemo;

import com.userzrq.threaddemo.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ThreadLocalWithUserContext implements Runnable {
    @Autowired
    private UserRepository userRepository;

    private static ThreadLocal<Context> userContext = new ThreadLocal<>();
    private Integer userId;

    @Override
    public void run() {
        String userName = userRepository.getUserNameForUserId(userId);
        userContext.set(new Context(userName));
        System.out.println("thread context for given userId: "
                + userId + " is: " + userContext.get());
    }
}

class Context {
    private String userName;

    public Context(String userName) {
        this.userName = userName;
    }
}
