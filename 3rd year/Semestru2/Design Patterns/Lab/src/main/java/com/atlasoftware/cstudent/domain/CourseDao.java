package com.atlasoftware.cstudent.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "course")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseDao {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    @Column(name = "course_code")
    private String courseCode;
    private String courseName;
    private Integer credits;
    @Column(name = "grade_formula")
    private String gradeFormula;
    private Boolean exam;
    @Column(name = "practical_exam")
    private Boolean practicalExam;
    @Column(name = "partial_exam")
    private Boolean partialExam;
    @Enumerated(EnumType.STRING)
    @Column(name = "exam_type")
    private ExamType examType;
    @Enumerated(EnumType.STRING)
    @Column(name = "course_type")
    private CourseType courseType;
    @ManyToMany(mappedBy = "courses")
    private Set<StudentDao> students;
    @OneToMany(mappedBy = "courseDao", cascade = CascadeType.ALL)
    @Column(name = "course_activities")
    private List<CourseActivityDao> courseActivities;

    @Override
    public String toString() {
        return "CourseDao{" +
                "id=" + id +
                ", courseCode='" + courseCode + '\'' +
                ", courseName='" + courseName + '\'' +
                ", credits=" + credits +
                ", gradeFormula='" + gradeFormula + '\'' +
                ", exam=" + exam +
                ", practicalExam=" + practicalExam +
                ", partialExam=" + partialExam +
                ", examType=" + examType +
                ", courseType=" + courseType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseDao)) return false;
        CourseDao courseDao = (CourseDao) o;
        return Objects.equals(getId(), courseDao.getId()) && Objects.equals(getCourseCode(), courseDao.getCourseCode()) && Objects.equals(getCourseName(), courseDao.getCourseName()) && Objects.equals(getCredits(), courseDao.getCredits()) && Objects.equals(getGradeFormula(), courseDao.getGradeFormula()) && Objects.equals(getExam(), courseDao.getExam()) && Objects.equals(getPracticalExam(), courseDao.getPracticalExam()) && Objects.equals(getPartialExam(), courseDao.getPartialExam()) && getExamType() == courseDao.getExamType() && getCourseType() == courseDao.getCourseType() && Objects.equals(getStudents(), courseDao.getStudents()) && Objects.equals(getCourseActivities(), courseDao.getCourseActivities());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCourseCode(), getCourseName(), getCredits(), getGradeFormula(), getExam(), getPracticalExam(), getPartialExam(), getExamType(), getCourseType(), getCourseActivities());
    }
}
