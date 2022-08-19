package com.keenant.madgrades.data;

public class Level  extends Attribute{
    public Level(String code, String name) {
        super(code, name);
    }

    public static Level fromJsonStr(String jsonStr){
        return (Level) Attribute.fromJsonStr(jsonStr);
    }
}
