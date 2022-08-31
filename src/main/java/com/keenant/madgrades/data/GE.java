package com.keenant.madgrades.data;

// GE include General Education and Ethnic studies
public class GE extends Attribute {

    public GE(String code, String name) {
        super(code, name);
    }

    public static GE fromJsonStr(String jsonStr) {
        var attr = Attribute.fromJsonStr(jsonStr);
        if (attr == null) {
            return null;
        }
        return new GE(attr.code, attr.name);
    }
}
