package com.keenant.madgrades.data;

import com.google.common.collect.Sets;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.GsonBuilder;

import java.util.*;
import java.util.stream.Collectors;

public class Course {
    private UUID uuid;

    private final int courseNumber;
    private String name;
    private final Set<CourseOffering> teachings = new HashSet<>();
    private List<Breadth> breadths;
    private GE ge;

    private Ethnic ethnic;
    private Level level;

    private String description;

    public Course(int courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAttributes(FullAttributes attributes) {
        this.breadths = attributes.getBreadthList();
        this.ge = attributes.getGe();
        this.level = attributes.getLevel();
        this.ethnic = attributes.getEthnic();
    }

    public void setName(String name) {
        this.name = name;
    }

    public Ethnic getEthnic() {
        return ethnic;
    }

    public Set<Subject> subjects() {
        return teachings.stream()
                .flatMap(o -> o.getSubjects().stream())
                .collect(Collectors.toSet());
    }

    public UUID getUuid() {
        if (this.uuid == null) {
            this.uuid = this.generateUuid();
        }
        return this.uuid;
    }

    public void setBreadths(List<Breadth> breadths) {
        this.breadths = breadths;
    }

    public Set<String> subjectCodes() {
        return teachings.stream()
                .flatMap(o -> o.getSubjectCodes().stream())
                .collect(Collectors.toSet());
    }

    public List<Breadth> getBreadths() {
        return this.breadths;
    }

    public GE getGE() {
        return this.ge;
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public Set<CourseOffering> getTeachings() {
        return teachings;
    }

    /**
     * Generate a unique id. It is based on the course number and the
     * UUID of the first offering of this course.
     *
     * @return the unique id.
     */
    public UUID generateUuid() {
        int hash = Objects.hash(this.name, this.courseNumber);
        return UUID.nameUUIDFromBytes((hash + "").getBytes());
    }

    public boolean isCourse(CourseOffering offering) {
        return courseNumber == offering.getCourseNumber() &&
                !Sets.intersection(subjectCodes(), offering.getSubjectCodes()).isEmpty();
    }

    public boolean isCourse(Course other) {
        return courseNumber == other.courseNumber &&
                !Sets.intersection(subjectCodes(), other.subjectCodes()).isEmpty();
    }

    public void addCourseOffering(CourseOffering offering) {
        // this is a weird situation, but it can happen

        // find an existing course offering with the same term code
        CourseOffering existingOffering = teachings.stream()
                .filter(o -> o.getTermCode() == offering.getTermCode())
                .findFirst()
                .orElse(null);

        // new term, we add it
        if (existingOffering == null) {
            teachings.add(offering);
        } else {
            existingOffering.merge(offering);
        }
    }

    public Level getLevel() {
        return level;
    }

    public GE getGe() {
        return ge;
    }

    public Optional<String> getName() {
        if (name != null)
            return Optional.of(name);

        String name = null;
        int term = 0;

        for (CourseOffering offering : teachings) {
            if (offering.getTermCode() < term)
                continue;

            if (offering.getName().isPresent())
                name = offering.getName().orElse(null);
        }

        return Optional.ofNullable(name);
    }

    @Override
    public String toString() {
        var builder = new GsonBuilder();
        builder.setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                return false;
            }

            @Override
            public boolean shouldSkipClass(Class<?> aClass) {
                return false;
            }
        });
        var gson = builder.create();
        return gson.toJson(this);
    }
}
