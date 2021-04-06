package com.userzrq.designPatterns.Observers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者模式
 * <p>
 * 观察者类
 *
 * @author zhangruiqi 10017
 * @create 2021/4/2
 */
@RequiredArgsConstructor
public class ReaderObserver implements Observer {

    @NonNull
    private String name;

    private String article;


    @Override
    public void update(Observable o, Object arg) {
        // 更新文章
        updateArticle(o);
    }

    private void updateArticle(Observable o) {
        JavaStackObservable javaStackObservable = (JavaStackObservable) o;
        this.article = javaStackObservable.getArtile();

        System.out.printf("我是读者：%s，文章已更新为：%s\n", this.name, this.article);
    }
}
