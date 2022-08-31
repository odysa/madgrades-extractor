package com.keenant.madgrades.data;

public class Ethnic extends Attribute {
    public Ethnic(String code, String name) {
        super(code, name);
    }

    public static Ethnic fromJsonStr(String jsonStr) {
        var attr = Attribute.fromJsonStr(jsonStr);
        if (attr == null) {
            return null;
        }
        return new Ethnic(attr.code, attr.name);
    }
}
