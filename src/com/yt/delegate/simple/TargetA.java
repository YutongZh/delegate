package com.yt.delegate.simple;

public class TargetA implements ITarget{

    @Override
    public void doing(String command) {
        System.out.println("A执行命令" + command);
    }
}
