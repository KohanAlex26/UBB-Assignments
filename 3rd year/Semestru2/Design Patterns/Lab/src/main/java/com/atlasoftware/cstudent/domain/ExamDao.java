package com.atlasoftware.cstudent.domain;

import com.atlasoftware.cstudent.domain.memento.SnapshotExam;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "exam")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExamDao {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    private String date;
    @Column(name = "course_code")
    private String courseCode;
    @Enumerated(EnumType.STRING)
    @Column(name = "exam_type")
    private ExamType examType;
    private String formula;

    private ExamDao(ExamDaoBuilder builder) {
        this.id = builder.id;
        this.date = builder.date;
        this.courseCode = builder.courseCode;
        this.examType = builder.examType;
        this.formula = builder.formula;
    }

    public SnapshotExam createSnapshot()
    {
        return new SnapshotExam(id,date,courseCode,examType,formula);
    }

    public void restore(SnapshotExam snapshotExam)
    {
        this.id=snapshotExam.getId();
        this.date=snapshotExam.getDate();
        this.courseCode=snapshotExam.getCourseCode();
        this.examType=snapshotExam.getExamType();
        this.formula=snapshotExam.getFormula();
    }



    public static class ExamDaoBuilder {
        private UUID id;
        private String date;
        private String courseCode;
        private ExamType examType;
        private String formula;

        public ExamDaoBuilder(UUID id)
        {
            this.id=id;
        }
        public ExamDaoBuilder buildDate(String date)
        {
            this.date=date;
            return this;
        }
        public ExamDaoBuilder buildCourseCode(String courseCode)
        {
            this.courseCode=courseCode;
            return this;
        }
        public ExamDaoBuilder buildExamType(ExamType examType)
        {
            this.examType=examType;
            return this;
        }
        public ExamDaoBuilder buildFormula(String formula)
        {
            this.formula=formula;
            return this;
        }
        public ExamDao build()
        {
            ExamDao exam=new ExamDao(this);
            return exam;
        }

    }

}
