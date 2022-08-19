package com.keenant.madgrades.data;

import java.util.Objects;
import java.util.regex.Pattern;

public class Breadth extends Attribute{

    public Breadth(String code, String name) {
        super(code, name);
    }

    public static Breadth fromJsonStr(String jsonStr) {
        return (Breadth) Attribute.fromJsonStr(jsonStr);
    }
}
