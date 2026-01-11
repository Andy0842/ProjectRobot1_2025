package com.nextgen.domain.observer;

import com.nextgen.domain.Sale;

/**
 * 观察者接口（观察者模式：所有观察者必须实现该接口）
 * 定义统一的更新方法，接收被观察者的消息
 */
public interface Observer {
    // 更新方法：被观察者状态变化时调用
    void update(Sale sale);
}