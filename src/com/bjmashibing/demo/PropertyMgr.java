package com.bjmashibing.demo;

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {
    static Properties proes = new Properties();

    static {
        try {
            proes.load(PropertyMgr.class.getClassLoader().getResourceAsStream("resources/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object key(String key){
        if (proes == null) return null;
        return proes.get(key);
    }

}
