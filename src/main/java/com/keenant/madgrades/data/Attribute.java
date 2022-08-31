package com.keenant.madgrades.data;

import com.google.gson.Gson;

import java.util.Objects;
import java.util.regex.Pattern;

public class Attribute {
    protected String name;
    protected String code;

    public Attribute(String str) {

    }

    public Attribute(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static Attribute fromJsonStr(String jsonStr) {
        if (jsonStr == null || "".equals(jsonStr) || "[]".equals(jsonStr)) {
            return null;
        }
        Pattern pattern = Pattern.compile("\\{code:([A-Z\s]+),description:([a-zA-Z\s]+)}");
        var matcher = pattern.matcher(jsonStr);
        if (matcher.find()) {
            var code = matcher.group(1);
            var name = matcher.group(2);
            return new Attribute(code, name);
        }
        return null;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
