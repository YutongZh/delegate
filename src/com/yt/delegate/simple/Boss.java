package com.yt.delegate.simple;

/**
 * 客户端请求Boss   委派者（leader） 被委派者（Itarget）
 */
public class Boss {
    public static void main(String[] args) {
        new Leader().doing("登录");
    }
}
