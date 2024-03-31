package com.mjc.stage2;

public class ThreadSafeSingleton {
    private static volatile ThreadSafeSingleton instance;

    //other useful variables here
    private ThreadSafeSingleton() {
    }

    public static ThreadSafeSingleton getInstance() {
        if (instance == null) {
            synchronized (ThreadSafeSingleton.class) {
                if (instance == null) {
                    instance = new ThreadSafeSingleton();
                }
            }
        }
        return instance;
    }
}
