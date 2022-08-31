package com.keenant.madgrades.data;

import java.util.List;

public class FullAttributes {
    private GE ge;
    private Level level;
    private Ethnic ethnic;
    private List<Breadth> breadthList;

    public FullAttributes setBreadthList(List<Breadth> breadthList) {
        this.breadthList = breadthList;
        return this;
    }

    public FullAttributes setEthnic(Ethnic ethnic) {
        this.ethnic = ethnic;
        return this;
    }

    public FullAttributes setGe(GE ge) {
        this.ge = ge;
        return this;
    }

    public FullAttributes setLevel(Level level) {
        this.level = level;
        return this;
    }

    public Ethnic getEthnic() {
        return ethnic;
    }

    public GE getGe() {
        return ge;
    }

    public Level getLevel() {
        return level;
    }

    public List<Breadth> getBreadthList() {
        return breadthList;
    }

    @Override
    public String toString() {
        return "FullAttributes{" +
                "ge=" + ge +
                ", level=" + level +
                ", ethnic=" + ethnic +
                ", breadthList=" + breadthList +
                '}';
    }
}
