package com.keenant.madgrades.data;

import java.util.regex.Pattern;

public class Attribute {
    protected String name;
    protected String code;
    public Attribute(String code, String name){
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

    public static Attribute fromJsonStr(String jsonStr){
        if (jsonStr == null || "".equals(jsonStr)) {
            return null;
        }

        Pattern pattern = Pattern.compile("");
        String[] b = (String[]) pattern.matcher(jsonStr).results().map(matchResult -> matchResult.group(1)).toArray();
        return new Attribute(b[0], b[1]);
    }
}
