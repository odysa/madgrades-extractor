package com.keenant.madgrades.data;

public class Level extends Attribute {
    public Level(String code, String name) {
        super(code, name);
    }

    public static Level fromJsonStr(String jsonStr) {
        var attr = Attribute.fromJsonStr(jsonStr);
        if (attr == null) {
            return null;
        }
        return new Level(attr.code, attr.name);
    }
}
