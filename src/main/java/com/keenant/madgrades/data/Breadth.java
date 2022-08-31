package com.keenant.madgrades.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class Breadth extends Attribute {

    public Breadth(String code, String name) {
        super(code, name);
    }

    public static Breadth fromJsonStr(String jsonStr) {
        return (Breadth) Attribute.fromJsonStr(jsonStr);
    }

    public static List<Breadth> listFromJsonStr(String jsonStr) {
        if (jsonStr == null || "".equals(jsonStr) || "[]".equals(jsonStr)) {
            return null;
        }
        var breadthList = new ArrayList<Breadth>();
        Pattern pattern = Pattern.compile("\\{code:([A-Z\s]+),description:([a-zA-Z\s]+)}");
        var matcher = pattern.matcher(jsonStr);
        while (matcher.find()) {
            var code = matcher.group(1);
            var name = matcher.group(2);
            breadthList.add(new Breadth(code, name));
        }
        return breadthList;
    }
}
