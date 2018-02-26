package com.keenant.madgrades.data;

import com.keenant.madgrades.fields.GradeType;
import java.util.Map;

public class GradeDistribution {
  private final String subjectCode;
  private final int courseNumber;
  private final int sectionNumber;
  private final Map<GradeType, Integer> grades;

  public GradeDistribution(String subjectCode, int courseNumber, int sectionNumber,
      Map<GradeType, Integer> grades) {
    this.subjectCode = subjectCode;
    this.courseNumber = courseNumber;
    this.sectionNumber = sectionNumber;
    this.grades = grades;
  }

  public String getSubjectCode() {
    return subjectCode;
  }

  public int getCourseNumber() {
    return courseNumber;
  }

  public int getSectionNumber() {
    return sectionNumber;
  }

  public Map<GradeType, Integer> getGrades() {
    return grades;
  }
}
