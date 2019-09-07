package com.yt.delegate.simple;

import java.util.HashMap;
import java.util.Map;

public class Leader {

    //持有引用  代理 策略
    private Map<String, ITarget> map = new HashMap<String, ITarget>();

    public Leader() {
        map.put("登录", new TargetA());
        map.put("安全", new TargetB());
    }

    public void doing(String command){
        ITarget iTarget = map.get(command);
        iTarget.doing(command);
    }
}
