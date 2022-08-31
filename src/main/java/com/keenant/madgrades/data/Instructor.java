package com.keenant.madgrades.data;

public class Instructor {
    private final int id;
    private final String name;

    public Instructor(int id, String name) {
        this.id = id;
        if (name != null && name.contains("/")) {
            var split = name.trim().split("/");
            this.name = split[split.length - 1].trim();
        } else {
            this.name = name;
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
