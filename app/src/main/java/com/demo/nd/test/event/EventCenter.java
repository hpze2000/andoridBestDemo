package com.demo.nd.test.event;


import com.demo.nd.test.base.lifecycle.LifeCycleComponentManager;

import de.greenrobot.event.EventBus;

public class EventCenter {

    private static final EventBus instance = new EventBus();

    private EventCenter() {
    }

    public static DemoSimpleEventHandler bindContainerAndHandler(Object container, DemoSimpleEventHandler handler) {
        LifeCycleComponentManager.tryAddComponentToContainer(handler, container);
        return handler;
    }

    public static final EventBus getInstance() {
        return instance;
    }
}
