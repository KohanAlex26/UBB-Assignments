package com.atlasoftware.cstudent.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "time_table")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TimeTableDao {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    @OneToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private StudentDao student;
    @OneToOne
    @JoinColumn(name = "course_activity_id", referencedColumnName = "id")
    private CourseActivityDao courseActivity;
}
