package com.nextgen.domain.observer;

/**
 * 被观察者接口（观察者模式：所有被观察者必须实现该接口）
 * 定义注册、移除、通知观察者的统一方法
 */
public interface Subject {
    // 注册观察者（添加订阅者）
    void attach(Observer observer);

    // 移除观察者（取消订阅者）
    void detach(Observer observer);

    // 通知所有观察者（状态变化时调用）
    void notifyObservers();
}