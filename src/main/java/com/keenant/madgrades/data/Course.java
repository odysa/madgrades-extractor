package com.keenant.madgrades.data;

import com.google.common.collect.Sets;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class Course {
  private final int courseNumber;
  private String name;
  private final Set<CourseOffering> courseOfferings = new HashSet<>();
  private Set<Breadth> breadths;
  private Set<GE> ges;
  private Level level;

  public Course(int courseNumber) {
    this.courseNumber = courseNumber;
  }
  public Course(int courseNumber, Set<Breadth> breadths, Set<GE> ges, Level level) {
    this.courseNumber = courseNumber;
    this.breadths = breadths;
    this.ges = ges;
    this.level = level;
  }
  public void setName(String name) {
    this.name = name;
  }

  public Set<Subject> subjects() {
    return courseOfferings.stream()
        .flatMap(o -> o.getSubjects().stream())
        .collect(Collectors.toSet());
  }

  public Set<String> subjectCodes() {
    return courseOfferings.stream()
        .flatMap(o -> o.getSubjectCodes().stream())
        .collect(Collectors.toSet());
  }

  public Set<Breadth> getBreadths(){
    return this.breadths;
  }

  public Set<GE> getGEs(){
    return this.ges;
  }

  public int getCourseNumber() {
    return courseNumber;
  }

  public Set<CourseOffering> getCourseOfferings() {
    return courseOfferings;
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
    CourseOffering existingOffering = courseOfferings.stream()
        .filter(o -> o.getTermCode() == offering.getTermCode())
        .findFirst()
        .orElse(null);

    // new term, we add it
    if (existingOffering == null) {
      courseOfferings.add(offering);
    }
    else {
      existingOffering.merge(offering);
    }
  }

  public Optional<String> getName() {
    if (name != null)
      return Optional.of(name);

    String name = null;
    int term = 0;

    for (CourseOffering offering : courseOfferings) {
      if (offering.getTermCode() < term)
        continue;

      if (offering.getName().isPresent())
        name = offering.getName().orElse(null);
    }

    return Optional.ofNullable(name);
  }
}
