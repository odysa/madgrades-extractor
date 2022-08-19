package com.keenant.madgrades.data;

import java.util.ArrayList;
import java.util.List;

public class CourseAttribute {
    private List<Breadth> breadths;
    private List<GE> ges;
    private Level level;

    public CourseAttribute(){
        this.breadths = new ArrayList<>();
        this.ges = new ArrayList<>();
    }

    public void addBreadth(Breadth b){
        this.breadths.add(b);
    }

    public void addGE(GE ge){
        this.ges.add(ge);
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }

    public List<Breadth> getBreadths() {
        return breadths;
    }

    public List<GE> getGes() {
        return ges;
    }
}
