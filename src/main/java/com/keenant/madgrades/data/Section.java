package com.keenant.madgrades.data;

import com.keenant.madgrades.utils.GradeType;
import com.keenant.madgrades.utils.SectionType;

import java.util.*;
import java.util.stream.Collectors;

public class Section {
  private final int termCode;
  private final int courseNumber;
  private final SectionType sectionType;
  private final int sectionNumber;
  private final Schedule schedule;
  private final Room room;
  private final Set<Instructor> instructors;
  private  Map<GradeType, Integer> grades;
  public Section(int termCode, int courseNumber, SectionType sectionType,
      int sectionNumber, Schedule schedule, Room room, Set<Instructor> instructors) {
    this.termCode = termCode;
    this.courseNumber = courseNumber;
    this.sectionType = sectionType;
    this.sectionNumber = sectionNumber;
    this.schedule = schedule;
    this.room = room;
    this.instructors = instructors;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Section) {
      Section other = (Section) o;
      return termCode == other.termCode &&
          courseNumber == other.courseNumber &&
          sectionType == other.sectionType &&
          sectionNumber == other.sectionNumber &&
          Objects.equals(schedule, other.schedule) &&
          Objects.equals(room, other.room) &&
          instructors.size() == other.instructors.size() &&
          instructors.containsAll(other.instructors);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(termCode, courseNumber, sectionType, sectionNumber, schedule, room, instructors);
  }

  public UUID generateUuid(CourseOffering offering) {
    String instructorsStr = instructors.stream()
        .sorted()
        .map(Object::toString)
        .collect(Collectors.joining());

    // section is weird because we rely on the parent UUID to generate its section UUID
    String uniqueStr = offering.generateUuid().toString() + sectionType + sectionNumber + schedule + room + instructorsStr;
    return UUID.nameUUIDFromBytes(uniqueStr.getBytes());
  }

  public boolean isCrossListed(DirSection other) {
    return courseNumber == other.getCourseNumber() &&
        sectionType == other.getSectionType() &&
        sectionNumber == other.getSectionNumber() &&
        Objects.equals(schedule, other.getSchedule()) &&
        Objects.equals(room, other.getRoom()) &&
        instructors.equals(other.getInstructors());
  }

  public int getCourseNumber() {
    return courseNumber;
  }

  public SectionType getSectionType() {
    return sectionType;
  }

  public int getSectionNumber() {
    return sectionNumber;
  }

  public Schedule getSchedule() {
    return schedule;
  }

  public Optional<Room> getRoom() {
    return Optional.ofNullable(room);
  }

  public Set<Instructor> getInstructors() {
    return instructors;
  }

  public void setGrades(Map<GradeType, Integer> grades) {
    this.grades = grades;
  }

  public Map<GradeType, Integer> getGrades() {
    return grades;
  }
}
