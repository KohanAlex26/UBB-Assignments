package com.atlasoftware.cstudent.domain;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.UUIDGenerator;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.UUID;


@Entity
@Table(name = "course_activity")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseActivityDao {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    @Enumerated(EnumType.STRING)
    @Column(name = "activity_type")
    private ActivityType activityType;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "professor_id", referencedColumnName = "id")
    private ProfessorDao professorDao;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id", referencedColumnName = "id", nullable = false)
    private CourseDao courseDao;
    private String courseCode;
    private String courseName;
    private String formula;
    private String startTime;
    private String endTime;
    @Enumerated(EnumType.STRING)
    private DayOfWeek day;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private RoomDao roomDao;
    private Boolean weekly;
    private Boolean week;
    private Boolean online;
}
