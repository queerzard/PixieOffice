package com.github.queerzard.pixieoffice.utils;

import lombok.Getter;
import lombok.SneakyThrows;

public abstract class AsyncExecutor implements Runnable, IAsyncTask {


    @Getter
    private Thread thread;
    @Getter
    private Object returnValue;

    @Getter
    private long delayInMillis = 0L;

    public AsyncExecutor(long delayInMillis) {
        this.delayInMillis = delayInMillis;
        this.thread = new Thread(this);
        this.thread.start();
    }

    public static Object task(long delay, IAsyncTask task) {
        return new AsyncExecutor(delay) {
            @Override
            public Object runAsync() {
                return task.runAsync();
            }
        }.getReturnValue();
    }

    public static Object task(IAsyncTask task) {
        return task(0L, task);
    }

    public AsyncExecutor() {
        this(0L);
    }

    @SneakyThrows
    @Override
    public void run() {
        Thread.sleep(this.delayInMillis);
        returnValue = runAsync();
    }

}
