//package com.atlasoftware.cstudent.domain.builder;
//
//import com.atlasoftware.cstudent.domain.ExamDao;
//import com.atlasoftware.cstudent.domain.ExamType;
//import com.atlasoftware.cstudent.domain.StudentDao;
//
//import javax.persistence.Column;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import java.util.UUID;
//
//public static class ExamDaoBuilder {
//    private UUID id;
//    private String date;
//    private String courseCode;
//    private ExamType examType;
//    private String formula;
//
//    public ExamDaoBuilder buildId(UUID id)
//    {
//        this.id=id;
//        return this;
//    }
//    public ExamDaoBuilder buildDate(String date)
//    {
//        this.date=date;
//        return this;
//    }
//    public ExamDaoBuilder buildCourseCode(String courseCode)
//    {
//        this.courseCode=courseCode;
//        return this;
//    }
//    public ExamDaoBuilder buildExamType(ExamType examType)
//    {
//        this.examType=examType;
//        return this;
//    }
//    public ExamDaoBuilder buildFormula(String formula)
//    {
//        this.formula=formula;
//        return this;
//    }
//    public ExamDao build()
//    {
//        ExamDao exam=new ExamDao(this);
//        return exam;
//    }
//
//}
