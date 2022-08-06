package com.atlasoftware.cstudent.domain.memento;

import com.atlasoftware.cstudent.domain.ExamType;

import java.util.UUID;

public class SnapshotExam {
    private UUID id;
    private String date;
    private String courseCode;
    private ExamType examType;
    private String formula;

    public SnapshotExam(UUID id, String date, String courseCode, ExamType examType, String formula)
    {
        this.id=id;
        this.date=date;
        this.courseCode=courseCode;
        this.examType=examType;
        this.formula=formula;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public ExamType getExamType() {
        return examType;
    }

    public void setExamType(ExamType examType) {
        this.examType = examType;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }
}
