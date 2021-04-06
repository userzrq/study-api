package com.userzrq.designPatterns.Observers;

import lombok.Getter;

import java.util.Observable;

/**
 * 观察者模式
 * <p>
 * 观察目标类
 *
 * @author zhangruiqi 10017
 * @create 2021/4/2
 */
@Getter
public class JavaStackObservable extends Observable {

    private String artile;

    /**
     * 发表文章
     *
     * @param artile
     */
    public void publish(String artile) {

        // 执行方法
        this.artile = artile;

        // 改变状态
        this.setChanged();

        // 通知所有观察者
        this.notifyObservers();
    }
}
