package com.yt.delegate.simple;

public class TargetB implements ITarget {

    @Override
    public void doing(String command) {
        System.out.println("B执行命令" + command);
    }
}
